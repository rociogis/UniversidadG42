/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadg42.AccesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg42.Entidades.Alumno;

/**
 *
 * @author 54351
 */
public class AlumnoData {
    private Connection con=null;
    
    public AlumnoData(){
        con=Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql="insert into alumno(dni,apellido,nombre,fechaNac,estado)"
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,alumno.getDni());
            ps.setString(2,alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5,alumno.isEstado());
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(1);
                JOptionPane.showMessageDialog(null,"Alumno Guardado");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
        }
    }
    
    public Alumno buscarAlumno(int id){
       
        String sql="select dni,apellido,nombre,fechaNac from alumno where idAlumno=? and estado=1";
        Alumno alumno=null;
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                alumno=new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"Ese alumno NO EXISTE");
            }
           ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
        }
        return alumno;
    }
    
    public Alumno buscarAlumnoPorDni(int dni){
         String sql = "SELECT idAlumno,dni, apellido, nombre, fechaNac, estado FROM alumno WHERE DNI= ?"; // AND ESTADO = 1"
        Alumno alum = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alum = new Alumno();
                alum.setIdAlumno(rs.getInt("idAlumno"));
                alum.setDni(rs.getInt("dni"));
                alum.setApellido(rs.getString("apellido"));
                alum.setNombre(rs.getString("nombre"));
                alum.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alum.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "ALUMNO NO EXISTE");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACCEDER A TABLA ALUMNOS");
        }
        return alum;
//        String sql="select idAlumno,dni,apellido,nombre,fechaNac from alumno where dni=? and estado=1";
//        Alumno alumno=null;
//        try {
//            PreparedStatement ps=con.prepareStatement(sql);
//            ps.setInt(1,dni);
//            ResultSet rs=ps.executeQuery();
//            if(rs.next()){
//                alumno=new Alumno();
//                alumno.setIdAlumno(rs.getInt("idAlumno"));
//                alumno.setDni(rs.getInt("dni"));
//                alumno.setApellido(rs.getString("apellido"));
//                alumno.setNombre(rs.getString("nombre"));
//                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
//                alumno.setEstado(true);
//            }else{
//                JOptionPane.showMessageDialog(null,"Ese alumno NO EXISTE");
//            }
//           ps.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
//        }
        
    }
    
    public List<Alumno>listarAlumnos(){
        String sql="select * from alumno where estado=1";
        ArrayList<Alumno>alumnos=new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Alumno alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
                
                alumnos.add(alumno);
            }
           ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
        }
        return alumnos;
    }
    
    public void modificarAlumno(Alumno alumno){
        String sql="update alumno set dni=?,apellido=?,nombre=?,fechaNac=?,estado=? "
                + "where idAlumno=? ";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,alumno.getDni());
            ps.setString(2,alumno.getApellido());
            ps.setString(3,alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNac()));
            ps.setBoolean(5, true);
            ps.setInt(6,alumno.getIdAlumno());
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Alumno Modificado");
            }
            ps.close();    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
        }
        
    }
    
    public void eliminarAlumno(int id){
        String sql="update alumno set estado=0 where idAlumno=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Alumno Eliminado");
            }
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno"); 
        }
    }
    
}
