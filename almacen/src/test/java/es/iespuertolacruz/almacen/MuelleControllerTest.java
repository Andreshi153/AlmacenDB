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

import es.iespuertolacruz.almacen.api.Muelle;
import es.iespuertolacruz.almacen.controlador.MuelleController;

public class MuelleControllerTest {

    static MuelleController muelleController;
    Muelle muelle1 = new Muelle(1, 'A' , true);
    Muelle muelle2 = new Muelle(1, 'B' , true);
    Muelle muelle3 = new Muelle(2, 'C', false);

@BeforeAll
public static void setUp(){
    try{
        muelleController = new MuelleController(); 
    
    }catch(Exception ex){
        fail("Se ha producido un error al crear el controlador" , ex);
    }
}

@BeforeEach
public void insertarTest(){
    try{
        muelleController.insertar(muelle1);
        assertNotNull(muelleController.buscar(muelle1.getIdMuelle()));
    }catch(Exception ex){
        fail("No se ha podido insertar el muelle", ex);
    }
}

@AfterEach
public void eliminarTest(){
    try{
        muelleController.eliminar(muelle1);
        assertNull(muelleController.buscar(muelle1.getIdMuelle()));
    }catch(Exception ex){
        fail("No se ha podido eliminar el muelle", ex);
    }
}

@Test
public void insertarErrorTest(){
    try{
        muelleController.insertar(muelle1);
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El muelle existe"));
    }
}

@Test
public void eliminarErrorTest(){
try {
    muelleController.eliminar(muelle3);
} catch (Exception ex) {
    assertTrue(ex.getMessage().contains("El muelle no existe"));
}
}

@Test
public void modificar(){
try{
    muelleController.modificar(muelle2);
    assertEquals(muelleController.buscar(muelle1.getIdMuelle()).getIdZona(), muelle2.getIdZona());
}catch(Exception ex){
    fail("No se ha podido modificar", ex);
}
}

@Test
public void modificarErrorTest(){
try{
    muelleController.modificar(muelle3);
}catch(Exception ex){
    assertTrue(ex.getMessage().contains("El muelle no existe"));
}
}

@Test
public void validarMuelleTest(){
    try{
        muelleController.validar(null);

    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El muelle no puede ser nulo"));
    }
    try{
        muelleController.validar(new Muelle(0,  ' ', true));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El id del muelle no puede ser cero o menor"));
        assertTrue(ex.getMessage().contains("El id de la zona no puede ser nulo"));
    }
    try{
        muelleController.validar(new Muelle(0, 'A', false));
    }catch(Exception ex){
        assertTrue(ex.getMessage().contains("El id del muelle no puede ser cero o menor" ));
    }
}



}
