package furaFila.mvc.login.model;

import furaFila.mvc.permissao.model.Permissao;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Login {

    private Integer id_login = 0;
    private String usuario = "";
    private String senha = "";
    private Boolean status;
    private Boolean disponivel_entrega;
    private Permissao permissao = new Permissao();

    public Integer getId_login() {
        return id_login;
    }

    public void setId_login(Integer id_login) {
        this.id_login = id_login;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenhaCriptografada() throws Exception {
        return FuraFilaUtils.criptografarTexto(getSenha());
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public String getStatusExibicao() {
        return status ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }
    
    public Character getStatusSQL() {
        return status ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDisponivel_entregaExibicao() {
        return disponivel_entrega ? FuraFilaConstants.DISPONIVEL : FuraFilaConstants.INDISPONIVEL;
    }

    public Character getDisponivel_entregaSQL() {
        return disponivel_entrega ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public Boolean getDisponivel_entrega() {
        return disponivel_entrega;
    }

    public void setDisponivel_entrega(Boolean disponivel_entrega) {
        this.disponivel_entrega = disponivel_entrega;
    }

    public Login clonar() throws Exception {

        Login login = new Login();

        login.setId_login(getId_login());
        login.setUsuario(getUsuario());
        login.setSenha(getSenha());
        login.setPermissao(getPermissao());
        login.setDisponivel_entrega(getDisponivel_entrega());
        login.setStatus(getStatus());

        return login;
    }

    public void zerarObjeto(){
        this.setId_login((Integer) 0);
        this.setUsuario("");
        this.setSenha("");
        this.setDisponivel_entrega(Boolean.FALSE);
        this.setStatus(Boolean.FALSE);
        getPermissao().zerarObjeto();
    }

}
