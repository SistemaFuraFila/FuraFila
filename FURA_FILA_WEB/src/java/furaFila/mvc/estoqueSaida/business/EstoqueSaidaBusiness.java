package furaFila.mvc.estoqueSaida.business;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estoqueSaida.model.EstoqueSaida;
import furaFila.mvc.principal.connectionFactory.BancoDados;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueSaidaBusiness {
    
    public void gravar(EstoqueSaida estoqueSaida, Estabelecimento estabelecimento) throws Exception {
        
        String strQuery = "SELECT * INTO #ESTOQUE_TEMP FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estabelecimento.getId_estabelecimento() + ";"
                +"INSERT INTO ESTOQUE_SAIDA ("
                + "qtdSaida,"
                + "id_produto_FK,"
                + "id_motivo_FK"
                + ") VALUES ("
                + estoqueSaida.getQtdSaida() + ","
                + estoqueSaida.getProduto().getId_produto() + ","
                + "(SELECT id_motivo FROM MOTIVO_SAIDA MS WHERE MS.motivoSaida = '" + estoqueSaida.getMotivoSaida().getMotivoSaida() + "')"
                + ")";
        
        BancoDados.executaComando(strQuery);
        
    }
    
}
