package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.terminal_marittimo.classiDAO.AutistaDAO;
import com.example.terminal_marittimo.classiDTO.Autista;

import java.util.List;

@RestController
@RequestMapping("/gestioneAutisti")
public class controllerClienteAutisti
{
    private final AutistaDAO dao = new AutistaDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam String nome, @RequestParam String cognome, @RequestParam String patente, @RequestParam String password) 
    {
        dao.inserisciAutista(nome, cognome, patente, password);
        return "OK";
    }

    @GetMapping("/tutti")
    public List<Autista> trovaTutti() 
    {
        return dao.trovaTuttiAutisti();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) 
    {
        dao.eliminaAutista(id);
        return "OK";
    }
}

