/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Despesa;

import controller.Helper.Despesa.DespesaControllerHelper;
import dao.modelDao.DespesaDao;
import java.time.LocalDate;
import java.util.List;
import model.Despesas.Despesa;
import view.TelaPrincipal;

/**
 *
 * @author Tiago Ventura
 */
public class DespesasController {

    
    private static DespesaControllerHelper helper;
    private static LocalDate data;
    private static DespesaDao despesaDao;
    private final TelaPrincipal view;
 
    public DespesasController(TelaPrincipal view) {
        this.helper = new DespesaControllerHelper(view);
        this.view = view;
        
        data = LocalDate.now();
        this.helper.setarData(data);
        despesaDao = new DespesaDao();
    }

    public void setarTela() {
        List<Despesa> despesas = despesaDao.buscarDespesasPorData(data);
        
        if(!despesas.isEmpty())
            this.helper.setarTela(despesas);
        else
            this.helper.imprime("Nenhuma despesa encontrada no mês corrente");
    }

    public void buscar() {
        LocalDate dataSelecionada = this.helper.getData();
        List<Despesa> despesas = despesaDao.buscarDespesasPorData(dataSelecionada);
        
        if(!despesas.isEmpty())
            this.helper.setarTela(despesas, dataSelecionada);
        else
            this.helper.imprime("Nenhuma despesa encontrada nesta data"); 
    }
    
    public static void atualizaTabela() {
        List<Despesa> despesas = despesaDao.buscarDespesasPorData(data);
        
        if(!despesas.isEmpty())
            DespesasController.helper.setarTela(despesas);
        else
            DespesasController.helper.imprime("Nenhuma despesa encontrada no mês corrente");
    }
}
