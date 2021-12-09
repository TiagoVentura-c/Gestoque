/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Despesa;

import dao.modelDao.DespesaDao;
import java.awt.Component;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Despesas.CategoriaDespesa;
import model.Despesas.Despesa;
import model.Despesas.ItemDespesa;
import view.Despesas.NovaDespesa;

/**
 *
 * @author Tiago Ventura
 */
public class NovaDespesaControler {
    private final NovaDespesa view;
    private final DespesaDao despesaDao;
    
    private final List<ItemDespesa> itemsDespesas;
    private final List<CategoriaDespesa> categoriaDespesas;

    public NovaDespesaControler(NovaDespesa view) throws IOException {
        this.view = view;
        despesaDao = new DespesaDao();
        
        itemsDespesas = despesaDao.listarItem();
        categoriaDespesas = despesaDao.listarCategoria();
    }

    public void setarTela() {
        DefaultComboBoxModel comboBoxModelItems = (DefaultComboBoxModel) view.getjComboBoxItemDespesa().getModel();
        DefaultComboBoxModel comboBoxModelCategorias = (DefaultComboBoxModel) view.getjComboBoxCategoriaDespesa().getModel();
        
        itemsDespesas.forEach(i -> {
            comboBoxModelItems.addElement(i.getDescricao());
        });
        categoriaDespesas.forEach(c -> {
            comboBoxModelCategorias.addElement(c.getCategoria());
        });    
    }

    public void salvar() throws IOException {
        Despesa d = new Despesa();
        CategoriaDespesa c = categoriaDespesas.get(view.getjComboBoxCategoriaDespesa().getSelectedIndex());
        ItemDespesa i = itemsDespesas.get(view.getjComboBoxItemDespesa().getSelectedIndex());
        d.setCategoriaDespesa(c);
        d.setData(LocalDateTime.now());
        d.setItemDespesa(i);
        
        try{
            d.setValor(Integer.parseInt(view.getjTextFieldPrecoDesepesa().getText()));
            despesaDao.inserirDespesa(d);
            imprime("Despesa insirida com sucesso");
        }catch(NumberFormatException ex){
            this.imprime("Preço inválido " + ex.getMessage());
        }
        finally{
            view.dispose();
        }
        DespesasController.atualizaTabela();
    }
        
        public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    }
    
}
