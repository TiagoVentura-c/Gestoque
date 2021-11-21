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
import src.dao.Conexao;
import src.model.Categoria;
import src.model.Unidade;

/**
 *
 * @author tiago
 */
public class UnidadeDao{

    public void inserir(Unidade unidade){
        try {
            Conexao conexao = new Conexao();
            String sql = " insert into unidades(id, unidade) values (null,?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, unidade.getUnidade());
            
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remover(int id){
        try {
            Conexao conexao = new Conexao();
            String sql = " DELETE from unidades where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Unidade buscar(int id){
        Unidade unidadeBuscada = new Unidade();
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from unidades where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            ResultSet rs=pre.executeQuery();
            unidadeBuscada.setId(rs.getInt("id"));
            unidadeBuscada.setUnidade(rs.getString("unidade"));
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return unidadeBuscada;
    }
    
    public void actualizar(Unidade unidade){
        try {
            Conexao conexao = new Conexao();
            String sql = "UPDATE unidades SET unidade= ? WHERE id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, unidade.getUnidade());
            pre.setInt(2, unidade.getId());
            
            pre.executeUpdate();
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public List<Unidade> listar(){
        List<Unidade> unidades = new ArrayList<>();
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from unidades";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Unidade unidade = new Unidade();
                unidade.setId(rs.getInt("id"));
                unidade.setUnidade(rs.getString("unidade"));
                unidades.add(unidade);
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return unidades;
    }
    
    
}
