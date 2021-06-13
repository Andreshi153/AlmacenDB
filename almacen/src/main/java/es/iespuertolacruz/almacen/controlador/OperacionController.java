package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.Operacion;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.OperacionModelo;
public class OperacionController {

    OperacionModelo operacionModelo;
    ListaProductosController listaProductosController;
    MuelleController muelleController;
    EmpresaController empresaController;

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public OperacionController() throws BbddException, FicheroException {
        listaProductosController = new ListaProductosController();
        muelleController = new MuelleController();
        empresaController = new EmpresaController();
        operacionModelo = new OperacionModelo();
    }

    /**
     * Funcion que verifica si existe una operacion en la bbdd
     * @param operacion a verificar
     * @return true/false existe o no
     * @throws BbddException controlado
     */
    private boolean existe(Operacion operacion) throws BbddException {
        return buscar(operacion.getIdListaProductos()) != null;
    }

    /**
     * Funcion que valida una operacion
     * @param operacion a validar
     * @throws AlmacenException controlado
     */
    public void validar(Operacion operacion) throws AlmacenException {
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
        if (operacion.getFecha() == null || !Validaciones.validarFecha(operacion.getFecha())) {
            mensaje += "La fecha de la operacion no puede ser nula o no valida\n";
        }
        if (operacion.getTipoOperacion() == null || !Validaciones.validarTipoOperacion(operacion.getTipoOperacion())) {
            mensaje += "El tipo de la operacion no puede ser nulo o no valido\n";
        }
        if (operacion.getCif() == null || !Validaciones.validarCif(operacion.getCif())) {
            mensaje += "El cif de la operacion no puede ser nulo o no valido\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

      //CRUD operacion

    /**
     * Metodo que inserta un operacion en la bbdd
     * @param operacion a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Operacion operacion) throws BbddException, AlmacenException {
        validar(operacion);
        if(!existe(operacion)) operacionModelo.insertar(operacion);
        else throw new AlmacenException("La operacion ya existe en la base de datos");
    }
    /**
     * Metodo que elimina un operacion de la bbdd
     * @param operacion a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Operacion operacion) throws BbddException, AlmacenException {
        validar(operacion);
        if(existe(operacion)) operacionModelo.eliminar(operacion);
        else throw new AlmacenException("La operacion no existe en la base de datos");
    }
    /**
     * Metodo que modifica un operacion de la bbdd
     * @param operacion a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Operacion operacion) throws BbddException, AlmacenException {
        validar(operacion);
        if(existe(operacion)) operacionModelo.modificar(operacion);
        else throw new AlmacenException("La operacion no existe en la base de datos");
    }
    /**
     * Metodo que busca una operacion en la bbdd
     * @param idListaProductos de la operacion a buscar
     * @throws BbddException controlado
     */
    public Operacion buscar(int idListaProductos) throws BbddException {
        return operacionModelo.buscar(String.valueOf(idListaProductos));
    }

    /**
     * Metodo que busca una operacion en la bbdd
     * @param idListaProductos de la operacion a buscar
     * @throws BbddException controlado
     */
    public Operacion buscar(String idListaProductos) throws BbddException {
        return operacionModelo.buscar(idListaProductos);
    }
}
