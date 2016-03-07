package furaFila.mvc.bairro.service;

import furaFila.mvc.bairro.business.BairroBusiness;
import furaFila.mvc.bairro.model.Bairro;
import furaFila.utils.FuraFilaUtils;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class BairroService {

    private BairroBusiness bairroBusiness = new BairroBusiness();

    public Bairro buscarBairro(Bairro bairro) throws Exception {

        List<String> lstDados = getBairroBusiness().buscarBairro(bairro);

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            bairro.setId_bairro(Integer.parseInt(lstDados.get(0)));
            bairro.setDesc_bairro(lstDados.get(1));
        } else {
            bairro = new Bairro();
        }
        return bairro;

    }

    public BairroBusiness getBairroBusiness() {
        return bairroBusiness;
    }

    public void setBairroBusiness(BairroBusiness bairroBusiness) {
        this.bairroBusiness = bairroBusiness;
    }

}
