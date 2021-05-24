package es.iespuertolacruz.almacen.api;

public class Producto {
    int idProducto;
    String nombre;
    float precioUnitario;
    String tipo;

    /**
     * Constructor de la clase
     * @param idProducto del producto
     * @param nombre del producto
     * @param precioUnitario del producto
     * @param tipo del producto
     */
    public Producto(int idProducto, String nombre, float precioUnitario, String tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.tipo = tipo;
    }

    /**
     * Funcion getter del idProducto del producto
     * @return idProducto del producto
     */
    public int getIdProducto() {
        return this.idProducto;
    }

    /**
     * Funcion getter del nombre del producto
     * @return nombre del producto
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Funcion getter del precioUnitario del producto
     * @return precioUnitario del producto
     */
    public float getPrecioUnitario() {
        return this.precioUnitario;
    }

    /**
     * Funcion getter del tipo del producto
     * @return tipo del producto
     */
    public String getTipo() {
        return this.tipo;
    }

    @Override
    /**
     * Funcion toString del producto
     * @return informacion del producto
     */
    public String toString() {
        return "-> Id: " + this.idProducto + "\n-> Nombre: " + this.nombre + "\n-> Precio unitario: " + this.precioUnitario + "\n-> Tipo: " + this.tipo + "\n";
    }

}