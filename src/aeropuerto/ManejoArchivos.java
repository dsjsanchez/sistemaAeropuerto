package aeropuerto;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * To change package Archivos;

import java.io.*;
import java.util.ArrayList;

/**
 * Los archivos se van a guardar y leer fuera de la carpeta src
 * @author Renato
 */
public class ManejoArchivos {
    
    /**
     * Se encarga de Leer los archivos que se le mande como parametro
     * No se valida su existencia porque eso se aprovecha en el codigo de otras clases
     * 
     * 
     */
    public static ArrayList<String> Leer(String nombreArchivo) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<String> lineas =new ArrayList<String>();
        
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (nombreArchivo);
            if(archivo.exists()){
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null){
                lineas.add(linea);
            }
            return lineas;}
            else{return null;}
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{                    
                if( null != fr ){   
                   fr.close();     
                }                  
            }catch (Exception e2){ 
                e2.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * En el archivo que se le mande como parametro escribe una nueva linea y le aniade el salto de linea
     * Si el archivo no existe, lo crea
     * 
     *  
     */
    public static void Escribir(String nombreFichero,String linea){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombreFichero,true);
            pw = new PrintWriter(fichero);   
            
            pw.println(linea);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    /**
     * Se encarga se eliminar todo lo que tenga dentro un archivo
     */
    public static void limpiarConsola() {
        
        //File fichero1 = new File("users.txt");
        File fichero2 = new File("aviones.txt");
        File fichero3 = new File("vuelos.txt");
        File fichero4 = new File("ventas.txt");
        File fichero5 = new File("aerolineas.txt");

        /*if (fichero1.delete()){
                Escribir("users.txt","0921456216,Daniel,Sanchez,pepepapo,Ifsnjq6789,danielsanchez@gmail.com,sistemas,023,aerolinea");
            System.out.println("El fichero usuarios ha sido inicializado");
        }else{
            System.out.println("El fichero usuarios no pudó ser borrado");}
        */
        if (fichero2.delete()){ 
            Escribir("aviones.txt","00000,fabricante,modelo,capacidadP,capacidadN,distMax,aereolinea");
            System.out.println("El fichero aviones ha sido borrado satisfactoriamente");
        }else{
            System.out.println("El fichero aviones no pudó ser borrado");}
        
        if (fichero5.delete()){ 
            Escribir("aerolineas.txt","nombreAerolinea");
            System.out.println("El fichero aerolineas ha sido borrado satisfactoriamente");
        }else{
            System.out.println("El fichero areolineas no pudó ser borrado");}
        
        if (fichero3.delete()){
            Escribir("vuelos.txt","00000,0000,YY/MM/DD-HH:mm,YY/MM/DD-HH:mm,YY/MM/DD-HH:mm,codigoIATAsalida,codigoIATAllegada,puertaSalida,puertaArribo ");
            System.out.println("El fichero vuelos ha sido borrado satisfactoriamente");
        }else{
            System.out.println("El fichero vuelos no pudó ser borrado");}
        
        if (fichero4.delete()){
            Escribir("ventas.txt","0000,numeroVuelo,cantidadPasajeros,aeroPuertoOrigen,aeropuertoDestino,valor");
            System.out.println("El fichero ventas ha sido borrado satisfactoriamente");
        }else{
            System.out.println("El fichero ventas no pudó ser borrado");}
        
        
        
        }
    
    public static boolean cambiarLinea(String nombreArchivo,String lineaVieja, String lineaNueva){
        ArrayList<String> lineasViejas = Leer(nombreArchivo);
        ArrayList<String> lineasNuevas = new ArrayList<String>();
        boolean seCambio = false;
        for(String linea:lineasViejas){
            if(linea.equals(lineaVieja)){
                lineasNuevas.add(lineaNueva);
                seCambio=true;}
            else{
                lineasNuevas.add(linea);
            }
        }
        
        File fichero1 = new File(nombreArchivo);
        
        if(fichero1.delete()){
            for(String s:lineasNuevas){
                Escribir(nombreArchivo,s);
            }
            return seCambio;
        }
        else{
            return seCambio;
        }
      
    }
}
    
