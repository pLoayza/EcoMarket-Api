package ecoMarket.duoc.cl.productos.service;

import ecoMarket.duoc.cl.productos.model.Producto;
import ecoMarket.duoc.cl.productos.model.ProductoDTO;
import ecoMarket.duoc.cl.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    public Producto obtenerPorId(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        return  productoRepository.findById(id).get();
    }
    public Producto guardar(Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            throw new IllegalArgumentException("Nombre de producto ya registrado");
        }
        return productoRepository.save(producto);
    }
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
    public Producto update(Long id, ProductoDTO dto) {

        if (!productoRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Producto producto1 = obtenerPorId(id);
        if (dto.getNombre() != null) {
            if (productoRepository.existsByNombre(dto.getNombre()) &&  !dto.getNombre().equals(producto1.getNombre())) {
                throw new IllegalArgumentException("Nombre de producto ya registrado");
            }
            producto1.setNombre(dto.getNombre());
        }

        if (dto.getPrecio() != null) {
            if (dto.getPrecio()<= 0 ) {
                throw new IllegalArgumentException("Precio no válido");
            }
            producto1.setPrecio(dto.getPrecio());
        }
        if (dto.getDescripcion()!= null) {
            producto1.setDescripcion(dto.getDescripcion());
        }
        if (dto.getStock() != null) {
            if (dto.getStock()< 0) {
                throw new IllegalArgumentException("El stock no puede ser negativo");
            }
            producto1.setStock(dto.getStock());
        }
        if (dto.getEstado() != null) {
            producto1.setEstado(dto.getEstado());
        }
        productoRepository.save(producto1);
        return producto1;
    }
}
