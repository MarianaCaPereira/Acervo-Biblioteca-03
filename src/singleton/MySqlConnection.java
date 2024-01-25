/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ericd
 */
public class MySqlConnection {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost";
    private static final String PORT = "3306";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB = "Acervo";
    private static final String TIMEZONE = "useTimezone=true&serverTimezone=UTC";
    
    private Connection conn;
    private static MySqlConnection instance;

    public MySqlConnection() throws ClassNotFoundException, SQLException {
        
        String conexao = URL + ":" + PORT + "/" + DB + "?" + TIMEZONE;
        
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(conexao, USER, PASS);   
    }
    
    public static MySqlConnection getInstance() throws ClassNotFoundException, SQLException{
        
        if(instance == null)
            instance = new MySqlConnection();

        return instance;
    }
    
    public Connection getConnection(){
        return conn;
    }
    
 
    
    
}
