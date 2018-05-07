package com.example.salvador.contactlist;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable{


    //Datos del contacto.
    private String name;
    private String number;
    private boolean favorite;


    //Constructor
    public Contacts(String name, String number, boolean favorite){
        this.name = name;
        this.number = number;
        this.favorite = favorite;
    }

    public Contacts(){}

    protected Contacts(Parcel in) {
        name = in.readString();
        number = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }
}
