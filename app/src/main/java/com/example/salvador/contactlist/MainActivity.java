package com.example.salvador.contactlist;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //Saber si esta en landscape o normal
    public boolean landscape;

    RecyclerView rv;
    ContactAdapter adapter;
    LinearLayoutManager manager;
    EditText search;

    Button home;
    Button fav;

    //Array para mientras
    ArrayList<Contacts> contacts,favcontacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Evitar que la app enfoque el EditText al inicio
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Iniciar arrays
        contacts = new ArrayList<>();
        favcontacts = new ArrayList<>();

        //Pido permisos para hacer llamadas y leer contactos.
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);


        home = findViewById(R.id.btn_home);
        fav = findViewById(R.id.btn_fav);


        rv = findViewById(R.id.contact_list);
        rv.setHasFixedSize(true);

        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);


        if(findViewById(R.id.contact_detail) != null){
            //Si no es null es porque esta en landscape.
            landscape = true;
        }else{
            landscape = false;
        }


        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestStoragePermission();
        }else{
            retrieveContact();
            adapter = new ContactAdapter(contacts, this, landscape) {
                @Override
                public void onClickCard(Contacts contacts, boolean landscape) {
                    intents(contacts,landscape);
                }
            };
            rv.setAdapter(adapter);
        }


        search = (EditText) findViewById(R.id.buscar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                searchedForContacts(s.toString());
            }
        });

        rv.setAdapter(adapter);

    }

    //Metodos para el search bar
    private void searchedForContacts(String text){
        ArrayList<Contacts> filteredList = new ArrayList<>();
        for(Contacts item : contacts){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }
    private void searchedForFavs(String text){
        ArrayList<Contacts> filteredList = new ArrayList<>();
        for(Contacts item : favcontacts){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
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
        EditText filter = (EditText) findViewById(R.id.buscar);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                searchedForContacts(editable.toString());
            }
        });


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
        EditText filter = (EditText) findViewById(R.id.buscar);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                searchedForFavs(editable.toString());
            }
        });
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

    //Recuperar los contactos.
    public void retrieveContact(){
            String help = "";

            Cursor contactos = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
            while (contactos.moveToNext()) {
                String name = contactos.getString(contactos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = contactos.getString(contactos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));



                if(!(help.equals(name))){
                    contacts.add(new Contacts(name, number, false));
                }

                help = name;


                //contacts.add(new Contacts(name, number, false));
            }
            contactos.close();
    }


    //AL DAR CLICK EN LOS CARDVIEW
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


    private void requestStoragePermission(){
           new AlertDialog.Builder(this)
                    .setTitle(R.string.permiso_necesario)
                    .setMessage(R.string.permiso_mensaje)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},23);
                        }
                    }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 23)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                retrieveContact();
                adapter = new ContactAdapter(contacts, this, landscape) {
                    @Override
                    public void onClickCard(Contacts contacts, boolean landscape) {
                        intents(contacts,landscape);
                    }
                };
                rv.setAdapter(adapter);
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
