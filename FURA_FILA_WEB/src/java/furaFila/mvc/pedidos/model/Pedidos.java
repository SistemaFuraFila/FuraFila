package furaFila.mvc.pedidos.model;

import furaFila.mvc.comanda.model.Comanda;
import furaFila.mvc.produto.model.Produto;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Pedidos {

    private Integer id_pedido = 0;
    private Integer qtd = 0;
    private Produto produto = new Produto();
    private Comanda comanda = new Comanda();

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void getSomarQtd() {
        setQtd(this.qtd++);
    }

    public void getSubtQtd() {
        setQtd(this.qtd--);
    }

    public void manipularQtd(char operacao) {
        if (operacao == '+') {
            setQtd(+1);
        } else if (operacao == '-') {
            if (getQtd() > 0) {
                setQtd(-1);
            }
        }
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getSubTotal() {
        if (getQtd() != null && getProduto() != null) {
            return getQtd() * getProduto().getValor_unitario();
        } else {
            return 0.0;
        }
    }

    public String getSubTotalExibicao() {

        if (getQtd() != null && getProduto() != null) {
            return FuraFilaUtils.formatarMoeda(getQtd() * getProduto().getValor_unitario());
        } else {
            return FuraFilaUtils.formatarMoeda(0.0);
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Pedidos clonar() {
        Pedidos pedidos = new Pedidos();

        pedidos.setId_pedido(getId_pedido());
        pedidos.setQtd(getQtd());
        pedidos.setProduto(getProduto().clonar());
        pedidos.setComanda(getComanda().clonar());

        return pedidos;
    }

    public void somarQtdeProdutos(int qtdProduto) {
        setQtd(getQtd() + qtdProduto);
    }


}
