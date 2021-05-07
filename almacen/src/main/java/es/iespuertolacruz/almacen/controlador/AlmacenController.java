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
        if (producto.getIdProducto().isEmpty() || producto.getIdProducto() == null) {
            mensaje += "El id del producto no puede ser nulo o vacio";
        }
        if (producto.getMaxProducto() < 1) {
            mensaje += "La cantidad maxima del producto no puede ser menor que 0";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }
}
