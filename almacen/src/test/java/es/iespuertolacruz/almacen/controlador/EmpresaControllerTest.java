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

import es.iespuertolacruz.almacen.api.Empresa;
public class EmpresaControllerTest {
    static EmpresaController empresaController;
    Empresa empresa1 = new Empresa("Y78989898", "A", "A", "922456789", "A@a.a");
    Empresa empresa2 = new Empresa(empresa1.getCif(), "B", "B", "922111111", "B@b.b");
    Empresa empresa3 = new Empresa("C45463820", "C", "C", "922333444", "C@c.c");

    @BeforeAll
    public static void setUp() {
        try {
            empresaController = new EmpresaController();
        } catch (Exception ex) {
            fail("Se ha producido un error al crear el controlador", ex);
        }
    }

    @BeforeEach
    public void insertarTest() {
        try {
            empresaController.insertar(empresa1);
            assertNotNull(empresaController.buscar(empresa1.getCif()));
        } catch (Exception ex) {
            fail("No se ha podido insertar la empresa", ex);
        }
    }

    @AfterEach
    public void eliminarTest() {
        try {
            empresaController.eliminar(empresa1);
            assertNull(empresaController.buscar(empresa1.getCif()));
        } catch (Exception ex) {
            fail("No se ha podido eliminar la empresa", ex);
        }
    }

    @Test
    public void insertarErrorTest() {
        try {
            empresaController.insertar(empresa1);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La empresa ya existe"));
        }
    }

    @Test
    public void eliminarErrorTest() {
        try {
            empresaController.eliminar(empresa3);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("La empresa no existe"));
        }
    }

    @Test
    public void modificar() {
        try {
            empresaController.modificar(empresa2);
            assertEquals(empresaController.buscar(empresa1.getCif()).getCorreo(), empresa2.getCorreo());
        } catch (Exception ex) {
            fail("No se ha podido modificar", ex);
        }
    }

    @Test
    public void modificarErrorTest() {
        try {
            empresaController.modificar(empresa3);
        } catch (Exception ex) {
            assertTrue((ex.getMessage().contains("La empresa no existe")));
        }
    }

    @Test
    public void validarEmpresaTest(){
    try {
        empresaController.validar(empresa1);
    } catch (Exception ex) {
        fail("No se deberia haber generado una excepcion");
    }
    try {
        empresaController.validar(null);

    } catch(Exception ex){
        assertTrue(ex.getMessage().contains("La empresa no puede ser nula"));
    } 
    try {
        empresaController.validar(new Empresa(null,  null, null, null, null));
    } catch(Exception ex){
        assertTrue(ex.getMessage().contains("El cif de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El nombre de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("La direccion de la empresa no puede ser nula"));
        assertTrue(ex.getMessage().contains("El telefono de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El correo de la empresa no puede ser nulo"));
    }
    try {
        empresaController.validar(new Empresa("A", "", "", "92245678", "A"));
    } catch(Exception ex){
        assertTrue(ex.getMessage().contains("El cif de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El nombre de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("La direccion de la empresa no puede ser nula"));
        assertTrue(ex.getMessage().contains("El telefono de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El correo de la empresa no puede ser nulo"));
    }
}
}
