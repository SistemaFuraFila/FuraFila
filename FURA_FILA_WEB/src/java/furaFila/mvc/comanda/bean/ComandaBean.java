package furaFila.mvc.comanda.bean;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionSearchResult;
import br.com.uol.pagseguro.domain.TransactionSummary;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.TransactionSearchService;
import furaFila.mvc.comanda.business.ComandaBusiness;
import furaFila.mvc.comanda.service.ComandaService;
import furaFila.mvc.estoqueSaida.business.EstoqueSaidaBusiness;
import furaFila.mvc.estoqueSaida.model.EstoqueSaida;
import furaFila.mvc.locker.business.LockerBusiness;
import furaFila.mvc.pedidoLocker.model.PedidoLocker;
import furaFila.mvc.pedidos.model.Pedidos;
import furaFila.utils.EnviarEmails;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.FuraFilaUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Wellington Gonçalves Pires
 */
@ManagedBean
@SessionScoped
public class ComandaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PedidoLocker> lstComandasAprovadas = new ArrayList<>();

    private ComandaBusiness comandaBusiness = new ComandaBusiness();
    private LockerBusiness lockerBusiness = new LockerBusiness();
    private EstoqueSaidaBusiness estoqueSaidaBusiness = new EstoqueSaidaBusiness();

    private ComandaService comandaService = new ComandaService();

    private PedidoLocker pedidoLocker = new PedidoLocker();

    public void popularComandasAprovadas() {

        try {
            setLstComandasAprovadas(getComandaService().listarComandasAprovadas(" WHERE C.id_status_FK <> " + FuraFilaConstants.COD_PRODUTO_ENTREGUE + " AND C.id_status_FK <> " + FuraFilaConstants.COD_ENCAMINHADO_LOCKER));
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void adicionarCabecalho() {
        FacesContext.getCurrentInstance().getExternalContext().addResponseHeader("Access-Control-Allow-Origin", "https://sandbox.pagseguro.uol.com.br");
    }

    public void atualizarComandas(ActionEvent ae) {

        try {

            //1 - VERIFICAR O STATUS JUNTO COM O PAGSEGURO
            //2 - ATUALIZAR A FORMA DE PAGAMENTO
            //3 - ORGANIZAR A LISTA DE ACORDO COM A LOCALIZAÇÃO
//            List<PedidoLocker> lstTodasComandas = getComandaService().listarComandasAprovadas(false);
            //VERIFICAR O STATUS JUNTO AO PAGSEGURO
//            buscarDadosPedido();
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    private void buscarDadosPedido() throws PagSeguroServiceException {

//        Transaction pedido = TransactionSearchService.searchByCode(PagSeguroConfig.getAccountCredentials(), "4C828EFF7474B67EE4C27FAF06F440A1");
//        pl.getPedidos().getComanda().getFormaPagamento().setForma_pagamento(pedido.getPaymentMethod().getType().toString());
//        pl.getPedidos().getComanda().getStatus().setStatus(pedido.getStatus().getDescription());
//        pl.getPedidos().getComanda().setDataVenda(pedido.getDate());
//        System.out.println(pedido.getPaymentMethod().getType().toString());
//        System.out.println(pedido.getStatus().getDescription());
//        System.out.println(pedido.getDate());
        List<String> lstTransactionCode = new ArrayList<>();
        TransactionSearchResult transactionSearchResult = null;

        Calendar initialDate = Calendar.getInstance();
        initialDate.set(2015, Calendar.NOVEMBER, 01, 0, 00);

        Calendar finalDate = Calendar.getInstance();
        finalDate.set(2015, Calendar.NOVEMBER, 21, 00, 00);

        transactionSearchResult = TransactionSearchService.searchByDate(PagSeguroConfig.getAccountCredentials(), initialDate.getTime(), finalDate.getTime(), 1, 1000);

        if (transactionSearchResult != null) {
            List<TransactionSummary> listTransactionSummaries = transactionSearchResult.getTransactionSummaries();
            Iterator<TransactionSummary> transactionSummariesIterator = listTransactionSummaries.iterator();
            while (transactionSummariesIterator.hasNext()) {
                TransactionSummary currentTransactionSummary = (TransactionSummary) transactionSummariesIterator.next();
                lstTransactionCode.add(currentTransactionSummary.getCode());
            }
        }

        for (String valor : lstTransactionCode) {
            Transaction pedido = TransactionSearchService.searchByCode(PagSeguroConfig.getAccountCredentials(), valor);

            System.out.println(pedido.getCode());
            System.out.println(pedido.getPaymentMethod().getType());
            System.out.println(pedido.getStatus().getDescription());
            System.out.println(pedido.getType().getDescription());
        }

    }

    public void confirmarPedido(ActionEvent ae) {
        try {
            List<Pedidos> lstProdutos = getComandaService().listarProdutosPorComanda(getPedidoLocker().getPedidos().getComanda());
            
            PedidoLocker pl = getComandaService().buscarEstabelecimentoLocker(getPedidoLocker().getPedidos().getComanda());
            
            pl.getPedidos().getComanda().getStatus().setId_status(FuraFilaConstants.COD_PRODUTO_ENTREGUE);
            pl.getLocker().getStatus().setId_status(FuraFilaConstants.COD_LOCKER_LIVRE);
            getLockerBusiness().alterarStatus(pl.getLocker());
            
            for (Pedidos p : lstProdutos) {
                EstoqueSaida es = new EstoqueSaida();
                es.setProduto(p.getProduto());
                es.setQtdSaida(p.getQtd());
                es.getMotivoSaida().setMotivoSaida(FuraFilaConstants.MOTIVO_SAIDA_VENDA);
                getEstoqueSaidaBusiness().gravar(es, pl.getPedidos().getComanda().getEstabelecimento());
            }
            
            FuraFilaUtils.executarJavascript("alert('Pedido nº" + getPedidoLocker().getPedidos().getComanda().getId_comanda() + " entregue!')");
            
            getComandaBusiness().alterarStatusComanda(getPedidoLocker().getPedidos().getComanda());
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }

    }

    public void alterarStatusPedido(ActionEvent ae) {
        try {

            List<Pedidos> lstProdutos = getComandaService().listarProdutosPorComanda(getPedidoLocker().getPedidos().getComanda());

            if (FuraFilaConstants.COD_EM_ANALISE == getPedidoLocker().getPedidos().getComanda().getStatus().getId_status()) {
                getPedidoLocker().getPedidos().getComanda().getStatus().setId_status(FuraFilaConstants.COD_EM_SEPARACAO);

                Double total = 0.0;

                for (Pedidos p : lstProdutos) {
                    total += p.getSubTotal();
                }

                EnviarEmails.enviarEmailEmSeparacao(getPedidoLocker(), FuraFilaUtils.formatarMoeda(total), lstProdutos);
            } else if (FuraFilaConstants.COD_EM_SEPARACAO == getPedidoLocker().getPedidos().getComanda().getStatus().getId_status()) {
                getPedidoLocker().getPedidos().getComanda().getStatus().setId_status(FuraFilaConstants.COD_ENCAMINHADO_LOCKER);
                getComandaBusiness().atualizarDataVenda(getPedidoLocker().getPedidos().getComanda());

                EnviarEmails.enviarEmailEncaminhadoLocker(getPedidoLocker());

            } else if (FuraFilaConstants.COD_ENCAMINHADO_LOCKER == getPedidoLocker().getPedidos().getComanda().getStatus().getId_status()) {
                getPedidoLocker().getPedidos().getComanda().getStatus().setId_status(FuraFilaConstants.COD_PRODUTO_ENTREGUE);
                getPedidoLocker().getLocker().getStatus().setId_status(FuraFilaConstants.COD_LOCKER_LIVRE);
                getLockerBusiness().alterarStatus(getPedidoLocker().getLocker());

                for (Pedidos p : lstProdutos) {
                    EstoqueSaida es = new EstoqueSaida();
                    es.setProduto(p.getProduto());
                    es.setQtdSaida(p.getQtd());
                    es.getMotivoSaida().setMotivoSaida(FuraFilaConstants.MOTIVO_SAIDA_VENDA);
                    getEstoqueSaidaBusiness().gravar(es, getPedidoLocker().getPedidos().getComanda().getEstabelecimento());
                }

                FuraFilaUtils.executarJavascript("alert('Pedido nº" + getPedidoLocker().getPedidos().getComanda().getId_comanda() + " entregue!')");

            }

            getComandaBusiness().alterarStatusComanda(getPedidoLocker().getPedidos().getComanda());
            popularComandasAprovadas();
        } catch (Exception ex) {
            FuraFilaUtils.growlAviso(FuraFilaConstants.AVISO_GROWL_TITULO, ex.getMessage());
        }
    }

    public List<PedidoLocker> getLstComandasAprovadas() {
        return lstComandasAprovadas;
    }

    public void setLstComandasAprovadas(List<PedidoLocker> lstComandasAprovadas) {
        this.lstComandasAprovadas = lstComandasAprovadas;
    }

    public ComandaService getComandaService() {
        return comandaService;
    }

    public void setComandaService(ComandaService comandaService) {
        this.comandaService = comandaService;
    }

    public PedidoLocker getPedidoLocker() {
        return pedidoLocker;
    }

    public void setPedidoLocker(PedidoLocker pedidoLocker) {
        this.pedidoLocker = pedidoLocker;
    }

    public ComandaBusiness getComandaBusiness() {
        return comandaBusiness;
    }

    public void setComandaBusiness(ComandaBusiness comandaBusiness) {
        this.comandaBusiness = comandaBusiness;
    }

    public LockerBusiness getLockerBusiness() {
        return lockerBusiness;
    }

    public void setLockerBusiness(LockerBusiness lockerBusiness) {
        this.lockerBusiness = lockerBusiness;
    }

    public EstoqueSaidaBusiness getEstoqueSaidaBusiness() {
        return estoqueSaidaBusiness;
    }

    public void setEstoqueSaidaBusiness(EstoqueSaidaBusiness estoqueSaidaBusiness) {
        this.estoqueSaidaBusiness = estoqueSaidaBusiness;
    }

}
