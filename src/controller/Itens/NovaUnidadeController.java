/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Itens;

import dao.modelDao.UnidadeDao;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.Unidade;
import view.Itens.NovaUnidade;
import view.Itens.NovoItem;

/**
 *
 * @author Tiago Ventura
 */
public class NovaUnidadeController {
    private final NovaUnidade view;
    
    public UnidadeDao unidadeDao = new UnidadeDao();

    public NovaUnidadeController(NovaUnidade view) {
        this.view = view;
    }

    public void salvar() throws IOException {
        Unidade unidade = new Unidade();
        unidade.setUnidade(view.getjTextFieldUnidade().getText().toString());
        
        unidadeDao.inserir(unidade);
        
        JOptionPane.showMessageDialog(null, "Unidade cadastrada com sucesso");
        NovoItem.controller.preencherTela();
        
        view.dispose();
    }
    
    
}
