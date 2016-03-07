package furaFila.mvc.comanda.service;

import furaFila.mvc.comanda.business.ComandaBusiness;
import furaFila.mvc.comanda.model.Comanda;
import furaFila.mvc.pedidoLocker.model.PedidoLocker;
import furaFila.mvc.pedidos.model.Pedidos;
import furaFila.utils.FuraFilaUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wellington Goncalves Pires
 */
public class ComandaService {

    private ComandaBusiness comandaBusiness = new ComandaBusiness();

    public List<PedidoLocker> listarComandasAprovadas(String complementoQuery) throws Exception {

        List<List<String>> lstDados = getComandaBusiness().listarComandasAprovadas(complementoQuery);
        List<PedidoLocker> lstComandasAprovadas = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                PedidoLocker pl = new PedidoLocker();

                pl.getPedidos().getComanda().setId_comanda(lstValores.get(index++));
                pl.getPedidos().getComanda().setCodigoPedido(lstValores.get(index++));
                pl.getPedidos().getComanda().getStatus().setId_status(Integer.parseInt(lstValores.get(index++)));
                pl.getPedidos().getComanda().getStatus().setStatus(lstValores.get(index++));
                pl.getPedidos().getComanda().getEstabelecimento().setId_estabelecimento(Integer.parseInt(lstValores.get(index++)));
                pl.getPedidos().getComanda().getEstabelecimento().setRazao_social(lstValores.get(index++));
                pl.getPedidos().getComanda().getEstabelecimento().setCnpj(Long.parseLong(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().setConjunto_locker_desc(lstValores.get(index++));
                pl.getLocker().setId_locker(Integer.parseInt(lstValores.get(index++)));
                pl.getLocker().setLocker_desc(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().getTipoLogradouro().setDesc_tipo_logradouro(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().setNroCep(Integer.parseInt(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().getLogradouro().setLogradouro(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().setLatitude(Double.parseDouble(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().getLogradouro().setLongitude(Double.parseDouble(lstValores.get(index++)));
                pl.getLocker().getConjuntoLocker().getLogradouro().getBairro().setDesc_bairro(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().getBairro().getCidade().setDesc_cidade(lstValores.get(index++));
                pl.getLocker().getConjuntoLocker().getLogradouro().getBairro().getCidade().getUf().setSigla_uf(lstValores.get(index++));
                pl.getPedidos().getComanda().getCliente().setNome(lstValores.get(index++));
                pl.getPedidos().getComanda().getCliente().setEmail(lstValores.get(index++));

                lstComandasAprovadas.add(pl);

            }
        }

        return lstComandasAprovadas;
    }

    public List<Pedidos> listarProdutosPorComanda(Comanda comanda) throws Exception {

        List<List<String>> lstDados = getComandaBusiness().listarProdutosPorComanda(comanda);
        List<Pedidos> lstProdutos = new ArrayList<>();

        if (!FuraFilaUtils.listaDuplaVaziaNula(lstDados)) {
            for (List<String> lstValores : lstDados) {

                int index = 0;
                Pedidos p = new Pedidos();
                p.getProduto().setId_produto(Integer.parseInt(lstValores.get(index++)));
                p.getProduto().setProduto_desc(lstValores.get(index++));
                p.getProduto().setValor_unitario(Double.parseDouble(lstValores.get(index++)));
                p.setQtd(Integer.parseInt(lstValores.get(index++)));
                lstProdutos.add(p);

            }
        }

        return lstProdutos;
    }

    public PedidoLocker buscarEstabelecimentoLocker(Comanda comanda) throws Exception {

        List<String> lstDados = getComandaBusiness().buscarEstabelecimentoLocker(comanda);
        PedidoLocker pedidoLocker = new PedidoLocker();

        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            int index = 0;
            pedidoLocker.getPedidos().getComanda().getEstabelecimento().setId_estabelecimento(Integer.parseInt(lstDados.get(index++)));
            pedidoLocker.getLocker().setId_locker(Integer.parseInt(lstDados.get(index++)));

        }

        return pedidoLocker;
    }

    public ComandaBusiness getComandaBusiness() {
        return comandaBusiness;
    }

    public void setComandaBusiness(ComandaBusiness comandaBusiness) {
        this.comandaBusiness = comandaBusiness;
    }

}
