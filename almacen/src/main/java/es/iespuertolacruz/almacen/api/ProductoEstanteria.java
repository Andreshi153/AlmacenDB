package es.iespuertolacruz.almacen.api;

public class ProductoEstanteria {
    int idProducto;
    int idEstanteria;
    int cantidad;

    /**
     * Constructor de la clase
     * @param idProducto del productoEstanteria
     * @param idEstanteria del productoEstanteria
     * @param cantidad del productoEstanteria
     */
    public ProductoEstanteria(int idProducto, int idEstanteria, int cantidad) {
        this.idProducto = idProducto;
        this.idEstanteria = idEstanteria;
        this.cantidad = cantidad;
    }

    /**
     * Funcion getter del idProducto del productoEstanteria
     * @return idProducto del productoEstanteria
     */
    public int getIdProducto() {
        return this.idProducto;
    }

    /**
     * Funcion getter del idEstanteria del productoEstanteria
     * @return idEstanteria del productoEstanteria
     */
    public int getIdEstanteria() {
        return this.idEstanteria;
    }

    /**
     * Funcion getter de la cantidad del productoEstanteria
     * @return cantidad del productoEstanteria
     */
    public int getCantidad() {
        return this.cantidad;
    }
    
}
