package es.iespuertolacruz.almacen.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import es.iespuertolacruz.almacen.api.Cliente;
import es.iespuertolacruz.almacen.api.Empresa;
import es.iespuertolacruz.almacen.api.Estanteria;
import es.iespuertolacruz.almacen.api.ListaProductos;
import es.iespuertolacruz.almacen.api.Muelle;
import es.iespuertolacruz.almacen.api.Operacion;
import es.iespuertolacruz.almacen.api.Producto;
import es.iespuertolacruz.almacen.api.ProductoEstanteria;
import es.iespuertolacruz.almacen.api.Proveedor;
import es.iespuertolacruz.almacen.api.Zona;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class Bbdd {

    /**
     * Strings constantes
     */
    private static final String ID_ZONA = "id_zona";
    private static final String SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA = "Se ha producido un error realizando la consulta";
    private static final String ID_PRODUCTO = "id_producto";
    private static final String WHERE_CIF = " WHERE cif = '";
    private static final String VALUES = " VALUES ('";

    private String driver;
    private String url;
    private String usuario;
    private String password;

    ArrayList<String> listaTablas;

    public Bbdd(String driver, String url, String usuario, String password) throws BbddException, FicheroException {
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        if(listaTablas == null) {
            listaTablas = new ArrayList<>();
            String[] nombresTablas = "producto,zona,estanteria,producto_estanteria,lista_productos,muelle,empresa,cliente,proveedor,operacion".split(",");
            Collections.addAll(listaTablas, nombresTablas);
        }
        init();
    }

    private void init() throws BbddException, FicheroException {
        for (String tabla : listaTablas) {
            if(!existeTabla(tabla)) {
                String crearTabla = new Fichero().leer("resources/sql/"+tabla+".crear.sql");
                System.out.println(tabla);
                actualizar(crearTabla);
                String insertElemento = new Fichero().leer("resources/sql/"+tabla+".insertar.sql");
                actualizar(insertElemento);
            }
        }
    }

    /**
     * Funcion encargada de verificar si una tabla existe
     * @param nombreTabla a verificar
     * @return true/false
     * @throws BbddException
     */
    private boolean existeTabla(String nombreTabla) throws BbddException {
        Connection connection = null;
        ResultSet resultSet = null;
        boolean existe = false;
        try {
            connection = getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            resultSet = meta.getTables(null, null, nombreTabla.toUpperCase(), new String[] {"TABLE"});
            existe = resultSet.next();
        } catch (Exception ex) {
            throw new BbddException("Se ha producido un error verificando las tablas", ex);
        } finally {
            closeConnection(connection, null, resultSet);
        }
        return existe;
    }

    /**
     * Funcion encargada de realizar la conexion con la BBDD
     * @return la coneccion
     * @throws BbddException controlado
     */
    private Connection getConnection() throws BbddException {
        Connection connection = null;

        try {
            //Class.forName(driver);
            if (usuario == null || password == null) {
                connection = DriverManager.getConnection(url);
            } else {
                DriverManager.getConnection(url, usuario, password);
            }
        } catch (Exception exception) {
            throw new BbddException("No se ha podido establecer la coneccion con la BBDD", exception);
        }
        return connection;
    }
     /**
     * Metodo que cierra las conexiciones con la base de datos
     * @param connection
     * @param statement
     * @param resultSet
     * @throws BbddException
     */
    private void closeConnection(Connection connection, Statement statement, ResultSet resultSet) throws BbddException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception exception) {
            throw new BbddException("Se ha producido un error cerrando la conexion", exception);
        }
    }

    /* Inserciones */

    /**
     * Metodo encargado de realizar la insercion de un producto
     * @param producto a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Producto producto) throws BbddException {
        String sql = "INSERT INTO producto (nombre, precio_unitario, tipo) " + VALUES + producto.getNombre() + "',"
                + " '" + producto.getPrecioUnitario() + "', '" + producto.getTipo() + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de una zona
     * @param zona a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Zona zona) throws BbddException {
        String sql = "INSERT INTO zona (id_zona, tipo) " + VALUES + zona.getIdZona() + "'," + " '" + zona.getTipo()
                + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de una estanteria
     * @param estanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Estanteria estanteria) throws BbddException {
        String sql = "INSERT INTO estanteria (idZona, numAlturas) " + VALUES + estanteria.getIdZona() + "'," + " '"
                + estanteria.getNumAlturas() + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de un productoEstanteria
     * @param productoEstanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(ProductoEstanteria productoEstanteria) throws BbddException {
        String sql = "INSERT INTO productoEstanteria " + VALUES + productoEstanteria.getIdEstanteria() + "', '"
                + productoEstanteria.getIdEstanteria() + "', '" + productoEstanteria.getCantidad() + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de una listaProductos 
     * @param listaProductos a insertar
     * @throws BbddException error controlado
     */
    public void insertar(ListaProductos listaProductos) throws BbddException {
        HashMap<Integer, Integer> mapaListaProductos = listaProductos.getLista();
        StringBuilder sql = new StringBuilder();
        mapaListaProductos.forEach(
                (producto, cantidad) -> sql.append("INSERT INTO lista_productos (id_lista_productos, id_zona, tipo)"
                        + VALUES + listaProductos.getIdListaProductos() + "'," + producto + ", " + cantidad + ")"));
        actualizar(sql.toString());
    }

    /**
     * Metodo encargado de realizar la insercion de un muelle
     * @param muelle a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Muelle muelle) throws BbddException {
        String sql = "INSERT INTO muelle (id_zona)" + VALUES + muelle.getIdZona() + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de un estanteria
     * @param estanteria a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Empresa empresa) throws BbddException {
        String sql = "INSERT INTO empresa " + VALUES + empresa.getCif() + "', '" + empresa.getNombre() + "', '"
                + empresa.getDireccion() + "', '" + empresa.getTelefono() + "', '" + empresa.getCorreo() + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de un cliente
     * @param cliente a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Cliente cliente) throws BbddException {
        String sql = "INSERT INTO cliente " + VALUES + cliente.getCif() + "', '" + cliente.getPorcentajeDesc() + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de un proveedor
     * @param proveedor a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Proveedor proveedor) throws BbddException {
        String sql = "INSERT INTO proveedor " + VALUES + proveedor.getCif() + "', '" + proveedor.getTipoProducto()
                + "')";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la insercion de una operacion
     * @param operacion a insertar
     * @throws BbddException error controlado
     */
    public void insertar(Operacion operacion) throws BbddException {
        String sql = "INSERT INTO operacion " + VALUES + operacion.getIdListaProductos() + "', '"
                + operacion.getIdMuelle() + "', '" + operacion.getFecha() + "', '" + operacion.getTipoOperacion()
                + "', '" + operacion.getCif() + "')";
        actualizar(sql);
    }

    /* Eliminaciones */

    /**
     * Metodo encargado de eliminar un producto
     * @param producto a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Producto producto) throws BbddException {
        String sql = "DELETE FROM producto WHERE id_producto = '" + producto.getIdProducto() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una zona
     * @param zona a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Zona zona) throws BbddException {
        String sql = "DELETE FROM zona WHERE id_zona = '" + zona.getIdZona() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una estanteria
     * @param estanteria a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Estanteria estanteria) throws BbddException {
        String sql = "DELETE FROM estanteria WHERE id_estanteria = '" + estanteria.getIdEstanteria() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un productoEstanteria
     * @param productoEstanteria a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(ProductoEstanteria productoEstanteria) throws BbddException {
        String sql = "DELETE FROM producto_estanteria WHERE id_producto = '" + productoEstanteria.getIdProducto()
                + "AND id_estanteria = '" + productoEstanteria.getIdEstanteria() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una listaProducto
     * @param listaProducto a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(ListaProductos listaProducto) throws BbddException {
        String sql = "DELETE FROM listaProducto WHERE id_lista_productos = '" + listaProducto.getIdListaProductos()
                + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un muelle
     * @param muelle a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Muelle muelle) throws BbddException {
        String sql = "DELETE FROM muelle WHERE id_muelle = '" + muelle.getIdMuelle() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una empresa
     * @param empresa a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Empresa empresa) throws BbddException {
        String sql = "DELETE FROM empresa WHERE cif = '" + empresa.getCif() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un cliente
     * @param cliente a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Cliente cliente) throws BbddException {
        String sql = "DELETE FROM cliente WHERE cif = '" + cliente.getCif() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar un proveedor
     * @param proveedor a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Proveedor proveedor) throws BbddException {
        String sql = "DELETE FROM proveedor WHERE cif = '" + proveedor.getCif() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de eliminar una operacion
     * @param operacion a eliminar
     * @throws BbddException error controlado
     */
    public void eliminar(Operacion operacion) throws BbddException {
        String sql = "DELETE FROM operacion WHERE id_lista_productos = '" + operacion.getIdListaProductos() + "'";
        actualizar(sql);
    }

    /* Modificaciones */

    /**
     * Metodo encargado de realizar la modificacion de un producto
     * @param producto a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Producto producto) throws BbddException {
        String sql = "UPDATE producto SET nombre = '" + producto.getNombre() + "'," + " precio_unitario = '"
                + producto.getPrecioUnitario() + "', tipo ='" + producto.getTipo() + "'" + " WHERE id_producto = '"
                + producto.getIdProducto() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una zona
     * @param zona a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Zona zona) throws BbddException {
        String sql = "UPDATE zona SET id_zona = '" + zona.getTipo() + "'" + " WHERE id_zona = '" + zona.getIdZona()
                + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una estanteria
     * @param estanteria a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Estanteria estanteria) throws BbddException {
        String sql = "UPDATE estanteria SET id_zona = '" + estanteria.getIdZona() + "'," + " num_alturas = '"
                + estanteria.getNumAlturas() + "'" + " WHERE id_estanteria = '" + estanteria.getIdEstanteria() + "'";
        actualizar(sql);
    }
    
    /**
     * Metodo encargado de realizar la modificacion de un productoEstanteria
     * @param productoEstanteria a modificar
     * @throws BbddException error controlado
     */
    public void modificar(ProductoEstanteria productoEstanteria) throws BbddException {
        String sql = "UPDATE producto_estanteria SET cantidad = '" + productoEstanteria.getCantidad()
                + "' WHERE id_producto = '" + productoEstanteria.getIdProducto() + "AND id_estanteria = '"
                + productoEstanteria.getIdEstanteria() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una listaProducto
     * @param listaProducto a modificar
     * @throws BbddException error controlado
     */
    public void modificar(ListaProductos listaProducto) throws BbddException {
        eliminar(listaProducto);
        insertar(listaProducto);
    }

    /**
     * Metodo encargado de realizar la modificacion de un muelle
     * @param muelle a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Muelle muelle) throws BbddException {
        String sql = "UPDATE muelle SET id_zona = '" + muelle.getIdZona() + "', disponible = '"
                + (muelle.getDisponible() ? "true" : "false") + "', WHERE id_muelle = " + muelle.getIdMuelle() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una empresa
     * @param empresa a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Empresa empresa) throws BbddException {
        String sql = "UPDATE empresa SET nombre = '" + empresa.getNombre() + "', direccion = '" + empresa.getDireccion()
                + "', " + "telefono = '" + empresa.getTelefono() + "', correo = '" + empresa.getCorreo() + "'"
                + WHERE_CIF + empresa.getCif() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un cliente 
     * @param cliente a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Cliente cliente) throws BbddException {
        String sql = "UPDATE cliente SET porcentaje_descuento = '" + cliente.getPorcentajeDesc() + "'" + WHERE_CIF
                + cliente.getCif() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de un proveedor 
     * @param proveedor a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Proveedor proveedor) throws BbddException {
        String sql = "UPDATE proveedor SET tipo_producto = '" + proveedor.getTipoProducto() + "'" + WHERE_CIF
                + proveedor.getCif() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion de una operacion
     * @param operacion a modificar
     * @throws BbddException error controlado
     */
    public void modificar(Operacion operacion) throws BbddException {
        String sql = "UPDATE operacion SET id_muelle = '" + operacion.getIdMuelle() + "', fecha = '"
                + operacion.getFecha() + "', tipo_operacion = '" + operacion.getTipoOperacion() + "', cif = '"
                + operacion.getCif() + "', WHERE id_lista_productos = " + operacion.getIdListaProductos() + "'";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la actualizacion de la BBDD
     * @param sql a ejecutar
     * @throws BbddException error controlado
     */
    private void actualizar(String sql) throws BbddException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            closeConnection(connection, statement, null);
        }

    }

    /**
     * Funcion que obtiene el hashmpa de una lista de productos
     * @param sql de la consulta
     * @return hashmap de producto y cantidad
     * @throws BbddException error controlado
     */
    private HashMap<Integer, Integer> obtenerHashMapListaProducto(String sql) throws BbddException {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        HashMap<Integer, Integer> listaProductos = new HashMap<>();
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idProducto = resultSet.getInt(ID_PRODUCTO);
                int cantidad = resultSet.getInt("cantidad");
                listaProductos.put(idProducto, cantidad);
            }
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return listaProductos;
    }
    /**
     * Funcion que realiza la consulta sobre la BBDD
     * @param sql de la consulta
     * @return lista de resultados
     * @throws BbddException controlado
     */
    private ArrayList<Object> obtenerListados(String sql) throws BbddException {
        ArrayList<Object> listado = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (sql.contains(" producto ")) {
                    int idProducto = resultSet.getInt(ID_PRODUCTO);
                    String nombre = resultSet.getString("nombre");
                    float precio = resultSet.getFloat("precio_unitario");
                    String tipo = resultSet.getString("tipo");
                    Producto producto = new Producto(idProducto, nombre, precio, tipo);
                    listado.add(producto);
                } else if (sql.contains(" zona ")) {
                    char idZona = resultSet.getString(ID_ZONA).charAt(0);
                    String tipo = resultSet.getString("tipo");
                    Zona zona = new Zona(idZona, tipo);
                    listado.add(zona);
                } else if (sql.contains(" estanteria ")) {
                    int idEstanteria = resultSet.getInt("id_estanteria");
                    char idZona = resultSet.getString(ID_ZONA).charAt(0);
                    int numAlturas = resultSet.getInt("num_alturas");
                    int numHuecosOcupados = resultSet.getInt("num_huecos_ocupados");
                    Estanteria estanteria = new Estanteria(idEstanteria, idZona, numAlturas, numHuecosOcupados);
                    listado.add(estanteria);
                } else if (sql.contains(" producto_estanteria ")) {
                    int idProducto = resultSet.getInt(ID_PRODUCTO);
                    int idEstanteria = resultSet.getInt("id_estanteria");
                    int cantidad = resultSet.getInt("cantidad");
                    ProductoEstanteria productoEstanteria = new ProductoEstanteria(idProducto, idEstanteria, cantidad);
                    listado.add(productoEstanteria);
                 }else if (sql.contains(" lista_producto ")) {
                    int idListaProductos = resultSet.getInt("id_lista_producto");
                    String sqlString = "SELECT id_producto, cantidad FROM lista_producto WHERE id_lista_producto = " + idListaProductos;
                    HashMap<Integer, Integer> mapaListaProducto = obtenerHashMapListaProducto(sqlString);
                    ListaProductos listaProductos = new ListaProductos(idListaProductos, mapaListaProducto);
                    listado.add(listaProductos);
                } else if (sql.contains(" muelle ")) {
                    int idMuelle = resultSet.getInt("id_muelle");
                    char idZona = resultSet.getString(ID_ZONA).charAt(0);
                    boolean disponible = resultSet.getBoolean("disponible");
                    Muelle muelle = new Muelle(idMuelle, idZona, disponible);
                    listado.add(muelle);
                } else if (sql.contains(" empresa ")) {
                    String cif = resultSet.getString("cif");
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String telefono = resultSet.getString("telefono");
                    String correo = resultSet.getString("correo");
                    Empresa empresa = new Empresa(cif, nombre, direccion, telefono, correo);
                    listado.add(empresa);
                } else if (sql.contains(" cliente ")) {
                    String cif = resultSet.getString("cif");
                    float porcentajeDesc = resultSet.getFloat("porcentaje_desc");
                    Cliente cliente = new Cliente(cif, porcentajeDesc);
                    listado.add(cliente);
                } else if (sql.contains(" proveedor ")) {
                    String cif = resultSet.getString("cif");
                    String tipoProducto = resultSet.getString("tipo_producto");
                    Proveedor proveedor = new Proveedor(cif, tipoProducto);
                    listado.add(proveedor);
                }  else if (sql.contains(" operacion ")) {
                    int idListaProductos = resultSet.getInt("id_lista_productos");
                    int idMuelle = resultSet.getInt("id_muelle");
                    String fecha = resultSet.getString("fecha");
                    String tipoOperacion = resultSet.getString("tipo_operacion");
                    String cif = resultSet.getString("cif");
                    Operacion operacion = new Operacion(idListaProductos, idMuelle, fecha, tipoOperacion, cif);
                    listado.add(operacion);
                }
            }
        } catch (Exception exception) {
            throw new BbddException(SE_HA_PRODUCIDO_UN_ERROR_REALIZANDO_LA_CONSULTA, exception);
        } finally {
            closeConnection(connection, statement, resultSet);
        }
        return listado;
    }

    /**
     * Funcion que obtiene el listado de todos los productos
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Producto> obtenerListadoProducto() throws BbddException {
        String sql = "SELECT * FROM producto ";
        return (ArrayList<Producto>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todas las zonas
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Zona> obtenerListadoZona() throws BbddException {
        String sql = "SELECT * FROM zona ";
        return (ArrayList<Zona>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todas las estanterias
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Estanteria> obtenerListadoEstanteria() throws BbddException {
        String sql = "SELECT * FROM estanteria ";
        return (ArrayList<Estanteria>) (ArrayList<?>) obtenerListados(sql);
    }
    
    /**
     * Funcion que obtiene el listado de todos los productosEstanteria
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<ProductoEstanteria> obtenerListadoProductoEstanteria() throws BbddException {
        String sql = "SELECT *  FROM producto_estanteria ";
        return (ArrayList<ProductoEstanteria>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todas las listaProducto
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<ListaProductos> obtenerListadoListaProducto() throws BbddException {
        String sql = "SELECT DISTINCT id_lista_producto FROM lista_producto ";
        return (ArrayList<ListaProductos>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todos los muelles
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Muelle> obtenerListadoMuelle() throws BbddException {
        String sql = "SELECT *  FROM muelle ";
        return (ArrayList<Muelle>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todas las empresas
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Empresa> obtenerListadoEmpresa() throws BbddException {
        String sql = "SELECT * FROM empresa ";
        return (ArrayList<Empresa>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todos los clientes
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Cliente> obtenerListadoCliente() throws BbddException {
        String sql = "SELECT * FROM cliente ";
        return (ArrayList<Cliente>) (ArrayList<?>) obtenerListados(sql);
    }

    /**
     * Funcion que obtiene el listado de todos los proveedores
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Proveedor> obtenerListadoProveedor() throws BbddException {
        String sql = "SELECT * FROM proveedor ";
        return (ArrayList<Proveedor>) (ArrayList<?>) obtenerListados(sql);
    }
    
    /**
     * Funcion que obtiene el listado de todos las operaciones
     * @return lista
     * @throws BbddException controlado
     */
    public ArrayList<Operacion> obtenerListadoOperacion() throws BbddException {
        String sql = "SELECT *  FROM operacion ";
        return (ArrayList<Operacion>) (ArrayList<?>) obtenerListados(sql);
    }
    


    /**
     * Funcion que obtiene un producto
     * @param idProducto identificador del producto
     * @return producto encontrado
     * @throws BbddException controlado
     */
    public Producto obtenerProducto(int idProducto) throws BbddException {
        Producto producto = null;
        ArrayList<Producto> lista = null;
        String sql = "SELECT * FROM producto WHERE id_producto = " + idProducto;
        lista = (ArrayList<Producto>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            producto = lista.get(0);
        }
        return producto;
    }

    /**
     * Funcion que obtiene una zona
     * @param idZona identificador de la zona
     * @return zona encontrada
     * @throws BbddException controlado
     */
    public Zona obtenerZona(char idZona) throws BbddException {
        Zona zona = null;
        ArrayList<Zona> lista = null;
        String sql = "SELECT * FROM producto WHERE id_producto = '" + idZona + "'";
        lista = (ArrayList<Zona>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            zona = lista.get(0);
        }
        return zona;
    }

    /**
     * Funcion que obtiene una estanteria
     * @param idEstanteria identificador de la estanteria
     * @return estanteria encontrada
     * @throws BbddException controlado
     */
    public Estanteria obtenerEstanteria(int idEstanteria) throws BbddException {
        Estanteria estanteria = null;
        ArrayList<Estanteria> lista = null;
        String sql = "SELECT * FROM estanteria WHERE id_producto = " + idEstanteria;
        lista = (ArrayList<Estanteria>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            estanteria = lista.get(0);
        }
        return estanteria;
    }

    /**
     * Funcion que obtiene un productoEstanteria
     * @param idProducto identificador del productoEstanteria
     * @param idEstanteria identificador del productoEstanteria
     * @return productoEstanteria encontrado
     * @throws BbddException controlado
     */
    public ProductoEstanteria obtenerProductoEstanteria(int idProducto, int idEstanteria) throws BbddException {
        ProductoEstanteria productoEstanteria = null;
        ArrayList<ProductoEstanteria> lista = null;
        String sql = "SELECT * FROM producto_estanteria WHERE id_producto = '" + idProducto + "' AND id_estanteria = '" + idEstanteria + "'";
        lista = (ArrayList<ProductoEstanteria>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            productoEstanteria = lista.get(0);
        }
        return productoEstanteria;
    }

    /**
     * Funcion que obtiene una listaProductos
     * @param idListaProductos identificador de la listaProductos
     * @return listaProductos encontrada
     * @throws BbddException controlado
     */
    public ListaProductos obtenerListaProductos(int idListaProductos) throws BbddException {
        ListaProductos listaProductos = null;
        ArrayList<ListaProductos> lista = null;
        String sql = "SELECT * FROM lista_productos WHERE id_lista_producto = " + idListaProductos + " ";
        lista = (ArrayList<ListaProductos>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            listaProductos = lista.get(0);
        }
        return listaProductos;
    }

    /**
     * Funcion que obtiene un muelle
     * @param idMuelle identificador del muelle
     * @return muelle encontrado
     * @throws BbddException controlado
     */
    public Muelle obtenerMuelle(int idMuelle) throws BbddException {
        Muelle muelle = null;
        ArrayList<Muelle> lista = null;
        String sql = "SELECT * FROM muelle WHERE id_muelle = " + idMuelle + " ";
        lista = (ArrayList<Muelle>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            muelle = lista.get(0);
        }
        return muelle;
    }

    /**
     * Funcion que obtiene una empresa
     * @param cif identificador de la empresa
     * @return empresa encontrada
     * @throws BbddException controlado
     */
    public Empresa obtenerEmpresa(String cif) throws BbddException {
        Empresa empresa = null;
        ArrayList<Empresa> lista = null;
        String sql = "SELECT * FROM empresa WHERE cif = '" + cif + "'";
        lista = (ArrayList<Empresa>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            empresa = lista.get(0);
        }
        return empresa;
    }

    /**
     * Funcion que obtiene un cliente
     * @param cif identificador del cliente
     * @return cliente encontrado
     * @throws BbddException controlado
     */
    public Cliente obtenerCliente(String cif) throws BbddException {
        Cliente cliente = null;
        ArrayList<Cliente> lista = null;
        String sql = "SELECT * FROM cliente WHERE cif = '" + cif + "'";
        lista = (ArrayList<Cliente>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            cliente = lista.get(0);
        }
        return cliente;
    }

    /**
     * Funcion que obtiene un proveedor
     * @param cif identificador del proveedor
     * @return proveedor encontrado
     * @throws BbddException controlado
     */
    public Proveedor obtenerProveedor(String cif) throws BbddException {
        Proveedor proveedor = null;
        ArrayList<Proveedor> lista = null;
        String sql = "SELECT * FROM proveedor WHERE cif = '" + cif + "'";
        lista = (ArrayList<Proveedor>) (ArrayList<?>) obtenerListados(sql);
        if (!lista.isEmpty()) {
            proveedor = lista.get(0);
        }
        return proveedor;
    }
    
    /**
     * Funcion que obtiene una operacion
     * @param idListaProductos identificador de la operacion
     * @return operacion encontrada
     * @throws BbddException controlado
     */
    public Operacion obtenerOperacion(int idListaProductos) throws BbddException {
        Operacion producto = null;
        ArrayList<Operacion> listaProducto = null;
        String sql = "SELECT * FROM operacion WHERE id_lista_productos = " + idListaProductos + " ";
        listaProducto = (ArrayList<Operacion>) (ArrayList<?>) obtenerListados(sql);
        if (!listaProducto.isEmpty()) {
            producto = listaProducto.get(0);
        }
        return producto;
    }
}