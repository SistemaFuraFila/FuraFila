package furaFila.mvc.pedidoLocker.model;

import furaFila.mvc.locker.model.Locker;
import furaFila.mvc.pedidos.model.Pedidos;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class PedidoLocker {
    
    private Integer id_pedido_locker;
    private Locker locker = new Locker();
    private Pedidos pedidos = new Pedidos();

    public Integer getId_pedido_locker() {
        return id_pedido_locker;
    }

    public void setId_pedido_locker(Integer id_pedido_locker) {
        this.id_pedido_locker = id_pedido_locker;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }
    
}
