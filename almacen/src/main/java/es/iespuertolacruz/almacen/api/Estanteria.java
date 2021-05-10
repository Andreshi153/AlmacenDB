package es.iespuertolacruz.almacen.api;

public class Estanteria {
    int idEstanteria;
    char idZona;
    int numAlturas;
    int numHuecosOcupados;

    public Estanteria(int idEstanteria, char idZona, int numAlturas, int numHuecosOcupados) {
        this.idEstanteria = idEstanteria;
        this.idZona = idZona;
        this.numAlturas = numAlturas;
        this.numHuecosOcupados = numHuecosOcupados;
    }

}
