package src.model;

import sun.util.resources.LocaleData;

public class Compra {
    private int id;
    private int id_venda;
    private int id_item;
    private int quantidade;
    private LocaleData data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
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
