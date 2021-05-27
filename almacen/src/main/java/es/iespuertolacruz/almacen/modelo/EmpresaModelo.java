package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Empresa;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class EmpresaModelo {

    SqLiteBbdd persistencia;
    private static final String TABLA = "empresa";
    private static final String CLAVE = "cif";

    /**
     * Constructor de la clase
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public EmpresaModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Metodo encargado de realizar la insercion de un estanteria
     * 
     * @param estanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Empresa empresa) throws BbddException {
        String sql = "INSERT INTO empresa VALUES ('" + empresa.getCif() + "', '" + empresa.getNombre() + "', '"
                + empresa.getDireccion() + "', '" + empresa.getTelefono() + "', '" + empresa.getCorreo() + "')";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una empresa
     * 
     * @param empresa a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Empresa empresa) throws BbddException {
        String sql = "DELETE FROM empresa WHERE cif = '" + empresa.getCif() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una empresa
     * 
     * @param empresa a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Empresa empresa) throws BbddException {
        String sql = "UPDATE empresa SET nombre = '" + empresa.getNombre() + "', direccion = '" + empresa.getDireccion()
                + "', " + "telefono = '" + empresa.getTelefono() + "', correo = '" + empresa.getCorreo()
                + "' WHERE cif = '" + empresa.getCif() + "'";
        persistencia.actualizar(sql);
    }

    /**
     * Funcion que busca un empresa en la bbdd
     * 
     * @param identificador del empresa
     * @return empresa encontrado
     * @throws BbddException error controlado
     */
    public Empresa buscar(String identificador) throws BbddException {
        Empresa empresaEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Empresa> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            empresaEncontrado = lista.get(0);
        }
        return empresaEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de clientes
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Empresa> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Empresa> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String cif = resultSet.getString("cif");
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                Empresa empresa = new Empresa(cif, nombre, direccion, telefono, correo);
                lista.add(empresa);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }

    public ArrayList<Empresa> buscarTodos() throws BbddException {
        String sql = "SELECT * FROM " + TABLA;
        ResultSet resultSet;
        ArrayList<Empresa> lista = new ArrayList<>();
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
