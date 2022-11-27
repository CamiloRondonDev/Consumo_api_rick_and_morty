package com.example.consumoapi.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.consumoapi.R;
import com.example.consumoapi.modelos.Informacionrespuestas;
import com.example.consumoapi.modelos.Personajes;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonajes extends RecyclerView.Adapter<AdaptadorPersonajes.PersonajesViewHolder> {

    ArrayList<Personajes> personajes;
    Context context;


    public AdaptadorPersonajes(Context context, ArrayList<Personajes> personajes) {
        this.personajes = personajes;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonajesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, null, false);
        return new PersonajesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajesViewHolder holder, int position) {

        holder.nameR.setText(personajes.get(position).getName());
        holder.statusR.setText(personajes.get(position).getStatus());
        holder.speciesR.setText(personajes.get(position).getSpecies());
        holder.typeR.setText(personajes.get(position).getType());
        holder.genderR.setText(personajes.get(position).getGender());
        holder.createdR.setText(personajes.get(position).getCreated());
        Glide.with(context)
                .load(personajes.get(position).getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageR);

    }

    @Override
    public int getItemCount() {
        return personajes.size() ;
    }

    public class PersonajesViewHolder extends RecyclerView.ViewHolder {

        TextView nameR;
        TextView statusR;
        TextView speciesR;
        TextView typeR;
        TextView genderR;
        ImageView imageR;
        TextView createdR;

        public PersonajesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameR = itemView.findViewById(R.id.nameRecycle);
            statusR = itemView.findViewById(R.id.statusRecycle);
            speciesR = itemView.findViewById(R.id.speciesRecycle);
            typeR = itemView.findViewById(R.id.typeRecycle);
            genderR = itemView.findViewById(R.id.genderRecycle);
            imageR = itemView.findViewById(R.id.imageRecycle);
            createdR = itemView.findViewById(R.id.createdRecycle);
        }
    }
}
