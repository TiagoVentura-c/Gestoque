/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Itens;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Estoque;
import model.Item;
import model.Unidade;
import view.Estoque.NovoItemEstoque;
import view.Itens.NovoItem;

/**
 *
 * @author Tiago Ventura
 */
public class NovoItemControllerHelper {
    
    private static NovoItem view;
    
    private List<Unidade> unidades;
    private List<Categoria> categorias;

    public NovoItemControllerHelper(NovoItem view) {
        this.view = view;
    }

    public void setarTela(List<Unidade> unidades, List<Categoria> categorias) {
        view.getjTextFieldCodigo().setText("");
        view.getjTextFieldDescricao().setText("");
        view.getjTextFieldPreco().setText("");
        
        DefaultComboBoxModel comboBoxModelUnidades = (DefaultComboBoxModel) view.getjComboBoxUnidade().getModel();
        DefaultComboBoxModel comboBoxModelCategorias = (DefaultComboBoxModel) view.getjComboBoxCategoria().getModel();
        
        
        for (Unidade u : unidades)
            comboBoxModelUnidades.addElement(u.getUnidade());   

        for (Categoria c : categorias)
            comboBoxModelCategorias.addElement(c.getCategoria());   
        
        this.unidades=unidades;
        this.categorias=categorias;
    }

    public Item getItem() {
        Item i = new Item();
        Unidade u = new Unidade();
        Categoria c = new Categoria();
        
        i.setCodigo(view.getjTextFieldCodigo().getText().toString());
        i.setDescricao(view.getjTextFieldDescricao().getText().toString());
        i.setPreco(Double.parseDouble(view.getjTextFieldPreco().getText().toString()));
        
        u.setId(obterIdUnidadeSelecionado());
        c.setId(obterIdCategoriaSelecionado());
        
        i.setCategoria(c);
        i.setUnidade(u);
        
        return i;        
    }

    private int obterIdUnidadeSelecionado() {
        return this.unidades.get(view.getjComboBoxUnidade().getSelectedIndex()).getId();
    }
    
    private int obterIdCategoriaSelecionado() {
        return this.categorias.get(view.getjComboBoxCategoria().getSelectedIndex()).getId();
    }

    public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    }   
    
}
