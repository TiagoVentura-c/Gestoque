package dao;

import static dao.Conexao.cria;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerarTabela {
    private static final String path ="C:\\Users\\"+System.getProperty("user.name")+"\\.gestoque";
    private static final String databaseName="\\base.db";
    
    
    public static void main() {
        File f = new File(path+databaseName);
        if(!f.exists()){
            java.io.File pasta = new java.io.File(path);
            pasta.mkdirs();
            java.io.File base = new java.io.File(path+databaseName);
            try {
                base.createNewFile();
                cria = true;
            } catch (IOException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            criarBase();
        }
            
    }
    
    public static void criarBase(){        
        List<String> tabelas = getTabelas();
        
        tabelas.forEach(t -> {
            try {
                Conexao conexao = new Conexao();
                String sql = t;
                PreparedStatement pre = conexao.conn.prepareStatement(sql);
                
                pre.execute();
                conexao.close();
            } catch (SQLException ex) {
                Logger.getLogger(GerarTabela.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public static List<String> getTabelas(){
       List<String> queries = new ArrayList<>();
       queries.add("CREATE TABLE \"unidades\" (\"id\"	INTEGER NOT NULL, \"unidade\"	TEXT NOT NULL UNIQUE, PRIMARY KEY(\"id\" AUTOINCREMENT));");
       queries.add("CREATE TABLE \"vendas\" (\"id\"	INTEGER NOT NULL,\"nome_cliente\" TEXT, valor_total decimal, data_compra datetime, PRIMARY KEY(\"id\" AUTOINCREMENT));");
       queries.add("CREATE TABLE items_despesas (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL, descricao TEXT UNIQUE);");
       queries.add("CREATE TABLE \"categorias\" (\"id\" INTEGER NOT NULL,\"categoria\" TEXT NOT NULL UNIQUE, PRIMARY KEY(\"id\" AUTOINCREMENT));");
       queries.add("CREATE TABLE categorias_despesas (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, categoria TEXT);");
       queries.add("CREATE TABLE despesas (id INTEGER PRIMARY KEY AUTOINCREMENT, id_item INT REFERENCES items_despesas (id), data DATETIME, valor_gasto REAL NOT NULL, id_categoria INT REFERENCES categorias_despesas (id));");
       queries.add("CREATE TABLE \"items\" (\"id\"	INTEGER NOT NULL, \"codigo\"	TEXT NOT NULL UNIQUE, \"id_categoria\"	INTEGER NOT NULL, \"preco\"	REAL NOT NULL, \"descricao\"	TEXT NOT NULL, \"id_unidade\"	INTEGER NOT NULL, PRIMARY KEY(\"id\" AUTOINCREMENT), FOREIGN KEY(\"id_unidade\") REFERENCES \"unidades\"(\"id\"), FOREIGN KEY(\"id_categoria\") REFERENCES \"categorias\"(\"id\"))");
       queries.add("CREATE TABLE \"compras\" (\"id\" INTEGER NOT NULL,\"id_venda\" INTEGER NOT NULL,\"id_item\"	INTEGER NOT NULL, \"quantidade\"	INTEGER NOT NULL, valor real, FOREIGN KEY(\"id_item\") REFERENCES \"items\"(\"id\"),FOREIGN KEY(\"id_venda\") REFERENCES \"vendas\"(\"id\"), PRIMARY KEY(\"id\" AUTOINCREMENT));");
       return queries;
    }
   
    
}