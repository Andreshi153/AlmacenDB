package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Muelle;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class MuelleModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "muelle";
    private static final String CLAVE = "id_muelle";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public MuelleModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de un muelle
     * 
     * @param muelle a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Muelle muelle) throws BbddException {
        String sql = "INSERT INTO muelle VALUES('" + muelle.getIdMuelle() + "', '" + muelle.getIdZona() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un muelle
     * 
     * @param muelle a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Muelle muelle) throws BbddException {
        String sql = "DELETE FROM muelle WHERE id_muelle = '" + muelle.getIdMuelle() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un muelle
     * 
     * @param muelle a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Muelle muelle) throws BbddException {
        String sql = "UPDATE muelle SET id_zona = '" + muelle.getIdZona() + "', disponible = '"
                + (muelle.getDisponible() ? "true" : "false") + "', WHERE id_muelle = " + muelle.getIdMuelle() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un muelle en la bbdd
     * 
     * @param identificador del muelle
     * @return muelle encontrado
     * @throws BbddException error controlado
     */
    public Muelle buscar(String identificador) throws BbddException {
        Muelle muelleEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Muelle> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            muelleEncontrado = lista.get(0);
        }
        return muelleEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Muelle> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Muelle> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idMuelle = resultSet.getInt(CLAVE);
                char idZona = resultSet.getString("id_zona").charAt(0);
                boolean disponible = resultSet.getBoolean("disponible");
                Muelle muelle = new Muelle(idMuelle, idZona, disponible);
                lista.add(muelle);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    public ArrayList<Muelle> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Muelle> lista = new ArrayList<>();
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
