package br.com.constran.mobile.persistence.vo;

/**
 * Created by moises_santana on 11/03/2015.
 */
public class EquipamentoCategoriaVO extends AbstractVO {

    //atributos herdados de AbstractVO


    @Override
    public String toString() {
        return getId()+" - "+getDescricao();
    }
}
