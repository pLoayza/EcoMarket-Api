package ecoMarket.duoc.cl.usuarios.service;

import ecoMarket.duoc.cl.usuarios.model.Usuario;
import ecoMarket.duoc.cl.usuarios.model.UsuarioDTO;
import ecoMarket.duoc.cl.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Usuario u1 = new Usuario();
        u1.setNombre("Pedro");

        Usuario u2 = new Usuario();
        u2.setNombre("Ana");

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        var resultado = usuarioService.findAll();

        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById_existente() {
        Usuario u = new Usuario();
        u.setIdUsuario(1);  // cambiado aquí
        u.setNombre("Pedro");

        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(u));

        Usuario resultado = usuarioService.findById(1);

        assertEquals("Pedro", resultado.getNombre());
    }

    @Test
    void testFindById_inexistente() {
        when(usuarioRepository.existsById(999)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> usuarioService.findById(999));
    }

    @Test
    void testSave_conNombreConNumero_deberiaLanzarExcepcion() {
        Usuario u = new Usuario();
        u.setNombre("Juan1");
        u.setApellidos("Perez");

        assertThrows(IllegalArgumentException.class, () -> usuarioService.save(u));
    }

    @Test
    void testSave_conTelefonoInvalido_deberiaLanzarExcepcion() {
        Usuario u = new Usuario();
        u.setNombre("Juan");
        u.setApellidos("Perez");
        u.setTelefono("abc123");

        assertThrows(IllegalArgumentException.class, () -> usuarioService.save(u));
    }

    @Test
    void testSave_valido() {
        Usuario u = new Usuario();
        u.setNombre("Laura");
        u.setApellidos("González");
        u.setTelefono("912345678");
        u.setEmail("laura@mail.com");
        u.setRut("12345678-9");

        when(usuarioRepository.existsByRut(anyString())).thenReturn(false);
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(usuarioRepository.existsByTelefono(anyString())).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(u);

        Usuario guardado = usuarioService.save(u);

        assertEquals("Laura", guardado.getNombre());
        verify(usuarioRepository).save(u);
    }

    @Test
    void testDeleteById_existente() {
        when(usuarioRepository.existsById(1)).thenReturn(true);

        usuarioService.deleteById(1);

        verify(usuarioRepository).deleteById(1);
    }

    @Test
    void testDeleteById_inexistente() {
        when(usuarioRepository.existsById(999)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> usuarioService.deleteById(999));
    }

    @Test
    void testUpdate_valido() {
        Usuario original = new Usuario();
        original.setIdUsuario(1);  // cambiado aquí
        original.setNombre("Pedro");
        original.setApellidos("Soto");

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Pedro actualizado");
        dto.setEmail("nuevo@mail.com");

        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(original));
        when(usuarioRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Usuario actualizado = usuarioService.update(1, dto);

        assertEquals("Pedro actualizado", actualizado.getNombre());
        assertEquals("nuevo@mail.com", actualizado.getEmail());
    }

    @Test
    void testUpdate_cambiarRut_deberiaLanzarExcepcion() {
        Usuario original = new Usuario();
        original.setIdUsuario(1);  // cambiado aquí
        original.setNombre("Pedro");

        UsuarioDTO dto = new UsuarioDTO();
        dto.setRut("nuevoRut");

        when(usuarioRepository.existsById(1)).thenReturn(true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(original));

        assertThrows(IllegalArgumentException.class, () -> usuarioService.update(1, dto));
    }
}