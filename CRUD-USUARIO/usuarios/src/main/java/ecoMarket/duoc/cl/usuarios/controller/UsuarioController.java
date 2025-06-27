package ecoMarket.duoc.cl.usuarios.controller;



import ecoMarket.duoc.cl.usuarios.model.Usuario;
import ecoMarket.duoc.cl.usuarios.model.UsuarioDTO;
import ecoMarket.duoc.cl.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream()
                    .map(err -> err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            Usuario creado = usuarioService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(required = false) String id) {
        try {
            Integer usuarioId = Integer.parseInt(id);
            return ResponseEntity.ok(usuarioService.findById(usuarioId));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El ID debe ser un número entero válido");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(required = false) String id) {
        try {
            Integer usuarioId = Integer.parseInt(id);
            usuarioService.deleteById(usuarioId);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El ID debe ser un número entero válido");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(required = false) String id, @RequestBody UsuarioDTO dto) {
        try {
            Integer usuarioId = Integer.parseInt(id);
            var resultado = usuarioService.update(usuarioId, dto);
            return ResponseEntity.ok(resultado);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El ID debe ser un número entero válido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

}

