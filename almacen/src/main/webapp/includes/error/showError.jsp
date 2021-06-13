<%@page import="es.iespuertolacruz.almacen.exception.*"%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <html>

            <head>
                <title>Mensaje de error</title>
            </head>

            <body>
                <h1>
                    ¡Oh, no! Se ha producido una excepción...
                </h1>
                <h3>
                    <%=exception.getMessage()%>
                </h3>
                <a href="../../index.jsp">Volver al inicio</a>
            </body>

            </html>