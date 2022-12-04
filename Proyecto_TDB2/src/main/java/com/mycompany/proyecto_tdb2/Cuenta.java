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
    ArrayList<Cuenta>solicitudes = new ArrayList();
    String Foto_Perfil;
    String Foto_Portada;
    
    public Cuenta() {
    }
    public void ObtenerAmigos(RedisConfig conecxion , int id){
       
       Object[] lista  = conecxion.Obtener_Lista_de_Registro("Amigos:"+id);
       System.out.println("s"+lista.length);
       for (int i = 0; i < lista.length; i++) {
            System.out.println("dsa");
            amigos.add(conecxion.Obtener_Una_Cuenta(lista[i].toString()));
        }
    }
    public void ObtenerSolicitudes(RedisConfig conecxion , int id){
       
        Object[] lista  = conecxion.Obtener_Lista_de_Registro("Solicitudes:"+id);
        System.out.println("s"+lista.length);
       for (int i = 0; i < lista.length; i++) {
           System.out.println("dsa"); 
           solicitudes.add(conecxion.Obtener_Una_Cuenta(lista[i].toString()));
        }
    }
    public Cuenta(int id_cuenta, String email, String nombre, String apellido, String fecha, String Foto_Perfil, String Foto_Portada) {
        this.id_cuenta = id_cuenta;
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha = fecha;
        this.Foto_Perfil = Foto_Perfil;
        this.Foto_Portada = Foto_Portada;
    }
    
    
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
        System.out.println("Tamañolist_publicaciones:"+publicaciones.size());
        this.publicaciones = publicaciones;
    }

    public ArrayList<Cuenta> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Cuenta> amigos) {
        this.amigos = amigos;
    }

    public String getFoto_Perfil() {
        return Foto_Perfil;
    }

    public void setFoto_Perfil(String Foto_Perfil) {
        this.Foto_Perfil = Foto_Perfil;
    }

    public String getFoto_Portada() {
        return Foto_Portada;
    }

    public void setFoto_Portada(String Foto_Portada) {
        this.Foto_Portada = Foto_Portada;
    }

    public ArrayList<Cuenta> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Cuenta> solicitudes) {
        this.solicitudes = solicitudes;
    }

    
    
    @Override
    public String toString() {
        String salida = "id_cuenta=" + id_cuenta + ", email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha=" + fecha+"\n";
        System.out.println("s:"+ publicaciones.size());
        for (int i = 0; i < publicaciones.size(); i++) {
            
            salida += i+"*"+publicaciones.get(i).toString()+"\n";
            
        }
        return salida;  
    }
    
    public void agregar_solicitud (Cuenta destinatario) {
        solicitudes.add(destinatario);
        
    }
    
}
