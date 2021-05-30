package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.ListaProductos;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.ListaProductosModelo;

public class ListaProductosController {
    
    ListaProductosModelo listaProductosModelo;
    ProductoController productoController;

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public ListaProductosController() throws BbddException, FicheroException {
        productoController = new ProductoController();
        listaProductosModelo = new ListaProductosModelo();
    }

    /**
     * Funcion que verifica si existe una listaProductos en la bbdd
     * @param listaProductos a verificar
     * @return true/false existe o no
     * @throws BbddException controlado
     */
    private boolean existe(ListaProductos listaProductos) throws BbddException {
        return buscar(listaProductos.getIdListaProductos()) != null;
    }

    /**
     * Funcion que valida una listaProductos
     * @param listaProductos a validar
     * @throws AlmacenException controlado
     */
    public void validar(ListaProductos listaProductos) throws AlmacenException {
        String mensaje = "";
        if (listaProductos == null) {
            throw new AlmacenException("La listaProductos no puede ser nula");
        }
        if (listaProductos.getIdListaProductos() <= 0) {
            mensaje += "El id de la listaProductos no puede ser menor o igual que 0\n";
        }
        if (listaProductos.getLista() == null || listaProductos.getLista().isEmpty()) {
            mensaje += "La lista de productos de listaProductos no puede estar vacia o ser nula\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

      //CRUD listaProductos

    /**
     * Metodo que inserta un listaProductos en la bbdd
     * @param listaProductos a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validar(listaProductos);
        if(!existe(listaProductos)) listaProductosModelo.insertar(listaProductos);
        else throw new AlmacenException("La listaProductos ya existe en la base de datos");
    }
    /**
     * Metodo que elimina un listaProductos de la bbdd
     * @param listaProductos a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validar(listaProductos);
        if(existe(listaProductos)) listaProductosModelo.eliminar(listaProductos);
        else throw new AlmacenException("La listaProductos no existe en la base de datos");
    }
    /**
     * Metodo que modifica un listaProductos de la bbdd
     * @param listaProductos a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validar(listaProductos);
        if(existe(listaProductos)) listaProductosModelo.modificar(listaProductos);
        else throw new AlmacenException("La listaProductos no existe en la base de datos");
    }
    /**
     * Metodo que busca un listaProductos en la bbdd
     * @param idProducto del listaProductos a buscar
     * @throws BbddException controlado
     */
    public ListaProductos buscar(int idListaProductos) throws BbddException {
        return listaProductosModelo.buscar(String.valueOf(idListaProductos));
    }

    /**
     * Funcion que obtiene el id mas alto de la lista de productos en la bbdd
     * @return idproducto mas alto
     * @throws BbddException controlado
     */
    public int obtenerMaxIdListaProductos() throws BbddException {
        return listaProductosModelo.obtenerMaxIdListaProductos();
    }
}
