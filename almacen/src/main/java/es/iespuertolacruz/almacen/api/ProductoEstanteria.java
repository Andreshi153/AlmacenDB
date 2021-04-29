package es.iespuertolacruz.almacen.api;

public class ProductoEstanteria {
    String idProducto;
    String idEstanteria;
    int cantidad;


    public ProductoEstanteria(String idProducto, String idEstanteria, int cantidad) {
        this.idProducto = idProducto;
        this.idEstanteria = idEstanteria;
        this.cantidad = cantidad;
    }

}
