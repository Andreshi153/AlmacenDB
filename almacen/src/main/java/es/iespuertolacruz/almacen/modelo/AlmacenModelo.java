package es.iespuertolacruz.almacen.modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class AlmacenModelo {
    
    SqLiteBbdd persistencia;
    //MySqlBbdd persistencia;

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public AlmacenModelo() throws BbddException, FicheroException {
        persistencia = new SqLiteBbdd(null, null);
        //persistencia = new MySqlBbdd("root", null);
    }
    
    //CRUD producto

    /**
     * Metodo que inserta un producto en la bbdd
     * @param producto a insertar
     * @throws BbddException controlado
     */
    public void insertar(Producto producto) throws BbddException {
        persistencia.insertar(producto);
    }
    /**
     * Metodo que elimina un producto de la bbdd
     * @param producto a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Producto producto) throws BbddException {
        persistencia.eliminar(producto);
    }
    /**
     * Metodo que modifica un producto de la bbdd
     * @param producto a modificar
     * @throws BbddException controlado
     */
    public void modificar(Producto producto) throws BbddException {
        persistencia.modificar(producto);
    }
    /**
     * Funcion que busca un producto en la bbdd
     * @param idProducto del producto a buscar
     * @throws BbddException controlado
     */
    public Producto obtenerProducto(int idProducto) throws BbddException {
        return persistencia.obtenerProducto(idProducto);
    }

    /**
     * Funcion que devuelve el listado de los productos
     * @return arraylist de productos
     * @throws BbddException controlado
     */
    public ArrayList<Producto> obtenerListadoProducto() throws BbddException {
        return persistencia.obtenerListadoProducto();
    }

    /**
     * Funcion que calcula el valor total de todos los productos del almacen
     * @return valor total de los productos
     * @throws BbddException controlado
     */
    public double obtenerValorProductosTotal() throws BbddException {
        return persistencia.obtenerValorProductosTotal();
    }

    /**
     * Funcion que obtiene los huecos totales y los ocupados del almacen
     * @return [huecos ocupados, huecos totales]
     * @throws BbddException controlado
     */
    public Integer[] obtenerHuecosOcupados() throws BbddException {
        return persistencia.obtenerHuecosOcupados();
    }

    //CRUD zona

    /**
     * Metodo que inserta una zona en la bbdd
     * @param zona a insertar
     * @throws BbddException controlado
     */
    public void insertar(Zona zona) throws BbddException {
        persistencia.insertar(zona);
    }
    /**
     * Metodo que elimina una zona de la bbdd
     * @param zona a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Zona zona) throws BbddException {
        persistencia.eliminar(zona);
    }
    /**
     * Metodo que modifica una zona de la bbdd
     * @param zona a modificar
     * @throws BbddException controlado
     */
    public void modificar(Zona zona) throws BbddException {
        persistencia.modificar(zona);
    }
    /**
     * Funcion que busca una zona en la bbdd
     * @param idZona de la zona a buscar
     * @throws BbddException controlado
     */
    public Zona obtenerZona(char idZona) throws BbddException {
        return persistencia.obtenerZona(idZona);
    }

    //CRUD estanteria
    /**
     * Metodo que inserta una estanteria en la bbdd
     * @param estanteria a insertar
     * @throws BbddException controlado
     */
    public void insertar(Estanteria estanteria) throws BbddException {
        persistencia.insertar(estanteria);
    }
    /**
     * Metodo que elimina una estanteria de la bbdd
     * @param estanteria a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Estanteria estanteria) throws BbddException {
        persistencia.eliminar(estanteria);
    }
    /**
     * Metodo que modifica una estanteria de la bbdd
     * @param estanteria a modificar
     * @throws BbddException controlado
     */
    public void modificar(Estanteria estanteria) throws BbddException {
        persistencia.modificar(estanteria);
    }
    /**
     * Funcion que busca una estanteria en la bbdd
     * @param idEstanteria de la estanteria a buscar
     * @throws BbddException controlado
     */
    public Estanteria obtenerEstanteria(int idEstanteria) throws BbddException {
        return persistencia.obtenerEstanteria(idEstanteria);
    }

    //CRUD productoEstanteria
    /**
     * Metodo que inserta un productoEstanteria en la bbdd
     * @param productoEstanteria a insertar
     * @throws BbddException controlado
     */
    public void insertar(ProductoEstanteria productoEstanteria) throws BbddException {
        persistencia.insertar(productoEstanteria);
    }
    /**
     * Metodo que elimina un productoEstanteria de la bbdd
     * @param productoEstanteria a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(ProductoEstanteria productoEstanteria) throws BbddException {
        persistencia.eliminar(productoEstanteria);
    }
    /**
     * Metodo que modifica un productoEstanteria de la bbdd
     * @param productoEstanteria a modificar
     * @throws BbddException controlado
     */
    public void modificar(ProductoEstanteria productoEstanteria) throws BbddException {
        persistencia.modificar(productoEstanteria);
    }
    /**
     * Funcion que busca un productoEstanteria en la bbdd
     * @param idProducto del productoEstanteria a buscar
     * @param idEstanteria del productoEstanteria a buscar
     * @throws BbddException controlado
     */
    public ProductoEstanteria obtenerProductoEstanteria(int idProducto, int idEstanteria) throws BbddException {
        return persistencia.obtenerProductoEstanteria(idProducto, idEstanteria);
    }

    //CRUD listaProductos

    /**
     * Metodo que inserta una listaProductos en la bbdd
     * @param listaProductos a insertar
     * @throws BbddException controlado
     */
    public void insertar(ListaProductos listaProductos) throws BbddException {
        persistencia.insertar(listaProductos);
    }
    /**
     * Metodo que elimina una listaProductos de la bbdd
     * @param listaProductos a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(ListaProductos listaProductos) throws BbddException {
        persistencia.eliminar(listaProductos);
    }
    /**
     * Metodo que modifica una listaProductos de la bbdd
     * @param listaProductos a modificar
     * @throws BbddException controlado
     */
    public void modificar(ListaProductos listaProductos) throws BbddException {
        persistencia.modificar(listaProductos);
    }
    /**
     * Funcion que busca una listaProductos en la bbdd
     * @param idListaProductos de la listaProductos a buscar
     * @throws BbddException controlado
     */
    public ListaProductos obtenerListaProductos(int idListaProductos) throws BbddException {
        return persistencia.obtenerListaProductos(idListaProductos);
    }

    //CRUD muelle
    /**
     * Metodo que inserta un muelle en la bbdd
     * @param muelle a insertar
     * @throws BbddException controlado
     */
    public void insertar(Muelle muelle) throws BbddException {
        persistencia.insertar(muelle);
    }
    /**
     * Metodo que elimina un muelle de la bbdd
     * @param muelle a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Muelle muelle) throws BbddException {
        persistencia.eliminar(muelle);
    }
    /**
     * Metodo que modifica un muelle de la bbdd
     * @param muelle a modificar
     * @throws BbddException controlado
     */
    public void modificar(Muelle muelle) throws BbddException {
        persistencia.modificar(muelle);
    }
    /**
     * Funcion que busca un muelle en la bbdd
     * @param idMuelle del muelle a buscar
     * @throws BbddException controlado
     */
    public Muelle obtenerMuelle(int idMuelle) throws BbddException {
        return persistencia.obtenerMuelle(idMuelle);
    }

    //CRUD empresa
    /**
     * Metodo que inserta una empresa en la bbdd
     * @param empresa a insertar
     * @throws BbddException controlado
     */
    public void insertar(Empresa empresa) throws BbddException {
        persistencia.insertar(empresa);
    }
    /**
     * Metodo que elimina una empresa de la bbdd
     * @param empresa a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Empresa empresa) throws BbddException {
        persistencia.eliminar(empresa);
    }
    /**
     * Metodo que modifica una empresa de la bbdd
     * @param empresa a modificar
     * @throws BbddException controlado
     */
    public void modificar(Empresa empresa) throws BbddException {
        persistencia.modificar(empresa);
    }
    /**
     * Funcion que busca una empresa en la bbdd
     * @param cif de la empresa a buscar
     * @throws BbddException controlado
     */
    public Empresa obtenerEmpresa(String cif) throws BbddException {
        return persistencia.obtenerEmpresa(cif);
    }

    //CRUD cliente
    /**
     * Metodo que inserta un cliente en la bbdd
     * @param cliente a insertar
     * @throws BbddException controlado
     */
    public void insertar(Cliente cliente) throws BbddException {
        persistencia.insertar(cliente);
    }
    /**
     * Metodo que elimina un cliente de la bbdd
     * @param cliente a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Cliente cliente) throws BbddException {
        persistencia.eliminar(cliente);
    }
    /**
     * Metodo que modifica un cliente de la bbdd
     * @param cliente a modificar
     * @throws BbddException controlado
     */
    public void modificar(Cliente cliente) throws BbddException {
        persistencia.modificar(cliente);
    }
    /**
     * Funcion que busca un cliente en la bbdd
     * @param cif del cliente a buscar
     * @throws BbddException controlado
     */
    public Cliente obtenerCliente(String cif) throws BbddException {
        return persistencia.obtenerCliente(cif);
    }

    /**
     * Funcion que devuelve el listado de clientes
     * @return arraylist de clientes
     * @throws BbddException controlado
     */
    public ArrayList<Cliente> obtenerListadoCliente() throws BbddException {
        return persistencia.obtenerListadoCliente();
    }
    
    //CRUD proveedor
    /**
     * Metodo que inserta un proveedor en la bbdd
     * @param proveedor a insertar
     * @throws BbddException controlado
     */
    public void insertar(Proveedor proveedor) throws BbddException {
        persistencia.insertar(proveedor);
    }
    /**
     * Metodo que elimina un proveedor de la bbdd
     * @param proveedor a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Proveedor proveedor) throws BbddException {
        persistencia.eliminar(proveedor);
    }
    /**
     * Metodo que modifica un proveedor de la bbdd
     * @param proveedor a modificar
     * @throws BbddException controlado
     */
    public void modificar(Proveedor proveedor) throws BbddException {
        persistencia.modificar(proveedor);
    }
    /**
     * Funcion que busca un proveedor en la bbdd
     * @param cif del proveedor a buscar
     * @throws BbddException controlado
     */
    public Proveedor obtenerProveedor(String cif) throws BbddException {
        return persistencia.obtenerProveedor(cif);
    }
    /**
     * Funcion que devuelve el listado de proveedores
     * @return arraylist de proveedores
     * @throws BbddException controlado
     */
    public ArrayList<Proveedor> obtenerListadoProveedor() throws BbddException {
        return persistencia.obtenerListadoProveedor();
    }

    //CRUD operacion
    
    /**
     * Metodo que inserta una operacion en la bbdd
     * @param operacion a insertar
     * @throws BbddException controlado
     */
    public void insertar(Operacion operacion) throws BbddException {
        persistencia.insertar(operacion);
    }
    /**
     * Metodo que elimina una operacion de la bbdd
     * @param operacion a eliminar
     * @throws BbddException controlado
     */
    public void eliminar(Operacion operacion) throws BbddException {
        persistencia.eliminar(operacion);
    }
    /**
     * Metodo que modifica una operacion de la bbdd
     * @param operacion a modificar
     * @throws BbddException controlado
     */
    public void modificar(Operacion operacion) throws BbddException {
        persistencia.modificar(operacion);
    }
    /**
     * Funcion que busca una operacion en la bbdd
     * @param idListaProductos de la operacion a buscar
     * @throws BbddException controlado
     */
    public Operacion obtenerOperacion(int idListaProductos) throws BbddException {
        return persistencia.obtenerOperacion(idListaProductos);
    }
}