package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.ProductoEstanteria;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.ProductoEstanteriaModelo;

public class ProductoEstanteriaController {
    
   ProductoEstanteriaModelo productoEstanteriaModelo;
   ProductoController productoController;
   EstanteriaController estanteriaController;

    public ProductoEstanteriaController() throws BbddException, FicheroException {
        productoController = new ProductoController();
        estanteriaController = new EstanteriaController();
        productoEstanteriaModelo = new ProductoEstanteriaModelo();
    }

    private boolean existe(ProductoEstanteria productoEstanteria) throws BbddException {
        return buscar(productoEstanteria.getIdProducto(), productoEstanteria.getIdEstanteria()) != null;
    }

    public void validar(ProductoEstanteria productoEstanteria) throws AlmacenException {
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
        validar(productoEstanteria);
        if(!existe(productoEstanteria)) productoEstanteriaModelo.insertar(productoEstanteria);
        else throw new AlmacenException("El producto estanteria ya existe en la base de datos");
    }
    /**
     * Metodo que elimina un productoEstanteria de la bbdd
     * @param productoEstanteria a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validar(productoEstanteria);
        if(existe(productoEstanteria)) productoEstanteriaModelo.eliminar(productoEstanteria);
        else throw new AlmacenException("El producto estanteria no existe en la base de datos");
    }
    /**
     * Metodo que modifica un productoEstanteria de la bbdd
     * @param productoEstanteria a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validar(productoEstanteria);
        if(existe(productoEstanteria)) productoEstanteriaModelo.modificar(productoEstanteria);
        else throw new AlmacenException("El producto estanteria no existe en la base de datos");
    }
    /**
     * Metodo que busca un productoEstanteria en la bbdd
     * @param idProducto del productoEstanteria a buscar
     * @param idEstanteria del productoEstanteria a buscar
     * @throws BbddException controlado
     */
    public ProductoEstanteria buscar(int idProducto, int idEstanteria) throws BbddException {
        return productoEstanteriaModelo.buscar(String.valueOf(idProducto), String.valueOf(idEstanteria));
    }
    
    public double obtenerValorProductosTotal() throws BbddException {
        return productoEstanteriaModelo.obtenerValorProductosTotal();
    }
    public int obtenerHuecosOcupados() throws BbddException {
        return productoEstanteriaModelo.obtenerHuecosOcupados();
    }
    public int obtenerHuecosTotales() throws BbddException {
        return productoEstanteriaModelo.obtenerHuecosTotales();
    }

    /**
     * Funcion que obtiene los huecos totales y los ocupados del almacen
     * 
     * @return [huecos ocupados, huecos totales]
     * @throws BbddException controlado
     */
    public Integer[] obtenerHuecos() throws BbddException {
        return new Integer[] {obtenerHuecosOcupados(), obtenerHuecosTotales()};
    }
}
