package com.example.adi.escuelaapp.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adi on 16/06/2018.
 */

public class Credencial implements Parcelable{
    private String nick;
    private String pass;

    public Credencial() {
    }

    public Credencial(String nick, String pass) {
        this.nick = nick;
        this.pass = pass;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nick);
        dest.writeString(this.pass);
    }

    protected Credencial(Parcel in) {
        this.nick = in.readString();
        this.pass = in.readString();
    }

    public static final Parcelable.Creator<Credencial> CREATOR = new Parcelable.Creator<Credencial>() {
        @Override
        public Credencial createFromParcel(Parcel source) {
            return new Credencial(source);
        }

        @Override
        public Credencial[] newArray(int size) {
            return new Credencial[size];
        }
    };
}
