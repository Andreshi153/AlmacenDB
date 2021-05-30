package es.iespuertolacruz.almacen.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Empresa;

public class EmpresaModeloTest {

    static EmpresaModelo empresaModelo;
    Empresa empresa1 = new Empresa("Y78989898", "A", "A", "922456789", "A@a.a");

    @BeforeAll
    public static void setUp() {
        try {
            empresaModelo = new EmpresaModelo();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el modelo", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            empresaModelo.insertar(empresa1);
        } catch (Exception ex) {
            fail("No se ha podido insertar la empresa", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            empresaModelo.eliminar(empresa1);
        } catch (Exception ex) {
            fail("No se ha podido eliminar la empresa", ex);
        }
    }

    @Test
    public void buscarTodosTest() {
        try {
            assertTrue(empresaModelo.buscarTodos().size() > 0);
        } catch (Exception ex) {
            fail("Se producido una excepcion buscando todos las empresas");
        }
    }
}
