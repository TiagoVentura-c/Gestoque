/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Itens;

import controller.Helper.Itens.EditarItemControllerHelper;
import dao.modelDao.CategoriaDao;
import dao.modelDao.ItemDao;
import dao.modelDao.UnidadeDao;
import java.util.List;
import model.Categoria;
import model.Item;
import model.Unidade;
import view.Itens.EditarItem;

/**
 **
 **@author Tiago Ventura
 **/
public class EditarItemController {
    private static EditarItem view;

    
    
    private final EditarItemControllerHelper helper;
    
    private UnidadeDao unidadeDao = new UnidadeDao();
    private CategoriaDao categoriaDao = new CategoriaDao();
    private ItemDao itemDao = new ItemDao();
    
    private int id;
    
    public EditarItemController(EditarItem view){
        this.view = view;
        this.helper = new EditarItemControllerHelper(view);
    }
 
    public  void salvarItem() {
        Item i = this.helper.getItem();
        i.setId(this.id);
        //TODO verificaçoes
        
        ItemDao.actualizar(i);
        
        this.helper.imprime("Item actualizado com sucesso");
        view.dispose();

        ItensCotroller.preencherTabelaBackground();
    }

    public void preencherTela(Item i) {
        List<Unidade> unidades = unidadeDao.listar();
        List<Categoria> categorias = categoriaDao.listar();
        this.id=i.getId();
        helper.setarTela(i, unidades, categorias);        
    }
    
    
    
}
