package com.btg.fondosbtg.controller;

import com.btg.fondosbtg.model.Fondo;
import com.btg.fondosbtg.service.FondoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fondos")
public class FondoController {

    @Autowired
    private FondoService fondoService;


    @PostMapping("/crear")
    public Fondo crearFondo(@RequestBody Fondo fondo) {
        return fondoService.crearFondo(fondo);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarFondo(@PathVariable String id) {
        return fondoService.eliminarFondo(id);
    }
}
