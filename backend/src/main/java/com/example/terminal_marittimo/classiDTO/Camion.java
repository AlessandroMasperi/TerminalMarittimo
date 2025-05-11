package com.example.terminal_marittimo.classiDTO;

public class Camion 
{

    private String targa;
    private String marca;
    private String modello;
    private String colore;
    
    public Camion(String targa, String marca, String modello, String colore) {
        this.targa = targa;
        this.marca = marca;
        this.modello = modello;
        this.colore = colore;
    }

    public String getTarga() {
        return targa;
    }
    public String getMarca() {
        return marca;
    }
    public String getModello() {
        return modello;
    }
    public String getColore() {
        return colore;
    }
}
