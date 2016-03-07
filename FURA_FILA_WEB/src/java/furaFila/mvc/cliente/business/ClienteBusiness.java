package furaFila.mvc.cliente.business;

import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ClienteBusiness {

    public void gravar(Cliente cliente) throws Exception {

        String strQuery = "INSERT INTO CLIENTE"
                + "("
                + "nome,"
                + "dataNascimento,"
                + "cpf,"
                + "sexo,"
                + "tel_res,"
                + "tel_com,"
                + "celular,"
                + "email,"
                + "complemento,"
                + "nroCasa,"
                + "nroApto,"
                + "nroCep_FK,"
                + "id_login_FK"
                + ")"
                + " VALUES "
                + "("
                + "'" + cliente.getNome() + "',"
                + "'" + cliente.getDataNascimentoSQL() + "',"
                + "'" + cliente.getCpf() + "',"
                + "'" + cliente.getSexo() + "',"
                + cliente.getTel_res() + ","
                + cliente.getTel_com() + ","
                + cliente.getCelular() + ","
                + "'" + cliente.getEmail() + "',"
                + "'" + cliente.getComplemento() + "',"
                + "'" + cliente.getNroCasa() + "',"
                + "'" + cliente.getNroApto() + "',"
                + cliente.getLogradouro().getNroCep() + ","
                + cliente.getLogin().getId_login()
                + ")";

        BancoDados.executaComando(strQuery);

    }

    public void alterarDadosBasicos(Cliente cliente) throws Exception{
        
        String strQuery = "UPDATE CLIENTE "
                + "SET "
                + "nome = '" + cliente.getNome() + "',"
                + "dataNascimento = '" + cliente.getDataNascimentoSQL() + "',"
                + "cpf = '" + cliente.getCpf() + "',"
                + "email = '" + cliente.getEmail() + "', "
                + "tel_res = '" + cliente.getTel_res() +"', "
                + "tel_com = '" + cliente.getTel_com() +"', "
                + "celular = '" + cliente.getCelular()+"' "
                + "WHERE id_cliente = " + cliente.getId_cliente();
        
        BancoDados.executaComando(strQuery);
        
    }
    
    
    public List<String> buscarDadosCliente(Cliente cliente) throws Exception {

        String strQuery = "SELECT "
                + "C.id_cliente AS [CODIGO],"
                + "C.nome AS [NOME],"
                + "C.cpf AS [CPF],"
                + "C.sexo AS [SEXO],"
                + "C.tel_res AS [TELEFONE],"
                + "C.dataNascimento AS [NASCIMENTO],"
                + "C.id_imagem_FK AS [CD IMAGEM],"
                + "(SELECT L.usuario FROM LOGIN L WHERE L.id_login = C.id_login_FK) AS [USUARIO]"
                + " FROM CLIENTE C WHERE C.id_login_FK = " + cliente.getLogin().getId_login();

        return BancoDados.retornaRegistro(strQuery);

    }

    public List<String> buscarDadosBasicosCliente(Cliente cliente) throws Exception {

        String strQuery = "SELECT "
                + "C.id_cliente AS [CODIGO], "
                + "C.nome AS [CLIENTE], "
                + "C.dataNascimento AS [NASCIMENTO], "
                + "C.cpf AS [CPF],"
                + "C.sexo AS [SEXO], "
                + "C.tel_res AS [TEL_COM], "
                + "C.tel_com AS [TEL_RES], "
                + "C.celular AS [CEL], "
                + "C.email AS [EMAIL], "
                + "C.complemento AS [COMPLEMENTO], "
                + "C.nroCasa AS [NRO], "
                + "C.nroApto AS [NRO_APTO],"
                + "(SELECT L.nroCep FROM LOGRADOURO L WHERE L.nroCep = C.nroCep_FK) AS [CEP], "
                + "(SELECT (SELECT TL.desc_tipo_logradouro FROM TIPO_LOGRADOURO TL WHERE TL.id_tipo_logradouro = L.id_tipo_logradouro_FK) FROM LOGRADOURO L WHERE L.nroCep = C.nroCep_FK) AS [TIPO_LOGRADOURO],"
                + "(SELECT L.logradouro FROM LOGRADOURO L WHERE L.nroCep = C.nroCep_FK) AS [LOGRADOURO],"
                + "(SELECT B.desc_bairro FROM BAIRRO B WHERE B.id_bairro = (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep = C.nroCep_FK)) AS [BAIRRO],"
                + "(SELECT CI.desc_cidade FROM CIDADE CI WHERE CI.id_cidade = (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro = (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep = C.nroCep_FK))) AS [CIDADE],"
                + "(SELECT U.sigla_uf FROM UF U WHERE U.id_uf = (SELECT CI.id_uf_FK FROM CIDADE CI WHERE CI.id_cidade = (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro = (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep = C.nroCep_FK)))) AS [UF],"
                + "(SELECT L.usuario FROM LOGIN L WHERE L.id_login = C.id_login_FK) AS [USUARIO] "
                + "FROM CLIENTE C WHERE C.id_cliente = " + cliente.getId_cliente() + " OR C.id_login_FK = " + cliente.getLogin().getId_login();

        return BancoDados.retornaRegistro(strQuery);
    }

}
