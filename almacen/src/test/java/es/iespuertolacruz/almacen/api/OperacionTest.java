package es.iespuertolacruz.almacen.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OperacionTest {

    Operacion operacion = new Operacion(3, 4, "2020-10-10", "Salida", "C44444444");

    @Test
    public void toStringTest() {
        String informacion = operacion.toString();
        assertTrue(informacion.contains("-> Id lista de productos: " + operacion.getIdListaProductos()));
        assertTrue(informacion.contains("· CIF de la empresa asociada: " + operacion.getCif()));
        assertTrue(informacion.contains("· Muelle: " + operacion.getIdMuelle()));
        assertTrue(informacion.contains("· Fecha: " + operacion.getFecha()));
        assertTrue(informacion.contains("· Tipo de operacion: " + operacion.getTipoOperacion()));
    }
}