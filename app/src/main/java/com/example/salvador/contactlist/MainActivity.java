package com.example.salvador.contactlist;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //Saber si esta en landscape o normal
    public boolean landscape;

    RecyclerView rv;
    ContactAdapter adapter;
    LinearLayoutManager manager;

    Button home;
    Button fav;

    //Array para mientras
    ArrayList<Contacts> contacts,favcontacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pido permisos para hacer llamadas.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

        home = findViewById(R.id.btn_home);
        fav = findViewById(R.id.btn_fav);


        rv = findViewById(R.id.contact_list);
        rv.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        //SI EL ARRAY LIST ESTA LISTO
        contacts = new ArrayList<>();
        favcontacts = new ArrayList<>();

        contacts.add(new Contacts("SALVADOR","22883001", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("Fernando","79275152", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("ASDASDASD","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("grgrgrg","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("ttytyty","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("cvfg","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("juilo","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("feg","71647405", "ASDASD", "asdasd", false));


        if(findViewById(R.id.contact_detail) != null){
            //Si no es null es porque esta en landscape.
            landscape = true;
        }else{
            landscape = false;
        }

        adapter = new ContactAdapter(contacts, this, landscape) {
            @Override
            public void onClickCard(Contacts contacts, boolean landscape) {
                intents(contacts,landscape);
            }
        };
        rv.setAdapter(adapter);
    }

    //Metodos onClick del menu

    public void homebtn(View v){
        adapter.setFalse();
        home.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        fav.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        adapter = new ContactAdapter(contacts, v.getContext(), landscape) {
            @Override
            public void onClickCard(Contacts contacts, boolean landscape) {
                intents(contacts,landscape);

            }
        };
        rv.setAdapter(adapter);
    }
    public void favbtn(View v){
        adapter.setTrue();
        home.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        fav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        adapter = new ContactAdapter(favcontacts, v.getContext(), landscape) {
            @Override
            public void onClickCard(Contacts contacts, boolean landscape) {
                intents(contacts,landscape);

            }
        };
        rv.setAdapter(adapter);
    }

    public void addFavourite(Contacts contacts) {
        favcontacts.add(contacts);
    }

    public void eraseFavourite(String name) {
        int counter=0;
        for (Contacts contacts : favcontacts){
            if (contacts.getName()== name){
                break;
            }

            counter++;
        }

        favcontacts.remove(counter);

        if (adapter.isOnFavS()){
            adapter = new ContactAdapter(favcontacts, this, landscape) {
                @Override
                public void onClickCard(Contacts contacts, boolean landscape) {
                    intents(contacts,landscape);
                }
            };
            rv.setAdapter(adapter);

        }
    }


    public void intents(Contacts contacts, boolean landscape){
        if(landscape){
            //fragment manager


        }else{
            //Intent
            Intent intent = new Intent(getApplicationContext(),ContactDetail.class);
            intent.putExtra("Contacto",contacts);
            startActivity(intent);
        }

    }


}
