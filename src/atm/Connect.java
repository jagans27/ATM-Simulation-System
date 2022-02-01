package atm;

import java.sql.*;
import javax.swing.JOptionPane;

public class Connect {
    
    Connection con = null;
    Statement stmt = null;
    
    public Connect() {
        try {
            final String DB_URL = "jdbc:mysql://localhost:3306/ATM";
            final String USERNAME = "root";
            final String PASSWORD = "";
            con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = con.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
    }
    
}
