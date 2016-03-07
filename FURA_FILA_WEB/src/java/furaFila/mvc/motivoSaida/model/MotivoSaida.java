
package furaFila.mvc.motivoSaida.model;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class MotivoSaida {
    private Integer id_motivo = 0;
    private String motivoSaida = "";

    public Integer getId_motivo() {
        return id_motivo;
    }

    public void setId_motivo(Integer id_motivo) {
        this.id_motivo = id_motivo;
    }

    public String getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(String motivoSaida) {
        this.motivoSaida = motivoSaida;
    }
    
    public MotivoSaida Clonar(){
        MotivoSaida ms = new MotivoSaida();
        
        ms.setId_motivo(getId_motivo());
        ms.setMotivoSaida(getMotivoSaida());
        
    
        return ms;
    }

}
