package es.iespuertolacruz.almacen.api;
public class Zona {
 
    char idZona;
    String tipo;

    /**
     * Constructor de la clase
     * @param idZona de la zona
     * @param tipo de la zona
     */
    public Zona(char idZona, String tipo) {
        this.idZona = idZona;
        this.tipo = tipo;
    }

    /**
     * Funcion getter del idZona de la zona
     * @return idZona de la zona
     */
    public char getIdZona() {
        return this.idZona;
    }

    /**
     * Funcion getter del tipo de la zona
     * @return tipo de la zona
     */
    public String getTipo() {
        return this.tipo;
    }
}
