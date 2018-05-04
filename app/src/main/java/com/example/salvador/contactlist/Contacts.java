package com.example.salvador.contactlist;

public class Contacts {


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
}
