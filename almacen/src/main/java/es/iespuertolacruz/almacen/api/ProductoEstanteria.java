package es.iespuertolacruz.almacen.api;

public class ProductoEstanteria {
    int idProducto;
    int idEstanteria;
    int cantidad;


    public ProductoEstanteria(int idProducto, int idEstanteria, int cantidad) {
        this.idProducto = idProducto;
        this.idEstanteria = idEstanteria;
        this.cantidad = cantidad;
    }


    public int getIdProducto() {
        return this.idProducto;
    }

    public int getIdEstanteria() {
        return this.idEstanteria;
    }

    public int getCantidad() {
        return this.cantidad;
    }
    
}
