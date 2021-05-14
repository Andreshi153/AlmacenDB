
package es.iespuertolacruz.almacen.modelo;

import java.sql.SQLException;

import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class MySqlBbdd extends Bbdd {
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/?user=root";
    
    public MySqlBbdd(String usuario, String password) throws BbddException, FicheroException, SQLException {
        super(driver, url, usuario, password);
    }
}
