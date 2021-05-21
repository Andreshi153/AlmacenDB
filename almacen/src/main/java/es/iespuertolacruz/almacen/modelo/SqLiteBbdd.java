package es.iespuertolacruz.almacen.modelo;

import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class SqLiteBbdd extends Bbdd {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL  = "jdbc:sqlite:almacen.db";
    
    public SqLiteBbdd(String driver, String urlConexion, String usuario, String password) throws BbddException, FicheroException {
       super(driver, urlConexion, usuario, password);
    }
 
    public SqLiteBbdd(String usuario, String password) throws BbddException, FicheroException {
       super(DRIVER, URL, usuario, password);
    }
 
   
 
 
 }
