package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import es.iespuertolacruz.almacen.api.ListaProductos;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class ListaProductosModelo {

    private static final String SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA = "Se ha producido un error realizando la consulta";
    SqLiteBbdd persistencia;
    private static final String TABLA = "lista_productos";
    private static final String CLAVE = "id_lista_productos";
    private static final String CLAVESEC = "id_producto";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public ListaProductosModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de una listaProductos
     * 
     * @param listaProductos a insertar
     * @throws BbddException error controlado
     */
    public void insertar(ListaProductos listaProductos) throws BbddException {
        HashMap<Integer, Integer> mapaListaProductos = listaProductos.getLista();
        StringBuilder sql = new StringBuilder();
        mapaListaProductos.forEach((producto, cantidad) -> sql
                .append("INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES ('"
                        + listaProductos.getIdListaProductos() + "'," + producto + ", " + cantidad + ");"));
        persistencia.insertarElementos(sql.toString());
    }

    /**
     * Metodo encargado de eliminar una listaProducto
     * 
     * @param listaProducto a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(ListaProductos listaProducto) throws BbddException {
        String sql = "DELETE FROM lista_productos WHERE id_lista_productos = '" + listaProducto.getIdListaProductos()
                + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una listaProducto
     * 
     * @param listaProducto a modificar
     * @throws BbddException error controlado
     */
    public void modificar(ListaProductos listaProducto) throws BbddException {
        eliminar(listaProducto);
        insertar(listaProducto);
    }

    /**
     * Funcion que busca un producto en la bbdd
     * 
     * @param identificador del producto
     * @return producto encontrado
     * @throws BbddException error controlado
     */
    public ListaProductos buscar(String identificador) throws BbddException {
        ListaProductos listaProductosEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<ListaProductos> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            listaProductosEncontrado = lista.get(0);
        }
        return listaProductosEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<ListaProductos> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<ListaProductos> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idListaProductos = resultSet.getInt(CLAVE);
                HashMap<Integer, Integer> mapaListaProducto = obtenerHashMapListaProducto(idListaProductos);
                ListaProductos listaProductos = new ListaProductos(idListaProductos, mapaListaProducto);
                lista.add(listaProductos);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    /**
     * Funcion encargada de buscar todas las listaproductos en la bbdd
     * @return arraylist de listaproductos
     * @throws BbddException controlado
     */
    public ArrayList<ListaProductos> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<ListaProductos> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }
        return lista;
    }

    /**
     * Funcion que obtiene el hashmap de una lista de productos
     * @param idListaProductos a buscar
     * @return hashmap<idProdcto, cantidad>
     * @throws BbddException controlado
     */
    private HashMap<Integer, Integer> obtenerHashMapListaProducto(int idListaProductos) throws BbddException {
        String sql = "SELECT id_producto, cantidad FROM lista_productos WHERE id_lista_productos = "
                        + idListaProductos;
        ResultSet resultSet;
        HashMap<Integer, Integer> lista = new HashMap<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            while (resultSet.next()) {
                int idProducto = resultSet.getInt(CLAVESEC);
                int cantidad = resultSet.getInt("cantidad");
                lista.put(idProducto, cantidad);
            }
        } catch (Exception e) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }
        return lista;
    }

    /**
     * Funcion que obtiene el id mas alto de listas de productos en la bbdd
     * @return id mas alto
     * @throws BbddException controlado
     */
    public int obtenerMaxIdListaProductos() throws BbddException {
        String sql = "SELECT MAX(id_lista_productos) AS max_id FROM lista_productos";
        int maxIdListaProductos = 0;
        ResultSet resultSet;
        try {
            resultSet = persistencia.buscarElementos(sql);
            while (resultSet.next()) {
                maxIdListaProductos = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }
        return maxIdListaProductos;
    }
}
