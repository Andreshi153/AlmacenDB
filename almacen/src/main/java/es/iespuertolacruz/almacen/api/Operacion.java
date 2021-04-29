package es.iespuertolacruz.almacen.api;

public class Operacion {
    String idListaProductos;
    String idMuelle;
    String fecha;
    String tipoOperacion;
    String cif;


    public Operacion(String idListaProductos, String idMuelle, String fecha, String tipoOperacion, String cif) {
        this.idListaProductos = idListaProductos;
        this.idMuelle = idMuelle;
        this.fecha = fecha;
        this.tipoOperacion = tipoOperacion;
        this.cif = cif;
    }

}