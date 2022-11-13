/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

/**
 *
 * @author angel
 */
public class Comentarios {
    int id_Comentario;
    int id_Cuenta;
    int id_publicacion;
    String email;
    String contenido;

    public Comentarios(int id_Comentario, int id_Cuenta, int id_publicacion, String email, String contenido) {
        this.id_Comentario = id_Comentario;
        this.id_Cuenta = id_Cuenta;
        this.id_publicacion = id_publicacion;
        this.email = email;
        this.contenido = contenido;
    }

    public int getId_Comentario() {
        return id_Comentario;
    }

    public void setId_Comentario(int id_Comentario) {
        this.id_Comentario = id_Comentario;
    }

    public int getId_Cuenta() {
        return id_Cuenta;
    }

    public void setId_Cuenta(int id_Cuenta) {
        this.id_Cuenta = id_Cuenta;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
