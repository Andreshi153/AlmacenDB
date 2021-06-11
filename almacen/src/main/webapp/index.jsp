<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <link rel="stylesheet" href="estilos-login.css">
    </head>

    <body>
        <video autoplay muted loop id="videoFondo">
        <source src="imagenes/video-fondo.mp4" type="video/mp4">
    </video>
        <div class="container">
            <header>
                <img src="imagenes/logo-circulo.png" alt="AlmacenDB">
            </header>
            <main>
                <form method="post" action="validarLogin.jsp" autocomplete="off" class="form">
                    <div class='control block-cube block-input'>
                        <input name="usuario" placeholder='Usuario' type='text'>
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
                        <input name="password" placeholder='Contraseña' type='password'>
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
                    <p>
                        <%
                        String error = request.getParameter("login");
                        if(error != null && error.equals("error")) {%>
                            El usuario o la contraseña no son correctos
                            <%}%>
                    </p>
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
                            Entrar
                        </div>
                    </button>
                </form>
            </main>
        </div>
    </body>

    </html>