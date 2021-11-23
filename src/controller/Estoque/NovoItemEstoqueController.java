/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Estoque;

import controller.Helper.Estoque.NovoItemEstoqueControllerHelper;
import dao.modelDao.EstoqueDao;
import dao.modelDao.ItemDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Estoque;
import model.Item;
import view.Estoque.NovoItemEstoque;

/**
 *
 * @author Tiago Ventura
 */
public class NovoItemEstoqueController {
    
    private static int id;
    private static int idItem;
    private static boolean actualiza=false;
    
    private static NovoItemEstoqueControllerHelper helper;
    private static NovoItemEstoque view;
    
    EstoqueDao estoqueDao = new EstoqueDao();
    ItemDao itemDao = new ItemDao();
    
    

    public NovoItemEstoqueController(NovoItemEstoque view) {
        this.view = view;
        this.helper = new NovoItemEstoqueControllerHelper(view);
    }
    
    
    
    public static void setarTelaEditar(Estoque e) {
        actualiza=true;
        System.out.println(e.getItem().getDescricao()+" ********************");
        
        NovoItemEstoqueController.id = e.getId();
        NovoItemEstoqueController.idItem = e.getItem().getId();
        
        DefaultComboBoxModel comboBoxModelItems = (DefaultComboBoxModel) view.getjComboBoxItems().getModel();
        
        comboBoxModelItems.addElement(e.getItem().getDescricao()  + ", uni.: " + e.getItem().getUnidade().getUnidade());
        
        view.getjTextFielQuantidade().setText(e.getQuantidade()+"");
        
        view.getjComboBoxItems().enable(false);
        
    }

    public static void setarTela() {
        List<Item> itens = ItemDao.listar();
        List<Estoque> es = EstoqueDao.listar();
        
        for(int i=0; i < es.size(); i++){
            for(int j =0; j < itens.size(); j++){
                if(es.get(i).getItem().getId() == itens.get(j).getId()){
                    itens.remove(j);
                }
            }
        }
        
        NovoItemEstoqueController.helper.setarTela(itens);
    }

    public void salvar() {
    if(actualiza){
        Estoque e = new Estoque();
        e.setId(id);
        Item i = new Item();
        i.setId(idItem);
        e.setItem(i);
        e.setQuantidade(Integer.parseInt(view.getjTextFielQuantidade().getText().toString()));
        estoqueDao.actualizar(e);
        
        actualiza=false;        
        
        this.helper.imprime("Item actualizado ao estoque com sucesso");
        
        EstoqueController.setarTabela();
        
    }else{     
        Estoque e = this.helper.obter();
    estoqueDao.inserir(e);
        this.helper.imprime("Item adicionado ao estoque com sucesso");
         EstoqueController.setarTabela();
    }
    
    }
    
    
}
