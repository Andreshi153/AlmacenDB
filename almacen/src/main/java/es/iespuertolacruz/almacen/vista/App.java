package es.iespuertolacruz.almacen.vista;

public class App {
    public static void main(String[] args) {
        //
    }

    public static void menu(int numMenu)  {
        switch(numMenu) {
            case 1:
                System.out.println("1. Productos" +
                "\n2. Listas de productos" +
                "\n3. Operaciones" +
                "\n4. Empresas" +
                "\n5. Funciones" +
                "\n6.");
                break;
            case 2:
                System.out.println("1. Mostrar informacion de un producto" +
                "\n2. Insertar nuevo producto" +
                "\n3. Editar informacion de un producto");
                break;
            case 3:
                System.out.println("1. Mostrar informacion de una lista" +
                "\n2. Crear nueva lista" +
                "\n3. Editar lista" +
                "\n4. Eliminar lista");
                break;
            case 4:
                System.out.println("1. Mostrar informacion de una operacion" +
                "\n2. Crear nueva operacion" +
                "\n3. Editar datos de una operacion");
                break;
            case 5:
                System.out.println("1. Mostrar informacion de una empresa" +
                "\n2. Mostrar clientes" +
                "\n3. Mostrar proveedores" +
                "\n4. Insertar nueva empresa" +
                "\n5. Modificar informacion de una empresa");
                break;
            case 6:
                System.out.println("1. Calcular indice de ocupacion del almacen" +
                "\n2. Calcular valor de productos del almacen" +
                "\n3. ");
                break;
            default:
                System.out.println("Introduce solo un numero del 1 al 5");
                break;
        }
    }
}