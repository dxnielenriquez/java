package Controlador;

import Modelo.Noticia;
import ModeloDAO.NoticiaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    String listar = "vistas/listar.jsp";
    String add = "vistas/add.jsp";
    Noticia p = new Noticia();
    NoticiaDAO dao = new NoticiaDAO();
    int idUsuarioPublicante = 4; // Reemplaza el 1 con un id de usuario válido que exista en tu tabla de usuarios.


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listar")) {
            acceso = listar;
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("Agregar")) {
            String titulo = request.getParameter("txtTitulo");
            String contenido = request.getParameter("txtContenido");
            String fechaPublicacion = request.getParameter("txtFechaPublicacion");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date parsedDate = dateFormat.parse(fechaPublicacion);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                p.setFechaPublicacion(timestamp);
            } catch(ParseException e) {
                e.printStackTrace();
            }
            
            p.setTitulo(titulo);
            p.setContenido(contenido);

            dao.add(p, idUsuarioPublicante);
            acceso = listar;
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
