package furaFila.mvc.estoqueEntrada.business;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estoqueEntrada.model.EstoqueEntrada;
import furaFila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueEntradaBusiness {

    public void gravar(EstoqueEntrada estoqueEntrada, Estabelecimento estabelecimento) throws Exception {

        String strQuery = "SELECT * INTO #ESTOQUE_TEMP FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estabelecimento.getId_estabelecimento() + ";"
                + "INSERT INTO ESTOQUE_ENTRADA ("
                + "qtdEntrada,"
                + "id_produto_FK,"
                + "id_motivo_entrada"
                + ") VALUES ("
                + estoqueEntrada.getQtdEntrada() + ","
                + estoqueEntrada.getProduto().getId_produto() + ","
                + "(SELECT id_motivo_entrada FROM MOTIVO_ENTRADA ME WHERE ME.motivoEntrada = '" + estoqueEntrada.getMotivoEntrada().getMotivoEntrada() + "')"
                + ")";

        BancoDados.executaComando(strQuery);
        
    }

}
