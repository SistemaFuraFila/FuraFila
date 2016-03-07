package furaFila.mvc.estoque.business;

import furaFila.mvc.estoque.model.Estoque;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueBusiness {

    public void gravar(Estoque estoque) throws Exception {

        String strQuery = "INSERT INTO ESTOQUE (id_estabelecimento_FK) VALUES (" + estoque.getEstabelecimento().getId_estabelecimento() + ")";

        BancoDados.executaComando(strQuery);

    }

    
    
    public List<String> verificarEstoqueExiste(Estoque estoque) throws Exception {

        String strQuery = "SELECT COUNT(*) FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estoque.getEstabelecimento().getId_estabelecimento();

        return BancoDados.retornaRegistro(strQuery);

    }

    public List<String> buscarCodigoEstoque(Estoque estoque) throws Exception {

        String strQuery = "SELECT E.id_estoque FROM ESTOQUE E WHERE E.id_estabelecimento_FK = " + estoque.getEstabelecimento().getId_estabelecimento();

        return BancoDados.retornaRegistro(strQuery);

    }

}
