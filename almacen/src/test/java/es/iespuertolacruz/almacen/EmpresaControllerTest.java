package es.iespuertolacruz.almacen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import es.iespuertolacruz.almacen.api.Empresa;
import es.iespuertolacruz.almacen.controlador.EmpresaController;

public class EmpresaControllerTest {
    static EmpresaController empresaController;
    Empresa empresa1 = new Empresa("1A", "A", "A", "922456789", "A");
    Empresa empresa2 = new Empresa("1A", "B", "B", "922111111", "B");
    Empresa empresa3 = new Empresa("2B", "C", "C", "922333444", "C");

    @BeforeAll
public static void setUp(){
    try{
        empresaController = new EmpresaController(); 
    
    }catch(Exception ex){
        fail("Se ha producido un error al crear el controlador" , ex);
    }
}

@BeforeEach
public void insertarTest(){
    try{
        empresaController.insertar(empresa1);
        assertNotNull(empresaController.buscar(empresa1.getCif()));
    }catch(Exception ex){
        fail("No se ha podido insertar la empresa", ex);
    }
}

@AfterEach
public void eliminarTest(){
    try{
        empresaController.eliminar(empresa1);
        assertNull(empresaController.buscar(empresa1.getCif()));
    }catch(Exception ex){
        fail("No se ha podido eliminar la empresa", ex);
    }
}

@Test
public void insertarErrorTest(){
    try{
        empresaController.insertar(empresa1);
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("La empresa existe"));
    }
}

@Test
public void eliminarErrorTest(){
try {
    empresaController.eliminar(empresa3);
} catch (Exception ex) {
    assertTrue(ex.getMessage().contains("La empresa no existe"));
}
}

@Test
public void modificar(){
try{
    empresaController.modificar(empresa2);
    assertEquals(empresaController.buscar(empresa1.getCif()).getCorreo(), empresa2.getCorreo());
}catch(Exception ex){
    fail("No se ha podido modificar", ex);
}
}

@Test
public void modificarErrorTest(){
try{
    empresaController.modificar(empresa3);
}catch(Exception ex){
    assertTrue((ex.getMessage().contains("La empresa no existe")));
}
}

@Test
public void validarEmpresaTest(){
    try{
        empresaController.validar(null);

    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("La empresa no puede ser nulo"));
    }
    try{
        empresaController.validar(new Empresa(null,  null, null, null, null));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El cif de la empresa no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El nombre no puede ser nulo"));
        assertTrue(ex.getMessage().contains("La direccion no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El telefono no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El correo no puede ser nulo"));
    }
    try{
        empresaController.validar(new Empresa(" A1", "A", "A", "922456789", "A"));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El cif no puede ser nulo" ));
    }
}




}
