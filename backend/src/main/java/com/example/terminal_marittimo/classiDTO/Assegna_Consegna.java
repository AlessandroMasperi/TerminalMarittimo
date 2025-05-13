package com.example.terminal_marittimo.classiDTO;

public class Assegna_Consegna 
{
    private int id;
    private Buono buono;
    private Autista autista;
    private Cliente cliente;

    public Assegna_Consegna(int id, Buono buono, Autista autista, Cliente cliente) {
        this.id = id;
        this.buono = buono;
        this.autista = autista;
        this.cliente = cliente;
    }
    
    public int getId() {
        return id;
    }
    public Buono getBuono() {
        return buono;
    }
    public Autista getAutista() {
        return autista;
    }
    public Cliente getCliente() {
        return cliente;
    }
}
