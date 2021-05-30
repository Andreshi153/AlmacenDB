package es.iespuertolacruz.almacen.controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.iespuertolacruz.almacen.api.Estanteria;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.EstanteriaModelo;

public class EstanteriaController {
         
    EstanteriaModelo estanteriaModelo;
    ZonaController zonaController;

    public EstanteriaController() throws BbddException, FicheroException {
        zonaController = new ZonaController();
        estanteriaModelo = new EstanteriaModelo();
    }

    public boolean existe(Estanteria estanteria) throws BbddException {
        return buscar(estanteria.getIdEstanteria()) != null;
    }

    private boolean validarIdZona(char idZona) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(String.valueOf(idZona));
        return matcher.matches();
    }

    public void validar(Estanteria estanteria) throws AlmacenException {
        String mensaje = "";
        if (estanteria == null) {
            throw new AlmacenException("La estanteria no puede ser nula");
        }
        if (estanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (!validarIdZona(estanteria.getIdZona())) {
            mensaje += "El id de la zona debe estar entre la A y la Z\n";
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
        validar(estanteria);
        if(!existe(estanteria)) estanteriaModelo.insertar(estanteria);
        else throw new AlmacenException("La estanteria ya existe en la base de datos");
    }
    /**
     * Metodo que elimina una estanteria de la bbdd
     * @param estanteria a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Estanteria estanteria) throws BbddException, AlmacenException {
        validar(estanteria);
        if(existe(estanteria)) estanteriaModelo.eliminar(estanteria);
        else throw new AlmacenException("La estanteria no existe en la base de datos");
    }
    /**
     * Metodo que modifica una estanteria de la bbdd
     * @param estanteria a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Estanteria estanteria) throws BbddException, AlmacenException {
        validar(estanteria);
        if(existe(estanteria)) estanteriaModelo.modificar(estanteria);
        else throw new AlmacenException("La estanteria no existe en la base de datos");
    }
    /**
     * Metodo que busca una estanteria en la bbdd
     * @param idEstanteria de la estanteria a buscar
     * @throws BbddException controlado
     */
    public Estanteria buscar(int idEstanteria) throws BbddException {
        return estanteriaModelo.buscar(String.valueOf(idEstanteria));
    }
}
