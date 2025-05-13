package com.example.terminal_marittimo.controller;

import com.example.terminal_marittimo.classiDAO.BuonoDAO;
import com.example.terminal_marittimo.classiDTO.Buono;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestioneBuoni")
public class controllerClienteBuono {

    private final BuonoDAO dao = new BuonoDAO();

    @GetMapping("/inserisci")
    public String inserisci(
            @RequestParam int idPolizza,
            @RequestParam int idCliente,
            @RequestParam double peso) 
    {
        dao.inserisciBuono(idPolizza, idCliente, peso);
        return "OK";
    }

    @GetMapping("/tutti")
    public List<Buono> trovaTutti() {
        return dao.trovaTuttiBuoni();
    }

    @GetMapping("/elimina")
    public String elimina(@RequestParam int id) {
        dao.eliminaBuono(id);
        return "OK";
    }
}
