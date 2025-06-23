package ecoMarket.duoc.cl.productos.repository;

import ecoMarket.duoc.cl.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
