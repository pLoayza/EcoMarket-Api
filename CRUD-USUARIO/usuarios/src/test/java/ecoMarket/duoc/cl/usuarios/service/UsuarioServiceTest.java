package ecoMarket.duoc.cl.usuarios.service;


import ecoMarket.duoc.cl.usuarios.model.Usuario;
import ecoMarket.duoc.cl.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll(){

        Usuario usuario =  new Usuario();
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
    List<Usuario> usuarios = usuarioService.findAll();
    assertNotNull(usuarios);
    assertEquals(1,usuarios.size());


    }
}
