package es.iespuertolacruz.almacen.exception;

public class AlmacenException extends Exception {
    
    /**
     * Constructor de la excepcion con mensaje
     * @param mensaje personalizado
     */
    public AlmacenException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor de la excepcion con mensaje y excepcion
     * @param mensaje personalizado
     * @param exception causante
     */
    public AlmacenException(String mensaje, Exception exception) {
        super(mensaje, exception);
    }
}
