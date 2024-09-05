package entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Domicilio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Domicilio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombreCalle;
    private int numero;

    // Para que sea bidireccional
    /*@OneToOne(mappedBy = "domicilio")
    private Cliente cliente;*/
}
