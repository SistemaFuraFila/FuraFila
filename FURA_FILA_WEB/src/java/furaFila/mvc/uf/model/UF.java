package furaFila.mvc.uf.model;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class UF {

    private Integer id_uf = 0;
    private String sigla_uf = "";
    private String desc_uf = "";

    public Integer getId_uf() {
        return id_uf;
    }

    public void setId_uf(Integer id_uf) {
        this.id_uf = id_uf;
    }

    public String getSigla_uf() {
        return sigla_uf;
    }

    public void setSigla_uf(String sigla_uf) {
        this.sigla_uf = sigla_uf;
    }

    public String getDesc_uf() {
        return desc_uf;
    }

    public void setDesc_uf(String desc_uf) {
        this.desc_uf = desc_uf;
    }

    public UF clonar() {

        UF uf = new UF();

        uf.setId_uf(getId_uf());
        uf.setSigla_uf(getSigla_uf());
        uf.setDesc_uf(getDesc_uf());

        return uf;

    }

    public boolean objetoVazio() {
        return 0 == getId_uf() && "".equals(getDesc_uf()) && "".equals(getSigla_uf());
    }

    public void zerarObjeto() {
        setId_uf(0);
        setSigla_uf("");
        setDesc_uf("");
    }

}
