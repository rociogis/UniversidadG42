/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadg42;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import universidadg42.AccesoADatos.AlumnoData;
import universidadg42.AccesoADatos.Conexion;
import universidadg42.AccesoADatos.InscripcionData;
import universidadg42.AccesoADatos.MateriaData;
import universidadg42.Entidades.Alumno;
import universidadg42.Entidades.Inscripcion;
import universidadg42.Entidades.Materia;

/**
 *
 * @author 54351
 */
public class UniversidadG42 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Connection conn=Conexion.getConexion();
//       Alumno alum=new Alumno(42424242,"Athos","Miguel",LocalDate.of(2001,8,21),true);
//       AlumnoData alu=new AlumnoData();
//       alu.guardarAlumno(alum);

//        AlumnoData alu = new AlumnoData();
//        Alumno alumnoEncontrado = alu.buscarAlumno(4);
//        if (alumnoEncontrado != null) {
//            System.out.println("ALUMNO ENCONTRADO");
//            System.out.println(alumnoEncontrado.toString());
//        }

//        AlumnoData alu = new AlumnoData();
//        Alumno alumnoEncontrado = alu.buscarAlumnoPorDni(36852852);
//        if (alumnoEncontrado != null) {
//            System.out.println("ALUMNO ENCONTRADO");
//            System.out.println(alumnoEncontrado.toString());
//        }

//        AlumnoData alu = new AlumnoData();
//        for (Alumno alumno : alu.listarAlumnos()) {
//            System.out.println(alumno.toString());
//        }

//        Alumno alum = new Alumno(3, 23852852, "Martel", "Sofia", LocalDate.of(2000, 10, 10), true);
//        AlumnoData alu = new AlumnoData();
//        alu.modificarAlumno(alum);
       
//        AlumnoData alu = new AlumnoData();
//        alu.eliminarAlumno(3);

//        Connection conn = Conexion.getConexion();
//        Materia materia = new Materia("Lengua", 2, true);
//        MateriaData mate = new MateriaData();
//        mate.guardarMateria(materia);

//        MateriaData mate = new MateriaData();
//        Materia materiaEncontrada = mate.buscarMateria(3);
//        if (materiaEncontrada != null) {
//            System.out.println("MATERIA ENCONTRADA");
//            System.out.println(materiaEncontrada.toString());
//        }

//        MateriaData mate = new MateriaData();
//        Materia materiaEncontrada = mate.buscarMateriaPorNombre("Logica");
//        if (materiaEncontrada != null) {
//            System.out.println("MATERIA ENCONTRADA");
//            System.out.println(materiaEncontrada.toString());
//        }

//         Materia materia=new Materia(8,"Literatura",2,true);
//         MateriaData mate=new MateriaData();
//         mate.modificarMateria(materia);
         
//          MateriaData mate = new MateriaData();
//          mate.eliminarMateria(8);
         
//         MateriaData mate=new MateriaData();
//         for(Materia materia:mate.listarMaterias()){
//             System.out.println(materia.toString());
//         }
         
//         Connection conn=Conexion.getConexion();
//         AlumnoData alu=new AlumnoData();
//         Alumno alumEncontrado=alu.buscarAlumno(4);
//         MateriaData mate=new MateriaData();
//         Materia matEncontrada=mate.buscarMateria(4);
//         Inscripcion insc=new Inscripcion(alumEncontrado,matEncontrada,7.50);
//         InscripcionData iData=new InscripcionData();
//         iData.guardarInsccripcion(insc);
         
//         InscripcionData iData=new InscripcionData();
//         for(Inscripcion insc:iData.ObtenerInscripciones()){
//             System.out.println(insc.toString());
//         }
         
//         InscripcionData iData=new InscripcionData();
//         for(Inscripcion insc:iData.obtenerInscripcionesporAlumno(4)){
//             System.out.println(insc.toString());
//         }

         InscripcionData iData=new InscripcionData();
         for(Materia mate:iData.obtenerMateriasCursadas(5)){
             System.out.println("Nombre: "+mate.getNombre()); 
         }

//         InscripcionData iData=new InscripcionData();
//         for(Materia mat:iData.obtenerMateriasNoCursadas(6)){
//             System.out.println("Nombre: "+mat.getNombre());
//         }
       
//          InscripcionData iData=new InscripcionData();
//          iData.borrarInscripcionMateriaAlumno(2, 2);

//          InscripcionData iData=new InscripcionData();
//          iData.actualizarNota(5, 2, 8);
//    
//          InscripcionData iData=new InscripcionData();
//          for(Alumno alu:iData.obtenerAlumnosPorMateria(5)){
//              System.out.println(alu.toString());
//          }

    }
 
    }
