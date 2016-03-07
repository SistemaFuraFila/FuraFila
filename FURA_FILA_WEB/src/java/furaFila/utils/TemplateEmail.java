package furaFila.utils;

import furaFila.mvc.pedidoLocker.model.PedidoLocker;
import furaFila.mvc.pedidos.model.Pedidos;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class TemplateEmail {

    public static String getEmailBemVindo(String cliente) {

        String template_bem_vindo
                = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
                + "        <title></title>\n"
                + "        <style type=\"text/css\">\n"
                + "            #outlook a {padding:0;}\n"
                + "            body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n"
                + "            .ExternalClass {width:100%;}\n"
                + "            .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;}\n"
                + "            #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\n"
                + "            img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}\n"
                + "            a img {border:none;}\n"
                + "            .image_fix {display:block;}\n"
                + "            p {margin: 1em 0;}\n"
                + "            h1, h2, h3, h4, h5, h6 {color: black !important;}\n"
                + "            h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: blue !important;}\n"
                + "            h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {\n"
                + "                color: red !important; \n"
                + "            }\n"
                + "            h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited, h6 a:visited {\n"
                + "                color: purple !important; \n"
                + "            }\n"
                + "            table td {border-collapse: collapse;}\n"
                + "            table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\n"
                + "            a {color: #000;}\n"
                + "            @media only screen and (max-device-width: 480px) {\n"
                + "                a[href^=\"tel\"], a[href^=\"sms\"] {\n"
                + "                    text-decoration: none;\n"
                + "                    color: black; /* or whatever your want */\n"
                + "                    pointer-events: none;\n"
                + "                    cursor: default;\n"
                + "                }\n"
                + "                .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n"
                + "                    text-decoration: default;\n"
                + "                    color: orange !important; /* or whatever your want */\n"
                + "                    pointer-events: auto;\n"
                + "                    cursor: default;\n"
                + "                }\n"
                + "            }\n"
                + "            @media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {\n"
                + "                a[href^=\"tel\"], a[href^=\"sms\"] {\n"
                + "                    text-decoration: none;\n"
                + "                    color: blue; /* or whatever your want */\n"
                + "                    pointer-events: none;\n"
                + "                    cursor: default;\n"
                + "                }\n"
                + "                .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n"
                + "                    text-decoration: default;\n"
                + "                    color: orange !important;\n"
                + "                    pointer-events: auto;\n"
                + "                    cursor: default;\n"
                + "                }\n"
                + "            }\n"
                + "            @media only screen and (-webkit-min-device-pixel-ratio: 2) {\n"
                + "                /* Put your iPhone 4g styles in here */\n"
                + "            }\n"
                + "            @media only screen and (-webkit-device-pixel-ratio:.75){\n"
                + "                /* Put CSS for low density (ldpi) Android layouts in here */\n"
                + "            }\n"
                + "            @media only screen and (-webkit-device-pixel-ratio:1){\n"
                + "                /* Put CSS for medium density (mdpi) Android layouts in here */\n"
                + "            }\n"
                + "            @media only screen and (-webkit-device-pixel-ratio:1.5){\n"
                + "                /* Put CSS for high density (hdpi) Android layouts in here */\n"
                + "            }\n"
                + "            /* end Android targeting */\n"
                + "            .bgBody{\n"
                + "                background: #ffffff;\n"
                + "            }\n"
                + "            .bgItem{\n"
                + "                background: #ffffff;\n"
                + "            }\n"
                + "            h2{\n"
                + "                color:#181818;\n"
                + "                font-family:Helvetica, Arial, sans-serif;\n"
                + "                font-size:22px;\n"
                + "                font-weight: normal;\n"
                + "            }\n"
                + "            p{\n"
                + "                color:#555;\n"
                + "                font-family:Helvetica, Arial, sans-serif;\n"
                + "                font-size:16px;\n"
                + "                line-height:160%;\n"
                + "            }\n"
                + "        </style>\n"
                + "        <script type=\"colorScheme\" class=\"swatch active\">\n"
                + "            {\n"
                + "            \"name\":\"Default\",\n"
                + "            \"bgBody\":\"ffffff\",\n"
                + "            \"link\":\"000000\",\n"
                + "            \"color\":\"555555\",\n"
                + "            \"bgItem\":\"ffffff\",\n"
                + "            \"title\":\"181818\"\n"
                + "            }\n"
                + "        </script>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <table cellpadding=\"0\" width=\"100%\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" class='bgBody'>\n"
                + "            <tr>\n"
                + "                <td>\n"
                + "                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\" class='bgItem'>\n"
                + "                        <tr>\n"
                + "                            <td class='movableContentContainer'>\n"
                + "                                <div class='movableContent'>\n"
                + "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">\n"
                + "                                        <tr height=\"40\">\n"
                + "                                            <td width=\"200\">&nbsp;</td>\n"
                + "                                            <td width=\"200\">&nbsp;</td>\n"
                + "                                            <td width=\"200\">&nbsp;</td>\n"
                + "                                        </tr>\n"
                + "                                        <tr>\n"
                + "                                            <td width=\"200\" valign=\"top\">&nbsp;</td>\n"
                + "                                            <td width=\"200\" valign=\"top\" align=\"center\">\n"
                + "                                                <div class=\"contentEditableContainer contentImageEditable\" >\n"
                + "                                                    <div class=\"contentEditable\" >\n"
                + "                                                        <img src=\"FuraFilaLogo1.png\" width=\"155\" height=\"155\"  ata-default=\"placeholder\" alt='Logo'/>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </td>\n"
                + "                                            <td width=\"200\" valign=\"top\">&nbsp;</td>\n"
                + "                                        </tr>\n"
                + "                                        <tr height=\"25\">\n"
                + "                                            <td width=\"200\">&nbsp;</td>\n"
                + "                                            <td width=\"200\">&nbsp;</td>\n"
                + "                                            <td width=\"200\">&nbsp;</td>\n"
                + "                                        </tr>\n"
                + "                                    </table>\n"
                + "                                </div>\n"
                + "                                <div class='movableContent'>\n"
                + "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">\n"
                + "                                        <tr>\n"
                + "                                            <td width=\"50\">&nbsp;</td>\n"
                + "                                            <td width=\"500\" colspan=\"3\" align=\"center\" style=\"padding-bottom:10px;padding-top:25px;\">\n"
                + "                                                <div class=\"contentEditableContainer contentTextEditable\" >\n"
                + "                                                    <div class=\"contentEditable\" >\n"
                + "                                                        <h2>Bem vindo ao Fura Fila, " + cliente + "</h2>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </td>\n"
                + "                                            <td width=\"50\">&nbsp;</td>\n"
                + "                                        </tr>\n"
                + "                                    </table>\n"
                + "                                </div>\n"
                + "                                <div class='movableContent'>\n"
                + "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">\n"
                + "                                        <tr>\n"
                + "                                            <td width=\"50\">&nbsp;</td>\n"
                + "                                            <td width=\"500\" align=\"left\" style=\"padding-bottom:15px;\">\n"
                + "                                                <div class=\"contentEditableContainer contentTextEditable\">\n"
                + "                                                    <div class=\"contentEditable\" >\n"
                + "                                                        <p>\n"
                + "                                                            <br />\n"
                + "                                                                 Sua conta foi ativada! A partir de agora voc&ecirc; poder&aacute; cadastrar seus lockers e produtos e disponibiliz&aacute;-los para a utiliza&ccedil;&atilde;o e venda no site <a href=\"http://www.google.com.br\">www.furafila.com.br</a> atrav&eacute;s da conta de seu estabelecimento.\n"
                + "                                                            <br />\n"
                + "                                                        </p>\n"
                + "                                                        <p>\n"
                + "                                                            <br/>\n"
                + "                                                                 Como acessar? &Eacute; f&aacute;cil, insira seu usu&aacute;rio e senha no site e pronto! Voc&ecirc; ser&aacute; automaticamente direcionado a p&aacute;gina de seu estabelecimento.\n"
                + "                                                            <br/>\n"
                + "                                                        </p>\n"
                + "                                                        <p>\n"
                + "                                                            <br/>\n"
                + "                                                                 Ainda possui d&uacute;vidas? Mande um e-mail para gsfieo_projeto@outlook.com, estaremos a disposi&ccedil;&atilde;o para responder todas as suas quest&otilde;es.\n"
                + "                                                            <br/>\n"
                + "                                                        </p>\n"
                + "                                                        <p>\n"
                + "                                                            <br/>\n"
                + "                                                            Atenciosamente,\n"
                + "                                                            <br/>\n"
                + "                                                            <br/>\n"
                + "                                                            Equipe Fura Fila.\n"
                + "                                                            <br/>\n"
                + "                                                        </p>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </td>\n"
                + "                                            <td width=\"50\">&nbsp;</td>\n"
                + "                                        </tr>\n"
                + "                                    </table>\n"
                + "                                </div>\n"
                + "                                <div class='movableContent'>\n"
                + "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">\n"
                + "                                        <tr><td colspan=\"3\" height='30'></td></tr>\n"
                + "                                        <tr>\n"
                + "                                            <td width=\"50\">&nbsp;</td>\n"
                + "                                            <td width=\"500\" align=\"center\" >\n"
                + "                                                <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"400\" height=\"50\">\n"
                + "                                                    <tr>\n"
                + "                                                        <td bgcolor=\"#ED006F\" align=\"center\" style=\"border-radius:4px;\" width=\"400\" height=\"50\">\n"
                + "                                                            <div class=\"contentEditableContainer contentTextEditable\" >\n"
                + "                                                                <div class=\"contentEditable\" style='text-align:center;'>\n"
                + "                                                                    <a target='_blank' href=\"[CLIENTS.WEBSITE]\" style=\"color:#fff;text-decoration:none;font-family:Helvetica, Arial, sans-serif;font-size:16px;color:#fff;border-radius:4px;\">Acessar Fura Fila</a>\n"
                + "                                                                </div>\n"
                + "                                                            </div>\n"
                + "                                                        </td>\n"
                + "                                                    </tr>\n"
                + "                                                </table>\n"
                + "                                            </td>\n"
                + "                                            <td width=\"50\">&nbsp;</td>\n"
                + "                                        </tr>\n"
                + "                                        <tr><td height=\"10\" colspan=\"3\"></td></tr>\n"
                + "                                    </table>\n"
                + "                                </div>\n"
                + "                                <div class='movableContent'>				\n"
                + "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" width=\"600\">\n"
                + "                                        <tr>\n"
                + "                                            <td width=\"100%\" colspan=\"2\" style=\"padding-top:100px;\">\n"
                + "                                                <hr style=\"height:1px;border:none;color:#333;background-color:#ddd;\" />\n"
                + "                                            </td>\n"
                + "                                        </tr>\n"
                + "                                        <tr>\n"
                + "                                            <td width=\"60%\" height=\"70\" valign=\"middle\" style=\"padding-bottom:20px;\">\n"
                + "                                                <div class=\"contentEditableContainer contentTextEditable\">\n"
                + "                                                    <div class=\"contentEditable\" >\n"
                + "                                                        <span style=\"font-size:13px;color:#181818;font-family:Helvetica, Arial, sans-serif;line-height:200%;\">Fura Fila</span>\n"
                + "                                                        <br/>\n"
                + "                                                        <span style=\"font-size:11px;color:#555;font-family:Helvetica, Arial, sans-serif;line-height:200%;\">www.furafila.com.br</span>\n"
                + "                                                        <br/>\n"
                + "                                                    </div>\n"
                + "                                                </div>\n"
                + "                                            </td>\n"
                + "                                        </tr>\n"
                + "                                    </table>\n"
                + "                                </div>\n"
                + "                            </td>\n"
                + "                        </tr>\n"
                + "                    </table>\n"
                + "                </td>\n"
                + "            </tr>\n"
                + "        </table>\n"
                + "    </body>\n"
                + "</html>";

        return template_bem_vindo;

    }

    public static String getEmailEmAnalise(PedidoLocker pedidoLocker, String total, List<PedidoLocker> lstCarrinho) throws IOException {

        String email = FuraFilaUtils.lerArquivo(FuraFilaConstants.CAMINHO_EMAIL_EM_ANALISE);

        String produtos
                = "<table border=\"1\" style=\"width:100%;border-width: 1px\">\n"
                + "    <tr>\n"
                + "        <th style=\"padding: 5px;\"><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;text-align: center\"><strong style=\"font-weight:bold;\">PRODUTO</strong></p></th>\n"
                + "       <th style=\"padding: 5px;\"><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;text-align: center\"><strong style=\"font-weight:bold;\">PREÇO</strong></p></th>\n"
                + "   </tr>";
        for (PedidoLocker pl : lstCarrinho) {
            produtos
                    += "<tr>\n"
                    + "<td style=\"padding: 5px;\" ><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;\">" + pl.getPedidos().getProduto().getProduto_desc() + "</p></td>\n"
                    + "<td style=\"padding: 5px;\" ><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;text-align: right\">" + pl.getPedidos().getProduto().getValor_unitarioExibicao() + "</p></td>\n"
                    + "</tr>";
        }

        produtos += "</table>";

        HashMap<String, String> hmParametros = new HashMap<>();
        hmParametros.put("$NomeCliente", pedidoLocker.getPedidos().getComanda().getCliente().getNome());
        hmParametros.put("$NomeDestinatario", pedidoLocker.getLocker().getConjuntoLocker().getConjunto_locker_desc());
        hmParametros.put("$EnderecoConjuntoLocker", pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().getLogradouroCompleto());
        hmParametros.put("$NumeroPedido", pedidoLocker.getPedidos().getComanda().getId_comanda());
        hmParametros.put("$NomeVendedor", pedidoLocker.getPedidos().getComanda().getEstabelecimento().getRazao_social());
        hmParametros.put("$Produtos", produtos);
        hmParametros.put("$TotalPedido", total);

        for (String chave : hmParametros.keySet()) {
            email = email.replace(chave, hmParametros.get(chave));
        }

        return email;
    }

    public static String getEmailEmSeparacao(PedidoLocker pedidoLocker, String total, List<Pedidos> lstCarrinho) throws IOException {

        String email = FuraFilaUtils.lerArquivo(FuraFilaConstants.CAMINHO_EMAIL_EM_SEPARACAO);

        String produtos
                = "<table border=\"1\" style=\"width:100%;border-width: 1px\">\n"
                + "    <tr>\n"
                + "        <th style=\"padding: 5px;\"><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;text-align: center\"><strong style=\"font-weight:bold;\">PRODUTO</strong></p></th>\n"
                + "       <th style=\"padding: 5px;\"><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;text-align: center\"><strong style=\"font-weight:bold;\">PREÇO</strong></p></th>\n"
                + "   </tr>";
        for (Pedidos p : lstCarrinho) {
            produtos
                    += "<tr>\n"
                    + "<td style=\"padding: 5px;\" ><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;\">" + p.getProduto().getProduto_desc() + "</p></td>\n"
                    + "<td style=\"padding: 5px;\" ><p style=\"font-style: normal;font-weight: 400;font-size: 15px;line-height: 24px;font-family: sans-serif;color: #60666d;text-align: right\">" + p.getProduto().getValor_unitarioExibicao() + "</p></td>\n"
                    + "</tr>";
        }

        produtos += "</table>";

        HashMap<String, String> hmParametros = new HashMap<>();
        hmParametros.put("$NomeDoCliente", pedidoLocker.getPedidos().getComanda().getCliente().getNome());
        hmParametros.put("$NomeVendedor", pedidoLocker.getPedidos().getComanda().getEstabelecimento().getRazao_social());
        hmParametros.put("$Produtos", produtos);
        hmParametros.put("$TotalCompra", total);

        for (String chave : hmParametros.keySet()) {
            email = email.replace(chave, hmParametros.get(chave));
        }

        return email;

    }

    public static String getEmailEnviadoLocker(PedidoLocker pedidoLocker) throws IOException {

        String email = FuraFilaUtils.lerArquivo(FuraFilaConstants.CAMINHO_EMAIL_ENCAMINHADO_LOCKER);

        HashMap<String, String> hmParametros = new HashMap<>();
        hmParametros.put("$NomeCliente", pedidoLocker.getPedidos().getComanda().getCliente().getNome());
        hmParametros.put("$NomeDestinatario", pedidoLocker.getLocker().getConjuntoLocker().getConjunto_locker_desc());
        hmParametros.put("$EnderecoConjuntoLocker", pedidoLocker.getLocker().getConjuntoLocker().getLogradouro().getLogradouroCompleto());
        hmParametros.put("$NumeroLocker", pedidoLocker.getLocker().getLocker_desc());
        hmParametros.put("$SenhaLocker", pedidoLocker.getPedidos().getComanda().getId_comanda());

        for (String chave : hmParametros.keySet()) {
            email = email.replace(chave, hmParametros.get(chave));
        }

        return email;

    }

}
