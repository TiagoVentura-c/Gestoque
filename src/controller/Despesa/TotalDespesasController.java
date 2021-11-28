/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Despesa;

import controller.Helper.Despesa.TotalDespesaControllerHelper;
import dao.modelDao.DespesaDao;
import java.time.LocalDate;
import java.util.List;
import model.Despesas.Despesa;
import model.Despesas.DetalheGasto;
import view.Despesas.TotalDespesasPorItem;

/**
 *
 * @author Tiago Ventura
 */
public class TotalDespesasController {
    private final TotalDespesaControllerHelper helper;
    private final LocalDate data;
    private DespesaDao despesaDao;

    public TotalDespesasController(TotalDespesasPorItem view) {
        this.helper = new TotalDespesaControllerHelper(view);
        despesaDao = new DespesaDao();
        
        data = LocalDate.now();
    }

    public void setarTabela() {
        List<DetalheGasto> lista = despesaDao.listarGastosMensais(data);
        this.helper.setarTabela(lista, data);
    }

    public void buscar() {
        LocalDate dataSelecionada = this.helper.getData();
        List<DetalheGasto> lista = despesaDao.listarGastosMensais(dataSelecionada);
        
        this.helper.setarTabela(lista, dataSelecionada);
    }
    
    
    
}
