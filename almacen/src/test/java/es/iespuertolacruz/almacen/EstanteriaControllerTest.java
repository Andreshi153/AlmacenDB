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

import es.iespuertolacruz.almacen.api.Estanteria;
import es.iespuertolacruz.almacen.controlador.EstanteriaController;

public class EstanteriaControllerTest {

    static EstanteriaController estanteriaController;
    Estanteria estanteria1 = new Estanteria(1000, 'A', 5, 0);
    Estanteria estanteria2 = new Estanteria(1000, 'B', 5, 0);
    Estanteria estanteria3 = new Estanteria(2000, 'A', 5, 0);

    @BeforeAll
    public static void setUp() {
        try {
            estanteriaController = new EstanteriaController();
        } catch (Exception ex) {
            fail("Se ha producido un error creando el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            estanteriaController.insertar(estanteria1);
            assertNotNull(estanteriaController.buscar(estanteria1.getIdEstanteria()));
        } catch (Exception ex) {
            fail("No se ha podido insertar la estanteria", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            estanteriaController.eliminar(estanteria1);
            assertNull(estanteriaController.buscar(estanteria1.getIdEstanteria()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar la estanteria", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            estanteriaController.insertar(estanteria1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La estanteria ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            estanteriaController.eliminar(estanteria3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La estanteria no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            estanteriaController.modificar(estanteria2);
            assertEquals(estanteriaController.buscar(estanteria1.getIdEstanteria()).getIdZona(), estanteria2.getIdZona());
        } catch (Exception ex) {
            fail("No se ha podido modificar la estanteria", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            estanteriaController.modificar(estanteria3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La estanteria no existe"));
        }
    }
    
    @Test
    public void validarEstanteriaTest() {
        try {
            estanteriaController.validar(null);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La estanteria no puede ser nula"));
        }
        try {
            estanteriaController.validar(new Estanteria(-1, 'a', 0, 3));
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("El id de la estanteria no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("El id de la zona debe estar entre la A y la Z"));
            assertTrue(ex.getMessage().contains("El numero de alturas de la estanteria no puede ser menor o igual que 0"));
            assertTrue(ex.getMessage().contains("El numero de huecos ocupados de la estanteria no puede ser mayor que el numero de alturas"));
        }
    }
}