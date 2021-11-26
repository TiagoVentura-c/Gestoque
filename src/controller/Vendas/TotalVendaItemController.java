/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import controller.Helper.Vendas.TotalVendaItemControllerHelper;
import dao.modelDao.CompraDao;
import dao.modelDao.ItemDao;
import dao.modelDao.VendaDao;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import model.Item;
import model.Venda;
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
    
    private List<Item> items;
    
    public TotalVendaItemController(TotalVendasPorItem view) {
        this.helper = new TotalVendaItemControllerHelper(view);
        items = itemDao.listar();
    }

    public void setarTela() {
        Date data = new Date();
        
        this.helper.setarTela(items, data);
        
        List<VendaItem> vendaItems = this.compraDao.buscarComprasPorItemList(data, items);
       
       if(vendaItems != null){
            this.helper.setarTabela(vendaItems, data);
       }
       else
           this.helper.imprime("Nenhuma venda encontrada nesta data"); 
    }

    public void buscar() throws ParseException {
        Date data = this.helper.obterMesEANoSelecionado();
        Item i = items.get(this.helper.itemSelecionado());
        
       //List<Venda> vendas = vendaDao.buscarPorMes(data);
        
       VendaItem vendaItems = this.compraDao.buscarComprasPorItem(data, i);
       
       if(vendaItems != null){
            this.helper.setarTabela(vendaItems, data);
       }
       else
           this.helper.imprime("Nenhuma venda encontrada nesta data");
        
    }
    
    
    
}
