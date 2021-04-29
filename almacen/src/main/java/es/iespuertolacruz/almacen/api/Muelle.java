package es.iespuertolacruz.almacen.api;
public class Muelle {
    
    String idMuelle;
    String idZona;
    boolean disponible;


    public Muelle(String idMuelle, String idZona, boolean disponible) {
        this.idMuelle = idMuelle;
        this.idZona = idZona;
        this.disponible = disponible;
    }
}
