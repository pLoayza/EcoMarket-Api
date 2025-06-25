package ecoMarket.duoc.cl.usuarios.repository;

import ecoMarket.duoc.cl.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    boolean existsByRut(String rut);

    boolean existsByEmail(String email);

    boolean existsByTelefono(String telefono);
}
