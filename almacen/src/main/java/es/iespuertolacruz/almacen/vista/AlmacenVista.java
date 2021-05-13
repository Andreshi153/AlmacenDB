package es.iespuertolacruz.almacen.vista;

import java.util.Scanner;

import es.iespuertolacruz.almacen.api.*;
import es.iespuertolacruz.almacen.controlador.AlmacenController;
import es.iespuertolacruz.almacen.exception.BbddException;
import es.iespuertolacruz.almacen.exception.FicheroException;

public class AlmacenVista {
    public static void main(String[] args) throws BbddException, FicheroException {
        AlmacenController almacenController = new AlmacenController();
        boolean salir = false;
        while(!salir) {
            int opcion = 0;
            menu(1);
            Scanner scanner = new Scanner(System.in);
            opcion = scanner.nextInt();
            switch(opcion) {
                case 1:
                    menu(2);
                    opcion = scanner.nextInt();
                    switch(opcion) {
                        case 1:
                            //leer idProducto
                            int idProducto = scanner.nextInt();
                            Producto producto = almacenController.obtenerProducto(idProducto);
                            //mostrar producto.toString()
                            System.out.println(producto.toString());
                            break;
                        case 2:
                            //leer nombre, precio y tipo
                            //insertar producto
                            System.out.println(almacenController.test());
                            break;
                        case 3:
                            //leer idProducto
                            //buscar producto
                            //leer nombre, precio y tipo
                            //modificar producto
                            break;
                        default:
                            break;
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Introduce solo numeros entre 1 y 6");
                    break;
            }
        }
    }

    public static void menu(int numMenu)  {
        switch(numMenu) {
            //menu principal
            case 1:
                System.out.println("1. Productos" + //menu(2)
                "\n2. Listas de productos" + //menu(3)
                "\n3. Operaciones" + //menu(4)
                "\n4. Empresas" + //menu(5)
                "\n5. Funciones" + //menu(6)
                "\n6.");
                break;
            //menu 1. Productos
            case 2:
                System.out.println("1. Mostrar informacion de un producto" + //
                "\n2. Insertar nuevo producto" +
                "\n3. Editar informacion de un producto");
                break;
            //menu 2. Listas de productos
            case 3:
                System.out.println("1. Mostrar informacion de una lista" +
                "\n2. Crear nueva lista" +
                "\n3. Editar lista" +
                "\n4. Eliminar lista");
                break;
            //menu 3. Operaciones
            case 4:
                System.out.println("1. Mostrar informacion de una operacion" +
                "\n2. Crear nueva operacion" +
                "\n3. Editar datos de una operacion");
                break;
            //menu 4. Empresas
            case 5:
                System.out.println("1. Mostrar informacion de una empresa" +
                "\n2. Mostrar clientes" +
                "\n3. Mostrar proveedores" +
                "\n4. Insertar nueva empresa" +
                "\n5. Modificar informacion de una empresa");
                break;
            //menu 5. Funciones
            case 6:
                System.out.println("1. Calcular indice de ocupacion del almacen" +
                "\n2. Calcular valor de productos del almacen" +
                "\n3. ");
                break;
            default:
                break;
        }
    }
}