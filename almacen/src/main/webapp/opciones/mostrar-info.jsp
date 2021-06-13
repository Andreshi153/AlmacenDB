<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@page import="es.iespuertolacruz.almacen.controlador.*, java.lang.reflect.Method" %>
        <% String tabla=request.getParameter("tabla"); String
            tablaUpper=tabla.replaceFirst(String.valueOf(tabla.charAt(0)),
            String.valueOf(Character.toUpperCase(tabla.charAt(0)))); String
            identificador=request.getParameter("identificador"); String
            nombreClase="es.iespuertolacruz.almacen.controlador." + tablaUpper + "Controller" ; Class
            claseController=Class.forName(nombreClase); Object controller=(Object) claseController.newInstance(); Object
            objetoBuscado=null; String[] infoObjeto; %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Mostrar información de
                    <%=tabla%>
                </title>
                <link rel="stylesheet" href="estilos-mostrar-info.css">
            </head>

            <body>
                <main>
                    <div id="loader">
                        <div class="TruckLoader">
                            <div class="TruckLoader-cab"></div>
                            <div class="infoObjeto">
                                <% try { Method buscar=claseController.getMethod("buscar", String.class);
                                objetoBuscado=buscar.invoke(controller, identificador); } catch (Exception ex) { throw
                                ex; } if(objetoBuscado !=null) { infoObjeto=objetoBuscado.toString().split("#"); %>

                                    <h1>
                                        <%=tablaUpper%>
                                    </h1>
                                    <h3>
                                        <%=infoObjeto[0]%>
                                    </h3>
                                    <%for(int i=1; i < infoObjeto.length; i++) {%>
                                        <p>
                                            <%=infoObjeto[i]%>
                                        </p>
                                        <%}} else {%>
                                            <h3>No existe ese/a
                                                <%=tabla%>.
                                            </h3>
                                            <%}%>
                            </div>
                        </div>
                    </div>
                    <div class="botones">
                        <a href="./leer-input.jsp?tabla=<%=tabla%>&accion=consultar">
                            <div class="boton">
                                Realizar otra búsqueda
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