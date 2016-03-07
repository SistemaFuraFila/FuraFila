package furaFila.mvc.cidade.model;

import furaFila.mvc.uf.model.UF;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Cidade {

    private Integer id_cidade = 0;
    private String desc_cidade = "";
    private UF uf = new UF();

    public Integer getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(Integer id_cidade) {
        this.id_cidade = id_cidade;
    }

    public String getDesc_cidade() {
        return desc_cidade;
    }

    public void setDesc_cidade(String desc_cidade) {
        this.desc_cidade = desc_cidade;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public Cidade clonar() {

        Cidade cidade = new Cidade();
        cidade.setId_cidade(getId_cidade());
        cidade.setDesc_cidade(getDesc_cidade());
        cidade.setUf(getUf());

        return cidade;

    }

    public boolean objetoVazio() {
        return 0 == getId_cidade() && "".equals(getDesc_cidade()) && getUf().objetoVazio();
    }

    public void zerarObjeto() {
        setId_cidade(0);
        setDesc_cidade("");
        getUf().zerarObjeto();
    }

}
