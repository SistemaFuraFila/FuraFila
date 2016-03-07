package furaFila.mvc.bairro.model;

import furaFila.mvc.cidade.model.Cidade;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Bairro {

    private Integer id_bairro = 0;
    private String desc_bairro = "";
    private Cidade cidade = new Cidade();

    public Integer getId_bairro() {
        return id_bairro;
    }

    public void setId_bairro(Integer id_bairro) {
        this.id_bairro = id_bairro;
    }

    public String getDesc_bairro() {
        return desc_bairro;
    }

    public void setDesc_bairro(String desc_bairro) {
        this.desc_bairro = desc_bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Bairro clonar() {

        Bairro bairro = new Bairro();
        bairro.setId_bairro(getId_bairro());
        bairro.setDesc_bairro(getDesc_bairro());
        bairro.setCidade(getCidade());

        return bairro;

    }

    public boolean objetoVazio() {
        return 0 == getId_bairro() && "".equals(getDesc_bairro()) && getCidade().objetoVazio();
    }

    public void zerarObjeto() {
        setId_bairro(0);
        setDesc_bairro("");
        getCidade().zerarObjeto();
    }

}
