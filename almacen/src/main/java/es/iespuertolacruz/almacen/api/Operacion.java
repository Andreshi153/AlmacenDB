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

}