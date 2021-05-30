package es.iespuertolacruz.almacen.controlador;

import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Producto;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.ProductoModelo;

public class ProductoController {
    
    ProductoModelo productoModelo;

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public ProductoController() throws BbddException, FicheroException {
        productoModelo = new ProductoModelo();
    }

    /**
     * Funcion que verifica si existe un producto en la bbdd
     * @param producto a verificar
     * @return true/false existe o no
     * @throws BbddException controlado
     */
    private boolean existe(Producto producto) throws BbddException {
        return buscar(producto.getIdProducto()) != null;
    }

    /**
     * Funcion que valida un producto
     * @param producto a validar
     * @throws AlmacenException controlado
     */
    public void validar(Producto producto) throws AlmacenException {
        String mensaje = "";
        if (producto == null) {
            throw new AlmacenException("El producto no puede ser nulo");
        }
        if (producto.getIdProducto() < 1) {
            mensaje += "El id del producto no puede ser inferior a 1\n";
        }
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            mensaje += "El nombre del producto no puede ser nulo o vacio\n";
        }
        if (producto.getPrecioUnitario() <= 0) {
            mensaje += "El precio del producto no puede ser menor o igual que 0\n";
        }
        if (producto.getTipo() == null || !Validaciones.validarTipoProducto(producto.getTipo())) {
            mensaje += "El tipo del producto no puede ser nulo o no valido\n";
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
        validar(producto);
        if(!existe(producto)) productoModelo.insertar(producto);
        else throw new AlmacenException("El producto ya existe en la base de datos");
    }
    /**
     * Metodo que elimina un producto de la bbdd
     * @param producto a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Producto producto) throws BbddException, AlmacenException {
        validar(producto);
        if(existe(producto)) productoModelo.eliminar(producto);
        else throw new AlmacenException("El producto no existe en la base de datos");
    }
    /**
     * Metodo que modifica un producto de la bbdd
     * @param producto a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Producto producto) throws BbddException, AlmacenException {
        validar(producto);
        if(existe(producto)) productoModelo.modificar(producto);
        else throw new AlmacenException("El producto no existe en la base de datos");
    }
    /**
     * Metodo que busca un producto en la bbdd
     * @param idProducto del producto a buscar
     * @throws BbddException controlado
     */
    public Producto buscar(int idProducto) throws BbddException {
        return productoModelo.buscar(String.valueOf(idProducto));
    }

    /**
     * Funcion que busca todos los productos de la bbdd
     * @return arraylist de productos
     * @throws BbddException controlado
     */
    public ArrayList<Producto> buscarTodos() throws BbddException {
        return productoModelo.buscarTodos();
    }

    /**
     * Funcion que retorna el id del ultimo producto insertado
     * @return id producto
     * @throws BbddException controlado
     */
    public int obtenerIdUltimo() throws BbddException {
        return productoModelo.obtenerIdUltimo();
    }
}
