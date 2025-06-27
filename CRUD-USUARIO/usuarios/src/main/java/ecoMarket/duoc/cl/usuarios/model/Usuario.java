package ecoMarket.duoc.cl.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Integer idUsuario;

    @Column(nullable=false)
    @NotBlank(message = "El nombre no es valido")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message ="El apellido no es valido")
    private String apellidos;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El rut no es valido")
    private String rut;

    @Column(nullable = false,name="fecha_nacimiento")
    @NotNull(message = "La fecha de nacimiento no es valida")
    private Date fechaNacimiento;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El email no es valido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña no es valida")
    private String password;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "El telefono no es valido")
    private String telefono;

    @Column(nullable = false)
    private Boolean estado = true;


}
