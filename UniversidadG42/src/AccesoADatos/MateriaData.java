
package AccesoADatos;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MateriaData {
    private Connection con = null;
    
    public MateriaData(){
        con = Conexion.getConexion();
    }
    
   public void GuardarMateria(Materia materia){
       String sql = "INSERT INTO MATERIA (NOMBRE,AÑO,ESTADO)"
               + "VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAño());
            ps.setBoolean(3,materia.getEstado()); 
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"MATERIA GUARDADA");
            }
            ps.close();;
            
        } catch (SQLException ex) {
           JOptionPane.showInternalMessageDialog(null, "ERROR AL ACCEDER A LA BASE DE DATOS");
        }
   
   }
   public Materia BuscarMateria(int Id){
       String sql = "SELECT NOMBRE,AÑO FROM materia WHERE ID_Materia= ? AND ESTADO = 1";
       Materia mat = null;
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            ResultSet rs = ps.executeQuery();
           if (rs.next()) {
            mat = new Materia();
            mat.setIdMateria(Id);
            mat.setNombre(rs.getString("NOMBRE"));
            mat.setAño(rs.getInt("AÑO"));
            mat.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"LA MATERIA NO EXISTE");
            }
           ps.close();  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA MATERIA");
        }
        return mat;
   }
   public Materia BuscarMateriaPorNombre(String Nombre){
     String sql = "SELECT ID_Materia, NOMBRE, AÑO FROM materia WHERE NOMBRE= ? AND  ESTADO = 1";
       Materia mat = null;
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,Nombre);
            ResultSet rs = ps.executeQuery();
           if (rs.next()) {
            mat = new Materia();
            mat.setIdMateria(rs.getInt("ID_Materia"));
            mat.setNombre(rs.getString("NOMBRE"));
            mat.setAño(rs.getInt("AÑO"));
            mat.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"LA MATERIA NO EXISTE");
            }
           ps.close();  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA MATERIA");
        }
        return mat;
   }
   public List <Materia> listarMateria(){
    String sql = "SELECT ID_Materia, NOMBRE, AÑO FROM materia WHERE ESTADO = 1";
       ArrayList <Materia> Materias = new ArrayList<>();
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
            Materia materia = new Materia();
            materia.setIdMateria(rs.getInt("ID_Materia"));
            materia.setNombre(rs.getString("NOMBRE"));
            materia.setAño(rs.getInt("AÑO"));
            materia.setEstado(true);
            Materias.add(materia);
            }
           ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA MATERIA");          
        }
        return Materias;
   
   }
   public void ModificarMateria(Materia materia){
    String sql = "UPDATE materia SET nombre = ?, año = ?"
            + " WHERE ID_Materia = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAño());
            ps.setInt(3,materia.getIdMateria());
            int exito = ps.executeUpdate();
            if(exito == 1){
            JOptionPane.showMessageDialog(null,"MATERIA MODIFICADA");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA MATERIA");
                   
        }
   }
   public void EliminarMateria(int Id){
    String sql = "UPDATE materia SET estado=0"
            + " WHERE Id_Materia = ?";
     try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            int exito = ps.executeUpdate();
            if(exito == 1){
            JOptionPane.showMessageDialog(null,"MATERIA ELIMINADA");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA MATERIA");
                   
        }
   }
}
