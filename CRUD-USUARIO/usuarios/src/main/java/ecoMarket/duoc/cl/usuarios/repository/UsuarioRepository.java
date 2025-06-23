package ecoMarket.duoc.cl.usuarios.repository;

import ecoMarket.duoc.cl.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
