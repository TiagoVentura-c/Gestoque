/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Despesa;

import controller.Utils.Util;
import java.awt.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Despesas.Despesa;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class DespesaControllerHelper {
    private final TelaPrincipal view;
    List<String> ms = Arrays.asList("JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV", "DEZ"); 
    List<Integer> anos = Arrays.asList(2021, 2022, 2023, 2024);
    
    public DespesaControllerHelper(TelaPrincipal view) {
        this.view = view;
    }
    
    public void setarData(LocalDate data) {
        DefaultComboBoxModel comboBoxModelMeses = (DefaultComboBoxModel) view.getjComboBox_Despesas_meses().getModel();
        DefaultComboBoxModel comboBoxModelAnos = (DefaultComboBoxModel) view.getjComboBox_Despesas_anos().getModel();
        
        comboBoxModelMeses.removeAllElements();
        comboBoxModelAnos.removeAllElements();
        
        for(String m: ms)
            comboBoxModelMeses.addElement(m);
        
        for(Integer ano: anos)
            comboBoxModelAnos.addElement(ano);
        
        view.getjComboBox_Despesas_meses().setSelectedIndex(data.getMonthValue()-1);
        view.getjComboBox_Despesas_anos().setSelectedItem(data.getYear()); 
        
        view.getjLabel_Despesas_despesasDe().setText("Despesas de: " + Util.obterMesEAnoEmString(data));
    }
    
    public void setarTela(List<Despesa> despesas) {
        float total=0;
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Despesas_tabela().getModel();
        
        tableModel.setNumRows(0);
        for (Despesa d: despesas) {
            tableModel.addRow(new Object[]{
                Util.obterDataEmString(d.getData()),
                d.getItemDespesa().getDescricao(),
                d.getCategoriaDespesa().getCategoria(),
                d.getValor()
             } );
            total+=d.getValor();
        }
        
        view.getjTextField_Despesas_total().setText(total+" Kz");
    }
    
    public void setarTela(List<Despesa> despesas, LocalDate d) {
        setarData(d);
        setarTela(despesas);
    }

    public LocalDate getData() {
        LocalDate d;
        int ano = anos.get(view.getjComboBox_Despesas_anos().getSelectedIndex());
        int mes = view.getjComboBox_Despesas_meses().getSelectedIndex()+1;
        
        d = LocalDate.of(ano, mes , 1);
        return d;
    }
     
    public void imprime(String msm) {
        Component rootPane = null;
        JOptionPane.showMessageDialog(rootPane, msm);
    } 
    
    
}
