package main;

import entidades.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Factura factura1 = Factura.builder().build();

            factura1.setFecha("10/05/2022");
            factura1.setNumero(12);

            Domicilio dom = Domicilio.builder()
                    .nombreCalle("Aristides")
                    .numero(123)
                    .build();

            Cliente cliente1 = Cliente.builder()
                    .nombre("Patricio")
                    .apellido("Buosi")
                    .dni(34414701)
                    .build();

            //dom.setCliente(cliente1);
            cliente1.setDomicilio(dom);
            factura1.setCliente(cliente1);

            Categoria perecederos = Categoria.builder()
                    .denominacion("Perecederos")
                    .build();
            Categoria lacteos = Categoria.builder()
                    .denominacion("Lacteos")
                    .build();
            Categoria limpieza = Categoria.builder()
                    .denominacion("Limpieza")
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(250)
                    .denominacion("Leche en polvo")
                    .precio(1200)
                    .build();
            Articulo art2 = Articulo.builder()
                    .cantidad(75)
                    .denominacion("Mr Músculo")
                    .precio(2000)
                    .build();

            art1.getCategorias().add(perecederos);
            art1.getCategorias().add(lacteos);
            lacteos.getArticulos().add(art1);
            perecederos.getArticulos().add(art1);

            art2.getCategorias().add(limpieza);
            limpieza.getArticulos().add(art2);


            DetalleFactura detalle1 = DetalleFactura.builder().build();

            detalle1.setArticulo(art1);
            detalle1.setCantidad(20);
            detalle1.setSubTotal(80);

            //art1.getDetalles().add(detalle1);
            factura1.getDetalles().add(detalle1);
            //detalle1.setFactura(factura1);

            DetalleFactura detalle2 = DetalleFactura.builder().build();

            detalle2.setArticulo(art2);
            detalle2.setCantidad(54);
            detalle2.setSubTotal(157);

            //art2.getDetalles().add(detalle2);
            factura1.getDetalles().add(detalle2);
            //detalle2.setFactura(factura1);

            factura1.setTotal(120);


            em.persist(factura1);

            em.flush();

            em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();

        }

        em.close();
        emf.close();

    }
}
