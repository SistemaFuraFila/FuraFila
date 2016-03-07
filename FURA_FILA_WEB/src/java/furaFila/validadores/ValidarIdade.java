package furaFila.validadores;

import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@FacesValidator("validarIdade")
public class ValidarIdade implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        try {
            
            int idade = FuraFilaUtils.calculaIdade((Date)value);

            if(idade < FuraFilaConstants.MAIOR_IDADE){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "O cliente deve ser maior de 18 anos", ""));
            }
            
        } catch (ParseException ex) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }

    }

}
