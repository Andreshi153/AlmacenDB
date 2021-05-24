package es.iespuertolacruz.almacen.controlador;


import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.ListaProductoModelo;
public class ListaProductoController {
    
    ListaProductoModelo listaProductoModelo;

    public void validarListaProductos(ListaProductos listaProductos) throws AlmacenException {
        String mensaje = "";
        if (listaProductos == null) {
            throw new AlmacenException("La listaProductos no puede ser nula");
        }
        if (listaProductos.getIdListaProductos() <= 0) {
            mensaje += "El id de la listaProductos no puede ser menor o igual que 0\n";
        }
        if (listaProductos.getLista().isEmpty()) {
            mensaje += "La lista de productos de listaProductos no puede estar vacia\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

     /**
     * Metodo que inserta una listaProductos en la bbdd
     * @param listaProductos a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validarListaProductos(listaProductos);
        listaProductoModelo.insertar(listaProductos);
    }
    /**
     * Metodo que elimina una listaProductos de la bbdd
     * @param listaProductos a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validarListaProductos(listaProductos);
        listaProductoModelo.eliminar(listaProductos);
    }
    /**
     * Metodo que modifica una listaProductos de la bbdd
     * @param listaProductos a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validarListaProductos(listaProductos);
        listaProductoModelo.modificar(listaProductos);
    }
    /**
     * Metodo que busca una listaProductos en la bbdd
     * @param idListaProductos de la listaProductos a buscar
     * @throws BbddException controlado
     */
    public ListaProductos obtenerListaProductos(int idListaProductos) throws BbddException {
        return listaProductoModelo.obtenerListaProductos(idListaProductos);
    }


}
