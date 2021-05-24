package es.iespuertolacruz.almacen.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.controlador.AlmacenController;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class AlmacenVista {

    private static final String EL_CIF_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_EMPRESA = "El cif introducido no corresponde con ninguna empresa";
    private static final String DIVISA = "€";
    private static final String NO_EXISTE_NINGUNA_OPERACION_ASOCIADA_A_ESE_IDENTIFICADOR_DE_LISTA_DE_PRODUCTOS = "No existe ninguna operacion asociada a ese identificador de lista de productos";
    private static final String EL_IDENTIFICADOR_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_LISTA_DE_PRODUCTOS = "El identificador introducido no corresponde con ninguna lista de productos";
    private static final String NO_EXISTE_NINGUN_PRODUCTO_CON_ESE_IDENTIFICADOR = "No existe ningun producto con ese identificador";
    private static final String SEPARADOR = "\n------------";
    private static Scanner scanner = new Scanner(System.in);
    private static AlmacenController almacenController;
    private static final String[] tiposProducto = { "Normal", "Frio", "Congelados" };

    /**
     * Constructor de la clase
     * 
     * @throws BbddException controlado
     * @throws FicheroException controlado
     */
    public AlmacenVista() throws BbddException, FicheroException {
        almacenController = new AlmacenController();
    }

    public static void main(String[] args) throws BbddException, FicheroException, AlmacenException {
        AlmacenVista almacenVista = new AlmacenVista();
        boolean salir = false;
        while (!salir) {
            try {
                int opcion = -1;
                menu(0);
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        menu(1);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        Producto producto;
                        switch (opcion) {
                            case 1:
                                producto = buscarProducto();
                                System.out.println(producto != null ? producto.toString()
                                        : NO_EXISTE_NINGUN_PRODUCTO_CON_ESE_IDENTIFICADOR);
                                break;
                            case 2:
                                System.out.println(listadoProductosToString());
                                break;
                            case 3:
                                almacenController.insertar(crearProducto(100));
                                break;
                            case 4:
                                producto = buscarProducto();
                                if (producto == null)
                                    System.out.println(NO_EXISTE_NINGUN_PRODUCTO_CON_ESE_IDENTIFICADOR);
                                else
                                    almacenController.modificar(crearProducto(producto.getIdProducto()));
                                break;
                            case 5:
                                producto = buscarProducto();
                                if (producto == null)
                                    System.out.println(NO_EXISTE_NINGUN_PRODUCTO_CON_ESE_IDENTIFICADOR);
                                else
                                    almacenController.eliminar(producto);
                                break;
                            default:
                                System.out.println("Introduce solo numeros entre 1 y 5");
                                break;
                        }
                        break;
                    case 2:
                        menu(2);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        ListaProductos listaProductos;
                        int idListaProductos;
                        switch (opcion) {
                            case 1:
                                listaProductos = buscarListaProductos(getIdListaProductos());
                                System.out.println(listaProductos != null ? listaProductos.toString()
                                        : EL_IDENTIFICADOR_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_LISTA_DE_PRODUCTOS);
                                break;
                            case 2:
                                listaProductos = obtenerListaProductos();
                                almacenController.insertar(listaProductos);
                                break;
                            case 3:
                                idListaProductos = getIdListaProductos();
                                listaProductos = buscarListaProductos(idListaProductos);
                                if (listaProductos == null)
                                    System.out.println(
                                            EL_IDENTIFICADOR_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_LISTA_DE_PRODUCTOS);
                                else {
                                    ListaProductos listaModificar = obtenerListaProductos();
                                    listaModificar.setIdListaProducto(idListaProductos);
                                    almacenController.modificar(listaModificar);
                                }
                            case 4:
                                listaProductos = buscarListaProductos(getIdListaProductos());
                                if (listaProductos == null)
                                    System.out.println(
                                            EL_IDENTIFICADOR_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_LISTA_DE_PRODUCTOS);
                                else
                                    almacenController.eliminar(listaProductos);
                                break;
                            default:
                                System.out.println("Introduce solo numeros entre 1 y 4");
                                break;
                        }
                        break;
                    case 3:
                        menu(3);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        Operacion operacion;
                        switch (opcion) {
                            case 1:
                                operacion = buscarOperacion(getIdListaProductos());
                                System.out.println(operacion != null ? operacion.toString()
                                        : NO_EXISTE_NINGUNA_OPERACION_ASOCIADA_A_ESE_IDENTIFICADOR_DE_LISTA_DE_PRODUCTOS);
                                break;
                            case 2:
                                idListaProductos = getIdListaProductos();
                                operacion = buscarOperacion(idListaProductos);
                                if (operacion != null)
                                    System.out.println(
                                            "Ya existe una operacion asociada a ese identificador de lista de productos");
                                else
                                    almacenController.insertar(crearOperacion(idListaProductos));
                                break;

                            case 3:
                                idListaProductos = getIdListaProductos();
                                operacion = buscarOperacion(idListaProductos);
                                if (operacion == null)
                                    System.out.println(
                                            NO_EXISTE_NINGUNA_OPERACION_ASOCIADA_A_ESE_IDENTIFICADOR_DE_LISTA_DE_PRODUCTOS);
                                else
                                    almacenController.modificar(crearOperacion(idListaProductos));
                                break;
                            default:
                                System.out.println("Introduce solo numeros entre 1 y 3");
                        }
                        break;
                    case 4:
                        menu(4);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        Empresa empresa;
                        String cif;
                        switch (opcion) {
                            case 1:
                                cif = getCif();
                                empresa = buscarEmpresa(cif);
                                if (empresa != null)
                                    System.out.println("La empresa ya existe");
                                else {
                                    empresa = crearEmpresa(cif);
                                    almacenController.insertar(empresa);
                                    insertarClienteProveedor(elegirTipoEmpresa(), empresa.getCif());
                                }
                                break;
                            case 2:
                                empresa = buscarEmpresa(getCif());
                                System.out.println(empresa != null ? empresa.toString()
                                        : EL_CIF_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_EMPRESA);
                                break;
                            case 3:
                                System.out.println(listadoClientesToString());
                                break;
                            case 4:
                                System.out.println(listadoProveedoresToString());
                                break;
                            case 5:
                                cif = getCif();
                                empresa = buscarEmpresa(cif);
                                if (empresa == null)
                                    System.out.println(EL_CIF_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_EMPRESA);
                                else {
                                    empresa = crearEmpresa(cif);
                                    almacenController.modificar(empresa);
                                    modificarClienteProveedor(elegirTipoEmpresa(), empresa.getCif());
                                }
                                break;
                            case 6:
                                empresa = buscarEmpresa(getCif());
                                if (empresa == null)
                                    System.out.println(EL_CIF_INTRODUCIDO_NO_CORRESPONDE_CON_NINGUNA_EMPRESA);
                                else {
                                    almacenController.eliminar(empresa);
                                    Cliente cliente = buscarCliente(empresa.getCif());
                                    if (cliente != null)
                                        almacenController.eliminar(cliente);
                                    else
                                        almacenController.eliminar(buscarProveedor(empresa.getCif()));
                                }
                                break;
                            default:
                                System.out.println("Escribe solo numeros del 1 al 6");
                                break;
                        }
                        break;
                    case 5:
                        menu(5);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion) {
                            case 1:
                                Integer[] huecos = obtenerHuecosOcupados();
                                System.out.println("El numero de huecos ocupados del almacen es: " + huecos[0]
                                        + "\nEl numero total de huecos del almacen es: " + huecos[1]
                                        + "\nEl indice de ocupacion del almacen es: "
                                        + Math.floor(((double) (huecos[0]) / huecos[1]) * 10000) / 100 + "%");
                                break;
                            case 2:
                                System.out.println("El valor total de los productos del almacen es: "
                                        + obtenerValorProductosTotal() + DIVISA);
                                break;
                            default:
                                System.out.println("Escribe solo numeros del 1 al 2");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Escribe solo numeros del 0 al 5");
                        break;
                }
            } catch (Exception ex) {
                throw ex;
            }
        }

    }

    /**
     * Metodo que muestra los menus del programa
     * 
     * @param numMenu numero del menu a mostrar
     */
    public static void menu(int numMenu) {
        switch (numMenu) {
            // menu principal
            case 0:
                System.out.println("\nALMACEN" + SEPARADOR + "\n0. Salir" + "\n1. Productos" + // menu(1)
                        "\n2. Listas de productos" + // menu(2)
                        "\n3. Operaciones" + // menu(3)
                        "\n4. Empresas" + // menu(4)
                        "\n5. Funciones"); // menu(5)
                break;
            // menu 1. Productos
            case 1:
                System.out.println(SEPARADOR + "\n1. Mostrar informacion de un producto" + "\n2. Mostrar listado de productos"
                        + "\n3. Insertar nuevo producto" + "\n4. Editar informacion de un producto"
                        + "\n5. Borrar un producto");
                break;
            // menu 2. Listas de productos
            case 2:
                System.out.println(SEPARADOR + "\n1. Mostrar informacion de una lista" + "\n2. Crear nueva lista"
                        + "\n3. Editar lista" + "\n4. Eliminar lista");
                break;
            // menu 3. Operaciones
            case 3:
                System.out.println(SEPARADOR + "\n1. Mostrar informacion de una operacion" + "\n2. Crear nueva operacion"
                        + "\n3. Editar datos de una operacion");
                break;
            // menu 4. Empresas
            case 4:
                System.out.println(SEPARADOR + "\n1. Insertar nueva empresa" + "\n2. Mostrar informacion de una empresa"
                        + "\n3. Mostrar clientes" + "\n4. Mostrar proveedores"
                        + "\n5. Modificar informacion de una empresa" + "\n6. Borrar empresa");
                break;
            // menu 5. Funciones
            case 5:
                System.out.println(SEPARADOR + "\n1. Calcular indice de ocupacion del almacen" + "\n2. Calcular valor de productos del almacen");
                break;
            default:
                break;
        }
    }

    /**
     * Funcion que pide informacion al usuario para crear una lista de productos
     * 
     * @return lista de productos creada
     * @throws BbddException controlado
     */
    public static ListaProductos obtenerListaProductos() throws BbddException {
        int idProducto = -1;
        ListaProductos lista = new ListaProductos();
        HashMap<Integer, Integer> mapa = new HashMap<>();
        while (idProducto != 0) {
            System.out.println("Introduce el id del producto: ");
            idProducto = scanner.nextInt();
            Producto producto = almacenController.obtenerProducto(idProducto);
            if (producto == null && idProducto != 0) {
                System.out.println("El producto no existe dentro de la base de datos");
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
     * Funcion toString del listado de clientes
     * 
     * @return cadena formateada de la lista de clientes
     * @throws BbddException controlado
     */
    private static String listadoClientesToString() throws BbddException {
        ArrayList<Cliente> listado = almacenController.obtenerListadoCliente();
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
     * @throws BbddException controlado
     */
    private static String listadoProveedoresToString() throws BbddException {
        ArrayList<Proveedor> listado = almacenController.obtenerListadoProveedor();
        StringBuilder informacion = new StringBuilder("Listado de proveedores:");
        for (Proveedor proveedor : listado) {
            informacion
                    .append("\n-> Cif: " + proveedor.getCif() + ", tipo de producto: " + proveedor.getTipoProducto());
        }
        return informacion.toString();
    }

    /**
     * Funcion toString del listado de productos
     * 
     * @return cadena formateada de la lista de productos
     * @throws BbddException controlado
     */
    private static String listadoProductosToString() throws BbddException {
        ArrayList<Producto> listado = almacenController.obtenerListadoProducto();
        StringBuilder informacion = new StringBuilder("Listado de productos:");
        for (Producto producto : listado) {
            informacion.append("\n-> Id del producto: " + producto.getIdProducto() + ", nombre del producto: "
                    + producto.getNombre() + ", tipo del producto: " + producto.getTipo() + ", precio del producto: "
                    + producto.getPrecioUnitario() + DIVISA);
        }
        return informacion.toString();
    }

    /**
     * Funcion que calcula el valor total de todos los productos del almacen
     * 
     * @return valor total de los productos
     * @throws BbddException controlado
     */
    private static double obtenerValorProductosTotal() throws BbddException {
        return almacenController.obtenerValorProductosTotal();
    }

    /**
     * Funcion que obtiene los huecos totales y los ocupados del almacen
     * 
     * @return [huecos ocupados, huecos totales]
     * @throws BbddException controlado
     */
    public static Integer[] obtenerHuecosOcupados() throws BbddException {
        return almacenController.obtenerHuecosOcupados();
    }

    /**
     * Funcion que pide al usuario el id de un producto y lo busca en la bbdd
     * 
     * @return producto encontrado
     * @throws BbddException controlado
     */
    private static Producto buscarProducto() throws BbddException {
        System.out.println("Introduce el id del producto: ");
        int idProducto = scanner.nextInt();
        return almacenController.obtenerProducto(idProducto);
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
     * @throws BbddException controlado
     */
    private static ListaProductos buscarListaProductos(int idListaProductos) throws BbddException {
        return almacenController.obtenerListaProductos(idListaProductos);
    }

    /**
     * Funcion que pide al usuario el id de una lista de productos busca la
     * operacion correspondiente en la bbdd
     * 
     * @return operacion encontrada
     * @throws BbddException controlado
     */
    private static Operacion buscarOperacion(int idListaProductos) throws BbddException {
        return almacenController.obtenerOperacion(idListaProductos);
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
     * @throws BbddException controlado
     */
    private static Empresa buscarEmpresa(String cif) throws BbddException {
        return almacenController.obtenerEmpresa(cif);
    }

    /**
     * Funcion que pide al usuario el cif de un cliente y la busca en la bbdd
     * 
     * @return cliente encontrado
     * @throws BbddException controlado
     */
    private static Cliente buscarCliente(String cif) throws BbddException {
        return almacenController.obtenerCliente(cif);
    }

    /**
     * Funcion que pide al usuario el cif de un proveedor y la busca en la bbdd
     * 
     * @return proveedor encontrado
     * @throws BbddException controlado
     */
    private static Proveedor buscarProveedor(String cif) throws BbddException {
        return almacenController.obtenerProveedor(cif);
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
     * Funcion que inserta un cliente/empresa en la bbdd
     * 
     * @param tipoEmpresa a crear 1-cliente 2-empresa
     * @param cif         del cliente/proveedor
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     */
    private static void insertarClienteProveedor(int tipoEmpresa, String cif) throws BbddException, AlmacenException {
        if (tipoEmpresa == 1)
            almacenController.insertar(crearCliente(cif));
        else
            almacenController.insertar(crearProveedor(cif));
    }

    /**
     * Funcion que modifica un cliente/empresa de la bbdd
     * 
     * @param tipoEmpresa a modificar 1-cliente 2-empresa
     * @param cif         del cliente/proveedor
     * @throws BbddException    controlado
     * @throws AlmacenException controlado
     */
    private static void modificarClienteProveedor(int tipoEmpresa, String cif) throws BbddException, AlmacenException {
        if (tipoEmpresa == 1)
            almacenController.modificar(crearCliente(cif));
        else
            almacenController.modificar(crearProveedor(cif));
    }
}