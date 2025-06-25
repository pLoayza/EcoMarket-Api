package ecoMarket.duoc.cl.usuarios.service;


import ecoMarket.duoc.cl.usuarios.model.Usuario;
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
        if (usuarioRepository.existsByRut(usuario.getRut())) {
            throw new IllegalArgumentException("RUT ya registrado");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (usuarioRepository.existsByTelefono(usuario.getTelefono())) {
            throw new IllegalArgumentException("Teléfono ya registrado");
        }
        return usuarioRepository.save(usuario);
    }
    public void deleteById(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
    public Usuario update(Integer id, Usuario usuario) {
        Usuario usuario1 = findById(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setApellidos(usuario.getApellidos());
        usuario1.setEmail(usuario.getEmail());
        usuario1.setRut(usuario.getRut());
        usuario1.setPassword(usuario.getPassword());
        usuario1.setFechaNacimiento(usuario.getFechaNacimiento());
        usuario1.setEstado(usuario.getEstado());
        usuario1.setTelefono(usuario.getTelefono());
        return usuarioRepository.save(usuario1);
    }

}
