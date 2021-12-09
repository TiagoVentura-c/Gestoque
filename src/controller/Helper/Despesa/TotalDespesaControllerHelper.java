/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Despesa;

import controller.Utils.Util;
import java.awt.Component;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Despesas.Despesa;
import model.Despesas.DetalheGasto;
import view.Despesas.TotalDespesasPorItem;

/**
 *
 * @author Tiago Ventura
 */
public class TotalDespesaControllerHelper {
    private final TotalDespesasPorItem view;
    List<String> ms = Arrays.asList("JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"); 
    List<Integer> anos = Arrays.asList(2021, 2022, 2023, 2024);

    public TotalDespesaControllerHelper(TotalDespesasPorItem view) {
        this.view = view;
    }
    
    
    public void setarData(LocalDate data) {
        DefaultComboBoxModel comboBoxModelMeses = (DefaultComboBoxModel) view.getjComboBoxMes().getModel();
        DefaultComboBoxModel comboBoxModelAnos = (DefaultComboBoxModel) view.getjComboBoxAno().getModel();
        
        comboBoxModelMeses.removeAllElements();
        comboBoxModelAnos.removeAllElements();
        
        for(String m: ms)
            comboBoxModelMeses.addElement(m);
        
        for(Integer ano: anos)
            comboBoxModelAnos.addElement(ano);
        
        view.getjComboBoxMes().setSelectedIndex(data.getMonthValue()-1);
        view.getjComboBoxAno().setSelectedItem(data.getYear()); 
        
        view.getjLabelValorTotalDe().setText("Valor total gasto de: " + Util.obterMesEAnoEmString(data));
    }

    public void setarTabela(List<DetalheGasto> lista, LocalDate data) {
        setarData(data);
        float total=0;
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableTabela().getModel();
        
        tableModel.setNumRows(0);
        for (DetalheGasto d: lista) {
            tableModel.addRow(new Object[]{
                d.getItem(),
                d.getData(),
                d.getTotal()
             } );
            total+=d.getTotal();
        }
        
        view.getjTextFieldValorTotal().setText(total+" Kz");
    }
    
     public LocalDate getData() {
        LocalDate d;
        int ano = anos.get(view.getjComboBoxAno().getSelectedIndex());
        int mes = view.getjComboBoxMes().getSelectedIndex()+1;
        
         System.out.println(ano +" "+ mes);
        
        d = LocalDate.of(ano, mes , 1);
        return d;
    }
    
     public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
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
