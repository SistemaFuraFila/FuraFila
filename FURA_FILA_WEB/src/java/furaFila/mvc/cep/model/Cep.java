package furaFila.mvc.cep.model;

import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Cep {

    private Integer nroCep = 0;
    private String tipoLogradouro = "";
    private String logradouro = "";
    private String bairro = "";
    private String cidade = "";
    private String estado = "";

    public Integer getNroCep() {
        return nroCep;
    }

    public void setNroCep(Integer nroCep) {
        this.nroCep = nroCep;
    }

    public String getNroCepFormatado() {

        String nroCepFormatado = "";
        try {
            if (nroCep.toString().length() < 8) {
                nroCepFormatado = "0" + nroCep;
            }
            nroCepFormatado = FuraFilaUtils.formataCep(nroCepFormatado);
        } catch (Exception e) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
        }

        return nroCepFormatado;
    }

    public void setNroCepFormatado(String nroCep) {
        this.nroCep = Integer.parseInt(nroCep.replace("-", ""));
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {

        return tipoLogradouro + " " + logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cep clonar() {
        Cep cep = new Cep();

        cep.setNroCep(getNroCep());
        cep.setTipoLogradouro(getTipoLogradouro());
        cep.setLogradouro(getLogradouro());
        cep.setBairro(getBairro());
        cep.setCidade(getCidade());
        cep.setEstado(getEstado());

        return cep;

    }

}
