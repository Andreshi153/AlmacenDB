package es.iespuertolacruz.almacen.controlador;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidacionesTest {

    @Test
    public void validarZona() {
        assertTrue(Validaciones.validarZona('A'));
        assertFalse(Validaciones.validarZona('a'));
        assertFalse(Validaciones.validarZona('1'));
    }

    @Test
    public void validarCif() {
        assertTrue(Validaciones.validarCif("A11111111"));
        assertFalse(Validaciones.validarCif("A1111111"));
        assertFalse(Validaciones.validarCif("11111111A"));
    }

    @Test
    public void validarTipoProducto() {
        assertTrue(Validaciones.validarTipoProducto("Normal"));
        assertTrue(Validaciones.validarTipoProducto("Frio"));
        assertTrue(Validaciones.validarTipoProducto("Congelados"));
        assertFalse(Validaciones.validarTipoProducto("Prueba"));
    }

    @Test
    public void validarTelefono() {
        assertTrue(Validaciones.validarTelefono("999999999"));
        assertFalse(Validaciones.validarTelefono("999 999 999"));
        assertFalse(Validaciones.validarTelefono("999"));
    }

    @Test
    public void validarCorreo() {
        assertTrue(Validaciones.validarCorreo("a@a.a"));
        assertFalse(Validaciones.validarCorreo("aaa@aaa"));
        assertFalse(Validaciones.validarTelefono("a.a"));
    }
}
