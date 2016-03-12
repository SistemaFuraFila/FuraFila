
package furaFila.mvc.login.service;

import furaFila.mvc.login.model.Login;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface ILoginService {
    
    public Login logarSe(Login login) throws Exception;
    public int verificarDuplicidade(Login login, boolean isAlteracao) throws Exception;
    public List<Login> listarEntregador() throws Exception;
    
}
