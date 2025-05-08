package com.example.terminal_marittimo.classiDTO;

public class Porto {
    private int id;
    private String porto;
    private String nazione;

    public Porto(int id, String porto, String nazione) {
        this.id = id;
        this.porto = porto;
        this.nazione = nazione;
    }

    public int getId() {
        return this.id;
    }

    public String getPorto() {
        return this.porto;
    }

    public String getNazione() {
        return this.nazione;
    }
}
