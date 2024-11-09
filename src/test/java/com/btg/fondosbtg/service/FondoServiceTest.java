//package com.btg.fondosbtg.service;
//
//import com.btg.fondosbtg.model.Fondo;
//import com.btg.fondosbtg.model.Transaccion;
//import com.btg.fondosbtg.model.Usuario;
//import com.btg.fondosbtg.repository.FondoRepository;
//import com.btg.fondosbtg.repository.TransaccionRepository;
//import com.btg.fondosbtg.repository.UsuarioRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//@SpringBootTest
//@AutoConfigureDataMongo
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class FondoServiceTest {
//
//    @InjectMocks
//    private FondoService fondoService;
//
//    @Mock
//    private NotificacionService notificacionService;
//
//    @Mock
//    private FondoRepository fondoRepository;
//
//    @Mock
//    private UsuarioRepository usuarioRepository;
//
//    @Mock
//    private TransaccionRepository transaccionRepository;
//
//    @BeforeEach
//    void setup() {
//        fondoRepository.deleteAll();
//        usuarioRepository.deleteAll();
//        transaccionRepository.deleteAll();
//
//    }
//
//    @Test
//    void testSuscribirAFondo_success() {
//        // Given
//        Fondo fondo = Fondo.builder()
//                .id("1")
//                .nombre("FPV_BTG_PACTUAL_RECAUDADORA")
//                .montoMinimo(75000)
//                .categoria("FPV")
//                .build();
//
//        Usuario usuario = Usuario.builder()
//                .id("1")
//                .nombre("User")
//                .email("user@example.com")
//                .saldo(500000)
//                .telefono("555-1234")
//                .build();
//
//        when(fondoRepository.findById("1")).thenReturn(Optional.of(fondo));
//        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
//        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
//
//        // When
//        String result = fondoService.suscribirAFondo("1", "1");
//
//        // Then
//        assertEquals("Suscripción exitosa al fondo: FPV_BTG_PACTUAL_RECAUDADORA", result);
//        verify(usuarioRepository).save(usuario);
//        verify(transaccionRepository).save(any(Transaccion.class));
//        verify(notificacionService).enviarNotificacion(usuario, "Suscripción exitosa al fondo: " + fondo.getNombre());
//    }
//
//    @Test
//    void testSuscribirAFondo_insufficientFunds() {
//        // Given
//        Fondo fondo = Fondo.builder()
//                .id("1")
//                .nombre("FPV_BTG_PACTUAL_RECAUDADORA")
//                .montoMinimo(75000)
//                .categoria("FPV")
//                .build();
//
//        Usuario usuario = Usuario.builder()
//                .id("1")
//                .nombre("User")
//                .email("user@example.com")
//                .saldo(50000)
//                .telefono(null)
//                .build(); // Not enough funds
//
//        when(fondoRepository.findById("1")).thenReturn(Optional.of(fondo));
//        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));
//
//        // When
//        String result = fondoService.suscribirAFondo("1", "1");
//
//        // Then
//        assertEquals("No tiene saldo disponible para vincularse al fondo FPV_BTG_PACTUAL_RECAUDADORA", result);
//        verify(usuarioRepository, never()).save(any());
//        verify(notificacionService, never()).enviarNotificacion(any(), any());
//    }
//}
