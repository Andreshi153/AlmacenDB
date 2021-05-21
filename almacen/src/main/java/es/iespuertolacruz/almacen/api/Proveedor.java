package es.iespuertolacruz.almacen.api;

public class Proveedor {

    String cif;
    String tipoProducto;

    /**
     * Constructor de la clase
     * @param cif del proveedor
     * @param tipoProducto del proveedor
     */
    public Proveedor(String cif, String tipoProducto) {
        this.cif = cif;
        this.tipoProducto = tipoProducto;
    }

    /**
     * Funcion getter del cif del proveedor
     * @return cif del proveedor
     */
    public String getCif() {
        return this.cif;
    }
    
    /**
     * Funcion getter del tipoProducto del proveedor
     * @return tipoProducto del proveedor
     */
    public String getTipoProducto() {
        return this.tipoProducto;
    }
}
