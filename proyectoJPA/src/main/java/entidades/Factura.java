package entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "factura")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fecha;
    private int numero;
    private int total;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @OneToMany(//mappedBy = "factura",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    @Builder.Default
    private Set<DetalleFactura> detalles = new HashSet<>();
}
