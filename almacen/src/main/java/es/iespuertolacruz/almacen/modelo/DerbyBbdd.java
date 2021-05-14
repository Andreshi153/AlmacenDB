package es.iespuertolacruz.almacen.modelo;

import java.sql.SQLException;

import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class DerbyBbdd extends Bbdd {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    static String url = "jdbc:derby:almacen;create=true";
    
    public DerbyBbdd(String usuario, String password) throws BbddException, FicheroException, SQLException {
        super(driver, url, usuario, password);
    }
}
