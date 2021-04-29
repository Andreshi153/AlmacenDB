package es.iespuertolacruz.almacen.api;

public class Estanteria {
    String idEstanteria;
    String idZona;
    int numAlturas;
    int numHuecosOcupados;

    public Estanteria(String idEstanteria, String idZona, int numAlturas, int numHuecosOcupados) {
        this.idEstanteria = idEstanteria;
        this.idZona = idZona;
        this.numAlturas = numAlturas;
        this.numHuecosOcupados = numHuecosOcupados;
    }

}
