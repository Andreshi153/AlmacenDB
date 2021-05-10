package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.Producto;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.modelo.AlmacenModelo;

public class AlmacenController {
    
    AlmacenModelo almacenModelo;

    public AlmacenController() {
        almacenModelo = new AlmacenModelo();
    }

    public void validarProducto(Producto producto) throws AlmacenException {
        String mensaje = "";
        if (producto == null) {
            throw new AlmacenException("El producto no puede ser nulo");
        }
        if (producto.getNombre().isEmpty() || producto.getNombre() == null) {
            mensaje += "El nombre del producto no puede ser nulo o vacio";
        }
        if (producto.getTipo().isEmpty() || producto.getTipo() == null) {
            mensaje += "El tipo del producto no puede ser nulo o vacio";
        }
        if (producto.getIdProducto() < 1) {
            mensaje += "El id del producto no puede ser inferior a 1";
        }
        if (producto.getPrecioUnitario() <= 0) {
            mensaje += "El precio del producto no puede ser menor o igual que 0";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }
}
