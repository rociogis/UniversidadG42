
package Entidades;

public class Materia {
    private int IdMateria;
    private String Nombre;
    private int Año;
    private Boolean Estado;

    public Materia() {
    }

    public Materia(int IdMateria, String Nombre, int Año, Boolean Estado) {
        this.IdMateria = IdMateria;
        this.Nombre = Nombre;
        this.Año = Año;
        this.Estado = Estado;
    }

    public Materia(String Nombre, int Año, Boolean Estado) {
        this.Nombre = Nombre;
        this.Año = Año;
        this.Estado = Estado;
    }

    public int getIdMateria() {
        return IdMateria;
    }

    public void setIdMateria(int IdMateria) {
        this.IdMateria = IdMateria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int Año) {
        this.Año = Año;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }

    @Override
    public String toString() {
        return "Materia:" + "IdMateria: " + IdMateria + ", Nombre: " + Nombre + ", A\u00f1o: " + Año + ", Estado: " + Estado + '}';
    }
    
    
}
