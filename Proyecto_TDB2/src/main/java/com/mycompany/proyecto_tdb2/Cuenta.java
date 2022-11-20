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
public class Cuenta {
    int id_cuenta;
    String email;
    String nombre;
    String apellido;
    String fecha;
    ArrayList<Publicaciones>publicaciones = new ArrayList();
    ArrayList<Cuenta>amigos = new ArrayList();
    
    public Cuenta(int id_cuenta, String email, String nombre, String apellido, String fecha) {
        this.id_cuenta = id_cuenta;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Publicaciones> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicaciones> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public ArrayList<Cuenta> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Cuenta> amigos) {
        this.amigos = amigos;
    }

    
    
    @Override
    public String toString() {
        return "Cuenta{" + "id_cuenta=" + id_cuenta + ", email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha=" + fecha + '}';
    }
    
    
    
}
