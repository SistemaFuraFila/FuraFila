package furaFila.mvc.cep.service;

import furaFila.mvc.cep.model.Distancia;
import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.utils.FuraFilaConstants;
import furaFila.utils.WebServices;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class CepService {

    private WebServices webService = new WebServices();

    public Integer pesquisarCep(Logradouro logradouro) throws Exception {

//        Integer retornoServidor = 1;
//        logradouro.getBairro().getCidade().getUf().setSigla_uf("SP");
//        logradouro.getBairro().getCidade().setDesc_cidade("Carapícuíba");
//        logradouro.getBairro().setDesc_bairro("Parque Jandaia");
//        logradouro.getTipoLogradouro().setDesc_tipo_logradouro("Rua");
//        logradouro.setLogradouro("José Altemir Pereira");
        Integer retornoServidor = 0;
        Element raiz = getWebService().pesquisarCep(logradouro);

        for (Iterator i = raiz.elementIterator(); i.hasNext();) {

            Element elemento = (Element) i.next();

            if (elemento.getQualifiedName().equals(FuraFilaConstants.UF)) {
                logradouro.getBairro().getCidade().getUf().setSigla_uf(elemento.getText());
            }

            if (elemento.getQualifiedName().equals(FuraFilaConstants.CIDADE)) {
                logradouro.getBairro().getCidade().setDesc_cidade(elemento.getText());
            }

            if (elemento.getQualifiedName().equals(FuraFilaConstants.BAIRRO)) {
                logradouro.getBairro().setDesc_bairro(elemento.getText());
            }

            if (elemento.getQualifiedName().equals(FuraFilaConstants.TIPO_LOGRADOURO)) {
                logradouro.getTipoLogradouro().setDesc_tipo_logradouro(elemento.getText());
            }

            if (elemento.getQualifiedName().equals(FuraFilaConstants.LOGRADOURO)) {
                logradouro.setLogradouro(elemento.getText());
            }

            if (elemento.getQualifiedName().equals(FuraFilaConstants.RESULTADO)) {
                retornoServidor = Integer.parseInt(elemento.getText());
            }

        }
        return retornoServidor;

    }

    public List<Double> pegarGeolocalizacao(Logradouro logradouro) throws Exception {

        Element raiz = getWebService().pesquisarCepGoogle(logradouro);
        List<Double> geoLocalizacao = new ArrayList<>();

        for (Iterator i = raiz.elementIterator(); i.hasNext();) {

            Element elemento = (Element) i.next();

            if (elemento.getQualifiedName().equals("result")) {
                for (Iterator iResult = elemento.elementIterator(); iResult.hasNext();) {
                    Element result = (Element) iResult.next();

                    if (result.getQualifiedName().equals("geometry")) {
                        for (Iterator iGeometry = result.elementIterator(); iGeometry.hasNext();) {
                            Element geometry = (Element) iGeometry.next();

                            if (geometry.getQualifiedName().equals("location")) {
                                for (Iterator iLocation = geometry.elementIterator(); iLocation.hasNext();) {
                                    Element location = (Element) iLocation.next();
                                    geoLocalizacao.add(Double.parseDouble(location.getText()));
                                }
                            }
                        }
                    }
                }
            }
        }

        return geoLocalizacao;

    }

    public void pegarCepLatiLong(Logradouro logradouro) throws Exception {

        Element raiz = getWebService().pesquisarCepViaLongLati(logradouro);
        Element resultado;

        for (Iterator status = raiz.elementIterator(); status.hasNext();) {
            resultado = (Element) status.next();

            if (resultado.getQualifiedName().equals("status") & "OK".equalsIgnoreCase(resultado.getTextTrim())) {
                for (Iterator result = raiz.elementIterator(); result.hasNext();) {

                    //TERMINAR ESTA BAGAÇA DEPOIS
                }

            }

        }

    }

    public void calcularDistancia(Distancia distancia) throws Exception {

        Element raiz = getWebService().calcularDistancia(distancia);

        for (Iterator i = raiz.elementIterator(); i.hasNext();) {
            Element resultado = (Element) i.next();

            for (Iterator iRow = resultado.elementIterator(); iRow.hasNext();) {
                Element row = (Element) iRow.next();

                for (Iterator iElement = row.elementIterator(); iElement.hasNext();) {
                    Element element = (Element) iElement.next();

                    if (element.getQualifiedName().equals("duration")) {
                        for (Iterator iDuration = element.elementIterator(); iDuration.hasNext();) {
                            Element duration = (Element) iDuration.next();
                            if (duration.getQualifiedName().equals("value")) {
                                distancia.setDuracao(Integer.parseInt(duration.getTextTrim()));
                            }
                        }
                    }

                    if (element.getQualifiedName().equals("distance")) {
                        for (Iterator iDistance = element.elementIterator(); iDistance.hasNext();) {
                            Element distance = (Element) iDistance.next();
                            if (distance.getQualifiedName().equals("value")) {
                                distancia.setDistancia(Integer.parseInt(distance.getTextTrim()));
                            }
                        }
                    }
                }
            }
        }
    }

    public WebServices getWebService() {
        return webService;
    }

    public void setWebService(WebServices webService) {
        this.webService = webService;
    }

}
