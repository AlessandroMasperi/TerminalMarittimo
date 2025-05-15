package com.example.terminal_marittimo.controller;

import com.example.terminal_marittimo.classiDAO.ConsegnaDAO;
import com.example.terminal_marittimo.classiDTO.Consegna;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/gestioneConsegne")
public class controllerAutistaConsegna {

    private final ConsegnaDAO dao = new ConsegnaDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam int idBuono,@RequestParam int idAutista,@RequestParam String targa) {
        dao.inserisciConsegna(idBuono, idAutista, targa);
        return "OK";
    }

    @GetMapping("/tutte")
    public ArrayList<Consegna> trovaTutte() {
        return dao.trovaTutteConsegne();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.eliminaConsegna(id);
        return "OK";
    }
}
