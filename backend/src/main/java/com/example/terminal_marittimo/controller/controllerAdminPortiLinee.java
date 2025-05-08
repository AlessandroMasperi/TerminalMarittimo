package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.LineaDAO;
import com.example.terminal_marittimo.classiDAO.PortoDAO;
import com.example.terminal_marittimo.classiDTO.Linea;
import com.example.terminal_marittimo.classiDTO.Porto;

import java.util.ArrayList;

@RestController
@RequestMapping("/gestionePorti")
public class controllerAdminPortiLinee {

    private final PortoDAO daoPorto = new PortoDAO();
    private final LineaDAO daoLinea = new LineaDAO();

    @GetMapping("/inserisciPorto")
    public String inserisciPorto(@RequestParam String porto, @RequestParam String nazione) 
    {
        daoPorto.inserisciPorto(porto, nazione);
        return "OK";
    }

    @GetMapping("/tuttiPorto")
    public ArrayList<Porto> trovaTuttiPorto() 
    {
        return daoPorto.trovaTuttiPorti();
    }

    @GetMapping("/eliminaPorto")
    public String eliminaPorto(@RequestParam int id) 
    {
        if(id == 1)
            return "No";

        daoPorto.eliminaPorto(id);
        return "OK";
    }

    @GetMapping("/inserisciLinea")
    public String inserisciLinea(@RequestParam String nome, @RequestParam int idPartenza, @RequestParam int idDestinazione) {
        daoLinea.inserisciLinea(nome, idPartenza, idDestinazione);
        return "OK";
    }

    @GetMapping("/tutteLinea")
    public ArrayList<Linea> trovaTutteLinea() {
        return daoLinea.trovaTutteLinee();
    }

    @GetMapping("/eliminaLinea")
    public String eliminaLinea(@RequestParam int id) {
        daoLinea.eliminaLinea(id);
        return "OK";
    }
}
