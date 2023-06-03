<%-- 
    Document   : listar
    Created on : 2/06/2023, 10:31:23 PM
    Author     : dany3
--%>

<%@page import="java.util.Iterator"%>
<%@page import="ModeloDAO.NoticiaDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Noticia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Noticias</h1>
            <a class="btn btn-success" href="Controlador?accion=add">Agregar Nueva</a>
            <br>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">titulo</th>
                        <th class="text-center">contenido</th>
                        <th class="text-center">fechaPublicacion</th>
                        <th class="text-center">idUsuarioPublicante</th>

                    </tr>
                </thead>
                <%
                    NoticiaDAO dao = new NoticiaDAO();
                    List<Noticia> list = dao.listar();
                    Iterator<Noticia> iter = list.iterator();
                    Noticia per = null;
                    while (iter.hasNext()) {
                        per = iter.next();

                %>

                <tbody>
                    <tr>
                        <td class="text-center"><%= per.getIdNoticia()%></td>
                        <td class="text-center"><%= per.getTitulo()%></td>
                        <td class="text-center"><%= per.getContenido()%></td>
                        <td class="text-center"><%= per.getFechaPublicacion()%></td>
                        <td class="text-center"><%= per.getIdUsuarioPublicante()%></td>

                <a>Editar</a>
                <a>Remove</a>

                </td>
                </tr>
                <%}%>
                </tbody>


            </table>

        </div>
    </body>
</html>