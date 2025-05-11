package com.example.terminal_marittimo.controller;

import com.example.terminal_marittimo.classiDAO.Richiesta_buonoDAO;
import com.example.terminal_marittimo.classiDTO.Richiesta_buono;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/gestioneRichiesteBuono")
public class controllerClienteRichiestaBuono {

    private final Richiesta_buonoDAO dao = new Richiesta_buonoDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam int idCliente, @RequestParam int idPolizza, @RequestParam float peso) {
        dao.inserisciRichiesta(idCliente, idPolizza, peso);
        return "Richiesta inserita con successo.";
    }

    @GetMapping("/tutte")
    public ArrayList<Richiesta_buono> trovaTutte() {
        return dao.trovaTutteRichieste();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.eliminaRichiesta(id);
        return "Richiesta eliminata con successo.";
    }

    @GetMapping("/approva")
    public String approva(@RequestParam int id) {
        dao.approvaRichiesta(id);
        return "Richiesta approvata";
    }
}
