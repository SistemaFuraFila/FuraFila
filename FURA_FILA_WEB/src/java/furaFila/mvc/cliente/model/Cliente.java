package furaFila.mvc.cliente.model;

import furaFila.mvc.imagem.model.Imagem;
import furaFila.mvc.login.model.Login;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Cliente {

    private Integer id_cliente = 0;
    private String nome = "";
    private Date dataNascimento;
    private Long cpf = 0L;
    private Character sexo = ' ';
    private Long tel_res = 0L;
    private Long tel_com = 0L;
    private Long celular = 0L;
    private String email = "";
    private Logradouro logradouro = new Logradouro();
    private String complemento = "";
    private Integer nroCasa;
    private Integer nroApto = 0;
    private Login login = new Login();
    private Imagem imagem = new Imagem();
    
    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getPrimeiroNome(){
        return getNome().split(" ")[0];
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimentoExibicao() {
        return FuraFilaUtils.formataDataExibicao(getDataNascimento());
    }

    public String getDataNascimentoSQL() {
        return FuraFilaUtils.formataDataSQL(getDataNascimento());
    }

    public void setDataNascimentoSQL(String dataNascimento) {
        try {
            this.dataNascimento = FuraFilaUtils.formataDataDate(dataNascimento);
        } catch (ParseException e) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
        }
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getCpfFormatado() {

        String cpfFormatado = "";
        try {
            cpfFormatado = FuraFilaUtils.formataCpf(getCpf());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return cpfFormatado;
    }

    public void setCpfFormatado(String cpf) {
        if ("".equals(cpf)) {
            cpf = "0";
        }
        this.cpf = Long.parseLong(cpf.replaceAll("[.|-]", ""));
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getSexoFormatado() {
        return sexo == FuraFilaConstants.COD_MASCULINO ? FuraFilaConstants.MASCULINO : FuraFilaConstants.FEMININO;
    }

    public void setSexoFormatado(String sexo) {
        this.sexo = FuraFilaConstants.MASCULINO.equals(sexo) ? FuraFilaConstants.COD_MASCULINO : FuraFilaConstants.COD_FEMININO;
    }

    public Long getTel_res() {
        return tel_res;
    }

    public void setTel_res(Long tel_res) {
        this.tel_res = tel_res;
    }

    public String getTel_resFormatado() {
        String telResFormatado = "";
        try {
            telResFormatado = FuraFilaUtils.formataTelefone(tel_res);
        } catch (Exception e) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
        }

        return telResFormatado;
    }

    public void setTel_resFormatado(String tel_res) {
        if ("".equals(tel_res)) {
            tel_res = "0";
        }
        this.tel_res = Long.parseLong(tel_res.replaceAll("[(|)|-]", ""));
    }

    public Long getTel_com() {
        return tel_com;
    }

    public void setTel_com(Long tel_com) {
        this.tel_com = tel_com;
    }

    public String getTel_comFormatado() {

        String telComFormatado = "";
        try {
            telComFormatado = FuraFilaUtils.formataTelefone(tel_com);
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

        return telComFormatado;

    }

    public void setTel_comFormatado(String tel_com) {
        if ("".equals(tel_com)) {
            tel_com = "0";
        }
        this.tel_com = Long.parseLong(tel_com.replaceAll("[(|)|-]", ""));
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getCelularFormatado() {
        String celularFormatado = "";
        try {
            celularFormatado = FuraFilaUtils.formataCelular(celular);
        } catch (Exception e) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
        }

        return celularFormatado;
    }

    public void setCelularFormatado(String celular) {
        if ("".equals(celular.replaceAll("[(|)|-]", complemento))) {
            celular = "0";
        }
        this.celular = Long.parseLong(celular.replaceAll("[(|)|-]", ""));
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNroCasa() {
        return nroCasa;
    }

    public void setNroCasa(Integer nroCasa) {
        this.nroCasa = nroCasa;
    }

    public Integer getNroApto() {
        return nroApto;
    }

    public void setNroApto(Integer nroApto) {
        this.nroApto = nroApto;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Cliente clonar() {
        Cliente cliente = new Cliente();

        cliente.setCpf(getCpf());
        cliente.setDataNascimento(getDataNascimento());
        cliente.setEmail(getEmail());
        cliente.setLogradouro(getLogradouro());
        cliente.setId_cliente(getId_cliente());
        cliente.setImagem(getImagem());
        cliente.setSexo(getSexo());
        cliente.setLogin(getLogin());
        cliente.setNome(getNome());
        cliente.setComplemento(getComplemento());
        cliente.setNroCasa(getNroCasa());
        cliente.setNroApto(getNroApto());
        cliente.setTel_res(getTel_res());
        cliente.setTel_com(getTel_com());
        cliente.setCelular(getCelular());

        return cliente;
    }

    public void zerarObjeto(){
        setCpf(0L);
        setDataNascimento(null);
        setEmail("");
        getLogradouro().zerarObjeto();
        setId_cliente(0);
        getImagem().zerarObjeto();
        setSexo(' ');
        getLogin().zerarObjeto();
        setNome("");
        setComplemento("");
        setNroCasa(0);
        setNroApto(0);
        setTel_res(0L);
        setTel_com(0L);
        setCelular(0L);
    }

}
