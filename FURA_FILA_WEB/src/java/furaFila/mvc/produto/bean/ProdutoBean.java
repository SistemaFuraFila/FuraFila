package furaFila.mvc.produto.bean;

import furaFila.mvc.dimensao.business.DimensaoBusiness;
import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import furaFila.mvc.estoque.business.EstoqueBusiness;
import furaFila.mvc.estoque.service.EstoqueService;
import furaFila.mvc.estoqueEntrada.business.EstoqueEntradaBusiness;
import furaFila.mvc.estoqueEntrada.model.EstoqueEntrada;
import furaFila.mvc.estoqueProdutos.business.EstoqueProdutosBusiness;
import furaFila.mvc.estoqueProdutos.model.EstoqueProdutos;
import furaFila.mvc.estoqueProdutos.service.EstoqueProdutosService;
import furaFila.mvc.estoqueSaida.business.EstoqueSaidaBusiness;
import furaFila.mvc.estoqueSaida.model.EstoqueSaida;
import furaFila.mvc.imagem.business.ImagemBusiness;
import furaFila.mvc.pedidos.model.Pedidos;
import furaFila.mvc.produto.business.ProdutoBusiness;
import furaFila.mvc.produto.model.Produto;
import furaFila.mvc.produto.service.ProdutoService;
import furaFila.mvc.tipoProduto.model.TipoProduto;
import furaFila.mvc.tipoProduto.service.TipoProdutoService;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import furaFila.utils.Navegacao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produtos = new Produto();
    private EstoqueProdutos estoqueProdutos = new EstoqueProdutos();
    private Pedidos pedidos = new Pedidos();

    private ProdutoBusiness produtoBusiness = new ProdutoBusiness();
    private ImagemBusiness imagemBusiness = new ImagemBusiness();
    private DimensaoBusiness dimensaoBusiness = new DimensaoBusiness();
    private EstoqueBusiness estoqueBusiness = new EstoqueBusiness();
    private EstoqueProdutosBusiness estoqueProdutosBusiness = new EstoqueProdutosBusiness();
    private EstoqueEntradaBusiness estoqueEntradaBusiness = new EstoqueEntradaBusiness();
    private EstoqueSaidaBusiness estoqueSaidaBusiness = new EstoqueSaidaBusiness();

    private TipoProdutoService tipoProdutoService = new TipoProdutoService();
    private ProdutoService produtoService = new ProdutoService();
    private EstoqueService estoqueService = new EstoqueService();
    private EstoqueProdutosService estoqueProdutosService = new EstoqueProdutosService();

    private List<TipoProduto> lstTipoProduto = new ArrayList<>();
    private List<EstoqueProdutos> lstProdutos = new ArrayList<>();
    private List<Produto> lstCardapio = new ArrayList<>();
    private StreamedContent imagem;
    private boolean flgBotoes = true;
    private Integer qtdeEstoqueAntiga = 0;
    private String tituloPagina = "";

    public void popularTipoProduto() {
        try {
            setLstTipoProduto(getTipoProdutoService().listarTipoProduto(false));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void popularProdutos() {
        try {
            setLstProdutos(getEstoqueProdutosService().listarProdutosPorCodigoEstabelecimento(pegarSessaoEstabelecimento()));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void popularCardapio() {
        try {
            setLstCardapio(getProdutoService().buscarCardapio(pegarSessaoEstabelecimento()));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void gravar(ActionEvent ae) {

        try {

            getImagemBusiness().gravar(getProdutos().getImagem());

            getDimensaoBusiness().gravar(getProdutos().getDimensao());

            getProdutoBusiness().gravar(getProdutos());

            EstoqueEntrada estoqueEntrada = new EstoqueEntrada();
            estoqueEntrada.setQtdEntrada(0);
            estoqueEntrada.setProduto(getProdutos());
            estoqueEntrada.getMotivoEntrada().setMotivoEntrada(FuraFilaConstants.MOTIVO_ENTRADA_INICIAL);

            getEstoqueEntradaBusiness().gravar(estoqueEntrada, pegarSessaoEstabelecimento().clonar());
            
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void alterar(ActionEvent ae) {

        try {

            getProdutoBusiness().alterar(getEstoqueProdutos().getProduto());

            getImagemBusiness().alterar(getEstoqueProdutos().getProduto().getImagem());

            getDimensaoBusiness().alterar(getEstoqueProdutos().getProduto().getDimensao());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void alterarStatus(ActionEvent ae) {

        try {

            Produto p = getEstoqueProdutos().getProduto().clonar();
            p.setStatus(!p.getStatus());

            getProdutoBusiness().alterarStatus(p);

            if (!p.getStatus()) {
                EstoqueSaida es = new EstoqueSaida();
                es.setProduto(p);
                es.setQtdSaida(0);
                es.getMotivoSaida().setMotivoSaida(FuraFilaConstants.MOTIVO_SAIDA_CORRECAO);

                getEstoqueSaidaBusiness().gravar(es, pegarSessaoEstabelecimento());
            }

            atualizarPagina();

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void entradaProdutos(ActionEvent ae) {

        try {

            if (getQtdeEstoqueAntiga() > getEstoqueProdutos().getQtdEstoque()) {
                //ENTRADA PRODUTOS

                EstoqueEntrada ee = new EstoqueEntrada();
                ee.setProduto(getEstoqueProdutos().getProduto());
                ee.setQtdEntrada(getQtdeEstoqueAntiga());
                ee.getMotivoEntrada().setMotivoEntrada(FuraFilaConstants.MOTIVO_ENTRADA_ENTRADA);

                getEstoqueEntradaBusiness().gravar(ee, pegarSessaoEstabelecimento());

            }

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void alterarPreco(ActionEvent ae) {
        try {
            getProdutoBusiness().alterarPreco(getEstoqueProdutos().getProduto());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void carregarImagem(FileUploadEvent event) {

        try {

            EstoqueProdutos ep = new EstoqueProdutos();
            ep.setProduto(getProdutos());
            ep.getEstoque().setEstabelecimento(pegarSessaoEstabelecimento());

            String ext[] = event.getFile().getFileName().split("\\.");
            String caminho = FuraFilaUtils.montarCaminho(null, pegarEstabelecimentoLoginSessao(), true);
            String nomeImagem = FuraFilaUtils.gerarNumeroAleatorio().toString() + "." + ext[ext.length - 1];

            getProdutos().getImagem().setImagem(FuraFilaUtils.copiarArquivo(caminho + nomeImagem, event.getFile().getInputstream()));

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public String atualizarPagina() {
        setProdutos(new Produto());
        return Navegacao.irEstoque();
    }

    public String atualizarCardapio() {
        return Navegacao.irCardapio();
    }

    public String atualizarPaginaComprarProduto() {
        return Navegacao.irPaginaComprarProduto();
    }

    public String pesquisarEnter(){
        return atualizarPaginaComprarProduto();
    }
    
    public Estabelecimento pegarSessaoEstabelecimento() {
        return (Estabelecimento) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO);
    }

    private EstabelecimentoLogin pegarEstabelecimentoLoginSessao() {
        return (EstabelecimentoLogin) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO_LOGIN);
    }

    public void habilitarBotoes(SelectEvent event) {
        manipularBotoes(false);
    }

    public void desabilitarBotoes(UnselectEvent event) {
        manipularBotoes(true);
    }

    private void manipularBotoes(boolean status) {
        setFlgBotoes(status);
    }

    public String nomeBotaoAtivarDesativar() {
        if (getEstoqueProdutos().getProduto().getStatus() == null) {
            return FuraFilaConstants.ATIVAR;
        }
        return getEstoqueProdutos().getProduto().getStatus() ? FuraFilaConstants.DESATIVAR : FuraFilaConstants.ATIVAR;
    }

    public Boolean verificarEstoque() {
        if (getEstoqueProdutos().getQtdEstoque() == null) {
            return true;
        }
        return getEstoqueProdutos().getQtdEstoque() != 0;
    }

    public Boolean verificarPrecoZerado() {
        if (getEstoqueProdutos().getProduto().getValor_unitario() == null) {
            return true;
        }
        return getEstoqueProdutos().getProduto().getValor_unitario() != Double.parseDouble("0.0");
    }

    public ProdutoBusiness getProdutoBusiness() {
        return produtoBusiness;
    }

    public void setProdutoBusiness(ProdutoBusiness produtoBusiness) {
        this.produtoBusiness = produtoBusiness;
    }

    public Produto getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    public List<TipoProduto> getLstTipoProduto() {
        return lstTipoProduto;
    }

    public void setLstTipoProduto(List<TipoProduto> lstTipoProduto) {
        this.lstTipoProduto = lstTipoProduto;
    }

    public TipoProdutoService getTipoProdutoService() {
        return tipoProdutoService;
    }

    public void setTipoProdutoService(TipoProdutoService tipoProdutoService) {
        this.tipoProdutoService = tipoProdutoService;
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }

    public ImagemBusiness getImagemBusiness() {
        return imagemBusiness;
    }

    public void setImagemBusiness(ImagemBusiness imagemBusiness) {
        this.imagemBusiness = imagemBusiness;
    }

    public DimensaoBusiness getDimensaoBusiness() {
        return dimensaoBusiness;
    }

    public void setDimensaoBusiness(DimensaoBusiness dimensaoBusiness) {
        this.dimensaoBusiness = dimensaoBusiness;
    }

    public List<EstoqueProdutos> getLstProdutos() {
        return lstProdutos;
    }

    public void setLstProdutos(List<EstoqueProdutos> lstProdutos) {
        this.lstProdutos = lstProdutos;
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public EstoqueBusiness getEstoqueBusiness() {
        return estoqueBusiness;
    }

    public void setEstoqueBusiness(EstoqueBusiness estoqueBusiness) {
        this.estoqueBusiness = estoqueBusiness;
    }

    public EstoqueService getEstoqueService() {
        return estoqueService;
    }

    public void setEstoqueService(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    public EstoqueProdutosBusiness getEstoqueProdutosBusiness() {
        return estoqueProdutosBusiness;
    }

    public void setEstoqueProdutosBusiness(EstoqueProdutosBusiness estoqueProdutosBusiness) {
        this.estoqueProdutosBusiness = estoqueProdutosBusiness;
    }

    public EstoqueProdutosService getEstoqueProdutosService() {
        return estoqueProdutosService;
    }

    public void setEstoqueProdutosService(EstoqueProdutosService estoqueProdutosService) {
        this.estoqueProdutosService = estoqueProdutosService;
    }

    public EstoqueProdutos getEstoqueProdutos() {
        return estoqueProdutos;
    }

    public void setEstoqueProdutos(EstoqueProdutos estoqueProdutos) {
        if (estoqueProdutos != null) {
            setQtdeEstoqueAntiga(estoqueProdutos.getQtdEstoque());
            this.estoqueProdutos = estoqueProdutos;
        }
    }

    public boolean getFlgBotoes() {
        return flgBotoes;
    }

    public void setFlgBotoes(boolean flgBotoes) {
        this.flgBotoes = flgBotoes;
    }

    public Integer getQtdeEstoqueAntiga() {
        return qtdeEstoqueAntiga;
    }

    public void setQtdeEstoqueAntiga(Integer qtdeEstoqueAntiga) {
        this.qtdeEstoqueAntiga = qtdeEstoqueAntiga;
    }

    public EstoqueEntradaBusiness getEstoqueEntradaBusiness() {
        return estoqueEntradaBusiness;
    }

    public void setEstoqueEntradaBusiness(EstoqueEntradaBusiness estoqueEntradaBusiness) {
        this.estoqueEntradaBusiness = estoqueEntradaBusiness;
    }

    public EstoqueSaidaBusiness getEstoqueSaidaBusiness() {
        return estoqueSaidaBusiness;
    }

    public void setEstoqueSaidaBusiness(EstoqueSaidaBusiness estoqueSaidaBusiness) {
        this.estoqueSaidaBusiness = estoqueSaidaBusiness;
    }

    public List<Produto> getLstCardapio() {
        return lstCardapio;
    }

    public void setLstCardapio(List<Produto> lstCardapio) {
        this.lstCardapio = lstCardapio;
    }

    public String getTituloPagina() {
        return tituloPagina;
    }

    public void setTituloPagina(String tituloPagina) {
        this.tituloPagina = tituloPagina;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

}
