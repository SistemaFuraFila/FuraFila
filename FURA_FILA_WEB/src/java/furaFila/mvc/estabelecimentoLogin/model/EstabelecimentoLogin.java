package furaFila.mvc.estabelecimentoLogin.model;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.login.model.Login;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class EstabelecimentoLogin {
    
    private Integer id_estabelecimento_login;
    private Estabelecimento estabelecimento = new Estabelecimento();
    private Login login = new Login();

    public Integer getId_estabelecimento_login() {
        return id_estabelecimento_login;
    }

    public void setId_estabelecimento_login(Integer id_estabelecimento_login) {
        this.id_estabelecimento_login = id_estabelecimento_login;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public EstabelecimentoLogin clonar() {
        
        EstabelecimentoLogin el = new EstabelecimentoLogin();
        
        el.setId_estabelecimento_login(getId_estabelecimento_login());
        el.setEstabelecimento(getEstabelecimento());
        el.setLogin(getLogin());
        
        return el;
        
    }
    
}
