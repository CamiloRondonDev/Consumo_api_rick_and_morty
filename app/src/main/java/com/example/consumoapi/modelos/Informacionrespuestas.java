package com.example.consumoapi.modelos;

import java.util.ArrayList;

public class Informacionrespuestas {

    ArrayList<Personajes> results;

    Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public ArrayList<Personajes> getResults() {
        return results;
    }

    public void setResults(ArrayList<Personajes> results) {
        this.results = results;
    }
}
