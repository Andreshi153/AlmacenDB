package es.iespuertolacruz.almacen.api;

import java.util.HashMap;

public class ListaProductos {
    int idListaProductos;
    HashMap<Integer, Integer> lista;

    public ListaProductos() {
        this.idListaProductos = 20000;
    }

    public ListaProductos(int idListaProductos, HashMap<Integer, Integer> lista) {
        this.idListaProductos = idListaProductos;
        this.lista = lista;
    }

    public int getIdListaProductos() {
        return this.idListaProductos;
    }
    public HashMap<Integer, Integer> getLista() {
        return this.lista;
    }

    public void setLista(HashMap<Integer, Integer> lista) {
        this.lista = lista;
    }

}
