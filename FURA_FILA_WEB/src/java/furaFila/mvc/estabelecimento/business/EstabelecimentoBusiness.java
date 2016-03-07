package furaFila.mvc.estabelecimento.business;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoBusiness {

    public void gravar(Estabelecimento estabelecimento) throws Exception {

        String strQuery = "INSERT INTO "
                + "ESTABELECIMENTO"
                + "("
                + "razao_social, "
                + "email,"
                + "cnpj, "
                + "inscricao_estadual, "
                + "status "
                + ") VALUES ("
                + "'" + estabelecimento.getRazao_social() + "',"
                + "'" + estabelecimento.getEmail() + "',"
                + "'" + estabelecimento.getCnpj() + "',"
                + "'" + estabelecimento.getInscricao_estadual() + "',"
                + "'" + estabelecimento.getStatusSQL() + "')";

        estabelecimento.setId_estabelecimento(BancoDados.inserirRetornaID(strQuery));

    }

    public void alterar(Estabelecimento estabelecimento) throws Exception {

        String strQuery = "UPDATE "
                + "ESTABELECIMENTO"
                + " SET "
                + "razao_social = '" + estabelecimento.getRazao_social() + "',"
                + " cnpj = '" + estabelecimento.getCnpj() + "',"
                + " inscricao_estadual = '" + estabelecimento.getInscricao_estadual() + "'"
                + " WHERE id_estabelecimento = " + estabelecimento.getId_estabelecimento();

        BancoDados.executaComando(strQuery);

    }

    public void alterarStatus(Estabelecimento estabelecimento) throws Exception {

        String strQuery = "UPDATE "
                + "ESTABELECIMENTO "
                + "SET status = '" + estabelecimento.getStatusSQL() + "' "
                + "WHERE id_estabelecimento = " + estabelecimento.getId_estabelecimento();

        BancoDados.executaComando(strQuery);

    }

    public List<List<String>> listarEstabelecimento() throws Exception {

        String strQuery = "SELECT "
                + "E.id_estabelecimento AS [CODIGO], "
                + "E.razao_social AS [RAZAO_SOCIAL], "
                + "E.cnpj AS [CNPJ], "
                + "E.inscricao_estadual AS [IE], "
                + "E.status AS [STATUS], "
                + "E.email AS [EMAIL],"
                + "E.id_imagem_FK AS [CODIGO_IMG] "
                + "FROM ESTABELECIMENTO E";

        return BancoDados.retorna_N_Registros(strQuery);

    }

    public List<String> listarInformacoesEstabelecimento(Estabelecimento estabelecimento) throws Exception {

        String strQuery = "SELECT "
                + "E.id_estabelecimento AS [CODIGO], "
                + "E.razao_social AS [RAZAO_SOCIAL], "
                + "E.email AS [EMAIL],"
                + "E.cnpj AS [CNPJ], "
                + "E.inscricao_estadual AS [INSCRICAO_ESTADUAL], "
                + "(SELECT I.id_imagem FROM IMAGEM I WHERE I.id_imagem = E.id_imagem_FK) AS [CD IMAGEM]"
                + " FROM ESTABELECIMENTO E WHERE E.id_estabelecimento = " + estabelecimento.getId_estabelecimento();

        return BancoDados.retornaRegistro(strQuery);

    }

    public List<String> pegarRazaoSocial(Estabelecimento estabelecimento) throws Exception{
        
        String strQuery = "SELECT E.razao_social AS [RAZAO_SOCIAL] "
                + "FROM ESTABELECIMENTO E WHERE E.razao_social LIKE '" + estabelecimento.getRazao_social() + "'";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    public List<String> pegarEmail(Estabelecimento estabelecimento) throws Exception{
        
        String strQuery = "SELECT E.email AS [EMAIL] "
                + "FROM ESTABELECIMENTO E WHERE E.email LIKE '" + estabelecimento.getEmail() + "' ";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    public List<String> pegarCnpj(Estabelecimento estabelecimento) throws Exception{
        
        String strQuery = "SELECT E.cnpj AS [CNPJ] "
                + "FROM ESTABELECIMENTO E WHERE E.cnpj = " + estabelecimento.getCnpj();
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    public List<String> pegarInscricaoEstadual(Estabelecimento estabelecimento) throws Exception{
        
        String strQuery = "SELECT E.inscricao_estadual AS [IE] "
                + "FROM ESTABELECIMENTO E WHERE E.inscricao_estadual = " + estabelecimento.getInscricao_estadual();
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
    public List<List<String>> listarEstabelecimentoMaisVendem() throws Exception{
        
        String strQuery = "SELECT "
                + "E.razao_social AS [EMPRESA],"
                + " COUNT (P.qtd) AS [PRODUTOS] "
                + "FROM PEDIDOS P "
                + "INNER JOIN COMANDA C ON (P.id_comanda_FK = C.id_comanda) "
                + "INNER JOIN ESTABELECIMENTO E ON (C.id_estabelecimento_FK = E.id_estabelecimento) "
                + "GROUP BY E.razao_social ORDER BY COUNT (P.qtd) DESC";
        
        return BancoDados.retorna_N_Registros(strQuery);
        
    }
    
}
