package furaFila.mvc.modelsGerais;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.produto.model.Produto;

/**
 *
 * @author Wellington Gonçalves Pires
 */

public class ComprarProduto {
    
    private String flag = "0";
    private String pesquisa = "";
    private String ordenarProdutos = "";
    private Produto produto = new Produto();
    private Estabelecimento estabelecimento = new Estabelecimento();

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrdenarProdutos() {
        return ordenarProdutos;
    }

    public void setOrdenarProdutos(String ordenarProdutos) {
        this.ordenarProdutos = ordenarProdutos;
    }

}
