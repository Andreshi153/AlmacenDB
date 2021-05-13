package es.iespuertolacruz.almacen.api;

public class Producto {
    int idProducto;
    String nombre;
    float precioUnitario;
    String tipo;


    public Producto(int idProducto, String nombre, float precioUnitario, String tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.tipo = tipo;
    }


    public int getIdProducto() {
        return this.idProducto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public float getPrecioUnitario() {
        return this.precioUnitario;
    }

    public String getTipo() {
        return this.tipo;
    }

    @Override
    public String toString() {
        return "-> Id: " + this.idProducto + "\n-> Nombre: " + this.nombre + "\n-> Precio unitario: " + this.precioUnitario + "\n-> Tipo: " + this.tipo + "\n";
    }

}