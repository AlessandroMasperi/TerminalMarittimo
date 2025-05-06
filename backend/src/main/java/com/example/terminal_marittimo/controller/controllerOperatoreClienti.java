package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.ClienteDAO;
import com.example.terminal_marittimo.classiDTO.Cliente;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/gestioneClienti")
public class controllerOperatoreClienti 
{
    private final ClienteDAO dao = new ClienteDAO();

    @GetMapping("/inserisci")
    public String inserisciCliente(@RequestParam String nome, @RequestParam String cognome, @RequestParam String indirizzo, @RequestParam String telefono, @RequestParam String email, @RequestParam String nomeAzienda, @RequestParam String password) throws SQLException
    {
        dao.inserisciCliente(nome, cognome, indirizzo, telefono, email, nomeAzienda, password);
        return "OK";
    }

    @GetMapping("/tutti")
    public ArrayList<Cliente> trovaTutti() 
    {
        return dao.trovaTuttiClienti();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) 
    {
        dao.eliminaCliente(id);
        return "OK";
    }
}

