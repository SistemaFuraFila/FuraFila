package furaFila.mvc.estabelecimento.service;

import furaFila.mvc.estabelecimento.business.EstabelecimentoBusiness;
import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.pedidos.model.Pedidos;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EstabelecimentoService {

    private EstabelecimentoBusiness estabelecimentoBusiness = new EstabelecimentoBusiness();

    public List<Estabelecimento> listarEstabelecimentos() throws Exception {

        List<Estabelecimento> lstEstabelecimentos = new ArrayList<>();
        List<List<String>> lstDados = getEstabelecimentoBusiness().listarEstabelecimento();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {

            for (List<String> valores : lstDados) {
 
                Estabelecimento e = new Estabelecimento();
                Integer indice = 0;
                
                e.setId_estabelecimento(Integer.parseInt(valores.get(indice++)));
                e.setRazao_social(valores.get(indice++));
                e.setCnpj(Long.parseLong(valores.get(indice++)));
                e.setInscricao_estadual(Long.parseLong(valores.get(indice++)));
                e.setStatus(valores.get(indice++).equals(String.valueOf(FuraFilaConstants.COD_ATIVO)));
                e.setEmail(valores.get(indice++));
                e.getImagem().setId_imagem(Integer.parseInt(valores.get(indice++) == null ? "0" : valores.get(indice++)));
                e.getImagem().setImagem(FuraFilaUtils.semImagem(e));
                
                lstEstabelecimentos.add(e);
                
            }

        }

        return lstEstabelecimentos;
        
    }

    public void buscarInformacaoEstabelecimento(Estabelecimento estabelecimento) throws Exception{
        
        List<String> lstDados = getEstabelecimentoBusiness().listarInformacoesEstabelecimento(estabelecimento);
        
        if(!FuraFilaUtils.listaVaziaNula(lstDados)){
            
            Integer indice = 0;
            
            estabelecimento.setId_estabelecimento(Integer.parseInt(lstDados.get(indice++)));
            estabelecimento.setRazao_social(lstDados.get(indice++));
            estabelecimento.setEmail(lstDados.get(indice++));
            estabelecimento.setCnpj(Long.parseLong(lstDados.get(indice++)));
            estabelecimento.setInscricao_estadual(Long.parseLong(lstDados.get(indice++)));
            estabelecimento.getImagem().setId_imagem(Integer.parseInt(lstDados.get(indice++)));
            
        }
        else{
            estabelecimento = new Estabelecimento();
        }
        
    }
    
    public List<Pedidos> listarEstabelecimentoMaisVendem() throws Exception{
        
        List<List<String>> lstDados = getEstabelecimentoBusiness().listarEstabelecimentoMaisVendem();
        List<Pedidos> lstEstabelecimentos = new ArrayList<>();
        
        if(!FuraFilaUtils.listaDuplaVaziaNula(lstDados)){
            for(List<String> lstValores : lstDados){
                
                int index = 0;
                Pedidos pedido = new Pedidos();
                
                pedido.getComanda().getEstabelecimento().setRazao_social(lstValores.get(index++));
                pedido.setQtd(Integer.parseInt(lstValores.get(index++)));
                
                lstEstabelecimentos.add(pedido);
            }
        }
        
        return lstEstabelecimentos;
        
    }
    
    public int verificarDuplicidadeRazaoSocial(Estabelecimento estabelecimento) throws Exception{
        return getEstabelecimentoBusiness().pegarRazaoSocial(estabelecimento).size();
    }
    public int verificarDuplicidadeEmail(Estabelecimento estabelecimento) throws Exception{
        return getEstabelecimentoBusiness().pegarEmail(estabelecimento).size();
    }
    public int verificarDuplicidadeCnpj(Estabelecimento estabelecimento) throws Exception{
        return getEstabelecimentoBusiness().pegarCnpj(estabelecimento).size();
    }
    public int verificarDuplicidadeInscricaoEstadual(Estabelecimento estabelecimento) throws Exception{
        return getEstabelecimentoBusiness().pegarInscricaoEstadual(estabelecimento).size();
    }
    
    public EstabelecimentoBusiness getEstabelecimentoBusiness() {
        return estabelecimentoBusiness;
    }

    public void setEstabelecimentoBusiness(EstabelecimentoBusiness estabelecimentoBusiness) {
        this.estabelecimentoBusiness = estabelecimentoBusiness;
    }

}
