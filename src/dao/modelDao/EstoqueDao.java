/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.modelDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estoque;
import model.Item;
import dao.Conexao;

/**
 *
 * @author tiago
 */
public class EstoqueDao {
    
    
    public static void main(String[] args) {
       
        //actualizar(e);
        
        remover(1);
                
        
        List<Estoque> es = listar();
        
        for(Estoque o : es)
            System.out.println("item = " + o.getItem().getDescricao()+" quntidade= "+ o.getQuantidade()+" "+ o.getItem().getUnidade().getUnidade());

    }
    
    private ItemDao itemDao = new ItemDao();
    
    public static void inserir(Estoque estoque){
        try {
            Conexao conexao = new Conexao();
            String sql = " insert into estoques(id, id_item, quantidade) values (null, ?, ?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            pre.setInt(1, estoque.getItem().getId());
            pre.setInt(2, estoque.getQuantidade());
            
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void remover(int id){
        try {
            Conexao conexao = new Conexao();
            String sql = " DELETE from estoques where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Estoque buscar(int id){
        
        Estoque estoqueBuscado = new Estoque();
        Item i = new Item();
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from estoques where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            ResultSet rs=pre.executeQuery();
            estoqueBuscado.setId(rs.getInt("id"));
            estoqueBuscado.setQuantidade(rs.getInt("quantidade"));
            
            i = ItemDao.buscarPorId(rs.getInt("id_item"));
            estoqueBuscado.setItem(i);
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estoqueBuscado;
    }
    
    public void actualizar(Estoque estoque){
        try {
            Conexao conexao = new Conexao();
            String sql = "UPDATE estoques SET quantidade = ? WHERE id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, estoque.getQuantidade());
            pre.setInt(2, estoque.getId());
            
            pre.executeUpdate();
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static List<Estoque> listar(){
        List<Estoque> estoques = new ArrayList<>();
        Item i = new Item();
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from estoques";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            ResultSet rs=pre.executeQuery();
            
            while(rs.next()){
                Estoque estoque = new Estoque();
                
                estoque.setId(rs.getInt("id"));
                estoque.setQuantidade(rs.getInt("quantidade"));
                i = ItemDao.buscarPorId(rs.getInt("id_item"));
                estoque.setItem(i);
                
                estoques.add(estoque);
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estoques;
    }

    public List<Estoque> buscarFiltrado(String coluna, String palavra) {
        List<Estoque> estoques = new ArrayList<>();
        Item i = new Item();
        
        try {
            Conexao conexao = new Conexao();
            String sql = "select estoques.id, estoques.id_item, estoques.quantidade from estoques "
                    + "join items on items.id = estoques.id_item and items."+coluna+" like ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, "%"+palavra+"%");
            
            ResultSet rs=pre.executeQuery();
            
            while(rs.next()){
                Estoque estoque = new Estoque();
                
                estoque.setId(rs.getInt("id"));
                estoque.setQuantidade(rs.getInt("quantidade"));
                i = ItemDao.buscarPorId(rs.getInt("id_item"));
                estoque.setItem(i);
                
                estoques.add(estoque);
            }
            conexao.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return estoques;
    }

    
}
