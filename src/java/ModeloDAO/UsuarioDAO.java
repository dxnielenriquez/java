/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import Config.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dany3
 */
public class UsuarioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Usuario> listarUsuariosInternosAutorizados() {
    List<Usuario> list = new ArrayList<>();
    String sql = "SELECT * FROM usuarios WHERE esInterno = 1 AND esAutorizado = 1";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNombre(rs.getString("nombreUsuario"));
            
            list.add(u);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
    }
    return list;
}
public Usuario obtenerUsuarioPorId(int idUsuario) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        rs = ps.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("idUsuario"));
            usuario.setNombre(rs.getString("nombreUsuario"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
    }
    return usuario;
}

    

}
