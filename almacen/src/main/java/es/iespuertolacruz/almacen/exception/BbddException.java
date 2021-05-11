package es.iespuertolacruz.almacen.exception;

public class BbddException extends Exception {
    
    public BbddException(String mensaje) {
        super(mensaje);
    }

    public BbddException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }
}