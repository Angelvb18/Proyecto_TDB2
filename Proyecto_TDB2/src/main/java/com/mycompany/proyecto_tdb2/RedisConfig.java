/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
