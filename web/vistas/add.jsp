<%-- 
    Document   : add
    Created on : 2/06/2023, 10:31:36 PM
    Author     : dany3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Agregar Noticia</h1>
            <form action="Controlador">
                Datos Noticia:<br>
                <input type="text" name="txtTitulo"><br>
                <input type="text" name="txtContenido"><br>
                <input type="datetime-local" name="txtFechaPublicacion"><br>
                <input class="btn btn-primary" type="submit" name="accion" value="Agregar"><br>


            </form>
        </div>
    </body>
</html>
