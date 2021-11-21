/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Itens;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import src.model.Item;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class ItensControllerHelper {
    
    private final TelaPrincipal view;
    
    public ItensControllerHelper(TelaPrincipal view){
        this.view = view;
    }
    
    public void preencherTabela(List<Item> items){
    
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Itens_TabelaItens().getModel();
     tableModel.setNumRows(0);
        for (Item item : items) {
            tableModel.addRow(new Object[]{
                item.getDescricao(),
                item.getCodigo(),
                item.getId_categoria(),
                item.getPreco(),
                item.getId_unidade()
             } );
        }
        
    }
    
}
