package furaFila.converters;

import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class CpfConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        String cpf = "";
        if(value != null && !"".equals(value)){
            cpf = value.replaceAll("[.|-]", "");;
        }
        return cpf;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        String cpf = "";
        if(value != null && value.toString().length() == 11){
            try {
                cpf = FuraFilaUtils.formataCpf(value);
            } catch (Exception ex) {
                FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
            }
        }
        
        return cpf;
        
    }
    
}
