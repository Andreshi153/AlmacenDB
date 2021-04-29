package es.iespuertolacruz.almacen.api;

public class ListaProducto {
   
    String idListaProductos;
    String idProducto;
    int cantidad;


    public ListaProducto(String idListaProductos, String idProducto, int cantidad) {
        this.idListaProductos = idListaProductos;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

}
