
package furaFila.mvc.estoque.model;

import furaFila.mvc.estabelecimento.model.Estabelecimento;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Estoque {
    private Integer id_estoque = 0;
    private Estabelecimento estabelecimento = new Estabelecimento();

    public Integer getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(Integer id_estoque) {
        this.id_estoque = id_estoque;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
   public Estoque Clonar(){
        Estoque estoque = new Estoque();
        
        estoque.setEstabelecimento(getEstabelecimento());
        estoque.setId_estoque(getId_estoque());
        
        return estoque;
    }
            
}
