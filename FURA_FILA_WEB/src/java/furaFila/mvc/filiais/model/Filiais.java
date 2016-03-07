package furaFila.mvc.filiais.model;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.logradouro.model.Logradouro;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Filiais {

    private Integer id_filial = 0;
    private Long telefone = 0L;
    private Long outro_telefone = 0L;
    private String email = "";
    private Logradouro logradouro = new Logradouro();
    private Estabelecimento estabelecimento = new Estabelecimento();

    public Integer getId_filial() {
        return id_filial;
    }

    public void setId_filial(Integer id_filial) {
        this.id_filial = id_filial;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Long getOutro_telefone() {
        return outro_telefone;
    }

    public void setOutro_telefone(Long outro_telefone) {
        this.outro_telefone = outro_telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

  
    
}
