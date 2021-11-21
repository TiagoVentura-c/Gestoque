/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Itens;

import dao.modelDao.CategoriaDao;
import javax.swing.JOptionPane;
import model.Categoria;
import view.Itens.NovaCategoria;

/**
 *
 * @author Tiago Ventura
 */
public class NovaCategoriaController {
    private final NovaCategoria view;
    private CategoriaDao categoriaDao = new CategoriaDao();

    public NovaCategoriaController(NovaCategoria view) {
        this.view = view;
    }

    public void salvar() {
        Categoria categoria = new Categoria();
        categoria.setCategoria(view.getjTextFieldCategoria().getText().toString());
        
        categoriaDao.inserir(categoria);
        JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso");
        view.dispose();
    }
    
    
    
}
