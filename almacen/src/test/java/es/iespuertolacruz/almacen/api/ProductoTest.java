package es.iespuertolacruz.almacen.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ProductoTest {

    Producto producto = new Producto(600, "Bandera de Canarias", 5, "Normal");

    @Test
    public void toStringTest() {
        String informacion = producto.toString();
        assertTrue(informacion.contains("-> Id: " + producto.getIdProducto()));
        assertTrue(informacion.contains("-> Nombre: " + producto.getNombre()));
        assertTrue(informacion.contains("-> Precio unitario: " + producto.getPrecioUnitario()));
        assertTrue(informacion.contains("-> Tipo: " + producto.getTipo()));
    }
}
