package furaFila.mvc.cep.model;

import furaFila.mvc.logradouro.model.Logradouro;
import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Wellington Gonçalves Pires
 */
public class Distancia {
    
    private Logradouro origem = new Logradouro();
    private Logradouro destino = new Logradouro();
    private Integer duracao;
    private Integer distancia;

    public Logradouro getOrigem() {
        return origem;
    }

    public void setOrigem(Logradouro origem) {
        this.origem = origem;
    }

    public Logradouro getDestino() {
        return destino;
    }

    public void setDestino(Logradouro destino) {
        this.destino = destino;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }
    
}
