package com.example.terminal_marittimo.classiDTO;

public class Utente 
{
    private int id;
    private String username;
    private String ruolo;

    public Utente(int id, String username, String ruolo) 
    {
        this.id = id;
        this.username = username;
        this.ruolo = ruolo;
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRuolo() {
        return this.ruolo;
    }
}

