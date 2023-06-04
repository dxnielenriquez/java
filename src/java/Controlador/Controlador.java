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
        
     
        String idUsuarioPublicanteString = request.getParameter("idUsuarioPublicante");
        int idUsuarioPublicante;
        if (idUsuarioPublicanteString != null) {
            try {
                idUsuarioPublicante = Integer.parseInt(idUsuarioPublicanteString);
            } catch (NumberFormatException e) {
             
                idUsuarioPublicante = 0; 
            }
        } else {
            idUsuarioPublicante = 0; 
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            java.util.Date parsedDate = dateFormat.parse(fechaPublicacion);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            p.setFechaPublicacion(timestamp);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        
        p.setTitulo(titulo);
        p.setContenido(contenido);
        p.setIdUsuarioPublicante(idUsuarioPublicante);

dao.add(p, p.getIdUsuarioPublicante());
        acceso = listar;
    }
    RequestDispatcher vista = request.getRequestDispatcher(acceso);
    vista.forward(request, response);
}


  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String action = request.getParameter("accion");
    if (action != null && action.equalsIgnoreCase("Agregar")) {
        // Obtener los parámetros de la solicitud
        String titulo = request.getParameter("txtTitulo");
        String contenido = request.getParameter("txtContenido");
        String fechaPublicacion = request.getParameter("txtFechaPublicacion");
        String idUsuarioPublicanteString = request.getParameter("idUsuarioPublicante");
        
        
        int idUsuarioPublicante = Integer.parseInt(idUsuarioPublicanteString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Timestamp fechaPublicacionTimestamp = null;
        try {
            java.util.Date parsedDate = dateFormat.parse(fechaPublicacion);
            fechaPublicacionTimestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            
        }

        // Crear una instancia de Noticia y establecer los valores
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setContenido(contenido);
        noticia.setFechaPublicacion(fechaPublicacionTimestamp);
        noticia.setIdUsuarioPublicante(idUsuarioPublicante);

    
        boolean resultado = dao.add(noticia, idUsuarioPublicante);

        if (resultado) {
          
            response.sendRedirect("Controlador?accion=listar");
        } else {
           
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Error al agregar la noticia');");
            out.println("window.location.href = 'Controlador?accion=listar';");
            out.println("</script>");
        }
    } else {
        // Redireccionar al usuario a la página de lista de noticias
        response.sendRedirect("Controlador?accion=listar");
    }
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
