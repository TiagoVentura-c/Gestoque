/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Despesas;

import java.time.LocalDateTime;

/**
 *
 * @author Tiago Ventura
 */
public class Despesa {
    private int id;
    private LocalDateTime data;
    private ItemDespesa itemDespesa;
    private float valor;
    private CategoriaDespesa categoriaDespesa;

    public CategoriaDespesa getCategoriaDespesa() {
        return categoriaDespesa;
    }

    public void setCategoriaDespesa(CategoriaDespesa categoriaDespesa) {
        this.categoriaDespesa = categoriaDespesa;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public ItemDespesa getItemDespesa() {
        return itemDespesa;
    }

    public void setItemDespesa(ItemDespesa itemDespesa) {
        this.itemDespesa = itemDespesa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    
}
