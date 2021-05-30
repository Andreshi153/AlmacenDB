package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Operacion;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class OperacionModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "operacion";
    private static final String CLAVE = "id_lista_productos";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public OperacionModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de una operacion
     * 
     * @param operacion a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Operacion operacion) throws BbddException {
        String sql = "INSERT INTO operacion VALUES ('" + operacion.getIdListaProductos() + "', '"
                + operacion.getIdMuelle() + "', '" + operacion.getFecha() + "', '" + operacion.getTipoOperacion()
                + "', '" + operacion.getCif() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una operacion
     * 
     * @param operacion a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Operacion operacion) throws BbddException {
        String sql = "DELETE FROM operacion WHERE id_lista_productos = '" + operacion.getIdListaProductos() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una operacion
     * 
     * @param operacion a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Operacion operacion) throws BbddException {
        String sql = "UPDATE operacion SET id_muelle = '" + operacion.getIdMuelle() + "', fecha = '"
                + operacion.getFecha() + "', tipo_operacion = '" + operacion.getTipoOperacion() + "', cif = '"
                + operacion.getCif() + "' WHERE id_lista_productos = '" + operacion.getIdListaProductos() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un operacion en la bbdd
     * 
     * @param identificador del operacion
     * @return operacion encontrado
     * @throws BbddException error controlado
     */
    public Operacion buscar(String identificador) throws BbddException {
        Operacion operacionEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Operacion> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            operacionEncontrado = lista.get(0);
        }
        return operacionEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Operacion> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Operacion> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idListaProductos = resultSet.getInt(CLAVE);
                int idMuelle = resultSet.getInt("id_muelle");
                String fecha = resultSet.getString("fecha");
                String tipoOperacion = resultSet.getString("tipo_operacion");
                String cif = resultSet.getString("cif");
                Operacion operacion = new Operacion(idListaProductos, idMuelle, fecha, tipoOperacion, cif);
                lista.add(operacion);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    /**
     * Funcion encargada de buscar todas las operaciones en la bbdd
     * 
     * @return arraylist de operaciones
     * @throws BbddException controlado
     */
    public ArrayList<Operacion> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Operacion> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }
        return lista;
    }
}
