
package AccesoADatos;

import Entidades.Inscripcion;
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
    private AlumnoData AlumDat;
    private MateriaData MatDat;

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

    public List<Inscripcion>ObtenerInscripcion(){
      String sql = "SELECT ID_Inscripto, NOTA, ID_Alumno, ID_Materia FROM Inscripcion";
       ArrayList <Inscripcion> Inscripciones = new ArrayList<>();
       
    try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
            Inscripcion insc = new Inscripcion();
            insc.setIdInscripto(rs.getInt("ID_Inscripto"));
            insc.setNota(rs.getDouble("NOTA"));
//            insc.setAlumno(rs.getInt((insc.getAlumno().getIdAlumno())));                           
//            insc.setMateria((rs.getInt("ID_Materia"))); 
            Inscripciones.add(insc);
            }
           ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog (null,"ERROR AL ACCEDER A TABLA MATERIA");          
        }
        return Inscripciones;
    
    
    
    }
//    public List<Inscripcion>ObtenerInscripcionPorAlumno(int ID){}
//    public List<Materia>ObtenerMateriasCursadas(int ID){}
//    public List<Materia>ObtenerMateriasNoCursadas(int ID){}
//    public void BorrarInscripcionMateriaAlumno(int IdAlumno, IdMateria){}
//    public void ActualizarNota(int IdAlumno, IdMateria, double Nota){}
//    public List<Alumno>ObtenerAlumnosporMateria(int IdMateria){}
}