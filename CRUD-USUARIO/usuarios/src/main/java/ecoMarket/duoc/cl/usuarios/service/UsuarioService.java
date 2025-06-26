package ecoMarket.duoc.cl.usuarios.service;


import ecoMarket.duoc.cl.usuarios.model.Usuario;
import ecoMarket.duoc.cl.usuarios.model.UsuarioDTO;
import ecoMarket.duoc.cl.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional

public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    public Usuario findById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        return  usuarioRepository.findById(id).get();
    }
    public Usuario save(Usuario usuario) {
        if (usuario.getNombre() != null && usuario.getNombre().matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no debe contener números");
        }
        if (usuario.getApellidos() != null && usuario.getApellidos().matches(".*\\d.*")) {
            throw new IllegalArgumentException("El apellido no puede contener numeros");
        }
        if (usuarioRepository.existsByRut(usuario.getRut())) {
            throw new IllegalArgumentException("RUT ya registrado");
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (usuarioRepository.existsByTelefono(usuario.getTelefono())) {
            throw new IllegalArgumentException("Teléfono ya registrado");
        }
        if (usuario.getTelefono() != null && !usuario.getTelefono().matches("^[0-9\\s()\\-]+$")) {
            throw new IllegalArgumentException("Formato de telefono no permitido");
        }
        return usuarioRepository.save(usuario);
    }
    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
    public Usuario update(Integer id, UsuarioDTO dto) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario1 = findById(id);
        if (dto.getNombre() != null) {
            if (dto.getNombre().matches(".*\\d.*")) {
                throw new IllegalArgumentException("El nombre no puede contener números");
            }
            usuario1.setNombre(dto.getNombre());
        }

        if (dto.getApellidos() != null) {
            if (dto.getApellidos().matches(".*\\d.*")) {
                throw new IllegalArgumentException("El apellido no puede contener números");
            }
            usuario1.setApellidos(dto.getApellidos());
        }

        if (dto.getRut() != null) {
            throw new IllegalArgumentException("No se puede cambiar el rut ya registrado, el campo debe estar vacío");
        }
        if (dto.getFechaNacimiento() != null) {
            throw new IllegalArgumentException("No se puede cambiar la fecha de nacimiento ya registrada, el campo debe estar vacío");
        }

        if (dto.getTelefono() != null) {
            if (!dto.getTelefono().matches("^[0-9\\s()\\-]+$")) {
                throw new IllegalArgumentException("Formato de telefono no válido");
            }
            usuario1.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null) {
            usuario1.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            usuario1.setPassword(dto.getPassword());
        }
        if (dto.getEstado() != null) {
            usuario1.setEstado(dto.getEstado());
        }
        usuarioRepository.save(usuario1);
        return usuario1;
    }

}
