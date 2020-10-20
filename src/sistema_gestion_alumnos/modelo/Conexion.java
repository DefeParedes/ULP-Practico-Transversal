package sistema_gestion_alumnos.modelo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String base = "sistema_gestion_alumnos";
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    private final String user = "root";
    private final String pass = "";
    //1 - DECLARO VARIABLE CONNECTION
    private Connection con; 
    
    public Connection getConnection(){
        try{
            //2 - DECLARO DRIVER
            Class.forName("com.mysql.jdbc.Driver");
            //3 - ESTABLEZCO CONEXION
            con = (Connection) DriverManager.getConnection(url,user,pass);
        }
        catch(SQLException | ClassNotFoundException e){
            System.err.print(e);
        }
        return con;
    }
    
}
