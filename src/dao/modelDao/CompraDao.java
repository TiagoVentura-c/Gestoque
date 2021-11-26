/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.modelDao;

import static dao.modelDao.VendaDao.getDataString;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.and;

import model.Compra;
import model.Item;
import model.Unidade;
import model.Venda;
import model.VendaItem;
import src.dao.Conexao;

/**
 *
 * @author tiago
 */
public class CompraDao {
 
    
    public static void main(String[] args) throws ParseException {
        
        List<Compra> compras = buscarCompras(19);
        
        for(Compra c: compras)
            System.out.printf("descricao=%s, idVenda = %d, idIten=%d  \n", 
                    c.getItem().getDescricao(), c.getVenda().getId(), c.getItem().getId());
        
    }
    
    static VendaDao vendaDao =  new VendaDao();
    static ItemDao itemDao = new ItemDao();
    
    public static List<Compra> buscarCompras(int id_venda) throws ParseException {
        List<Compra> compras = new ArrayList<>();
        
        Venda v = new Venda();
        Item i = new Item();
         
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from compras where id_venda = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, id_venda);
            ResultSet rs=pre.executeQuery();
            
            while(rs.next()){
                Compra compraBuscada = new Compra();
                compraBuscada.setId(rs.getInt("id"));
                compraBuscada.setQuantidade(rs.getInt("quantidade"));
                compraBuscada.setValor(rs.getFloat("valor"));
                
                i = itemDao.buscarPorId(rs.getInt("id_item"));
                v = vendaDao.buscar(rs.getInt("id_venda"));

                compraBuscada.setItem(i);
                compraBuscada.setVenda(v);
                
                compras.add(compraBuscada);
           }
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compras;
    }    
    
    public void inserir(Compra compra){
        try {
            Conexao conexao = new Conexao();
            String sql = " insert into compras(id, id_venda, id_item, quantidade, valor) values (null, ?, ?, ?, ?)";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setInt(1, compra.getVenda().getId());
            pre.setInt(2, compra.getItem().getId());
            pre.setInt(3, compra.getQuantidade());
            pre.setFloat(4, compra.getValor());
            
            pre.executeUpdate();
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remover(int id){
        try {
            Conexao conexao = new Conexao();
            String sql = " DELETE from compras where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            pre.executeUpdate();
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Compra buscar(int id) throws ParseException{
        Compra compraBuscada = new Compra();
        Venda v = new Venda();
        Item i = new Item();
        
        try {
            Conexao conexao = new Conexao();
            String sql = " select * from compras where id = ?";
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setLong(1, id);
            
            ResultSet rs=pre.executeQuery();
            
            compraBuscada.setId(rs.getInt("id"));
            compraBuscada.setQuantidade(rs.getInt("quantidade"));
            compraBuscada.setValor(rs.getFloat("valor"));
            
            i = itemDao.buscarPorId(rs.getInt("id_item"));
            v = vendaDao.buscar(rs.getInt("id_venda"));
            
            compraBuscada.setItem(i);
            compraBuscada.setVenda(v);
                        
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return compraBuscada;
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
    
    /*
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
    }*/

    public void inserir(List<Compra> compras) {
        for(Compra c: compras)
        {
          inserir(c);
        }
           
    }

    public List<VendaItem> buscarComprasPorItem(List<Venda> vendas, Item i) {
       List<VendaItem> vendaItems = new ArrayList<>();
       
       /*select sum(compras.quantidade), sum(compras.valor)
        from compras join items, vendas on compras.id_venda = vendas.id and compras.id_item = items.id
        where vendas.data_compra between "2021-11" and "2021-12" and compras.id_item = 1;
        */
   
       return vendaItems;    
    }

    public VendaItem buscarComprasPorItem(Date data, Item i) {
        VendaItem vendaItem = new VendaItem();
        vendaItem.setData(data);
        vendaItem.setDescricao(i.getDescricao());
        vendaItem.setUnidade(i.getUnidade().getUnidade());
        
        String mesActual = getDataString(data);
        data.setMonth(data.getMonth()+1);
        String mesSeguinte = getDataString(data);
        
        System.out.println(mesActual + "  "+ mesSeguinte);
        System.out.println(i.getDescricao() +" "+ i.getId());
        
        try {
            Conexao conexao = new Conexao();
            String sql = "select sum(compras.quantidade), sum(compras.valor) from compras join items, "
                    + "vendas on compras.id_venda = vendas.id and compras.id_item = items.id where "
                    + "vendas.data_compra >= ? and vendas.data_compra < ? and compras.id_item = ?";
            
            PreparedStatement pre = conexao.conn.prepareStatement(sql);
            pre.setString(1, mesActual);
            pre.setString(2, mesSeguinte);
            pre.setInt(3, i.getId());
            
            ResultSet rs=pre.executeQuery();
            
            vendaItem.setQuantidade(rs.getInt("sum(compras.quantidade)"));
            vendaItem.setTotal(rs.getFloat("sum(compras.valor)"));
                       
            conexao.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UnidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vendaItem;
    }

    public List<VendaItem> buscarComprasPorItemList(Date data, List<Item> is) {
        List<VendaItem> vendaItems = new ArrayList<>();
        
        for(Item i: is)
            vendaItems.add(buscarComprasPorItem(data, i)); 
        return vendaItems;
    }
    
}
