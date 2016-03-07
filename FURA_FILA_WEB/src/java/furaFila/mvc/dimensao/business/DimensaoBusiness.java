package furaFila.mvc.dimensao.business;

import furaFila.mvc.dimensao.model.Dimensao;
import furaFila.mvc.principal.connectionFactory.BancoDados;
import java.io.Serializable;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class DimensaoBusiness  implements Serializable {

    private static final long serialVersionUID = 1L;
    public void gravar(Dimensao dimensao) throws Exception {

        String strQuery = "INSERT INTO DIMENSAO"
                + "("
                + "altura,"
                + "largura,"
                + "profundidade"
                + ") VALUES ("
                + dimensao.getAltura() + ","
                + dimensao.getLargura() + ","
                + dimensao.getProfundidade()
                + ")";

        dimensao.setId_dimensao(BancoDados.inserirRetornaID(strQuery));

    }

    public void alterar(Dimensao dimensao) throws Exception {

        String strQuery = "UPDATE DIMENSAO "
                + "SET "
                + "altura = " + dimensao.getAltura() + ", "
                + "largura =  " + dimensao.getLargura()+ ", "
                + "profundidade = " + dimensao.getProfundidade()
                + " WHERE id_dimensao = " + dimensao.getId_dimensao();
        
        BancoDados.executaComando(strQuery);
        
    }

}
