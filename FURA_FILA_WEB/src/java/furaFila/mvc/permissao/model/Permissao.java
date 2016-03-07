package furaFila.mvc.permissao.model;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class Permissao {
    
    private Integer id_permissao = 0;
    private String permissao = ""; 
    private String sigla_permissao = "";

    public Integer getId_permissao() {
        return id_permissao;
    }

    public void setId_permissao(Integer id_permissao) {
        this.id_permissao = id_permissao;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getSigla_permissao() {
        return sigla_permissao;
    }

    public void setSigla_permissao(String sigla_permissao) {
        this.sigla_permissao = sigla_permissao;
    }

    public Permissao Clonar(){
    
        Permissao permissao = new Permissao();
    
        permissao.setId_permissao(getId_permissao());
        permissao.setPermissao(getPermissao());
        permissao.setSigla_permissao(getSigla_permissao());
            
    return permissao;
    }

    public void zerarObjeto(){
        setId_permissao(0);
        setPermissao("");
        setSigla_permissao("");
    }
    
}
