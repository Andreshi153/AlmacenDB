package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.AlmacenModelo;

public class AlmacenController {
    
    private static final String EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z = "El id de la zona debe estar entre la A y la Z\n";
    AlmacenModelo almacenModelo;

    public AlmacenController() throws BbddException, FicheroException {
        almacenModelo = new AlmacenModelo();
    }

    public void validarProducto(Producto producto) throws AlmacenException {
        String mensaje = "";
        if (producto == null) {
            throw new AlmacenException("El producto no puede ser nulo");
        }
        if (producto.getIdProducto() < 1) {
            mensaje += "El id del producto no puede ser inferior a 1\n";
        }
        if (producto.getNombre().isEmpty() || producto.getNombre() == null) {
            mensaje += "El nombre del producto no puede ser nulo o vacio\n";
        }
        if (producto.getPrecioUnitario() <= 0) {
            mensaje += "El precio del producto no puede ser menor o igual que 0\n";
        }
        if (producto.getTipo().isEmpty() || producto.getTipo() == null) {
            mensaje += "El tipo del producto no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarZona(Zona zona) throws AlmacenException {
        String mensaje = "";
        if (zona == null) {
            throw new AlmacenException("La zona no puede ser nula");
        }
        if (zona.getIdZona() < 'A' || zona.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if (zona.getTipo().isEmpty() || zona.getTipo() == null) {
            mensaje += "El tipo de la zona no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarEstanteria(Estanteria estanteria) throws AlmacenException {
        String mensaje = "";
        if (estanteria == null) {
            throw new AlmacenException("La estanteria no puede ser nula");
        }
        if (estanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (estanteria.getIdZona() < 'A' || estanteria.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if (estanteria.getNumAlturas() <= 0) {
            mensaje += "El numero de alturas de la estanteria no puede ser menor o igual que 0\n";
        }
        if (estanteria.getNumHuecosOcupados() > estanteria.getNumAlturas()) {
            mensaje += "El numero de huecos ocupados de la estanteria no puede ser mayor que el numero de alturas\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarProductoEstanteria(ProductoEstanteria productoEstanteria) throws AlmacenException {
        String mensaje = "";
        if (productoEstanteria == null) {
            throw new AlmacenException("El productoEstanteria no puede ser nulo");
        }
        if (productoEstanteria.getIdProducto() <= 0) {
            mensaje += "El id del producto no puede ser menor o igual que 0\n";
        }
        if (productoEstanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (productoEstanteria.getCantidad() <= 0) {
            mensaje += "La cantidad de productos no puede ser menor o igual que 0\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarListaProductos(ListaProductos listaProductos) throws AlmacenException {
        String mensaje = "";
        if (listaProductos == null) {
            throw new AlmacenException("La listaProductos no puede ser nula");
        }
        if (listaProductos.getIdListaProductos() <= 0) {
            mensaje += "El id de la listaProductos no puede ser menor o igual que 0\n";
        }
        if (listaProductos.getLista().isEmpty()) {
            mensaje += "La lista de productos de listaProductos no puede estar vacia\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarMuelle(Muelle muelle) throws AlmacenException {
        String mensaje = "";
        if (muelle == null) {
            throw new AlmacenException("El muelle no puede ser nula");
        }
        if (muelle.getIdMuelle() <= 0) {
            mensaje += "El id del muelle no puede ser menor o igual que 0\n";
        }
        if (muelle.getIdZona() < 'A' || muelle.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarEmpresa(Empresa empresa) throws AlmacenException {
        String mensaje = "";
        if (empresa == null) {
            throw new AlmacenException("La empresa no puede ser nula");
        }
        if (empresa.getCif().isEmpty() || empresa.getCif() == null) {
            mensaje += "El cif de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getNombre().isEmpty() || empresa.getNombre() == null) {
            mensaje += "El nombre de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getDireccion().isEmpty() || empresa.getDireccion() == null) {
            mensaje += "La direccion de la empresa no puede ser nula o vacia\n";
        }
        if (empresa.getTelefono().isEmpty() || empresa.getTelefono() == null) {
            mensaje += "El telefono de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getCorreo().isEmpty() || empresa.getCorreo() == null) {
            mensaje += "El correo de la empresa no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarCliente(Cliente cliente) throws AlmacenException {
        String mensaje = "";
        if (cliente == null) {
            throw new AlmacenException("El cliente no puede ser nulo");
        }
        if (cliente.getCif().isEmpty() || cliente.getCif() == null) {
            mensaje += "El cif del cliente no puede ser nulo o vacio\n";
        }
        if (cliente.getPorcentajeDesc() < 0) {
            mensaje += "El porcentaje de descuento del cliente no puede ser menor que 0\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    public void validarProveedor(Proveedor proveedor) throws AlmacenException {
        String mensaje = "";
        if (proveedor == null) {
            throw new AlmacenException("El cliente no puede ser nulo");
        }
        if (proveedor.getCif().isEmpty() || proveedor.getCif() == null) {
            mensaje += "El cif del proveedor no puede ser nulo o vacio\n";
        }
        if (proveedor.getTipoProducto().isEmpty() || proveedor.getTipoProducto() == null) {
            mensaje += "El tipo de producto del proveedor no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }
    
    public void validarOperacion(Operacion operacion) throws AlmacenException {
        String mensaje = "";
        if (operacion == null) {
            throw new AlmacenException("La operacion no puede ser nula");
        }
        if (operacion.getIdListaProductos() <= 0) {
            mensaje += "El id de la lista de productos no puede ser menor o igual que 0\n";
        }
        if (operacion.getIdMuelle() <= 0) {
            mensaje += "El id del muelle no puede ser menor o igual que 0\n";
        }
        if (operacion.getFecha().isEmpty() || operacion.getFecha() == null) {
            mensaje += "La fecha de la operacion no puede ser nula o vacia\n";
        }
        if (operacion.getTipoOperacion().isEmpty() || operacion.getTipoOperacion() == null) {
            mensaje += "El tipo de la operacion no puede ser nulo o vacio\n";
        }
        if (operacion.getCif().isEmpty() || operacion.getCif() == null) {
            mensaje += "El cif de la operacion no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }
}
