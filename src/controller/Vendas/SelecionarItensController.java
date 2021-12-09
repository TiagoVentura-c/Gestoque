/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import dao.modelDao.ItemDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Compra;
import model.Item;
import view.Vendas.SelecionarItem;

/**
 *
 * @author Tiago Ventura
 */
public class SelecionarItensController {

    
   
    
    ItemDao itemDao = new ItemDao();
    
    private static List<Item> itens ;
    
    private final SelecionarItem view;

    public SelecionarItensController(SelecionarItem view){
        this.view = view;
        itens = ItemDao.listar();
    }
    
    
    public static void resetarListaItens() {
        itens = ItemDao.listar();
    }
    
    public void setarTela() {
        //itens = ItemDao.listar();
        DefaultComboBoxModel comboBoxModelItens = (DefaultComboBoxModel) view.getjComboBoxItens().getModel();
                
        for(Item i: itens)
            comboBoxModelItens.addElement(i.getDescricao() +" ("+i.getUnidade().getUnidade()+")");
                
    }

    public void adicionar() {
        Compra compra = new Compra();
        
        compra.setItem(itens.get(view.getjComboBoxItens().getSelectedIndex()));
        compra.setQuantidade(Integer.parseInt(view.getjTextFieldQuantidade().getText().toString()));
        compra.setValor((float) compra.getQuantidade() * compra.getItem().getPreco());
        
        NovaVendaController.adicionar(compra);
        
        itens.remove(view.getjComboBoxItens().getSelectedIndex());
    }
        
    
}
