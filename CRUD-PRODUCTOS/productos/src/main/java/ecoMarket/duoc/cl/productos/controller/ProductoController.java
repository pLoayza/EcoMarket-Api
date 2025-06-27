package ecoMarket.duoc.cl.productos.controller;

import ecoMarket.duoc.cl.productos.model.Producto;
import ecoMarket.duoc.cl.productos.model.ProductoDTO;
import ecoMarket.duoc.cl.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return new ResponseEntity<>(productoService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productoService.obtenerPorId(id));

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@Valid @RequestBody Producto producto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(err -> err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            Producto creado = productoService.guardar(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id,@RequestBody ProductoDTO dto) {
       try {
            var resultado = productoService.update(id,dto);
           return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminar(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
