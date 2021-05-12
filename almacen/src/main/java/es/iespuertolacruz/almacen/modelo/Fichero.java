package es.iespuertolacruz.almacen.modelo;

import java.io.*;
import java.util.Scanner;

import es.iespuertolacruz.almacen.exception.FicheroException;

public class Fichero {

    private static final String RETORNO_CARRO = "\n";

    /**
     * Funcion encargada de leer un ficher
     * 
     * @param nombre nombre del fichero a leer
     * @throws FicheroException Error controlado en la lectura del fichero
     */
    public String leer(String nombre) throws FicheroException {
        StringBuilder informacion = null;
        File fichero = new File(nombre);
        if (!validarFichero(fichero)) {
            throw new FicheroException("El fichero a leer no existe");
        }
        try (Scanner scanner = new Scanner(fichero)) {
            informacion = new StringBuilder();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                informacion.append(linea + RETORNO_CARRO);
            }
        } catch (Exception e) {
            throw new FicheroException("Se ha producido un error en la lectura del fichero", e);
        }
        return informacion.toString();
    }

    /**
     * Funcion que verifica si el fichero existo
     * 
     * @param fichero
     * @return
     */
    public boolean validarFichero(File fichero) {
        return fichero.exists();
    }
}
