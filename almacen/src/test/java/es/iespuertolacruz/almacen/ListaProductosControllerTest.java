package es.iespuertolacruz.almacen;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.ListaProductos;
import es.iespuertolacruz.almacen.controlador.ListaProductosController;

public class ListaProductosControllerTest {

    static ListaProductosController listaProductosController;
    static ListaProductos listaProductos1 = new ListaProductos();
    static ListaProductos listaProductos2;

    @BeforeAll
    public static void setUp() {
        try {
            listaProductosController = new ListaProductosController();
            listaProductos1.setIdListaProducto(listaProductosController.obtenerMaxIdListaProductos()+1);
            HashMap<Integer, Integer> mapaLista1 = generarMapa(2);
            listaProductos1.setLista(mapaLista1);
            listaProductos2 = new ListaProductos(listaProductos1.getIdListaProductos(), generarMapa(3));
        } catch (Exception ex) {
            fail("Se ha producido un error creando el controlador", ex);
        }
    }

    private static HashMap<Integer, Integer> generarMapa(int tamanio) {
        HashMap<Integer, Integer> mapa = new HashMap<>();
        for(int i = 1; i <= tamanio; i++) {
            mapa.put(i, (int) Math.ceil(Math.random()*20));
        }
        return mapa;
    }

    @BeforeEach
    public void insertarTest() {
        try {
            listaProductosController.insertar(listaProductos1);
            assertNotNull(listaProductosController.buscar(listaProductos1.getIdListaProductos()));
        } catch (Exception ex) {
            fail("No se ha podido insertar la lista de productos", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            listaProductosController.eliminar(listaProductos1);
            assertNull(listaProductosController.buscar(listaProductos1.getIdListaProductos()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar la lista de productos", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            listaProductosController.insertar(listaProductos1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La listaProductos ya existe en la base de datos"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            listaProductosController.eliminar(new ListaProductos(500, generarMapa(1)));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La listaProductos no existe en la base de datos"));
        }
    }

    @Test
    public void modificar() {
        try {
            listaProductosController.modificar(listaProductos2);
            assertEquals(listaProductosController.buscar(listaProductos1.getIdListaProductos()).getLista(), listaProductos2.getLista());
        } catch (Exception ex) {
            fail("No se ha podido modificar la lista de productos", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            listaProductosController.modificar(new ListaProductos(500, generarMapa(1)));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La listaProductos no existe en la base de datos"));
        }
    }
    
    @Test
    public void validarListaProductosTest() {
        try {
            listaProductosController.validar(null);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La listaProductos no puede ser nula"));
        }
        try {
            listaProductosController.validar(new ListaProductos(0, generarMapa(0)));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El id de la listaProductos no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("La lista de productos de listaProductos no puede estar vacia"));
        }
        try {
            listaProductosController.validar(new ListaProductos(5, null));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La lista de productos de listaProductos no puede estar vacia o ser nula"));
        }
    }
}
