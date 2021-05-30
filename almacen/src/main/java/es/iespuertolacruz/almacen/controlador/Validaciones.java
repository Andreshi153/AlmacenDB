package es.iespuertolacruz.almacen.controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    private Validaciones(){}

    /**
     * Funcion que comprueba si un valor cumple un patron
     * @param patron a comprobar
     * @param valor a comprobar
     * @return true/false se cumple o no
     */
    private static boolean validar(String patron, String valor) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(valor);
        return matcher.matches();
    }
    
    /**
     * Funcion que comprueba si un id de zona es valido
     * @param idZona a validar
     * @return true/false es valido o no
     */
    public static boolean validarZona(char idZona) {
        return validar("[A-Z]", String.valueOf(idZona));
    }

    /**
     * Funcion que comprueba si un cif es valido
     * @param cif a validar
     * @return true/false es valido o no
     */
    public static boolean validarCif(String cif) {
        return validar("[A-Z][0-9]{8}", cif);
    }

    /**
     * Funcion que comprueba si un tipo de producto es valido
     * @param tipoProducto a validar
     * @return true/false es valido o no
     */
    public static boolean validarTipoProducto(String tipoProducto) {
        return validar("Normal|Frio|Congelados", tipoProducto);
    }

    /**
     * Funcion que comprueba si un telefono es valido
     * @param telefono a validar
     * @return true/false es valido o no
     */
    public static boolean validarTelefono(String telefono) {
        return validar("[0-9]{9}", telefono);
    }

    /**
     * Funcion que comprueba si un correo es valido
     * @param correo a validar
     * @return true/false es valido o no
     */
    public static boolean validarCorreo(String correo) {
        return validar(".+@.+\\..+", correo);
    }

    /**
     * Funcion que comprueba si una fecha es valida
     * @param fecha a validar
     * @return true/false es valida o no
     */
    public static boolean validarFecha(String fecha) {
        return validar("[0-9]{4}[-/][0-9]{2}[-/][0-9]{2}|[0-9]{2}[-/][0-9]{2}[-/][0-9]{4}", fecha);
    }

    /**
     * Funcion que comprueba si un tipo de operacion es valido
     * @param tipoOperacion a validar
     * @return true/false es valido o no
     */
    public static boolean validarTipoOperacion(String tipoOperacion) {
        return validar("Entrada|Salida", tipoOperacion);
    }
}
