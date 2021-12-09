/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import com.itextpdf.text.DocumentException;
import controller.Helper.Vendas.TotalVendaItemControllerHelper;
import controller.Utils.Impressao.ImprimirValorItens;
import controller.Utils.Util;
import dao.modelDao.CompraDao;
import dao.modelDao.ItemDao;
import dao.modelDao.VendaDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import model.Item;
import model.VendaItem;
import view.Vendas.TotalVendasPorItem;

/**
 *
 * @author Tiago Ventura
 */
public class TotalVendaItemController {
    
    private final TotalVendaItemControllerHelper helper;
    
    private final ItemDao itemDao = new ItemDao();
    private final VendaDao vendaDao = new VendaDao();
    private final CompraDao compraDao = new CompraDao();
    private final TotalVendasPorItem view;
    private static LocalDateTime localDate;
    
    private final List<Item> items;
    
    public TotalVendaItemController(TotalVendasPorItem view) throws IOException, ClassNotFoundException, SQLException {
        this.view = view;
        this.helper = new TotalVendaItemControllerHelper(view);
        items = itemDao.listar();
    }

    public void setarTela() {
        LocalDateTime data = LocalDateTime.now();
        TotalVendaItemController.localDate = data;
        
        System.out.println(data);
        
        this.helper.setarTela(items, data);
        
        List<VendaItem> vendaItems = this.compraDao.buscarComprasPorItemList(data, items);
       
       if(vendaItems != null){
            this.helper.setarTabela(vendaItems, data);
       }
       else
           this.helper.imprime("Nenhuma venda encontrada nesta data"); 
    }

    public void buscar() throws ParseException {
        LocalDateTime dataB = this.helper.obterMesEANoSelecionado();
        TotalVendaItemController.localDate = dataB;
        
        List<VendaItem> vendaItemsB = this.compraDao.buscarComprasPorItemList(dataB, items);
       
       if(vendaItemsB != null){
            this.helper.setarTabela(vendaItemsB, dataB);
       }
       else
           this.helper.imprime("Nenhuma venda encontrada nesta data");
        
    }

    public void salvarPdf() throws FileNotFoundException, DocumentException {
        String data = Util.obterMesEAnoEmString(localDate);
        String valorTotal = view.getjTextFieldValorTotal().getText();
        List<List<String>> s = this.helper.matriz();
        
        ImprimirValorItens ivi = new ImprimirValorItens();
        ivi.imprimirValorVendido("Total de vendas por item mensal",data, valorTotal, s);
        
    }
    
    
    
}
