package com.example.terminal_marittimo.classiDTO;

public class Autista 
{
    private int id;
    private String nome;
    private String cognome;
    private String patente;
    private String password;

    public Autista(int id, String nome, String cognome, String patente, String password) 
    {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.patente = patente;
        this.password = password;
    }

    public int getId() {return this.id;}

    public String getNome() {return this.nome;}

    public String getCognome() {return this.cognome;}

    public String getPatente() {return this.patente;}

    public String getPassword() {return this.password;}

}
