package es.iespuertolacruz.almacen.controlador;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Cliente;
public class ClienteControllerTest {

    static ClienteController clienteController;
    Cliente cliente1 = new Cliente("X00000000", 0.85f);
    Cliente cliente2 = new Cliente(cliente1.getCif(), 0.9f);
    Cliente cliente3 = new Cliente("X12345678", 1f);

    @BeforeAll
    public static void setUp() {
        try {
            clienteController = new ClienteController();
        } catch (Exception ex) {
            fail("Se ha producido un error creando el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            clienteController.insertar(cliente1);
            assertNotNull(clienteController.buscar(cliente1.getCif()));
        } catch (Exception ex) {
            fail("No se ha podido insertar el cliente", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            clienteController.eliminar(cliente1);
            assertNull(clienteController.buscar(cliente1.getCif()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar el cliente", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            clienteController.insertar(cliente1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cliente ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            clienteController.eliminar(cliente3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cliente no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            clienteController.modificar(cliente2);
            assertEquals(clienteController.buscar(cliente1.getCif()).getPorcentajeDesc(), cliente2.getPorcentajeDesc());
        } catch (Exception ex) {
            fail("No se ha podido modificar el cliente", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            clienteController.modificar(cliente3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cliente no existe"));
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            ArrayList<Cliente> lista = clienteController.buscarTodos();
            assertNotEquals(0, lista.size());
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos los clientes");
        }
    }
    
    @Test
    public void validarClienteTest() {
        try {
            clienteController.validar(null);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cliente no puede ser nulo"));
        }
        try {
            clienteController.validar(new Cliente(null, -1));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cif del cliente no puede ser nulo"));
            assertTrue(ex.getMessage().contains("El porcentaje de descuento del cliente no puede ser menor que 0"));
        }
        try {
            clienteController.validar(new Cliente("", 1));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cif del cliente no puede ser nulo"));
        }
    }
}
