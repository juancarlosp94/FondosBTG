package com.btg.fondosbtg.service;

import com.btg.fondosbtg.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        if (usuario.getEmail() != null) {
            // Lógica para enviar email
            System.out.println("Enviando email a " + usuario.getEmail() + ": " + mensaje);
        } else if (usuario.getTelefono() != null) {
            // Lógica para enviar SMS
            System.out.println("Enviando SMS a " + usuario.getTelefono() + ": " + mensaje);
        }
    }
}
