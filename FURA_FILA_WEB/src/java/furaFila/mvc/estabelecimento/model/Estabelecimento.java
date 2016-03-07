package furaFila.mvc.estabelecimento.model;

import furaFila.mvc.imagem.model.Imagem;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Estabelecimento {

    private Integer id_estabelecimento = 0;
    private String razao_social = "";
    private Long inscricao_estadual;
    private Long cnpj = 0L;
    private Boolean status;
    private String email = "";
    private Imagem imagem = new Imagem();

    public Integer getId_estabelecimento() {
        return id_estabelecimento;
    }

    public void setId_estabelecimento(Integer id_estabelecimento) {
        this.id_estabelecimento = id_estabelecimento;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public Long getInscricao_estadual() {
        return inscricao_estadual;
    }

    public void setInscricao_estadual(Long inscricao_estadual) {
        this.inscricao_estadual = inscricao_estadual;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpjFormatado() {
        String cnpjFormatado = "";
        try {
            cnpjFormatado = FuraFilaUtils.formataCnpj(cnpj);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
        return cnpjFormatado;
    }

    public void setCnpjFormatado(String cnpj) {
        this.cnpj = Long.parseLong("".equals(cnpj) ? "0" : cnpj.replaceAll("[.|//|-]", ""));
    }

    public String getStatusFormatado() {
        if (status != null) {
            return status ? FuraFilaConstants.ATIVO : FuraFilaConstants.INATIVO;
        } else {
            return FuraFilaConstants.ATIVO;
        }
    }

    public String getStatusSQL() {
        return String.valueOf(status ? FuraFilaConstants.COD_ATIVO : FuraFilaConstants.COD_INATIVO);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        if (status != null) {
            this.status = status;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public boolean objetoVazio() {
        return FuraFilaUtils.valorZerado(getId_estabelecimento())
                && FuraFilaUtils.valorVazioNulo(getRazao_social())
                && FuraFilaUtils.valorZerado(getInscricao_estadual())
                && FuraFilaUtils.valorZerado(getCnpj())
                && FuraFilaUtils.valorVazioNulo(getEmail())
                && getImagem().objetoVazio();

    }

    public Estabelecimento clonar() {
        
        Estabelecimento e = new Estabelecimento();

        e.setCnpj(getCnpj());
        e.setEmail(getEmail());
        e.setInscricao_estadual(getInscricao_estadual());
        e.setRazao_social(getRazao_social());
        e.setId_estabelecimento(getId_estabelecimento());
        e.setImagem(getImagem());
        e.setStatus(getStatus());

        return e;
    }

    public void zerarObjeto() {
        this.id_estabelecimento = 0;
        this.razao_social = "";
        this.inscricao_estadual = 0L;
        this.cnpj = 0L;
        this.status = null;
        this.email = "";
        this.imagem.zerarObjeto();

    }

}
