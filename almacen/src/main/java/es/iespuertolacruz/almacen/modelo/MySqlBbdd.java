package es.iespuertolacruz.almacen.modelo;

import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class MySqlBbdd extends Bbdd {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/test";
    
    public MySqlBbdd(String usuario, String password) throws BbddException, FicheroException {
        super(driver, url, usuario, password);
    }
}
