/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import dao.modelDao.CompraDao;
import java.text.ParseException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Compra;
import model.Venda;
import view.Vendas.DetalheVenda;

/**
 *
 * @author Tiago Ventura
 */
public class DetalheVendaController {

    
    private static DetalheVenda view;
    private static CompraDao compraDao = new CompraDao();

    public DetalheVendaController(DetalheVenda view) {
        this.view = view;
    }
    
    
    public static void setarTela(Venda v) throws ParseException {
        
        List<Compra> compras = compraDao.buscarCompras(v.getId());
               
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableDetalhe().getModel();
        
        tableModel.setNumRows(0);
        for (Compra c: compras) {
            tableModel.addRow(new Object[]{
                c.getItem().getDescricao(),
                c.getQuantidade() +" "+c.getItem().getUnidade().getUnidade(),
                c.getValor()
             });
        }
        
        view.getjTextFieldNomeCliente().setText(v.getNomeCliente());
        view.getjTextFieldDataCompra().setText(v.getDataFormatada());
        view.getjTextFieldValorTotal().setText(v.getValorTotal()+" KZ");
            
    }
    
}
