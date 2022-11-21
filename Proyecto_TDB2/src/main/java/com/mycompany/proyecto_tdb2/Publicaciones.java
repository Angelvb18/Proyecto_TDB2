/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author angel
 */
public class Publicaciones {
    int id_cuenta;
    int id_publicaciones;
    String [] contenido;
    String fecha;
    ArrayList<Comentarios>Comentarios = new ArrayList();

    public Publicaciones(int id_cuenta, int id_publicaciones, String[] contenido, String fecha) {
        this.id_cuenta = id_cuenta;
        this.id_publicaciones = id_publicaciones;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_publicaciones() {
        return id_publicaciones;
    }

    public void setId_publicaciones(int id_publicaciones) {
        this.id_publicaciones = id_publicaciones;
    }

    public String[] getContenido() {
        return contenido;
    }

    public void setContenido(String[] contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Comentarios> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(ArrayList<Comentarios> Comentarios) {
        this.Comentarios = Comentarios;
    }

    @Override
    public String toString() {
        
        //Por ahora solo imprimer el texto, no la foto
       
        return contenido[1];
    }

    public Publicaciones() {
    }
    
    
    
}
