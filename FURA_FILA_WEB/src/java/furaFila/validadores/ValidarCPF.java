package furaFila.validadores;

import furaFila.utils.FuraFilaConstants;
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
@FacesValidator("validarCPF")
public class ValidarCPF implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object valorTela) throws ValidatorException {

        valorTela = valorTela.toString().replaceAll("[.|-]", "");
        
        if (!validaCPF(String.valueOf(valorTela))) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FuraFilaConstants.AVISO_CPF_INVALIDO,FuraFilaConstants.AVISO_CPF_INVALIDO));
        }

    }

    private static boolean validaCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    private static boolean isCPFPadrao(String cpf) {
        return cpf.equals("11111111111") 
                || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999");

    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = 0;
        } else {
            primDig = 11 - (soma % 11);
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = 0;
        } else {
            segDig = 11 - (soma % 11);
        }

        return primDig.toString() + segDig.toString();
    }

}
