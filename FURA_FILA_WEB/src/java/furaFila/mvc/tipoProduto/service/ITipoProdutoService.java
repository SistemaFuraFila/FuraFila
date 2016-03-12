package furaFila.mvc.tipoProduto.service;

import furaFila.mvc.tipoProduto.model.TipoProduto;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public interface ITipoProdutoService {
    
    public List<TipoProduto> listarTipoProduto(boolean isAdministador) throws Exception;
    public int pegarTipoProduto(TipoProduto tipoProduto) throws Exception;
    
}
