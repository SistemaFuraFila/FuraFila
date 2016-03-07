package furaFila.mvc.bairro.business;

import furaFila.mvc.bairro.model.Bairro;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class BairroBusiness  implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public void gravar(Bairro bairro) throws Exception{
        
        String strQuery = "INSERT INTO BAIRRO "
                + "("
                + "desc_bairro,"
                + "id_cidade_FK)"
                + " VALUES "
                + "('" + bairro.getDesc_bairro() + "'"
                + "," + bairro.getCidade().getId_cidade()
                + ")";
        
        bairro.setId_bairro(BancoDados.inserirRetornaID(strQuery));
         
    }
    
    public List<String> buscarBairro(Bairro bairro) throws Exception{
        
        String strQuery = "SELECT B.id_bairro AS [CD],"
                + " B.desc_bairro AS [BAIRRO] "
                + "FROM BAIRRO B WHERE B.desc_bairro LIKE '" + bairro.getDesc_bairro() + "'";
        
        return BancoDados.retornaRegistro(strQuery);
        
    }
    
}
