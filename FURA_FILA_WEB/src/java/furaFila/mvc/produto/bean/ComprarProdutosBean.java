package furaFila.mvc.produto.bean;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import furaFila.mvc.cep.model.Distancia;
import furaFila.mvc.cep.service.CepService;
import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.comanda.business.ComandaBusiness;
import furaFila.mvc.comanda.model.Comanda;
import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.conjuntoLocker.service.ConjuntoLockerService;
import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estabelecimento.service.EstabelecimentoService;
import furaFila.mvc.locker.business.LockerBusiness;
import furaFila.mvc.locker.model.Locker;
import furaFila.mvc.locker.service.LockerService;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.mvc.modelsGerais.ComprarProduto;
import furaFila.mvc.pedidoLocker.business.PedidoLockerBusiness;
import furaFila.mvc.pedidoLocker.model.PedidoLocker;
import furaFila.mvc.pedidos.business.PedidoBusiness;
import furaFila.mvc.pedidos.model.Pedidos;
import furaFila.mvc.produto.model.Produto;
import furaFila.mvc.produto.service.ProdutoService;
import furaFila.utils.EnviarEmails;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import furaFila.utils.Navegacao;
import furaFila.utils.TemplateEmail;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class ComprarProdutosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProdutoService produtoService = new ProdutoService();
    private ConjuntoLockerService conjuntoLockerService = new ConjuntoLockerService();
    private LockerService lockerService = new LockerService();
    private EstabelecimentoService estabelecimentoService = new EstabelecimentoService();

    private ComandaBusiness comandaBusiness = new ComandaBusiness();
    private PedidoBusiness pedidoBusiness = new PedidoBusiness();
    private PedidoLockerBusiness pedidoLockerBusiness = new PedidoLockerBusiness();
    private LockerBusiness lockerBusiness = new LockerBusiness();

    private ComprarProduto comprarProduto = new ComprarProduto();
    private Comanda comanda = new Comanda();
    private Pedidos pedidos = new Pedidos();
    private Produto produtos = new Produto();
    private Logradouro logradouro = new Logradouro();
    private PedidoLocker pedidoLocker = new PedidoLocker();
    private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();

    private List<Pedidos> lstProdutoComprar = new ArrayList<>();
    private List<PedidoLocker> lstCarrinho = new ArrayList<>();
    private List<ConjuntoLocker> lstConjuntoLockers = new ArrayList<>();
    private List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();
    private List<Estabelecimento> lstEstabelecimentosFiltro = new ArrayList<>();
    private List<SelectItem> lstOrdens = new ArrayList<>();

    private String centerGeoMap = "0,0";
    private MapModel geoModel = new DefaultMapModel();
    private Double latitudeHidden = 0D;
    private Double longitudeHidden = 0D;
    private String response;

    private Integer tabIndex = 0;

    public void popularProdutosComprar() {
        try {
            setLstProdutoComprar(getProdutoService().listarProdutosComprar(getComprarProduto()));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void popularFiltro(){
        
        try {
            setLstEstabelecimentosFiltro(getEstabelecimentoService().listarEstabelecimentos());
            
            getLstOrdens().clear();
            SelectItemGroup sigPreco = new SelectItemGroup("Preço");
            sigPreco.setSelectItems(new SelectItem[] {new SelectItem(" P.valor_unitario DESC","Maior Preço"), new SelectItem(" P.valor_unitario ASC","Menor preço")});
            getLstOrdens().add(sigPreco);
            
            SelectItemGroup sigProdutos = new SelectItemGroup("Produto");
            sigProdutos.setSelectItems(new SelectItem[] {new SelectItem(" P.produto_desc ASC", "A-Z"), new SelectItem(" P.produto_desc DESC", "Z-A")});
            getLstOrdens().add(sigProdutos);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        
    }
    
    public void popularLockers() {
        try {
            setTabIndex(0);
            setLstConjuntoLockers(getConjuntoLockerService().listarConjuntoLocker());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void organizarLockers() {

        try {
            popularLockers();
            HashMap<Integer, Object> hmDistancias = new HashMap<>();
            Distancia d = new Distancia();
            d.getOrigem().setLatitude(getLatitudeHidden());
            d.getOrigem().setLongitude(getLongitudeHidden());

            for (ConjuntoLocker l : getLstConjuntoLockers()) {

                d.setDestino(l.getLogradouro());
                FuraFilaUtils.calcularDistancia(d);

                hmDistancias.put(d.getDistancia(), l);

                LatLng center = new LatLng(l.getLogradouro().getLatitude(), l.getLogradouro().getLongitude());
                getGeoModel().addOverlay(new Marker(center, l.getConjunto_locker_desc()));

            }

            Map<Integer, Object> hmDistanciasOrdenadas = new TreeMap<>(hmDistancias);
            getLstConjuntoLockers().clear();

            for (Map.Entry<Integer, Object> distancia : hmDistanciasOrdenadas.entrySet()) {
                getLstConjuntoLockers().add((ConjuntoLocker) distancia.getValue());
            }

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void adicionarCarrinho(Pedidos p) {

        PedidoLocker pd = new PedidoLocker();
        pd.setPedidos(p.clonar());

        int posicaoLista = existeProduto(pd.getPedidos().getProduto().getId_produto());

        if (posicaoLista > -1) {
            getLstCarrinho().get(posicaoLista).getPedidos().somarQtdeProdutos(pd.getPedidos().getQtd());
        } else {
            getLstCarrinho().add(pd);
        }

        listarEstabelecimento();

        p.setQtd(0);
        FuraFilaUtils.growlInfo(FuraFilaConstants.INFO_GROWL_TITULO, FuraFilaConstants.INFO_PRODUTO_AD_CARRINHO);

    }

    public void deletarCarrinho(PedidoLocker p) {

        getLstCarrinho().remove(p);

        FuraFilaUtils.growlInfo(FuraFilaConstants.INFO_GROWL_TITULO, FuraFilaConstants.INFO_PRODUTO_DEL_CARRINHO);
    }

    public String getTotalFormatado() {

        Double total = 0.0;
        for (PedidoLocker pd : getLstCarrinho()) {
            total += pd.getPedidos().getSubTotal();
        }

        return FuraFilaUtils.formatarMoeda(total);

    }

    private int existeProduto(int codigo) {

        int posicaoLista = -1;

        for (int i = 0; i < getLstCarrinho().size(); i++) {
            if (getLstCarrinho().get(i).getPedidos().getProduto().getId_produto() == codigo) {
                posicaoLista = i;
            }
        }

        return posicaoLista;

    }

    public void pesquisarGeolocalizacao(ActionEvent ae) {

        try {
            CepService cep = new CepService();

            cep.pesquisarCep(getLogradouro());

            List<Double> lstGeo = cep.pegarGeolocalizacao(getLogradouro());

            LatLng center = new LatLng(lstGeo.get(0), lstGeo.get(1));
            setCenterGeoMap(center.getLat() + "," + center.getLng());
            getGeoModel().addOverlay(new Marker(center, "", null, FuraFilaConstants.IMAGEM_LOCKER));
            setLatitudeHidden(lstGeo.get(0));
            setLongitudeHidden(lstGeo.get(1));

            organizarLockers();

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    private void listarEstabelecimento() {

        for (int i = 0; i < getLstCarrinho().size(); i++) {

            int estabelecimentoExiste = 0;
            if (getLstEstabelecimentos().isEmpty()) {
                getLstEstabelecimentos().add(getLstCarrinho().get(i).getPedidos().getComanda().getEstabelecimento());
                return;
            }

            Estabelecimento e = getLstCarrinho().get(i).getPedidos().getComanda().getEstabelecimento();

            for (Estabelecimento est : getLstEstabelecimentos()) {
                if (e.getRazao_social().equalsIgnoreCase(est.getRazao_social())) {
                    estabelecimentoExiste++;
                }
            }

            if (estabelecimentoExiste == 0) {
                getLstEstabelecimentos().add(getLstCarrinho().get(i).getPedidos().getComanda().getEstabelecimento());
            }

        }

    }

    public void finalizar(ActionEvent ae){
        try {
            EnviarEmails.envarEmailEmAnalise(getPedidoLocker(),getTotalFormatado(),getLstCarrinho());
        } catch (Exception ex) {
            Logger.getLogger(ComprarProdutosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        avancar(null);
    }
    
    public void avancar(ActionEvent ae) {
        this.setTabIndex((Integer) (this.getTabIndex() + 1));
    }

    public void retroceder(ActionEvent ae) {
        if (getTabIndex() > 0) {
            this.setTabIndex((Integer) (this.getTabIndex() - 1));
        }
    }

    public void efetuarPagamento(ActionEvent ae) {

        String codigoCompra = "";
        try {
            Checkout pagamento = new Checkout();

            for (PedidoLocker p : getLstCarrinho()) {
                pagamento.addItem(
                        p.getPedidos().getProduto().getId_produto().toString(),
                        p.getPedidos().getProduto().getProduto_desc(),
                        p.getPedidos().getQtd(),
                        new BigDecimal(p.getPedidos().getProduto().getValor_unitario()).setScale(2, RoundingMode.UP),
                        0L,
                        new BigDecimal("0.00"));
                getPedidoLocker().getPedidos().getComanda().setId_comanda(p.getPedidos().getComanda().getId_comanda());
            }

            Cliente c = pegarDadosSessaoCliente();

            pagamento.setSender(
                    c.getNome(),
                    c.getEmail(),
                    c.getTel_res().toString().substring(0, 2),
                    c.getTel_res().toString().substring(2),
                    DocumentType.CPF,
                    c.getCpfFormatado(),
                    FuraFilaUtils.formataData(c.getDataNascimento(), FuraFilaConstants.PADRAO_DATA_EXIBICAO));

            pagamento.setCurrency(Currency.BRL);

            pagamento.setShipping(ShippingType.NOT_SPECIFIED,
                    "BRA",
                    c.getLogradouro().getBairro().getCidade().getUf().getSigla_uf(),
                    c.getLogradouro().getBairro().getCidade().getDesc_cidade(),
                    c.getLogradouro().getBairro().getDesc_bairro(),
                    c.getLogradouro().getNroCepFormatado().replace("-", ""),
                    c.getLogradouro().getLogradouroFormatado(),
                    c.getNroCasa().toString(),
                    c.getComplemento());

            boolean apenasCodigoPagamento = true;
            codigoCompra = pagamento.register(PagSeguroConfig.getAccountCredentials(), apenasCodigoPagamento);
            pagamento.getNotificationURL();
            getPedidoLocker().getPedidos().getComanda().setCodigoPedido(codigoCompra);

            getPedidoLocker().getPedidos().getComanda().getStatus().setId_status(FuraFilaConstants.COD_EM_ANALISE);
            getPedidoLocker().getPedidos().getComanda().setCliente(pegarDadosSessaoCliente().clonar());
            getPedidoLocker().getPedidos().getComanda().setId_comanda(FuraFilaUtils.gerarCodigoVenda().toString());

            FacesContext.getCurrentInstance().getExternalContext().redirect("https://sandbox.pagseguro.uol.com.br/v2/checkout/payment.html?code=" + codigoCompra);
            gravarPedidoComanda();

        } catch (PagSeguroServiceException | IOException ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    private void gravarPedidoComanda() {

        try {
            getComandaBusiness().gravarComanda(getPedidoLocker().getPedidos().getComanda());

            for (PedidoLocker pl : getLstCarrinho()) {
                pl.getPedidos().getComanda().setId_comanda(getPedidoLocker().getPedidos().getComanda().getId_comanda());
                getPedidoBusiness().gravarPedido(pl.getPedidos());
                getPedidoLockerBusiness().gravar(pl);
            }

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public String finalizarPedido() {

        for (PedidoLocker pd : getLstCarrinho()) {
            pd.getPedidos().getComanda().setCliente(pegarDadosSessaoCliente().clonar());
            pd.getPedidos().getComanda().getStatus().setId_status(FuraFilaConstants.COD_EM_ANALISE);
        }

        return Navegacao.irPaginaFinalizarCompra();
    }

    public void irResumoPedido(ActionEvent ae) {

        try {
            Integer volumeTotal = 0;

            for (PedidoLocker pl : getLstCarrinho()) {
                volumeTotal += pl.getPedidos().getProduto().getDimensao().calcularVolume();
            }

            List<Locker> lstLocker = getLockerService().buscarLockerPorVolume(volumeTotal,getConjuntoLocker());

            // TRAZER O MENOR VOLUME DOS QUE FORAM BUSCADOS DO BANCO DE DADOS
            Integer menorValor = Integer.MAX_VALUE;
            Locker locker = new Locker();
            for (Locker l : lstLocker) {
                if (l.getDimensao().calcularVolume() < menorValor) {
                    menorValor = l.getDimensao().calcularVolume();
                    locker = l;
                }
            }

            locker.getStatus().setId_status(FuraFilaConstants.COD_LOCKER_EM_USO);

            for (PedidoLocker pd : getLstCarrinho()) {
                pd.getLocker().setId_locker(locker.getId_locker());
                pd.getLocker().setLocker_desc(locker.getLocker_desc());
                pd.getLocker().setDimensao(locker.getDimensao());
                pd.getLocker().setConjuntoLocker(getConjuntoLocker());
                pd.getLocker().setStatus(locker.getStatus());
                getPedidoLocker().getPedidos().getComanda().setEstabelecimento(pd.getPedidos().getComanda().getEstabelecimento());
                getPedidoLocker().getLocker().setConjuntoLocker(getConjuntoLocker());
            }

            //ALTERAR STATUS DO LOCKER
            getLockerBusiness().alterarStatus(locker);

            avancar(null);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    private Cliente pegarDadosSessaoCliente() {
        return (Cliente) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_CLIENTE);
    }

    public String irPesquisa() {
        zerarBean();
        return Navegacao.irPaginaPesquisa();
    }

    public String irPaginaCliente() {
        zerarBean();
        return Navegacao.irPaginaPrincipalCliente();
    }

    private void zerarBean() {
        getLstCarrinho().clear();
        getLstConjuntoLockers().clear();
        this.setTabIndex(0);
        setPedidoLocker(new PedidoLocker());
        setConjuntoLocker(new ConjuntoLocker());
    }

    public void limparFiltros(ActionEvent ae) {
        setComprarProduto(new ComprarProduto());
        popularProdutosComprar();
    }

    public Boolean verificarEstabelecimentoDuplicado(Pedidos p){
        Boolean desativar = false;
        for(PedidoLocker pl : getLstCarrinho()){
            if(!p.getComanda().getEstabelecimento().getRazao_social().equalsIgnoreCase(pl.getPedidos().getComanda().getEstabelecimento().getRazao_social())){
                desativar = true;
                break;
            }
        }
        return desativar;
    }
    
    public ProdutoService getProdutoService() {
        return produtoService;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public ComprarProduto getComprarProduto() {
        return comprarProduto;
    }

    public void setComprarProduto(ComprarProduto comprarProduto) {
        this.comprarProduto = comprarProduto;
    }

    public List<Pedidos> getLstProdutoComprar() {
        return lstProdutoComprar;
    }

    public void setLstProdutoComprar(List<Pedidos> lstProdutoComprar) {
        this.lstProdutoComprar = lstProdutoComprar;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Produto getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    public List<PedidoLocker> getLstCarrinho() {
        return lstCarrinho;
    }

    public void setLstCarrinho(List<PedidoLocker> lstCarrinho) {
        this.lstCarrinho = lstCarrinho;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Double getLatitudeHidden() {
        return latitudeHidden;
    }

    public void setLatitudeHidden(Double latitudeHidden) {
        this.latitudeHidden = latitudeHidden;
    }

    public Double getLongitudeHidden() {
        return longitudeHidden;
    }

    public void setLongitudeHidden(Double longitudeHidden) {
        this.longitudeHidden = longitudeHidden;
    }

    public List<ConjuntoLocker> getLstConjuntoLockers() {
        return lstConjuntoLockers;
    }

    public void setLstConjuntoLockers(List<ConjuntoLocker> lstConjuntoLockers) {
        this.lstConjuntoLockers = lstConjuntoLockers;
    }

    public List<Estabelecimento> getLstEstabelecimentos() {
        return lstEstabelecimentos;
    }

    public void setLstEstabelecimentos(List<Estabelecimento> lstEstabelecimentos) {
        this.lstEstabelecimentos = lstEstabelecimentos;
    }

    public ConjuntoLockerService getConjuntoLockerService() {
        return conjuntoLockerService;
    }

    public void setConjuntoLockerService(ConjuntoLockerService conjuntoLockerService) {
        this.conjuntoLockerService = conjuntoLockerService;
    }

    public Integer getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(Integer tabIndex) {
        this.tabIndex = tabIndex;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public LockerService getLockerService() {
        return lockerService;
    }

    public void setLockerService(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    public PedidoLocker getPedidoLocker() {
        return pedidoLocker;
    }

    public void setPedidoLocker(PedidoLocker pedidoLocker) {
        this.pedidoLocker = pedidoLocker;
    }

    public ConjuntoLocker getConjuntoLocker() {
        return conjuntoLocker;
    }

    public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
        if (conjuntoLocker != null) {
            this.conjuntoLocker = conjuntoLocker;
        }
    }

    public ComandaBusiness getComandaBusiness() {
        return comandaBusiness;
    }

    public void setComandaBusiness(ComandaBusiness comandaBusiness) {
        this.comandaBusiness = comandaBusiness;
    }

    public PedidoBusiness getPedidoBusiness() {
        return pedidoBusiness;
    }

    public void setPedidoBusiness(PedidoBusiness pedidoBusiness) {
        this.pedidoBusiness = pedidoBusiness;
    }

    public PedidoLockerBusiness getPedidoLockerBusiness() {
        return pedidoLockerBusiness;
    }

    public void setPedidoLockerBusiness(PedidoLockerBusiness pedidoLockerBusiness) {
        this.pedidoLockerBusiness = pedidoLockerBusiness;
    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

    public List<SelectItem> getLstOrdens() {
        return lstOrdens;
    }

    public void setLstOrdens(List<SelectItem> lstOrdens) {
        this.lstOrdens = lstOrdens;
    }

    public EstabelecimentoService getEstabelecimentoService() {
        return estabelecimentoService;
    }

    public void setEstabelecimentoService(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    public List<Estabelecimento> getLstEstabelecimentosFiltro() {
        return lstEstabelecimentosFiltro;
    }

    public void setLstEstabelecimentosFiltro(List<Estabelecimento> lstEstabelecimentosFiltro) {
        this.lstEstabelecimentosFiltro = lstEstabelecimentosFiltro;
    }

}
