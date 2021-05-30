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

import es.iespuertolacruz.almacen.api.Proveedor;
public class ProveedorControllerTest {

    static ProveedorController proveedorController;
    Proveedor proveedor1 = new Proveedor("U89087654", "Normal");
    Proveedor proveedor2 = new Proveedor(proveedor1.getCif(), "Frio");
    Proveedor proveedor3 = new Proveedor("B22344556", "Congelados");

    @BeforeAll
    public static void setUp() {
        try {
            proveedorController = new ProveedorController();

        } catch (Exception ex) {
            fail("Se ha producido un error al crear el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            proveedorController.insertar(proveedor1);
            assertNotNull(proveedorController.buscar(proveedor1.getCif()));
        } catch (Exception ex) {
            fail("No se ha podido insertar al proveedor", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            proveedorController.eliminar(proveedor1);
            assertNull(proveedorController.buscar(proveedor1.getCif()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar al proveedor", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            proveedorController.insertar(proveedor1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El proveedor ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            proveedorController.eliminar(proveedor3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El proveedor no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            proveedorController.modificar(proveedor2);
            assertEquals(proveedorController.buscar(proveedor1.getCif()).getTipoProducto(),
                    proveedor2.getTipoProducto());
        } catch (Exception ex) {
            fail("No se ha podido modificar", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            proveedorController.modificar(proveedor3);
        } catch (Exception ex) {
            assertTrue((ex.getMessage().contains("El proveedor no existe")));
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(proveedorController.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se ha producido una excepcion buscando todos los proveedores", ex);
        }
    }

    @Test
    public void validarEmpresaTest() {
        try {
            proveedorController.validar(null);

        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El proveedor no puede ser nulo"));
        }
        try {
            proveedorController.validar(new Proveedor(null, null));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cif del proveedor no puede ser nulo o no valido"));
            assertTrue(ex.getMessage().contains("El tipo de producto del proveedor no puede ser nulo o no valido"));
        }
        try {
            proveedorController.validar(new Proveedor("", "A"));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El cif del proveedor no puede ser nulo o no valido"));
            assertTrue(ex.getMessage().contains("El tipo de producto del proveedor no puede ser nulo o no valido"));
        }
    }

}
