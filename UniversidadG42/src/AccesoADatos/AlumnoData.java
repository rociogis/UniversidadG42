
package AccesoADatos;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlumnoData {
    private Connection con = null;
    
    public AlumnoData(){
        con = Conexion.getConexion();
    }

   public void GuardarAlumno(Alumno alumno){
       String sql = "INSERT INTO ALUMNOS (DNI,APELLIDO,NOMBRE,FECHANAC,ESTADO)"
               + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,alumno.getDNI());
            ps.setString(2,alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5,alumno.isEstado()); 
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"ALUMNO GUARDADO");
            }
            ps.close();;
            
        } catch (SQLException ex) {
           JOptionPane.showInternalMessageDialog(null, "ERROR AL ACCEDER A LA BASE DE DATOS");
        }
   
   }
   public Alumno BuscarAlumno(int Id){
       String sql = "SELECT DNI, APELLIDO, NOMBRE, FECHANAC FROM alumnos WHERE ID_Alumno= ? AND ESTADO = 1";
       Alumno alum = null;
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            ResultSet rs = ps.executeQuery();
           if (rs.next()) {
            alum = new Alumno();
            alum.setIdAlumno(Id);
            alum.setDNI(rs.getInt("DNI"));
            alum.setApellido(rs.getString("APELLIDO"));
            alum.setNombre(rs.getString("NOMBRE"));
            alum.setFechaNac(rs.getDate("FECHANAC").toLocalDate());
            alum.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"ALUMNO NO EXISTE");
            }
           ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA ALUMNOS");
                   
        }
       
        return alum;
   }
   public Alumno BuscarAlumnoPorDni(int DNI){
    String sql = "SELECT ID_Alumno,DNI, APELLIDO, NOMBRE, FECHANAC FROM alumnos WHERE DNI= ? AND ESTADO = 1";
       Alumno alum = null;
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,DNI);
            ResultSet rs = ps.executeQuery();
           if (rs.next()) {
            alum = new Alumno();
            alum.setIdAlumno(rs.getInt("ID_Alumno"));
            alum.setDNI(rs.getInt("DNI"));
            alum.setApellido(rs.getString("APELLIDO"));
            alum.setNombre(rs.getString("NOMBRE"));
            alum.setFechaNac(rs.getDate("FECHANAC").toLocalDate());
            alum.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"ALUMNO NO EXISTE");
            }
           ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA ALUMNOS");
                   
        }
       
        return alum;
   }
   public List <Alumno> listarAlumno(){
    String sql = "SELECT ID_Alumno,DNI, APELLIDO, NOMBRE, FECHANAC FROM alumnos WHERE ESTADO = 1";
       ArrayList <Alumno> Alumnos = new ArrayList<>();
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
            Alumno alumno = new Alumno();
            alumno.setIdAlumno(rs.getInt("ID_Alumno"));
            alumno.setDNI(rs.getInt("DNI"));
            alumno.setApellido(rs.getString("APELLIDO"));
            alumno.setNombre(rs.getString("NOMBRE"));
            alumno.setFechaNac(rs.getDate("FECHANAC").toLocalDate());
            alumno.setEstado(true);
            
            Alumnos.add(alumno);
            }
           ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA ALUMNOS");          
        }
        return Alumnos;
   
   }
   public void ModificarAlumno(Alumno alumno){
    String sql = "UPDATE alumnos SET dni = ?, apellido = ?, nombre = ?, fechanac=?"
            + " WHERE Id_Alumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDNI());
            ps.setString(2,alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNac()));
            ps.setInt(5, alumno.getIdAlumno());
            int exito = ps.executeUpdate();
            if(exito == 1){
            JOptionPane.showMessageDialog(null,"ALUMNO MODIFICADO");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA ALUMNO");
                   
        }
   }
   public void EliminarAlulmno(int Id){
    String sql = "UPDATE alumnos SET estado=0"
            + " WHERE Id_Alumno = ?";
     try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,Id);
            int exito = ps.executeUpdate();
            if(exito == 1){
            JOptionPane.showMessageDialog(null,"ALUMNO ELIMINADO");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA ALUMNO");
                   
        }
   }
   
    
}
