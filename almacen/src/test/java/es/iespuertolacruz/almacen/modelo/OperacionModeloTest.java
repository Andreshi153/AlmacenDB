package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Operacion;

public class OperacionModeloTest {

    static OperacionModelo operacionModelo;
    Operacion operacion1 = new Operacion(3, 4, "2020-10-10", "Salida", "C44444444");

    @BeforeAll
    public static void setUp() {
        try {
            operacionModelo = new OperacionModelo();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            operacionModelo.insertar(operacion1);
        } catch (Exception ex) {
            fail("No se ha podido insertar la operacion", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            operacionModelo.eliminar(operacion1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar la operacion", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(operacionModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos las operaciones");
        }
    }
}
