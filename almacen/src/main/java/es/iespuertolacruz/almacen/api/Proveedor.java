package es.iespuertolacruz.almacen.api;

public class Proveedor extends Empresa {

    String tipoProducto;

    public Proveedor(String cif, String nombre, String direccion, String telefono, String correo, String tipoProducto) {
        super(cif, nombre, direccion, telefono, correo);
        this.tipoProducto = tipoProducto;
    }
}
