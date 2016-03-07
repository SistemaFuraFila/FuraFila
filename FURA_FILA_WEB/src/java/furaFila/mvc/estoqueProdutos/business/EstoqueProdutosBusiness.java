package furaFila.mvc.estoqueProdutos.business;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estoqueProdutos.model.EstoqueProdutos;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueProdutosBusiness {

    public void gravar(EstoqueProdutos estoqueProdutos) throws Exception {

        String strQuery = "INSERT INTO ESTOQUE_PRODUTOS"
                + " ("
                + "qtdEstoque,"
                + "id_produto_FK,"
                + "id_estoque_FK"
                + ") VALUES ("
                + estoqueProdutos.getQtdEstoque() + ","
                + estoqueProdutos.getProduto().getId_produto() + ","
                + estoqueProdutos.getEstoque().getId_estoque()
                + ")";

        estoqueProdutos.setId_estoque_produtos(BancoDados.inserirRetornaID(strQuery));

    }

    public List<List<String>> listarProdutosPorCodigoEstabelecimento(Estabelecimento estabelecimento) throws Exception {

        String strQuery = "SELECT "
                + "P.id_produto AS [CD_PRODUTO],"
                + "P.produto_desc AS [PRODUTO],"
                + "P.qtdMinima AS [QTD_MINIMA],"
                + "P.valor_unitario AS [PRECO],"
                + "P.status AS [STATUS],"
                + " (SELECT TP.id_tipo_produto FROM TIPO_PRODUTO TP WHERE TP.id_tipo_produto = P.id_tipo_produto_FK) AS [CD_TIPO_PRODUTO],"
                + " (SELECT TP.tipo_produto_desc FROM TIPO_PRODUTO TP WHERE TP.id_tipo_produto = P.id_tipo_produto_FK) AS [TIPO_PRODUTO],"
                + "(SELECT D.id_dimensao FROM DIMENSAO D WHERE D.id_dimensao = P.id_dimensao_FK) AS [CD_DIMENSAO],"
                + "(SELECT D.altura FROM DIMENSAO D WHERE D.id_dimensao = P.id_dimensao_FK) AS [ALTURA],"
                + "(SELECT D.largura FROM DIMENSAO D WHERE D.id_dimensao = P.id_dimensao_FK) AS [LARGURA],"
                + "(SELECT D.profundidade FROM DIMENSAO D WHERE D.id_dimensao = P.id_dimensao_FK) AS [PROFUNDIDADE],"
                + "(SELECT I.id_imagem FROM IMAGEM I WHERE I.id_imagem = P.id_imagem_FK) AS [CD_IMAGEM],"
                + "(SELECT EP.qtdEstoque FROM ESTOQUE_PRODUTOS EP WHERE EP.id_produto_FK = P.id_produto) AS [QTD_ESTOQUE]"
                + " FROM PRODUTO P WHERE P.id_tipo_produto_FK IN (SELECT TP.id_tipo_produto FROM TIPO_PRODUTO TP WHERE TP.status = 1)"
                + " AND P.id_produto IN (SELECT EP.id_produto_FK FROM ESTOQUE_PRODUTOS EP WHERE EP.id_estoque_FK = (SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estabelecimento.getId_estabelecimento() + "))";

        return BancoDados.retorna_N_Registros(strQuery);

    }
    
    public List<List<String>> dadosParaRelatorio(EstoqueProdutos ep) throws Exception{
        
        String strQuery = "SELECT (SELECT E.razao_social FROM ESTABELECIMENTO E WHERE E.id_estabelecimento IN (SELECT EQ.id_estabelecimento_FK FROM ESTOQUE EQ WHERE EQ.id_estoque = EP.id_estoque_FK)) AS [ESTABELECIMENTO],"
                + "(SELECT P.produto_desc FROM PRODUTO P WHERE P.id_produto = EP.id_produto_FK) AS [PRODUTO],"
                + " (SELECT P.valor_unitario FROM PRODUTO P WHERE P.id_produto = EP.id_produto_FK) AS [VL_UNITARIO],"
                + "EP.qtdEstoque AS [QTD_ESTOQUE],"
                + "(SELECT TP.tipo_produto_desc FROM TIPO_PRODUTO TP WHERE TP.id_tipo_produto IN (SELECT P.id_tipo_produto_FK FROM PRODUTO P WHERE P.id_produto = EP.id_produto_FK)) AS [TIPO_PRODUTO] "
                + "FROM ESTOQUE_PRODUTOS EP WHERE EP.id_estoque_FK = (SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + ep.getEstoque().getEstabelecimento().getId_estabelecimento() + ")";
        
        return BancoDados.retorna_N_Registros(strQuery);
        
    }
    
}
