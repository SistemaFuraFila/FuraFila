package furaFila.mvc.locker.bean;

import furaFila.mvc.bairro.business.BairroBusiness;
import furaFila.mvc.bairro.service.BairroService;
import furaFila.mvc.cep.service.CepService;
import furaFila.mvc.cidade.business.CidadeBusiness;
import furaFila.mvc.cidade.service.CidadeService;
import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.conjuntoLocker.service.ConjuntoLockerService;
import furaFila.mvc.dimensao.business.DimensaoBusiness;
import furaFila.mvc.locker.business.LockerBusiness;
import furaFila.mvc.locker.model.Locker;
import furaFila.mvc.locker.service.LockerService;
import furaFila.mvc.logradouro.business.LogradouroBusiness;
import furaFila.mvc.logradouro.service.LogradouroService;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@ViewScoped
public class LockerBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private ConjuntoLocker conjuntoLocker = new ConjuntoLocker();

    private LockerBusiness lockerBusiness = new LockerBusiness();
    private CidadeBusiness cidadeBusiness = new CidadeBusiness();
    private BairroBusiness bairroBusiness = new BairroBusiness();
    private LogradouroBusiness logradouroBusiness = new LogradouroBusiness();
    private DimensaoBusiness dimensaoBusiness = new DimensaoBusiness();

    private LockerService lockerService = new LockerService();
    private CepService cepService = new CepService();
    private CidadeService cidadeService = new CidadeService();
    private BairroService bairroService = new BairroService();
    private LogradouroService logradouroService = new LogradouroService();
    private ConjuntoLockerService conjuntoLockerService = new ConjuntoLockerService();

    private List<Locker> lstLockers = new ArrayList<>();

    public void gerarRelatorio(ActionEvent ae) {

        try {

            Cliente cliente = pegarDadosSessaoCliente().clonar();

            FuraFilaUtils.gerarRelatorios("lockersProximos", getConjuntoLockerService().listarLockersProximos(cliente), "LOCKERS_PROXIMOS");

        } catch (JRException ex) {
            Logger.getLogger(LockerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LockerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LockerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Cliente pegarDadosSessaoCliente() {
        return (Cliente) FuraFilaUtils.pegarDadosSessao(FuraFilaConstants.SESSAO_CLIENTE);
    }

    public List<Locker> getLstLockers() {
        return lstLockers;
    }

    public void setLstLockers(List<Locker> lstLockers) {
        this.lstLockers = lstLockers;
    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

    public LockerService getLockerService() {
        return lockerService;
    }

    public void setLockerService(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    public CepService getCepService() {
        return cepService;
    }

    public void setCepService(CepService cepService) {
        this.cepService = cepService;
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

    public LogradouroBusiness getLogradouroBusiness() {
        return logradouroBusiness;
    }

    public void setLogradouroBusiness(LogradouroBusiness logradouroBusiness) {
        this.logradouroBusiness = logradouroBusiness;
    }

    public CidadeService getCidadeService() {
        return cidadeService;
    }

    public void setCidadeService(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
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

    public DimensaoBusiness getDimensaoBusiness() {
        return dimensaoBusiness;
    }

    public void setDimensaoBusiness(DimensaoBusiness dimensaoBusiness) {
        this.dimensaoBusiness = dimensaoBusiness;
    }

    public ConjuntoLocker getConjuntoLocker() {
        return conjuntoLocker;
    }

    public void setConjuntoLocker(ConjuntoLocker conjuntoLocker) {
        this.conjuntoLocker = conjuntoLocker;
    }

    public ConjuntoLockerService getConjuntoLockerService() {
        return conjuntoLockerService;
    }

    public void setConjuntoLockerService(ConjuntoLockerService conjuntoLockerService) {
        this.conjuntoLockerService = conjuntoLockerService;
    }

}
