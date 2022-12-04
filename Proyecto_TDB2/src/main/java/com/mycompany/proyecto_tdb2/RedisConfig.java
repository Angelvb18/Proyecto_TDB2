/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;

/**
 *
 * @author angel
 */
public class RedisConfig {
    Jedis conection ;
   
   public void connectionFactory(){
       CreateJasonObject();
       try {
           conection = new Jedis("localhost");
           System.out.println("Connection susccesful");
           System.out.println("Severping: " +conection.ping());
          //CrearBase();
           //conection.sadd("proveedores:1", "producto:3");
          //  System.out.println(conection.smembers("proveedores:1").toArray()[0]);
           /*conection.set("edad", "1");
           conection.incr("edad");
           System.out.println(conection.get("edad2"));
           conection.hset("proveedor:1","id","1");
           conection.hset("proveedor:1","nombre","Lacteos");
           
          /* Map<String , String> fields = conection.hgetAll("proveedor:1");
           System.out.println("Atributos:" +fields.values());
             conection.hset("producto:1","id","1");
             conection.hset("producto:1","nombre","Lacteos");
             conection.hset("producto:1","id","1");
             conection.hset("producto:1","nombre","Lacteos");
             conection.hset("producto:2","id","2");
             conection.hset("producto:2","nombre","Arroz");
            conection.sadd("proveedores:1", "producto:2");
            System.out.println(conection.smembers("proveedores:1"));
           System.out.println(":" + Obtener_Lista_de_Registro("proveedores:1")[0]);*/
            
       } catch (Exception e) {
           throw e;
       }
   }
   public void CrearBase(){
       if(conection.get("NumerodeCuentas") == null){
           conection.set("NumerodeCuentas", "1");
       }
       if(conection.get("NumerodePublicaciones") == null){
           conection.set("NumerodePublicaciones", "1");
       }
       if(conection.get("NumerodeComentarios") == null){
           conection.set("NumerodeComentarios", "1");
       }
   }
   public String getNumerodeComentarios(){
       return conection.get("NumerodeComentarios");
   }
   public String getNumerodePublicaciones(){
       return conection.get("NumerodePublicaciones");
   }
   public String getNumerodeCuentas(){
       return conection.get("NumerodeCuentas");
   }
   public void Agregar_a_Lista_de_Registro(String key, String member){
       conection.sadd(key, member);
   }
   
   public Map <String ,String> Obtener_Registro(String key){
       return conection.hgetAll(key);
   }
   public String Obtener_valor_Registro(String key , String feild){
       return conection.hget(key, feild);
   }
   public Object[] Obtener_Lista_de_Registro(String key){
       
       return  conection.smembers(key).toArray();
   }
   public void Agregar_Atributo_Valor_Registo (String key , String feild, String value){
       conection.hset(key, feild, value);
   }
   public void Insertar_Registro(String nombre_Tipo,String [][]Fields_and_value){
       for (int i = 0; i < Fields_and_value.length; i++) {
            Agregar_Atributo_Valor_Registo(nombre_Tipo, Fields_and_value[i][0], Fields_and_value[i][1]);
       }
       Aumento(nombre_Tipo);
   }
   
   public void Aumento(String Tipo){
       if(Tipo.contains("Comentario")){
           conection.incr("NumerodeComentarios");
       }else{
           if(Tipo.contains("Cuenta")){
               conection.incr("NumerodeCuentas");
            }else{
               if(Tipo.contains("Publicacion")){
                   conection.incr("NumerodePublicaciones");
                }
           }
       }
   }
   
   public Cuenta Obtener_Una_Cuenta(String key){
        Cuenta temp = new Cuenta();
        String [] parseado_key = key.split(":");
        Map <String ,String> cuenta = Obtener_Registro(key);
        System.out.println(":fdsfsdfdf"+key);
        temp.setId_cuenta(Integer.parseInt(parseado_key[1]));
        
        for (int j = 0; j < cuenta.values().toArray().length; j++) {
            if(cuenta.keySet().toArray()[j].toString().equals("Email")){
                temp.setEmail(cuenta.values().toArray()[j].toString());
            }
            if(cuenta.keySet().toArray()[j].toString().equals("Nombre")){
                temp.setNombre(cuenta.values().toArray()[j].toString());
            }
            if(cuenta.keySet().toArray()[j].toString().equals("Apellidos")){
                temp.setApellido(cuenta.values().toArray()[j].toString());
            }
            if(cuenta.keySet().toArray()[j].toString().equals("Password")){

            }
            if(cuenta.keySet().toArray()[j].toString().equals("Date")){
                temp.setFecha(cuenta.values().toArray()[j].toString());
            }
            if(cuenta.keySet().toArray()[j].toString().equals("foto_perfil")){
                temp.setFoto_Perfil(cuenta.values().toArray()[j].toString());
            }
            if(cuenta.keySet().toArray()[j].toString().equals("foto_portada")){
                temp.setFoto_Portada(cuenta.values().toArray()[j].toString());
            }
            
           
            
        }
         temp.setPublicaciones(Obtener_Publicaciones_deUsuario(Integer.parseInt(parseado_key[1])));
       return temp;
   }
   
   
   
