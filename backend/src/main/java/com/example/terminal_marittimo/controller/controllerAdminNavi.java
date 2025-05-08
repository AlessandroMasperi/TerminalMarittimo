package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.terminal_marittimo.classiDAO.NaveDAO;
import com.example.terminal_marittimo.classiDAO.TipologiaNaveDAO;
import com.example.terminal_marittimo.classiDTO.Nave;
import com.example.terminal_marittimo.classiDTO.TipologiaNave;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gestioneNavi")
public class controllerAdminNavi {

    private final NaveDAO dao = new NaveDAO();
    private final TipologiaNaveDAO tipologiaDAO = new TipologiaNaveDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam String nome, @RequestParam int annoProduzione, @RequestParam int idTipologia) 
    {
        dao.inserisciNave(nome, annoProduzione, idTipologia);
        return "OK";
    }

    @GetMapping("/tutte")
    public List<Nave> trovaTutte() 
    {
        return dao.trovaTutteLeNavi();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) 
    {
        dao.eliminaNave(id);
        return "OK";
    }

    @GetMapping("/tipologie")
    public ArrayList<TipologiaNave> getTipologie() 
    {
        return tipologiaDAO.trovaTutteLeTipologie();
    }
}
