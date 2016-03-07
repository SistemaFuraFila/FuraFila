package furaFila.mvc.estoqueProdutos.service;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.estoqueProdutos.business.EstoqueProdutosBusiness;
import furaFila.mvc.estoqueProdutos.model.EstoqueProdutos;
import furaFila.mvc.produto.model.Produto;
import furaFila.relatorios.VOs.ProdutoVO;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstoqueProdutosService {

    private EstoqueProdutosBusiness estoqueProdutosBusiness = new EstoqueProdutosBusiness();
    
    public List<EstoqueProdutos> listarProdutosPorCodigoEstabelecimento(Estabelecimento estabelecimento) throws Exception {

        List<List<String>> lstDados = getEstoqueProdutosBusiness().listarProdutosPorCodigoEstabelecimento(estabelecimento);
        List<EstoqueProdutos> lstProdutos = new ArrayList<>();
        

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            
            for (List<String> lstValores : lstDados) {
                
                int index = 0;
                
                EstoqueProdutos e = new EstoqueProdutos();
                e.getProduto().setId_produto(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().setProduto_desc(lstValores.get(index++));
                e.getProduto().setQtdMinima(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().setValor_unitario(Double.parseDouble(lstValores.get(index++)));
                e.getProduto().setStatus(lstValores.get(index++).charAt(0) == FuraFilaConstants.COD_ATIVO);
                e.getProduto().getTipoProduto().setId_tipo_produto(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getTipoProduto().setTipo_produto_desc(lstValores.get(index++));
                e.getProduto().getDimensao().setId_dimensao(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getDimensao().setAltura(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getDimensao().setLargura(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getDimensao().setProfundidade(Integer.parseInt(lstValores.get(index++)));
                e.getProduto().getImagem().setId_imagem(Integer.parseInt(lstValores.get(index++)));
                e.setQtdEstoque(Integer.parseInt(lstValores.get(index++)));
                
                lstProdutos.add(e);
            }

        }

        return lstProdutos;
        
    }

    public List<ProdutoVO> valoresParaRelatorio(EstoqueProdutos ep) throws Exception{
        
        List<List<String>> lstDados = getEstoqueProdutosBusiness().dadosParaRelatorio(ep);
        List<ProdutoVO> lstRelatorio = new ArrayList<>();
        
        for(List<String> lstValores : lstDados){
            
            ProdutoVO p = new ProdutoVO();
            
            int index = 0;
            p.setRazao_social(lstValores.get(index++));
            p.setProduto_desc(lstValores.get(index++));
            p.setValor_unitario(Double.parseDouble(lstValores.get(index++)));
            p.setQtdEstoque(Integer.parseInt(lstValores.get(index++)));
            p.setTipo_produto_desc(lstValores.get(index++));
            
            lstRelatorio.add(p);
            
        }
        
        return lstRelatorio;
        
    }
    
    public EstoqueProdutosBusiness getEstoqueProdutosBusiness() {
        return estoqueProdutosBusiness;
    }

    public void setEstoqueProdutosBusiness(EstoqueProdutosBusiness estoqueProdutosBusiness) {
        this.estoqueProdutosBusiness = estoqueProdutosBusiness;
    }

}
