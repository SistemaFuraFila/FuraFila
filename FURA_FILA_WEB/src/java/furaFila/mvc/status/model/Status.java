package furaFila.mvc.status.model;

import furaFila.utils.FuraFilaConstants;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Status {
    private Integer id_status= 0;
    private String status = "";

    public Integer getId_status() {
        return id_status;
    }

    public void setId_status(Integer id_status) {
        this.id_status = id_status;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusFormatado(){
        
        switch(getId_status()){
            
            case FuraFilaConstants.COD_LOCKER_EM_USO        : return FuraFilaConstants.LOCKER_EM_USO;
            case FuraFilaConstants.COD_LOCKER_LIVRE         : return FuraFilaConstants.LOCKER_LIVRE;
            case FuraFilaConstants.COD_EM_ANALISE           : return FuraFilaConstants.EM_ANALISE;
            case FuraFilaConstants.COD_EM_SEPARACAO         : return FuraFilaConstants.EM_SEPARACAO;
            case FuraFilaConstants.COD_ENCAMINHADO_LOCKER   : return FuraFilaConstants.ENCAMINHADO_LOCKER;
            case FuraFilaConstants.COD_APROVADO             : return FuraFilaConstants.APROVADO;
            case FuraFilaConstants.COD_REPROVADO            : return FuraFilaConstants.REPROVADO;
            case FuraFilaConstants.COD_PRODUTO_ENTREGUE     : return FuraFilaConstants.PRODUTO_ENTREGUE;
                default: return "";
            
        }
        
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Status clonar(){
        Status s = new Status();
        s.setId_status(getId_status());
        s.setStatus(getStatus());
        return s;
    }
    
}