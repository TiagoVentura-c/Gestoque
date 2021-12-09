/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Estoque;

import controller.Helper.Estoque.EstoqueControllerHelper;
import java.util.List;
import model.Estoque;
import dao.modelDao.EstoqueDao;
import dao.modelDao.ItemDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Item;
import view.TelaPrincipal;

/**
 * 
 * @author Tiago Ventura
 */
public class EstoqueController {

    private final EstoqueControllerHelper helper;
    
    private EstoqueDao estoqueDao = new EstoqueDao();
    
    public EstoqueController(TelaPrincipal view){
        this.helper = new EstoqueControllerHelper(view);
    }

    public static void setarTabela() throws IOException {
        List<Estoque> estoques = EstoqueDao.listar();
        EstoqueControllerHelper.setarTabela(estoques);
    }

    public void buscar() throws IOException {
         // 0-> descricao
        // 1-> codigo
        String []s = this.helper.buscar();
        
        EstoqueDao estoqueDao = new EstoqueDao();
        List<Estoque> estoques = new ArrayList<>();
        
        if(!s[0].isEmpty() && s[1].isEmpty()){
         estoques = estoqueDao.buscarFiltrado("descricao",s[0]);
         helper.setarTabela(estoques);
        } 
        else if(s[0].isEmpty() && !s[1].isEmpty()){
            estoques = estoqueDao.buscarFiltrado("codigo" ,s[1]);
            helper.setarTabela(estoques);
        }
        else{
            estoques = estoqueDao.listar();
            helper.setarTabela(estoques);
        }
    }

    public Estoque itemSelecionado() {
        return this.helper.itemSelecionado();
    }

    public void apagar(Estoque e) throws IOException {
        //todo confirmar apgar
        
        estoqueDao.remover(e.getId());
        JOptionPane.showMessageDialog(null, "Item apagado com sucesso");
        setarTabela();
    }
    
    
    
    
}
