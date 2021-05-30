package es.iespuertolacruz.almacen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.Zona;
import es.iespuertolacruz.almacen.controlador.ZonaController;

public class ZonaControllerTest {
    
    static ZonaController zonaController;
    Zona zona = new Zona('A', "Normal ");
    Zona zona2 = new Zona('A', "Frio");
    Zona zona3 = new Zona('B', "Congelados");
    
@BeforeAll
public static void setUp(){
    try{
        zonaController = new ZonaController(); 
    
    }catch(Exception ex){
        fail("Se ha producido un error al crear el controlador" , ex);
    }
}

@BeforeEach
public void insertarTest(){
    try{
        zonaController.insertar(zona);
        assertNotNull(zonaController.buscar(zona.getIdZona()));
    }catch(Exception ex){
        fail("No se ha podido insertar la zona", ex);
    }
}

@AfterEach
public void eliminarTest(){
    try{
        zonaController.eliminar(zona);
        assertNull(zonaController.buscar(zona.getIdZona()));
    }catch(Exception ex){
        fail("No se ha podido eliminar la zona", ex);
    }
}

@Test
public void insertarErrorTest(){
    try{
        zonaController.insertar(zona);
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("La zona existe"));
    }
}

@Test
public void eliminarErrorTest(){
try {
    zonaController.eliminar(zona3);
} catch (Exception ex) {
    assertTrue(ex.getMessage().contains("La zona no existe"));
}
}

@Test
public void modificar(){
try{
    zonaController.modificar(zona2);
    assertEquals(zonaController.buscar(zona.getIdZona()).getTipo(), zona2.getTipo());
}catch(Exception ex){
    fail("No se ha podido modificar", ex);
}
}

@Test
public void modificarErrorTest(){
try{
    zonaController.modificar(zona3);
}catch(Exception ex){
    assertTrue(ex.getMessage().contains("La zona no existe"));
}
}

@Test
public void validarMuelleTest(){
    try{
        zonaController.validar(null);

    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("La zona no puede ser nulo"));
    }
    try{
        zonaController.validar(new Zona( ' ',  null));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El id de la zona no puede ser nulo"));
        assertTrue(ex.getMessage().contains("El tipo no puede ser nulo"));
    }
    try{
        zonaController.validar(new Zona(' ', "A"));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El id de la zona no puede ser nulo" ));
    }
}



}
