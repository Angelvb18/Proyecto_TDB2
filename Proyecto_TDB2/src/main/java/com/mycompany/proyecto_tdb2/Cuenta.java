/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

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
    ArrayList<Publicaciones>publicaciones_to_show = new ArrayList();
    String acceso;
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

    public ArrayList<Cuenta> AmigosdeAmigos(Cuenta cuenta_A , Cuenta cuenta_B){
       ArrayList lista_amigosdeamigos = new ArrayList();
       for (int i = 0; i < cuenta_A.getAmigos().size(); i++) {
           for (int j = 0; j < cuenta_B.getAmigos().size(); j++) {
               if(cuenta_A.getId_cuenta() != cuenta_B.getAmigos().get(j).getId_cuenta()){
                   if (cuenta_A.getAmigos().get(i) == cuenta_B.getAmigos().get(j)) {
                        lista_amigosdeamigos.add(cuenta_B.getAmigos().get(j));
                    }
               }
           }
       }
       return lista_amigosdeamigos;
   }

    public ArrayList<Publicaciones> getPublicaciones_to_show() {
        return publicaciones_to_show;
    }

    public void setPublicaciones_to_show(ArrayList<Publicaciones> publicaciones_to_show) {
        this.publicaciones_to_show = publicaciones_to_show;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String Acesso) {
        this.acceso = Acesso;
    }
    
    public ArrayList<Publicaciones> Publicaciones_de_Amigos_de_Amigos(Cuenta cuenta_A , Cuenta cuenta_B){
        ArrayList <Publicaciones> temp_to_show = new ArrayList();
        ArrayList <Cuenta> amigos_de_amigos = AmigosdeAmigos(cuenta_A, cuenta_B);
        for (int i = 0; i < amigos_de_amigos.size(); i++) {
            if(amigos_de_amigos.get(i).getAcceso().equals("Amigos de Amigos")){
                for (int j = 0; j < amigos_de_amigos.get(i).getPublicaciones().size(); j++) {
                    temp_to_show.add(amigos_de_amigos.get(i).getPublicaciones().get(j));
                }
            }
        }
        return temp_to_show;
    }
    public void Publicaciones_to_show(){
        publicaciones_to_show = new ArrayList();
        for (int i = 0; i < amigos.size(); i++) {
            for (int j = 0; j < amigos.get(i).getPublicaciones().size(); j++) {
                publicaciones_to_show.add(amigos.get(i).getPublicaciones().get(j));
            }
        }
    }
    
    @Override
    public String toString() {
        String salida = "id_cuenta=" + id_cuenta + ", email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", fecha=" + fecha+"\n";
        System.out.println("s:"+ publicaciones.size());
        for (int i = 0; i < publicaciones.size(); i++) {
            
            salida += i+"*"+publicaciones.get(i).toString()+"\n";
            
        }
        salida += "         Amigos:\n";
        for (int i = 0; i < amigos.size(); i++) {
            salida += "     "+i+"**"+amigos.get(i).getNombre() +" "+ amigos.get(i).getEmail()+"\n";
        }
        salida += "         Solicitudes:\n";
        for (int i = 0; i < solicitudes.size(); i++) {
            salida += "     "+i+"**"+solicitudes.get(i).getNombre() +" "+ solicitudes.get(i).getEmail()+"\n";
        }
        return salida;  
    }
    
    public String agregar_solicitud (Cuenta destinatario) {
        String cadena = getNombre()+" "+ getApellido() +" ya le mando una solicitud";
        for (int i = 0; i < amigos.size(); i++) {
            if (destinatario.getEmail().equals(amigos.get(i).getEmail()) ) {
                i = amigos.size();
                cadena = "Ya lo tiene agregado como amigo";
            }
        }
        for (int i = 0; i < destinatario.getSolicitudes().size(); i++) {
            if (getEmail().equals(destinatario.getSolicitudes().get(i).getEmail()) ) {
                i = destinatario.getSolicitudes().size();
                cadena = "Ya le mando solicitud "+ destinatario.getNombre() +" " + destinatario.getApellido();
            }  
        }
        if(cadena.equals(getNombre()+" "+ getApellido() +" ya le mando una solicitud")){
            cadena = "Solicitud enviada";
        }
        for (int i = 0; i < solicitudes.size(); i++) {
            if (destinatario.getEmail().equals(solicitudes.get(i).getEmail()) ) {
                i = solicitudes.size();
                cadena = "Ya le mando solicitud";
            }  
        }
        if (cadena.equals("Solicitud enviada")) {
            solicitudes.add(destinatario);
        }
        return cadena;
    }
    public ArrayList<Cuenta> Personas_no_agregadas(ArrayList<Cuenta> lista_dada){
        
        for (int i = 0; i < solicitudes.size(); i++) {
            for (int j = 0; j < lista_dada.size(); j++) {
                if(lista_dada.get(j).getId_cuenta() == solicitudes.get(i).getId_cuenta()){
                    System.out.println("Hola");
                    lista_dada.remove(j);
                    j = lista_dada.size()+1;
                }
            }
        }
        for (int i = 0; i < amigos.size(); i++) {
            for (int j = 0; j < lista_dada.size(); j++) {
                if(lista_dada.get(j).getId_cuenta() == amigos.get(i).getId_cuenta()){
                    lista_dada.remove(j);
                    j = lista_dada.size()+1;
                }
            }
        }
        return lista_dada;
    } 
    
}
