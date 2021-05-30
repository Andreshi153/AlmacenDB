package es.iespuertolacruz.almacen;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Producto;
import es.iespuertolacruz.almacen.controlador.ProductoController;

public class ProductoControllerTest {

    static ProductoController productoController;
    static Producto producto1;
    static Producto producto2;
    static Producto producto3 = new Producto(600, "Bandera de Canarias 3", 5, "Normal");

    @BeforeAll
    public static void setUp() {
        try {
            productoController = new ProductoController();
            producto1 = new Producto(productoController.obtenerIdUltimo()+1, "Bandera de Canarias", 5, "Normal");
            producto2 = new Producto(producto1.getIdProducto(), "Bandera de Canarias 2", 5.9f, "Normal");
        } catch (Exception ex) {
            fail("Se ha producido un error creando el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            productoController.insertar(producto1);
            assertEquals(producto1.getIdProducto(),productoController.obtenerIdUltimo());
        } catch (Exception ex) {
            fail("No se ha podido insertar el producto", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            productoController.eliminar(producto1);
            assertNotEquals(producto1.getIdProducto(), productoController.obtenerIdUltimo());
        } catch (Exception ex) {
            fail("No se ha podido eliminar el producto", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            productoController.insertar(producto1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            productoController.eliminar(producto3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            productoController.modificar(producto2);
            assertEquals(productoController.buscar(producto1.getIdProducto()).getNombre(), producto2.getNombre());
        } catch (Exception ex) {
            fail("No se ha podido modificar el producto", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            productoController.modificar(producto3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto no existe"));
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            ArrayList<Producto> lista = productoController.buscarTodos();
            assertNotEquals(0, lista.size());
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos los productos");
        }
    }
    
    @Test
    public void validarProductoTest() {
        try {
            productoController.validar(null);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto no puede ser nulo"));
        }
        try {
            productoController.validar(new Producto(0, null, -1, null));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El id del producto no puede ser inferior a 1"));
            assertTrue(ex.getMessage().contains("El nombre del producto no puede ser nulo"));
            assertTrue(ex.getMessage().contains("El precio del producto no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("El tipo del producto no puede ser nulo o vacio"));
        }
        try {
            productoController.validar(new Producto(1, "", 1, ""));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El nombre del producto no puede ser nulo o vacio"));
            assertTrue(ex.getMessage().contains("El tipo del producto no puede ser nulo o vacio"));
        }
    }
}