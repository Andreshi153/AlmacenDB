package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.EstanteriaModelo;
public class EstanteriaController {
    
    private static final String EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z = "El id de la zona debe estar entre la A y la Z\n";
     
    EstanteriaModelo estanteriaModelo;

    public void validarEstanteria(Estanteria estanteria) throws AlmacenException {
        String mensaje = "";
        if (estanteria == null) {
            throw new AlmacenException("La estanteria no puede ser nula");
        }
        if (estanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (estanteria.getIdZona() < 'A' || estanteria.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if (estanteria.getNumAlturas() <= 0) {
            mensaje += "El numero de alturas de la estanteria no puede ser menor o igual que 0\n";
        }
        if (estanteria.getNumHuecosOcupados() > estanteria.getNumAlturas()) {
            mensaje += "El numero de huecos ocupados de la estanteria no puede ser mayor que el numero de alturas\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }


      /**
     * Metodo que inserta una estanteria en la bbdd
     * @param estanteria a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Estanteria estanteria) throws BbddException, AlmacenException {
        validarEstanteria(estanteria);
        estanteriaModelo.insertar(estanteria);
    }
    /**
     * Metodo que elimina una estanteria de la bbdd
     * @param estanteria a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Estanteria estanteria) throws BbddException, AlmacenException {
        validarEstanteria(estanteria);
        estanteriaModelo.eliminar(estanteria);
    }
    /**
     * Metodo que modifica una estanteria de la bbdd
     * @param estanteria a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Estanteria estanteria) throws BbddException, AlmacenException {
        validarEstanteria(estanteria);
        estanteriaModelo.modificar(estanteria);
    }
    /**
     * Metodo que busca una estanteria en la bbdd
     * @param idEstanteria de la estanteria a buscar
     * @throws BbddException controlado
     */
    public Estanteria obtenerEstanteria(int idEstanteria) throws BbddException {
        return estanteriaModelo.obtenerEstanteria(idEstanteria);
    }
}
