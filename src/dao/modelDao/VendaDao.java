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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Venda;
import dao.Conexao;
import java.io.IOException;

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
        
                
    }
    
    public static void inserir(Venda venda){
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

    public static int obterUltimoId(){
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
    
    

    public void remover(int id) throws IOException{
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
    
    public Venda buscar(int id) throws ParseException, IOException{
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
    
    public static List<Venda> listar() throws ParseException, IOException{
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

    public static List<Venda> buscarPorMes(LocalDateTime data){
        List<Venda> vendas = new ArrayList<>();
        String mesActual = Util.obterMesEAnoEmString(data);
        data = data.plusMonths(1);        
        String mesSeguinte = Util.obterMesEAnoEmString(data);
                
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
                try {
                    vendaBuscada.setDataString(rs.getString("data_compra"));
                } catch (ParseException ex) {
                    Logger.getLogger(VendaDao.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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

    public List<Venda> buscarPorData(LocalDate data) throws ParseException, IOException {
        List<Venda> vendas = new ArrayList<>();
        
        String dataS = Util.obterAnoMesDiaEmString(data);
        
        System.out.println(dataS);
                        
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
