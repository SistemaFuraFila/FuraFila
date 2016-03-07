package furaFila.mvc.logoff.bean;

import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class LogoffBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer numero = 5;

    public Integer getNumero() {
        return numero;
    }

    public String getNumeroExibicao() {
        if (numero > 1) {
            return numero.toString() + " segundos";
        } else if (numero <= 1) {
            return numero.toString() + " segundo";
        }

        return null;
    }

    public void contador() {
        if (numero > 0) {
            numero--;
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
            }
        }
    }

}
