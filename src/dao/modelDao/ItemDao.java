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

import model.Categoria;
import model.Item;
import model.Unidade;
import src.dao.Conexao;

/**
 *
 * @author tiago
 */
public class ItemDao {
    
    public static void main(String[] args) {
        
        Item item = new Item();
        item.setCodigo("AL03");
       // item.setId_categoria(1);
        item.setPreco(8300);
        item.setDescricao("Ketchup");
       // item.setId_unidade(3);
        
        //inserir(item);
        
        /**/
        List<Item> items = buscar("codigo","A");
        
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
            pre.setInt(2, item.getCategoria().getId());
            pre.setDouble(3, item.getPreco());
            pre.setString(4, item.getDescricao());
            pre.setInt(5, item.getUnidade().getId());
            
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
    
    public static List<Item> buscar(String nomeColuna, String codigo){
        List<Item> items = new ArrayList<>();
        Unidade unidade = new Unidade();
        Categoria categoria = new Categoria();

        UnidadeDao unidadeDao = new UnidadeDao();
        CategoriaDao categoriaDao= new CategoriaDao();
        
        try {
            Conexao conexao = new Conexao();
            String sql = "select * from items where "+nomeColuna+" like ?";
            
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            //pre.setString(1, "'"+nomeColuna+"'");
            pre.setString(1, "%"+codigo+"%");            
            
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                Item item = new Item();
                
                item.setId(rs.getInt("id"));
                item.setCodigo(rs.getString("codigo"));
                item.setPreco(rs.getDouble("preco"));
                item.setDescricao(rs.getString("descricao"));

                unidade = unidadeDao.buscar(rs.getInt("id_unidade"));
                categoria = categoriaDao.buscar(rs.getInt("id_categoria"));

                item.setUnidade(unidade);
                item.setCategoria(categoria);

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
            //pre.setInt(2, item.getId_categoria());
            pre.setDouble(3, item.getPreco());
            pre.setString(4, item.getDescricao());
            //pre.setInt(5, item.getId_unidade());
            
            pre.executeUpdate();
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public List<Item> listar(){
        List<Item> items = new ArrayList<>();
        Unidade unidade = new Unidade();
        Categoria categoria = new Categoria();

        UnidadeDao unidadeDao = new UnidadeDao();
        CategoriaDao categoriaDao= new CategoriaDao();

        try {
            Conexao conexao = new Conexao();
            String sql = "select * from items";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);



            ResultSet rs = pre.executeQuery();

            while(rs.next()){
                Item item = new Item();

                item.setId(rs.getInt("id"));
                item.setCodigo(rs.getString("codigo"));
                item.setPreco(rs.getDouble("preco"));
                item.setDescricao(rs.getString("descricao"));

                unidade = unidadeDao.buscar(rs.getInt("id_unidade"));
                categoria = categoriaDao.buscar(rs.getInt("id_categoria"));

                item.setUnidade(unidade);
                item.setCategoria(categoria);

                items.add(item);
            }

            conexao.close();

        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return items;
    }
    
}