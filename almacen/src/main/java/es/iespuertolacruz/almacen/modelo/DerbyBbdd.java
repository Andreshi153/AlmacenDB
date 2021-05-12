package es.iespuertolacruz.almacen.modelo;

import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class DerbyBbdd extends Bbdd {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    static String url = "mem:derby.db";
    
    public DerbyBbdd(String usuario, String password) throws BbddException, FicheroException {
        super(driver, url, usuario, password);
    }
}
