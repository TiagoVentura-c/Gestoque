/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Vendas;

import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Compra;
import model.Item;
import model.Venda;
import view.Vendas.NovoVenda;

/**
 *
 * @author Tiago Ventura
 */
public class NovaVendaControllerHelper {
    
    private final NovoVenda view;
    
    private float valorTotal=0;

    public NovaVendaControllerHelper(NovoVenda view) {
        this.view = view;
    }

    public void setartabela(List<Compra> compras) {
        valorTotal=0;
        
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableCompras().getModel();
        tableModel.setNumRows(0);
        for (Compra compra : compras) {
            tableModel.addRow(new Object[]{
                compra.getItem().getDescricao(),
                compra.getQuantidade()+" "+compra.getItem().getUnidade().getUnidade()+" x "+compra.getItem().getPreco() +" Kz"
             } );
            
            valorTotal += compra.getItem().getPreco() * compra.getQuantidade();
        } 
        
        view.getjTextFieldValorTotal().setText(valorTotal+" Kz");        
    }

    public int linhaSelecionada() {
        return view.getjTableCompras().getSelectedRow();
    }

    public Venda obterVenda() {
        Venda v = new Venda();
        v.setValorTotal(valorTotal);
        v.setNomeCliente(view.getjTextFieldNomeCliente().getText().toString());        
        return v;
    }
    
     public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    } 
    
}
