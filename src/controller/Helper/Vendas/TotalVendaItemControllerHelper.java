/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Vendas;

import controller.Utils.Util;
import java.awt.Component;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Item;
import model.VendaItem;
import view.Vendas.TotalVendasPorItem;

/**
 *
 * @author Tiago Ventura
 */
public class TotalVendaItemControllerHelper {
    
    private final TotalVendasPorItem view;
    public TotalVendaItemControllerHelper(TotalVendasPorItem view) {
        this.view = view;
    }
    
    List<String> ms = Arrays.asList("JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"); 
    List<Integer> anos = Arrays.asList(2021, 2022, 2023, 2024);

    public void setarTela(List<Item> items, Date data) {
        DefaultComboBoxModel comboBoxModelMeses = (DefaultComboBoxModel) view.getjComboBoxMes().getModel();
        DefaultComboBoxModel comboBoxModelAnos = (DefaultComboBoxModel) view.getjComboBoxAno().getModel();
        DefaultComboBoxModel comboBoxModelItens = (DefaultComboBoxModel) view.getjComboBoxItem().getModel();
        
        comboBoxModelMeses.removeAllElements();
        comboBoxModelAnos.removeAllElements();
        
        for(String m: ms)
            comboBoxModelMeses.addElement(m);
        
        for(Integer ano: anos)
            comboBoxModelAnos.addElement(ano);
        
        for(Item i: items)
            comboBoxModelItens.addElement(i.getDescricao());
        
        view.getjComboBoxMes().setSelectedIndex(data.getMonth());
        view.getjComboBoxAno().setSelectedItem(Integer.parseInt(Util.getAno(data)));
       }
    
    public Date obterMesEANoSelecionado() {
        Date d = new Date();
        d.setMonth(view.getjComboBoxMes().getSelectedIndex());
        d.setYear(anos.get(view.getjComboBoxAno().getSelectedIndex())-1900);  
        return d;
    }

    public int itemSelecionado() {
        return view.getjComboBoxItem().getSelectedIndex();
    }
   

    public void setarTabela(VendaItem vendaItems, Date data) {
     
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableTabela().getModel();
     tableModel.setNumRows(0);
            tableModel.addRow(new Object[]{
                vendaItems.getDescricao(),
                Util.getAnoEMes(data),
                vendaItems.getQuantidade() +" "+ vendaItems.getUnidade(),
                vendaItems.getTotal() +" Kz"
             } );
       view.getjLabelValorTotalDe().setText("Valor total de "+ Util.getAnoEMes(data));
       view.getjTextFieldValorTotal().setText(vendaItems.getTotal()+" Kz");
    }
    
      public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    } 

    public void setarTabela(List<VendaItem> vendasItems, Date data) {
        
        float valorTotal = 0.f;
     
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableTabela().getModel();
     tableModel.setNumRows(0);
            for(VendaItem vendaItems: vendasItems){
            tableModel.addRow(new Object[]{
                vendaItems.getDescricao(),
                Util.getAnoEMes(data),
                vendaItems.getQuantidade() +" "+ vendaItems.getUnidade(),
                vendaItems.getTotal() +" Kz"
             } );
            
            valorTotal += vendaItems.getTotal();
           }
            
       view.getjLabelValorTotalDe().setText("Valor total de "+ Util.getAnoEMes(data));
       view.getjTextFieldValorTotal().setText(valorTotal+" Kz");
    }
}
