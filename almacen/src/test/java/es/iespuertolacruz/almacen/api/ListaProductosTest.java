package es.iespuertolacruz.almacen.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListaProductosTest {

    ListaProductos listaProductos;

    @BeforeEach
    public void setUp() {
        if (listaProductos == null) {
            listaProductos = new ListaProductos();
            listaProductos.setIdListaProducto(100);
            HashMap<Integer, Integer> mapa = new HashMap<>();
            mapa.put(1, 500);
            mapa.put(2, 300);
            listaProductos.setLista(mapa);
        }
    }

    @Test
    public void toStringTest() {
        String informacion = listaProductos.toString();
        assertTrue(informacion.contains("-> Id de la lista: " + listaProductos.getIdListaProductos()));
        listaProductos.getLista().forEach((producto, cantidad) -> assertTrue(
                informacion.contains("· Producto " + producto + ": " + cantidad + " articulos")));
    }
}
