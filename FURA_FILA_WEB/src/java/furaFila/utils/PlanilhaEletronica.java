/**
 *
 * @author: Wellington Gon�alves Pires - Foursys
 *
 *
 */
package furaFila.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public abstract class PlanilhaEletronica {

    protected int indiceLinha = 0;

    protected HSSFWorkbook workBook = new HSSFWorkbook();
    protected HSSFSheet planilha;

    protected Cell celula;
    protected Row linha;

    protected FileOutputStream saida;

    protected HSSFPalette palette;
    protected HSSFFont cambriaTitulo;
    protected HSSFFont cambriaCabecalho;
    protected HSSFFont calibriCabecalho;
    protected CellStyle bordaPadrao;
    
    protected CellStyle padrao1;
    protected CellStyle padrao2;
    protected CellStyle padrao3;

    protected CellStyle estiloTitulo;
    protected CellStyle estiloCabecalho;
    protected CellStyle estiloRegistros;
    
    protected String nomeArquivo;

    public PlanilhaEletronica(String nomePlanilha, String nomeArquivo) {

        setPlanilha(getWorkBook().createSheet(nomePlanilha));

        this.nomeArquivo = nomeArquivo;
        
        inicializar();

        }
    
    private void inicializar(){
        
        /* =================FORMATAÇÕES====================== */
        setPalette(getWorkBook().getCustomPalette());

        getPalette().setColorAtIndex((byte) 41, (byte) 0, (byte) 102, (byte) 255);

        setCambriaTitulo(getWorkBook().createFont());
        getCambriaTitulo().setFontName("Cambria");
        getCambriaTitulo().setColor(HSSFColor.WHITE.index);
        getCambriaTitulo().setFontHeightInPoints((short) 15);
        getCambriaTitulo().setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        setCambriaCabecalho(getWorkBook().createFont());
        getCambriaCabecalho().setFontName("Cambria");
        getCambriaCabecalho().setColor(HSSFColor.WHITE.index);
        getCambriaCabecalho().setFontHeightInPoints((short) 12);
        getCambriaCabecalho().setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        setCalibriCabecalho(getWorkBook().createFont());
        getCalibriCabecalho().setFontName("Calibri");
        getCalibriCabecalho().setColor(HSSFColor.BLACK.index);
        getCalibriCabecalho().setFontHeightInPoints((short) 10);
        
        
        setBordaPadrao(getWorkBook().createCellStyle());
        getBordaPadrao().setBorderLeft(HSSFCellStyle.BORDER_THIN);
        getBordaPadrao().setBorderRight(HSSFCellStyle.BORDER_THIN);
        getBordaPadrao().setBorderTop(HSSFCellStyle.BORDER_THIN);
        getBordaPadrao().setBorderBottom(HSSFCellStyle.BORDER_THIN);
        
        
        
        setPadrao1(getWorkBook().createCellStyle());
        getPadrao1().cloneStyleFrom(getBordaPadrao());
        getPadrao1().setFont(getCambriaTitulo());
        getPadrao1().setFillForegroundColor(getPalette().getColor(41).getIndex());
        getPadrao1().setFillPattern(CellStyle.SOLID_FOREGROUND);
        getPadrao1().setAlignment(CellStyle.ALIGN_CENTER);
        
        setPadrao2(getWorkBook().createCellStyle());
        getPadrao2().cloneStyleFrom(getBordaPadrao());
        getPadrao2().setFont(getCambriaCabecalho());
        getPadrao2().setFillForegroundColor(getPalette().getColor(41).getIndex());
        getPadrao2().setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        setPadrao3(getWorkBook().createCellStyle());
        getPadrao3().cloneStyleFrom(getBordaPadrao());
        getPadrao3().setFont(getCalibriCabecalho());
        
        
        setEstiloTitulo(getWorkBook().createCellStyle());
        getEstiloTitulo().cloneStyleFrom(getPadrao1());
        
        setEstiloCabecalho(getWorkBook().createCellStyle());
        getEstiloCabecalho().cloneStyleFrom(getPadrao2());
        
        setEstiloRegistros(getWorkBook().createCellStyle());
        getEstiloRegistros().cloneStyleFrom(getPadrao3());
        
    }

    public void criaTitulo(int indiceCelula, Object valor, CellStyle estilo)
            throws NullPointerException {

        criaLinha(indiceLinha++);

        criaCelula(indiceCelula, valor, estilo);

    }

    public void criaCabecalho(HashMap<Integer, Object> cabecalho,
            CellStyle estilo) throws NullPointerException {

        criaLinha(indiceLinha++);

        for (Integer chave : cabecalho.keySet()) {

            criaCelula(chave, String.valueOf(cabecalho.get(chave)), estilo);

        }

    }

    public void criaRegistros(
            HashMap<Integer, HashMap<Integer, Object>> registro,
            CellStyle estilo) throws NullPointerException {

        for (Integer chave : registro.keySet()) {

            criaRegistro(registro.get(chave), estilo);

        }

    }

    public void criaRegistro(HashMap<Integer, Object> registro, CellStyle estilo)
            throws NullPointerException {

        criaLinha(indiceLinha++);

        for (Integer chave : registro.keySet()) {

            criaCelula(chave, registro.get(chave), estilo);

        }

    }

    public void criarCelulaPersonalizada(int indexCelula, Object valor,
            CellStyle estilo) throws NullPointerException {

        criarCelulaComIndice(indexCelula, indiceLinha++, valor, estilo);

    }

    public void criarCelulaComIndice(int indexCelula, int indexLinha,
            Object valor, CellStyle estilo) throws NullPointerException {

        criaLinha(indexLinha);

        criaCelula(indexCelula, valor, estilo);

    }

    public void criaLinha(int indexLinha) throws NullPointerException {

        setLinha(getPlanilha().createRow(indexLinha));

    }

    public void criaCelula(int indexCelula, Object valor, CellStyle estilo)
            throws NullPointerException {

        setCelula(getLinha().createCell(indexCelula));

        if (valor instanceof String) {

            getCelula().setCellValue(String.valueOf(valor));

        } else if (valor instanceof Integer) {

            getCelula().setCellValue(Integer.parseInt(String.valueOf(valor)));

        } else if (valor instanceof Double) {

            getCelula().setCellValue(Double.valueOf(String.valueOf(valor)));

        } else if (valor instanceof Boolean) {

            getCelula().setCellValue(Boolean.valueOf(String.valueOf(valor)));

        } else if (valor instanceof BigDecimal) {

            getCelula().setCellValue(((BigDecimal) valor).doubleValue());

        } else if (valor instanceof Date) {

            DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

            getCelula().setCellValue(dataFormatada.format(valor));

        }

        if (estilo != null) {
            getCelula().setCellStyle(estilo);
        }

    }

    public void montarFormula(Object coluna, int indexCelula, CellStyle estilo)
            throws NullPointerException {

        String formula = "SUM(" + coluna + "8" + ":" + coluna
                + (indiceLinha - 1) + ")";

        criaFormula(indexCelula, formula, estilo);

    }

    public void criaFormula(int indexCelula, String formula, CellStyle estilo)
            throws NullPointerException {

        setCelula(getLinha().createCell(indexCelula));
        getCelula().setCellFormula(formula);

        if (estilo != null) {
            getCelula().setCellStyle(estilo);
        }

    }

    public void mesclarCelula(int primeiraLinha, int ultimaLinha,
            int primeiraColuna, int ultimaColuna) throws NullPointerException {

        getPlanilha().addMergedRegion(
                new CellRangeAddress(primeiraLinha, ultimaLinha,
                        primeiraColuna, ultimaColuna));

        CellRangeAddress range = new CellRangeAddress(primeiraLinha,
                ultimaLinha, primeiraColuna, ultimaColuna);

        RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, range, getPlanilha(),
                getWorkBook());
        RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, range, getPlanilha(),
                getWorkBook());
        RegionUtil.setBorderRight(CellStyle.BORDER_THIN, range, getPlanilha(),
                getWorkBook());
        RegionUtil.setBorderTop(CellStyle.BORDER_THIN, range, getPlanilha(),
                getWorkBook());

    }

    public void tamanhoDeColuna(int indiceColuna, int tamanho) throws Exception {

        getPlanilha().setColumnWidth(indiceColuna, tamanho);

    }

    public void centralizarCelula(int linha, int celula) {

        CellStyle centralizado = getWorkBook().createCellStyle();
        //centralizado.cloneStyleFrom(getEstiloRegistros());
        centralizado.setAlignment(CellStyle.ALIGN_CENTER);

        getPlanilha().getRow(linha).getCell(celula).setCellStyle(centralizado);

    }

    public void efetuarDownload() throws IOException {
        
        
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        getWorkBook().write(outByteStream);

        byte[] bytes = outByteStream.toByteArray();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();

        ServletOutputStream ouputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");// octet-stream
        response.addHeader("Content-disposition", "attachment;filename="
                + nomeArquivo + ".xls");
        response.setContentLength(bytes.length);

        // Esse popula o arquivo que será aberto ou salvo
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
        response.flushBuffer();
        facesContext.responseComplete();

    }

    public void gravarXLS() throws IOException {

        getPlanilha().autoSizeColumn(0);
        getPlanilha().autoSizeColumn(1);
        getPlanilha().autoSizeColumn(2);
        getPlanilha().autoSizeColumn(3);
        getPlanilha().autoSizeColumn(4);

        setSaida(new FileOutputStream(new File(
                "C:/Users/wpires/Desktop/Planilhas/planilha1.xls")));

        getWorkBook().write(getSaida());
        getSaida().close();

    }

    public int retornaLinhaAtual() {

        return indiceLinha;

    }

    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    public HSSFSheet getPlanilha() {
        return planilha;
    }

    public void setPlanilha(HSSFSheet planilha) {
        this.planilha = planilha;
    }

    public Cell getCelula() {
        return celula;
    }

    public void setCelula(Cell celula) {
        this.celula = celula;
    }

    public Row getLinha() {
        return linha;
    }

    public void setLinha(Row linha) {
        this.linha = linha;
    }

    public FileOutputStream getSaida() {
        return saida;
    }

    public void setSaida(FileOutputStream saida) {
        this.saida = saida;
    }

    public int getIndiceLinha() {
        return indiceLinha;
    }

    public void setIndiceLinha(int indiceLinha) {
        this.indiceLinha = indiceLinha;
    }

    public HSSFPalette getPalette() {
        return palette;
    }

    public void setPalette(HSSFPalette palette) {
        this.palette = palette;
    }

    public CellStyle getBordaPadrao() {
        return bordaPadrao;
    }

    public void setBordaPadrao(CellStyle bordaPadrao) {
        this.bordaPadrao = bordaPadrao;
    }

    public HSSFFont getCambriaTitulo() {
        return cambriaTitulo;
    }

    public void setCambriaTitulo(HSSFFont cambriaTitulo) {
        this.cambriaTitulo = cambriaTitulo;
    }

    public HSSFFont getCambriaCabecalho() {
        return cambriaCabecalho;
    }

    public void setCambriaCabecalho(HSSFFont cambriaCabecalho) {
        this.cambriaCabecalho = cambriaCabecalho;
    }

    public CellStyle getPadrao1() {
        return padrao1;
    }

    public void setPadrao1(CellStyle padrao1) {
        this.padrao1 = padrao1;
    }

    public CellStyle getEstiloTitulo() {
        return estiloTitulo;
    }

    public void setEstiloTitulo(CellStyle estiloTitulo) {
        this.estiloTitulo = estiloTitulo;
    }

    public CellStyle getEstiloCabecalho() {
        return estiloCabecalho;
    }

    public void setEstiloCabecalho(CellStyle estiloCabecalho) {
        this.estiloCabecalho = estiloCabecalho;
    }

    public CellStyle getPadrao2() {
        return padrao2;
    }

    public void setPadrao2(CellStyle padrao2) {
        this.padrao2 = padrao2;
    }

    public HSSFFont getCalibriCabecalho() {
        return calibriCabecalho;
    }

    public void setCalibriCabecalho(HSSFFont calibriCabecalho) {
        this.calibriCabecalho = calibriCabecalho;
    }

    public CellStyle getPadrao3() {
        return padrao3;
    }

    public void setPadrao3(CellStyle padrao3) {
        this.padrao3 = padrao3;
    }

    public CellStyle getEstiloRegistros() {
        return estiloRegistros;
    }

    public void setEstiloRegistros(CellStyle estiloRegistros) {
        this.estiloRegistros = estiloRegistros;
    }

}
