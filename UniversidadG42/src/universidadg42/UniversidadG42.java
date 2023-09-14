
package universidadg42;

import AccesoADatos.AlumnoData;
import AccesoADatos.Conexion;
import AccesoADatos.InscripcionData;
import AccesoADatos.MateriaData;
import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.time.LocalDate;

public class UniversidadG42 {

    public static void main(String[] args) {
       
        Connection con = Conexion.getConexion();

//        Alumno alumno = new Alumno(2,32656987,"SALAS","JUAN RAMON",LocalDate.of(1993,9,13),true);
        //AlumnoData Alum = new AlumnoData();
        //Alum.GuardarAlumno(alumno);
        //Alum.ModificarAlumno(alumno);
        //Alum.EliminarAlulmno(1);
        // Alumno Encontrado = Alum.BuscarAlumno(4);
//        Alumno Encontrado = Alum.BuscarAlumnoPorDni(40669875);
//         System.out.println(Encontrado.toString());
//        for (Alumno alumno : Alum.listarAlumno()) {
//            System.out.println(alumno.toString());
//        }

//        Materia materia = new Materia(2,"MATEMATICA",2,true);
//        MateriaData mat = new MateriaData();
//        mat.GuardarMateria(materia);
//        Materia encontrada = mat.BuscarMateria(1);
//        Materia encontrada = mat.BuscarMateriaPorNombre("MATEMATICA");
//        System.out.println(encontrada.toString());
//        Materia materia = new Materia(1,"LENGUA",2,true);
//        mat.ModificarMateria(materia);
        
//        Materia mat2 = new Materia("MATEMATICA",1,true);
//        mat.GuardarMateria(mat2);
//        Materia mat3 = new Materia("LOGICA",1,true);
//        mat.GuardarMateria(mat3);
//        Materia mat4 = new Materia("LABORATORIO",3,true);
//        mat.GuardarMateria(mat4);
//            for (Materia materia : mat.listarMateria()) {
//            System.out.println(materia.toString());
//            }

//        Inscripcion Insc = new Inscripcion(alumno,materia,07.55);
        InscripcionData InscDat = new InscripcionData();
//        InscDat.GuardaInscripcion(Insc);
        for (Inscripcion Insc : InscDat.ObtenerInscripcionPorAlumno(2)) {
            System.out.println(Insc.toString());
        }
        System.out.println("-------------------------------------");
        
        for (Materia mat : InscDat.ObtenerMateriasCursadas(3)) {
            System.out.println(mat.toString());
        }

    }

    
    
}
