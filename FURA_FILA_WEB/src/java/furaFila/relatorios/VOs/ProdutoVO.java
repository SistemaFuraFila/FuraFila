package furaFila.relatorios.VOs;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ProdutoVO {
    
   private String razao_social;
   private String produto_desc;
   private Double valor_unitario;
   private Integer qtdEstoque;
   private String tipo_produto_desc;

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getProduto_desc() {
        return produto_desc;
    }

    public void setProduto_desc(String produto_desc) {
        this.produto_desc = produto_desc;
    }

    public Double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getTipo_produto_desc() {
        return tipo_produto_desc;
    }

    public void setTipo_produto_desc(String tipo_produto_desc) {
        this.tipo_produto_desc = tipo_produto_desc;
    }
    
}
