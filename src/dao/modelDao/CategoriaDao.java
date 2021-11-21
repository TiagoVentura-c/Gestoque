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
import src.dao.Conexao;
import model.Categoria;

/**
 *
 * @author tiago
 */
public class CategoriaDao{
    
    public static void main(String[] args) {
        
        Categoria categoria = new Categoria();
        //categoria.setCategoria("Tecnologias");
        categoria.setId(7);
        
        //adicionar(categoria);
        //actualizar(categoria);
       //remover(categoria);
       //categoria = buscar(7);
        
        List<Categoria> categorias = listar();    
        for(Categoria c : categorias)
            System.out.println("id= " + c.getId() + " categoria = "+ c.getCategoria());
        
    }

    public static void inserir(Categoria categoria){
        try {
            Conexao conexao = new Conexao();
            String sql = " insert into categorias(id, categoria) values (null,?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, categoria.getCategoria());
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void remover(int id){
        try {
            Conexao conexao = new Conexao();
            String sql = " DELETE from categorias where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static Categoria buscar(int id){
        Categoria categoriaBuscada = new Categoria();
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from categorias where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            ResultSet rs=pre.executeQuery();
            categoriaBuscada.setId(rs.getInt("id"));
            categoriaBuscada.setCategoria(rs.getString("categoria"));
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categoriaBuscada;
    }
    
    public static void actualizar(Categoria categoria){
        try {
            Conexao conexao = new Conexao();
            String sql = "UPDATE categorias SET categoria= ? WHERE id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, categoria.getCategoria());
            pre.setInt(2, categoria.getId());
            
            pre.executeUpdate();
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static List<Categoria> listar(){
        List<Categoria> categorias = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from categorias";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
           
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("categoria"));
                categorias.add(categoria);
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
    
    
}
