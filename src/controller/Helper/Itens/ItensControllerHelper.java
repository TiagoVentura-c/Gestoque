package controller.Helper.Itens;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import model.Item;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class ItensControllerHelper {
    
    private final TelaPrincipal view;
    private List<Item> items;
    
    public ItensControllerHelper(TelaPrincipal view){
        this.view = view;
    }
    
    public void preencherTabela(List<Item> items){
    this.items = items;
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Itens_TabelaItens().getModel();
     tableModel.setNumRows(0);
     items.forEach(item -> {
         tableModel.addRow(new Object[]{
             item.getDescricao(),
             item.getCodigo(),
             item.getCategoria().getCategoria(),
             item.getPreco(),
             item.getUnidade().getUnidade()
         } );
        });        
    }
    public void preencherTabelaVazia(){
    
     DefaultTableModel tableModel =   (DefaultTableModel) view.getjTable_Itens_TabelaItens().getModel();
     tableModel.setNumRows(0);
               
    }

    public String[] buscar() {
        String[] s = new String[2];
        
        s[0] = view.getjTextField_Itens_Descricao().getText().toString();
        s[1] = view.getjTextField_Itens_Codigo().getText().toString();
        
        return s;
    }

    public void preencherTabelaBack(List<Item> items) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Item itemSelecionado() {
        int i = view.getjTable_Itens_TabelaItens().getSelectedRow();
        return items.get(i);
    }
    
}