    public RedisConfig() {
        connectionFactory();
    }
    
    public String convertirSHA256(String password) {
	MessageDigest md = null;
	try {
		md = MessageDigest.getInstance("SHA-256");
	} 
	catch (NoSuchAlgorithmException e) {		
		e.printStackTrace();
		return null;
	}
	    
	byte[] hash = md.digest(password.getBytes());
	StringBuffer sb = new StringBuffer();
	    
	for(byte b : hash) {        
		sb.append(String.format("%02x", b));
	}
	    
	return sb.toString();
    }
    
    public boolean validarCorreo (String correo) {
        for (int i = 1; i < Integer.parseInt(getNumerodeCuentas()); i++) {
            if (Obtener_valor_Registro("Cuenta:" + i, "Email").equals(correo)) {
                return true;
            }
        }
        
        
        return false;
    }
    
    public int login (String Email, String Contra) {
        for (int i = 1; i < Integer.parseInt(getNumerodeCuentas()); i++) {
            if (Obtener_valor_Registro("Cuenta:" + i, "Email").equals(Email)) {
                if (Obtener_valor_Registro("Cuenta:" + i, "Password").equals(convertirSHA256(Contra))) {
                    return i;
                }else{
                    return 0;
                }
            }
        }
        return 0;
    }
   
    public ArrayList<Cuenta> Obtenertodas_cuentas(int UsuarioActivo){
        ArrayList<Cuenta> listas = new ArrayList();
        for (int i = 1; i < Integer.parseInt(getNumerodeCuentas()); i++) {
            if(i != UsuarioActivo){
                Map <String ,String> cuenta = Obtener_Registro("Cuenta:"+i);
                Cuenta temp = new Cuenta();
                    temp.setId_cuenta(i);
              
                for (int j = 0; j < cuenta.values().toArray().length; j++) {
                    
                    if(cuenta.keySet().toArray()[j].toString().equals("Email")){
                        temp.setEmail(cuenta.values().toArray()[j].toString());
                    }
                    if(cuenta.keySet().toArray()[j].toString().equals("Nombre")){
                        temp.setNombre(cuenta.values().toArray()[j].toString());
                    }
                    if(cuenta.keySet().toArray()[j].toString().equals("Apellidos")){
                        temp.setApellido(cuenta.values().toArray()[j].toString());
                    }
                    if(cuenta.keySet().toArray()[j].toString().equals("Password")){

                    }
                    if(cuenta.keySet().toArray()[j].toString().equals("Date")){
                        temp.setFecha(cuenta.values().toArray()[j].toString());
                    }
                    if(cuenta.keySet().toArray()[j].toString().equals("foto_perfil")){
                        temp.setFoto_Perfil(cuenta.values().toArray()[j].toString());
                    }
                    if(cuenta.keySet().toArray()[j].toString().equals("foto_portada")){
                        temp.setFoto_Portada(cuenta.values().toArray()[j].toString());
                    }
                    
                    
                }
                temp.setPublicaciones(Obtener_Publicaciones_deUsuario(i));
                listas.add(temp);
            }
        }
        
        return listas;
    }
    
