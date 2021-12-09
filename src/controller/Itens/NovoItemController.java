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
import java.io.IOException;
import java.sql.SQLException;
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
    private final NovoItem view;
    
    private static UnidadeDao unidadeDao = new UnidadeDao();
    private static CategoriaDao categoriaDao = new CategoriaDao();
    private ItemDao itemDao = new ItemDao();
    

    
    public NovoItemController(NovoItem view){
        this.helper = new NovoItemControllerHelper(view);
        this.view = view;
    }

    public  void salvarItem() throws IOException, ClassNotFoundException, SQLException {
        Item i = this.helper.getItem();
        
        //TODO verificaçoes
        
        ItemDao.inserir(i);
        
        this.helper.imprime("Item cadastrado com sucesso");
        view.dispose();
        preencherTela();
        ItensCotroller.preencherTabelaBackground();
        
    }

    public void preencherTela() throws IOException {
        List<Unidade> unidades = unidadeDao.listar();
        List<Categoria> categorias = categoriaDao.listar();
        
        helper.setarTela(unidades, categorias);        
    }
    
    
    
}
