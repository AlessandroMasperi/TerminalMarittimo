package com.example.terminal_marittimo.classiDTO;

public class Consegna 
{
    private int id;
    private Buono buono;
    private String data;
    private Autista autista;
    private Camion camion;

    public Consegna(int id, Buono buono, String data, Autista autista, Camion camion) {
        this.id = id;
        this.buono = buono;
        this.data = data;
        this.autista = autista;
        this.camion = camion;
    }
    
    public int getId() {
        return id;
    }
    public Buono getBuono() {
        return buono;
    }
    public String getData() {
        return data;
    }
    public Autista getAutista() {
        return autista;
    }
    public Camion getCamion() {
        return camion;
    }
}
