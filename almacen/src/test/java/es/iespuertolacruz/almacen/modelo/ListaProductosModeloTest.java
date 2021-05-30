package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.ListaProductos;

public class ListaProductosModeloTest {

    static ListaProductosModelo listaProductosModelo;
    static ListaProductos listaProductos1 = new ListaProductos();

    @BeforeAll
    public static void setUp() {
        try {
            listaProductosModelo = new ListaProductosModelo();
            listaProductos1.setIdListaProducto(100);
            HashMap<Integer, Integer> mapa = new HashMap<>();
            mapa.put(1, 500);
            listaProductos1.setLista(mapa);
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            listaProductosModelo.insertar(listaProductos1);
        } catch (Exception ex) {
            fail("No se ha podido insertar la lista de productos", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            listaProductosModelo.eliminar(listaProductos1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar la lista de productos", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(listaProductosModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todas las listas de productos");
        }
    }
}
