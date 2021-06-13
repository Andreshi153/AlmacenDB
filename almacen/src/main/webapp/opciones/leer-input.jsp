<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <% String tabla=request.getParameter("tabla"); 
    String accion = request.getParameter("accion");
    String boton = accion.equals("eliminar") ? "Eliminar" : "Consultar";
    String formAction = accion.equals("eliminar") ? "./validar-eliminar-producto.jsp" : "./mostrar-info.jsp";
    %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Mostrar información de
                <%=tabla%>
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
                            <form action="<%=formAction%>" autocomplete="off" class="form">
                                <div class='control block-cube block-input'>
                                    <input name="tabla" value="<%=tabla%>" hidden>
                                    <input name="identificador" placeholder='Identificador' type='text'>
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