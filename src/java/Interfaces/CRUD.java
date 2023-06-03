/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Modelo.Noticia;
import java.util.List;

/**
 *
 * @author dany3
 */
public interface CRUD {
    public List listar();
    public Noticia list(int idNoticia);
    public boolean add(Noticia per);
}
