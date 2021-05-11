package es.iespuertolacruz.almacen.api;

public class Operacion {
    int idListaProductos;
    int idMuelle;
    String fecha;
    String tipoOperacion;
    String cif;


    public Operacion(int idListaProductos, int idMuelle, String fecha, String tipoOperacion, String cif) {
        this.idListaProductos = idListaProductos;
        this.idMuelle = idMuelle;
        this.fecha = fecha;
        this.tipoOperacion = tipoOperacion;
        this.cif = cif;
    }

    public int getIdListaProductos() {
        return this.idListaProductos;
    }

    public int getIdMuelle() {
        return this.idMuelle;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getTipoOperacion() {
        return this.tipoOperacion;
    }

    public String getCif() {
        return this.cif;
    }

}