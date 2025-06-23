package ecoMarket.duoc.cl.usuarios.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")

public class Usuario {
    @Id
    @Column(name="id_usuario")
    private Integer idUsuario;



}
