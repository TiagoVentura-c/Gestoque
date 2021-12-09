/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Despesa;

import com.itextpdf.text.DocumentException;
import controller.Helper.Despesa.TotalDespesaControllerHelper;
import controller.Utils.Impressao.ImprimirValorItens;
import controller.Utils.Util;
import dao.modelDao.DespesaDao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.temporal.TemporalQueries.localDate;
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
    private final TotalDespesasPorItem view;
    private LocalDate localDate;

    public TotalDespesasController(TotalDespesasPorItem view) {
        this.view = view;
        this.helper = new TotalDespesaControllerHelper(view);
        despesaDao = new DespesaDao();
        
        data = LocalDate.now();
    }

    public void setarTabela() throws IOException {
        localDate = data;
        List<DetalheGasto> lista = despesaDao.listarGastosMensais(data);
        this.helper.setarTabela(lista, data);
    }

    public void buscar() throws IOException {
        LocalDate dataSelecionada = this.helper.getData();
        localDate = dataSelecionada;
        List<DetalheGasto> lista = despesaDao.listarGastosMensais(dataSelecionada);
        
        this.helper.setarTabela(lista, dataSelecionada);
    }

    public void salvarPdf() throws FileNotFoundException, DocumentException {
        
        
        String data = Util.obterMesEAnoEmString(localDate);
        String valorTotal = view.getjTextFieldValorTotal().getText();
        List<List<String>> s = this.helper.matriz();
        
        ImprimirValorItens ivi = new ImprimirValorItens();
        ivi.imprimirValorVendido("Total de despesas por item mensal",data, valorTotal, s);
    }
    
    
    
}
