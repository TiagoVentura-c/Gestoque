/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Helper.Estoque;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Estoque;
import model.Item;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class EstoqueControllerHelper {

    private static TelaPrincipal view;
    public EstoqueControllerHelper(TelaPrincipal view) {
        this.view = view;
    }
    
    private static List<Estoque> es;

    public static void setarTabela(List<Estoque> estoques) {
    EstoqueControllerHelper.es = estoques;
    
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Estoque_tabela().getModel();
     tableModel.setNumRows(0);
        for (Estoque e : estoques) {
            tableModel.addRow(new Object[]{
                e.getItem().getDescricao(),
                e.getItem().getCodigo(),
                e.getQuantidade() +" "+ e.getItem().getUnidade().getUnidade()
             } );
        } 
    }

     public String[] buscar() {
        String[] s = new String[2];
        
        s[0] = view.getjTextField_Estoque_descricao().getText().toString();
        s[1] = view.getjTextField_Estoque_codigp().getText().toString();
        
        return s;
    } 

    public Estoque itemSelecionado() {
        Estoque e = new Estoque();
       if(view.getjTable_Estoque_tabela().getSelectedRow() != -1)
        e = es.get(view.getjTable_Estoque_tabela().getSelectedRow());
       else{
            JOptionPane.showMessageDialog(view, "Selecione um item para editar");
           return null;
       }
        return e;
        
    }
    
}
