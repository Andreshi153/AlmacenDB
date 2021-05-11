package es.iespuertolacruz.almacen.modelo;

import es.iespuertolacruz.almacen.exception.BbddException;

public class MySqlBbdd extends Bbdd {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/test";
    
    public MySqlBbdd(String usuario, String password) throws BbddException {
        super(driver, url, usuario, password);
    }
}
