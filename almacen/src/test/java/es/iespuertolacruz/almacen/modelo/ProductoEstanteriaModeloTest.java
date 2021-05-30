package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.ProductoEstanteria;

public class ProductoEstanteriaModeloTest {

    static ProductoEstanteriaModelo productoEstanteriaModelo;
    ProductoEstanteria productoEstanteria1 = new ProductoEstanteria(20, 1, 100);

    @BeforeAll
    public static void setUp() {
        try {
            productoEstanteriaModelo = new ProductoEstanteriaModelo();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            productoEstanteriaModelo.insertar(productoEstanteria1);
        } catch (Exception ex) {
            fail("No se ha podido insertar el producto estanteria", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            productoEstanteriaModelo.eliminar(productoEstanteria1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar el producto estanteria", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(productoEstanteriaModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos los producto estanteria");
        }
    }
}
