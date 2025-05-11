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
        int id = autistaDAO.verificaCredenziali(username, password);
        if (id != -1) {
            return new Utente(id, username, "Autista");
        }
        id = clienteDAO.verificaCredenziali(username, password);
        if (id != -1) {
            return new Utente(id,  username, "Cliente");
        }
        id = fornitoreDAO.verificaCredenziali(username, password);
        if (id != -1) {
            return new Utente(id,  username, "Fornitore");
        }
        id = operatoreDAO.verificaCredenziali(username, password);
        if (id != -1) {
            return new Utente(id,  username, "Operatore");
        }
        id = adminDAO.verificaCredenziali(username, password);
        if (id != -1) {
            return new Utente(id, username, "Admin");
        }

        return new Utente(-1,"null","null");
    }
}

