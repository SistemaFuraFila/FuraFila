package furaFila.mvc.conjuntoLocker.service;

import furaFila.mvc.cliente.model.Cliente;
import furaFila.mvc.conjuntoLocker.business.ConjuntoLockerBusiness;
import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.locker.model.Locker;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ConjuntoLockerService {

    private ConjuntoLockerBusiness conjuntoLockerBusiness = new ConjuntoLockerBusiness();

    public List<ConjuntoLocker> listarConjuntoLocker() throws Exception {

        List<List<String>> lstDados = getConjuntoLockerBusiness().listarConjuntoLockers();
        List<ConjuntoLocker> lstConjuntoLockers = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                ConjuntoLocker cl = new ConjuntoLocker();

                cl.setId_conjunto_locker(Integer.parseInt(lstValores.get(index++)));
                cl.setConjunto_locker_desc(lstValores.get(index++));
                cl.setNro_localizacao(Integer.parseInt(lstValores.get(index++)));
                cl.getLogradouro().setNroCep(Integer.parseInt(lstValores.get(index++)));
                cl.getLogradouro().setLatitude(Double.parseDouble(lstValores.get(index++)));
                cl.getLogradouro().setLongitude(Double.parseDouble(lstValores.get(index++)));
                cl.getLogradouro().getTipoLogradouro().setDesc_tipo_logradouro(lstValores.get(index++));
                cl.getLogradouro().setLogradouro(lstValores.get(index++));
                cl.getLogradouro().getBairro().setDesc_bairro(lstValores.get(index++));
                cl.getLogradouro().getBairro().getCidade().setDesc_cidade(lstValores.get(index++));
                cl.getLogradouro().getBairro().getCidade().getUf().setDesc_uf(lstValores.get(index++));
                cl.setStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
                cl.setQtdLockers(Integer.parseInt(lstValores.get(index++)));

                lstConjuntoLockers.add(cl);

            }
        }

        return lstConjuntoLockers;

    }

    public List<Locker> listarLockerPorConjunto(ConjuntoLocker conjuntoLocker) throws Exception {

        List<List<String>> lstDados = getConjuntoLockerBusiness().listarLockersPorConjunto(conjuntoLocker);
        List<Locker> lstLockers = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {
                int index = 0;
                Locker l = new Locker();

                l.setId_locker(Integer.parseInt(lstValores.get(index++)));
                l.setLocker_desc(lstValores.get(index++));
                l.setBolStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
                l.getStatus().setId_status(Integer.parseInt(lstValores.get(index++)));
                l.getStatus().setStatus(lstValores.get(index++));
                l.getDimensao().setId_dimensao(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setDimensao_desc(lstValores.get(index++));
                l.getDimensao().setAltura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setLargura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setProfundidade(Integer.parseInt(lstValores.get(index++)));

                lstLockers.add(l);
            }
        }

        return lstLockers;

    }

    public List<Locker> listarLockersProximos(Cliente cliente) throws Exception {

        List<List<String>> lstDados = getConjuntoLockerBusiness().listarLockersProximos(cliente);
        List<Locker> lstLockersProximos = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> lstValores : lstDados) {
                int index = 0;
                Locker locker = new Locker();

                locker.getConjuntoLocker().setId_conjunto_locker(Integer.parseInt(lstValores.get(index++)));
                locker.getConjuntoLocker().setConjunto_locker_desc(lstValores.get(index++));
                locker.setId_locker(Integer.parseInt(lstValores.get(index++)));
                locker.setLocker_desc(lstValores.get(index++));
                locker.getDimensao().setDimensao_desc(lstValores.get(index++));
                locker.getConjuntoLocker().getLogradouro().setNroCep(Integer.parseInt(lstValores.get(index++)));
                
                lstLockersProximos.add(locker);
            }
        }
        
        return lstLockersProximos;
        
    }

    public ConjuntoLockerBusiness getConjuntoLockerBusiness() {
        return conjuntoLockerBusiness;
    }

    public void setConjuntoLockerBusiness(ConjuntoLockerBusiness conjuntoLockerBusiness) {
        this.conjuntoLockerBusiness = conjuntoLockerBusiness;
    }

}
