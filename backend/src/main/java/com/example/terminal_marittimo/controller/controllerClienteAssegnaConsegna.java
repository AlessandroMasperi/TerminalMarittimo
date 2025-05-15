package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.Assegna_consegnaDAO;
import com.example.terminal_marittimo.classiDTO.Assegna_Consegna;

import java.util.ArrayList;

@RestController
@RequestMapping("/gestioneAssegnazioni")
public class controllerClienteAssegnaConsegna {

    private final Assegna_consegnaDAO dao = new Assegna_consegnaDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam int idBuono,@RequestParam int idAutista,@RequestParam int idCliente) {
        dao.inserisciAssegnazione(idBuono, idAutista, idCliente);
        return "OK";
    }

    @GetMapping("/tutti")
    public ArrayList<Assegna_Consegna> trovaTutti() {
        return dao.trovaTutteAssegnazioni();
    }

    @GetMapping("/tuttiID")
    public ArrayList<Assegna_Consegna> trovaTuttiID(@RequestParam int id) {
        return dao.trovaTutteAssegnazioniByID(id);
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.eliminaAssegnazione(id);
        return "OK";
    }
}
