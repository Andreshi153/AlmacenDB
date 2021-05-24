package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.ClienteModelo;
public class ClienteController {
    
    ClienteModelo clienteModelo;
    
    public void validarCliente(Cliente cliente) throws AlmacenException {
        String mensaje = "";
        if (cliente == null) {
            throw new AlmacenException("El cliente no puede ser nulo");
        }
        if (cliente.getCif().isEmpty() || cliente.getCif() == null) {
            mensaje += "El cif del cliente no puede ser nulo o vacio\n";
        }
        if (cliente.getPorcentajeDesc() < 0) {
            mensaje += "El porcentaje de descuento del cliente no puede ser menor que 0\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

     /**
     * Metodo que inserta un cliente en la bbdd
     * @param cliente a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Cliente cliente) throws BbddException, AlmacenException {
        validarCliente(cliente);
        clienteModelo.insertar(cliente);
    }
    /**
     * Metodo que elimina un cliente de la bbdd
     * @param cliente a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Cliente cliente) throws BbddException, AlmacenException {
        validarCliente(cliente);
        clienteModelo.eliminar(cliente);
    }
    /**
     * Metodo que modifica un cliente de la bbdd
     * @param cliente a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Cliente cliente) throws BbddException, AlmacenException {
        validarCliente(cliente);
        clienteModelo.modificar(cliente);
    }
    /**
     * Metodo que busca un cliente en la bbdd
     * @param cif del cliente a buscar
     * @throws BbddException controlado
     */
    public Cliente obtenerCliente(String cif) throws BbddException {
        return clienteModelo.obtenerCliente(cif);
    }
    /**
     * Funcion que devuelve el listado de clientes
     * @return arraylist de clientes
     * @throws BbddException
     */
    public ArrayList<Cliente> obtenerListadoCliente() throws BbddException {
        return clienteModelo.obtenerListadoCliente();
    }
}
