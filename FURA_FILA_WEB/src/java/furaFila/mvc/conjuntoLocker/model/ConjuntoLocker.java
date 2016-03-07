package furaFila.mvc.conjuntoLocker.model;

import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.utils.FuraFilaConstants;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConjuntoLocker {

    private Integer id_conjunto_locker;
    private String conjunto_locker_desc;
    private Integer nro_localizacao;
    private Boolean status;
    private Logradouro logradouro = new Logradouro();
    private Integer qtdLockers = 0;

    public Integer getId_conjunto_locker() {
        return id_conjunto_locker;
    }

    public void setId_conjunto_locker(Integer id_conjunto_locker) {
        this.id_conjunto_locker = id_conjunto_locker;
    }

    public Integer getNro_localizacao() {
        return nro_localizacao;
    }

    public void setNro_localizacao(Integer nro_localizacao) {
        this.nro_localizacao = nro_localizacao;
    }

    public Character getStatusSQL() {
        return getStatus() ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }

    public String getStatusExibicao() {
        return getStatus() ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public String getConjunto_locker_desc() {
        return conjunto_locker_desc;
    }

    public void setConjunto_locker_desc(String conjunto_locker_desc) {
        this.conjunto_locker_desc = conjunto_locker_desc;
    }

    public Integer getQtdLockers() {
        return qtdLockers;
    }

    public void setQtdLockers(Integer qtdLockers) {
        this.qtdLockers = qtdLockers;
    }

}
