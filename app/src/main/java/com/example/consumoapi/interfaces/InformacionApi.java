package com.example.consumoapi.interfaces;

import com.example.consumoapi.modelos.Informacionrespuestas;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InformacionApi {
    @GET("api/character")
    Call<Informacionrespuestas> obtenerLista();


    @GET("api/character")
    Call<Informacionrespuestas> obtenerListaNext(@Query("page") int page);
}