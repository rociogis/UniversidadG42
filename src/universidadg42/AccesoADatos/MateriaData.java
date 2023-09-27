/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadg42.AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import universidadg42.Entidades.Materia;

/**
 *
 * @author 54351
 */
public class MateriaData {
    private Connection con=null;

    public MateriaData() {
        con=Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        String sql="insert into materia(nombre,año,estado)"
                + "values(?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAñoMateria());
            ps.setBoolean(3,materia.isEstado());
            ps.executeUpdate();
            
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(1);
                JOptionPane.showMessageDialog(null,"Materia Guardada");
            } 
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
        }
    }
    
    public Materia buscarMateria(int id){
        String sql = "select nombre,año,estado from materia where idmateria=?";  
        Materia materia = null;
        try {
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                materia=new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAñoMateria(rs.getInt("año"));
//                materia.setEstado(true);
                materia.setEstado(rs.getBoolean("estado"));
            }else{
                JOptionPane.showMessageDialog(null,"Esa materia NO EXISTE");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
        }
        return materia;   
    }
    
    public Materia buscarMateriaPorNombre(String nombre){
        String sql = "select idMateria,nombre,año from materia where nombre=? and estado=1";
        Materia materia = null;
        try {
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAñoMateria(rs.getInt("año"));
                materia.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"Esa materia NO EXISTE");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
        }
        return materia;   
    }
    
    public void modificarMateria(Materia materia){
        String sql="update materia set nombre=?,año=? where idmateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAñoMateria());
            ps.setInt(3,materia.getIdMateria());
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Materia Modificada");
            }
            ps.close();    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
        }   
    }
     
    public void eliminarMateria(int id){
        String sql="update materia set estado=0 where idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            int exito=ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Materia Eliminada");
            }
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia"); 
        }
    }
    
    public List<Materia>listarMaterias(){
        String sql="select * from materia where estado=1";
        ArrayList<Materia>materias=new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAñoMateria(rs.getInt("año"));
                materia.setEstado(true);
                
                materias.add(materia);
            }
           ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla materia");
        }
        return materias;
    }
    
    
}
