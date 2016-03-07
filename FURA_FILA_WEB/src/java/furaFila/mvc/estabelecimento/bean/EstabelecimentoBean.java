package furaFila.mvc.estabelecimento.bean;

import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.estabelecimento.business.EstabelecimentoBusiness;
import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estabelecimento.service.EstabelecimentoService;
import furaFila.mvc.estabelecimentoLogin.business.EstabelecimentoLoginBusiness;
import furaFila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import furaFila.mvc.estoque.business.EstoqueBusiness;
import furaFila.mvc.estoque.model.Estoque;
import furaFila.mvc.estoque.service.EstoqueService;
import furaFila.mvc.estoqueProdutos.model.EstoqueProdutos;
import furaFila.mvc.imagem.business.ImagemBusiness;
import furaFila.mvc.locker.bean.LockerBean;
import furaFila.mvc.login.business.LoginBusiness;
import furaFila.utils.EnviarEmails;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import furaFila.utils.Navegacao;
import furaFila.validadores.FuraFilaValidadores;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class EstabelecimentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();

    private Estabelecimento estabelecimento = new Estabelecimento();
    private EstabelecimentoLogin estabelecimentoLogin = new EstabelecimentoLogin();
    private Estoque estoque = new Estoque();

    private EstabelecimentoBusiness estabelecimentoBusiness = new EstabelecimentoBusiness();
    private LoginBusiness loginBusiness = new LoginBusiness();
    private EstabelecimentoLoginBusiness estabelecimentoLoginBusiness = new EstabelecimentoLoginBusiness();
    private ImagemBusiness imagemBusiness = new ImagemBusiness();
    private EstoqueBusiness estoqueBusiness = new EstoqueBusiness();

    private EstabelecimentoService estabelecimentoService = new EstabelecimentoService();
    private EstoqueService estoqueService = new EstoqueService();

    public void listarEstabelecimentos() {
        try {
            setEstabelecimento(new Estabelecimento());
            setLstEstabelecimentos(getEstabelecimentoService().listarEstabelecimentos());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void gerarRelatorio(ActionEvent ae){

        try {
            FuraFilaUtils.gerarRelatorios("estabelecimentoVendas", getEstabelecimentoService().listarEstabelecimentoMaisVendem(), "ESTABELECIMENTOS_VENDAS");
        } catch (JRException ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        } catch (IOException ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }
    
    public String gravar() {

        try {
            
//            if(!FuraFilaValidadores.validarEstabelecimento(getEstabelecimento())){
//                return "";
//            }
//            if(!FuraFilaValidadores.validarLogin(getEstabelecimentoLogin().getLogin())){
//                return "";
//            }
            
//            getImagemBusiness().gravar(getEstabelecimento().getImagem());

            getEstabelecimento().setStatus(Boolean.FALSE);
            getEstabelecimentoBusiness().gravar(getEstabelecimento());

            getEstabelecimentoLogin().getLogin().getPermissao().setId_permissao(FuraFilaConstants.CODIGO_PERFIL_2);
            getEstabelecimentoLogin().getLogin().setStatus(Boolean.TRUE);
            getEstabelecimentoLogin().getLogin().setDisponivel_entrega(Boolean.FALSE);
            getLoginBusiness().gravar(getEstabelecimentoLogin().getLogin());

            getEstabelecimentoLogin().getEstabelecimento().setId_estabelecimento(getEstabelecimento().getId_estabelecimento());
            getEstabelecimentoLoginBusiness().gravar(getEstabelecimentoLogin());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        zerarLogin();
        zerarEstabelecimento();
        return Navegacao.irIndex();

    }

    public void alterar(ActionEvent ae) {

        try {

            getImagemBusiness().alterar(getEstabelecimento().getImagem());

            getEstabelecimentoBusiness().alterar(getEstabelecimento());
            
            getEstabelecimentoLoginSessao().getEstabelecimento().setImagem(getEstabelecimento().getImagem());
            getEstabelecimentoLoginSessao().getEstabelecimento().setRazao_social(getEstabelecimento().getRazao_social());
            
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public String ativarEmpresa() {

        try {
            alterarStatus(Boolean.TRUE);

            getEstoque().setEstabelecimento(getEstabelecimento());

            if (!getEstoqueService().estoqueExiste(getEstoque())) {
                getEstoque().setEstabelecimento(getEstabelecimento());
                getEstoqueBusiness().gravar(getEstoque());
            }

            EnviarEmails.enviarEmailBoasVindas(getEstabelecimento());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return Navegacao.irEstabelecimentosCadastrados();

    }

    public String desativarEmpresa() {

        try {
            alterarStatus(Boolean.FALSE);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return Navegacao.irEstabelecimentosCadastrados();

    }

    public void alterarStatus(Boolean status) throws Exception {

        getEstabelecimento().setStatus(status);

        getEstabelecimentoBusiness().alterarStatus(getEstabelecimento());

    }

    public void iniciarTabelaEstabelecimentos(ActionEvent ae) {

        try {
            setLstEstabelecimentos(getEstabelecimentoService().listarEstabelecimentos());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void listarInfoEstabelecimento() {

        try {
            setEstabelecimento(pegarEstabelecimentoSessao());

            getEstabelecimentoService().buscarInformacaoEstabelecimento(getEstabelecimento());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public void carregar(FileUploadEvent event) {

        try {

            EstoqueProdutos ep = new EstoqueProdutos();
            ep.getEstoque().setEstabelecimento(getEstabelecimento());

            String ext[] = event.getFile().getFileName().split("\\.");

            String caminho = FuraFilaUtils.montarCaminho(null, getEstabelecimentoLoginSessao(), false);
            String nomeImagem = FuraFilaUtils.montarNomeImagem(null, ep, false) + "." + ext[ext.length - 1];

            getEstabelecimento().getImagem().setImagem(FuraFilaUtils.copiarArquivo(caminho + nomeImagem, event.getFile().getInputstream()));

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    private Estabelecimento pegarEstabelecimentoSessao() {
        return (Estabelecimento) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO);
    }

    public EstabelecimentoLogin getEstabelecimentoLoginSessao() {
        return (EstabelecimentoLogin) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO_LOGIN);
    }

    public void zerarEstabelecimento() {
        getEstabelecimento().zerarObjeto();
    }
    
    public void zerarLogin() {
        getEstabelecimentoLogin().getLogin().setUsuario("");
    }

    public EstabelecimentoBusiness getEstabelecimentoBusiness() {
        return estabelecimentoBusiness;
    }

    public void setEstabelecimentoBusiness(EstabelecimentoBusiness estabelecimentoBusiness) {
        this.estabelecimentoBusiness = estabelecimentoBusiness;
    }

    public LoginBusiness getLoginBusiness() {
        return loginBusiness;
    }

    public void setLoginBusiness(LoginBusiness loginBusiness) {
        this.loginBusiness = loginBusiness;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        if (estabelecimento != null && !estabelecimento.objetoVazio()) {
            this.estabelecimento = estabelecimento;
        }
    }

    public List<Estabelecimento> getLstEstabelecimentos() {
        return lstEstabelecimentos;
    }

    public void setLstEstabelecimentos(List<Estabelecimento> lstEstabelecimentos) {
        this.lstEstabelecimentos = lstEstabelecimentos;
    }

    public EstabelecimentoService getEstabelecimentoService() {
        return estabelecimentoService;
    }

    public void setEstabelecimentoService(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    public EstabelecimentoLogin getEstabelecimentoLogin() {
        return estabelecimentoLogin;
    }

    public void setEstabelecimentoLogin(EstabelecimentoLogin estabelecimentoLogin) {
        this.estabelecimentoLogin = estabelecimentoLogin;
    }

    public EstabelecimentoLoginBusiness getEstabelecimentoLoginBusiness() {
        return estabelecimentoLoginBusiness;
    }

    public void setEstabelecimentoLoginBusiness(EstabelecimentoLoginBusiness estabelecimentoLoginBusiness) {
        this.estabelecimentoLoginBusiness = estabelecimentoLoginBusiness;
    }

    public ImagemBusiness getImagemBusiness() {
        return imagemBusiness;
    }

    public void setImagemBusiness(ImagemBusiness imagemBusiness) {
        this.imagemBusiness = imagemBusiness;
    }

    public EstoqueBusiness getEstoqueBusiness() {
        return estoqueBusiness;
    }

    public void setEstoqueBusiness(EstoqueBusiness estoqueBusiness) {
        this.estoqueBusiness = estoqueBusiness;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public EstoqueService getEstoqueService() {
        return estoqueService;
    }

    public void setEstoqueService(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

}
