/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Despesa;

import dao.modelDao.DespesaDao;
import java.awt.Component;
import javax.swing.JOptionPane;
import model.Despesas.ItemDespesa;
import view.Despesas.NovoItem;

/**
 *
 * @author Tiago Ventura
 */
public class NovoItemController {
    private final NovoItem view;
    private final DespesaDao despesaDao;

    public NovoItemController(NovoItem view) {
        this.view = view;
        despesaDao = new DespesaDao();
    }

    public void salvar() {
        ItemDespesa id = new ItemDespesa();
        id.setDescricao(view.getjTextFieldCategoria().getText());
        despesaDao.inserirItem(id);
        imprime("Item adicionado com succeso");
        view.dispose();
    }
    
    public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    } 
    
}
