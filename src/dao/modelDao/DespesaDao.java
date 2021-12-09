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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Despesas.CategoriaDespesa;
import model.Despesas.Despesa;
import model.Despesas.DetalheGasto;
import model.Despesas.ItemDespesa;
import dao.Conexao;
import java.io.IOException;

/**
 *
 * @author Tiago Ventura
 */
public class DespesaDao {
    
    public void inserirDespesa(Despesa despesa) throws IOException{
        try {
            Conexao conexao = new Conexao();
            String sql = " insert into despesas(id, id_item, data, valor_gasto, id_categoria) values (null, ?, ?, ?, ?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, despesa.getItemDespesa().getId());
            pre.setString(2, Util.obterDataEmString(despesa.getData()));
            pre.setFloat(3, despesa.getValor());
            pre.setInt(4, despesa.getCategoriaDespesa().getId());
                        
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inserirItem(ItemDespesa itemDespesa) throws IOException{
        try {
            Conexao conexao = new Conexao();
            String sql = "insert into items_despesas(id, descricao) values (null, ?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, itemDespesa.getDescricao());
                        
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void inserirCategoria(CategoriaDespesa cd) throws IOException{
        try {
            Conexao conexao = new Conexao();
            String sql = " insert into categorias_despesas(id, categoria) values (null, ?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, cd.getCategoria());
            
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Despesa buscarDespesa(int id) throws IOException{
        Despesa despesa = new Despesa();
        ItemDespesa itemDespesa = new ItemDespesa();
        CategoriaDespesa cd = new CategoriaDespesa();
        try {
            Conexao conexao = new Conexao();
            String sql = " select *from despesas where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, id);
            
            ResultSet rs=pre.executeQuery();
            despesa.setId(rs.getInt("id"));
            despesa.setData(Util.formatarStringEmData(rs.getString("data")));
            despesa.setValor(rs.getFloat("valor"));
            
            itemDespesa = buscarItem(rs.getInt("id_item"));
            
            cd = buscarCategoria(rs.getInt("id_categoria"));
            despesa.setCategoriaDespesa(cd);
            despesa.setItemDespesa(itemDespesa);
            
            
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return despesa;
    }

    private ItemDespesa buscarItem(int id) throws IOException {
        ItemDespesa itemDespesa = new ItemDespesa();
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select *from items_despesas where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, id);
            
            ResultSet rs=pre.executeQuery();
            itemDespesa.setId(rs.getInt("id"));
            itemDespesa.setDescricao(rs.getString("descricao"));
            
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemDespesa;
        
    }

    private CategoriaDespesa buscarCategoria(int id) throws IOException {
        CategoriaDespesa cd = new CategoriaDespesa();
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select *from categorias_despesas where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, id);
            
            ResultSet rs=pre.executeQuery();
            cd.setId(rs.getInt("id"));
            cd.setCategoria(rs.getString("categoria"));
                        
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cd;
    }
    
    public List<Despesa> listarDespesa() throws IOException{
        List<Despesa> despesas = new ArrayList<>();
         
        try {
            Conexao conexao = new Conexao();
            String sql = " select *from despesas";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
               Despesa despesa = new Despesa();
               ItemDespesa itemDespesa = new ItemDespesa();
               CategoriaDespesa cd = new CategoriaDespesa();
               
               despesa.setId(rs.getInt("id"));
               despesa.setData(Util.formatarStringEmData(rs.getString("data")));
               despesa.setValor(rs.getFloat("valor_gasto"));

               itemDespesa = buscarItem(rs.getInt("id_item"));
               despesa.setItemDespesa(itemDespesa);
               
               cd = buscarCategoria(rs.getInt("id_categoria"));
               despesa.setCategoriaDespesa(cd);
               despesas.add(despesa);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return despesas;
    }
    
    
        public List<ItemDespesa> listarItem() throws IOException {
        List<ItemDespesa> itemDespesas = new ArrayList<>();
        
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select *from items_despesas";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
      
            ResultSet rs=pre.executeQuery();
            
            while(rs.next()){
                ItemDespesa itemDespesa = new ItemDespesa();
                itemDespesa.setId(rs.getInt("id"));
                itemDespesa.setDescricao(rs.getString("descricao"));
                
                itemDespesas.add(itemDespesa);
            }
            
            
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemDespesas;
        
    }

    public List<CategoriaDespesa> listarCategoria() throws IOException {
        List<CategoriaDespesa> categoriaDespesas = new ArrayList<>();
        
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select *from categorias_despesas";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);

            
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                CategoriaDespesa cd = new CategoriaDespesa();
                cd.setId(rs.getInt("id"));
                cd.setCategoria(rs.getString("categoria"));
                categoriaDespesas.add(cd);
            }
            
                        
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categoriaDespesas;
    }

    public List<Despesa> buscarDespesasPorData(LocalDate data) throws IOException {
        List<Despesa> despesas = new ArrayList<>();
        String mesActual = Util.obterMesEAnoEmString(data);
        data = data.plusMonths(1);        
        String mesSeguinte = Util.obterMesEAnoEmString(data);
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from despesas where data >= ? and data < ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, mesActual);
            pre.setString(2, mesSeguinte);
            
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
               Despesa despesa = new Despesa();
               ItemDespesa itemDespesa = new ItemDespesa();
               CategoriaDespesa cd = new CategoriaDespesa();
               
               despesa.setId(rs.getInt("id"));
               despesa.setData(Util.formatarStringEmData(rs.getString("data")));
               despesa.setValor(rs.getFloat("valor_gasto"));

               itemDespesa = buscarItem(rs.getInt("id_item"));
               despesa.setItemDespesa(itemDespesa);
               
               cd = buscarCategoria(rs.getInt("id_categoria"));
               despesa.setCategoriaDespesa(cd);
               
               despesas.add(despesa);
            }
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(DespesaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return despesas;
    }

    public List<DetalheGasto> listarGastosMensais(LocalDate data) throws IOException{
        List<DetalheGasto> detalheGastos = new ArrayList<>();
        List<ItemDespesa> itemDespesas = listarItem();
        
        
        String mesActual = Util.obterMesEAnoEmString(data);
        LocalDate dataSeguir = data.plusMonths(1);        
        String mesSeguinte = Util.obterMesEAnoEmString(dataSeguir);
        
        itemDespesas.forEach(ids -> {
            DetalheGasto detalheGasto = new DetalheGasto();
            
            detalheGasto.setItem(ids.getDescricao());
            detalheGasto.setData(Util.obterMesEAnoEmString(data));
            
            try {
                Conexao conexao = new Conexao();
            String sql = "select sum(valor_gasto) from despesas where id_item=? and data >= ? and data < ?";
                PreparedStatement pre = conexao.conn.prepareStatement(sql);
                
                pre.setInt(1, ids.getId());
                pre.setString(2, mesActual);
                pre.setString(3, mesSeguinte);
                
                ResultSet rs=pre.executeQuery();
                detalheGasto.setTotal(rs.getFloat("sum(valor_gasto)"));
                
                detalheGastos.add(detalheGasto);
                
            } catch (SQLException ex) {
                Logger.getLogger(DespesaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        return detalheGastos;
    }
    
}
