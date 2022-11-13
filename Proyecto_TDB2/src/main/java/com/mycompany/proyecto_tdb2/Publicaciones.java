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
    Date fecha;
    ArrayList<String>Comentarios;

    public Publicaciones(int id_cuenta, int id_publicaciones, String[] contenido, Date fecha, ArrayList<String> Comentarios) {
        this.id_cuenta = id_cuenta;
        this.id_publicaciones = id_publicaciones;
        this.contenido = contenido;
        this.fecha = fecha;
        this.Comentarios = Comentarios;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<String> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(ArrayList<String> Comentarios) {
        this.Comentarios = Comentarios;
    }
    
}
