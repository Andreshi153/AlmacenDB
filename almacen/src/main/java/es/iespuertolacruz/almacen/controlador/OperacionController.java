package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.OperacionModelo;
public class OperacionController {
    OperacionModelo operacionModelo;

    public void validarOperacion(Operacion operacion) throws AlmacenException {
        String mensaje = "";
        if (operacion == null) {
            throw new AlmacenException("La operacion no puede ser nula");
        }
        if (operacion.getIdListaProductos() <= 0) {
            mensaje += "El id de la lista de productos no puede ser menor o igual que 0\n";
        }
        if (operacion.getIdMuelle() <= 0) {
            mensaje += "El id del muelle no puede ser menor o igual que 0\n";
        }
        if (operacion.getFecha().isEmpty() || operacion.getFecha() == null) {
            mensaje += "La fecha de la operacion no puede ser nula o vacia\n";
        }
        if (operacion.getTipoOperacion().isEmpty() || operacion.getTipoOperacion() == null) {
            mensaje += "El tipo de la operacion no puede ser nulo o vacio\n";
        }
        if (operacion.getCif().isEmpty() || operacion.getCif() == null) {
            mensaje += "El cif de la operacion no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

     /**
     * Metodo que inserta una operacion en la bbdd
     * @param operacion a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Operacion operacion) throws BbddException, AlmacenException {
        validarOperacion(operacion);
        operacionModelo.insertar(operacion);
    }
    /**
     * Metodo que elimina una operacion de la bbdd
     * @param operacion a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Operacion operacion) throws BbddException, AlmacenException {
        validarOperacion(operacion);
        operacionModelo.eliminar(operacion);
    }
    /**
     * Metodo que modifica una operacion de la bbdd
     * @param operacion a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Operacion operacion) throws BbddException, AlmacenException {
        validarOperacion(operacion);
        operacionModelo.modificar(operacion);
    }
    /**
     * Metodo que busca una operacion en la bbdd
     * @param idListaProductos de la operacion a buscar
     * @throws BbddException controlado
     */
    public Operacion obtenerOperacion(int idListaProductos) throws BbddException {
        return operacionModelo.obtenerOperacion(idListaProductos);
    }
}
