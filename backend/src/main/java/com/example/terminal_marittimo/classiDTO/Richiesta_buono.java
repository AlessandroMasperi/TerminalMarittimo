package com.example.terminal_marittimo.classiDTO;

public class Richiesta_buono 
{
    private int id;
    private Cliente c;
    private Polizza p;
    private float peso;
    
    public Richiesta_buono(int id, Cliente c, Polizza p, float peso) {
        this.id = id;
        this.c = c;
        this.p = p;
        this.peso = peso;
    }
    
    public int getId() {
        return id;
    }
    public Cliente getC() {
        return c;
    }
    public Polizza getP() {
        return p;
    }
    public float getPeso() {
        return peso;
    }
}
