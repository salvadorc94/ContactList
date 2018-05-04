package com.example.salvador.contactlist;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


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

        home = findViewById(R.id.btn_home);
        fav = findViewById(R.id.btn_fav);

        rv = findViewById(R.id.contact_list);
        rv.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        //SI EL ARRAY LIST ESTA LISTO
        contacts = new ArrayList<>();
        favcontacts = new ArrayList<>();

        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));

        adapter = new ContactAdapter(contacts, this);
        rv.setAdapter(adapter);
    }

    //Metodos onClick del menu

    public void homebtn(View v){
        adapter.setFalse();
        home.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        fav.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        adapter = new ContactAdapter(contacts,v.getContext());
        rv.setAdapter(adapter);
    }
    public void favbtn(View v){
        adapter.setTrue();
        home.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        fav.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        adapter = new ContactAdapter(favcontacts,v.getContext());
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
            adapter = new ContactAdapter(favcontacts,this);
            rv.setAdapter(adapter);

        }
    }
}
