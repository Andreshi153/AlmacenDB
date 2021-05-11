package es.iespuertolacruz.almacen.api;
public class Muelle {
    
    int idMuelle;
    char idZona;
    boolean disponible;


    public Muelle(int idMuelle, char idZona, boolean disponible) {
        this.idMuelle = idMuelle;
        this.idZona = idZona;
        this.disponible = disponible;
    }

    public int getIdMuelle() {
        return this.idMuelle;
    }

    public char getIdZona() {
        return this.idZona;
    }

    public boolean getDisponible() {
        return this.disponible;
    }
}
