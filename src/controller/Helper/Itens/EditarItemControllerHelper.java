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
import model.Item;
import model.Unidade;
import view.Itens.EditarItem;
import view.Itens.NovoItem;

/**
 *
 * @author Tiago Ventura
 */
public class EditarItemControllerHelper {
    
    private static EditarItem view;
    
    private List<Unidade> unidades;
    private List<Categoria> categorias;

    public EditarItemControllerHelper(EditarItem view) {
        this.view = view;
    }

    public void setarTela(Item i, List<Unidade> unidades, List<Categoria> categorias) {
        view.getjTextFieldCodigo().setText(i.getCodigo());
        view.getjTextFieldDescricao().setText(i.getDescricao());
        view.getjTextFieldPreco().setText(""+i.getPreco());
        
        DefaultComboBoxModel comboBoxModelUnidades = (DefaultComboBoxModel) view.getjComboBoxUnidade().getModel();
        DefaultComboBoxModel comboBoxModelCategorias = (DefaultComboBoxModel) view.getjComboBoxCategoria().getModel();
        
        
        for (Unidade u : unidades)
            comboBoxModelUnidades.addElement(u.getUnidade());   

        for (Categoria c : categorias)
            comboBoxModelCategorias.addElement(c.getCategoria());
        
        comboBoxModelUnidades.setSelectedItem(i.getUnidade().getUnidade());
        comboBoxModelCategorias.setSelectedItem(i.getCategoria().getCategoria());   
        
        this.unidades=unidades;
        this.categorias=categorias;
    }

    public Item getItem() {
        Item i = new Item();
        Unidade u = new Unidade();
        Categoria c = new Categoria();
        
        i.setCodigo(view.getjTextFieldCodigo().getText().toString());
        i.setDescricao(view.getjTextFieldDescricao().getText().toString());
        i.setPreco(Float.parseFloat(view.getjTextFieldPreco().getText().toString()));
        
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
