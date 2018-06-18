package com.example.adi.escuelaapp.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adi on 16/06/2018.
 */

public class Usuario implements Parcelable{
    private Integer id;
    private String nombre;
    private Credencial credencial;
    private boolean activo;
    private Categoria categoria;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, Credencial credencial, boolean activo, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.credencial = credencial;
        this.activo = activo;
        this.categoria = categoria;
    }

    public Usuario(String nombre, Credencial credencial, Categoria categoria) {
        this.nombre = nombre;
        this.credencial = credencial;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.nombre);
        dest.writeParcelable(this.credencial, flags);
        dest.writeByte(this.activo ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.categoria, flags);
    }

    protected Usuario(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.credencial = in.readParcelable(Credencial.class.getClassLoader());
        this.activo = in.readByte() != 0;
        this.categoria = in.readParcelable(Categoria.class.getClassLoader());
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
