package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.ProductoEstanteriaModelo;
public class ProductoEstanteria {
    
   ProductoEstanteriaModelo productoEstanteriaModelo;

    public void validarProductoEstanteria(ProductoEstanteria productoEstanteria) throws AlmacenException {
        String mensaje = "";
        if (productoEstanteria == null) {
            throw new AlmacenException("El productoEstanteria no puede ser nulo");
        }
        if (productoEstanteria.getIdProducto() <= 0) {
            mensaje += "El id del producto no puede ser menor o igual que 0\n";
        }
        if (productoEstanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (productoEstanteria.getCantidad() <= 0) {
            mensaje += "La cantidad de productos no puede ser menor o igual que 0\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    
    /**
     * Metodo que inserta un productoEstanteria en la bbdd
     * @param productoEstanteria a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validarProductoEstanteria(productoEstanteria);
        productoEstanteriaModelo.insertar(productoEstanteria);
    }
    /**
     * Metodo que elimina un productoEstanteria de la bbdd
     * @param productoEstanteria a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validarProductoEstanteria(productoEstanteria);
        productoEstanteriaModelo.eliminar(productoEstanteria);
    }
    /**
     * Metodo que modifica un productoEstanteria de la bbdd
     * @param productoEstanteria a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validarProductoEstanteria(productoEstanteria);
        productoEstanteriaModelo.modificar(productoEstanteria);
    }
    /**
     * Metodo que busca un productoEstanteria en la bbdd
     * @param idProducto del productoEstanteria a buscar
     * @param idEstanteria del productoEstanteria a buscar
     * @throws BbddException controlado
     */
    public ProductoEstanteria obtenerProductoEstanteria(int idProducto, int idEstanteria) throws BbddException {
        return productoEstanteriaModelo.obtenerProductoEstanteria(idProducto, idEstanteria);
    }



}
