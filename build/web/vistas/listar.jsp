<%-- 
    Document   : listar
    Created on : 2/06/2023, 10:31:23 PM
    Author     : dany3
--%>

<%@page import="java.util.Iterator"%>
<%@page import="ModeloDAO.NoticiaDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Noticia"%>
<%@page import="Modelo.Usuario"%>
<%@page import="ModeloDAO.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Noticias</title>
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
                        <th class="text-center">Título</th>
                        <th class="text-center">Contenido</th>
                        <th class="text-center">Fecha de Publicación</th>
                        <th class="text-center">Usuario Publicante</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        NoticiaDAO ndao = new NoticiaDAO();
                        List<Noticia> list = ndao.listar();
                        UsuarioDAO udao = new UsuarioDAO();
                        for(Noticia n : list){
                            Usuario usuario = udao.obtenerUsuarioPorId(n.getIdUsuarioPublicante());
                            String nombreUsuario = "";
                            if (usuario != null) {
                                nombreUsuario = usuario.getNombre();
                            }
                    %>
                    <tr>
                        <td><%= n.getIdNoticia() %></td>
                        <td><%= n.getTitulo() %></td>
                        <td><%= n.getContenido() %></td>
                        <td><%= n.getFechaPublicacion() %></td>
                        <td><%= nombreUsuario %></td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>