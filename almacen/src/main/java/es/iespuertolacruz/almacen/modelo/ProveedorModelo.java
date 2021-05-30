package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Proveedor;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class ProveedorModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "proveedor";
    private static final String CLAVE = "cif";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public ProveedorModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de un proveedor
     * 
     * @param proveedor a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Proveedor proveedor) throws BbddException {
        String sql = "INSERT INTO proveedor VALUES('" + proveedor.getCif() + "', '" + proveedor.getTipoProducto()
                + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un proveedor
     * 
     * @param proveedor a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Proveedor proveedor) throws BbddException {
        String sql = "DELETE FROM proveedor WHERE cif = '" + proveedor.getCif() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un proveedor
     * 
     * @param proveedor a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Proveedor proveedor) throws BbddException {
        String sql = "UPDATE proveedor SET tipo_producto = '" + proveedor.getTipoProducto() + "' WHERE cif = '"
                + proveedor.getCif() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un producto en la bbdd
     * 
     * @param identificador del producto
     * @return producto encontrado
     * @throws BbddException error controlado
     */
    public Proveedor buscar(String identificador) throws BbddException {
        Proveedor proveedorEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Proveedor> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            proveedorEncontrado = lista.get(0);
        }
        return proveedorEncontrado;
    }

    /**
     * Funcion encargada de buscar todos los proveedores en la bbdd
     * 
     * @return arraylist de proveedores
     * @throws BbddException controlado
     */
    public ArrayList<Proveedor> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Proveedor> lista = new ArrayList<>();
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

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Proveedor> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Proveedor> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String cif = resultSet.getString("cif");
                String tipoProducto = resultSet.getString("tipo_producto");
                Proveedor proveedor = new Proveedor(cif, tipoProducto);
                lista.add(proveedor);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }
}
