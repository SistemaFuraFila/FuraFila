package furaFila.mvc.conjuntoLocker.business;

import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConjuntoLockerBusiness {

    public void gravar(ConjuntoLocker conjuntoLocker) throws Exception {

        String strQuery = "INSERT INTO CONJUNTO_LOCKER("
                + "conjunto_locker_desc,"
                + "nro_localizacao,"
                + "nroCep_FK"
                + ") VALUES ("
                + "'" + conjuntoLocker.getConjunto_locker_desc() + "'"
                + "," + conjuntoLocker.getNro_localizacao()
                + "," + conjuntoLocker.getLogradouro().getNroCep()
                + ")";

        conjuntoLocker.setId_conjunto_locker(BancoDados.inserirRetornaID(strQuery));

    }

    public void alterar(ConjuntoLocker conjuntoLocker) throws Exception {
        
        String strQuery = "UPDATE "
                + "CONJUNTO_LOCKER "
                + "SET "
                + "conjunto_locker_desc = '" + conjuntoLocker.getConjunto_locker_desc() + "'"
                + ",nroCep_FK = " + conjuntoLocker.getLogradouro().getNroCep()
                + ",nro_localizacao = " + conjuntoLocker.getNro_localizacao()
                + " WHERE id_conjunto_locker = " + conjuntoLocker.getId_conjunto_locker();
        
        BancoDados.executaComando(strQuery);
        
    }
    
    public void alterarStatus(ConjuntoLocker conjuntoLocker) throws Exception {

        String strQuery = "UPDATE "
                + "CONJUNTO_LOCKER "
                + "SET "
                + "status = '" + conjuntoLocker.getStatusSQL() + "' "
                + "WHERE id_conjunto_locker = " + conjuntoLocker.getId_conjunto_locker();

        BancoDados.executaComando(strQuery);

    }

    public List<List<String>> listarConjuntoLockers() throws Exception {

        String strQuery = "SELECT "
                + "CL.id_conjunto_locker AS [COD],"
                + "CL.conjunto_locker_desc AS [DESC_CONJUNTO],"
                + "CL.nro_localizacao AS [NRO_LOCALIZACAO],"
                + "CL.nroCep_FK AS [CEP],"
                + "(SELECT L.latitude FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK) AS [LATITUDE],"
                + "(SELECT L.longitude FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK) AS [LONGITUDE],"
                + "(SELECT TL.desc_tipo_logradouro FROM TIPO_LOGRADOURO TL WHERE TL.id_tipo_logradouro IN (SELECT L.id_tipo_logradouro_FK FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK)) AS [TIPO_LOGRADOURO],"
                + "(SELECT L.logradouro FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK) AS [LOGRADOURO],"
                + "(SELECT B.desc_bairro FROM BAIRRO B WHERE B.id_bairro IN (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK)) AS [BAIRRO],"
                + "(SELECT C.desc_cidade FROM CIDADE C WHERE C.id_cidade IN (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro IN (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK))) AS [CIDADE],"
                + "(SELECT U.sigla_uf FROM UF U WHERE U.id_uf IN (SELECT C.id_uf_FK FROM CIDADE C WHERE C.id_cidade IN (SELECT B.id_cidade_FK FROM BAIRRO B WHERE B.id_bairro IN (SELECT L.id_bairro_FK FROM LOGRADOURO L WHERE L.nroCep = CL.nroCep_FK)))) AS [UF],"
                + "CL.status AS [STATUS],"
                + "(SELECT COUNT(L.id_locker) FROM LOCKER L WHERE L.id_conjunto_locker_FK = CL.id_conjunto_locker) AS [QTD_LOCKER]"
                + "FROM CONJUNTO_LOCKER CL";

        return BancoDados.retorna_N_Registros(strQuery);
    }

    public List<List<String>> listarLockers(ConjuntoLocker conjuntoLocker) throws Exception {

        String strQuery = "SELECT L.id_locker AS [COD_LOCKER],"
                + "L.locker_desc AS [DESC],"
                + "(SELECT DL.id_dimensao_locker FROM DIMENSOES_LOCKER DL WHERE DL.id_dimensao_locker = L.id_dimensao_locker_FK) AS [CODIGO],"
                + "(SELECT DL.dimensao_desc FROM DIMENSOES_LOCKER DL WHERE DL.id_dimensao_locker = L.id_dimensao_locker_FK) AS [TIPO],"
                + "(SELECT S.id_status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [CD_STATUS],"
                + "(SELECT S.status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [STATUS] FROM LOCKER L WHERE L.id_conjunto_locker_FK = " + conjuntoLocker.getId_conjunto_locker();

        return BancoDados.retorna_N_Registros(strQuery);

    }

    public List<List<String>> listarLockersPorConjunto(ConjuntoLocker conjuntoLocker) throws Exception {

        String strQuery = "SELECT L.id_locker AS [CODIGO],"
                + "L.locker_desc AS [DESC],"
                + "L.status AS [STATUS],"
                + "(SELECT S.id_status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [CD_STATUS],"
                + "(SELECT S.status FROM STATUS S WHERE S.id_status = L.id_status_FK) AS [DISPONIBILIZACAO],"
                + "(SELECT D.id_dimensao FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [CD_DIMENSAO],"
                + "(SELECT D.dimensao_desc FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [DIMENSAO_DESC],"
                + "(SELECT D.altura FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [ALTURA],"
                + "(SELECT D.largura FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [LARGURA],"
                + "(SELECT D.profundidade FROM DIMENSAO D WHERE D.id_dimensao = L.id_dimensao_FK) AS [PROFUNDIDADE]"
                + " FROM LOCKER L WHERE (L.id_status_FK = 1 OR L.id_status_FK = 2) AND L.id_conjunto_locker_FK = " + conjuntoLocker.getId_conjunto_locker();

        return BancoDados.retorna_N_Registros(strQuery);

    }
    
    public List<List<String>> listarLockersProximos(Cliente cliente) throws Exception{
        
        String strQuery = "SELECT "
                + "C.id_conjunto_locker,"
                + " C.conjunto_locker_desc,"
                + "P.id_locker,"
                + "P.locker_desc,"
                + "D.dimensao_desc,"
                + "C.nroCep_FK "
                + "FROM CONJUNTO_LOCKER C"
                + " INNER JOIN LOCKER P ON (P.id_conjunto_locker_FK = C.id_conjunto_locker) INNER JOIN"
                + " DIMENSAO D ON (P.id_dimensao_FK = D.id_dimensao) "
                + "WHERE P.id_status_FK = 2 ORDER BY ABS(C.nroCep_FK - " + cliente.getLogradouro().getNroCep() + ") ASC";
        
        return BancoDados.retorna_N_Registros(strQuery);
        
    }

}
