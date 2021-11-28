/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Vendas;

import controller.Utils.Util;
import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
    
    public void setarAnoEMes(LocalDateTime data){
        DefaultComboBoxModel comboBoxModelMeses = (DefaultComboBoxModel) view.getjComboBox_Vendas_meses().getModel();
        DefaultComboBoxModel comboBoxModelAnos = (DefaultComboBoxModel) view.getjComboBox_Vendas_ano().getModel();
        
        comboBoxModelMeses.removeAllElements();
        comboBoxModelAnos.removeAllElements();
        
        for(String m: ms)
            comboBoxModelMeses.addElement(m);
        
        for(Integer ano: anos)
            comboBoxModelAnos.addElement(ano);
        
        view.getjComboBox_Vendas_meses().setSelectedIndex(data.getMonthValue()-1);
        view.getjComboBox_Vendas_ano().setSelectedItem(data.getYear());    
    }

    public void setarTela(List<Venda> vendas, LocalDateTime data) { 
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
        view.getjLabel_Vendas_vendasDe().setText("Vendas de: "+Util.obterMesEAnoEmString(data));  
    }

    public LocalDateTime obterMesEANoSelecionado() {
        LocalDateTime d;
        int ano = anos.get(view.getjComboBox_Vendas_ano().getSelectedIndex());
        int mes = view.getjComboBox_Vendas_meses().getSelectedIndex()+1;
        
        System.out.println(ano +" "+mes);
        d = LocalDateTime.of(ano, mes , 1, 1, 1);
         
        return d;
    }

    public LocalDate obterData() {
        LocalDate date = null;
        
        String dataString = view.getjTextField_Vendas_data().getText(); 
        try{
            date = Util.formatarStringEmDataBarra(dataString);
        } catch(Exception ex){
            imprime("Data inválida");
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

    public void setarTelaVazia(LocalDateTime data) {
        setarAnoEMes(data);
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Vendas_tabela().getModel();
        tableModel.setNumRows(0);
        
        view.getjTextField_Vendas_total().setText(0+" Kz");
        view.getjLabel_Vendas_vendasDe().setText("Vendas de: "+ Util.obterMesEAnoEmString(data));
        
    }

    
}
