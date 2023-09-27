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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg42.Entidades.Alumno;
import universidadg42.Entidades.Inscripcion;
import universidadg42.Entidades.Materia;

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
                insc.setIdInscripcion(1);
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
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alumno=aluData.buscarAlumno(rs.getInt("idAlumno"));
                Materia materia=matData.buscarMateria(rs.getInt("idMateria"));
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
        String sql = "select * from inscripcion where idAlumno=?";
        ArrayList<Inscripcion> Inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripcion(rs.getInt("idInscripto"));
                Alumno alumno=aluData.buscarAlumno(rs.getInt("idAlumno"));
                Materia materia=matData.buscarMateria(rs.getInt("idMateria"));
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
        String sql="select inscripcion.idMateria,nombre,año,estado from inscripcion, "
                + "materia where inscripcion.idMateria=materia.idMateria\n"
               + "and inscripcion.idAlumno=?"; 
        List<Materia>materias=new ArrayList<Materia>();
          try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAñoMateria(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion ");
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
                materia.setAñoMateria(rs.getInt("año"));
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
                alumno.setDni(rs.getInt("dni"));
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
