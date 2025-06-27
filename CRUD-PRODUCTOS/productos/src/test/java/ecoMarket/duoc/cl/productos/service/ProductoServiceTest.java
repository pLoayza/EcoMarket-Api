package ecoMarket.duoc.cl.productos.service;

import ecoMarket.duoc.cl.productos.model.Producto;
import ecoMarket.duoc.cl.productos.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.test.context.ActiveProfiles;
@ActiveProfiles("test")
class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        Producto p1 = new Producto();
        p1.setNombre("Pan");
        p1.setPrecio(1000);

        Producto p2 = new Producto();
        p2.setNombre("Leche");
        p2.setPrecio(1200);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Producto> resultado = productoService.obtenerTodos();

        assertEquals(2, resultado.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Café");

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Optional<Producto> resultado = productoService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Café", resultado.get().getNombre());
    }

    @Test
    void testGuardar() {
        Producto producto = new Producto();
        producto.setNombre("Queso");
        producto.setPrecio(2500);

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.guardar(producto);

        assertNotNull(resultado);
        assertEquals("Queso", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testEliminar() {
        Long id = 5L;

        productoService.eliminar(id);

        verify(productoRepository, times(1)).deleteById(id);
    }
}
