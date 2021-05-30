package es.iespuertolacruz.almacen;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.ProductoEstanteria;
import es.iespuertolacruz.almacen.controlador.ProductoEstanteriaController;

public class ProductoEstanteriaControllerTest {

    static ProductoEstanteriaController productoEstanteriaController;
    ProductoEstanteria productoEstanteria1 = new ProductoEstanteria(20, 1, 100);
    ProductoEstanteria productoEstanteria2 = new ProductoEstanteria(20, 1, 200);
    ProductoEstanteria productoEstanteria3 = new ProductoEstanteria(30, 1, 100);

    @BeforeAll
    public static void setUp() {
        try {
            productoEstanteriaController = new ProductoEstanteriaController();
        } catch (Exception ex) {
            fail("Se ha producido un error creando el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            productoEstanteriaController.insertar(productoEstanteria1);
            assertNotNull(productoEstanteriaController.buscar(productoEstanteria1.getIdProducto(), productoEstanteria1.getIdEstanteria()));
        } catch (Exception ex) {
            fail("No se ha podido insertar el producto estanteria", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            productoEstanteriaController.eliminar(productoEstanteria1);
            assertNull(productoEstanteriaController.buscar(productoEstanteria1.getIdProducto(), productoEstanteria1.getIdEstanteria()));        } catch (Exception ex) {
            fail("No se ha podido eliminar el producto estanteria", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            productoEstanteriaController.insertar(productoEstanteria1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto estanteria ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            productoEstanteriaController.eliminar(productoEstanteria3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto estanteria no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            productoEstanteriaController.modificar(productoEstanteria2);
            assertEquals(productoEstanteriaController.buscar(productoEstanteria1.getIdProducto(), productoEstanteria1.getIdEstanteria()).getCantidad(), productoEstanteria2.getCantidad());
        } catch (Exception ex) {
            fail("No se ha podido modificar el productoEstanteria", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            productoEstanteriaController.modificar(productoEstanteria3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El producto estanteria no existe"));
        }
    }
    
    @Test
    public void validarProductoEstanteriaTest() {
        try {
            productoEstanteriaController.validar(null);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El productoEstanteria no puede ser nulo"));
        }
        try {
            productoEstanteriaController.validar(new ProductoEstanteria(0, 0, 0));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El id del producto no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("El id de la estanteria no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("La cantidad de productos no puede ser menor o igual que 0"));
        }
    }

    @Test
    public void obtenerValorProductosTotal() {
        try {
            assertTrue(productoEstanteriaController.obtenerValorProductosTotal() >= 0);
        } catch (Exception ex) {
            fail("Se ha producido una excepcion", ex);
        }
    }

    @Test
    public void obtenerHuecos() {
        try {
            Integer[] huecos = productoEstanteriaController.obtenerHuecos();
            assertTrue(huecos[0] >= 0);
            assertTrue(huecos[1] >= 0);
        } catch (Exception ex) {
            fail("Se ha producido una excepcion", ex);
        }
    }
}