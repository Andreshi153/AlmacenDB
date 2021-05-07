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


    public String getIdProducto() {
        return this.idProducto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getMaxProducto() {
        return this.maxProducto;
    }

    public String getTipo() {
        return this.tipo;
    }

}