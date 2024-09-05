package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String apellido;

    @Column(name = "dni", unique = true)
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    // Para que sea bidireccional
    /*@OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();*/

}
