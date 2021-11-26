/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.modelDao;

import controller.Utils.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Venda;
import src.dao.Conexao;

/**
 *
 * @author tiago
 */
public class VendaDao {
    
    
    public static void main(String[] args) throws ParseException {
        Date s = new Date();
        //s.setYear(2021);
        s.setMonth(2);
        
        System.out.println(s);
        System.out.println(s.getMonth());
        System.out.println(s.getYear());
        
        Venda v = new Venda();
        v.setNomeCliente("FFF");
        v.setValorTotal(123);
        v.setData(s);
          
        
        System.out.println(v.getAno());
        
        List<Venda> vs = buscarPorMes(s);
        /**/
        for(Venda ve: vs){
            System.out.printf("id=%d, nomecliente=%s, data=%s \n", ve.getId(), ve.getNomeCliente(), ve.getDataFormatada());
        }
                
    }
    
    public static void inserir(Venda venda) throws ParseException{
        try {      
            Conexao conexao = new Conexao();
            String sql = " insert into vendas(id, nome_cliente, valor_total, data_compra) values (null,?, ?, ?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, venda.getNomeCliente());
            pre.setDouble(2, venda.getValorTotal());
            pre.setString(3, venda.getDataStringSql());
            
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static int obterUltimoId() {
        int id=0;
         try {
            Conexao conexao = new Conexao();
            String sql = " select * from vendas";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            ResultSet rs=pre.executeQuery();
            while(rs.next())
                id=rs.getInt("id");
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return id;
    }    
    
    

    public void remover(int id){
        try {
            Conexao conexao = new Conexao();
            String sql = " DELETE from vendas where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Venda buscar(int id) throws ParseException{
        Venda vendaBuscada = new Venda();
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from vendas where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            ResultSet rs=pre.executeQuery();
            vendaBuscada.setId(rs.getInt("id"));
            vendaBuscada.setNomeCliente(rs.getString("nome_cliente"));
            vendaBuscada.setValorTotal(rs.getFloat("valor_total"));
            vendaBuscada.setDataString(rs.getString("data_compra"));
            
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendaBuscada;
    }
    
    /*
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
    }*/
    
    public static List<Venda> listar() throws ParseException{
        List<Venda> vendas = new ArrayList<>();
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from vendas";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Venda vendaBuscada = new Venda();
                
                vendaBuscada.setId(rs.getInt("id"));
                vendaBuscada.setNomeCliente(rs.getString("nome_cliente"));
                vendaBuscada.setValorTotal(rs.getFloat("valor_total"));
                vendaBuscada.setDataString(rs.getString("data_compra"));
                
                vendas.add(vendaBuscada);
                
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendas;
    }

    public static List<Venda> buscarPorMes(Date data) throws ParseException {
        List<Venda> vendas = new ArrayList<>();
        String mesActual = getDataString(data);
        data.setMonth(data.getMonth()+1);
        String mesSeguinte = getDataString(data);
                
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from vendas where data_compra >= ? and data_compra <= ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, mesActual);
            pre.setString(2, mesSeguinte);
            
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Venda vendaBuscada = new Venda();
                
                vendaBuscada.setId(rs.getInt("id"));
                vendaBuscada.setNomeCliente(rs.getString("nome_cliente"));
                vendaBuscada.setValorTotal(rs.getFloat("valor_total"));
                vendaBuscada.setDataString(rs.getString("data_compra"));
                
                vendas.add(vendaBuscada);
                
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendas;
    }
    
    
    public static String getDataString(Date data){
        return new SimpleDateFormat("yyyy-MM").format(data);
    }

    public List<Venda> buscarPorData(Date data) throws ParseException {
        List<Venda> vendas = new ArrayList<>();
        
        String dataS = Util.getDataStringSql(data);
                        
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from vendas where data_compra like ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, "%"+dataS+"%");
                        
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                Venda vendaBuscada = new Venda();
                
                vendaBuscada.setId(rs.getInt("id"));
                vendaBuscada.setNomeCliente(rs.getString("nome_cliente"));
                vendaBuscada.setValorTotal(rs.getFloat("valor_total"));
                vendaBuscada.setDataString(rs.getString("data_compra"));
                
                vendas.add(vendaBuscada);
                
            }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendas;
    }
    
}
