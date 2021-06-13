<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menú</title>
        <link rel="stylesheet" href="estilos-main.css">
    </head>

    <body>
        <div id="loader">
            <div class="TruckLoader">
                <div class="TruckLoader-cab"></div>
                <div class="TruckLoader-smoke"></div>
            </div>
        </div>
        <div class="cortinas">
            <div class="cortina-wrapper">
                <div class="cortina-panel"></div>
                <main>
                    <header id="hdrMenu">
                        <button id="mostrarMenu" class="boton-grande">
                            <hr>
                            <hr>
                            <hr>
                        </button>
                        <nav id="navMenu">
                            <ul>
                                <a href="#">
                                    <li id="liTop">
                                        Menú principal
                                    </li>
                                </a>
                                <a href="#estante-productos">
                                    <li>
                                        Menú de productos
                                    </li>
                                </a>
                                <a href="#estante-lista">
                                    <li>
                                        Menú de listas
                                    </li>
                                </a>
                                <a href="#estante-operaciones">
                                    <li>
                                        Menú de operaciones
                                    </li>
                                </a>
                                <a href="#estante-empresas">
                                    <li>
                                        Menú de empresas
                                    </li>
                                </a>
                                <a href="#estante-funciones">
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
                    <div class="cabinet">
                        <div id="estante-principal">
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <a href="#estante-productos">
                                        <div class="tarjeta menu-rojo">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front centrado">
                                                    <img src="../imagenes/productos.png" alt="Productos">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Productos</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="#estante-lista">
                                        <div class="tarjeta menu-naranja">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front centrado">
                                                    <img src="../imagenes/lista-icon.png" alt="Lista de productos">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Listas de productos</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <a href="#estante-operaciones">
                                        <div class="tarjeta menu-verde">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front centrado">
                                                    <img src="../imagenes/operaciones.png" alt="Operaciones" class="operacion">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Operaciones</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="#estante-empresas">
                                        <div class="tarjeta menu-lila">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front centrado">
                                                    <img src="../imagenes/empresas.png" alt="Empresas" class="imagen-grande">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Empresas</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="#estante-funciones">
                                        <div class="tarjeta menu-agua">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front centrado">
                                                    <img src="../imagenes/funciones.png" alt="Funciones" class="imagen-grande">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Funciones</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                        </div>
                        <div id="estante-productos">
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <a href="../opciones/leer-input.jsp?tabla=producto&accion=consultar">
                                        <div class="tarjeta menu-lila">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front">
                                                    <img src="../imagenes/productos.png" alt="Producto" class="imagen-principal">
                                                    <img src="../imagenes/info-icon.png" alt="Información" class="imagen-sec">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Información de un producto</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="tarjeta menu-rojo">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/productos.png" alt="Producto" class="imagen-principal">
                                                <img src="../imagenes/lista-icon.png" alt="Lista" class="imagen-sec list">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Mostrar la lista de productos</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <a href="../opciones/insertar-producto.jsp?accion=insertar">
                                        <div class="tarjeta menu-verde">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front">
                                                    <img src="../imagenes/productos.png" alt="Producto" class="imagen-principal">
                                                    <img src="../imagenes/add-icon.png" alt="Insertar" class="imagen-sec">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Insertar un nuevo producto</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="../opciones/insertar-producto.jsp?accion=modificar">
                                        <div class="tarjeta menu-naranja">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front">
                                                    <img src="../imagenes/productos.png" alt="Producto" class="imagen-principal">
                                                    <img src="../imagenes/edit-icon.png" alt="Editar" class="imagen-sec edit">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Editar información de un producto</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="../opciones/leer-input.jsp?tabla=producto&accion=eliminar">
                                        <div class="tarjeta menu-azul">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front">
                                                    <img src="../imagenes/productos.png" alt="Producto" class="imagen-principal">
                                                    <img src="../imagenes/delete-icon.png" alt="Eliminar" class="imagen-sec">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Eliminar un producto</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                        </div>
                        <div id="estante-lista">
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <div class="tarjeta menu-rojo">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/lista-icon.png" alt="Lista de productos" class="imagen-principal">
                                                <img src="../imagenes/info-icon.png" alt="Información" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Mostrar información de una lista</h1>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tarjeta menu-naranja">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/lista-icon.png" alt="Lista de productos" class="imagen-principal">
                                                <img src="../imagenes/add-icon.png" alt="Insertar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Insertar una nueva lista</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <div class="tarjeta menu-verde">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/lista-icon.png" alt="Lista de productos" class="imagen-principal">
                                                <img src="../imagenes/edit-icon.png" alt="Editar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Editar una lista</h1>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tarjeta menu-lila">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/lista-icon.png" alt="Lista de productos" class="imagen-principal">
                                                <img src="../imagenes/delete-icon.png" alt="Eliminar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Borrar una lista</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                        </div>
                        <div id="estante-operaciones">
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <a href="../opciones/leer-input.jsp?tabla=operacion&accion=consultar">
                                        <div class="tarjeta menu-naranja">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front">
                                                    <img src="../imagenes/operaciones.png" alt="Operaciones" class="imagen-principal operacion">
                                                    <img src="../imagenes/info-icon.png" alt="Información" class="imagen-sec">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Mostrar información de una operación</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="tarjeta menu-azul">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/operaciones.png" alt="Operaciones" class="imagen-principal operacion">
                                                <img src="../imagenes/add-icon.png" alt="Insertar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Crear nueva operación</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <div class="tarjeta menu-verde">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/operaciones.png" alt="Operaciones" class="imagen-principal operacion">
                                                <img src="../imagenes/edit-icon.png" alt="Editar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Editar datos de una operación</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                        </div>
                        <div id="estante-empresas">
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <a href="../opciones/leer-input.jsp?tabla=empresa&accion=consultar">
                                        <div class="tarjeta menu-rojo">
                                            <div class="tarjeta-inner">
                                                <div class="tarjeta-front">
                                                    <img src="../imagenes/empresas.png" alt="Empresas" class="imagen-principal imagen-grande">
                                                    <img src="../imagenes/info-icon.png" alt="Información" class="imagen-sec">
                                                </div>
                                                <div class="tarjeta-back">
                                                    <h1>Mostrar información de una empresa</h1>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <div class="tarjeta menu-naranja">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/customer-icon.png" alt="Clientes" class="imagen-principal imagen-grande">
                                                <img src="../imagenes/lista-icon.png" alt="Lista" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Mostrar lista de clientes</h1>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tarjeta menu-verde">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/supplier-icon.png" alt="Proveedor" class="imagen-principal imagen-grande">
                                                <img src="../imagenes/lista-icon.png" alt="Lista" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Mostrar lista de proveedores</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <div class="tarjeta menu-lila">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/empresas.png" alt="Empresa" class="imagen-principal imagen-grande">
                                                <img src="../imagenes/add-icon.png" alt="Insertar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Insertar una nueva empresa</h1>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tarjeta menu-agua">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/empresas.png" alt="Empresa" class="imagen-principal imagen-grande">
                                                <img src="../imagenes/edit-icon.png" alt="Editar" class="imagen-sec edit">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Editar información de una empresa</h1>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tarjeta menu-darknaranja">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/empresas.png" alt="Empresa" class="imagen-principal imagen-grande">
                                                <img src="../imagenes/delete-icon.png" alt="Eliminar" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Eliminar una empresa</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                        </div>
                        <div id="estante-funciones">
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <div class="tarjeta menu-rojo">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/warehouse-icon.png" alt="Almacén" class="imagen-principal imagen-grande">
                                                <img src="../imagenes/percentage-icon.png" alt="Porcentaje" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Calcular índice de ocupación del almacén</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
                            </div>
                            <div class="shelf">
                                <div class="back"></div>
                                <div class="estanteria">
                                    <div class="tarjeta menu-naranja">
                                        <div class="tarjeta-inner">
                                            <div class="tarjeta-front">
                                                <img src="../imagenes/productos.png" alt="Productos" class="imagen-principal">
                                                <img src="../imagenes/euro-icon.png" alt="Euros" class="imagen-sec">
                                            </div>
                                            <div class="tarjeta-back">
                                                <h1>Calcular valor de los productos del almacén</h1>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="base"></div>
                                <div class="front"></div>
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
            </div>
        </div>
    </body>
    <script>
        const DOM = {
            btnTop: document.getElementById("liTop"),
            truckDiv: document.getElementById("loader"),
            main: document.querySelector("main"),
            cortina: document.querySelector(".cortina-panel"),
            btnMenu: document.getElementById("mostrarMenu"),
            menu: document.getElementById("navMenu"),
            header: document.getElementById("hdrMenu"),
            footer: document.getElementById("footer")
        }

        const removeTruck = () => {
            setTimeout(() => {
                if (document.location.href == "#") scrollTop();
                DOM.main.style.visibility = "visible";
                DOM.cortina.style.transform = "translateX(-100%)";
            }, 5500);
            //5500
            setTimeout(() => {
                DOM.truckDiv.style.display = "none";
                DOM.header.style.left = "0";
                DOM.footer.style.right = "0";
            }, 7550);
            setTimeout(() => {
                DOM.truckDiv.style.display = "none";
            }, 8000);
            //8000
        }
        const scrollTop = () => {
            document.location.href = "#";
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }

        const mostrarMenu = () => {
            DOM.menu.classList.toggle("oculto");
            DOM.btnMenu.classList.toggle("boton-grande");
        }

        DOM.btnMenu.addEventListener("click", mostrarMenu);

        window.addEventListener('popstate', () => {
            let pagina = document.location.href.match("#.*");
            document.querySelectorAll("nav a").forEach(elemento => elemento.classList.remove("selected"));
            let elementoMenu = document.querySelector(`nav a[href="${pagina}"]`);
            if (elementoMenu != null) elementoMenu.classList.add("selected");
        });

        DOM.btnTop.addEventListener("click", scrollTop);
        removeTruck();
    </script>

    </html>