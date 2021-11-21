package model;

import sun.util.resources.LocaleData;

public class Compra {
    private int id;
    private Venda venda;
    private Item item;
    private int quantidade;
    private LocaleData data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocaleData getData() {
        return data;
    }

    public void setData(LocaleData data) {
        this.data = data;
    }
}
