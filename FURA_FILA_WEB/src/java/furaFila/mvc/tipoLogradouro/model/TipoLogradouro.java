package furaFila.mvc.tipoLogradouro.model;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TipoLogradouro {
    
    private Integer id_tipo_logradouro = 0;
    private String desc_tipo_logradouro = "";

    public Integer getId_tipo_logradouro() {
        return id_tipo_logradouro;
    }

    public void setId_tipo_logradouro(Integer id_tipo_logradouro) {
        this.id_tipo_logradouro = id_tipo_logradouro;
    }

    public String getDesc_tipo_logradouro() {
        return desc_tipo_logradouro;
    }

    public void setDesc_tipo_logradouro(String desc_tipo_logradouro) {
        this.desc_tipo_logradouro = desc_tipo_logradouro;
    }
    
    public boolean objetoVazio(){
        return 0 == getId_tipo_logradouro() && "".equals(getDesc_tipo_logradouro());
    }
    
    public void zerarObjeto(){
        setId_tipo_logradouro(0);
        setDesc_tipo_logradouro("");
    }
    
}
