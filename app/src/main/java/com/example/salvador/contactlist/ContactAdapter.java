package com.example.salvador.contactlist;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactsViewHolder> {

    //Arraylist de la clase contactos TODO: SOLO PARA PRUEBAS.
    private ArrayList<Contacts> contactos;


    public static class ContactsViewHolder extends RecyclerView.ViewHolder{

        //Variables donde se guardaran las visuales de mi layout.
        CardView cardview;
        TextView name;
        TextView number;
        //TODO: configurar boton despues
        Button favorite;




        public ContactsViewHolder(View itemView){
            super(itemView);

            //Recupero las referencias del layout
            cardview = itemView.findViewById(R.id.card_view_contact_list);
            name = itemView.findViewById(R.id.text_view_name);
            number = itemView.findViewById(R.id.text_view_number);

        }

    }

    public ContactAdapter(ArrayList<Contacts> contactos){this.contactos=contactos;}


    @NonNull
    @Override
    public ContactAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview, parent, false);
        return (new ContactsViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactsViewHolder holder, int position) {
        //aquí se agregan los listeners a los botones.
        holder.name.setText(contactos.get(position).getName());
        holder.number.setText(contactos.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }




}
