
package furaFila.mvc.formaPagamento.model;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class FormaPagamento {
    private Integer id_forma_pagamento = 0;
    private String forma_pagamento = "";

    public Integer getId_forma_pagamento() {
        return id_forma_pagamento;
    }

    public void setId_forma_pagamento(Integer id_forma_pagamento) {
        this.id_forma_pagamento = id_forma_pagamento;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }
    
    public FormaPagamento Clonar(){
    FormaPagamento forma = new FormaPagamento();
        
        forma.setId_forma_pagamento(getId_forma_pagamento());
        forma.setForma_pagamento(getForma_pagamento());
       
        return forma;
    }
    
}
