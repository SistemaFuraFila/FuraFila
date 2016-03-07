package furaFila.mvc.imagem.model;

import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.io.File;
import java.io.FileInputStream;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Imagem {

    private Integer id_imagem = 0;
    private String imagem = "";

    public Integer getId_imagem() {
        return id_imagem;
    }

    public void setId_imagem(Integer id_imagem) {
        this.id_imagem = id_imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public StreamedContent getImagemVisualizar() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            File arquivo = new File((String) FuraFilaUtils.pegarValoresParametro("imagem"));
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(arquivo);
            } catch (Exception e) {
                try {
                    inputStream = new FileInputStream(new File(FuraFilaConstants.SEM_IMAGEM_ESTABELECIMENTO));
                } catch (Exception ex) {
                    FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
                }
            }
            return new DefaultStreamedContent(inputStream);
        }

    }
    
    public StreamedContent getImagemVisualizar_2() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            File arquivo = new File(getImagem());
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(arquivo);
            } catch (Exception e) {
                try {
                    inputStream = new FileInputStream(new File(FuraFilaConstants.SEM_IMAGEM_ESTABELECIMENTO));
                } catch (Exception ex) {
                    FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
                }
            }
            return new DefaultStreamedContent(inputStream);
        }

    }

    public void setImagem(String imagem) {
        if(!"".equals(imagem)){
            this.imagem = imagem;
        }
    }

    public boolean objetoVazio() {
        return FuraFilaUtils.valorZerado(getId_imagem()) && FuraFilaUtils.valorVazioNulo(getImagem());
    }

    public Imagem clonar() {
        Imagem i = new Imagem();

        i.setId_imagem(getId_imagem());
        i.setImagem(getImagem());

        return i;
    }
    
    public void zerarObjeto(){
        setId_imagem(0);
        this.imagem = "";
    }

}
