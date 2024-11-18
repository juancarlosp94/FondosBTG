package com.btg.fondosbtg.controller;

import com.btg.fondosbtg.model.Usuario;
import com.btg.fondosbtg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        return usuarioService.eliminarUsuario(id);
    }

    @PostMapping("/{usuarioId}/suscribir/{fondoId}")
    public String suscribirAFondo(@PathVariable String usuarioId, @PathVariable String fondoId) {
        return usuarioService.suscribirAFondo(usuarioId, fondoId);
    }

    @DeleteMapping("/{usuarioId}/cancelar/{fondoId}")
    public String cancelarSuscripcion(@PathVariable String usuarioId, @PathVariable String fondoId) {
        return usuarioService.cancelarSuscripcion(usuarioId, fondoId);
    }


}
