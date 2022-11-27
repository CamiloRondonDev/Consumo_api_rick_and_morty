package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.consumoapi.adaptadores.AdaptadorPersonajes;
import com.example.consumoapi.interfaces.InformacionApi;
import com.example.consumoapi.modelos.Informacionrespuestas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrimerosPersonajes extends AppCompatActivity {
    private static final String TAG ="mensaje error";
    private Retrofit retrofit;
    Button bottonNext;
    Button buttonatras;
    int contador = 1;
    int pagina ;

    Informacionrespuestas informacionrespuestas;
    AdaptadorPersonajes adaptadorPersonajes;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeros_personajes);
        buttonatras = findViewById(R.id.buttonatras);
        bottonNext = findViewById(R.id.buttonNext);
        informacionrespuestas = new Informacionrespuestas();
        retrofit = new Retrofit.Builder().baseUrl("https://rickandmortyapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
        info();

        buttonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastpage();
            }
        });

        bottonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage();

            }
        });
    }


    private  void info (){
            InformacionApi informacionApi = retrofit.create(InformacionApi.class);
            Call<Informacionrespuestas> call = informacionApi.obtenerListaNext(contador);
            call.enqueue(new Callback<Informacionrespuestas>() {

                @Override
                public void onResponse(Call<Informacionrespuestas> call, Response<Informacionrespuestas> response) {
                    if (response.isSuccessful()) {
                        informacionrespuestas = response.body();
                        pagina = Integer.parseInt(informacionrespuestas.getInfo().getPages());

                    } else {
                        Log.e(TAG, "onResponse:" + response.errorBody());
                    }
                }
                @Override
                public void onFailure(Call<Informacionrespuestas> call, Throwable t) {
                    Log.e(TAG , "onFailture"+ t.getMessage());
                }
            });
    }



    private  void nextPage (){
        if (contador <= pagina) {
            int contador01 = contador;
            contador++;
            InformacionApi informacionApi = retrofit.create(InformacionApi.class);
            Call<Informacionrespuestas> call = informacionApi.obtenerListaNext(contador01);
            call.enqueue(new Callback<Informacionrespuestas>() {

                @Override
                public void onResponse(Call<Informacionrespuestas> call, Response<Informacionrespuestas> response) {
                    if (response.isSuccessful()) {


                        informacionrespuestas = response.body();
                        adaptadorPersonajes = new AdaptadorPersonajes(PrimerosPersonajes.this , informacionrespuestas.getResults());
                        recyclerView = findViewById(R.id.recyclePersonajes);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PrimerosPersonajes.this));
                        recyclerView.setAdapter(adaptadorPersonajes);


                    } else {
                        Log.e(TAG, "onResponse:" + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Informacionrespuestas> call, Throwable t) {
                    Log.e(TAG , "onFailture"+ t.getMessage());
                }
            });
        }

    }

    private  void lastpage (){
        if (contador > 1 ){
            contador--;
            int contador02 = contador;
            InformacionApi informacionApi = retrofit.create(InformacionApi.class);
            Call<Informacionrespuestas> call = informacionApi.obtenerListaNext(contador02);
            call.enqueue(new Callback<Informacionrespuestas>() {

                @Override
                public void onResponse(Call<Informacionrespuestas> call, Response<Informacionrespuestas> response) {
                    if (response.isSuccessful()) {

                        informacionrespuestas = response.body();

                        adaptadorPersonajes = new AdaptadorPersonajes(PrimerosPersonajes.this , informacionrespuestas.getResults());
                        recyclerView = findViewById(R.id.recyclePersonajes);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PrimerosPersonajes.this));
                        recyclerView.setAdapter(adaptadorPersonajes);


                    } else {
                        Log.e(TAG, "onResponse:" + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Informacionrespuestas> call, Throwable t) {
                    Log.e(TAG , "onFailture"+ t.getMessage());
                }
            });
        }

    }


}