package ecoMarket.duoc.cl.productos.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Debe proporcionar un nombre válido")
    private String nombre;

    @Column(nullable = false)
    @Min(value = 1, message = "El precio no es valido")
    private double precio;

    @Column(nullable = false)
    @NotBlank(message = "Debe proporcionar una descripcion válida")
    private String descripcion;

    @Column(nullable = false)
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Column(nullable = false)
    private Boolean estado = true;

}

