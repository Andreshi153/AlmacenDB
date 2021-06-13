<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page errorPage = "../includes/error/showError.jsp" %>
    <%@page import="es.iespuertolacruz.almacen.controlador.ProductoController" %>
        <% ProductoController controller = new ProductoController();
    int idUltimo = controller.obtenerIdUltimo();
    String accion = request.getParameter("accion");
    int idMinimo = accion.equals("insertar") ? idUltimo+1 : 1;
    String idMaximo = accion.equals("insertar") ? "" : "max='" + idUltimo + "' ";
    String boton = accion.equals("insertar") ? "Insertar" : "Modificar";
    %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Insertar/modificar producto
                </title>
                <link rel="stylesheet" href="estilos-leer-input.css">
            </head>

            <body>
                <main>
                    <header id="hdrMenu">
                        <button id="mostrarMenu" class="boton-grande">
                            <hr>
                            <hr>
                            <hr>
                        </button>
                        <nav id="navMenu">
                            <ul>
                                <a href="../main/main.jsp#">
                                    <li id="liTop">
                                        Menú principal
                                    </li>
                                </a>
                                <a href="../main/main.jsp#estante-productos">
                                    <li>
                                        Menú de productos
                                    </li>
                                </a>
                                <a href="../main/main.jsp#estante-lista">
                                    <li>
                                        Menú de listas
                                    </li>
                                </a>
                                <a href="../main/main.jsp#estante-operaciones">
                                    <li>
                                        Menú de operaciones
                                    </li>
                                </a>
                                <a href="../main/main.jsp#estante-empresas">
                                    <li>
                                        Menú de empresas
                                    </li>
                                </a>
                                <a href="../main/main.jsp#estante-funciones">
                                    <li>
                                        Menú de funciones
                                    </li>
                                </a>
                                <a href="../index.jsp">
                                    <li>
                                        Salir
                                    </li>
                                </a>
                            </ul>
                        </nav>
                    </header>
                    <div id="loader">
                        <div class="TruckLoader">
                            <div class="TruckLoader-cab"></div>
                            <div class="inputs">
                                <form action="./validar-<%=accion%>-producto.jsp" autocomplete="off" class="form">
                                    <div class='control block-cube block-input'>
                                        <input name="identificador" placeholder='Identificador' type='number' min="<%=idMinimo%>" value="<%=idMinimo%>" <%=idMaximo%>>
                                        <div class='bg-top'>
                                            <div class='bg-inner'></div>
                                        </div>
                                        <div class='bg-right'>
                                            <div class='bg-inner'></div>
                                        </div>
                                        <div class='bg'>
                                            <div class='bg-inner'></div>
                                        </div>
                                    </div>
                                    <div class='control block-cube block-input'>
                                        <input name="nombre" placeholder='Nombre' type='text'>
                                        <div class='bg-top'>
                                            <div class='bg-inner'></div>
                                        </div>
                                        <div class='bg-right'>
                                            <div class='bg-inner'></div>
                                        </div>
                                        <div class='bg'>
                                            <div class='bg-inner'></div>
                                        </div>
                                    </div>
                                    <div class='control block-cube block-input'>
                                        <input name="precioUnitario" placeholder='Precio unitario' type='text'>
                                        <div class='bg-top'>
                                            <div class='bg-inner'></div>
                                        </div>
                                        <div class='bg-right'>
                                            <div class='bg-inner'></div>
                                        </div>
                                        <div class='bg'>
                                            <div class='bg-inner'></div>
                                        </div>
                                    </div>
                                    <div class="radio">
                                        <h3>Tipo de producto:</h3>
                                        <input name="tipo" type='radio' checked value="Normal">
                                        <label for="Normal">Normal</label>
                                        <input name="tipo" type='radio' value="Frio">
                                        <label for="Frio">Frío</label>
                                        <input name="tipo" type='radio' value="Congelados">
                                        <label for="Congelados">Congelados</label>
                                    </div>
                                    <button class='btn block-cube block-cube-hover' type='submit'>
                                    <div class='bg-top'>
                                        <div class='bg-inner'></div>
                                    </div>
                                    <div class='bg-right'>
                                        <div class='bg-inner'></div>
                                    </div>
                                    <div class='bg'>
                                        <div class='bg-inner'></div>
                                    </div>
                                    <div class='text'>
                                        <%=boton%>
                                    </div>
                                </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <footer id="footer">
                        <ul>
                            <a href="https://github.com/Andreshi153/AlmacenDB/wiki" target="_blank">
                                <li>Wiki del proyecto</li>
                            </a>
                            <a href="https://github.com/rubenrzprz" target="_blank">
                                <li>@rubenrzprz</li>
                            </a>
                        </ul>
                    </footer>
                </main>

            </body>

            </html>