package es.iespuertolacruz.almacen.controlador;

import es.iespuertolacruz.almacen.api.Zona;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.modelo.ZonaModelo;

public class ZonaController {
    
    private static final String EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z = "El id de la zona debe estar entre la A y la Z\n";
    
    ZonaModelo zonaModelo;

    public void validarZona(Zona zona) throws AlmacenException {
        String mensaje = "";
        if (zona == null) {
            throw new AlmacenException("La zona no puede ser nula");
        }
        if (zona.getIdZona() < 'A' || zona.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if (zona.getTipo().isEmpty() || zona.getTipo() == null) {
            mensaje += "El tipo de la zona no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }


    /**
     * Metodo que inserta una zona en la bbdd
     * @param zona a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Zona zona) throws BbddException, AlmacenException {
        validarZona(zona);
        zonaModelo.insertar(zona);
    }
    /**
     * Metodo que elimina una zona de la bbdd
     * @param zona a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Zona zona) throws BbddException, AlmacenException {
        validarZona(zona);
        zonaModelo.eliminar(zona);
    }
    /**
     * Metodo que modifica una zona de la bbdd
     * @param zona a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Zona zona) throws BbddException, AlmacenException {
        validarZona(zona);
        zonaModelo.modificar(zona);
    }
    /**
     * Metodo que busca una zona en la bbdd
     * @param idZona de la zona a buscar
     * @throws BbddException controlado
     */
    public Zona obtenerZona(char idZona) throws BbddException {
        return zonaModelo.obtenerZona(idZona);
    }

}
