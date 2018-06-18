package com.example.adi.escuelaapp.Entidades;

/**
 * Created by Adi on 16/06/2018.
 */

public class Calificacion {

    private Integer id;
    private double calificacion;
    private String fecha;
    private Materia materia;
    private Usuario usuario;
    public Calificacion() {
    }

    public Calificacion(double calificacion, Materia materia, Usuario usuario) {
        this.calificacion = calificacion;
        this.materia = materia;
        this.usuario = usuario;
    }

    public Calificacion(Integer id, double calificacion, String fecha, Materia materia, Usuario usuario) {
        this.id = id;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.materia = materia;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
