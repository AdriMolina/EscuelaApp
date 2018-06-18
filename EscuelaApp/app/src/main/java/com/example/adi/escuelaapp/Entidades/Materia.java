package com.example.adi.escuelaapp.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adi on 16/06/2018.
 */

public class Materia implements Parcelable{
    private Integer id;
    private String clave;
    private String nombre;
    private boolean activo;

    public Materia() {
    }

    public Materia(Integer id, String clave, String nombre, boolean activo) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.activo = activo;
    }

    public Materia(String clave, String nombre) {
        this.clave = clave;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.clave);
        dest.writeString(this.nombre);
        dest.writeByte(this.activo ? (byte) 1 : (byte) 0);
    }

    protected Materia(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.clave = in.readString();
        this.nombre = in.readString();
        this.activo = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Materia> CREATOR = new Parcelable.Creator<Materia>() {
        @Override
        public Materia createFromParcel(Parcel source) {
            return new Materia(source);
        }

        @Override
        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };
}
