/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectDB;

import java.sql.*;

/**
 *
 * @author THANHTRI
 */
public class ConnectDB {
    private static Connection con = null;
    private static ConnectDB instance = null;
    
    public static Connection getConnection() {
        return con;
    }
    
    public static ConnectDB getInstance() {
        return instance;
    }
    
    public static void connect() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLINHAHANG";
        String username = "sa";
        String pwd = "sapassword";
        try {
            con = DriverManager.getConnection(url, username, pwd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
