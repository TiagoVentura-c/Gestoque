/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import controller.Helper.Vendas.NovaVendaControllerHelper;
import dao.modelDao.CompraDao;
import dao.modelDao.VendaDao;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Compra;
import model.Venda;
import view.Vendas.NovoVenda;

/**
 *
 * @author Tiago Ventura
 */
public class NovaVendaController {

    private static NovoVenda view;
    private static NovaVendaControllerHelper helper;
    
    private VendaDao vendaDao = new VendaDao();
    
    private CompraDao compraDao = new CompraDao();
    
    private static List<Compra> compras = new ArrayList<>();

    public NovaVendaController(NovoVenda view){
        this.view = view;
        helper = new NovaVendaControllerHelper(view);
        compras = new ArrayList<>();
        SelecionarItensController.resetarListaItens();
    }
    
    
    public static void adicionar(Compra compra) {
        compras.add(compra);
        
        NovaVendaController.helper.setartabela(compras);
    }

    public void remover() {
        int linhaRemovida = this.helper.linhaSelecionada();
        compras.remove(linhaRemovida);
        
        NovaVendaController.helper.setartabela(compras);
    }

    public void finalizar() throws ParseException {
        
        Date d = new Date();
        Venda v = this.helper.obterVenda();
        v.setData(d);
        VendaDao.inserir(v);
        int id_venda = vendaDao.obterUltimoId();
        v.setId(id_venda);
        
        for(Compra c: compras){
           c.setVenda(v);
        }
            
        
        compraDao.inserir(compras);
        
        helper.imprime("Venda finalizada com sucesso");
        VendasController.setarTabela();
        
        NovaVendaController.compras = new ArrayList<>();
        SelecionarItensController.resetarListaItens();
        
    }
    
}
