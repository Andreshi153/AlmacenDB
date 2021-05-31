package es.iespuertolacruz.almacen.vista;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.almacen.api.*;

public class AlmacenVistaTest {

    static AlmacenVista almacenVista;

    @BeforeAll
    public static void setUp() {
        almacenVista = new AlmacenVista();
    }

    @Test
    public void menuTest() {
        assertTrue(AlmacenVista.menu(0).contains("0. Salir"));
        assertTrue(AlmacenVista.menu(1).contains("1. Mostrar informacion de un producto"));
        assertTrue(AlmacenVista.menu(2).contains("1. Mostrar informacion de una lista"));
        assertTrue(AlmacenVista.menu(3).contains("1. Mostrar informacion de una operacion"));
        assertTrue(AlmacenVista.menu(4).contains("1. Insertar nueva empresa"));
        assertTrue(AlmacenVista.menu(5).contains("1. Calcular indice de ocupacion del almacen"));
        assertNull(AlmacenVista.menu(6));
    }

    @Test
    public void listadoClientesToStringTest() {
        try {
            String listadoClientes = AlmacenVista.listadoClientesToString();
            assertTrue(listadoClientes.contains("Listado de clientes:"));
            assertTrue(listadoClientes.contains("-> Cif: "));
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando la lista de clientes", ex);
        } 
    }

    @Test
    public void listadoProductosToStringTest() {
        try {
            String listadoProductos = AlmacenVista.listadoProductosToString();
            assertTrue(listadoProductos.contains("Listado de productos:"));
            assertTrue(listadoProductos.contains("-> Id del producto: "));
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando la lista de clientes", ex);
        } 
    }

    @Test
    public void listadoProveedoresToStringTest() {
        try {
            String listadoProveedores = AlmacenVista.listadoProveedoresToString();
            assertTrue(listadoProveedores.contains("Listado de proveedores:"));
            assertTrue(listadoProveedores.contains("-> Cif: "));
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando la lista de productos", ex);
        } 
    }

    @Test
    public void obtenerValorProductosTotalTest() {
        try {
            double valorTotal = AlmacenVista.obtenerValorProductosTotal();
            assertTrue(valorTotal > 0);
        } catch (Exception ex) {
            fail("Se ha generado una excepcion calculando el valor total de los productos", ex);
        }
    }

    @Test
    public void obtenerHuecosTest() {
        try {
            Integer[] huecos = AlmacenVista.obtenerHuecos();
            assertTrue(huecos[0] >= 0);
            assertTrue(huecos[1] > 0);
        } catch (Exception ex) {
            fail("Se ha generado una excepcion calculando los huecos del almacen", ex);
        }
    }

    @Test
    public void buscarProductoTest() {
        try {
            Producto producto = AlmacenVista.buscarProducto(1);
            assertEquals(1, producto.getIdProducto());
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando el producto", ex);
        }
    }

    @Test
    public void buscarOperacionTest() {
        try {
            Operacion operacion = AlmacenVista.buscarOperacion(1);
            assertEquals(1, operacion.getIdListaProductos());
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando la operacion", ex);
        }
    }

    @Test
    public void buscarEmpresaTest() {
        try {
            Empresa empresa = AlmacenVista.buscarEmpresa("A11111111");
            assertEquals("A11111111", empresa.getCif());
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando la empresa", ex);
        }
    }

    @Test
    public void buscarListaProductosTest() {
        try {
            ListaProductos listaProductos = AlmacenVista.buscarListaProductos(1);
            assertEquals(1, listaProductos.getIdListaProductos());
        } catch (Exception ex) {
            fail("Se ha generado una excepcion buscando la lista de productos", ex);
        }
    }
}