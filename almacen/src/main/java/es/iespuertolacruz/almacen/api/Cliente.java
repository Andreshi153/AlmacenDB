package es.iespuertolacruz.almacen.api;

public class Cliente extends Empresa {
    float porcentajeDesc;

    public Cliente(String cif, String nombre, String direccion, String telefono, String correo, float porcentajeDesc) {
        super(cif, nombre, direccion, telefono, correo);
        this.porcentajeDesc = porcentajeDesc;
    }

}
