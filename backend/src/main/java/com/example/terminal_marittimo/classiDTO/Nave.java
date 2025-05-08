package com.example.terminal_marittimo.classiDTO;

public class Nave
{
    private int id;
    private String nome;
    private int annoProduzione;
    private TipologiaNave tipologia;

    public Nave(int id, String nome, int annoProduzione, TipologiaNave tipologia) {
        this.id = id;
        this.nome = nome;
        this.annoProduzione = annoProduzione;
        this.tipologia = tipologia;
    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAnnoProduzione() {
        return this.annoProduzione;
    }

    public String getTipologia() 
    {
        return this.tipologia.getNome();
    }
}
