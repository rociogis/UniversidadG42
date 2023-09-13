
package Entidades;

import java.time.LocalDate;

public class Alumno {
    private int IdAlumno;
    private int DNI;
    private String Apellido;
    private String Nombre;
    private LocalDate FechaNac;
    private boolean Estado;

    public Alumno() {
    }

    public Alumno(int IdAlumno,int DNI, String Apellido, String Nombre, LocalDate FechaNac, boolean Estado) {
        this.IdAlumno = IdAlumno;
        this.Apellido = Apellido;
        this.Nombre = Nombre;
        this.FechaNac = FechaNac;
        this.Estado = Estado;
        this.DNI = DNI;
    }

    public Alumno(int DNI, String Apellido, String Nombre, LocalDate FechaNac, boolean Estado) {
        this.Apellido = Apellido;
        this.Nombre = Nombre;
        this.FechaNac = FechaNac;
        this.Estado = Estado;
        this.DNI = DNI;
    }

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.IdAlumno = IdAlumno;
    }
     public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public LocalDate getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(LocalDate FechaNac) {
        this.FechaNac = FechaNac;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

   
    @Override
    public String toString() {
        return "Alumno -> " + "#Alumno: " + IdAlumno + " " + Apellido + ", " + Nombre + ", " + FechaNac + ", Estado: " + Estado + '}';
    }
    
    
    
}
