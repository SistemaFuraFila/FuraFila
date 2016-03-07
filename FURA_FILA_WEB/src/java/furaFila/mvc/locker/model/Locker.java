package furaFila.mvc.locker.model;

import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.dimensao.model.Dimensao;
import furaFila.mvc.status.model.Status;
import furaFila.utils.FuraFilaConstants;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Locker {

    private Integer id_locker;
    private String locker_desc = "";
    private Boolean bolStatus;
    private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();
    private Dimensao dimensao = new Dimensao();
    private Status status = new Status();

    public Integer getId_locker() {
        return id_locker;
    }

    public void setId_locker(Integer id_locker) {
        this.id_locker = id_locker;
    }

    public String getLocker_desc() {
        return locker_desc;
    }

    public void setLocker_desc(String locker_desc) {
        if (!"".equals(locker_desc)) {
            this.locker_desc = locker_desc;
        }
    }

    public ConjuntoLocker getConjuntoLocker() {
        return conjuntoLocker;
    }

    public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
        this.conjuntoLocker = conjuntoLocker;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getBolStatusExibicao(){
        return bolStatus ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }
    
    public Character getBolStatusSQL(){
        return bolStatus ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public Boolean getBolStatus() {
        return bolStatus;
    }

    public void setBolStatus(Boolean bolStatus) {
        this.bolStatus = bolStatus;
    }
    
    public Locker clonar() {

        Locker locker = new Locker();
        locker.setId_locker(getId_locker());
        locker.setLocker_desc(getLocker_desc());
        locker.setDimensao(getDimensao());
        locker.setBolStatus(getBolStatus());

        return locker;
    }


}
