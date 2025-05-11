package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.MerciDAO;
import com.example.terminal_marittimo.classiDAO.PolizzaDAO;
import com.example.terminal_marittimo.classiDTO.Merce;
import com.example.terminal_marittimo.classiDTO.Polizza;

import java.util.ArrayList;

@RestController
@RequestMapping("/gestionePolizze")
public class controllerFornitorePolizze 
{

    private final PolizzaDAO dao = new PolizzaDAO();
    private final MerciDAO daoMerci = new MerciDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam int idViaggio, @RequestParam int idFornitore, @RequestParam int idCliente, @RequestParam float peso, @RequestParam int idMerce, @RequestParam int ggFranchigia, @RequestParam float costoGg) {
        dao.inserisciPolizza(idViaggio, idFornitore, idCliente, idMerce, peso, ggFranchigia, costoGg);
        return "OK";
    }

    @GetMapping("/tutte")
    public ArrayList<Polizza> trovaTutte() {
        return dao.trovaTuttePolizze();
    }

    @GetMapping("/tutteID")
    public ArrayList<Polizza> trovaTutte(@RequestParam int id) {
        return dao.trovaTuttePolizzeDatoIDCliente(id);
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.eliminaPolizza(id);
        return "OK";
    }

    @GetMapping("/tutteMerci")
    public ArrayList<Merce> trovaTutteMerci() {
        return daoMerci.trovaTutteLeTipologie();
    }
}
