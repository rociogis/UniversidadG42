
package Entidades;

public class Inscripcion {
    private int IdInscripto;
    private Alumno alumno;
    private Materia materia;
    private Double Nota;

    public Inscripcion() {
    }

    public Inscripcion(int IdInscripto, Alumno alumno, Materia materia, Double Nota) {
        this.IdInscripto = IdInscripto;
        this.alumno = alumno;
        this.materia = materia;
        this.Nota = Nota;
    }

    public Inscripcion(Alumno alumno, Materia materia, Double Nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.Nota = Nota;
    }

    public int getIdInscripto() {
        return IdInscripto;
    }
    

    public void setIdInscripto(int IdInscripto) {
        this.IdInscripto = IdInscripto;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }


    public Double getNota() {
        return Nota;
    }

    public void setNota(Double Nota) {
        this.Nota = Nota;
    }
    
    @Override
    public String toString() {
        String insc = IdInscripto + " " +  alumno.getApellido()+ " " + alumno.getNombre() + " " + materia.getNombre();
                return insc;
    }
}
