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

import es.iespuertolacruz.almacen.api.Proveedor;
import es.iespuertolacruz.almacen.controlador.ProveedorController;

public class ProveedorControllerTest {
    
    static ProveedorController proveedorController;
    Proveedor proveedor = new Proveedor("A1", "Normal");
    Proveedor proveedor2 = new Proveedor("A1", "Frio");
    Proveedor proveedor3 = new Proveedor("B2", "Congelado");

    @BeforeAll
public static void setUp(){
    try{
        proveedorController = new ProveedorController(); 
    
    }catch(Exception ex){
        fail("Se ha producido un error al crear el controlador" , ex);
    }
}

@BeforeEach
public void insertarTest(){
    try{
        proveedorController.insertar(proveedor);
        assertNotNull(proveedorController.buscar(proveedor.getCif()));
    }catch(Exception ex){
        fail("No se ha podido insertar al proveedor", ex);
    }
}

@AfterEach
public void eliminarTest(){
    try{
        proveedorController.eliminar(proveedor);
        assertNull(proveedorController.buscar(proveedor.getCif()));
    }catch(Exception ex){
        fail("No se ha podido eliminar al proveedor", ex);
    }
}

@Test
public void insertarErrorTest(){
    try{
        proveedorController.insertar(proveedor);
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El proveedor existe"));
    }
}

@Test
public void eliminarErrorTest(){
try {
    proveedorController.eliminar(proveedor);
} catch (Exception ex) {
    assertTrue(ex.getMessage().contains("El proveedor no existe"));
}
}

@Test
public void modificar(){
try{
    proveedorController.modificar(proveedor2);
    assertEquals(proveedorController.buscar(proveedor.getCif()).getTipoProducto(), proveedor2.getTipoProducto());
}catch(Exception ex){
    fail("No se ha podido modificar", ex);
}
}

@Test
public void modificarErrorTest(){
try{
    proveedorController.modificar(proveedor3);
}catch(Exception ex){
    assertTrue((ex.getMessage().contains("El proveedor no existe")));
}
}

@Test
public void validarEmpresaTest(){
    try{
        proveedorController.validar(null);

    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El proveedor no puede ser nulo"));
    }
    try{
        proveedorController.validar(new Proveedor(null, null));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El cif del proveedor no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El tipo de producto no puede ser nulo"));
        
    }
    try{
        proveedorController.validar(new Proveedor("", "Normal"));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El cif no puede ser nulo" ));
    }
}




}

