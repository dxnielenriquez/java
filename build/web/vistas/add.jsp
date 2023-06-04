<%-- 
    Document   : add
    Created on : 2/06/2023, 10:31:36 PM
    Author     : dany3
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Noticia</title>
    </head>
    <body>
        <div>
            <h1>Agregar Noticia</h1>
            <form action="Controlador">
                Datos Noticia:<br>
                <input type="text" name="txtTitulo" placeholder="Titulo"><br>
                <input type="text" name="txtContenido" placeholder="Contenido"><br>
                <input type="datetime-local" name="txtFechaPublicacion"><br>
                Seleccionar Usuario Publicante:<br>
                <br>
                <%
                    UsuarioDAO udao = new UsuarioDAO();
                    List<Usuario> list = udao.listarUsuariosInternosAutorizados();
                    if (list.isEmpty()) {
                %>
                <p>No hay usuarios internos autorizados disponibles.</p>
                <%
                } else {
                %>
                <select name="idUsuarioPublicante">
                    <% for (Usuario u : list) {%>
                    <option value="<%= u.getIdUsuario()%>"><%= u.getNombre()%></option>
                    <% } %>
                </select>
                <%
                    }
                %>
                <br>
                <br>
                <br>
                <br>
                <input class="btn btn-primary" type="submit" name="accion" value="Agregar"><br>
            </form>
        </div>
    </body>
</html>