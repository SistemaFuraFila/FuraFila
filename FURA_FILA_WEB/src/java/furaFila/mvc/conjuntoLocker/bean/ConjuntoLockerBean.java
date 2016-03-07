package furaFila.mvc.conjuntoLocker.bean;

import furaFila.mvc.cep.service.CepService;
import furaFila.mvc.conjuntoLocker.business.ConjuntoLockerBusiness;
import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.conjuntoLocker.service.ConjuntoLockerService;
import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.locker.business.LockerBusiness;
import furaFila.mvc.locker.model.Locker;
import furaFila.mvc.locker.service.LockerService;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.mvc.logradouro.service.LogradouroService;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class ConjuntoLockerBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();
    private Locker locker = new Locker();

    private ConjuntoLockerBusiness conjuntoLockerBusiness = new ConjuntoLockerBusiness();
    private LockerBusiness lockerBusiness = new LockerBusiness();

    private LogradouroService logradouroService = new LogradouroService();
    private CepService cepService = new CepService();
    private ConjuntoLockerService conjuntoLockerService = new ConjuntoLockerService();
    private LockerService lockerService = new LockerService();

    private List<ConjuntoLocker> lstConjuntoLockers = new ArrayList<>();
    private List<Locker> lstLockers = new ArrayList<>();

    private int qtdLockerPequeno;
    private int qtdLockerMedio;
    private int qtdLockerGrande;
    private int qtdeLockers;
    private Boolean flgBotoes = true;

    public void popularConjuntoLockers() {

        try {
            if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                setLstConjuntoLockers(getConjuntoLockerService().listarConjuntoLocker());
            }
        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void popularLockerPorConjunto(ActionEvent ae) {

        try {
            setLstLockers(getConjuntoLockerService().listarLockerPorConjunto(getConjuntoLocker()));
        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void alterarStatus(Locker locker) {

        try {
            locker.setBolStatus(!locker.getBolStatus());
        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public String nomeBotao(Locker locker){
        return locker.getBolStatus() ? FuraFilaConstants.DESATIVAR : FuraFilaConstants.ATIVAR;
    }
    
    public void alterar(){

        try {

            FuraFilaUtils.gravarLogradouro(getConjuntoLocker().getLogradouro());

            getConjuntoLockerBusiness().alterar(getConjuntoLocker());
            
            for(Locker l : getLstLockers()){
                getLockerBusiness().alterar(l);
            }
            
            popularConjuntoLockers();

        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }
    
    public void gravar(ActionEvent ae) {

        try {

            FuraFilaUtils.gravarLogradouro(getConjuntoLocker().getLogradouro());

            getConjuntoLockerBusiness().gravar(getConjuntoLocker());

            Locker lockerUnd;

            if (getQtdLockerPequeno() != 0) {
                for (Integer i = 0; i < getQtdLockerPequeno(); i++) {

                    lockerUnd = new Locker();
                    lockerUnd.setLocker_desc(String.valueOf((i + 1)));
                    lockerUnd.setConjuntoLocker(getConjuntoLocker());
                    lockerUnd.getDimensao().setId_dimensao(FuraFilaConstants.COD_TIPO_DIMENSAO_LOCKER_PEQUENO);
                    lockerUnd.getStatus().setId_status(FuraFilaConstants.COD_LOCKER_LIVRE);

                    getLockerBusiness().gravar(lockerUnd);
                }
            }

            if (getQtdLockerMedio() != 0) {
                for (Integer i = 0; i < getQtdLockerMedio(); i++) {

                    lockerUnd = new Locker();
                    lockerUnd.setLocker_desc(String.valueOf((i + 1)));
                    lockerUnd.setConjuntoLocker(getConjuntoLocker());
                    lockerUnd.getDimensao().setId_dimensao(FuraFilaConstants.COD_TIPO_DIMENSAO_LOCKER_MEDIO);
                    lockerUnd.getStatus().setId_status(FuraFilaConstants.COD_LOCKER_LIVRE);

                    getLockerBusiness().gravar(lockerUnd);
                }
            }

            if (getQtdLockerGrande() != 0) {
                for (Integer i = 0; i < getQtdLockerGrande(); i++) {

                    lockerUnd = new Locker();
                    lockerUnd.setLocker_desc(String.valueOf((i + 1)));
                    lockerUnd.setConjuntoLocker(getConjuntoLocker());
                    lockerUnd.getDimensao().setId_dimensao(FuraFilaConstants.COD_TIPO_DIMENSAO_LOCKER_GRANDE);
                    lockerUnd.getStatus().setId_status(FuraFilaConstants.COD_LOCKER_LIVRE);

                    getLockerBusiness().gravar(lockerUnd);
                }
            }

            popularConjuntoLockers();

        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void procurarCep() {

        try {
            if (0 != getConjuntoLocker().getLogradouro().getNroCep()) {
                if (!getLogradouroService().logradouroExiste(getConjuntoLocker().getLogradouro())) {
                    getCepService().pesquisarCep(getConjuntoLocker().getLogradouro());
                    List<Double> lstCoordenadas = getCepService().pegarGeolocalizacao(getConjuntoLocker().getLogradouro());
                    getConjuntoLocker().getLogradouro().setLatitude(lstCoordenadas.get(0));
                    getConjuntoLocker().getLogradouro().setLongitude(lstCoordenadas.get(1));
                } else {
                    getLogradouroService().buscarEnderecoCompleto(getConjuntoLocker().getLogradouro());
                }
            } else {
                getConjuntoLocker().setLogradouro(new Logradouro());
                FuraFilaUtils.growlErro(FuraFilaConstants.AVISO_GROWL_TITULO, FuraFilaConstants.AVISO_CEP_VAZIO);
            }

        } catch (Exception e) {
            FuraFilaUtils.growlErro(FuraFilaConstants.ERRO_GROWL_TITULO, e.getMessage());
        }

    }

    public void alterarStatus(ActionEvent ae) {

        try {
            getConjuntoLocker().setStatus(!getConjuntoLocker().getStatus());

            getConjuntoLockerBusiness().alterarStatus(getConjuntoLocker());

            popularConjuntoLockers();

        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public String nomeBotarAtivarDesativar() {
        if (null == getConjuntoLocker().getStatus()) {
            return FuraFilaConstants.ATIVAR;
        }
        return getConjuntoLocker().getStatus() ? FuraFilaConstants.DESATIVAR : FuraFilaConstants.ATIVAR;
    }

    public Estabelecimento pegarSessaoEstabelecimento() {
        return (Estabelecimento) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_ESTABELECIMENTO);
    }

    public void ativarBotoes(SelectEvent event) {
        manipularBotoes(false);
    }

    public void desativarBotoes(UnselectEvent event) {
        manipularBotoes(true);
    }

    private void manipularBotoes(boolean status) {
        setFlgBotoes(status);
    }

    public ConjuntoLocker getConjuntoLocker() {
        return conjuntoLocker;
    }

    public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
        if (conjuntoLocker != null) {
            this.conjuntoLocker = conjuntoLocker;
        }
    }

    public int getQtdLockerPequeno() {
        return qtdLockerPequeno;
    }

    public void setQtdLockerPequeno(int qtdLockerPequeno) {
        this.qtdLockerPequeno = qtdLockerPequeno;
    }

    public int getQtdLockerMedio() {
        return qtdLockerMedio;
    }

    public void setQtdLockerMedio(int qtdLockerMedio) {
        this.qtdLockerMedio = qtdLockerMedio;
    }

    public int getQtdLockerGrande() {
        return qtdLockerGrande;
    }

    public void setQtdLockerGrande(int qtdLockerGrande) {
        this.qtdLockerGrande = qtdLockerGrande;
    }

    public LogradouroService getLogradouroService() {
        return logradouroService;
    }

    public void setLogradouroService(LogradouroService logradouroService) {
        this.logradouroService = logradouroService;
    }

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
    }

    public ConjuntoLockerBusiness getConjuntoLockerBusiness() {
        return conjuntoLockerBusiness;
    }

    public void setConjuntoLockerBusiness(ConjuntoLockerBusiness conjuntoLockerBusiness) {
        this.conjuntoLockerBusiness = conjuntoLockerBusiness;
    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

    public ConjuntoLockerService getConjuntoLockerService() {
        return conjuntoLockerService;
    }

    public void setConjuntoLockerService(ConjuntoLockerService conjuntoLockerService) {
        this.conjuntoLockerService = conjuntoLockerService;
    }

    public List<ConjuntoLocker> getLstConjuntoLockers() {
        return lstConjuntoLockers;
    }

    public void setLstConjuntoLockers(List<ConjuntoLocker> lstConjuntoLockers) {
        this.lstConjuntoLockers = lstConjuntoLockers;
    }

    public Boolean getFlgBotoes() {
        return flgBotoes;
    }

    public void setFlgBotoes(Boolean flgBotoes) {
        this.flgBotoes = flgBotoes;
    }

    public List<Locker> getLstLockers() {
        return lstLockers;
    }

    public void setLstLockers(List<Locker> lstLockers) {
        this.lstLockers = lstLockers;
    }

    public LockerService getLockerService() {
        return lockerService;
    }

    public void setLockerService(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public int getQtdeLockers() {
        return qtdeLockers;
    }

    public void setQtdeLockers(int qtdeLockers) {
        this.qtdeLockers = qtdeLockers;
    }

}
