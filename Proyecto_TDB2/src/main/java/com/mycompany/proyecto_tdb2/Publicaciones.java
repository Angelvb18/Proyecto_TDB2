/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angel
 */
public class Publicaciones {
    int id_cuenta;
    int id_publicaciones;
    String  contenido ;
    String foto;
    String fecha;
    ArrayList<Comentarios>Comentarios = new ArrayList();
    String acceso;
    Date fecha_en_date;
    public Publicaciones(int id_cuenta, int id_publicaciones, String contenido, String fecha) {
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

    public String getContenido() {
        return contenido;
    }

    public Publicaciones(int id_cuenta, int id_publicaciones, String contenido, String foto, String fecha) {
        this.id_cuenta = id_cuenta;
        this.id_publicaciones = id_publicaciones;
        this.contenido = contenido;
        this.foto = foto;
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
        setFecha_en_date();
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public Date getFecha_en_date() {
        return fecha_en_date;
    }

    public void setFecha_en_date() {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        try {
            this.fecha_en_date = sp.parse(getFecha());
        } catch (ParseException ex) {
           
        }
    }

    public ArrayList<Comentarios> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(ArrayList<Comentarios> Comentarios) {
        this.Comentarios = Comentarios;
    }

    @Override
    public String toString() {
        
        String Salida = "   /*"+id_cuenta +" "+ id_publicaciones+ " "+ contenido +" "+foto + " "+ fecha+ "\n";
        
        for (int i = 0; i < Comentarios.size(); i++) {
            Salida += i+"-"+Comentarios.get(i).toString()+"\n";
        }
        
        return Salida;
        
    }
    /*@Override
    public String toString() {
        
        //Por ahora solo imprimer el texto, no la foto
        if(contenido == null){
            return "Fallo";
        }else{
            return contenido;
        }
        
    }*/

    public Publicaciones() {
        acceso = "Amigos";
        foto = "";
        contenido = "";
    }
    
    
    
}
