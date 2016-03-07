package furaFila.mvc.estabelecimentoLogin.service;

import furaFila.mvc.estabelecimentoLogin.business.EstabelecimentoLoginBusiness;
import furaFila.mvc.estabelecimentoLogin.model.EstabelecimentoLogin;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoLoginService {

    private EstabelecimentoLoginBusiness estabelecimentoLoginBusiness = new EstabelecimentoLoginBusiness();

    public void buscarInformacoesIniciaisEstabelecimento(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

        List<String> lstDados = getEstabelecimentoLoginBusiness().buscarInformacoesIniciaisEstabelecimento(estabelecimentoLogin);

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {

            Integer indice = 0;

            estabelecimentoLogin.getEstabelecimento().setId_estabelecimento(Integer.parseInt(lstDados.get(indice++)));
            estabelecimentoLogin.getEstabelecimento().setRazao_social(lstDados.get(indice++));
            estabelecimentoLogin.getEstabelecimento().setStatus(lstDados.get(indice++).charAt(0) == FuraFilaConstants.COD_ATIVO);

        }

    }

    public EstabelecimentoLoginBusiness getEstabelecimentoLoginBusiness() {
        return estabelecimentoLoginBusiness;
    }

    public void setEstabelecimentoLoginBusiness(EstabelecimentoLoginBusiness estabelecimentoLoginBusiness) {
        this.estabelecimentoLoginBusiness = estabelecimentoLoginBusiness;
    }

    public List<EstabelecimentoLogin> listarUsuarios(EstabelecimentoLogin estabelecimentoLogin) throws Exception {

        List<List<String>> lstDados = getEstabelecimentoLoginBusiness().listarUsuarios(estabelecimentoLogin);
        List<EstabelecimentoLogin> lstEstabelecimentoLogin = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> valores : lstDados) {

                EstabelecimentoLogin el = new EstabelecimentoLogin();
                el.getLogin().setId_login(Integer.parseInt(valores.get(0)));
                el.getLogin().setUsuario(valores.get(1));
                lstEstabelecimentoLogin.add(el);

            }

        }

        return lstEstabelecimentoLogin;
        
    }

}
