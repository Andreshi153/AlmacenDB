package es.iespuertolacruz.almacen.controlador;

public class MuelleController {
    
    public void validarMuelle(Muelle muelle) throws AlmacenException {
        String mensaje = "";
        if (muelle == null) {
            throw new AlmacenException("El muelle no puede ser nula");
        }
        if (muelle.getIdMuelle() <= 0) {
            mensaje += "El id del muelle no puede ser menor o igual que 0\n";
        }
        if (muelle.getIdZona() < 'A' || muelle.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }



}
