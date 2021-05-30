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

import es.iespuertolacruz.almacen.api.Zona;
public class ZonaControllerTest {

    static ZonaController zonaController;
    Zona zona1 = new Zona('Z', "Normal");
    Zona zona2 = new Zona(zona1.getIdZona(), "Frio");
    Zona zona3 = new Zona('Y', "Congelados");

    @BeforeAll
    public static void setUp() {
        try {
            zonaController = new ZonaController();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            zonaController.insertar(zona1);
            assertNotNull(zonaController.buscar(zona1.getIdZona()));
        } catch (Exception ex) {
            fail("No se ha podido insertar la zona", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            zonaController.eliminar(zona1);
            assertNull(zonaController.buscar(zona1.getIdZona()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar la zona", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            zonaController.insertar(zona1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La zona ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            zonaController.eliminar(zona3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La zona no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            zonaController.modificar(zona2);
            assertEquals(zonaController.buscar(zona1.getIdZona()).getTipo(), zona2.getTipo());
        } catch (Exception ex) {
            fail("No se ha podido modificar", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            zonaController.modificar(zona3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La zona no existe"));
        }
    }

    @Test
    public void validarMuelleTest() {
        try {
            zonaController.validar(null);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La zona no puede ser nula"));
        }
        try {
            zonaController.validar(new Zona('a', null));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El id de la zona debe estar entre la A y la Z"));
            assertTrue(ex.getMessage().contains("El tipo de la zona no puede ser nulo o no valido"));
        }
        try {
            zonaController.validar(new Zona('A', "A"));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El tipo de la zona no puede ser nulo o no valido"));
        }
    }
}