    public ArrayList<Publicaciones> Obtener_Publicaciones_deUsuario(int Usuario_Designado){
        ArrayList<Publicaciones> lista = new ArrayList(); 
        
        Object[]lista_Publicaciones = conection.smembers("Publicaciones:"+Usuario_Designado).toArray();
        for (int i = 0; i < lista_Publicaciones.length; i++) {
            String[] parseado = lista_Publicaciones[i].toString().split(":") ;
            int Id_publicacion = Integer.parseInt(parseado[1]);
            Map <String , String >  Publicacion_Actual =Obtener_Registro(lista_Publicaciones[i].toString());
            Publicaciones temp = new Publicaciones();
            temp.setId_cuenta(Usuario_Designado);
            temp.setId_publicaciones(Id_publicacion);
            for (int j = 0; j < (Publicacion_Actual.values().toArray().length-1); j++) {
                    if(Publicacion_Actual.keySet().toArray()[j].toString().equals("Contenido")){
                        temp.setContenido((Publicacion_Actual.values().toArray()[j].toString()));
                    }
                    if(Publicacion_Actual.keySet().toArray()[j].toString().equals("Foto")){
                        temp.setFoto((Publicacion_Actual.values().toArray()[j].toString()));
                    }
                    if(Publicacion_Actual.keySet().toArray()[j].toString().equals("fecha")){
                        temp.setFecha(Publicacion_Actual.values().toArray()[j].toString());
                    }
            }
            temp.setComentarios(Obtener_Comentarios_Publicacion(temp));
            lista.add(temp);
            
        }
        return  lista;
    }
    
    public int buscarCorreo (String Correo) {
        for (int i = 1; i < Integer.parseInt(getNumerodeCuentas()); i++) {
            if (Obtener_valor_Registro("Cuenta:" + i, "Email").equals(Correo)) {
                    return i;
            }
        }
        return 0;
    }
    
    public ArrayList<Cuenta> Lista_solicitada(String key){
        ArrayList<Cuenta> lista = new ArrayList();
        Object[]lista_requerida = Obtener_Lista_de_Registro(key);
        for (int i = 0; i < lista_requerida.length; i++) {
            lista.add(Obtener_Una_Cuenta(lista_requerida.toString()));
        }
        return lista;
    }
    
   public ArrayList<Comentarios> Obtener_Comentarios_Publicacion(Publicaciones pub){
       ArrayList<Comentarios> lista = new ArrayList();
       Object[]lista_Comentarios = conection.smembers("Comentarios:"+pub.getId_publicaciones()).toArray();
//       
       for (int i = 0; i < lista_Comentarios.length; i++) {
           System.out.println("Aquis;"+lista_Comentarios[i].toString());
           String[] parseado = lista_Comentarios[i].toString().split(":") ;
           int Id_Comentario = Integer.parseInt(parseado[1]);
           System.out.println("Aqui:"+parseado[0]);
           System.out.println("Aqui2:"+lista_Comentarios[i].toString());
           Map <String , String >  Publicacion_Actual =Obtener_Registro(lista_Comentarios[i].toString());
           System.out.println(Publicacion_Actual);
           
          // int id_Cuenta = Integer.parseInt(Publicacion_Actual.values().toArray()[1].toString());
           Comentarios temp = new Comentarios();
         //  temp.setId_Cuenta(id_Cuenta);
           temp.setId_publicacion(pub.getId_publicaciones());
           temp.setId_Comentario(Id_Comentario);
           System.out.println(":x:"+Publicacion_Actual.keySet());
           for (int j = 0; j < Publicacion_Actual.values().toArray().length  ; j++) {
               if(Publicacion_Actual.keySet().toArray()[j].toString().equals("Contenido")){
                   System.out.println("::"+Publicacion_Actual.values().toArray()[j].toString());
                    temp.setContenido((Publicacion_Actual.values().toArray()[j].toString()));
                }
                if(Publicacion_Actual.keySet().toArray()[j].toString().equals("Email")){
                    System.out.println("::"+Publicacion_Actual.values().toArray()[j].toString());
                    temp.setEmail(Publicacion_Actual.values().toArray()[j].toString());
                }
                if(Publicacion_Actual.keySet().toArray()[j].toString().equals("nombreComment")){
                    System.out.println("::"+Publicacion_Actual.values().toArray()[j].toString());
                    temp.setNombreComment(Publicacion_Actual.values().toArray()[j].toString());
                }
           }
           lista.add(temp);
           System.out.println("ls:"+lista.size());
           /*lista.add(new Comentarios(Id_Comentario,id_Cuenta
                   ,pub.getId_publicaciones(),
                   Publicacion_Actual.values().toArray()[1].toString(),
                   Publicacion_Actual.values().toArray()[2].toString()
                   ,Publicacion_Actual.values().toArray()[3].toString()));*/
       }
       return  lista;
   }
    
   public JSONObject CreateJasonObject(){
       String hola []= {"dsa" , "fsfsd","fsdf"};
       JSONObject jsobj = new JSONObject();
       jsobj = new JSONObject();
       jsobj.put("Name", "Carlos");
       jsobj.put("Age", 49);
       jsobj.put("arreglo", hola);
       //System.out.println(jsobj.toString());
       return  jsobj;
   }
}
