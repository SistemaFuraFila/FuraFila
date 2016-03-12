package furaFila.mvc.login.bean;

import furaFila.mvc.login.business.LoginBusiness;
import furaFila.mvc.login.model.Login;
import furaFila.mvc.login.service.ILoginService;
import furaFila.mvc.login.service.LoginService;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import furaFila.utils.Navegacao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class EntregadoresBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Login> lstEntregadores = null;

    private ILoginService iLoginService;

    private LoginBusiness loginBusiness  = null;

    private Login login  = null;

    private Boolean flgBtnExcluir = null;
    
    private Boolean isAlteracao = null;

    public String inicializarEntregadores(){
        try {
            this.loginBusiness = new LoginBusiness();
            this.login = new Login();
            this.iLoginService = new LoginService();
            this.lstEntregadores = this.iLoginService.listarEntregador();
            flgBtnExcluir = true;
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        
        return Navegacao.irPaginaEntregador();
        
    }
    
    public String inicializarNovoEntregador(){
        login = new Login();
        isAlteracao = false;
        return Navegacao.irPaginaNovoEntregador();
    }
    
    public String inicializarEditarEntregador(){
        isAlteracao = true;
        return Navegacao.irPaginaNovoEntregador();
    }
    
    public void salvar(ActionEvent ae){
        
        try {
            if (isAlteracao) {
                alterar();
            } else {
                gravar();
            }
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        
    }
    
    public void gravar() throws Exception {

        if (usuarioDuplicado()) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.AVISO_ENTREGADOR_EXISTE);
            return;
        }

        getLogin().setStatus(Boolean.TRUE);
        getLogin().setDisponivel_entrega(Boolean.TRUE);
        getLogin().getPermissao().setId_permissao(FuraFilaConstants.CODIGO_PERFIL_4);
        getLoginBusiness().gravar(getLogin());

        FuraFilaUtils.growlInfo(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.INFO_ENTREGADOR_CADASTRADO);

    }

    public void alterar() throws Exception {

        if (usuarioDuplicado()) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.AVISO_ENTREGADOR_EXISTE);
            return;
        }

        getLoginBusiness().alterar(getLogin());
        
        FuraFilaUtils.growlInfo(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.INFO_ENTREGADOR_ALTERADO);
    }

    public void alterarStatus(ActionEvent ae) {

        try {

            getLogin().setStatus(!getLogin().getStatus());
            getLoginBusiness().alterarStatus(getLogin());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void alterarDisponibilidade(ActionEvent ae) {

        try {

            getLogin().setDisponivel_entrega(!getLogin().getDisponivel_entrega());
            getLoginBusiness().alterarDisponibilidade(getLogin());

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void habilitarBotaoExcluir(SelectEvent event) {
        setFlgBtnExcluir(false);
    }

    public void desabilitarBotaoExcluir(UnselectEvent event) {
        setFlgBtnExcluir(true);
    }

    public String mudarNomeBotao() {
        if (getLogin().getStatus() != null) {
            return getLogin().getStatus() ? FuraFilaConstants.DESATIVAR : FuraFilaConstants.ATIVAR;
        } else {
            return "";
        }
    }

    public String mudarNomeBotaoDisponivel() {
        if (getLogin().getDisponivel_entrega() != null) {
            return getLogin().getDisponivel_entrega() ? "Indisponibilizar" : "Disponibilizar";
        } else {
            return "";
        }
    }
    
    private Boolean usuarioDuplicado() throws Exception{
        return getiLoginService().verificarDuplicidade(login, isAlteracao) > 0;
    }
    
    public List<Login> getLstEntregadores() {
        return lstEntregadores;
    }

    public void setLstEntregadores(List<Login> lstEntregadores) {
        this.lstEntregadores = lstEntregadores;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        if (login != null) {
            this.login = login;
        }
    }

    public LoginBusiness getLoginBusiness() {
        return loginBusiness;
    }

    public void setLoginBusiness(LoginBusiness loginBusiness) {
        this.loginBusiness = loginBusiness;
    }

    public Boolean getFlgBtnExcluir() {
        return flgBtnExcluir;
    }

    public void setFlgBtnExcluir(Boolean flgBtnExcluir) {
        this.flgBtnExcluir = flgBtnExcluir;
    }

    public ILoginService getiLoginService() {
        return iLoginService;
    }

    public void setiLoginService(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    public Boolean getIsAlteracao() {
        return isAlteracao;
    }

    public void setIsAlteracao(Boolean isAlteracao) {
        this.isAlteracao = isAlteracao;
    }

}
