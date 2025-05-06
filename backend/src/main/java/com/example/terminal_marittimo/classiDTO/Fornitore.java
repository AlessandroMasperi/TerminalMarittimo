package com.example.terminal_marittimo.classiDTO;

public class Fornitore {
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String email;
    private String nomeAzienda;
    private String password;

    public Fornitore(int id, String nome, String cognome, String indirizzo, String telefono, String email, String nomeAzienda, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.email = email;
        this.nomeAzienda = nomeAzienda;
        this.password = password;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getIndirizzo() { return indirizzo; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getNomeAzienda() { return nomeAzienda; }
    public String getPassword() { return password; }
}

