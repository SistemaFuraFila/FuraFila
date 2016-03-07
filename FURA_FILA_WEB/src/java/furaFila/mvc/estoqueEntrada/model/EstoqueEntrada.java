
package furaFila.mvc.estoqueEntrada.model;

import furaFila.mvc.motivoEntrada.model.MotivoEntrada;
import furaFila.mvc.produto.model.Produto;
import java.util.Date;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class EstoqueEntrada {
    private Integer id_estoque_entrada = 0;
    private Integer qtdEntrada = 0;
    private Date dataEntrada;
    private Produto produto = new Produto();
    private MotivoEntrada motivoEntrada = new MotivoEntrada();

    public Integer getId_estoque_entrada() {
        return id_estoque_entrada;
    }

    public void setId_estoque_entrada(Integer id_estoque_entrada) {
        this.id_estoque_entrada = id_estoque_entrada;
    }

    public Integer getQtdEntrada() {
        return qtdEntrada;
    }

    public void setQtdEntrada(Integer qtdEntrada) {
        this.qtdEntrada = qtdEntrada;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public MotivoEntrada getMotivoEntrada() {
        return motivoEntrada;
    }

    public void setMotivoEntrada(MotivoEntrada motivoEntrada) {
        this.motivoEntrada = motivoEntrada;
    }
    public EstoqueEntrada clonar(){
        
        EstoqueEntrada ee = new EstoqueEntrada();
        
        ee.setDataEntrada(getDataEntrada());
        ee.setId_estoque_entrada(getId_estoque_entrada());
        ee.setMotivoEntrada(getMotivoEntrada());
        ee.setProduto(ee.getProduto());
        ee.setQtdEntrada(ee.getQtdEntrada());
        
        return ee;
    }
    
}
