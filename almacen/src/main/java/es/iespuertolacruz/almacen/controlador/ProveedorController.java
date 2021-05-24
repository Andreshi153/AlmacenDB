package es.iespuertolacruz.almacen.controlador;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.ProveedorModelo;
public class ProveedorController {
    
    ProveedorModelo proveedorModelo;

    public void validarProveedor(Proveedor proveedor) throws AlmacenException {
        String mensaje = "";
        if (proveedor == null) {
            throw new AlmacenException("El cliente no puede ser nulo");
        }
        if (proveedor.getCif().isEmpty() || proveedor.getCif() == null) {
            mensaje += "El cif del proveedor no puede ser nulo o vacio\n";
        }
        if (proveedor.getTipoProducto().isEmpty() || proveedor.getTipoProducto() == null) {
            mensaje += "El tipo de producto del proveedor no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

     /**
     * Metodo que inserta un proveedor en la bbdd
     * @param proveedor a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Proveedor proveedor) throws BbddException, AlmacenException {
        validarProveedor(proveedor);
        proveedorModelo.insertar(proveedor);
    }
    /**
     * Metodo que elimina un proveedor de la bbdd
     * @param proveedor a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Proveedor proveedor) throws BbddException, AlmacenException {
        validarProveedor(proveedor);
        proveedorModelo.eliminar(proveedor);
    }
    /**
     * Metodo que modifica un proveedor de la bbdd
     * @param proveedor a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Proveedor proveedor) throws BbddException, AlmacenException {
        validarProveedor(proveedor);
        proveedorModelo.modificar(proveedor);
    }
    /**
     * Metodo que busca un proveedor en la bbdd
     * @param cif del proveedor a buscar
     * @throws BbddException controlado
     */
    public Proveedor obtenerProveedor(String cif) throws BbddException {
        return proveedorModelo.obtenerProveedor(cif);
    }
    /**
     * Funcion que devuelve el listado de proveedores
     * @return arraylist de proveedores
     * @throws BbddException controlado
     */
    public ArrayList<Proveedor> obtenerListadoProveedor() throws BbddException {
        return proveedorModelo.obtenerListadoProveedor();
    }


}
