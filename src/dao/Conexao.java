package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tiago
 */
public class Conexao {
    
    private final String driver = "org.sqlite.JDBC";
    private final String url = "jdbc:sqlite:db//base.db";
    
    public Connection conn = null;
    
    public Conexao(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url);
            System.out.println("Connection Success");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection Error : " + e);
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
