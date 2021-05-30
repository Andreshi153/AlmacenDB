package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.ProductoEstanteria;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class ProductoEstanteriaModelo {

    /**
     *
     */
    private static final String SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA = "Se ha producido un error realizando la consulta";
    SqLiteBbdd persistencia;
    private static final String TABLA = "producto_estanteria";
    private static final String CLAVE = "id_producto";
    private static final String CLAVESEC = "id_estanteria";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public ProductoEstanteriaModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de un productoEstanteria
     * 
     * @param productoEstanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(ProductoEstanteria productoEstanteria) throws BbddException {
        String sql = "INSERT INTO producto_estanteria VALUES ('" + productoEstanteria.getIdProducto() + "', '"
                + productoEstanteria.getIdEstanteria() + "', '" + productoEstanteria.getCantidad() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un productoEstanteria
     * 
     * @param productoEstanteria a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(ProductoEstanteria productoEstanteria) throws BbddException {
        String sql = "DELETE FROM producto_estanteria WHERE id_producto = '" + productoEstanteria.getIdProducto()
                + "' AND id_estanteria = '" + productoEstanteria.getIdEstanteria() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un productoEstanteria
     * 
     * @param productoEstanteria a modificar
     * @throws BbddException error controlado
     */
    public void modificar(ProductoEstanteria productoEstanteria) throws BbddException {
        String sql = "UPDATE producto_estanteria SET cantidad = '" + productoEstanteria.getCantidad()
                + "' WHERE id_producto = '" + productoEstanteria.getIdProducto() + "' AND id_estanteria = '"
                + productoEstanteria.getIdEstanteria() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un producto en la bbdd
     * 
     * @param identificador del producto
     * @return producto encontrado
     * @throws BbddException error controlado
     */
    public ProductoEstanteria buscar(String identificador, String identificadorSec) throws BbddException {
        ProductoEstanteria productoEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "' AND " + CLAVESEC + " = '"
                + identificadorSec + "'";
        ResultSet resultSet;
        ArrayList<ProductoEstanteria> lista = new ArrayList<>();
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
    private ArrayList<ProductoEstanteria> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<ProductoEstanteria> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idProducto = resultSet.getInt(CLAVE);
                int idEstanteria = resultSet.getInt(CLAVESEC);
                int cantidad = resultSet.getInt("cantidad");
                ProductoEstanteria productoEstanteria = new ProductoEstanteria(idProducto, idEstanteria, cantidad);
                lista.add(productoEstanteria);
            }
        } catch (SQLException e) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    public ArrayList<ProductoEstanteria> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<ProductoEstanteria> lista = new ArrayList<>();
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
     * Funcion que calcula el valor total de todos los productos del almacen
     * @return valor total de los productos
     * @throws BbddException controlado
     */
    public double obtenerValorProductosTotal() throws BbddException {
        String sql = "SELECT cantidad * precio_unitario AS valor_total FROM producto NATURAL JOIN producto_estanteria";
        double valorTotal = 0;
        ResultSet resultSet = null;
        try {
            resultSet = persistencia.buscarElementos(sql);
            while (resultSet.next()) {
                valorTotal += resultSet.getFloat("valor_total");
            }
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return Math.floor(valorTotal*100)/100;
    }

    /**
     * Funcion que obtiene los huecos ocupados del almacen
     * @return huecos ocupados
     * @throws BbddException controlado
     */
    public int obtenerHuecosOcupados() throws BbddException {
        String sql = "SELECT COUNT(id_producto) AS num_huecos FROM producto_estanteria";
        int numHuecosOcupados = 0;
        ResultSet resultSet = null;
        try {
            resultSet = persistencia.buscarElementos(sql);
            while (resultSet.next()) {
                numHuecosOcupados = resultSet.getInt("num_huecos");
            }
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return numHuecosOcupados;
    }

    /**
     * Funcion que obtiene los huecos ocupados del almacen
     * @return huecos ocupados
     * @throws BbddException controlado
     */
    public int obtenerHuecosTotales() throws BbddException {
        String sql = "SELECT SUM(num_alturas) AS num_alturas FROM estanteria";
        int numHuecosTotales = 0;
        ResultSet resultSet = null;
        try {
            resultSet = persistencia.buscarElementos(sql);
            while (resultSet.next()) {
                numHuecosTotales = resultSet.getInt("num_alturas");
            }
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return numHuecosTotales;
    }
}
