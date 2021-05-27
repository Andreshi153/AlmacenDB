package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Zona;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class ZonaModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "zona";
    private static final String CLAVE = "id_zona";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public ZonaModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de una zona
     * 
     * @param zona a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Zona zona) throws BbddException {
        String sql = "INSERT INTO zona (id_zona, tipo) VALUES('" + zona.getIdZona() + "'," + " '" + zona.getTipo()
                + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una zona
     * 
     * @param zona a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Zona zona) throws BbddException {
        String sql = "DELETE FROM zona WHERE id_zona = '" + zona.getIdZona() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una zona
     * 
     * @param zona a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Zona zona) throws BbddException {
        String sql = "UPDATE zona SET id_zona = '" + zona.getTipo() + "'" + " WHERE id_zona = '" + zona.getIdZona()
                + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un zona en la bbdd
     * 
     * @param identificador del zona
     * @return zona encontrado
     * @throws BbddException error controlado
     */
    public Zona buscar(String identificador) throws BbddException {
        Zona zonaEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Zona> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            zonaEncontrado = lista.get(0);
        }
        return zonaEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Zona> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Zona> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                char idZona = resultSet.getString(CLAVE).charAt(0);
                String tipo = resultSet.getString("tipo");
                Zona zona = new Zona(idZona, tipo);
                lista.add(zona);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    public ArrayList<Zona> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Zona> lista = new ArrayList<>();
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
