package es.iespuertolacruz.almacen.controlador;

import java.util.ArrayList;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;
import es.iespuertolacruz.almacen.modelo.AlmacenModelo;

public class AlmacenController {
    
    private static final String EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z = "El id de la zona debe estar entre la A y la Z\n";
    AlmacenModelo almacenModelo;

    /**
     * Constructor de la clase
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public AlmacenController() throws BbddException, FicheroException {
        almacenModelo = new AlmacenModelo();
    }

    /**
     * Metodo que valida si un producto es valida
     * @param producto a validar
     * @throws AlmacenException controlado
     */
    public void validarProducto(Producto producto) throws AlmacenException {
        String mensaje = "";
        if (producto == null) {
            throw new AlmacenException("El producto no puede ser nulo");
        }
        if (producto.getIdProducto() < 1) {
            mensaje += "El id del producto no puede ser inferior a 1\n";
        }
        if (producto.getNombre().isEmpty() || producto.getNombre() == null) {
            mensaje += "El nombre del producto no puede ser nulo o vacio\n";
        }
        if (producto.getPrecioUnitario() <= 0) {
            mensaje += "El precio del producto no puede ser menor o igual que 0\n";
        }
        if (producto.getTipo().isEmpty() || producto.getTipo() == null) {
            mensaje += "El tipo del producto no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si una zona es valida
     * @param zona a validar
     * @throws AlmacenException controlado
     */
    public void validarZona(Zona zona) throws AlmacenException {
        String mensaje = "";
        if (zona == null) {
            throw new AlmacenException("La zona no puede ser nula");
        }
        if (zona.getIdZona() < 'A' || zona.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if (zona.getTipo().isEmpty() || zona.getTipo() == null) {
            mensaje += "El tipo de la zona no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si una estanteria es valida
     * @param estanteria a validar
     * @throws AlmacenException controlado
     */
    public void validarEstanteria(Estanteria estanteria) throws AlmacenException {
        String mensaje = "";
        if (estanteria == null) {
            throw new AlmacenException("La estanteria no puede ser nula");
        }
        if (estanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (estanteria.getIdZona() < 'A' || estanteria.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if (estanteria.getNumAlturas() <= 0) {
            mensaje += "El numero de alturas de la estanteria no puede ser menor o igual que 0\n";
        }
        if (estanteria.getNumHuecosOcupados() > estanteria.getNumAlturas()) {
            mensaje += "El numero de huecos ocupados de la estanteria no puede ser mayor que el numero de alturas\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si un productoEstanteria es valido
     * @param productoEstanteria a validar
     * @throws AlmacenException controlado
     */
    public void validarProductoEstanteria(ProductoEstanteria productoEstanteria) throws AlmacenException {
        String mensaje = "";
        if (productoEstanteria == null) {
            throw new AlmacenException("El productoEstanteria no puede ser nulo");
        }
        if (productoEstanteria.getIdProducto() <= 0) {
            mensaje += "El id del producto no puede ser menor o igual que 0\n";
        }
        if (productoEstanteria.getIdEstanteria() <= 0) {
            mensaje += "El id de la estanteria no puede ser menor o igual que 0\n";
        }
        if (productoEstanteria.getCantidad() <= 0) {
            mensaje += "La cantidad de productos no puede ser menor o igual que 0\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si una lista de productos es valida
     * @param lista de productos a validar
     * @throws AlmacenException controlado
     */
    public void validarListaProductos(ListaProductos listaProductos) throws AlmacenException {
        String mensaje = "";
        if (listaProductos == null) {
            throw new AlmacenException("La listaProductos no puede ser nula");
        }
        if (listaProductos.getIdListaProductos() <= 0) {
            mensaje += "El id de la listaProductos no puede ser menor o igual que 0\n";
        }
        if (listaProductos.getLista().isEmpty()) {
            mensaje += "La lista de productos de listaProductos no puede estar vacia\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si un muelle es valido
     * @param muelle a validar
     * @throws AlmacenException controlado
     */
    public void validarMuelle(Muelle muelle) throws AlmacenException {
        String mensaje = "";
        if (muelle == null) {
            throw new AlmacenException("El muelle no puede ser nula");
        }
        if (muelle.getIdMuelle() <= 0) {
            mensaje += "El id del muelle no puede ser menor o igual que 0\n";
        }
        if (muelle.getIdZona() < 'A' || muelle.getIdZona() > 'Z') {
            mensaje += EL_ID_DE_LA_ZONA_DEBE_ESTAR_ENTRE_LA_A_Y_LA_Z;
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si una empresa es valida
     * @param empresa a validar
     * @throws AlmacenException controlado
     */
    public void validarEmpresa(Empresa empresa) throws AlmacenException {
        String mensaje = "";
        if (empresa == null) {
            throw new AlmacenException("La empresa no puede ser nula");
        }
        if (empresa.getCif().isEmpty() || empresa.getCif() == null) {
            mensaje += "El cif de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getNombre().isEmpty() || empresa.getNombre() == null) {
            mensaje += "El nombre de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getDireccion().isEmpty() || empresa.getDireccion() == null) {
            mensaje += "La direccion de la empresa no puede ser nula o vacia\n";
        }
        if (empresa.getTelefono().isEmpty() || empresa.getTelefono() == null) {
            mensaje += "El telefono de la empresa no puede ser nulo o vacio\n";
        }
        if (empresa.getCorreo().isEmpty() || empresa.getCorreo() == null) {
            mensaje += "El correo de la empresa no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si un cliente es valido
     * @param cliente a validar
     * @throws AlmacenException controlado
     */
    public void validarCliente(Cliente cliente) throws AlmacenException {
        String mensaje = "";
        if (cliente == null) {
            throw new AlmacenException("El cliente no puede ser nulo");
        }
        if (cliente.getCif().isEmpty() || cliente.getCif() == null) {
            mensaje += "El cif del cliente no puede ser nulo o vacio\n";
        }
        if (cliente.getPorcentajeDesc() < 0) {
            mensaje += "El porcentaje de descuento del cliente no puede ser menor que 0\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    /**
     * Metodo que valida si un proveedor es valido
     * @param proveedor a validar
     * @throws AlmacenException controlado
     */
    public void validarProveedor(Proveedor proveedor) throws AlmacenException {
        String mensaje = "";
        if (proveedor == null) {
            throw new AlmacenException("El cliente no puede ser nulo");
        }
        if (proveedor.getCif().isEmpty() || proveedor.getCif() == null) {
            mensaje += "El cif del proveedor no puede ser nulo o vacio\n";
        }
        if (proveedor.getTipoProducto().isEmpty() || proveedor.getTipoProducto() == null) {
            mensaje += "El tipo de producto del proveedor no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }
    
    /**
     * Metodo que valida si una operacion es valida
     * @param operacion a validar
     * @throws AlmacenException controlado
     */
    public void validarOperacion(Operacion operacion) throws AlmacenException {
        String mensaje = "";
        if (operacion == null) {
            throw new AlmacenException("La operacion no puede ser nula");
        }
        if (operacion.getIdListaProductos() <= 0) {
            mensaje += "El id de la lista de productos no puede ser menor o igual que 0\n";
        }
        if (operacion.getIdMuelle() <= 0) {
            mensaje += "El id del muelle no puede ser menor o igual que 0\n";
        }
        if (operacion.getFecha().isEmpty() || operacion.getFecha() == null) {
            mensaje += "La fecha de la operacion no puede ser nula o vacia\n";
        }
        if (operacion.getTipoOperacion().isEmpty() || operacion.getTipoOperacion() == null) {
            mensaje += "El tipo de la operacion no puede ser nulo o vacio\n";
        }
        if (operacion.getCif().isEmpty() || operacion.getCif() == null) {
            mensaje += "El cif de la operacion no puede ser nulo o vacio\n";
        }
        if(!mensaje.isBlank()) {
            throw new AlmacenException(mensaje);
        }
    }

    //CRUD producto

    /**
     * Metodo que inserta un producto en la bbdd
     * @param producto a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Producto producto) throws BbddException, AlmacenException {
        validarProducto(producto);
        almacenModelo.insertar(producto);
    }
    /**
     * Metodo que elimina un producto de la bbdd
     * @param producto a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Producto producto) throws BbddException, AlmacenException {
        validarProducto(producto);
        almacenModelo.eliminar(producto);
    }
    /**
     * Metodo que modifica un producto de la bbdd
     * @param producto a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Producto producto) throws BbddException, AlmacenException {
        validarProducto(producto);
        almacenModelo.modificar(producto);
    }
    /**
     * Metodo que busca un producto en la bbdd
     * @param idProducto del producto a buscar
     * @throws BbddException controlado
     */
    public Producto obtenerProducto(int idProducto) throws BbddException {
        return almacenModelo.obtenerProducto(idProducto);
    }

    /**
     * Funcion que devuelve el listado de los productos
     * @return arraylist de productos
     * @throws BbddException controlado
     */
    public ArrayList<Producto> obtenerListadoProducto() throws BbddException {
        return almacenModelo.obtenerListadoProducto();
    }

    /**
     * Funcion que calcula el valor total de todos los productos del almacen
     * @return valor total de los productos
     * @throws BbddException controlado
     */
    public double obtenerValorProductosTotal() throws BbddException {
        return almacenModelo.obtenerValorProductosTotal();
    }

    /**
     * Funcion que obtiene los huecos totales y los ocupados del almacen
     * @return [huecos ocupados, huecos totales]
     * @throws BbddException controlado
     */
    public Integer[] obtenerHuecosOcupados() throws BbddException {
        return almacenModelo.obtenerHuecosOcupados();
    }

    //CRUD zona

    /**
     * Metodo que inserta una zona en la bbdd
     * @param zona a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Zona zona) throws BbddException, AlmacenException {
        validarZona(zona);
        almacenModelo.insertar(zona);
    }
    /**
     * Metodo que elimina una zona de la bbdd
     * @param zona a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Zona zona) throws BbddException, AlmacenException {
        validarZona(zona);
        almacenModelo.eliminar(zona);
    }
    /**
     * Metodo que modifica una zona de la bbdd
     * @param zona a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Zona zona) throws BbddException, AlmacenException {
        validarZona(zona);
        almacenModelo.modificar(zona);
    }
    /**
     * Metodo que busca una zona en la bbdd
     * @param idZona de la zona a buscar
     * @throws BbddException controlado
     */
    public Zona obtenerZona(char idZona) throws BbddException {
        return almacenModelo.obtenerZona(idZona);
    }

    //CRUD estanteria

    /**
     * Metodo que inserta una estanteria en la bbdd
     * @param estanteria a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Estanteria estanteria) throws BbddException, AlmacenException {
        validarEstanteria(estanteria);
        almacenModelo.insertar(estanteria);
    }
    /**
     * Metodo que elimina una estanteria de la bbdd
     * @param estanteria a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Estanteria estanteria) throws BbddException, AlmacenException {
        validarEstanteria(estanteria);
        almacenModelo.eliminar(estanteria);
    }
    /**
     * Metodo que modifica una estanteria de la bbdd
     * @param estanteria a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Estanteria estanteria) throws BbddException, AlmacenException {
        validarEstanteria(estanteria);
        almacenModelo.modificar(estanteria);
    }
    /**
     * Metodo que busca una estanteria en la bbdd
     * @param idEstanteria de la estanteria a buscar
     * @throws BbddException controlado
     */
    public Estanteria obtenerEstanteria(int idEstanteria) throws BbddException {
        return almacenModelo.obtenerEstanteria(idEstanteria);
    }

    //CRUD productoEstanteria

    /**
     * Metodo que inserta un productoEstanteria en la bbdd
     * @param productoEstanteria a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validarProductoEstanteria(productoEstanteria);
        almacenModelo.insertar(productoEstanteria);
    }
    /**
     * Metodo que elimina un productoEstanteria de la bbdd
     * @param productoEstanteria a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validarProductoEstanteria(productoEstanteria);
        almacenModelo.eliminar(productoEstanteria);
    }
    /**
     * Metodo que modifica un productoEstanteria de la bbdd
     * @param productoEstanteria a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(ProductoEstanteria productoEstanteria) throws BbddException, AlmacenException {
        validarProductoEstanteria(productoEstanteria);
        almacenModelo.modificar(productoEstanteria);
    }
    /**
     * Metodo que busca un productoEstanteria en la bbdd
     * @param idProducto del productoEstanteria a buscar
     * @param idEstanteria del productoEstanteria a buscar
     * @throws BbddException controlado
     */
    public ProductoEstanteria obtenerProductoEstanteria(int idProducto, int idEstanteria) throws BbddException {
        return almacenModelo.obtenerProductoEstanteria(idProducto, idEstanteria);
    }

    //CRUD listaProductos

    /**
     * Metodo que inserta una listaProductos en la bbdd
     * @param listaProductos a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validarListaProductos(listaProductos);
        almacenModelo.insertar(listaProductos);
    }
    /**
     * Metodo que elimina una listaProductos de la bbdd
     * @param listaProductos a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validarListaProductos(listaProductos);
        almacenModelo.eliminar(listaProductos);
    }
    /**
     * Metodo que modifica una listaProductos de la bbdd
     * @param listaProductos a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(ListaProductos listaProductos) throws BbddException, AlmacenException {
        validarListaProductos(listaProductos);
        almacenModelo.modificar(listaProductos);
    }
    /**
     * Metodo que busca una listaProductos en la bbdd
     * @param idListaProductos de la listaProductos a buscar
     * @throws BbddException controlado
     */
    public ListaProductos obtenerListaProductos(int idListaProductos) throws BbddException {
        return almacenModelo.obtenerListaProductos(idListaProductos);
    }

    //CRUD muelle

    /**
     * Metodo que inserta un muelle en la bbdd
     * @param muelle a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Muelle muelle) throws BbddException, AlmacenException {
        validarMuelle(muelle);
        almacenModelo.insertar(muelle);
    }
    /**
     * Metodo que elimina un muelle de la bbdd
     * @param muelle a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Muelle muelle) throws BbddException, AlmacenException {
        validarMuelle(muelle);
        almacenModelo.eliminar(muelle);
    }
    /**
     * Metodo que modifica un muelle de la bbdd
     * @param muelle a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Muelle muelle) throws BbddException, AlmacenException {
        validarMuelle(muelle);
        almacenModelo.modificar(muelle);
    }
    /**
     * Metodo que busca un muelle en la bbdd
     * @param idMuelle del muelle a buscar
     * @throws BbddException controlado
     */
    public Muelle obtenerMuelle(int idMuelle) throws BbddException {
        return almacenModelo.obtenerMuelle(idMuelle);
    }

    //CRUD empresa

    /**
     * Metodo que inserta una empresa en la bbdd
     * @param empresa a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Empresa empresa) throws BbddException, AlmacenException {
        validarEmpresa(empresa);
        almacenModelo.insertar(empresa);
    }
    /**
     * Metodo que elimina una empresa de la bbdd
     * @param empresa a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Empresa empresa) throws BbddException, AlmacenException {
        validarEmpresa(empresa);
        almacenModelo.eliminar(empresa);
    }
    /**
     * Metodo que modifica una empresa de la bbdd
     * @param empresa a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Empresa empresa) throws BbddException, AlmacenException {
        validarEmpresa(empresa);
        almacenModelo.modificar(empresa);
    }
    /**
     * Metodo que busca una empresa en la bbdd
     * @param cif de la empresa a buscar
     * @throws BbddException controlado
     */
    public Empresa obtenerEmpresa(String cif) throws BbddException {
        return almacenModelo.obtenerEmpresa(cif);
    }

    //CRUD cliente
    /**
     * Metodo que inserta un cliente en la bbdd
     * @param cliente a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Cliente cliente) throws BbddException, AlmacenException {
        validarCliente(cliente);
        almacenModelo.insertar(cliente);
    }
    /**
     * Metodo que elimina un cliente de la bbdd
     * @param cliente a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Cliente cliente) throws BbddException, AlmacenException {
        validarCliente(cliente);
        almacenModelo.eliminar(cliente);
    }
    /**
     * Metodo que modifica un cliente de la bbdd
     * @param cliente a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Cliente cliente) throws BbddException, AlmacenException {
        validarCliente(cliente);
        almacenModelo.modificar(cliente);
    }
    /**
     * Metodo que busca un cliente en la bbdd
     * @param cif del cliente a buscar
     * @throws BbddException controlado
     */
    public Cliente obtenerCliente(String cif) throws BbddException {
        return almacenModelo.obtenerCliente(cif);
    }
    /**
     * Funcion que devuelve el listado de clientes
     * @return arraylist de clientes
     * @throws BbddException
     */
    public ArrayList<Cliente> obtenerListadoCliente() throws BbddException {
        return almacenModelo.obtenerListadoCliente();
    }
    
    //CRUD proveedor
    /**
     * Metodo que inserta un proveedor en la bbdd
     * @param proveedor a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Proveedor proveedor) throws BbddException, AlmacenException {
        validarProveedor(proveedor);
        almacenModelo.insertar(proveedor);
    }
    /**
     * Metodo que elimina un proveedor de la bbdd
     * @param proveedor a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Proveedor proveedor) throws BbddException, AlmacenException {
        validarProveedor(proveedor);
        almacenModelo.eliminar(proveedor);
    }
    /**
     * Metodo que modifica un proveedor de la bbdd
     * @param proveedor a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Proveedor proveedor) throws BbddException, AlmacenException {
        validarProveedor(proveedor);
        almacenModelo.modificar(proveedor);
    }
    /**
     * Metodo que busca un proveedor en la bbdd
     * @param cif del proveedor a buscar
     * @throws BbddException controlado
     */
    public Proveedor obtenerProveedor(String cif) throws BbddException {
        return almacenModelo.obtenerProveedor(cif);
    }
    /**
     * Funcion que devuelve el listado de proveedores
     * @return arraylist de proveedores
     * @throws BbddException controlado
     */
    public ArrayList<Proveedor> obtenerListadoProveedor() throws BbddException {
        return almacenModelo.obtenerListadoProveedor();
    }

    //CRUD operacion
    
    /**
     * Metodo que inserta una operacion en la bbdd
     * @param operacion a insertar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void insertar(Operacion operacion) throws BbddException, AlmacenException {
        validarOperacion(operacion);
        almacenModelo.insertar(operacion);
    }
    /**
     * Metodo que elimina una operacion de la bbdd
     * @param operacion a eliminar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void eliminar(Operacion operacion) throws BbddException, AlmacenException {
        validarOperacion(operacion);
        almacenModelo.eliminar(operacion);
    }
    /**
     * Metodo que modifica una operacion de la bbdd
     * @param operacion a modificar
     * @throws BbddException controlado
     * @throws AlmacenException controlado
     */
    public void modificar(Operacion operacion) throws BbddException, AlmacenException {
        validarOperacion(operacion);
        almacenModelo.modificar(operacion);
    }
    /**
     * Metodo que busca una operacion en la bbdd
     * @param idListaProductos de la operacion a buscar
     * @throws BbddException controlado
     */
    public Operacion obtenerOperacion(int idListaProductos) throws BbddException {
        return almacenModelo.obtenerOperacion(idListaProductos);
    }
}
