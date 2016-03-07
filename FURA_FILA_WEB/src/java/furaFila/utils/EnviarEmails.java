package furaFila.utils;

import furaFila.mvc.estabelecimento.model.Estabelecimento;
import furaFila.mvc.pedidoLocker.model.PedidoLocker;
import furaFila.mvc.pedidos.model.Pedidos;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class EnviarEmails {

    private static final Mail mail = new Mail();

    public static void enviarEmailBoasVindas(Estabelecimento estabelecimento) throws Exception {

        mail.addTo(estabelecimento.getEmail(),estabelecimento.getRazao_social());
        mail.setFrom(FuraFilaConstants.EMAIL_OFICIAL,FuraFilaConstants.TITULO_PROJETO);
        mail.setSubject(FuraFilaConstants.ASSUNTO_BOAS_VINDAS + estabelecimento.getRazao_social());
        mail.setContent(TemplateEmail.getEmailBemVindo(estabelecimento.getRazao_social()));
        mail.send();

    }
    
    public static void envarEmailEmAnalise(PedidoLocker pedidoLocker,String total,List<PedidoLocker> lstCarrinho) throws Exception {

        mail.addTo(pedidoLocker.getPedidos().getComanda().getCliente().getEmail(),pedidoLocker.getPedidos().getComanda().getCliente().getNome());
        mail.setFrom(FuraFilaConstants.EMAIL_OFICIAL,FuraFilaConstants.TITULO_PROJETO);
        mail.setSubject(FuraFilaConstants.ASSUNTO_STATUS);
        mail.setContent(TemplateEmail.getEmailEmAnalise(pedidoLocker,total,lstCarrinho));
        mail.send();

    }
    
    public static void enviarEmailEmSeparacao(PedidoLocker pedidoLocker,String total,List<Pedidos> lstCarrinho) throws Exception {

        mail.addTo(pedidoLocker.getPedidos().getComanda().getCliente().getEmail(),pedidoLocker.getPedidos().getComanda().getCliente().getNome());
        mail.setFrom(FuraFilaConstants.EMAIL_OFICIAL,FuraFilaConstants.TITULO_PROJETO);
        mail.setSubject(FuraFilaConstants.ASSUNTO_STATUS);
        mail.setContent(TemplateEmail.getEmailEmSeparacao(pedidoLocker,total,lstCarrinho));
        mail.send();

    }
    
    public static void enviarEmailEncaminhadoLocker(PedidoLocker pedidoLocker) throws Exception {

        mail.addTo(pedidoLocker.getPedidos().getComanda().getCliente().getEmail(),pedidoLocker.getPedidos().getComanda().getCliente().getNome());
        mail.setFrom(FuraFilaConstants.EMAIL_OFICIAL,FuraFilaConstants.TITULO_PROJETO);
        mail.setSubject(FuraFilaConstants.ASSUNTO_STATUS);
        mail.setContent(TemplateEmail.getEmailEnviadoLocker(pedidoLocker));
        mail.send();

    }

}
