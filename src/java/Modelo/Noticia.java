/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Timestamp;
/**
 *
 * @author dany3
 */
public class Noticia {

    int idNoticia;
    String titulo;
    String contenido;
    Timestamp fechaPublicacion;
    int idUsuarioPublicante;

    public Noticia() {
    }

    public Noticia(int idNoticia, String titulo, String contenido, Timestamp fechaPublicacion, int idUsuarioPublicante) {
        this.idNoticia = idNoticia;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.idUsuarioPublicante = idUsuarioPublicante;
    }

    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdUsuarioPublicante() {
        return idUsuarioPublicante;
    }

    public void setIdUsuarioPublicante(int idUsuarioPublicante) {
        this.idUsuarioPublicante = idUsuarioPublicante;
    }
 
    
    
}