package com.example.terminal_marittimo.classiDTO;

public class Polizza 
{
    private int id;
    private Viaggio viaggio;
    private Fornitore fornitore;
    private Cliente cliente;
    private float peso;
    private Merce merce;
    private int gg_franchigia;
    private float costo_gg;

    public Polizza(int id, Viaggio viaggio, Fornitore fornitore, Cliente cliente, float peso, Merce merce, int gg_franchigia, float costo_gg) {
        this.id = id;
        this.viaggio = viaggio;
        this.fornitore = fornitore;
        this.cliente = cliente;
        this.peso = peso;
        this.merce = merce;
        this.gg_franchigia = gg_franchigia;
        this.costo_gg = costo_gg;
    }

    public int getId() {
        return id;
    }
    public Viaggio getViaggio() {
        return viaggio;
    }
    public Fornitore getFornitore() {
        return fornitore;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public float getPeso() {
        return peso;
    }
    public Merce getMerce() {
        return merce;
    }
    public int getGg_franchigia() {
        return gg_franchigia;
    }
    public float getCosto_gg() {
        return costo_gg;
    }
}
