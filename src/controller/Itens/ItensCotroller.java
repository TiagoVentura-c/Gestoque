/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Itens;

import controller.Helper.Itens.ItensControllerHelper;
import dao.modelDao.ItemDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import model.Item;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */

public class ItensCotroller {

    private final ItensControllerHelper helper;
    private static TelaPrincipal view;
    static DefaultTableModel tableModel;
    
    public ItensCotroller(TelaPrincipal view) {
        this.view = view;
        this.helper = new ItensControllerHelper(view);
    }
    
    public void preencherTabela(){
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.listar();
        helper.preencherTabela(items);
    }
    
    public static void preencherTabelaBackground(){
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.listar();
        
        tableModel =   (DefaultTableModel) view.getjTable_Itens_TabelaItens().getModel();
        tableModel.setNumRows(0);
        for (Item item : items) {
            tableModel.addRow(new Object[]{
                item.getDescricao(),
                item.getCodigo(),
                item.getCategoria().getCategoria(),
                item.getPreco(),
                item.getUnidade().getUnidade()
             } );
        }
        
    }

   
    public void buscar() {
        // 0-> descricao
        // 1-> codigo
        String []s = this.helper.buscar();
        
        ItemDao itemDao = new ItemDao();
        List<Item> items = new ArrayList<>();
        
        if(!s[0].isEmpty() && s[1].isEmpty()){
         items = itemDao.buscar("descricao",s[0]);
         helper.preencherTabela(items);
        } 
        else if(s[0].isEmpty() && !s[1].isEmpty()){
            items = itemDao.buscar("codigo" ,s[1]);
            helper.preencherTabela(items);
        }
        else{
            items = itemDao.listar();
            helper.preencherTabela(items);
        }
               
    }

    public Item itemSelecionado() {
        return this.helper.itemSelecionado();
    }
    
    
    
}
