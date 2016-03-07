package furaFila.utils;

import furaFila.mvc.bairro.business.BairroBusiness;
import furaFila.mvc.bairro.model.Bairro;
import furaFila.mvc.bairro.service.BairroService;
import furaFila.mvc.cep.model.Distancia;
import furaFila.mvc.cep.service.CepService;
import furaFila.mvc.cidade.business.CidadeBusiness;
import furaFila.mvc.cidade.model.Cidade;
import furaFila.mvc.cidade.service.CidadeService;
import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import furaFila.mvc.estoqueProdutos.model.EstoqueProdutos;
import furaFila.mvc.logradouro.business.LogradouroBusiness;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.mvc.logradouro.service.LogradouroService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @Author: Wellington Gonçalves Pires
 */
public class FuraFilaUtils {

    public static void passarDadosSessao(String nomeSessao, Object dadosSessao) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute(nomeSessao, dadosSessao);

    }

    public static Object pegarDadosSessao(String nomeSessao) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        return session.getAttribute(nomeSessao);

    }

    public static Object pegarValoresParametro(String nomeParametro) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getRequestParameterMap().get(nomeParametro);

    }

    public static String[] converterMatrizVetor(String[][] matriz) {

        String[] vetor = new String[matriz.length];

        int index = 0;
        for (String[] valor : matriz) {

            vetor[index++] = valor[0];

        }

        return vetor;

    }

    public static boolean valorVazioNulo(Object valor) {
        return valor == null || "".equals(valor.toString());
    }

    public static boolean valorZerado(Object valor) {

        if (valor instanceof Double) {
            return Double.parseDouble(String.valueOf(valor)) == 0.0 || Double.parseDouble(String.valueOf(valor)) < 0.0;
        } else if (valor instanceof Float) {
            return Float.parseFloat(String.valueOf(valor)) == 0.0 || Float.parseFloat(String.valueOf(valor)) < 0.0;
        } else if (valor instanceof Integer) {
            return Integer.valueOf(String.valueOf(valor)) == 0 || Integer.valueOf(String.valueOf(valor)) < 0;
        } else if (valor instanceof Long) {
            return Long.valueOf(String.valueOf(valor)) == 0 || Long.valueOf(String.valueOf(valor)) < 0;
        }

        return true;

    }

    public static void executarJavascript(String comando) {
        RequestContext.getCurrentInstance().execute(comando);
    }

    private static void aparecerGrowl(Severity severity, String titulo, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, titulo, mensagem));
    }

    public static void growlErro(String titulo, String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
    }

    public static void growlFatal(String titulo, String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_FATAL, titulo, mensagem);
    }

    public static void growlInfo(String titulo, String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_INFO, titulo, mensagem);
    }

    public static void growlAviso(String titulo, String mensagem) {
        aparecerGrowl(FacesMessage.SEVERITY_WARN, titulo, mensagem);
    }

    public static String formatarMoeda(Object valor) {

        String valorFormatado = "";

        if (valor instanceof BigDecimal || valor instanceof Double || valor instanceof Integer) {
            valorFormatado = FuraFilaConstants.PADRAO_MONETARIO_PT_BR + new DecimalFormat(FuraFilaConstants.PADRAO_MONETARIO).format(new BigDecimal(String.valueOf(valor)).doubleValue());
        }

        return valorFormatado;

    }

    public static boolean vetorVazioNulo(Object[] dados) {

        Boolean vetorNulo = true;

        if (dados != null) {
            for (Object valor : dados) {
                if (valor != null && !"".equals(valor)) {
                    return false;
                }
            }
        } else {
            vetorNulo = true;
        }

        return vetorNulo;
    }

    public static boolean matrizVazioNula(Object[][] dados) {

        Boolean matrizNula = false;

        if (dados != null) {
            for (Object valor[] : dados) {
                for (Object valorInterno : valor) {
                    matrizNula = (valorInterno == null || "".equals(valorInterno));
                }
            }
        } else {
            matrizNula = true;
        }

        return matrizNula;
    }

    public static boolean listaDuplaVaziaNula(List<List<String>> lstDados) {

        Boolean listaNulaVazia = false;

        for (List<?> lstValores : lstDados) {

            if (listaVaziaNula(lstValores)) {
                listaNulaVazia = true;
            } else {
                return false;
            }

        }

        return listaNulaVazia;

    }

    public static boolean listaVaziaNula(List<?> lstDados) {

        Integer qtdNulaVazia = 0;

        if (lstDados != null) {
            for (Object valor : lstDados) {
                if (valor == null || "".equals(valor)) {
                    qtdNulaVazia++;
                }
            }
        } else {
            return true;
        }

        return qtdNulaVazia == lstDados.size();
    }

    public static String formataCpf(Object valor) throws Exception {

        return formataValores(FuraFilaConstants.PADRAO_CPF, valor);
    }

    public static String formataCnpj(Object valor) throws Exception {

        return formataValores(FuraFilaConstants.PADRAO_CNPJ, valor);
    }

    public static String formataCep(Object valor) throws Exception {

        return formataValores(FuraFilaConstants.PADRAO_CEP, valor);
    }

    public static String formataTelefone(Object valor) throws Exception {

        return formataValores(FuraFilaConstants.PADRAO_TELEFONE, valor);

    }

    public static String formataCelular(Object valor) throws Exception {

        return formataValores(FuraFilaConstants.PADRAO_CELULAR_BRASIL, valor);

    }

    public static String formataValores(String padrao, Object valor) throws ParseException, Exception {
        MaskFormatter mascara = new MaskFormatter(padrao);
        mascara.setValueContainsLiteralCharacters(false);
        return mascara.valueToString(valor);
    }

    public static String formataDataSQL(Date data) {

        return formataData(data, FuraFilaConstants.PADRAO_DATA_HORA_SQL);

    }

    public static String formataDataExibicao(Date data) {

        return formataData(data, FuraFilaConstants.PADRAO_DATA_HORA_EXIBICAO);

    }

    public static String formataData(Date data, String padrao) {

        return new SimpleDateFormat(padrao).format(data);

    }

    public static Date formataDataDate(String data) throws ParseException {

        return new SimpleDateFormat(FuraFilaConstants.PADRAO_DATA_SQL).parse(data);

    }

    public static String semImagem(Character sexo) {
        return sexo == FuraFilaConstants.COD_MASCULINO ? FuraFilaConstants.SEM_IMAGEM_MASCULINO : FuraFilaConstants.SEM_IMAGEM_FEMININO;
    }

    public static String semImagem(Estabelecimento estabelecimento) {
        return FuraFilaUtils.valorVazioNulo(estabelecimento.getImagem().getImagem()) ? FuraFilaConstants.SEM_IMAGEM_ESTABELECIMENTO : estabelecimento.getImagem().getImagem();
    }

    public static void gravarLogradouro(Logradouro logradouro) throws Exception {

        CidadeBusiness cidadeBusiness = new CidadeBusiness();
        BairroBusiness bairroBusiness = new BairroBusiness();
        LogradouroBusiness logradouroBusiness = new LogradouroBusiness();

        CidadeService cidadeService = new CidadeService();
        BairroService bairroService = new BairroService();
        LogradouroService logradouroService = new LogradouroService();

        //GRAVAR CIDADE
        Cidade cidade = cidadeService.buscarCidade(logradouro.getBairro().getCidade());

        if (cidade.objetoVazio()) {
            cidadeBusiness.gravar(logradouro.getBairro().getCidade());
        }

        //GRAVAR BAIRRO
        Bairro bairro = bairroService.buscarBairro(logradouro.getBairro());

        if (bairro.objetoVazio()) {
            bairroBusiness.gravar(logradouro.getBairro());
        }

        //GRAVAR LOGRADOURO
        Logradouro l = logradouroService.buscarLogradouro(logradouro);

        if (l.objetoVazio()) {
            logradouroBusiness.gravar(logradouro);
        }
    }

    public static String copiarArquivo(String arquivo, InputStream is) throws Exception {

        OutputStream os = new FileOutputStream(new File(arquivo));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }

        is.close();
        os.flush();
        os.close();

        return arquivo;

    }

    public static String montarCaminho(Cliente cliente, EstabelecimentoLogin lojista, boolean isProduto) throws Exception {

        String caminhoFormado = FuraFilaConstants.UNIDADE + FuraFilaConstants.DIRETORIO_IMAGENS;
        String barraDupla = "//";

        String nome;
        String perfil = "";
        String identificador = "";

        if (cliente != null) {

            nome = cliente.getNome().replace(" ", "_");
            perfil = cliente.getLogin().getPermissao().getPermissao();
            identificador = cliente.getId_cliente() + "_" + nome;

            caminhoFormado += perfil + barraDupla + identificador + barraDupla + FuraFilaConstants.DIRETORIO_IMAGENS_PERFIL;
        } else if (lojista != null) {

            nome = lojista.getEstabelecimento().getRazao_social().replace(" ", "_");
            perfil = lojista.getLogin().getPermissao().getPermissao();
            identificador = lojista.getEstabelecimento().getId_estabelecimento() + "_" + nome;

            caminhoFormado += perfil + barraDupla + identificador + barraDupla + (isProduto ? FuraFilaConstants.DIRETORIO_IMAGENS_PRODUTOS : FuraFilaConstants.DIRETORIO_IMAGENS_PERFIL);
        }

        new File(caminhoFormado).mkdirs();

        return caminhoFormado;
    }

    public static String montarNomeImagem(Cliente cliente, EstoqueProdutos lojista, boolean isProduto) throws Exception {

        //PERFIL 'CODIGO'_'NOME'
        //PRODUTO 'CODIGO_ESTABELECIMENTO'_'NOME_PRODUTO'
        String nomeImagem = "";

        if (cliente != null) {
            nomeImagem = cliente.getId_cliente() + "_" + cliente.getNome().replace(" ", "_");
        } else if (lojista != null) {

            if (isProduto) {
                nomeImagem = lojista.getEstoque().getEstabelecimento().getId_estabelecimento() + "_" + lojista.getProduto().getProduto_desc().replace(" ", "_");
            } else {
                nomeImagem = lojista.getEstoque().getEstabelecimento().getId_estabelecimento() + "_" + lojista.getEstoque().getEstabelecimento().getRazao_social().replace(" ", "_");
            }

        }

        return nomeImagem;

    }

    public static Integer gerarNumeroAleatorio() throws Exception {
        return new Random().nextInt(999999999);
    }

    public static String criptografarTexto(String textoPlano) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(textoPlano.getBytes("ISO-8859-1")));

        return String.format("%32x", hash);
    }

    public static void calcularDistancia(Distancia distancia) throws Exception {
        new CepService().calcularDistancia(distancia);
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static List<String> separarTipoLogradouro(String logradouroFormatado) {

        List<String> lstLogradouro = new ArrayList<>();
        String tipoLogradouro = logradouroFormatado.split(" ")[0];
        logradouroFormatado = logradouroFormatado.substring(tipoLogradouro.length(), logradouroFormatado.length()).trim();
        lstLogradouro.add(tipoLogradouro);
        lstLogradouro.add(logradouroFormatado);

        return lstLogradouro;

    }

    public static Integer calcularVolume(Integer altura, Integer largura, Integer profundidade) {
        return altura * largura * profundidade;
    }

    public static Integer gerarCodigoVenda() {
        Integer codigo = new Random(System.nanoTime()).nextInt();
        return codigo < 0 ? codigo * -1 : codigo;
    }

    public static void gerarRelatorios(String nomeJasper, List<?> lstValores, String nomeRelatorio) throws JRException, IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();

        Hashtable<String, Object> parametros = new Hashtable<>();
        parametros.put("APP_PATH", FacesContext.getCurrentInstance().getExternalContext().getRealPath(""));
        parametros.put("PATH_IMAGE", servletContext.getRealPath("/resources/FuraFilaLogo1.png"));

        String relatorio = servletContext.getRealPath("/relatorios/" + nomeJasper + ".jasper");

        JRBeanCollectionDataSource jrds = new JRBeanCollectionDataSource(lstValores);

        JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrds);
        byte[] bytes = JasperExportManager.exportReportToPdf(impressao);

        // Essa é a configuração do pop´up que será apresentado
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        ServletOutputStream ouputStream = response.getOutputStream();
        response.addHeader("Content-disposition", "attachment;filename=" + nomeRelatorio + ".pdf");
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);

        // Esse popula o arquivo que será aberto ou salvo
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
        response.flushBuffer();
        context.responseComplete();

    }

    public static String lerArquivo(String email) throws FileNotFoundException, IOException {
        return IOUtils.toString(new FileInputStream(email));
    }

    public static int calculaIdade(String dataNasc) throws ParseException {

        DateFormat sdf = new SimpleDateFormat(FuraFilaConstants.PADRAO_DATA_EXIBICAO);
        Date dataNascInput = sdf.parse(dataNasc);
        
        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.setTime(dataNascInput);
        
        Calendar hoje = Calendar.getInstance();
        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        dataNascimento.add(Calendar.YEAR, idade);
        if (hoje.before(dataNascimento)) {
            idade--;
        }

        return idade;
    }
    
    public static int calculaIdade(Date dataNasc) throws ParseException {

        Calendar dataNascimento = new GregorianCalendar();
        dataNascimento.setTime(dataNasc);
        
        Calendar hoje = Calendar.getInstance();
        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
        dataNascimento.add(Calendar.YEAR, idade);
        if (hoje.before(dataNascimento)) {
            idade--;
        }

        return idade;
    }
}
