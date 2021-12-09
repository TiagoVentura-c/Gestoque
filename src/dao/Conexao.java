package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tiago
 */
public class Conexao {
    
    private final String driver = "org.sqlite.JDBC";
    private final String path ="C:\\Users\\"+System.getProperty("user.name")+"\\.gestoque\\";
    private final String databaseName="\\base.db";
    private final String url = "jdbc:sqlite:"+path+databaseName;
    
    public static Connection conn = null;
    public static boolean cria = true;
    
    public Conexao(){
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            conn = DriverManager.getConnection(url); 
            cria = false;
          }
        catch(SQLException e) {
            
            }
        }
    
    
    
    
    public void close() {
        
        try {
           
            if ( conn != null && !conn.isClosed() ) {
                conn.close();
            }
            
        } catch (SQLException e) {
            System.err.println("Close Error : " + e);
        }
    }
    }
   