package mainpackage;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 

public class serverTest{
 
    //private final String url = "jdbc:postgresql://localhost/javaServer";
    //private final String user = "postgres";
    //private final String password = "eimaikouklos";
 

    public Connection connect() {
        Connection conn = null;
        try {
        	conn = DriverManager.getConnection("jdbc:postgresql:postgres", "postgres", "eimaikouklos");

            System.out.println("Connected to the PostgreSQL server successfully.");
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pelates");
            ResultSet Rs = stmt.executeQuery();
            while(Rs.next()) {
            	System.out.println(Rs.getInt(1) + " " + Rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }

}