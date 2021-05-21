package es.iespuertolacruz.almacen.api;

public class Estanteria {
    int idEstanteria;
    char idZona;
    int numAlturas;
    int numHuecosOcupados;

    /**
     * Constructor de la clase
     * @param idEstanteria de la estanteria
     * @param idZona de la estanteria
     * @param numAlturas de la estanteria
     * @param numHuecosOcupados de la estanteria
     */
    public Estanteria(int idEstanteria, char idZona, int numAlturas, int numHuecosOcupados) {
        this.idEstanteria = idEstanteria;
        this.idZona = idZona;
        this.numAlturas = numAlturas;
        this.numHuecosOcupados = numHuecosOcupados;
    }

    /**
     * Funcion getter del id de la estanteria
     * @return idEstanteria de la estanteria
     */
    public int getIdEstanteria() {
        return this.idEstanteria;
    }

    /**
     * Funcion getter del id de la zona
     * @return idZona de la estanteria
     */
    public char getIdZona() {
        return this.idZona;
    }

    /**
     * Funcion getter del num de alturas
     * @return numAlturas de la estanteria
     */
    public int getNumAlturas() {
        return this.numAlturas;
    }

    /**
     * Funcion getter del num de los huecos ocupados
     * @return numHuecosOcupados de la estanteria
     */
    public int getNumHuecosOcupados() {
        return this.numHuecosOcupados;
    }

}
