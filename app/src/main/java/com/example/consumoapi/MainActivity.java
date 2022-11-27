package com.example.consumoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.consumoapi.adaptadores.AdaptadorPersonajes;
import com.example.consumoapi.interfaces.InformacionApi;
import com.example.consumoapi.modelos.Informacionrespuestas;
import com.example.consumoapi.modelos.Personajes;

import java.text.BreakIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="mensaje error";
    TextView name;
    TextView status;
    TextView species;
    TextView type;
    TextView gender;
    ImageView image;
    TextView created;
    TextView origin;
    Button button;
    Button siguiente;
    String vacio = "";
    Button buttonirRecycle;
    EditText buscar;
    EditText nextEdit;
    Informacionrespuestas informacionrespuestas;
    private Retrofit retrofit;
    String numero;
    Personajes personajes;
    RecyclerView recyclerView;
    AdaptadorPersonajes adaptadorPersonajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        species = findViewById(R.id.species);
        type = findViewById(R.id.type);
        gender = findViewById(R.id.gender);
        image = findViewById(R.id.image);
        created = findViewById(R.id.created);
        button = findViewById(R.id.button);
        buscar = findViewById(R.id.buscar);
        origin = findViewById(R.id.origin);
        nextEdit = findViewById(R.id.next);
        siguiente = findViewById(R.id.siguiente);
        buttonirRecycle = findViewById(R.id.buttonirRecycle);
        informacionrespuestas = new Informacionrespuestas();
        personajes = new Personajes();


      siguiente.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            //  String contador = "1";
             // nextPage(Integer.parseInt(contador) );

          }
      });


        buttonirRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrimerosPersonajes.class);
                startActivity(intent);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !buscar.getText().toString().equals(vacio) ){
                    if (Integer.parseInt(buscar.getText().toString()) <= 19 ){

                        numero = String.valueOf(Integer.parseInt(buscar.getText().toString()));
                        findConsulta();

                    }else {
                        Toast.makeText(MainActivity.this, "solo 19 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "ingrese un numero", Toast.LENGTH_SHORT).show();
                }
            }
        });

        retrofit = new Retrofit.Builder().baseUrl("https://rickandmortyapi.com/").addConverterFactory(GsonConverterFactory.create()).build();
    }

 /*   private  void nextPage(int  next){
        InformacionApi informacionApi = retrofit.create(InformacionApi.class);
        Call<Informacionrespuestas> call = informacionApi.obtenerListaNext(next);
        call.enqueue(new Callback<Informacionrespuestas>() {

            @Override
            public void onResponse(Call<Informacionrespuestas> call, Response<Informacionrespuestas> response) {
                if (response.isSuccessful()) {

                    informacionrespuestas = response.body();
                    adaptadorPersonajes = new AdaptadorPersonajes(MainActivity.this , informacionrespuestas.getResults());
                    recyclerView = findViewById(R.id.recyclePersonajes);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
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
*/

    // recibe informacion y lo mando al adaptador para que lo muestre en el recycler
    private  void find(){
         InformacionApi informacionApi = retrofit.create(InformacionApi.class);
        Call<Informacionrespuestas> call = informacionApi.obtenerLista();
        call.enqueue(new Callback<Informacionrespuestas>() {
            @Override
            public void onResponse(Call<Informacionrespuestas> call, Response<Informacionrespuestas> response) {

                if (response.isSuccessful()) {

                    informacionrespuestas = response.body();

                      /*  adaptadorPersonajes = new AdaptadorPersonajes(MainActivity.this , informacionrespuestas.getResults());
                        recyclerView = findViewById(R.id.recyclePersonajes);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(adaptadorPersonajes);
*/
                } else {

                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Informacionrespuestas> call, Throwable t) {
                Log.e(TAG , "onFailture"+ t.getMessage());
                System.out.println("onFailture"+ t.getMessage());

            }
        });
    }



    // recibe informacion para y mustrar por medio del id en el text View
    private  void findConsulta (){
        InformacionApi informacionApi = retrofit.create(InformacionApi.class);
        Call<Informacionrespuestas> call = informacionApi.obtenerLista();
        call.enqueue(new Callback<Informacionrespuestas>() {
            @Override
            public void onResponse(Call<Informacionrespuestas> call, Response<Informacionrespuestas> response) {

                if (response.isSuccessful()) {

                    informacionrespuestas = response.body();

                        String URL_IMG = "https://rickandmortyapi.com/api/character/avatar/" + informacionrespuestas.getResults().get(Integer.parseInt(numero)).getId() +".jpeg";
                        name.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getName());
                        status.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getStatus());
                        species.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getSpecies());
                        type.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getType());
                        gender.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getGender());
                        created.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getCreated());
                        origin.setText(informacionrespuestas.getResults().get(Integer.parseInt(numero)).getOrigin().getName());
                        //origin.setText(informacionrespuestas.getInfo().getNext());
                        Glide.with(getApplication()).load(URL_IMG).into(image);


                } else {

                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Informacionrespuestas> call, Throwable t) {
                Log.e(TAG , "onFailture"+ t.getMessage());
                System.out.println("onFailture"+ t.getMessage());

            }
        });
    }

  /*  private void recicler() {
        setContentView(R.layout.primeros_personajes);
        find();
}*/
}
