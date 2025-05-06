package com.example.terminal_marittimo.classiDTO;

public class Operatore {
    private int id;
    private String username;
    private String password;

    public Operatore(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
