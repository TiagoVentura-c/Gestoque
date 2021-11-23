/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Estoque;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Estoque;
import model.Item;
import view.Estoque.NovoItemEstoque;

/**
 *
 * @author Tiago Ventura
 */
public class NovoItemEstoqueControllerHelper {
    private final NovoItemEstoque view;
    private List<Item> items;

    public NovoItemEstoqueControllerHelper(NovoItemEstoque view) {
        this.view = view;
    }

    public void setarTela(List<Item> items) {
        this.items = items;
        
        DefaultComboBoxModel comboBoxModelItems = (DefaultComboBoxModel) view.getjComboBoxItems().getModel();
        
        for (Item i : items)
            comboBoxModelItems.addElement(i.getDescricao()  + ", uni.: " + i.getUnidade().getUnidade());   
        
    }

    public Estoque obter() {
        Estoque e = new Estoque();
        Item i = new Item();
        i.setId(this.items.get(view.getjComboBoxItems().getSelectedIndex()).getId());
        
        e.setItem(i);
        e.setQuantidade(Integer.parseInt(view.getjTextFielQuantidade().getText().toString()));
        
        return e;
    }
    
    public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    }

    public  void setarTelaEditar(Estoque e) {
        
        //System.out.println(e.getItem().getDescricao()+" ********************");
       
        DefaultComboBoxModel comboBoxModelItems = (DefaultComboBoxModel) view.getjComboBoxItems().getModel();
        
        comboBoxModelItems.addElement(e.getItem().getDescricao()  + ", uni.: " + e.getItem().getUnidade().getUnidade());
        view.getjTextFielQuantidade().setText(e.getQuantidade()+"");
        
        view.getjComboBoxItems().enable(false);
    }
}
