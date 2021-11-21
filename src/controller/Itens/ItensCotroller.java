/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Itens;

import controller.Helper.Itens.ItensControllerHelper;
import dao.modelDao.ItemDao;
import java.util.List;
import src.model.Item;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */

public class ItensCotroller {

    private final ItensControllerHelper helper;
    
    public ItensCotroller(TelaPrincipal view) {
        this.helper = new ItensControllerHelper(view);
        preencherTabela();
    }
    
    public void preencherTabela(){
        ItemDao itemDao = new ItemDao();
        List<Item> items = itemDao.listar();
        helper.preencherTabela(items);
        
    }
    
    
}
