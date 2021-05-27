package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Cliente;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class ClienteModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "cliente";
    private static final String CLAVE = "cif";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public ClienteModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de un cliente
     * 
     * @param cliente a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Cliente cliente) throws BbddException {
        String sql = "INSERT INTO cliente VALUES('" + cliente.getCif() + "', '" + cliente.getPorcentajeDesc() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un cliente
     * 
     * @param cliente a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Cliente cliente) throws BbddException {
        String sql = "DELETE FROM cliente WHERE cif = '" + cliente.getCif() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un cliente
     * 
     * @param cliente a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Cliente cliente) throws BbddException {
        String sql = "UPDATE cliente SET porcentaje_desc = '" + cliente.getPorcentajeDesc() + "' WHERE cif = '"
                + cliente.getCif() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un cliente en la bbdd
     * 
     * @param identificador del cliente
     * @return cliente encontrado
     * @throws BbddException error controlado
     */
    public Cliente buscar(String identificador) throws BbddException {
        Cliente clienteEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            clienteEncontrado = lista.get(0);
        }
        return clienteEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Cliente> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String cif = resultSet.getString("cif");
                float porcentajeDesc = resultSet.getFloat("porcentaje_desc");
                Cliente cliente = new Cliente(cif, porcentajeDesc);
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    public ArrayList<Cliente> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Cliente> lista = new ArrayList<>();
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
