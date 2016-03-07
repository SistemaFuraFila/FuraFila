package furaFila.mvc.logradouro.business;

import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LogradouroBusiness {
    
    public void gravar(Logradouro logradouro) throws Exception{
 
        String strQuery = "INSERT INTO LOGRADOURO ("
                + "nroCep,"
                + "logradouro,"
                + " latitude,"
                + "longitude,"
                + "id_tipo_logradouro_FK,"
                + " id_bairro_FK"
                + ") "
                + "VALUES"
                + " ( " + logradouro.getNroCep() + " ,"
                + "'" + logradouro.getLogradouro() + "',"
                + logradouro.getLatitude()  + ","
                + logradouro.getLongitude()+ ","
                + "(SELECT TP.id_tipo_logradouro FROM TIPO_LOGRADOURO TP WHERE TP.desc_tipo_logradouro LIKE '" + logradouro.getTipoLogradouro().getDesc_tipo_logradouro() + "'),"
                + logradouro.getBairro().getId_bairro() + ")";
   
        BancoDados.executaComando(strQuery);
        
    }
    
    public List<String> buscarLogradouro(Logradouro logradouro) throws Exception{
        
        String strQuery = "SELECT L.nroCep AS [CEP],"
                + " L.logradouro AS [LOGRADOURO],"
                + " (SELECT TL.desc_tipo_logradouro FROM TIPO_LOGRADOURO TL WHERE TL.desc_tipo_logradouro LIKE '" + logradouro.getTipoLogradouro().getDesc_tipo_logradouro() +  "') AS [TIPO]"
                + " FROM LOGRADOURO L WHERE L.nroCep = " + logradouro.getNroCep();
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
    public List<String> buscarEnderecoCompleto(Logradouro logradouro) throws Exception{
        
        String strQuery = "SELECT L.nroCep AS [CEP],"
                + " (SELECT TL.desc_tipo_logradouro FROM TIPO_LOGRADOURO TL WHERE TL.id_tipo_logradouro = L.id_tipo_logradouro_FK) AS [TIPO_LOGRADOURO],"
                + "L.logradouro AS [LOGRADOURO],"
                + "(SELECT B.desc_bairro FROM BAIRRO B WHERE B.id_bairro = L.id_bairro_FK) AS [BAIRRO],"
                + "(SELECT C.desc_cidade FROM CIDADE C WHERE C.id_cidade IN (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro = L.id_bairro_FK)) AS [CIDADE],"
                + "(SELECT U.sigla_uf FROM UF U WHERE U.id_uf IN (SELECT C.id_uf_FK FROM CIDADE C WHERE C.id_cidade IN (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro = L.id_bairro_FK))) AS [UF]"
                + "FROM LOGRADOURO L WHERE L.nroCep = " + logradouro.getNroCep();
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
}
