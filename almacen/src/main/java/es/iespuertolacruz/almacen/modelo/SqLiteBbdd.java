package es.iespuertolacruz.almacen.modelo;

import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class SqLiteBbdd extends Bbdd {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL  = "jdbc:sqlite:almacen.db";
    
    public SqLiteBbdd(String tabla, String clave, String driver, String urlConexion, String usuario, String password) throws BbddException, FicheroException {
       super(tabla, clave, driver, urlConexion, usuario, password);
    }
 
    public SqLiteBbdd(String tabla, String clave, String usuario, String password) throws BbddException, FicheroException {
       super(tabla, clave, DRIVER, URL, usuario, password);
    }
 
   
 
 
 }
