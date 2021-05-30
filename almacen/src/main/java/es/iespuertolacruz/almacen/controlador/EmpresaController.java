package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.Empresa;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.EmpresaModelo;
public class EmpresaController {
    EmpresaModelo empresaModelo;

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public EmpresaController() throws BbddException, FicheroException {
        empresaModelo = new EmpresaModelo();
    }

    /**
     * Funcion que verifica si existe una empresa en la bbdd
     * @param empresa a verificar
     * @return true/false existe o no
     * @throws BbddException controlado
     */
    private boolean existe(Empresa empresa) throws BbddException {
        return buscar(empresa.getCif()) != null;
    }
    
    /**
     * Funcion que valida una empresa
     * @param empresa a validar
     * @throws AlmacenException controlado
     */
    public void validar(Empresa empresa) throws AlmacenException {
        String mensaje = "";
        if (empresa == null) {
            throw new AlmacenException("La empresa no puede ser nula");
        }
        if (empresa.getCif() == null || !Validaciones.validarCif(empresa.getCif())) {
            mensaje += "El cif de la empresa no puede ser nulo o no valido\n";
        }
        if (empresa.getNombre() == null || empresa.getNombre().isEmpty()) {
            mensaje += "El nombre de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getDireccion() == null || empresa.getDireccion().isEmpty()) {
            mensaje += "La direccion de la empresa no puede ser nula o vacia\n";
        }
        if (empresa.getTelefono() == null || !Validaciones.validarTelefono(empresa.getTelefono())) {
            mensaje += "El telefono de la empresa no puede ser nulo o no valido\n";
        }
        if (empresa.getCorreo() == null || !Validaciones.validarCorreo(empresa.getCorreo())) {
            mensaje += "El correo de la empresa no puede ser nulo o no valido\n";
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
