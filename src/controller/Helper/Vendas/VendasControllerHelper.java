/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Vendas;

import controller.Utils.Util;
import java.awt.Component;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Venda;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class VendasControllerHelper {
    
    private final TelaPrincipal view;
    
    private static List<Venda> vendas;
    
    List<String> ms = Arrays.asList("JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"); 
    List<Integer> anos = Arrays.asList(2021, 2022, 2023, 2024);

    public VendasControllerHelper(TelaPrincipal view) {
        this.view = view;
        
    }
    
    public void setarAnoEMes(Date data){
        DefaultComboBoxModel comboBoxModelMeses = (DefaultComboBoxModel) view.getjComboBox_Vendas_meses().getModel();
        DefaultComboBoxModel comboBoxModelAnos = (DefaultComboBoxModel) view.getjComboBox_Vendas_ano().getModel();
        
        comboBoxModelMeses.removeAllElements();
        comboBoxModelAnos.removeAllElements();
        
        for(String m: ms)
            comboBoxModelMeses.addElement(m);
        
        for(Integer ano: anos)
            comboBoxModelAnos.addElement(ano);
        
        view.getjComboBox_Vendas_meses().setSelectedIndex(data.getMonth());
        view.getjComboBox_Vendas_ano().setSelectedItem(Integer.parseInt(Util.getAno(data)));
        
    }

    public void setarTela(List<Venda> vendas, Date data) { 
        setarAnoEMes(data);
        
         this.vendas = vendas;
        
        double total=0;
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Vendas_tabela().getModel();
        
        tableModel.setNumRows(0);
        for (Venda v: vendas) {
            tableModel.addRow(new Object[]{
                v.getDataFormatada(),
                v.getValorTotal() +" Kz",
                v.getNomeCliente()
             } );
            total+=v.getValorTotal();
        } 
          
        view.getjTextField_Vendas_total().setText(total+" Kz");
        view.getjLabel_Vendas_vendasDe().setText("Vendas de: "+vendas.get(0).getAnoEMes());  
    }

    public Date obterMesEANoSelecionado() {
        Date d = new Date();
        d.setMonth(view.getjComboBox_Vendas_meses().getSelectedIndex());
        d.setYear(anos.get(view.getjComboBox_Vendas_ano().getSelectedIndex())-1900);  
        return d;
    }

    public Date obterData() {
        Date date = new Date();
        String dataString = view.getjTextField_Vendas_data().getText().toString();
        
        try {       
            date = Util.setDataString(dataString);
        } catch (ParseException ex) {
            imprime("Nenhuma venda encontrada com esta data "+ ex.getMessage());
            //Logger.getLogger(VendasControllerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return date;
    }
    
    public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    } 

    public Venda obterVendaSelecionada() {
        return this.vendas.get(view.getjTable_Vendas_tabela().getSelectedRow());
    }

    
}
