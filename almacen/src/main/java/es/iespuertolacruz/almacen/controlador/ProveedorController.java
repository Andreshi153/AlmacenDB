package es.iespuertolacruz.almacen.controlador;

import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Proveedor;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.ProveedorModelo;

public class ProveedorController {
    
    ProveedorModelo proveedorModelo;
    EmpresaController empresaController;

    public ProveedorController() throws BbddException, FicheroException {
        empresaController = new EmpresaController();
        proveedorModelo = new ProveedorModelo();
    }
    
    private boolean existe(Proveedor proveedor) throws BbddException {
        return buscar(proveedor.getCif()) != null;
    }

    public void validar(Proveedor proveedor) throws AlmacenException {
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
        validar(proveedor);
        if(!existe(proveedor)) proveedorModelo.insertar(proveedor);
        else throw new AlmacenException("El provedor ya existe en la base de datos");
    }
    /**
     * Metodo que elimina un proveedor de la bbdd
     * @param proveedor a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Proveedor proveedor) throws BbddException, AlmacenException {
        validar(proveedor);
        if(existe(proveedor)) proveedorModelo.eliminar(proveedor);
        else throw new AlmacenException("El provedor no existe en la base de datos");
    }
    /**
     * Metodo que modifica un proveedor de la bbdd
     * @param proveedor a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Proveedor proveedor) throws BbddException, AlmacenException {
        validar(proveedor);
        if(existe(proveedor)) proveedorModelo.modificar(proveedor);
        else throw new AlmacenException("El provedor no existe en la base de datos");
    }
    /**
     * Metodo que busca un proveedor en la bbdd
     * @param cif del proveedor a buscar
     * @throws BbddException controlado
     */
    public Proveedor buscar(String cif) throws BbddException {
        return proveedorModelo.buscar(cif);
    }
    /**
     * Funcion que devuelve el listado de proveedores
     * @return arraylist de proveedores
     * @throws BbddException controlado
     */
    public ArrayList<Proveedor> buscarTodos() throws BbddException {
        return proveedorModelo.buscarTodos();
    }


}
