package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Estanteria;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class EstanteriaModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "estanteria";
    private static final String CLAVE = "id_estanteria";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public EstanteriaModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de una estanteria
     * 
     * @param estanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Estanteria estanteria) throws BbddException {
        String sql = "INSERT INTO estanteria (idZona, numAlturas) VALUES ('" + estanteria.getIdZona() + "'," + " '"
                + estanteria.getNumAlturas() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una estanteria
     * 
     * @param estanteria a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Estanteria estanteria) throws BbddException {
        String sql = "DELETE FROM estanteria WHERE id_estanteria = '" + estanteria.getIdEstanteria() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una estanteria
     * 
     * @param estanteria a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Estanteria estanteria) throws BbddException {
        String sql = "UPDATE estanteria SET id_zona = '" + estanteria.getIdZona() + "'," + " num_alturas = '"
                + estanteria.getNumAlturas() + "'" + " WHERE id_estanteria = '" + estanteria.getIdEstanteria() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un estanteria en la bbdd
     * 
     * @param identificador del estanteria
     * @return estanteria encontrado
     * @throws BbddException error controlado
     */
    public Estanteria buscar(String identificador) throws BbddException {
        Estanteria estanteriaEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Estanteria> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            estanteriaEncontrado = lista.get(0);
        }
        return estanteriaEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Estanteria> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Estanteria> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int idEstanteria = resultSet.getInt(CLAVE);
                char idZona = resultSet.getString("id_zona").charAt(0);
                int numAlturas = resultSet.getInt("num_alturas");
                int numHuecosOcupados = resultSet.getInt("num_huecos_ocupados");
                Estanteria estanteria = new Estanteria(idEstanteria, idZona, numAlturas, numHuecosOcupados);
                lista.add(estanteria);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    public ArrayList<Estanteria> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Estanteria> lista = new ArrayList<>();
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
