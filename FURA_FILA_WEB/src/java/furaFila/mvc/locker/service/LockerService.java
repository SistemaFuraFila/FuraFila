package furaFila.mvc.locker.service;

import furaFila.mvc.conjuntoLocker.model.ConjuntoLocker;
import furaFila.mvc.locker.business.LockerBusiness;
import furaFila.mvc.locker.model.Locker;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class LockerService {

    private LockerBusiness lockerBusiness = new LockerBusiness();

    public List<Locker> buscarLockerPorVolume(Integer volumeTotal,ConjuntoLocker conjuntoLocker) throws Exception {

        List<Locker> lstLocker = new ArrayList<>();
        List<List<String>> lstDados = getLockerBusiness().buscarLockersPorVolume(volumeTotal,conjuntoLocker);

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                Locker l = new Locker();

                l.setId_locker(Integer.parseInt(lstValores.get(index++)));
                l.setLocker_desc(lstValores.get(index++));
                l.getConjuntoLocker().setId_conjunto_locker(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setId_dimensao(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setAltura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setLargura(Integer.parseInt(lstValores.get(index++)));
                l.getDimensao().setProfundidade(Integer.parseInt(lstValores.get(index++)));
                l.getStatus().setId_status(Integer.parseInt(lstValores.get(index++)));

                lstLocker.add(l);
            }
        }

        return lstLocker;

    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

}
