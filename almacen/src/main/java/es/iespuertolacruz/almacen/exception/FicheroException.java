package es.iespuertolacruz.almacen.exception;

public class FicheroException extends Exception {
    
    /**
     * Constructor de la excepcion con mensaje
     * @param mensaje personalizado
     */
    public FicheroException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor de la excepcion con mensaje y excepcion
     * @param mensaje personalizado
     * @param exception causante
     */
    public FicheroException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }
}
