/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Despesa;

import dao.modelDao.DespesaDao;
import java.awt.Component;
import javax.swing.JOptionPane;
import model.Despesas.CategoriaDespesa;
import view.Despesas.NovaCategoria;

/**
 *
 * @author Tiago Ventura
 */
public class NovaCategoriaController {
    private final NovaCategoria view;
    private final DespesaDao despesaDao;

    public NovaCategoriaController(NovaCategoria view) {
        this.view = view;
        despesaDao = new DespesaDao();
    }

    public void salvar() {
        CategoriaDespesa cd = new CategoriaDespesa();
        despesaDao.inserirCategoria(cd);
        imprime("Categoria adicionada com succeso");
        view.dispose();
    }
    
    public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    }
}
