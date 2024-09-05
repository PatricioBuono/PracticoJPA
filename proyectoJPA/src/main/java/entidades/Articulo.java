package entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;


@Entity
@Table(name = "articulo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Articulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cantidad;
    private String denominacion;
    private int precio;

    // Para que sea bidireccional
   /* @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();*/

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //
    @JoinTable(
            name = "articulo_categoria",
            joinColumns = @JoinColumn(name = "articulo_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @Builder.Default
    private Set<Categoria> categorias = new HashSet<>();
}
