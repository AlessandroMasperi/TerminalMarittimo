package com.example.terminal_marittimo.classiDTO;

public class Linea 
{
    private int id;
    private String nome;
    private Porto porto_partenza;
    private Porto porto_destinazione;

    public Linea(int id, String nome, Porto porto_partenza, Porto porto_destinazione) 
    {
        this.id = id;
        this.nome = nome;
        this.porto_partenza = porto_partenza;
        this.porto_destinazione = porto_destinazione;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Porto getPorto_partenza() {
        return this.porto_partenza;
    }

    public Porto getPorto_destinazione() {
        return this.porto_destinazione;
    }
}
