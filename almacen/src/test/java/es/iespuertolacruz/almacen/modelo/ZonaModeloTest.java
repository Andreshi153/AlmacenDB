package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Zona;

public class ZonaModeloTest {

    static ZonaModelo zonaModelo;
    Zona zona1 = new Zona('Z', "Normal");

    @BeforeAll
    public static void setUp() {
        try {
            zonaModelo = new ZonaModelo();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            zonaModelo.insertar(zona1);
        } catch (Exception ex) {
            fail("No se ha podido insertar la zona", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            zonaModelo.eliminar(zona1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar la zona", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(zonaModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos las zonas");
        }
    }
}
