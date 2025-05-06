package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.FornitoreDAO;
import com.example.terminal_marittimo.classiDTO.Fornitore;

import java.sql.SQLException;
import java.util.ArrayList;;

@RestController
@RequestMapping("/gestioneFornitori")
public class controllerOperatoreFornitori 
{
    private final FornitoreDAO dao = new FornitoreDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam String nome, @RequestParam String cognome, @RequestParam String indirizzo, @RequestParam String telefono, @RequestParam String email, @RequestParam String nomeAzienda, @RequestParam String password) throws SQLException
    {
        dao.inserisciFornitore(nome, cognome, indirizzo, telefono, email, nomeAzienda, password);
        return "OK";
    }

    @GetMapping("/tutti")
    public ArrayList<Fornitore> trovaTutti() 
    {
        return dao.trovaTuttiFornitori();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) 
    {
        dao.eliminaFornitore(id);
        return "OK";
    }
}

