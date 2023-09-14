
package AccesoADatos;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
    private Connection con = null;
    private AlumnoData AlumDat= new AlumnoData();
    private MateriaData MatDat  = new MateriaData();

    public InscripcionData() {
         con = Conexion.getConexion();
    }
    
    public void GuardaInscripcion(Inscripcion insc){
      String sql = "INSERT INTO inscripcion (NOTA,ID_Alumno,ID_Materia)"
               + "VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,insc.getNota());
            ps.setInt(2,insc.getAlumno().getIdAlumno());
            ps.setInt(3,insc.getMateria().getIdMateria()); 
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                insc.setIdInscripto(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"INSCRIPCION REALIZADA");
            }
            ps.close();;
            
        } catch (SQLException ex) {
           JOptionPane.showInternalMessageDialog(null, "ERROR AL ACCEDER A LA BASE DE DATOS");
        }
   
    
    }

    public List<Inscripcion> ObtenerInscripcion() {
        String sql = "select * from inscripcion";
        
        ArrayList<Inscripcion> Inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("idInscripto"));
                Alumno alumno = AlumDat.BuscarAlumno(rs.getInt("idAlumno"));
                Materia materia=MatDat.BuscarMateria(rs.getInt("idMateria"));
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
    public List<Inscripcion>ObtenerInscripcionPorAlumno(int ID){
      String sql = "SELECT * FROM inscripcion WHERE ID_Alumno = ?";
        
        ArrayList<Inscripcion> Inscripciones = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion insc = new Inscripcion();
                insc.setIdInscripto(rs.getInt("ID_Inscripto"));
                Alumno alumno = AlumDat.BuscarAlumno(rs.getInt("ID_Alumno"));
                Materia materia=MatDat.BuscarMateria(rs.getInt("ID_Materia"));
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
    public ArrayList<Materia>ObtenerMateriasCursadas(int ID){
    
     String sql = "SELECT inscripcion.ID_Materia, nombre, año, estado FROM inscripcion, materia "
             + "WHERE inscripcion.ID_Materia = materia.ID_Materia\n AND inscripcion.ID_Alumno = ?";
        
        ArrayList<Materia> Materias = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            Materia materia = new Materia();
            while (rs.next()) {
                materia.setIdMateria(rs.getInt("ID_Materia"));
                materia.setNombre(rs.getString("NOMBRE"));
                materia.setAño(rs.getInt("AÑO"));
                materia.setEstado(rs.getBoolean("ESTADO"));
                Materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return Materias;
    
}
//    public List<Materia>ObtenerMateriasNoCursadas(int ID){}
//    public void BorrarInscripcionMateriaAlumno(int IdAlumno, IdMateria){}
//    public void ActualizarNota(int IdAlumno, IdMateria, double Nota){}
//    public List<Alumno>ObtenerAlumnosporMateria(int IdMateria){}
}