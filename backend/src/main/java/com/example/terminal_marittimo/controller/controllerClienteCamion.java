package com.example.terminal_marittimo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

import com.example.terminal_marittimo.classiDAO.CamionDAO;
import com.example.terminal_marittimo.classiDTO.Camion;

@RestController
@RequestMapping("/gestioneCamion")
public class controllerClienteCamion 
{

    CamionDAO dao = new CamionDAO();

    @GetMapping("/inserisci")
    public String inserisciCamion(@RequestParam String targa, @RequestParam String marca, @RequestParam String modello, @RequestParam String colore) {
        dao.inserisciCamion(targa, marca, modello, colore);
        return "OK";
    }

    @GetMapping("/tutti")
    public ArrayList<Camion> tuttiICamion() {
        return dao.trovaTuttiICamion();
    }

    @GetMapping("/elimina")
    public String eliminaCamion(@RequestParam String targa) {
        dao.eliminaCamion(targa);
        return "OK";
    }
}
