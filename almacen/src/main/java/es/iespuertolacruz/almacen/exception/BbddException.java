package es.iespuertolacruz.almacen.exception;

public class BbddException extends Exception {
    
    /**
     * Constructor de la excepcion con mensaje
     * @param mensaje personalizado
     */
    public BbddException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor de la excepcion con mensaje y excepcion
     * @param mensaje personalizado
     * @param exception causante
     */
    public BbddException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }
}