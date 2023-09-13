
package AccesoADatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    
   private static final String URL = "jdbc:mariadb://localhost/";
   private static final String Db  = "UniversidadG42";
   private static final String Usuario = "root";
   private static final String Password  = "";
   private static Connection connection;
    
   private Conexion(){}
   public static Connection getConexion(){
        if (connection == null) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL + Db , Usuario, Password);
                JOptionPane.showMessageDialog(null, "CONEXION EXITOSA !");
                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "error al cargar el driver" );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "error de conexion");
            }
        }
        return connection;
    }
}
