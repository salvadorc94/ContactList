package com.example.salvador.contactlist;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContactDetail extends AppCompatActivity {

    TextView nombre;
    TextView telefono;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        nombre = findViewById(R.id.text_view_name);
        telefono = findViewById(R.id.text_view_number);

        Contacts contacts = (Contacts) getIntent().getExtras().getParcelable("Contacto");

        nombre.setText(contacts.getName());
        telefono.setText(contacts.getNumber());

    }


    public void llamar(View view){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        //asdasdasdasdasdasdasdsad
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+telefono.getText().toString()));
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }

    public void share(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Nombre: "+nombre.getText().toString()+"\nTelefono: "+telefono.getText().toString());

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(intent,String.valueOf(R.string.share)));
        }
    }

}

