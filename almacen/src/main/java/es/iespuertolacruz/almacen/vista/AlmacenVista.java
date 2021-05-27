package es.iespuertolacruz.almacen.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.controlador.*;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class AlmacenVista {

    private static final String DIVISA = "€";
    private static final String SEPARADOR = "\n------------";
    private static final String[] tiposProducto = { "Normal", "Frio", "Congelados" };
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Funcion principal de la aplicacion
     * 
     * @param args
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void main(String[] args) throws BbddException, FicheroException, AlmacenException {
        boolean salir = false;
        while (!salir) {
            System.out.println(menu(0));
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    menuProductos();
                    break;
                case 2:
                    menuListaProductos();
                    break;
                case 3:
                    menuOperaciones();
                    break;
                case 4:
                    menuEmpresas();
                    break;
                case 5:
                    menuFunciones();
                    break;
                default:
                    System.out.println("Escribe solo numeros del 0 al 5");
                    break;
            }
        }
    }

    /**
     * Metodo que muestra los menus del programa
     * 
     * @param numMenu numero del menu a mostrar
     */
    public static String menu(int numMenu) {
        switch (numMenu) {
            // menu principal
            case 0:
                return "\nALMACEN" + SEPARADOR + "\n0. Salir" + "\n1. Productos" + // menu(1)
                        "\n2. Listas de productos" + // menu(2)
                        "\n3. Operaciones" + // menu(3)
                        "\n4. Empresas" + // menu(4)
                        "\n5. Funciones"; // menu(5)
            // menu 1. Productos
            case 1:
                return SEPARADOR + "\n1. Mostrar informacion de un producto" + "\n2. Mostrar listado de productos"
                        + "\n3. Insertar nuevo producto" + "\n4. Editar informacion de un producto"
                        + "\n5. Borrar un producto";
            // menu 2. Listas de productos
            case 2:
                return SEPARADOR + "\n1. Mostrar informacion de una lista" + "\n2. Crear nueva lista"
                        + "\n3. Editar lista" + "\n4. Eliminar lista";
            // menu 3. Operaciones
            case 3:
                return SEPARADOR + "\n1. Mostrar informacion de una operacion" + "\n2. Crear nueva operacion"
                        + "\n3. Editar datos de una operacion";
            // menu 4. Empresas
            case 4:
                return SEPARADOR + "\n1. Insertar nueva empresa" + "\n2. Mostrar informacion de una empresa"
                        + "\n3. Mostrar clientes" + "\n4. Mostrar proveedores"
                        + "\n5. Modificar informacion de una empresa" + "\n6. Borrar empresa";
            // menu 5. Funciones
            case 5:
                return SEPARADOR + "\n1. Calcular indice de ocupacion del almacen"
                        + "\n2. Calcular valor de productos del almacen";
            default:
                break;
        }
        return null;
    }

    /**
     * Metodo que genera el menu de productos
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    public static void menuProductos() throws BbddException, AlmacenException, FicheroException {
        System.out.println(menu(1));
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                Producto producto = buscarProducto();
                System.out.println(
                        producto != null ? producto.toString() : "No existe ningun producto con ese identificador");
                break;
            case 2:
                System.out.println(listadoProductosToString());
                break;
            case 3:
                insertarProducto();
                break;
            case 4:
                modificarProducto();
                break;
            case 5:
                eliminarProducto();
                break;
            default:
                System.out.println("Introduce solo numeros entre 1 y 5");
                break;
        }
    }

    /**
     * Funcion que pide al usuario el id de un producto y lo busca en la bbdd
     * 
     * @return producto encontrado
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    private static Producto buscarProducto() throws BbddException, FicheroException {
        ProductoController productoController = new ProductoController();
        System.out.println("Introduce el id del producto: ");
        int idProducto = scanner.nextInt();
        return productoController.buscar(idProducto);
    }

    /**
     * Funcion que pide al usuario los datos de un producto para crearlo
     * 
     * @param idProducto del producto a crear
     * @return producto creado
     */
    private static Producto crearProducto(int idProducto) {
        scanner.nextLine();
        System.out.println("Introduce el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el precio del producto: ");
        float precio = scanner.nextFloat();
        System.out.println("Introduce el tipo del producto:" + "\n1. " + tiposProducto[0] + "\n2. " + tiposProducto[1]
                + "\n3. " + tiposProducto[2]);
        int tipo = scanner.nextInt();
        while (tipo < 1 || tipo > 3) {
            System.out.println("Debes introducir solo numeros del 1 al 3");
            tipo = scanner.nextInt();
        }
        return new Producto(idProducto, nombre, precio, tiposProducto[tipo - 1]);
    }

    /**
     * Metodo que inserta un producto en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    private static void insertarProducto() throws BbddException, AlmacenException, FicheroException {
        ProductoController productoController = new ProductoController();
        Producto producto = crearProducto(100);
        productoController.insertar(producto);
    }

    /**
     * Metodo que modifica un producto
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    private static void modificarProducto() throws BbddException, AlmacenException, FicheroException {
        ProductoController productoController = new ProductoController();
        Producto producto = crearProducto(buscarProducto().getIdProducto());
        productoController.modificar(producto);
    }

    /**
     * Metodo que elimina un producto
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    private static void eliminarProducto() throws BbddException, AlmacenException, FicheroException {
        ProductoController productoController = new ProductoController();
        Producto producto = buscarProducto();
        productoController.eliminar(producto);
    }

    /**
     * Metodo que genera el menu de la lista de productos
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    private static void menuListaProductos() throws BbddException, AlmacenException, FicheroException {
        System.out.println(menu(2));
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                ListaProductos listaProductos = buscarListaProductos(getIdListaProductos());
                System.out.println(listaProductos != null ? listaProductos.toString()
                        : "No existe ninguna lista de productos con ese identificador");
                break;
            case 2:
                insertarListaProductos();
                break;
            case 3:
                modificarListaProductos();
                break;
            case 4:
                eliminarListaProductos();
                break;
            default:
                System.out.println("Introduce solo numeros entre 1 y 4");
                break;
        }
    }

    /**
     * Funcion que pide al usuario el id de una lista de productos
     * 
     * @return id de la lista introducida por el usuario
     * @throws BbddException controlado
     */
    private static int getIdListaProductos() {
        System.out.println("Introduce el id de la lista de productos: ");
        return scanner.nextInt();
    }

    /**
     * Funcion que pide al usuario el id de una lista de productos y la busca en la
     * bbdd
     * 
     * @return listaProductos encontrada
     * @throws BbddException    controlado
     * @throws FicheroException
     */
    private static ListaProductos buscarListaProductos(int idListaProductos) throws BbddException, FicheroException {
        ListaProductosController listaProductosController = new ListaProductosController();
        return listaProductosController.buscar(idListaProductos);
    }

    /**
     * Funcion que pide informacion al usuario para crear una lista de productos
     * 
     * @return lista de productos creada
     * @throws BbddException    controlado
     * @throws FicheroException
     */
    public static ListaProductos obtenerListaProductos() throws BbddException, FicheroException {
        ProductoController productoController = new ProductoController();
        int idProducto = -1;
        ListaProductos lista = new ListaProductos();
        HashMap<Integer, Integer> mapa = new HashMap<>();
        while (idProducto != 0) {
            System.out.println("Introduce el id del producto: ");
            idProducto = scanner.nextInt();
            Producto producto = productoController.buscar(idProducto);
            if (producto == null && idProducto != 0) {
                System.out.println("No existe ningun producto con ese identificador");
            } else if (mapa.containsKey(idProducto)) {
                System.out.println("El producto ya esta en la lista");
            } else if (idProducto != 0) {
                System.out.println("Introduce la cantidad del producto: ");
                int cantidad = scanner.nextInt();
                mapa.put(idProducto, cantidad);
            }
        }
        lista.setLista(mapa);
        return lista;
    }

    /**
     * Metodo que inserta una lista de productos en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void insertarListaProductos() throws BbddException, FicheroException, AlmacenException {
        ListaProductosController listaProductosController = new ListaProductosController();
        ListaProductos listaProductos = obtenerListaProductos();
        listaProductosController.insertar(listaProductos);
    }

    /**
     * Metodo que modifica una lista de productos en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void modificarListaProductos() throws BbddException, AlmacenException, FicheroException {
        ListaProductosController listaProductosController = new ListaProductosController();
        int idListaProductos = getIdListaProductos();
        buscarListaProductos(idListaProductos);
        ListaProductos listaModificar = obtenerListaProductos();
        listaModificar.setIdListaProducto(idListaProductos);
        listaProductosController.modificar(listaModificar);
    }

    /**
     * Metodo que elimina una lista de productos en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void eliminarListaProductos() throws BbddException, AlmacenException, FicheroException {
        ListaProductosController listaProductosController = new ListaProductosController();
        int idListaProductos = getIdListaProductos();
        ListaProductos listaProductos = buscarListaProductos(idListaProductos);
        listaProductosController.eliminar(listaProductos);
    }

    /**
     * Funcion toString del listado de productos
     * 
     * @return cadena formateada de la lista de productos
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    private static String listadoProductosToString() throws BbddException, FicheroException {
        ProductoController productoController = new ProductoController();
        ArrayList<Producto> listado = productoController.buscarTodos();
        StringBuilder informacion = new StringBuilder("Listado de productos:");
        for (Producto producto : listado) {
            informacion.append("\n-> Id del producto: " + producto.getIdProducto() + ", nombre del producto: "
                    + producto.getNombre() + ", tipo del producto: " + producto.getTipo() + ", precio del producto: "
                    + producto.getPrecioUnitario() + DIVISA);
        }
        return informacion.toString();
    }

    /**
     * Metodo que genera el menu de operaciones
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    public static void menuOperaciones() throws BbddException, AlmacenException, FicheroException {
        menu(3);
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                Operacion operacion = buscarOperacion(getIdListaProductos());
                System.out.println(
                        operacion != null ? operacion.toString() : "No existe ninguna operacion con ese identificador");
                break;
            case 2:
                insertarOperaciones();
                break;
            case 3:
                modificarOperacion();
                break;
            default:
                System.out.println("Introduce solo numeros entre 1 y 3");
                break;
        }
    }

    /**
     * Funcion que pide al usuario el id de una lista de productos busca la
     * operacion correspondiente en la bbdd
     * 
     * @return operacion encontrada
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    private static Operacion buscarOperacion(int idListaProductos) throws BbddException, FicheroException {
        OperacionController operacionController = new OperacionController();
        return operacionController.buscar(idListaProductos);
    }

    /**
     * Metodo que inserta una operacion en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void insertarOperaciones() throws BbddException, AlmacenException, FicheroException {
        OperacionController operacionController = new OperacionController();
        int idListaProductos = getIdListaProductos();
        Operacion operacion = crearOperacion(idListaProductos);
        operacionController.insertar(operacion);
    }

    /**
     * Metodo que modificar una operacion en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void modificarOperacion() throws BbddException, FicheroException, AlmacenException {
        OperacionController operacionController = new OperacionController();
        int idListaProductos = getIdListaProductos();
        buscarOperacion(idListaProductos);
        Operacion operacion = crearOperacion(idListaProductos);
        operacionController.modificar(operacion);
    }

    /**
     * Funcion que pide al usuario los datos de una operacion para crearla
     * 
     * @param idListaProductos de la operacion a crear
     * @return operacion creada
     */
    private static Operacion crearOperacion(int idListaProductos) {
        String[] tiposOperacion = { "Entrada", "Salida" };
        System.out.println("Introduce el id del muelle");
        int idMuelle = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la fecha");
        String fecha = scanner.nextLine();
        System.out.println("Introduce el cif de la operacion");
        String cif = scanner.nextLine();
        System.out.println("Introduce tipo de operacion:\n1. Entrada\n2. Salida");
        int tipo = scanner.nextInt();
        while (tipo < 1 || tipo > 3) {
            System.out.println("Debes introducir solo numeros del 1 al 2");
            tipo = scanner.nextInt();
        }
        return new Operacion(idListaProductos, idMuelle, fecha, tiposOperacion[tipo - 1], cif);
    }

    /**
     * Metodo que genera el menu de empresas
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    public static void menuEmpresas() throws BbddException, FicheroException, AlmacenException {
        menu(4);
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                insertarEmpresa();
                break;
            case 2:
                Empresa empresa = buscarEmpresa(getCif());
                System.out.println(empresa != null ? empresa.toString() : "No existe ninguna empresa con ese CIF");
                break;
            case 3:
                System.out.println(listadoClientesToString());
                break;
            case 4:
                System.out.println(listadoProveedoresToString());
                break;
            case 5:
                modificarEmpresa();
                break;
            case 6:
                eliminarEmpresa();
                break;
            default:
                System.out.println("Escribe solo numeros del 1 al 6");
                break;
        }
    }

    /**
     * Funcion que pide al usuario el cif de una empresa
     * 
     * @return cif introducido
     * @throws BbddException controlado
     */
    private static String getCif() {
        System.out.println("Introduce el cif de la empresa:");
        return scanner.nextLine();
    }

    /**
     * Funcion que pide al usuario el cif de una empresa y la busca en la bbdd
     * 
     * @return empresa encontrada
     * @throws BbddException    controlado
     * @throws FicheroException
     */
    private static Empresa buscarEmpresa(String cif) throws BbddException, FicheroException {
        EmpresaController empresaController = new EmpresaController();
        return empresaController.buscar(cif);
    }

    /**
     * Metodo que inserta una empresa en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void insertarEmpresa() throws BbddException, AlmacenException, FicheroException {
        EmpresaController empresaController = new EmpresaController();
        String cif = getCif();
        Empresa empresa = crearEmpresa(cif);
        empresaController.insertar(empresa);
        insertarClienteProveedor(elegirTipoEmpresa(), cif);
    }

    /**
     * Funcion que inserta un cliente/empresa en la bbdd
     * 
     * @param tipoEmpresa a crear 1-cliente 2-empresa
     * @param cif         del cliente/proveedor
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException
     */
    private static void insertarClienteProveedor(int tipoEmpresa, String cif)
            throws BbddException, AlmacenException, FicheroException {
        if (tipoEmpresa == 1) {
            ClienteController clienteController = new ClienteController();
            clienteController.insertar(crearCliente(cif));
        } else {
            ProveedorController proveedorController = new ProveedorController();
            proveedorController.insertar(crearProveedor(cif));
        }
    }

    /**
     * Metodo que modifica una empresa en la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void modificarEmpresa() throws BbddException, AlmacenException, FicheroException {
        EmpresaController empresaController = new EmpresaController();
        String cif = getCif();
        Empresa empresa = crearEmpresa(cif);
        empresaController.modificar(empresa);
        modificarClienteProveedor(elegirTipoEmpresa(), empresa.getCif());
    }

    /**
     * Funcion que modifica un cliente/empresa de la bbdd
     * 
     * @param tipoEmpresa a modificar 1-cliente 2-empresa
     * @param cif         del cliente/proveedor
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException
     */
    private static void modificarClienteProveedor(int tipoEmpresa, String cif)
            throws BbddException, AlmacenException, FicheroException {
        if (tipoEmpresa == 1) {
            ClienteController clienteController = new ClienteController();
            clienteController.modificar(crearCliente(cif));
        } else {
            ProveedorController proveedorController = new ProveedorController();
            proveedorController.modificar(crearProveedor(cif));
        }
    }

    /**
     * Funcion toString del listado de clientes
     * 
     * @return cadena formateada de la lista de clientes
     * @throws BbddException    controlado
     * @throws FicheroException
     */
    private static String listadoClientesToString() throws BbddException, FicheroException {
        ClienteController clienteController = new ClienteController();
        ArrayList<Cliente> listado = clienteController.buscarTodos();
        StringBuilder informacion = new StringBuilder("Listado de clientes:");
        for (Cliente cliente : listado) {
            informacion.append("\n-> Cif: " + cliente.getCif() + ", porcentaje de descuento: "
                    + Math.round((1 - cliente.getPorcentajeDesc()) * 100) + "%");
        }
        return informacion.toString();
    }

    /**
     * Funcion toString del listado de proveedores
     * 
     * @return cadena formateada de la lista de proveedores
     * @throws BbddException    controlado
     * @throws FicheroException
     */
    private static String listadoProveedoresToString() throws BbddException, FicheroException {
        ProveedorController proveedorController = new ProveedorController();
        ArrayList<Proveedor> listado = proveedorController.buscarTodos();
        StringBuilder informacion = new StringBuilder("Listado de proveedores:");
        for (Proveedor proveedor : listado) {
            informacion
                    .append("\n-> Cif: " + proveedor.getCif() + ", tipo de producto: " + proveedor.getTipoProducto());
        }
        return informacion.toString();
    }

    /**
     * Metodo que elimina una empresa de la bbdd
     * 
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     * @throws AlmacenException controlado
     */
    public static void eliminarEmpresa() throws BbddException, AlmacenException, FicheroException {
        EmpresaController empresaController = new EmpresaController();
        Empresa empresa = buscarEmpresa(getCif());
        empresaController.eliminar(empresa);
    }

    /**
     * Funcion que pide al usuario los datos de una empresa para crearla
     * 
     * @param cif de la empresa a crear
     * @return empresa creada
     */
    private static Empresa crearEmpresa(String cif) {
        System.out.println("Introduce el nombre de la empresa");
        String nombreEmpresa = scanner.nextLine();
        System.out.println("Introduce la direccion de la empresa");
        String direccion = scanner.nextLine();
        System.out.println("Introduce el telefono de la empresa");
        String telefono = scanner.nextLine();
        System.out.println("Introduce el correo de la empresa");
        String correo = scanner.nextLine();
        return new Empresa(cif, nombreEmpresa, direccion, telefono, correo);
    }

    /**
     * Funcion que pide al usuario los datos de un cliente para crearlo
     * 
     * @param cif del cliente a crear
     * @return cliente creado
     */
    private static Cliente crearCliente(String cif) {
        System.out.println("Introduce el porcentaje de descuento (0-1):");
        float porcentajeDesc = scanner.nextFloat();
        return new Cliente(cif, porcentajeDesc);
    }

    /**
     * Funcion que pide al usuario los datos de un proveedor para crearlo
     * 
     * @param cif del proveedor a crear
     * @return proveedor creado
     */
    private static Proveedor crearProveedor(String cif) {
        System.out.println("Introduce el tipo del producto:" + "\n1. " + tiposProducto[0] + "\n2. " + tiposProducto[1]
                + "\n3. " + tiposProducto[2]);
        int tipo = scanner.nextInt();
        while (tipo < 1 || tipo > 3) {
            System.out.println("Debes introducir solo numeros del 1 al 3");
            tipo = scanner.nextInt();
        }
        return new Proveedor(cif, tiposProducto[tipo - 1]);
    }

    /**
     * Funcion que pide al usuario el tipo de empresa a crear
     * 
     * @return int tipo de empresa
     */
    private static int elegirTipoEmpresa() {
        System.out.println("Introduce el tipo de empresa:\n1. Cliente\n2.Proveedor");
        int tipoEmpresa = scanner.nextInt();
        while (tipoEmpresa < 1 || tipoEmpresa > 2) {
            System.out.println("Debes introducir solo numeros del 1 al 2");
            tipoEmpresa = scanner.nextInt();
        }
        return tipoEmpresa;
    }

    /**
     * Metodo que genera el menu de funciones
     * 
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     * @throws FicheroException controlado
     */
    public static void menuFunciones() throws BbddException, FicheroException {
        menu(5);
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                Integer[] huecos = obtenerHuecos();
                System.out.println("El numero de huecos ocupados del almacen es: " + huecos[0]
                        + "\nEl numero total de huecos del almacen es: " + huecos[1]
                        + "\nEl indice de ocupacion del almacen es: "
                        + Math.floor(((double) (huecos[0]) / huecos[1]) * 10000) / 100 + "%");
                break;
            case 2:
                System.out.println(
                        "El valor total de los productos del almacen es: " + obtenerValorProductosTotal() + DIVISA);
                break;
            default:
                System.out.println("Escribe solo numeros del 1 al 2");
                break;
        }
    }

    /**
     * Funcion que devuelve el valor total de todos los productos del almacen
     * 
     * @return valor total de los productos
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    private static double obtenerValorProductosTotal() throws BbddException, FicheroException {
        ProductoEstanteriaController productoEstanteriaController = new ProductoEstanteriaController();
        return productoEstanteriaController.obtenerValorProductosTotal();
    }

    /**
     * Funcion que devuelve los huecos ocupados y los libres del almacen
     * 
     * @return [huecos ocupados, huecos totales]
     * @throws BbddException    controlado
     * @throws FicheroException controlado
     */
    public static Integer[] obtenerHuecos() throws BbddException, FicheroException {
        ProductoEstanteriaController productoEstanteriaController = new ProductoEstanteriaController();
        return productoEstanteriaController.obtenerHuecos();
    }
}