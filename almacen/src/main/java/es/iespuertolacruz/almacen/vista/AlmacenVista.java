package es.iespuertolacruz.almacen.vista;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.controlador.AlmacenController;
import es.iespuertolacruz.almacen.exception.AlmacenException;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class AlmacenVista {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws BbddException, FicheroException, AlmacenException, SQLException {
        AlmacenController almacenController = new AlmacenController();
        boolean salir = false;
        int idProducto = -1;
        String[] tiposProducto = { "Normal", "Frio", "Congelados" };
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
                        String nombre;
                        float precio;
                        int tipo;
                        switch (opcion) {
                            case 1:
                                // leer idProducto

                                System.out.println("Introduce el id del producto: ");
                                idProducto = scanner.nextInt();

                                // buscar producto por id
                                producto = almacenController.obtenerProducto(idProducto);

                                // mostrar producto.toString()
                                System.out.println(producto.toString());

                                break;
                            case 2:
                                // leer nombre, precio y tipo

                                System.out.println("Introduce el nombre del producto: ");
                                nombre = scanner.nextLine();
                                System.out.println("Introduce el precio del producto: ");
                                precio = scanner.nextFloat();
                                System.out.println("Introduce el tipo del producto:" + "\n1. " + tiposProducto[0]
                                        + "\n2. " + tiposProducto[1] + "\n3. " + tiposProducto[2]);
                                tipo = scanner.nextInt();

                                // insertar producto

                                if (tipo < 1 || tipo > 3) {
                                    System.out.println("Debes introducir solo numeros del 1 al 3");
                                } else {
                                    almacenController
                                            .insertar(new Producto(100, nombre, precio, tiposProducto[tipo - 1]));
                                }

                                break;
                            case 3:
                                // EDITAR INFORMACION PRODUCTO
                                // leer idProducto
                                System.out.println("Introduce el id del producto: ");
                                idProducto = scanner.nextInt();

                                // buscar producto
                                producto = almacenController.obtenerProducto(idProducto);

                                // leer nombre, precio y tipo
                                System.out.println("Introduce el nombre del producto: ");
                                nombre = scanner.nextLine();
                                System.out.println("Introduce el precio del producto: ");
                                precio = scanner.nextFloat();

                                System.out.println("Introduce el tipo del producto:" + "\n1. " + tiposProducto[0]
                                        + "\n2. " + tiposProducto[1] + "\n3. " + tiposProducto[2]);
                                tipo = scanner.nextInt();
                                // modificar producto
                                if (tipo < 1 || tipo > 3) {
                                    System.out.println("Debes introducir solo numeros del 1 al 3");
                                } else {
                                    almacenController.modificar(
                                            new Producto(idProducto, nombre, precio, tiposProducto[tipo - 1]));
                                }
                                break;
                            case 4:
                                // BORRAR PRODUCTO
                                // leer idProducto
                                System.out.println("Introduce el id del producto");
                                idProducto = scanner.nextInt();
                                // buscar producto
                                producto = almacenController.obtenerProducto(idProducto);
                                // borrar producto
                                almacenController.eliminar(producto);
                                break;
                            default:
                                System.out.println("Introduce solo numeros entre 1 y 4");
                                break;
                        }
                        break;

                    case 2:
                        menu(2);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        int idListaProductos;
                        ListaProductos listaProductos;
                        int cantidad;
                        switch (opcion) {
                            case 1:
                                // leer idProducto

                                System.out.println("Introduce el id de la lista producto: ");
                                idListaProductos = scanner.nextInt();

                                // buscar producto por id
                                listaProductos = almacenController.obtenerListaProductos(idListaProductos);

                                // mostrar producto.toString()
                                System.out.println(listaProductos.toString());

                                break;
                            case 2:
                                ListaProductos lista = obtenerListaProductos(almacenController);
                                almacenController.insertar(lista);
                                break;
                            case 3:
                                // EDITAR LISTA PRODUCTOS
                                // leer idListaProducto
                                System.out.println("Introduce el id de la lista de productos: ");
                                idListaProductos = scanner.nextInt();
                                // buscar listaProducto
                                listaProductos = almacenController.obtenerListaProductos(idListaProductos);
                                ListaProductos listaModificar = obtenerListaProductos(almacenController);
                                almacenController.modificar(listaModificar);
                            case 4:
                                // BORRAR LISTA PRODUCTO
                                // leer idListaProducto
                                System.out.println("Introduce el id de la lista de productos: ");
                                idListaProductos = scanner.nextInt();
                                // buscar listaProducto
                                listaProductos = almacenController.obtenerListaProductos(idListaProductos);
                                // borrar producto
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
                        int idMuelle;
                        String fecha;
                        String cif;
                        String[] tiposOperacion = { "Entrada", "Salida" };
                        switch (opcion) {
                            case 1:
                                // leer operacion
                                System.out.println("Introduce el id de la lista de productos");
                                idListaProductos = scanner.nextInt();
                                // buscar operacion
                                operacion = almacenController.obtenerOperacion(idListaProductos);
                                // mostrar operacion
                                System.out.println(operacion.toString());
                                break;

                            case 2:
                                // leer operacion
                                System.out.println("Introduce el id de la lista de productos");
                                idListaProductos = scanner.nextInt();
                                System.out.println("Introduce el id del muelle");
                                idMuelle = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Introduce la fecha");
                                fecha = scanner.nextLine();
                                System.out.println("Introduce el cif de la operacion");
                                cif = scanner.nextLine();
                                System.out.println("Introduce tipo de operacion:\n1. Entrada\n2. Salida");
                                tipo = scanner.nextInt();

                                // crear operacion
                                if (tipo < 1 || tipo > 2) {
                                    System.out.println("Debes introducir solo numeros del 1 al 2");
                                } else {
                                    almacenController.insertar(new Operacion(idListaProductos, idMuelle, fecha,
                                            tiposOperacion[tipo - 1], cif));
                                }

                                break;

                            case 3:
                                // leer operacion
                                System.out.println("Introduce el id de la lista de productos");
                                idListaProductos = scanner.nextInt();

                                // buscar operacion
                                operacion = almacenController.obtenerOperacion(idListaProductos);
                                // if(operacion == null) -> sysout(la operacion no existe)
                                if (operacion == null) {
                                    System.out.println("La operacion no existe");
                                } else {
                                    System.out.println("Introduce el id del muelle");
                                    idMuelle = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Introduce la fecha");
                                    fecha = scanner.nextLine();
                                    System.out.println("Introduce el cif de la operacion");
                                    cif = scanner.nextLine();
                                    System.out.println("Introduce tipo de operacion:\n1. Entrada\n2. Salida");
                                    tipo = scanner.nextInt();

                                    // editar operacion
                                    if (tipo < 1 || tipo > 2) {
                                        System.out.println("Debes introducir solo numeros del 1 al 2");
                                    } else {
                                        almacenController.modificar(new Operacion(idListaProductos, idMuelle, fecha,
                                                tiposOperacion[tipo - 1], cif));
                                    }
                                }

                                break;
                            default:
                                System.out.println("Introduce solo numeros entre 0 y 5");
                        }
                        break;
                    case 4:
                        menu(4);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        Empresa empresa;
                        String[] tiposEmpresa = { "Cliente", "Proveedor" };
                        String nombreEmpresa;
                        String direccion;
                        String telefono;
                        String correo;
                        int tipoEmpresa;
                        float porcentajeDesc;
                        switch (opcion) {
                            case 1:
                                // INSERTAR EMPRESA
                                System.out.println("Introduce el cif de la empresa:");
                                cif = scanner.nextLine();

                                // buscar empresa
                                empresa = almacenController.obtenerEmpresa(cif);

                                if (empresa != null) {
                                    System.out.println("La empresa ya existe");
                                } else {
                                    System.out.println("Introduce el nombre de la empresa");
                                    nombreEmpresa = scanner.nextLine();
                                    System.out.println("Introduce la direccion de la empresa");
                                    direccion = scanner.nextLine();
                                    System.out.println("Introduce el telefono de la empresa");
                                    telefono = scanner.nextLine();
                                    System.out.println("Introduce el correo de la empresa");
                                    correo = scanner.nextLine();
                                    System.out.println("Introduce el tipo de empresa:\n1. Cliente\n2.Proveedor");
                                    tipoEmpresa = scanner.nextInt();

                                    // comprueba que el tipo de empresa sea valido
                                    if (tipoEmpresa < 1 || tipoEmpresa > 2) {
                                        System.out.println("Debes introducir solo numeros del 1 al 2");
                                    } else {
                                        almacenController
                                                .insertar(new Empresa(cif, nombreEmpresa, direccion, telefono, correo));
                                        // si la empresa es un cliente
                                        if (tipoEmpresa == 1) {
                                            System.out.println("Introduce el porcentaje de descuento (0-1):");
                                            porcentajeDesc = scanner.nextFloat();
                                            almacenController.insertar(new Cliente(cif, porcentajeDesc));
                                        } // si la empresa es un proveedor
                                        else {
                                            System.out.println(
                                                    "Introduce el tipo del producto:" + "\n1. " + tiposProducto[0]
                                                            + "\n2. " + tiposProducto[1] + "\n3. " + tiposProducto[2]);
                                            tipo = scanner.nextInt();
                                            // comprueba que el tipo de producto sea valido
                                            if (tipo < 1 || tipo > 3) {
                                                System.out.println("Debes introducir solo numeros del 1 al 3");
                                            } else {
                                                almacenController.insertar(new Proveedor(cif, tiposProducto[tipo - 1]));
                                            }
                                        }
                                    }

                                }

                                break;
                            case 2:
                                // MOSTRAR INFO DE UNA EMPRESA
                                System.out.println("Introduce el cif de la empresa");
                                cif = scanner.nextLine();

                                empresa = almacenController.obtenerEmpresa(cif);
                                System.out.println(empresa.toString());
                                break;
                            case 3:
                                // MOSTRAR CLIENTES
                                System.out.println(almacenController.obtenerListadoCliente().toString());
                                break;
                            case 4:
                                // MOSTRAR PROVEEDORES
                                System.out.println(almacenController.obtenerListadoProveedor().toString());
                                break;
                            case 5:
                                // MODIFICAR INFORMACION EMPRESA
                                break;
                            case 6:
                                // BORRAR EMPRESA
                                break;
                        }
                        break;
                    case 5:
                        menu(5);
                        opcion = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcion) {
                            case 1:
                                System.out.println("");
                                break;
                            case 2:
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

    public static void menu(int numMenu) {
        switch (numMenu) {
            // menu principal
            case 0:
                System.out.println("0. Salir" + "\n1. Productos" + // menu(1)
                        "\n2. Listas de productos" + // menu(2)
                        "\n3. Operaciones" + // menu(3)
                        "\n4. Empresas" + // menu(4)
                        "\n5. Funciones"); // menu(5)
                break;
            // menu 1. Productos
            case 1:
                System.out.println("1. Mostrar informacion de un producto" + "\n2. Insertar nuevo producto"
                        + "\n3. Editar informacion de un producto" + "\n4. Borrar un producto");
                break;
            // menu 2. Listas de productos
            case 2:
                System.out.println("1. Mostrar informacion de una lista" + "\n2. Crear nueva lista"
                        + "\n3. Editar lista" + "\n4. Eliminar lista");
                break;
            // menu 3. Operaciones
            case 3:
                System.out.println("1. Mostrar informacion de una operacion" + "\n2. Crear nueva operacion"
                        + "\n3. Editar datos de una operacion");
                break;
            // menu 4. Empresas
            case 4:
                System.out.println("1. Insertar nueva empresa" + "\n2. Mostrar informacion de una empresa"
                        + "\n3. Mostrar clientes" + "\n4. Mostrar proveedores"
                        + "\n5. Modificar informacion de una empresa" + "\n6. Borrar empresa");
                break;
            // menu 5. Funciones
            case 5:
                System.out.println(
                        "1. Calcular indice de ocupacion del almacen" + "\n2. Calcular valor de productos del almacen");
                break;
            default:
                break;
        }
    }

    public static ListaProductos obtenerListaProductos(AlmacenController almacenController) throws BbddException {
        // crear listaProducto
        // crear HashMap<Integer, Integer>
        // while idproducto != 0
        // leer idproducto
        // comprobar producto
        // buscar producto
        // ver que producto != null
        // leer cantidad
        // insertar en el hashmap
        int idProducto = -1;
        ListaProductos lista = new ListaProductos();
        HashMap<Integer, Integer> mapa = new HashMap<>();
        while (idProducto != 0) {
            System.out.println("Introduce el id del producto: ");
            idProducto = scanner.nextInt();
            Producto producto = almacenController.obtenerProducto(idProducto);
            if (producto == null) {
                System.out.println("El producto no existe dentro de la base de datos");
            } else {
                System.out.println("Introduce la cantidad del producto: ");
                int cantidad = scanner.nextInt();
                mapa.put(idProducto, cantidad);
            }
        }
        lista.setLista(mapa);
        return lista;
    }
}