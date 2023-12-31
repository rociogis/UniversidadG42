/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;

/**
 *
 * @author 54351
 */
public class InscripcionData {
    private Connection con=null;
    private AlumnoData aluData=new AlumnoData();
    private MateriaData matData=new MateriaData();

    public InscripcionData() {
        con=Conexion.getConexion();
    }
    
    public void guardarInsccripcion(Inscripcion insc) {
        String sql = "insert into inscripcion(nota,idAlumno,idMateria)"
                + "values(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insc.setIdInscripto(1);
                JOptionPane.showMessageDialog(null, "Inscripcion Exitosa");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }
    
    public List<Inscripcion> ObtenerInscripciones() {
        String sql = "select * from inscripcion";
        ArrayList<Inscripcion> Inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("idInscripto"));
                Alumno alumno=aluData.BuscarAlumno(rs.getInt("idAlumno"));
                Materia materia=matData.BuscarMateria(rs.getInt("idMateria"));
                insc.setAlumno(alumno);
                insc.setMateria(materia);
                insc.setNota(rs.getDouble("nota"));
                
                Inscripciones.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return Inscripciones;
    }
    
    public List<Inscripcion>obtenerInscripcionesporAlumno(int idAlumno){
        String sql = "select * from inscripcion where id_Alumno=?";
        ArrayList<Inscripcion> Inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("id_Inscripto"));
                Alumno alumno=aluData.BuscarAlumno(rs.getInt("id_Alumno"));
                Materia materia=matData.BuscarMateria(rs.getInt("id_Materia"));
                insc.setAlumno(alumno);
                insc.setMateria(materia);
                insc.setNota(rs.getDouble("nota"));
                
                Inscripciones.add(insc);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return Inscripciones;
    }

    public List<Materia>obtenerMateriasCursadas(int idAlumno){
        List<Materia>materias=new ArrayList<Materia>();
        String sql="select inscripcion.id_Materia,nombre,año,estado from inscripcion, "
                + "materia where inscripcion.id_Materia=materia.id_Materia\n"
               + "and inscripcion.id_Alumno=?"; 

          try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_Materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("ESTADO"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion "+ex.getMessage());
        }
        return materias;
    }
    
    public List<Materia>obtenerMateriasNoCursadas(int idAlumno){
        String sql="select * from materia where estado=1 and idMateria not in "
                + "(select idMateria from inscripcion where idAlumno=?)";
        List<Materia>materias=new ArrayList<Materia>();
          try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return materias;
                
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        String sql = "delete from inscripcion where inscripcion.idAlumno=? and inscripcion.idMateria=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int borrar=ps.executeUpdate();
            if(borrar>0){
                JOptionPane.showMessageDialog(null, "Inscripcion Borrada");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }

    }
    
    public void actualizarNota(int idAlumno,int idMateria,double nota){
        String sql="update inscripcion set nota=? where idAlumno=? and idMateria=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
             int nota1=ps.executeUpdate();
             if(nota1>0){
                JOptionPane.showMessageDialog(null, "Nota Actualizada"); 
             }
             ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria){
        String sql="select inscripcion.idAlumno,dni,apellido,nombre,fechaNac,estado from inscripcion,"
                + "alumno where inscripcion.idAlumno=alumno.idAlumno\n and inscripcion.idMateria=?";
        ArrayList<Alumno>alumnos=new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, idMateria);
             ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDNI(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
               
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return alumnos;
    }
    
     
}
