package furaFila.mvc.dimensao.model;

import furaFila.utils.FuraFilaUtils;

/**
 *
 * @author Gabriel Sanches Martins
 */
public class Dimensao {

    private Integer id_dimensao = 0;
    private String dimensao_desc;
    private Integer altura;
    private Integer largura;
    private Integer profundidade;

    public Integer getId_dimensao() {
        return id_dimensao;
    }

    public void setId_dimensao(Integer id_dimensao) {
        this.id_dimensao = id_dimensao;
    }

    public String getDimensao_desc() {
        return dimensao_desc;
    }

    public void setDimensao_desc(String dimensao_desc) {
        this.dimensao_desc = dimensao_desc;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        if(altura != 0)
            this.altura = altura;
    }

    public Integer getLargura() {
        return largura;
    }

    public void setLargura(Integer largura) {
        if(largura != 0)
            this.largura = largura;
    }

    public Integer getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Integer profundidade) {
        if(profundidade != 0)
            this.profundidade = profundidade;
    }

    public Integer calcularVolume(){
        return FuraFilaUtils.calcularVolume(getAltura() , getLargura(), getProfundidade());
    }
    
    public Dimensao clonar() {
        Dimensao d = new Dimensao();

        d.setAltura(getAltura());
        d.setDimensao_desc(getDimensao_desc());
        d.setId_dimensao(getId_dimensao());
        d.setLargura(getLargura());
        d.setProfundidade(getProfundidade());

        return d;
    }

}
