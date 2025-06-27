package ecoMarket.duoc.cl.productos.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO{
    private Integer id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private Integer stock;
    private Boolean estado;
}
