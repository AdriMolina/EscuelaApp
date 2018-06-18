package com.example.adi.escuelaapp.Entidades;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adi on 16/06/2018.
 */

public class Categoria implements Parcelable {
    private Integer id;
    private String nombre;
    private boolean activo;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(int id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dest.writeString(this.nombre);
        dest.writeByte(this.activo ? (byte) 1 : (byte) 0);
    }

    protected Categoria(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nombre = in.readString();
        this.activo = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel source) {
            return new Categoria(source);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
}
