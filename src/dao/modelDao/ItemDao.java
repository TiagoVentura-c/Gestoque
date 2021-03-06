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
import dao.Conexao;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author tiago
 */
public class ItemDao {

    public ItemDao() {
        
    }
   
    
    
    public static void inserir(Item item) throws IOException{
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

    public static void remover(int id) throws IOException{
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
    
    public static List<Item> buscar(String nomeColuna, String codigo) throws IOException{
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
                item.setPreco(rs.getFloat("preco"));
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
    
    public static void actualizar(Item item) throws IOException{
        try {
            Conexao conexao = new Conexao();
            String sql = "UPDATE items SET "
                    + "codigo=?, id_categoria=?, preco=?, descricao=?, id_unidade=? WHERE id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            pre.setString(1, item.getCodigo());
            pre.setInt(2, item.getCategoria().getId());
            pre.setDouble(3, item.getPreco());
            pre.setString(4, item.getDescricao());
            pre.setInt(5, item.getUnidade().getId());
            pre.setInt(6, item.getId());
            
            pre.executeUpdate();
            
            conexao.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return;
        }
        
    }

    public static List<Item> listar(){
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
                item.setPreco(rs.getFloat("preco"));
                item.setDescricao(rs.getString("descricao"));

                try {
                    unidade = unidadeDao.buscar(rs.getInt("id_unidade"));
                } catch (IOException ex) {
                    Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    categoria = CategoriaDao.buscar(rs.getInt("id_categoria"));
                } catch (IOException ex) {
                    Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
                }

                item.setUnidade(unidade);
                item.setCategoria(categoria);

                items.add(item);
            }

            conexao.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return null;        
        }

        return items;
    }

    public static Item buscarPorId(int id) throws IOException {
        Unidade unidade = new Unidade();
        Categoria categoria = new Categoria();
        Item item = new Item();

        UnidadeDao unidadeDao = new UnidadeDao();
        CategoriaDao categoriaDao= new CategoriaDao();
        
        try {
            Conexao conexao = new Conexao();
            String sql = "select * from items where id=?";
            
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, id);            
            
            ResultSet rs = pre.executeQuery();
                
                item.setId(rs.getInt("id"));
                item.setCodigo(rs.getString("codigo"));
                item.setPreco(rs.getFloat("preco"));
                item.setDescricao(rs.getString("descricao"));

                unidade = unidadeDao.buscar(rs.getInt("id_unidade"));
                categoria = categoriaDao.buscar(rs.getInt("id_categoria"));

                item.setUnidade(unidade);
                item.setCategoria(categoria);
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return item;
    }
    
    
    
}
