/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Vendas;

import controller.Utils.Util;
import java.awt.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void setarTela(List<Item> items, LocalDateTime data) {
        DefaultComboBoxModel comboBoxModelMeses = (DefaultComboBoxModel) view.getjComboBoxMes().getModel();
        DefaultComboBoxModel comboBoxModelAnos = (DefaultComboBoxModel) view.getjComboBoxAno().getModel();
 
        
        comboBoxModelMeses.removeAllElements();
        comboBoxModelAnos.removeAllElements();
        
        ms.forEach(m -> {
            comboBoxModelMeses.addElement(m);
        });
        
        anos.forEach(ano -> {
            comboBoxModelAnos.addElement(ano);
        });
         
        
        view.getjComboBoxMes().setSelectedIndex(data.getMonthValue()-1);
        view.getjComboBoxAno().setSelectedItem(data.getYear());
       }
    
    public LocalDateTime obterMesEANoSelecionado() {
        LocalDateTime d;
        int ano = anos.get(view.getjComboBoxAno().getSelectedIndex());
        int mes = view.getjComboBoxMes().getSelectedIndex()+1;
        
        System.out.println(ano +" "+mes);
        d = LocalDateTime.of(ano, mes , 1, 1, 1);
        
        return d;
    }
 

    public void setarTabela(VendaItem vendaItems, LocalDateTime data) {
     
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableTabela().getModel();
     tableModel.setNumRows(0);
            tableModel.addRow(new Object[]{
                vendaItems.getDescricao(),
                Util.obterMesEAnoEmString(data),
                vendaItems.getQuantidade() +" "+ vendaItems.getUnidade(),
                vendaItems.getTotal() +" Kz"
             } );
       view.getjLabelValorTotalDe().setText("Valor total de "+ Util.obterMesEAnoEmString(data));
       view.getjTextFieldValorTotal().setText(vendaItems.getTotal()+" Kz");
    }
    
      public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    } 

    public void setarTabela(List<VendaItem> vendasItems, LocalDateTime data) {
        
     float valorTotal = 0.f;
     
     
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableTabela().getModel();
     tableModel.setNumRows(0);
     
            for(VendaItem vendaItems: vendasItems){
                System.out.println(vendaItems.getQuantidade());
                tableModel.addRow(new Object[]{
                vendaItems.getDescricao(),
                Util.obterMesEAnoEmString(vendaItems.getData()),
                vendaItems.getQuantidade() +" "+ vendaItems.getUnidade(),
                vendaItems.getTotal() +" Kz"
             } );
            
            valorTotal += vendaItems.getTotal();
           }
            
            
       view.getjLabelValorTotalDe().setText("Valor total de "+ Util.obterMesEAnoEmString(data));
       view.getjTextFieldValorTotal().setText(valorTotal+" Kz");
           
    }
    
    public List<List<String>> matriz(){
        int columnCount = view.getjTableTabela().getColumnCount();
        int rowCount = view.getjTableTabela().getRowCount();
        
        List<String> Colunas = new ArrayList<>();
        List<List<String>> compras = new ArrayList<>();
        
        for (int i  = 0; i < columnCount; i++) {
            Colunas.add(view.getjTableTabela().getColumnName(i));
        }
        
        compras.add(Colunas);
  
        for (int i = 0; i < rowCount; i++) {
            List<String> c = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                String valueAt = view.getjTableTabela().getValueAt(i, j).toString();
                c.add(valueAt);
            }
            compras.add(c);
        }
        
        return compras;
    }
    
    
}
