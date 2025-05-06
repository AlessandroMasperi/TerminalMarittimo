package com.example.terminal_marittimo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.terminal_marittimo.classiDAO.OperatoreDAO;
import com.example.terminal_marittimo.classiDTO.Operatore;

import java.util.List;

@RestController
@RequestMapping("/gestioneOperatori")
public class controllerAdminOperatore {

    private final OperatoreDAO dao = new OperatoreDAO();

    @GetMapping("/inserisci")
    public String inserisci(@RequestParam String username, @RequestParam String password) {
        dao.inserisciOperatore(username, password);
        return "OK";
    }

    @GetMapping("/tutti")
    public List<Operatore> trovaTutti() {
        return dao.trovaTuttiOperatori();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.eliminaOperatore(id);
        return "OK";
    }
}
