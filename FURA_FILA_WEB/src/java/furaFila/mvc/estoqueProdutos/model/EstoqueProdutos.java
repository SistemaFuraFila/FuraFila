
package furaFila.mvc.estoqueProdutos.model;

import furaFila.mvc.estoque.model.Estoque;
import furaFila.mvc.produto.model.Produto;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class EstoqueProdutos {
    
    private Integer id_estoque_produtos;
    private Integer qtdEstoque;
    private Produto produto = new Produto();
    private Estoque estoque = new Estoque();

    public Integer getId_estoque_produtos() {
        return id_estoque_produtos;
    }

    public void setId_estoque_produtos(Integer id_estoque_produtos) {
        this.id_estoque_produtos = id_estoque_produtos;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
    public EstoqueProdutos Clonar(){
        EstoqueProdutos ep = new EstoqueProdutos();
        
        ep.setEstoque(getEstoque());
        ep.setId_estoque_produtos(getId_estoque_produtos());
        ep.setProduto(getProduto());
        ep.setQtdEstoque(getQtdEstoque());
        
        return ep;
    }
}
