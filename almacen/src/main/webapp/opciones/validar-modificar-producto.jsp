<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page errorPage = "../includes/error/showError.jsp" %>
    <%@page import="es.iespuertolacruz.almacen.controlador.ProductoController, es.iespuertolacruz.almacen.api.Producto"%>
        <% ProductoController productoController = new ProductoController();
            int identificador= Integer.parseInt(request.getParameter("identificador")); 
            String nombre = request.getParameter("nombre");
            float precioUnitario = Float.parseFloat(request.getParameter("precioUnitario"));
            String tipo = request.getParameter("tipo");
            String mensaje = "¡Producto modificado correctamente!";
            %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Validando inserción...
                </title>
                <link rel="stylesheet" href="estilos-mostrar-info.css">
            </head>

            <body>
                <main>
                    <div id="loader">
                        <div class="TruckLoader">
                            <div class="TruckLoader-cab"></div>
                            <div class="infoObjeto">
                                <% try {
                                    Producto producto = new Producto(identificador, nombre, precioUnitario, tipo);
                                    productoController.modificar(producto);
                                } catch (Exception ex) {%>
                                    mensaje = "Ha ocurrido un error modificando el producto";
                                    <%}%>
                                        <h3>
                                            <%=mensaje%>
                                        </h3>
                            </div>
                        </div>
                    </div>
                    <div class="botones">
                        <a href="./insertar-producto.jsp?accion=modificar">
                            <div class="boton">
                                Realizar otra modificación
                            </div>
                        </a>
                        <a href="../main/main.jsp">
                            <div class="boton">
                                Menú principal
                            </div>
                        </a>
                    </div>
                </main>
            </body>

            </html>