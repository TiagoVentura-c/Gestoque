/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Vendas;

import controller.Helper.Vendas.VendasControllerHelper;
import dao.modelDao.VendaDao;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        LocalDateTime data = LocalDateTime.now();
        VendasController.helper.setarAnoEMes(data);        
        
        List<Venda> vendas = VendaDao.buscarPorMes(data);
        
        
        if(!vendas.isEmpty())
        VendasController.helper.setarTela(vendas, data);
        else
            VendasController.helper.imprime("Nenhuma venda encontrada no mês corrente");
    }

    public void buscarMesEANo() throws ParseException {
       LocalDateTime data = VendasController.helper.obterMesEANoSelecionado();
       
       List<Venda> vendas = VendaDao.buscarPorMes(data); 
       
       if(!vendas.isEmpty())
       VendasController.helper.setarTela(vendas, data);
       else{ 
        VendasController.helper.setarTelaVazia(data);
        VendasController.helper.imprime("Nenhuma venda encontrada nesta data");
       }
           
    }

    public void buscarData() throws ParseException   {
        LocalDate data = VendasController.helper.obterData();
        List<Venda> vendas = vendaDao.buscarPorData(data); 
        
        LocalDateTime d = LocalDateTime.of(data, LocalTime.MIN);
        
        if(!vendas.isEmpty())
       VendasController.helper.setarTela(vendas, d);
       else{ 
        VendasController.helper.setarTelaVazia(d);
        VendasController.helper.imprime("Nenhuma venda encontrada nesta data");
       }
        
    }

    public Venda obterVendaSeleciona() {
        return VendasController.helper.obterVendaSelecionada();
    }
    
    
    
}
