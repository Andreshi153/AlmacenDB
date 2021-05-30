package es.iespuertolacruz.almacen.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EmpresaTest {

    Empresa empresa = new Empresa("Y78989898", "A", "A", "922456789", "A@a.a");

    @Test
    public void toStringTest() {
        String informacion = empresa.toString();
        assertTrue(informacion.contains("-> CIF: " + empresa.getCif()));
        assertTrue(informacion.contains("· Nombre: " + empresa.getNombre()));
        assertTrue(informacion.contains("· Direccion: " + empresa.getDireccion()));
        assertTrue(informacion.contains("· Telefono: " + empresa.getTelefono()));
        assertTrue(informacion.contains("· Correo: " + empresa.getCorreo()));
    }
}
