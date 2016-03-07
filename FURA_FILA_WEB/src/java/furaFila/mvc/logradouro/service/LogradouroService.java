package furaFila.mvc.logradouro.service;

import furaFila.mvc.logradouro.business.LogradouroBusiness;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.utils.FuraFilaUtils;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LogradouroService {
    
    private LogradouroBusiness logradouroBusiness = new LogradouroBusiness();

    public Logradouro buscarLogradouro(Logradouro logradouro) throws Exception{
        
        List<String> lstDados = getLogradouroBusiness().buscarLogradouro(logradouro);
        
        if(!FuraFilaUtils.listaVaziaNula(lstDados)){
            logradouro.setNroCep(Integer.parseInt(lstDados.get(0)));
            logradouro.setLogradouro(lstDados.get(1));
            logradouro.getTipoLogradouro().setDesc_tipo_logradouro(lstDados.get(2));
        }
        else{
            logradouro = new Logradouro();
        }
        
        return logradouro;
        
    }
    
    public boolean logradouroExiste(Logradouro logradouro) throws Exception{
        
        List<String> lstDados = getLogradouroBusiness().buscarLogradouro(logradouro);
        
        return !FuraFilaUtils.listaVaziaNula(lstDados);
        
    }
    
    public void buscarEnderecoCompleto(Logradouro logradouro) throws Exception{
        
       List<String> lstDados = getLogradouroBusiness().buscarEnderecoCompleto(logradouro);
       
       if(!FuraFilaUtils.listaVaziaNula(lstDados)){

           int index = 0;
           logradouro.setNroCep(Integer.parseInt(lstDados.get(index++)));
           logradouro.getTipoLogradouro().setDesc_tipo_logradouro(lstDados.get(index++));
           logradouro.setLogradouro(lstDados.get(index++));
           logradouro.getBairro().setDesc_bairro(lstDados.get(index++));
           logradouro.getBairro().getCidade().setDesc_cidade(lstDados.get(index++));
           logradouro.getBairro().getCidade().getUf().setSigla_uf(lstDados.get(index++));
           
       }
        
    }
    
    public LogradouroBusiness getLogradouroBusiness() {
        return logradouroBusiness;
    }

    public void setLogradouroBusiness(LogradouroBusiness logradouroBusiness) {
        this.logradouroBusiness = logradouroBusiness;
    }
    
}
