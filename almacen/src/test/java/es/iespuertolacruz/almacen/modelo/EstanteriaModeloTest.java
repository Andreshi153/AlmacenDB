package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Estanteria;

public class EstanteriaModeloTest {

    static EstanteriaModelo estanteriaModelo;
    Estanteria estanteria1 = new Estanteria(1000, 'A', 5, 0);

    @BeforeAll
    public static void setUp() {
        try {
            estanteriaModelo = new EstanteriaModelo();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            estanteriaModelo.insertar(estanteria1);
        } catch (Exception ex) {
            fail("No se ha podido insertar la estanteria", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            estanteriaModelo.eliminar(estanteria1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar la estanteria", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(estanteriaModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos las estanterias");
        }
    }
}
