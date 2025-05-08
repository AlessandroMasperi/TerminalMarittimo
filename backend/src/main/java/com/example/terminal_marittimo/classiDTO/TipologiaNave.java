package com.example.terminal_marittimo.classiDTO;

public class TipologiaNave 
{
    private int id;
    private String nome;

    public TipologiaNave(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}

