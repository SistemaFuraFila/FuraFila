package furaFila.mvc.cliente.service;

import furaFila.mvc.cliente.business.ClienteBusiness;
import furaFila.mvc.cliente.model.Cliente;
import furaFila.utils.FuraFilaUtils;
import java.util.List;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class ClienteService {

    private ClienteBusiness clienteBusiness = new ClienteBusiness();

    public Cliente buscarDadosCliente(Cliente cliente) throws Exception {

        List<String> lstDados = getClienteBusiness().buscarDadosCliente(cliente);
        Integer indice = 0;
        
        Cliente c = new Cliente();
        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            c.setId_cliente(Integer.parseInt(lstDados.get(indice++)));
            c.setNome(lstDados.get(indice++));
            c.setDataNascimentoSQL(lstDados.get(indice++));
            c.setCpf(Long.parseLong(lstDados.get(indice++)));
            c.setSexo(lstDados.get(indice++).charAt(0));
            c.setTel_res(Long.parseLong(lstDados.get(indice++)));
            c.setTel_com(Long.parseLong(lstDados.get(indice++)));
            c.setCelular(Long.parseLong(lstDados.get(indice++)));
            c.setEmail(lstDados.get(indice++));
            c.setComplemento(lstDados.get(indice++));
            c.setNroCasa(Integer.parseInt(lstDados.get(indice++)));
            c.setNroApto(Integer.parseInt(lstDados.get(indice++)));
            c.getLogradouro().setNroCep(Integer.parseInt(lstDados.get(indice++)));
            c.getLogradouro().getTipoLogradouro().setDesc_tipo_logradouro(lstDados.get(indice++));
            c.getLogradouro().setLogradouro(lstDados.get(indice++));
            c.getLogradouro().getBairro().setDesc_bairro(lstDados.get(indice++));
            c.getLogradouro().getBairro().getCidade().setDesc_cidade(lstDados.get(indice++));
            c.getLogradouro().getBairro().getCidade().getUf().setSigla_uf(lstDados.get(indice++));
            c.getLogin().setUsuario(lstDados.get(indice++));
        }
        
        return c;
        
    }

    public Cliente buscarDadosBasicosCliente(Cliente cliente) throws Exception {

        List<String> lstDados = getClienteBusiness().buscarDadosBasicosCliente(cliente);
        Integer indice = 0;

        Cliente c = new Cliente();
        
        if (!FuraFilaUtils.listaVaziaNula(lstDados)) {
            c.setId_cliente(Integer.parseInt(lstDados.get(indice++)));
            c.setNome(lstDados.get(indice++));
            c.setDataNascimentoSQL(lstDados.get(indice++));
            c.setCpf(Long.parseLong(lstDados.get(indice++)));
            c.setSexo(lstDados.get(indice++).charAt(0));
            c.setTel_res(Long.parseLong(lstDados.get(indice++)));
            c.setTel_com(Long.parseLong(lstDados.get(indice++)));
            c.setCelular(Long.parseLong(lstDados.get(indice++)));
            c.setEmail(lstDados.get(indice++));
            c.setComplemento(lstDados.get(indice++));
            c.setNroCasa(Integer.parseInt(lstDados.get(indice++)));
            c.setNroApto(Integer.parseInt(lstDados.get(indice++)));
            c.getLogradouro().setNroCep(Integer.parseInt(lstDados.get(indice++)));
            c.getLogradouro().getTipoLogradouro().setDesc_tipo_logradouro(lstDados.get(indice++));
            c.getLogradouro().setLogradouro(lstDados.get(indice++));
            c.getLogradouro().getBairro().setDesc_bairro(lstDados.get(indice++));
            c.getLogradouro().getBairro().getCidade().setDesc_cidade(lstDados.get(indice++));
            c.getLogradouro().getBairro().getCidade().getUf().setSigla_uf(lstDados.get(indice++));
            c.getLogin().setUsuario(lstDados.get(indice++));
        }
        
        return c;

    }

    public ClienteBusiness getClienteBusiness() {
        return clienteBusiness;
    }

    public void setClienteBusiness(ClienteBusiness clienteBusiness) {
        this.clienteBusiness = clienteBusiness;
    }

}
