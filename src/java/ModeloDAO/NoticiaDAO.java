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
    public List<Noticia> listar() {
        ArrayList<Noticia> list = new ArrayList<>();
        String sql = "SELECT * FROM noticias";
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
                per.setIdUsuarioPublicante(rs.getInt("idUsuarioPublicante"));

                list.add(per);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Noticia list(int idNoticia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean add(Noticia per, int idUsuarioPublicante) {
    String sql = "INSERT INTO noticias (idUsuarioPublicante, titulo, contenido, fechaPublicacion) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuarioPublicante);
            ps.setString(2, per.getTitulo());
            ps.setString(3, per.getContenido());
            ps.setTimestamp(4, per.getFechaPublicacion());

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
   public boolean add(Noticia per) {
    return add(per, per.getIdUsuarioPublicante());
}

}
