package es.iespuertolacruz.almacen.api;

public class Operacion {
    int idListaProductos;
    int idMuelle;
    String fecha;
    String tipoOperacion;
    String cif;

    /**
     * Constructor de la clase
     * @param idListaProductos de operacion
     * @param idMuelle de operacion
     * @param fecha de operacion
     * @param tipoOperacion de operacion
     * @param cif de operacion
     */
    public Operacion(int idListaProductos, int idMuelle, String fecha, String tipoOperacion, String cif) {
        this.idListaProductos = idListaProductos;
        this.idMuelle = idMuelle;
        this.fecha = fecha;
        this.tipoOperacion = tipoOperacion;
        this.cif = cif;
    }

    /**
     * Funcion getter del id lista de productos de la operacion
     * @return idListaProductos de la operacion
     */
    public int getIdListaProductos() {
        return this.idListaProductos;
    }

    /**
     * Funcion getter del id del muelle de la operacion
     * @return idMuelle de la operacion
     */
    public int getIdMuelle() {
        return this.idMuelle;
    }

    /**
     * Funcion getter de la fecha de la operacion
     * @return fecha de la operacion
     */
    public String getFecha() {
        return this.fecha;
    }

    /**
     * Funcion getter del tipo de la operacion
     * @return tipoOperacion de la operacion
     */
    public String getTipoOperacion() {
        return this.tipoOperacion;
    }

    /**
     * Funcion getter del cif de la operacion
     * @return cif de la operacion
     */
    public String getCif() {
        return this.cif;
    }

    /**
     * Funcion toString de operacion
     * @return informacion de la operacion
     */
    @Override
    public String toString() {
        return "-> Id lista de productos: " + this.idListaProductos + 
        "\n· CIF de la empresa asociada: " + this.cif + 
        "\n· Muelle: " + this.idMuelle + "\n· Fecha: " + this.fecha + 
        "\n· Tipo de operacion: " + this.tipoOperacion;
    }

}