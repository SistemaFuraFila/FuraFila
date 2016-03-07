package furaFila.utils;

/*
 * @Author: Wellington Gonçalves Pires
 *
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StringConexao {

    private String server;
    private String nomeBancoDados;
    private String usuario;
    private String senha;
    private String caminho;

    public void lerBancoDados() throws Exception, FileNotFoundException, IOException {

        Properties propriedades = new Properties();

        InputStream projetoCaminho = this.getClass().getResourceAsStream(FuraFilaConstants.NOME_ARQUIVO_BANCO_DADOS);
        propriedades.load(projetoCaminho);

        nomeBancoDados = propriedades.getProperty("banco");
        usuario = propriedades.getProperty("usuario");
        senha = propriedades.getProperty("senha");
        server = propriedades.getProperty("servidor");

        caminho = "jdbc:sqlserver://" + server + ";databaseName=" + nomeBancoDados;

    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getNomeBancoDados() {
        return nomeBancoDados;
    }

    public void setNomeBancoDados(String nomeBancoDados) {
        this.nomeBancoDados = nomeBancoDados;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

}
