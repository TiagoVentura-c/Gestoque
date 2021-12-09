/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import com.itextpdf.text.DocumentException;
import controller.Utils.ImprimirDetalheVenda;
import dao.modelDao.CompraDao;
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
    private static final CompraDao compraDao = new CompraDao();

    public DetalheVendaController(DetalheVenda view) {
        DetalheVendaController.view = view;
    }
    
    
    public static void setarTela(Venda v) throws ParseException, IOException {
        
        List<Compra> compras = CompraDao.buscarCompras(v.getId());
               
        DefaultTableModel tableModel =   (DefaultTableModel) view.getjTableDetalhe().getModel();
        
        tableModel.setNumRows(0);
        compras.forEach(c -> {
            tableModel.addRow(new Object[]{
                c.getItem().getDescricao(),
                c.getQuantidade() +" "+c.getItem().getUnidade().getUnidade(),
                c.getValor()/c.getQuantidade() +" X " + c.getQuantidade()
            });
        });
        
        view.getjTextFieldNomeCliente().setText(v.getNomeCliente());
        view.getjTextFieldDataCompra().setText(v.getDataFormatada());
        view.getjTextFieldValorTotal().setText(v.getValorTotal()+" KZ");
            
    }

    public void imprimir() throws FileNotFoundException, DocumentException {
        int columnCount = view.getjTableDetalhe().getColumnCount();
        int rowCount = view.getjTableDetalhe().getRowCount();
        
        List<String> Colunas = new ArrayList<>();
        List<List<String>> compras = new ArrayList<>();
        
        for (int i  = 0; i < columnCount; i++) {
            Colunas.add(view.getjTableDetalhe().getColumnName(i));
        }
        
        
        compras.add(Colunas);
  
        for (int i = 0; i < rowCount; i++) {
            List<String> c = new ArrayList<>();
            for (int j = 0; j < columnCount; j++) {
                String valueAt = view.getjTableDetalhe().getValueAt(i, j).toString();
                c.add(valueAt);
            }
            compras.add(c);
        }
        
        String nomeCiente = view.getjTextFieldNomeCliente().getText();
        String data = view.getjTextFieldDataCompra().getText();
        String valorTotal = view.getjTextFieldValorTotal().getText();
        ImprimirDetalheVenda idv = new ImprimirDetalheVenda();
        idv.imprimirDetalheVenda("Detalhe de venda", nomeCiente, data, valorTotal, compras);
        
    }
    
}
