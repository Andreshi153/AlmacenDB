import es.iespuertolacruz.almacen.api.Producto;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.ProductoModelo;

public class ProductoController {
    
    ProductoModelo productoModelo;

    public ProductoController() throws BbddException, FicheroException, SQLException {
        productoModelo = new ProductoModelo();
    }

    public String test() throws BbddException {
        return productoModelo.test();
    }

    public void validarProducto(Producto producto) throws AlmacenException {
        String mensaje = "";
        if (producto == null) {
            throw new AlmacenException("El producto no puede ser nulo");
        }
        if (producto.getIdProducto() < 1) {
            mensaje += "El id del producto no puede ser inferior a 1\n";
        }
        if (producto.getNombre().isEmpty() || producto.getNombre() == null) {
            mensaje += "El nombre del producto no puede ser nulo o vacio\n";
        }
        if (producto.getPrecioUnitario() <= 0) {
            mensaje += "El precio del producto no puede ser menor o igual que 0\n";
        }
        if (producto.getTipo().isEmpty() || producto.getTipo() == null) {
            mensaje += "El tipo del producto no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

     //CRUD producto

    /**
     * Metodo que inserta un producto en la bbdd
     * @param producto a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Producto producto) throws BbddException, AlmacenException {
        validarProducto(producto);
        productoModelo.insertar(producto);
    }
    /**
     * Metodo que elimina un producto de la bbdd
     * @param producto a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Producto producto) throws BbddException, AlmacenException {
        validarProducto(producto);
        productoModelo.eliminar(producto);
    }
    /**
     * Metodo que modifica un producto de la bbdd
     * @param producto a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Producto producto) throws BbddException, AlmacenException {
        validarProducto(producto);
        productoModelo.modificar(producto);
    }
    /**
     * Metodo que busca un producto en la bbdd
     * @param idProducto del producto a buscar
     * @throws BbddException controlado
     */
    public Producto obtenerProducto(int idProducto) throws BbddException {
        return productoModelo.obtenerProducto(idProducto);
    }


}
