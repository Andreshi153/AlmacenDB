package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Producto;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class ProductoModelo {

    private static final String SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA = "Se ha producido un error realizando la consulta";
    SqLiteBbdd persistencia;
    private static final String TABLA = "producto";
    private static final String CLAVE = "id_producto";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public ProductoModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de una estanteria
     * 
     * @param estanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Producto producto) throws BbddException {
        String sql = "INSERT INTO producto VALUES ('" + producto.getIdProducto() + "', '" + producto.getNombre() + "',"
                + " '" + producto.getPrecioUnitario() + "', '" + producto.getTipo() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un producto
     * 
     * @param producto a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Producto producto) throws BbddException {
        String sql = "DELETE FROM producto WHERE id_producto = '" + producto.getIdProducto() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un producto
     * 
     * @param producto a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Producto producto) throws BbddException {
        String sql = "UPDATE producto SET nombre = '" + producto.getNombre() + "'," + " precio_unitario = '"
                + producto.getPrecioUnitario() + "', tipo ='" + producto.getTipo() + "'" + " WHERE id_producto = '"
                + producto.getIdProducto() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un producto en la bbdd
     * 
     * @param identificador del producto
     * @return producto encontrado
     * @throws BbddException error controlado
     */
    public Producto buscar(String identificador) throws BbddException {
        Producto productoEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = '" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            productoEncontrado = lista.get(0);
        }
        return productoEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Producto> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idProducto = resultSet.getInt(CLAVE);
                String nombre = resultSet.getString("nombre");
                float precio = resultSet.getFloat("precio_unitario");
                String tipo = resultSet.getString("tipo");
                Producto producto = new Producto(idProducto, nombre, precio, tipo);
                lista.add(producto);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    /**
     * Funcion encargada de buscar todos los productos en la bbdd
     * 
     * @return arraylist de productos
     * @throws BbddException controlado
     */
    public ArrayList<Producto> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Producto> lista = new ArrayList<>();
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
     * Funcion que obtiene el id del producto con el id mas alto de la bbdd
     * @return id del producto
     * @throws BbddException controlado
     */
    public int obtenerIdUltimo() throws BbddException {
        String sql = "SELECT " + CLAVE + " FROM " + TABLA + " ORDER BY " + CLAVE + " DESC LIMIT 1";
        int idUltimo = 0;
        ResultSet resultSet = null;
        try {
            resultSet = persistencia.buscarElementos(sql);
            while (resultSet.next()) {
                idUltimo = resultSet.getInt(CLAVE);
            }
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return idUltimo;
    }

}
