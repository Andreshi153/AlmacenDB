package es.iespuertolacruz.almacen.api;

public class Proveedor {

    String cif;
    String tipoProducto;

    public Proveedor(String cif, String tipoProducto) {
        this.cif = cif;
        this.tipoProducto = tipoProducto;
    }

    public String getCif() {
        return this.cif;
    }
    
    public String getTipoProducto() {
        return this.tipoProducto;
    }
}
