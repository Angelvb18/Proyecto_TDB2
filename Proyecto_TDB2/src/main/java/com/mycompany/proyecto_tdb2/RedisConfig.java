/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tdb2;

import java.util.Map;
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
           conection.set("edad", "1");
           conection.incr("edad");
           System.out.println(conection.get("edad"));
           conection.hset("proveedor:1","id","1");
           conection.hset("proveedor:1","nombre","Lacteos");
           System.out.println("pv1: "+ conection.hget("proveedor:1", "nombre") );
           Map<String , String> fields = conection.hgetAll("proveedor:1");
           System.out.println("Atributos:" +fields.values().size());
             conection.hset("producto:1","id","1");
             conection.hset("producto:1","nombre","Lacteos");
             conection.hset("producto:1","id","1");
             conection.hset("producto:1","nombre","Lacteos");
             conection.hset("producto:2","id","2");
             conection.hset("producto:2","nombre","Arroz");
            conection.sadd("proveedores:1", "producto:1");
            
       } catch (Exception e) {
           throw e;
       }
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
