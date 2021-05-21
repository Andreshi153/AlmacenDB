package es.iespuertolacruz.almacen.api;

public class Cliente {

    String cif;
    float porcentajeDesc;

    /**
     * Constructor de la clase
     * @param cif del cliente
     * @param porcentajeDesc del cliente
     */
    public Cliente(String cif, float porcentajeDesc) {
        this.cif = cif;
        this.porcentajeDesc = porcentajeDesc;
    }

    /**
     * Funcion getter del cif
     * @return cif del cliente
     */
    public String getCif() {
        return this.cif;
    }

    /**
     * Funcion getter del porcentaje de descuento
     * @return porcentaje de descuento del cliente
     */
    public float getPorcentajeDesc() {
        return this.porcentajeDesc;
    }

}
