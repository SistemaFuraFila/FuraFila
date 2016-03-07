
package furaFila.mvc.estoqueSaida.model;

import furaFila.mvc.motivoSaida.model.MotivoSaida;
import furaFila.mvc.produto.model.Produto;
import java.util.Date;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class EstoqueSaida {  
    private Integer id_estoque_saida = 0;
    private Integer qtdSaida =0;
    private Date dataSaida;
    private MotivoSaida motivoSaida = new MotivoSaida();
    private Produto produto = new Produto();

    public Integer getId_estoque_saida() {
        return id_estoque_saida;
    }

    public void setId_estoque_saida(Integer id_estoque_saida) {
        this.id_estoque_saida = id_estoque_saida;
    }

    public Integer getQtdSaida() {
        return qtdSaida;
    }

    public void setQtdSaida(Integer qtdSaida) {
        this.qtdSaida = qtdSaida;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public MotivoSaida getMotivoSaida() {
        return motivoSaida;
    }

    public void setMotivoSaida(MotivoSaida motivoSaida) {
        this.motivoSaida = motivoSaida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public EstoqueSaida Clonar(){
        EstoqueSaida estoqueS= new EstoqueSaida();
        
        estoqueS.setId_estoque_saida(getId_estoque_saida());
        estoqueS.setMotivoSaida(getMotivoSaida());
        estoqueS.setProduto(getProduto());
        estoqueS.setDataSaida(getDataSaida());
        estoqueS.setQtdSaida(getQtdSaida());
            
    return estoqueS;
    }

}
