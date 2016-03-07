package furaFila.mvc.comanda.business;

import furaFila.mvc.comanda.model.Comanda;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import furaFila.utils.FuraFilaConstants;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ComandaBusiness {

    public void gravarComanda(Comanda comanda) throws Exception {

        String strQuery = "INSERT INTO COMANDA "
                + "(id_comanda,"
                + "codigoPedido,"
                + "id_estabelecimento_FK,"
                + "id_cliente_FK,"
                + "id_status_FK"
                + ") VALUES ("
                + comanda.getId_comanda()
                + ",'" + comanda.getCodigoPedido() + "'"
                + "," + comanda.getEstabelecimento().getId_estabelecimento()
                + "," + comanda.getCliente().getId_cliente()
                + "," + comanda.getStatus().getId_status()
                + ")";

        BancoDados.executaComando(strQuery);
    }

    public void alterarStatusComanda(Comanda comanda) throws Exception {

        String strQuery = "UPDATE COMANDA SET id_status_FK = " + comanda.getStatus().getId_status() + " WHERE id_comanda = " + comanda.getId_comanda();

        BancoDados.executaComando(strQuery);

    }

    public void atualizarDataVenda(Comanda comanda) throws Exception {

        String strQuery = "UPDATE COMANDA SET dataConfirmacao = GETDATE() WHERE id_comanda = " + comanda.getId_comanda();

        BancoDados.executaComando(strQuery);

    }

    public List<List<String>> listarProdutosPorComanda(Comanda comanda) throws Exception {
        
        String strQuery = "SELECT "
                + " P.id_produto AS [CODIGO_PRODUTO],"
                + " P.produto_desc  AS [DESC_PRODUTO],"
                + " P.valor_unitario AS [PRECO],"
                + " (SELECT PE.qtd FROM PEDIDOS PE WHERE PE.id_produto_FK = P.id_produto  AND PE.id_comanda_FK = " + comanda.getId_comanda() + ") AS [QTD]"
                + " FROM PRODUTO P "
                + " WHERE P.id_produto IN (SELECT PE.id_produto_FK FROM PEDIDOS PE WHERE PE.id_comanda_FK = " + comanda.getId_comanda() + ")";
        
        return BancoDados.retorna_N_Registros(strQuery);
        
    }
    
    public List<String> buscarEstabelecimentoLocker(Comanda comanda) throws Exception{
        
        String strQuery = "SELECT (SELECT E.id_estabelecimento FROM ESTABELECIMENTO E WHERE E.id_estabelecimento IN (SELECT C.id_estabelecimento_FK FROM COMANDA C WHERE C.id_comanda = " + comanda.getId_comanda() + ")) AS ESTABELECIMENTO,"
                + " (SELECT id_locker FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_pedido_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = " + comanda.getId_comanda() + ")))"
                + " AS [ID_LOCKER] FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = " + comanda.getId_comanda() + ")";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
    public List<List<String>> listarComandasAprovadas(String complementoQuery) throws Exception {

        String strQuery = "SELECT "
                + "C.id_comanda AS [COMANDA],"
                + "C.codigoPedido AS [CODIGO_PEDIDO]"
                + ",(SELECT S.id_status FROM STATUS S WHERE S.id_status = C.id_status_FK) AS [CD_STATUS]"
                + ",(SELECT S.status FROM STATUS S WHERE S.id_status = C.id_status_FK) AS [STATUS]"
                + ",(SELECT E.id_estabelecimento FROM ESTABELECIMENTO E WHERE E.id_estabelecimento = C.id_estabelecimento_FK) AS [CD_EMPRESA]"
                + ",(SELECT E.razao_social FROM ESTABELECIMENTO E WHERE E.id_estabelecimento = C.id_estabelecimento_FK) AS [EMPRESA]"
                + ",(SELECT E.cnpj FROM ESTABELECIMENTO E WHERE E.id_estabelecimento = C.id_estabelecimento_FK) AS [CNPJ]"
                + ",(SELECT CL.conjunto_locker_desc FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda)))) AS [CONJUNTO_LOCKER]"
                + ",(SELECT L.id_locker FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))) AS [CODI_LOCKER]"
                + ",(SELECT L.locker_desc FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))) AS [DESC_LOCKER]"
                + ",(SELECT TL.desc_tipo_logradouro FROM TIPO_LOGRADOURO TL WHERE TL.id_tipo_logradouro IN (SELECT L.id_tipo_logradouro_FK FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))))))AS [TIPO_LOGRADOURO]"
                + ",(SELECT L.nroCep FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))))) AS [NRO_CEP]"
                + ",(SELECT L.logradouro FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))))) AS [LOGRADOURO]"
                + ",(SELECT L.latitude FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))))) AS [LATITUDE]"
                + ",(SELECT L.longitude FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))))) AS [LONGITUDE]"
                + ",(SELECT B.desc_bairro FROM BAIRRO B WHERE B.id_bairro IN (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda)))))) AS [BAIRRO]"
                + ",(SELECT CI.desc_cidade FROM CIDADE CI WHERE CI.id_cidade IN (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro	IN (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda))))))) AS [CIDADE]"
                + ",(SELECT U.sigla_uf FROM UF U WHERE U.id_uf IN (SELECT CI.id_uf_FK FROM CIDADE CI WHERE CI.id_cidade IN (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro IN (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep IN (SELECT CL.nroCep_FK FROM CONJUNTO_LOCKER CL WHERE CL.id_conjunto_locker IN (SELECT L.id_conjunto_locker_FK FROM LOCKER L WHERE L.id_locker IN (SELECT PL.id_locker_FK FROM PEDIDO_LOCKER PL WHERE PL.id_pedido_FK IN (SELECT P.id_pedido FROM PEDIDOS P WHERE P.id_comanda_FK = C.id_comanda)))))))) AS [UF] "
                + ",(SELECT CL.nome FROM CLIENTE CL WHERE CL.id_cliente = C.id_cliente_FK) AS [NOME]"
                + ",(SELECT CL.email FROM CLIENTE CL WHERE CL.id_cliente = C.id_cliente_FK) AS [EMAIL]"
                + " FROM COMANDA C " + complementoQuery;

        return BancoDados.retorna_N_Registros(strQuery);

    }

}
