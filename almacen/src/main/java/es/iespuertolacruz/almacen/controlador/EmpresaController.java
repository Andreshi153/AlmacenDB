package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.Empresa;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.EmpresaModelo;
public class EmpresaController {
    EmpresaModelo empresaModelo;

    public EmpresaController() throws BbddException, FicheroException {
        empresaModelo = new EmpresaModelo();
    }

    private boolean existe(Empresa empresa) throws BbddException {
        return buscar(empresa.getCif()) != null;
    }
    
    public void validar(Empresa empresa) throws AlmacenException {
        String mensaje = "";
        if (empresa == null) {
            throw new AlmacenException("La empresa no puede ser nula");
        }
        if (empresa.getCif().isEmpty() || empresa.getCif() == null) {
            mensaje += "El cif de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getNombre().isEmpty() || empresa.getNombre() == null) {
            mensaje += "El nombre de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getDireccion().isEmpty() || empresa.getDireccion() == null) {
            mensaje += "La direccion de la empresa no puede ser nula o vacia\n";
        }
        if (empresa.getTelefono().isEmpty() || empresa.getTelefono() == null) {
            mensaje += "El telefono de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getCorreo().isEmpty() || empresa.getCorreo() == null) {
            mensaje += "El correo de la empresa no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }
    
    /**
     * Metodo que inserta una empresa en la bbdd
     * @param empresa a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Empresa empresa) throws BbddException, AlmacenException {
        validar(empresa);
        if(!existe(empresa)) empresaModelo.insertar(empresa);
        else throw new AlmacenException("La empresa ya existe en la base de datos");
    }
    /**
     * Metodo que elimina una empresa de la bbdd
     * @param empresa a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Empresa empresa) throws BbddException, AlmacenException {
        validar(empresa);
        if(existe(empresa)) empresaModelo.eliminar(empresa);
        else throw new AlmacenException("La empresa no existe en la base de datos");
    }
    /**
     * Metodo que modifica una empresa de la bbdd
     * @param empresa a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Empresa empresa) throws BbddException, AlmacenException {
        validar(empresa);
        if(existe(empresa)) empresaModelo.modificar(empresa);
        else throw new AlmacenException("La empresa no existe en la base de datos");
    }
    /**
     * Metodo que busca una empresa en la bbdd
     * @param cif de la empresa a buscar
     * @throws BbddException controlado
     */
    public Empresa buscar(String cif) throws BbddException {
        return empresaModelo.buscar(cif);
    }

}
