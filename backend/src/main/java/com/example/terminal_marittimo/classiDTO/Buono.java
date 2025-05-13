package com.example.terminal_marittimo.classiDTO;

public class Buono 
{
    private int id;
    private String data;
    private Polizza polizza;
    private Cliente cliente;
    private float peso;
    private String codiceConferma;
    
    public Buono(int id, String data, Polizza polizza, Cliente cliente, float peso, String codiceConferma) {
        this.id = id;
        this.data = data;
        this.polizza = polizza;
        this.cliente = cliente;
        this.peso = peso;
        this.codiceConferma = codiceConferma;
    }
    
    public int getId() {
        return id;
    }
    public String getData() {
        return data;
    }
    public Polizza getPolizza() {
        return polizza;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public float getPeso() {
        return peso;
    }
    public String getCodiceConferma() {
        return codiceConferma;
    }
}
