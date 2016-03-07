package furaFila.mvc.tipoProduto.service;

import furaFila.mvc.tipoProduto.business.TipoProdutoBusiness;
import furaFila.mvc.tipoProduto.model.TipoProduto;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TipoProdutoService {

    private TipoProdutoBusiness tipoProdutoBusiness = new TipoProdutoBusiness();

    public List<TipoProduto> listarTipoProduto(boolean isAdministador) throws Exception {

        TipoProduto tipoProduto;
        List<TipoProduto> lstTipoProdutos = new ArrayList<>();
        List<List<String>> lstDados = getTipoProdutoBusiness().listarTipoProduto(isAdministador);

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> valor : lstDados) {

                tipoProduto = new TipoProduto();
                int indice = 0;

                tipoProduto.setId_tipo_produto(Integer.parseInt(valor.get(indice++)));
                tipoProduto.setTipo_produto_desc(valor.get(indice++));
                tipoProduto.setStatus(valor.get(indice++).charAt(0) == FuraFilaConstants.COD_ATIVO);

                lstTipoProdutos.add(tipoProduto);

            }
        }

        return lstTipoProdutos;

    }

    public int pegarTipoProduto(TipoProduto tipoProduto) throws Exception {

        List<String> lstDados = getTipoProdutoBusiness().listarTipoProduto(tipoProduto);

        return lstDados.size();

    }

    public TipoProdutoBusiness getTipoProdutoBusiness() {
        return tipoProdutoBusiness;
    }

    public void setTipoProdutoBusiness(TipoProdutoBusiness tipoProdutoBusiness) {
        this.tipoProdutoBusiness = tipoProdutoBusiness;
    }

}
