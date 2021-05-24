package es.iespuertolacruz.almacen.api;

import java.util.HashMap;

public class ListaProductos {
    int idListaProductos;
    HashMap<Integer, Integer> lista;

    /**
     * Constructor por defecto de la clase
     */
    public ListaProductos() {
        this.idListaProductos = 20000;
    }

    /**
     * Constructor de la clase con parametros
     * @param idListaProductos de la lista de productos
     * @param lista hashmap de productos y cantidades
     */
    public ListaProductos(int idListaProductos, HashMap<Integer, Integer> lista) {
        this.idListaProductos = idListaProductos;
        this.lista = lista;
    }

    /**
     * Funcion getter del id de la lista productos
     * @return idListaProductos de la lista de productos
     */
    public int getIdListaProductos() {
        return this.idListaProductos;
    }

    /**
     * Funcion getter del hashmap lista de la lista productos
     * @return lista de la lista de productos
     */
    public HashMap<Integer, Integer> getLista() {
        return this.lista;
    }

    /**
     * Metodo setter de la lista
     * @param lista de la listaproductos
     */
    public void setLista(HashMap<Integer, Integer> lista) {
        this.lista = lista;
    }

    @Override
    /**
     * Funcion toString de la lista de productos
     * @return informacion de la lista de productos
     */
    public String toString() {
        StringBuilder informacion = new StringBuilder("-> Id de la lista: " + idListaProductos);
        this.lista.forEach((producto, cantidad) -> informacion.append("\n· Producto " + producto + ": " + cantidad + " articulos"));
        return informacion.toString();
    }

    /**
     * Metodo setter del id de la lista de productos
     * @param idListaProductos de la lista
     */
    public void setIdListaProducto(int idListaProductos) {
        this.idListaProductos = idListaProductos;
    }

}
