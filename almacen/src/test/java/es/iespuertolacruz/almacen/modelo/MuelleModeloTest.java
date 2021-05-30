package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Muelle;

public class MuelleModeloTest {

    static MuelleModelo muelleModelo;
    Muelle muelle1 = new Muelle(50, 'A', false);

    @BeforeAll
    public static void setUp() {
        try {
            muelleModelo = new MuelleModelo();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            muelleModelo.insertar(muelle1);
        } catch (Exception ex) {
            fail("No se ha podido insertar el muelle", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            muelleModelo.eliminar(muelle1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar el muelle", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(muelleModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos los muelles");
        }
    }
}
