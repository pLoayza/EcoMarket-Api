package ecoMarket.duoc.cl.productos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ecoMarket.duoc.cl.productos.model.Producto;
import ecoMarket.duoc.cl.productos.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.context.ActiveProfiles;
@ActiveProfiles("test")
@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // POST tests
    @Test
    void crearProducto_conNombreVacio_deberiaRetornarBadRequest() throws Exception {
        Producto producto = new Producto();
        producto.setNombre(""); // ❌ inválido
        producto.setPrecio(1000);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearProducto_conPrecioNegativo_deberiaRetornarBadRequest() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Producto válido");
        producto.setPrecio(-200); // ❌ inválido

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearProducto_valido_deberiaRetornarOK() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Producto válido");
        producto.setPrecio(500);
        producto.setDescripcion("Descripción opcional");

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk());
    }

    // PUT tests
    @Test
    void actualizarProducto_conPrecioNegativo_deberiaRetornarBadRequest() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Producto actualizado");
        producto.setPrecio(-999); // ❌ inválido

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarProducto_valido_deberiaRetornarOK() throws Exception {
        Producto producto = new Producto();
        producto.setNombre("Producto actualizado");
        producto.setPrecio(1000);

        when(productoService.guardar(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk());
    }

    // GET test
    @Test
    void obtenerProducto_porIdInexistente_deberiaRetornarNotFound() throws Exception {
        when(productoService.obtenerPorId(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/productos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void obtenerProducto_porIdValido_deberiaRetornarOK() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto ejemplo");
        producto.setPrecio(1000);

        when(productoService.obtenerPorId(1L)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk());
    }

    // DELETE test
    @Test
    void eliminarProducto_porIdValido_deberiaRetornarOK() throws Exception {
        doNothing().when(productoService).eliminar(1L);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());
    }
}
