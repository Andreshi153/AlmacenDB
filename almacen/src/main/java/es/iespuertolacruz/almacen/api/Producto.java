package es.iespuertolacruz.almacen.api;

public class Producto {
    String idProducto;
    String nombre;
    int maxProducto;
    String tipo;


    public Producto(String idProducto, String nombre, int maxProducto, String tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.maxProducto = maxProducto;
        this.tipo = tipo;
    }
}