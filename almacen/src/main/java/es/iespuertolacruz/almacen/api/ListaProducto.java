package es.iespuertolacruz.almacen.api;

public class ListaProducto {
    int idListaProductos;
    int idProducto;
    int cantidad;


    public ListaProducto(int idListaProductos, int idProducto, int cantidad) {
        this.idListaProductos = idListaProductos;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

}
