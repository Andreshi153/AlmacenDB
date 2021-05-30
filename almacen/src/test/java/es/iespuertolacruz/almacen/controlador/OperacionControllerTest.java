package es.iespuertolacruz.almacen.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Operacion;
public class OperacionControllerTest {

    static OperacionController operacionController;
    Operacion operacion1 = new Operacion(3, 4, "2020-10-10", "Salida", "C44444444");
    Operacion operacion2 = new Operacion(operacion1.getIdListaProductos(), 3, "2020-12-12", "Salida", "B22222222");
    Operacion operacion3 = new Operacion(50, 4, "2020-11-11", "Salida", "C44444444");

    @BeforeAll
    public static void setUp() {
        try {
            operacionController = new OperacionController();

        } catch (Exception ex) {
            fail("Se ha producido un error al crear el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            operacionController.insertar(operacion1);
            assertNotNull(operacionController.buscar(operacion1.getIdListaProductos()));
        } catch (Exception ex) {
            fail("No se ha podido insertar la operacion", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            operacionController.eliminar(operacion1);
            assertNull(operacionController.buscar(operacion1.getIdListaProductos()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar la operacion", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            operacionController.insertar(operacion1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La operacion ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            operacionController.eliminar(operacion3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La operacion no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            operacionController.modificar(operacion2);
            assertEquals(operacionController.buscar(operacion1.getIdListaProductos()).getFecha(),
                    operacion2.getFecha());
        } catch (Exception ex) {
            fail("No se ha podido modificar", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            operacionController.modificar(operacion3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La operacion no existe"));
        }
    }

    @Test
    public void validarMuelleTest() {
        try {
            operacionController.validar(null);

        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La operacion no puede ser nula"));
        }
        try {
            operacionController.validar(new Operacion(0, 0, null, null, null));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El id de la lista de productos no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("El id del muelle no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("La fecha de la operacion no puede ser nula o no valida"));
            assertTrue(ex.getMessage().contains("El tipo de la operacion no puede ser nulo o no valido"));
            assertTrue(ex.getMessage().contains("El cif de la operacion no puede ser nulo o no valido"));
        }
        try {
            operacionController.validar(new Operacion(5, 4, "2020-1-10", "Sali", "C4"));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La fecha de la operacion no puede ser nula o no valida"));
            assertTrue(ex.getMessage().contains("El tipo de la operacion no puede ser nulo o no valido"));
            assertTrue(ex.getMessage().contains("El cif de la operacion no puede ser nulo o no valido"));
        }
    }
}
