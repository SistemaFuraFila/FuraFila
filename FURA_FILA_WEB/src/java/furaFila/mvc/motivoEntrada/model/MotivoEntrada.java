
package furaFila.mvc.motivoEntrada.model;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class MotivoEntrada {
    private Integer id_motivo_entrada = 0;
    private String motivoEntrada ="";

    public Integer getId_motivo_entrada() {
        return id_motivo_entrada;
    }

    public void setId_motivo_entrada(Integer id_motivo_entrada) {
        this.id_motivo_entrada = id_motivo_entrada;
    }

    public String getMotivoEntrada() {
        return motivoEntrada;
    }

    public void setMotivoEntrada(String motivoEntrada) {
        this.motivoEntrada = motivoEntrada;
    }
    
    public MotivoEntrada Clonar(){
        MotivoEntrada me = new MotivoEntrada();
        
        me.setId_motivo_entrada(getId_motivo_entrada());
        me.setMotivoEntrada(me.getMotivoEntrada());
    
        return me;
    }
}
