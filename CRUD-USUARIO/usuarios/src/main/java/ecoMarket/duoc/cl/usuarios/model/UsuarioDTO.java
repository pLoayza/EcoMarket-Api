package ecoMarket.duoc.cl.usuarios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nombre;
    private String apellidos;
    private String rut;
    private Date fechaNacimiento;
    private String email;
    private String password;
    private String telefono;
    private Boolean estado;
}
