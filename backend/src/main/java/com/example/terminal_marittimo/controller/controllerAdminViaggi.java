package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.ViaggioDAO;
import com.example.terminal_marittimo.classiDTO.Viaggio;

import java.util.ArrayList;

@RestController
@RequestMapping("/gestioneViaggi")
public class controllerAdminViaggi {

    private final ViaggioDAO daoViaggio = new ViaggioDAO();

    @GetMapping("/inserisciViaggio")
    public String inserisciViaggio(@RequestParam int idNave, @RequestParam int idLinea, @RequestParam String dtPartenza,
            @RequestParam String dtArrivo) {
        daoViaggio.inserisciViaggio(idNave, idLinea, dtPartenza, dtArrivo);
        return "OK";
    }

    @GetMapping("/tuttiViaggi")
    public ArrayList<Viaggio> trovaTuttiViaggi() {
        return daoViaggio.trovaTuttiViaggi();
    }

    @GetMapping("/eliminaViaggio")
    public String eliminaViaggio(@RequestParam int id) {
        daoViaggio.eliminaViaggio(id);
        return "OK";
    }
}
