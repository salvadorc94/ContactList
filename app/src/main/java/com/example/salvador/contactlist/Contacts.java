package com.example.salvador.contactlist;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable{


    //Datos del contacto.
    private String name;
    private String number;
    private String email;
    private String address;
    private boolean favorite;


    //Constructor
    public Contacts(String name, String number, String email, String address, boolean favorite){
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.favorite = favorite;
    }

    public Contacts(){}

    protected Contacts(Parcel in) {
        name = in.readString();
        number = in.readString();
        email = in.readString();
        address = in.readString();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        dest.writeString(email);
        dest.writeString(address);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }
}
