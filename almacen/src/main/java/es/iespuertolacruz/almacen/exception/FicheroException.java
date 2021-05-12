package es.iespuertolacruz.almacen.exception;

public class FicheroException extends Exception {
    
    public FicheroException(String mensaje) {
        super(mensaje);
    }

    public FicheroException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }
}
