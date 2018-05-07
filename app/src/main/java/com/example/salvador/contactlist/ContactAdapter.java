package com.example.salvador.contactlist;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public abstract class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {

    //Arraylist de la clase contactos TODO: ARRAY SOLO PARA PRUEBAS.
    private ArrayList<Contacts> contactos;
    private boolean landscape;
    private static boolean fav = false;
    private Context context;


    public static class ContactsViewHolder extends RecyclerView.ViewHolder{

        //Variables donde se guardaran las visuales de mi layout.
        CardView cardview;
        TextView name;
        TextView number;
        Button favorite;




        public ContactsViewHolder(View itemView){
            super(itemView);

            //Recupero las referencias del layout
            cardview = itemView.findViewById(R.id.card_view_contact_list);
            name = itemView.findViewById(R.id.text_view_name);
            number = itemView.findViewById(R.id.text_view_number);
            favorite = itemView.findViewById(R.id.btn_fav);


        }

    }

    public ContactAdapter(ArrayList<Contacts> contactos, Context context, boolean landscape){this.contactos=contactos; this.context = context; this.landscape = landscape;}


    @NonNull
    @Override
    public ContactAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview, parent, false);
        return (new ContactsViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactsViewHolder holder, final int position) {
        //aquí se agregan los listeners a los botones.
        holder.name.setText(contactos.get(position).getName());
        holder.number.setText(contactos.get(position).getNumber());


        //Conocer si es favorito y así modificar el botón.
        if (contactos.get(position).isFavorite()) {
            holder.favorite.setText(R.string.btn_full_star);
        } else {
            holder.favorite.setText(R.string.btn_empty_star);
        }

        //Listener de cada boton favorito de la lista.
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFav(position)) {
                    holder.favorite.setText(R.string.btn_full_star);
                    ((MainActivity) context).addFavourite(contactos.get(position));

                } else {
                    holder.favorite.setText(R.string.btn_empty_star);
                    ((MainActivity) context).eraseFavourite(contactos.get(position).getName());
                }
            }
        });

        //Listener del cardview para saber los details.
        holder.cardview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onClickCard(contactos.get(position),landscape);
            }
        });





    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }
    //Metodos para saber si el adapter muestra falso o verdadero y si el contacto esta en favoritos.
    public void setTrue() {
        fav = true;
    }
    public void setFalse() {
        fav = false;
    }
    public boolean isOnFavS() {
        return fav;
    }

    public boolean isFav(int position){
        contactos.get(position).setFavorite(!contactos.get(position).isFavorite());
        return contactos.get(position).isFavorite();
    }

    public abstract void onClickCard(Contacts contacts, boolean landscape);


}
