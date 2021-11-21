/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Itens;

import controller.Helper.Itens.NovoItemControllerHelper;
import dao.modelDao.CategoriaDao;
import dao.modelDao.ItemDao;
import dao.modelDao.UnidadeDao;
import java.util.List;
import model.Categoria;
import model.Item;
import model.Unidade;
import view.Itens.NovoItem;

/**
 **
 **@author Tiago Ventura
 **/
public class NovoItemController {
    
    private final NovoItemControllerHelper helper;
    
    private static UnidadeDao unidadeDao = new UnidadeDao();
    private static CategoriaDao categoriaDao = new CategoriaDao();
    private ItemDao itemDao = new ItemDao();
    

    
    public NovoItemController(NovoItem view){
        this.helper = new NovoItemControllerHelper(view);
    }

    public  void salvarItem() {
        Item i = this.helper.getItem();
        
        //TODO verificaçoes
        
        ItemDao.inserir(i);
        
        this.helper.imprime("Item cadastrado com sucesso");
        preencherTela();
        ItensCotroller.preencherTabelaBackground();
    }

    public void preencherTela() {
        List<Unidade> unidades = unidadeDao.listar();
        List<Categoria> categorias = categoriaDao.listar();
        
        helper.setarTela(unidades, categorias);        
    }
    
    
    
}
