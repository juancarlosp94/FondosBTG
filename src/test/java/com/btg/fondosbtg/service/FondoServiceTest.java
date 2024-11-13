package com.btg.fondosbtg.service;

import com.btg.fondosbtg.exception.InvalidObjectIdException;
import com.btg.fondosbtg.exception.UsuarioNotFoundException;
import com.btg.fondosbtg.exception.FondoNotFoundException;
import com.btg.fondosbtg.model.Fondo;
import com.btg.fondosbtg.model.Transaccion;
import com.btg.fondosbtg.model.Usuario;
import com.btg.fondosbtg.repository.TransaccionRepository;
import com.btg.fondosbtg.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private FondoService fondoService;

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("John Doe");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario createdUsuario = usuarioService.crearUsuario(usuario);

        assertNotNull(createdUsuario);
        assertEquals("John Doe", createdUsuario.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testEliminarUsuario_Success() {
        String userId = new ObjectId().toString();

        when(usuarioRepository.existsById(any(ObjectId.class))).thenReturn(true);

        String result = usuarioService.eliminarUsuario(userId);

        assertEquals("Usuario eliminado correctamente.", result);
        verify(usuarioRepository, times(1)).deleteById(any(ObjectId.class));
    }

    @Test
    void testEliminarUsuario_UsuarioNotFound() {
        String userId = new ObjectId().toString();

        when(usuarioRepository.existsById(any(ObjectId.class))).thenReturn(false);

        assertThrows(UsuarioNotFoundException.class, () -> usuarioService.eliminarUsuario(userId));
    }

    @Test
    void testSuscribirAFondo_Success() {
        String usuarioId = new ObjectId().toString();
        String fondoId = new ObjectId().toString();

        Usuario usuario = new Usuario();
        usuario.setSaldo(1000);
        Fondo fondo = new Fondo();
        fondo.setMontoMinimo(500);
        fondo.setNombre("Fondo A");

        when(usuarioRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(usuario));
        when(fondoService.obtenerFondoPorId(fondoId)).thenReturn(Optional.of(fondo));

        String result = usuarioService.suscribirAFondo(usuarioId, fondoId);

        assertEquals("Suscripción al fondo Fondo A completada con éxito.", result);
        verify(usuarioRepository, times(1)).save(usuario);
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));
    }

    @Test
    void testSuscribirAFondo_InsufficientFunds() {
        String usuarioId = new ObjectId().toString();
        String fondoId = new ObjectId().toString();

        Usuario usuario = new Usuario();
        usuario.setSaldo(200);
        Fondo fondo = new Fondo();
        fondo.setMontoMinimo(500);
        fondo.setNombre("Fondo A");

        when(usuarioRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(usuario));
        when(fondoService.obtenerFondoPorId(fondoId)).thenReturn(Optional.of(fondo));

        String result = usuarioService.suscribirAFondo(usuarioId, fondoId);

        assertEquals("No tiene saldo disponible para vincularse al fondo Fondo A", result);
        verify(usuarioRepository, never()).save(usuario);
        verify(transaccionRepository, never()).save(any(Transaccion.class));
    }

    @Test
    void testCancelarSuscripcion_Success() {
        String usuarioId = new ObjectId().toString();
        String fondoId = new ObjectId().toString();

        Usuario usuario = new Usuario();
        usuario.getFondos().add(fondoId);
        Fondo fondo = new Fondo();
        fondo.setMontoMinimo(500);
        fondo.setNombre("Fondo A");

        when(usuarioRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(usuario));
        when(fondoService.obtenerFondoPorId(fondoId)).thenReturn(Optional.of(fondo));

        String result = usuarioService.cancelarSuscripcion(usuarioId, fondoId);

        assertEquals("Cancelación del fondo Fondo A realizada con éxito.", result);
        assertFalse(usuario.getFondos().contains(fondoId));
        verify(usuarioRepository, times(1)).save(usuario);
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));
    }

    @Test
    void testCancelarSuscripcion_FondoNotFound() {
        String usuarioId = new ObjectId().toString();
        String fondoId = new ObjectId().toString();

        Usuario usuario = new Usuario();

        when(usuarioRepository.findById(any(ObjectId.class))).thenReturn(Optional.of(usuario));
        when(fondoService.obtenerFondoPorId(fondoId)).thenReturn(Optional.empty());

        assertThrows(FondoNotFoundException.class, () -> usuarioService.cancelarSuscripcion(usuarioId, fondoId));
        verify(usuarioRepository, never()).save(usuario);
    }


}
