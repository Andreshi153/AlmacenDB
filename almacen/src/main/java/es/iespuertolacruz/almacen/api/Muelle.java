package es.iespuertolacruz.almacen.api;
public class Muelle {
    
    int idMuelle;
    char idZona;
    boolean disponible;

    /**
     * Constructor de la clase
     * @param idMuelle del muelle
     * @param idZona del muelle
     * @param disponible true/false disponiblidad del muelle
     */
    public Muelle(int idMuelle, char idZona, boolean disponible) {
        this.idMuelle = idMuelle;
        this.idZona = idZona;
        this.disponible = disponible;
    }

    /**
     * Funcion getter del id del muelle
     * @return idMuelle del muelle
     */
    public int getIdMuelle() {
        return this.idMuelle;
    }

    /**
     * Funcion getter del id de la zona
     * @return idZona del muelle
     */
    public char getIdZona() {
        return this.idZona;
    }

    /**
     * Funcion getter de disponible del muelle
     * @return disponible del muelle
     */
    public boolean getDisponible() {
        return this.disponible;
    }
}
