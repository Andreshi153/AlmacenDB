<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="es.iespuertolacruz.almacen.controlador.UsuarioController" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Accept User Page</title>
        </head>

        <body>
            <% 
                UsuarioController usuarioController=new UsuarioController(); String
                usuario=request.getParameter("usuario"); String password=request.getParameter("password");
                if(usuarioController.validarUsuario(usuario, password))
                    response.setHeader("Refresh", "0;url=main/main.jsp" ); 
                else
                    response.setHeader("Refresh", "0;url=index.jsp?login=error" );
                %>
        </body>

        </html>