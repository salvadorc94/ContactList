package com.example.salvador.contactlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView rv;
    ContactAdapter adapter;
    //para mientras
    ArrayList<Contacts> contacts;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rv = findViewById(R.id.contact_list);
        rv.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        //SI EL ARRAY LIST ESTA LISTO
        contacts = new ArrayList<>();
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));
        contacts.add(new Contacts("SALVADOR","71647405", "ASDASD", "asdasd", false));

        adapter = new ContactAdapter(contacts);
        rv.setAdapter(adapter);
    }



}
