package ecoMarket.duoc.cl.usuarios.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecoMarket.duoc.cl.usuarios.model.Usuario;
import ecoMarket.duoc.cl.usuarios.model.UsuarioDTO;
import ecoMarket.duoc.cl.usuarios.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void obtenerTodos_deberiaRetornarOK() throws Exception {
        Usuario u = new Usuario();
        u.setNombre("Pedro");
        u.setEmail("pedro@mail.com");

        when(usuarioService.findAll()).thenReturn(List.of(u));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Pedro"));
    }

    @Test
    void obtenerPorId_existente_deberiaRetornarOK() throws Exception {
        Usuario u = new Usuario();
        u.setNombre("Maria");
        u.setIdUsuario(1);  // cambiado aquí
        u.setEmail("maria@mail.com");

        when(usuarioService.findById(1)).thenReturn(u);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Maria"));
    }

    @Test
    void obtenerPorId_inexistente_deberiaRetornarNotFound() throws Exception {
        when(usuarioService.findById(999)).thenThrow(new EntityNotFoundException("Usuario no encontrado"));

        mockMvc.perform(get("/api/usuarios/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario no encontrado"));
    }

    @Test
    void crearUsuario_valido_deberiaRetornarCreated() throws Exception {
        Usuario u = new Usuario();
        u.setNombre("Luis");
        u.setApellidos("Pérez");
        u.setEmail("luis@mail.com");
        u.setRut("12345678-9");
        u.setTelefono("912345678");
        u.setPassword("clave123");
        u.setFechaNacimiento(new Date()); // ✅ Se agrega fecha de nacimiento

        when(usuarioService.save(any(Usuario.class))).thenReturn(u);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Luis"));
    }

    @Test
    void crearUsuario_nombreConNumero_deberiaRetornarBadRequest() throws Exception {
        Usuario u = new Usuario();
        u.setNombre("Luis9"); // nombre inválido
        u.setApellidos("Pérez");
        u.setEmail("luis@mail.com");
        u.setRut("12345678-9");
        u.setTelefono("912345678");
        u.setPassword("clave123");
        u.setFechaNacimiento(new Date()); // ✅ requerido

        when(usuarioService.save(any())).thenThrow(new IllegalArgumentException("El nombre no debe contener números"));

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(u)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("El nombre no debe contener números"));
    }

    @Test
    void actualizarUsuario_valido_deberiaRetornarOK() throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("NuevoNombre");

        Usuario actualizado = new Usuario();
        actualizado.setIdUsuario(1);  // cambiado aquí
        actualizado.setNombre("NuevoNombre");

        when(usuarioService.update(eq(1), any(UsuarioDTO.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("NuevoNombre"));
    }

    @Test
    void eliminarUsuario_existente_deberiaRetornarOK() throws Exception {
        doNothing().when(usuarioService).deleteById(1);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado correctamente"));
    }

    @Test
    void eliminarUsuario_inexistente_deberiaRetornarNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Usuario no encontrado")).when(usuarioService).deleteById(999);

        mockMvc.perform(delete("/api/usuarios/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario no encontrado"));
    }
}