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

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public ProveedorController() throws BbddException, FicheroException {
        empresaController = new EmpresaController();
        proveedorModelo = new ProveedorModelo();
    }
    
    /**
     * Funcion que verifica si existe un proveedor en la bbdd
     * @param proveedor a verificar
     * @return true/false existe o no
     * @throws BbddException controlado
     */
    private boolean existe(Proveedor proveedor) throws BbddException {
        return buscar(proveedor.getCif()) != null;
    }

    /**
     * Funcion que valida un proveedor
     * @param proveedor a validar
     * @throws AlmacenException controlado
     */
    public void validar(Proveedor proveedor) throws AlmacenException {
        String mensaje = "";
        if (proveedor == null) {
            throw new AlmacenException("El proveedor no puede ser nulo");
        }
        if (proveedor.getCif() == null || !Validaciones.validarCif(proveedor.getCif())) {
            mensaje += "El cif del proveedor no puede ser nulo o no valido\n";
        }
        if (proveedor.getTipoProducto() == null || !Validaciones.validarTipoProducto(proveedor.getTipoProducto())) {
            mensaje += "El tipo de producto del proveedor no puede ser nulo o no valido\n";
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
        else throw new AlmacenException("El proveedor ya existe en la base de datos");
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
        else throw new AlmacenException("El proveedor no existe en la base de datos");
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
        else throw new AlmacenException("El proveedor no existe en la base de datos");
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
