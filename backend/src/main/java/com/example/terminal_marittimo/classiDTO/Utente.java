package com.example.terminal_marittimo.classiDTO;

public class Utente 
{
    private String username;
    private String ruolo;

    public Utente(String username, String ruolo) {
        this.username = username;
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRuolo() {
        return this.ruolo;
    }
}

