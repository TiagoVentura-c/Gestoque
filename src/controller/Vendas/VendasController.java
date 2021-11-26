/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import controller.Helper.Vendas.VendasControllerHelper;
import dao.modelDao.VendaDao;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import model.Venda;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class VendasController {
    
    private static VendasControllerHelper helper;
    private VendaDao vendaDao = new VendaDao();

    public VendasController(TelaPrincipal view) {
        this.helper = new VendasControllerHelper(view);
    }

    public static void setarTabela() throws ParseException {
        Date data = new Date();
        VendasController.helper.setarAnoEMes(data);
        
        List<Venda> vendas = VendaDao.buscarPorMes(data);
        
        
        if(!vendas.isEmpty())
        VendasController.helper.setarTela(vendas, data);
        else
            VendasController.helper.imprime("Nenhuma venda encontrada no mês corrente");
    }

    public void buscarMesEANo() throws ParseException {
       Date data = VendasController.helper.obterMesEANoSelecionado();
       
       List<Venda> vendas = VendaDao.buscarPorMes(data); 
       
       if(!vendas.isEmpty())
       VendasController.helper.setarTela(vendas, data);
       else
           VendasController.helper.imprime("Nenhuma venda encontrada nesta data");
    }

    public void buscarData() throws ParseException   {
        Date data = VendasController.helper.obterData();
        
        List<Venda> vendas = vendaDao.buscarPorData(data); 
        
        if(!vendas.isEmpty())
            VendasController.helper.setarTela(vendas, data);
       else
           VendasController.helper.imprime("Nenhuma venda encontrada nesta data");
    }

    public Venda obterVendaSeleciona() {
        return VendasController.helper.obterVendaSelecionada();
    }
    
    
    
}
