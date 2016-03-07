package furaFila.mvc.produto.model;

import furaFila.mvc.dimensao.model.Dimensao;
import furaFila.mvc.imagem.model.Imagem;
import furaFila.mvc.tipoProduto.model.TipoProduto;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Produto {

    private Integer id_produto;
    private String produto_desc;
    private Double valor_unitario = 0.0;
    private Integer qtdMinima;
    private Integer qtdMaxima;
    private Boolean status;
    private TipoProduto tipoProduto = new TipoProduto();
    private Imagem imagem = new Imagem();
    private Dimensao dimensao = new Dimensao();

    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
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

    public String getValor_unitarioExibicao(){
        return FuraFilaUtils.formatarMoeda(getValor_unitario());
    }
    
    public void setValor_unitario(Double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public Integer getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(Integer qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public Integer getQtdMaxima() {
        return qtdMaxima;
    }

    public void setQtdMaxima(Integer qtdMaxima) {
        this.qtdMaxima = qtdMaxima;
    }
    
    public Character getStatusSQL(){
        return status? FuraFilaConstants.COD_ATIVO :  FuraFilaConstants.COD_INATIVO;
    }
    
    public String getStatusExibicao(){
        return status ? FuraFilaConstants.ATIVO :  FuraFilaConstants.INATIVO;
    }
    
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Dimensao getDimensao() {
        return dimensao;
    }

    public void setDimensao(Dimensao dimensao) {
        this.dimensao = dimensao;
    }

    public Produto clonar() {
        Produto produto = new Produto();

        produto.setId_produto(getId_produto());
        produto.setProduto_desc(getProduto_desc());
        produto.setValor_unitario(getValor_unitario());
        produto.setQtdMaxima(getQtdMaxima());
        produto.setQtdMinima(getQtdMinima());
        produto.setStatus(getStatus());
        produto.setTipoProduto(getTipoProduto());
        produto.setImagem(getImagem());
        produto.setDimensao(getDimensao());

        return produto;
    }

}
