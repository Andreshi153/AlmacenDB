package es.iespuertolacruz.almacen.api;

public class Cliente {

    String cif;
    float porcentajeDesc;

    public Cliente(String cif, float porcentajeDesc) {
        this.cif = cif;
        this.porcentajeDesc = porcentajeDesc;
    }

    public String getCif() {
        return this.cif;
    }
    public float getPorcentajeDesc() {
        return this.porcentajeDesc;
    }

}
