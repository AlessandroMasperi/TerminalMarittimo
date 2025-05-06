package com.example.terminal_marittimo.controller;

import com.example.terminal_marittimo.classiDAO.*;
import com.example.terminal_marittimo.classiDTO.Utente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class controllerLogin 
{
    private final AutistaDAO autistaDAO = new AutistaDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FornitoreDAO fornitoreDAO = new FornitoreDAO();
    private final OperatoreDAO operatoreDAO = new OperatoreDAO();
    private final AdminDAO adminDAO = new AdminDAO();

    @GetMapping
    public Utente login(@RequestParam String username, @RequestParam String password) {
        if (autistaDAO.verificaCredenziali(username, password)) {
            return new Utente(username, "Autista");
        }
        if (clienteDAO.verificaCredenziali(username, password)) {
            return new Utente(username, "Cliente");
        }
        if (fornitoreDAO.verificaCredenziali(username, password)) {
            return new Utente(username, "Fornitore");
        }
        if (operatoreDAO.verificaCredenziali(username, password)) {
            return new Utente(username, "Operatore");
        }
        if (adminDAO.verificaCredenziali(username, password)) {
            return new Utente(username, "Admin");
        }

        return new Utente("null", "null");
    }
}

