/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadg42.Entidades;

/**
 *
 * @author 54351
 */
public class Materia {
    private int idMateria;
    private String nombre;
    private int añoMateria;
    private boolean estado;

    public Materia() {
    }

    public Materia(int idMateria, String nombre, int añoMateria, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.añoMateria = añoMateria;
        this.estado = estado;
    }

    public Materia(String nombre, int añoMateria, boolean estado) {
        this.nombre = nombre;
        this.añoMateria = añoMateria;
        this.estado = estado;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoMateria() {
        return añoMateria;
    }

    public void setAñoMateria(int añoMateria) {
        this.añoMateria = añoMateria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", a\u00f1oMateria=" + añoMateria + '}';
    }
    
}
