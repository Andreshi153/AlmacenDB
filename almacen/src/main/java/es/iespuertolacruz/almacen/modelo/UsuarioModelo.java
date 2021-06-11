package es.iespuertolacruz.almacen.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.Usuario;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class UsuarioModelo {
    
    SqLiteBbdd persistencia;
    final String TABLA = "usuarios";
    final String CLAVE = "nombre";
    
    /**
     * Constructor de la clase
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public UsuarioModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(TABLA, CLAVE, null, null);
    }

    /**
     * Funcion que busca un usuario en la bbdd
     * 
     * @param identificador del zona
     * @return usuario encontrado
     * @throws BbddException error controlado
     */
    public Usuario buscar(String identificador) throws BbddException {
        Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + "='" + identificador + "'";
        ResultSet resultSet;
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            resultSet = persistencia.buscarElementos(sql);
            lista = buscar(resultSet);
        } catch (BbddException e) {
            throw new BbddException("Se ha producido un error realizando la consulta", e);
        } finally {
            persistencia.closeConnection(persistencia.getConnection(), null, null);
        }

        if (!lista.isEmpty()) {
            usuarioEncontrado = lista.get(0);
        }
        return usuarioEncontrado;
    }

    /**
     * Funcion encargada de transformar un ResultSet en una lista de resultados
     * 
     * @param resultSet de entrada
     * @return lista de usuarios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Usuario> buscar(ResultSet resultSet) throws BbddException {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String user = resultSet.getString(CLAVE);
                String password = resultSet.getString("password");
                Usuario usuario = new Usuario(user, password);
                lista.add(usuario);
            }
        } catch (SQLException e) {
            throw new BbddException("Se ha producido un error realizando la transformacion a Cliente", e);
        } finally {
            persistencia.closeConnection(null, null, resultSet);
        }
        return lista;
    }
}
