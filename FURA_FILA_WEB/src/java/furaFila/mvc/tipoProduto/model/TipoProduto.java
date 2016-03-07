package furaFila.mvc.tipoProduto.model;

import furaFila.utils.FuraFilaConstants;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class TipoProduto {

    private Integer id_tipo_produto = 0;
    private String tipo_produto_desc = "";
    private Boolean status;

    public Integer getId_tipo_produto() {
        return id_tipo_produto;
    }

    public void setId_tipo_produto(Integer id_tipo_produto) {
        this.id_tipo_produto = id_tipo_produto;
    }

    public String getTipo_produto_desc() {
        return tipo_produto_desc;
    }

    public void setTipo_produto_desc(String tipo_produto_desc) {
        this.tipo_produto_desc = tipo_produto_desc;
    }
    
    public Character getStatusSQL() {
        return status ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO;
    }
    
    public String getStatusExibicao() {
        return status ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public TipoProduto clonar() {
        
        TipoProduto tp = new TipoProduto();

        tp.setId_tipo_produto(getId_tipo_produto());
        tp.setTipo_produto_desc(getTipo_produto_desc());
        tp.setStatus(getStatus());

        return tp;
    }

}
