/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.dao.modelDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.dao.Conexao;
import src.model.Item;
import src.model.Unidade;

/**
 *
 * @author tiago
 */
public class ItemDao {
    
    public static void main(String[] args) {
        
        Item item = new Item();
        item.setCodigo("AL03");
        item.setId_categoria(1);
        item.setPreco(8300);
        item.setDescricao("Ketchup");
        item.setId_unidade(3);
        
        //inserir(item);
        
        
        List<Item> items = listar();
        
        for(Item i: items)
            System.out.printf("id = %d, item = %s\n", i.getId(), i.getDescricao());
        
    }
    
    public static void inserir(Item item){
        try {
            Conexao conexao = new Conexao();
            String sql =
            "insert into items(id, codigo, id_categoria, preco, descricao, id_unidade)"
            + " values (null,?, ?, ?, ?, ?)";
            
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            pre.setString(1, item.getCodigo());
            pre.setInt(2, item.getId_categoria());
            pre.setDouble(3, item.getPreco());
            pre.setString(4, item.getDescricao());
            pre.setInt(5, item.getId_unidade());
            
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void remover(int id){
        try {
            Conexao conexao = new Conexao();
            String sql = " DELETE from items where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static List<Item> buscar(String codigo){
        List<Item> items = new ArrayList<>();
        
        try {
            Conexao conexao = new Conexao();
            String sql = "select * from items where codigo like ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, "'%"+ codigo+"%'");
            
            
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                Item item = new Item();
                
                item.setId(rs.getInt("id"));
                item.setCodigo(rs.getString("codigo"));
                item.setId_categoria(rs.getInt("id_categoria"));
                item.setPreco(rs.getDouble("preco"));
                item.setDescricao(rs.getString("descricao"));
                item.setId_unidade(rs.getInt("id_unidade"));
                
                items.add(item);
            }
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    public static void actualizar(Item item){
        try {
            Conexao conexao = new Conexao();
            String sql = "UPDATE items SET "
                    + "codigo=?, id_categoria=?, preco=?, descricao=?, id_unidade=? WHERE id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            pre.setString(1, item.getCodigo());
            pre.setInt(2, item.getId_categoria());
            pre.setDouble(3, item.getPreco());
            pre.setString(4, item.getDescricao());
            pre.setInt(5, item.getId_unidade());
            
            pre.executeUpdate();
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static List<Item> listar(){
        List<Item> items = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            String sql = "select * from items";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                Item item = new Item();
                
                item.setId(rs.getInt("id"));
                item.setCodigo(rs.getString("codigo"));
                item.setId_categoria(rs.getInt("id_categoria"));
                item.setPreco(rs.getFloat("preco"));
                item.setDescricao(rs.getString("descricao"));
                item.setId_unidade(rs.getInt("id_unidade"));
                
                items.add(item);
            }
            
            conexao.close();
            
        } catch (SQLException ex) { 
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
}
