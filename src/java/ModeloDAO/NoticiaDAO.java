/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUD;
import Modelo.Noticia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dany3
 */
public class NoticiaDAO implements CRUD {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Noticia n = new Noticia();

    @Override
    public List listar() {
        ArrayList<Noticia> list = new ArrayList<>();
        String sql = "select * from noticias";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Noticia per = new Noticia();
                per.setIdNoticia(rs.getInt("idNoticia"));
                per.setTitulo(rs.getString("titulo"));
                per.setContenido(rs.getString("contenido"));
                per.setFechaPublicacion(rs.getTimestamp("fechaPublicacion"));
                per.setIdUsuarioPublicante(rs.getInt("idUsuarioPublicante")); // Aquí he cambiado Timestamp por getInt, ya que me parece que idUsuarioPublicante es un id (número entero), pero modifícalo según tu modelo de datos.

                list.add(per);
            }
        } catch (Exception e) {
            // Sería buena idea imprimir el error para entender qué salió mal en caso de excepción.
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Noticia list(int idNoticia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean add(Noticia per, int idUsuarioPublicante) {
    String sql="insert into noticias(idUsuarioPublicante Titulo, Contenido, TechaPublicacion)values( '"+per.getIdUsuarioPublicante()+"','"+per.getTitulo()+"','"+per.getContenido()+"','"+per.getFechaPublicacion()+"')";
     try {
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
         ps.setInt(1, idUsuarioPublicante); // Usa idUsuarioPublicante aquí
        ps.setString(2, per.getTitulo());
        ps.setString(3, per.getContenido());
        ps.setTimestamp(4, per.getFechaPublicacion());

            System.out.println("Antes de ejecutar ps.executeUpdate()");

            ps.executeUpdate();

            System.out.println("Después de ejecutar ps.executeUpdate()");

            return true;
        } catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if(ps != null) ps.close();
                if(con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    }

    @Override
    public boolean add(Noticia per) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}