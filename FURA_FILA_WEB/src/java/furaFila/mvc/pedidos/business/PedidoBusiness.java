package furaFila.mvc.pedidos.business;

import furaFila.mvc.pedidos.model.Pedidos;
import furaFila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class PedidoBusiness {
    
    public void gravarPedido(Pedidos pedido) throws Exception{
        
        String strQuery = "INSERT INTO PEDIDOS ("
                + "id_produto_FK,"
                + "qtd,"
                + "id_comanda_FK"
                + ") VALUES ("
                + pedido.getProduto().getId_produto()
                + "," + pedido.getQtd()
                + "," + pedido.getComanda().getId_comanda()
                + ")";
        pedido.setId_pedido(BancoDados.inserirRetornaID(strQuery));
        
    }
    
}
