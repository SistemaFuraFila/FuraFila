package furaFila.mvc.cliente.bean;

import furaFila.mvc.bairro.business.BairroBusiness;
import furaFila.mvc.bairro.service.BairroService;
import furaFila.mvc.cep.service.CepService;
import furaFila.mvc.cidade.business.CidadeBusiness;
import furaFila.mvc.cidade.service.CidadeService;
import furaFila.mvc.cliente.business.ClienteBusiness;
import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.cliente.service.ClienteService;
import furaFila.mvc.comanda.service.ComandaService;
import furaFila.mvc.imagem.business.ImagemBusiness;
import furaFila.mvc.login.business.LoginBusiness;
import furaFila.mvc.login.model.Login;
import furaFila.mvc.logradouro.business.LogradouroBusiness;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.mvc.logradouro.service.LogradouroService;
import furaFila.mvc.pedidoLocker.model.PedidoLocker;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cliente cliente = new Cliente();

    private CepService cepService = new CepService();
    private CidadeService cidadeService = new CidadeService();
    private BairroService bairroService = new BairroService();
    private LogradouroService logradouroService = new LogradouroService();
    private ClienteService clienteService = new ClienteService();
    private ComandaService comandaService = new ComandaService();

    private ClienteBusiness clienteBusiness = new ClienteBusiness();
    private LoginBusiness loginBusiness = new LoginBusiness();
    private CidadeBusiness cidadeBusiness = new CidadeBusiness();
    private BairroBusiness bairroBusiness = new BairroBusiness();
    private LogradouroBusiness logradouroBusiness = new LogradouroBusiness();
    private ImagemBusiness imagemBusiness = new ImagemBusiness();
    
    private List<PedidoLocker> lstComandaLocker = new ArrayList<>();

    public void popularCliente() {

        try {
            setCliente(getClienteService().buscarDadosBasicosCliente(pegarDadosSessaoCliente()));
        } catch (Exception ex) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void popularComandasNoLocker() {

        try {
            setLstComandaLocker(getComandaService().listarComandasAprovadas(" WHERE C.id_status_FK = " + FuraFilaConstants.COD_PRODUTO_ENTREGUE + " OR C.id_status_FK = " + FuraFilaConstants.COD_ENCAMINHADO_LOCKER));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void cadastrarCliente(ActionEvent ae) {

        try {

            //GRAVAR LOGIN
            getCliente().getLogin().getPermissao().setId_permissao(FuraFilaConstants.CODIGO_PERFIL_3);
            getCliente().getLogin().setStatus(Boolean.TRUE);
            getCliente().getLogin().setDisponivel_entrega(Boolean.FALSE);
            getLoginBusiness().gravar(getCliente().getLogin());

            //GRAVAR IMAGEM
            getImagemBusiness().gravar(getCliente().getImagem());

            FuraFilaUtils.gravarLogradouro(getCliente().getLogradouro());

            //GRAVAR CLIENTE
            getClienteBusiness().gravar(getCliente());

            Login l = getCliente().getLogin();
            setCliente(new Cliente());
            getCliente().setLogin(l);

        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void alterarDadosBasicos(ActionEvent ae) {

        try {
            Cliente c = pegarDadosSessaoCliente();
            getCliente().getImagem().setId_imagem(c.getImagem().getId_imagem());
            getImagemBusiness().alterar(getCliente().getImagem());
            getClienteBusiness().alterarDadosBasicos(getCliente());
        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void alterarDadosAcesso(ActionEvent ae) {

        try {
            getLoginBusiness().alterar(getCliente().getLogin());
        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void procurarCep() {

        try {
            if (0 != getCliente().getLogradouro().getNroCep()) {
                if (!getLogradouroService().logradouroExiste(getCliente().getLogradouro())) {
                    getCepService().pesquisarCep(getCliente().getLogradouro());
                    List<Double> lstCoordenadas = getCepService().pegarGeolocalizacao(getCliente().getLogradouro());
                    getCliente().getLogradouro().setLatitude(lstCoordenadas.get(0));
                    getCliente().getLogradouro().setLongitude(lstCoordenadas.get(1));
                } else {
                    getLogradouroService().buscarEnderecoCompleto(getCliente().getLogradouro());
                }
            } else {
                getCliente().setLogradouro(new Logradouro());
                FuraFilaUtils.growlErro(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.AVISO_CEP_VAZIO);
            }

        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void carregarDados(ActionEvent ae) {

        try {
            setCliente(getClienteService().buscarDadosBasicosCliente(pegarDadosSessaoCliente()));
        } catch (Exception e) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
        }

    }

    private boolean validarTodosCampos() {

        return validarDadosBasicos() || validarUsuario() || validarEndereco();

    }

    private boolean validarDadosBasicos() {

        return FuraFilaUtils.valorVazioNulo(getCliente().getNome())
                || FuraFilaUtils.valorVazioNulo(getCliente().getDataNascimento())
                || FuraFilaUtils.valorVazioNulo(getCliente().getSexo())
                || FuraFilaUtils.valorZerado(getCliente().getCpf())
                || FuraFilaUtils.valorVazioNulo(getCliente().getEmail())
                || FuraFilaUtils.valorZerado(getCliente().getTel_res());

    }

    private boolean validarUsuario() {

        return FuraFilaUtils.valorVazioNulo(getCliente().getLogin().getUsuario())
                || FuraFilaUtils.valorVazioNulo(getCliente().getLogin().getSenha());

    }

    private boolean validarEndereco() {

        return FuraFilaUtils.valorZerado(getCliente().getLogradouro().getNroCep());

    }

    public void importarImagem(FileUploadEvent event) {

        try {

            String ext[] = event.getFile().getFileName().split("\\.");

            String caminho = FuraFilaUtils.montarCaminho(pegarDadosSessaoCliente(), null, false);
            String nomeImagem = FuraFilaUtils.montarNomeImagem(pegarDadosSessaoCliente(), null, false) + "." + ext[ext.length - 1];

            getCliente().getImagem().setImagem(FuraFilaUtils.copiarArquivo(caminho + nomeImagem, event.getFile().getInputstream()));

        } catch (Exception e) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, e.getMessage());
        }

    }

    private Cliente pegarDadosSessaoCliente() {
        return (Cliente) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_CLIENTE);
    }

    public void zerarImagem(ActionEvent ae) {
        getCliente().zerarObjeto();
    }

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteBusiness getClienteBusiness() {
        return clienteBusiness;
    }

    public void setClienteBusiness(ClienteBusiness clienteBusiness) {
        this.clienteBusiness = clienteBusiness;
    }

    public LoginBusiness getLoginBusiness() {
        return loginBusiness;
    }

    public void setLoginBusiness(LoginBusiness loginBusiness) {
        this.loginBusiness = loginBusiness;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    public CidadeBusiness getCidadeBusiness() {
        return cidadeBusiness;
    }

    public void setCidadeBusiness(CidadeBusiness cidadeBusiness) {
        this.cidadeBusiness = cidadeBusiness;
    }

    public BairroBusiness getBairroBusiness() {
        return bairroBusiness;
    }

    public void setBairroBusiness(BairroBusiness bairroBusiness) {
        this.bairroBusiness = bairroBusiness;
    }

    public BairroService getBairroService() {
        return bairroService;
    }

    public void setBairroService(BairroService bairroService) {
        this.bairroService = bairroService;
    }

    public LogradouroService getLogradouroService() {
        return logradouroService;
    }

    public void setLogradouroService(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    public LogradouroBusiness getLogradouroBusiness() {
        return logradouroBusiness;
    }

    public void setLogradouroBusiness(LogradouroBusiness logradouroBusiness) {
        this.logradouroBusiness = logradouroBusiness;
    }

    public ClienteService getClienteService() {
        return clienteService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public ImagemBusiness getImagemBusiness() {
        return imagemBusiness;
    }

    public void setImagemBusiness(ImagemBusiness imagemBusiness) {
        this.imagemBusiness = imagemBusiness;
    }

    public List<PedidoLocker> getLstComandaLocker() {
        popularComandasNoLocker();
        return lstComandaLocker;
    }

    public void setLstComandaLocker(List<PedidoLocker> lstComandaLocker) {
        this.lstComandaLocker = lstComandaLocker;
    }

    public ComandaService getComandaService() {
        return comandaService;
    }

    public void setComandaService(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

}
