package com.example.terminal_marittimo.classiDTO;

public class Viaggio 
{
    private int id;
    private Nave nave;
    private Linea linea;
    private String dt_partenza;
    private String dt_arrivo;

    public Viaggio(int id, Nave nave, Linea linea, String dt_partenza, String dt_arrivo) {
        this.id = id;
        this.nave = nave;
        this.linea = linea;
        this.dt_partenza = dt_partenza;
        this.dt_arrivo = dt_arrivo;
    }

    public int getId() {
        return this.id;
    }

    public Nave getNave() {
        return this.nave;
    }

    public Linea getLinea() {
        return this.linea;
    }

    public String getDt_partenza() {
        return this.dt_partenza;
    }

    public String getDt_arrivo() {
        return this.dt_arrivo;
    }
}
