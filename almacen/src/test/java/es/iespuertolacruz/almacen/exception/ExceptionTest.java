package es.iespuertolacruz.almacen.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExceptionTest {
    
    Exception exception = new NullPointerException();
    String mensaje = "test";

    @Test
    public void bbddExceptionTest() {
        try {
            throw new BbddException(mensaje);
        } catch (BbddException ex) {
            assertTrue(ex.getMessage().contains(mensaje));
        }
        try {
            throw new BbddException(mensaje, exception);
        } catch (BbddException ex) {
            assertTrue(ex.getMessage().contains(mensaje));
            assertTrue(ex.getCause().toString().contains(exception.toString()));
        }
    }

    @Test
    public void ficheroExceptionTest() {
        try {
            throw new FicheroException(mensaje);
        } catch (FicheroException ex) {
            assertTrue(ex.getMessage().contains(mensaje));
        }
        try {
            throw new FicheroException(mensaje, exception);
        } catch (FicheroException ex) {
            assertTrue(ex.getMessage().contains(mensaje));
            assertTrue(ex.getCause().toString().contains(exception.toString()));
        }
    }

    @Test
    public void almacenExceptionTest() {
        try {
            throw new AlmacenException(mensaje);
        } catch (AlmacenException ex) {
            assertTrue(ex.getMessage().contains(mensaje));
        }
        try {
            throw new AlmacenException(mensaje, exception);
        } catch (AlmacenException ex) {
            assertTrue(ex.getMessage().contains(mensaje));
            assertTrue(ex.getCause().toString().contains(exception.toString()));
        }
    }
}
