/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class Aeropuerto {

    /**
     */
     public static ArrayList<Usuario> usuarios = new ArrayList<>();
     public static ArrayList<Aerolinea> aerolineas = new ArrayList<>();
     public static Scanner sc = new Scanner(System.in);
     public static Path path = Paths.get("users.txt");
     
    public static void main(String[] args) {
        //ManejoArchivos.limpiarConsola();
        cargarUsuarios();
        cargarAerolineas();
        login();
        //Administrador.agregarMasUsuarios();
    }
     public static void login() {
       
        System.out.println("--- INICIAR SESION ---");
    
        Usuario enteredUser = ingresarUsuario();
        while (!usuarios.contains(enteredUser)) {
            System.err.println("Usuario no encontrado");
            enteredUser = ingresarUsuario();
        }
        ArrayList<String> lineas = ManejoArchivos.Leer("users.txt");
        String c="";
        for(int i=0;i<lineas.size();i++){
            if(lineas.get(i).contains(enteredUser.getUsuario())){
                ArrayList<String> user = new ArrayList<>(Arrays.asList(lineas.get(i).split(",")));
                c=user.get(6);//numero dentro del get es la posicion en los strings del txt
                System.out.println("TIPO DE USUARIO: "+c);
                break;
            }
            
        }
        switch (c){
            case "sistemas":
                System.out.println("1. crear Usuarios");
                System.out.println("2. crear Aerolineas");
                System.out.println("3. Salir");
                switch(sc.nextLine()){
                    case "1":
                        Administrador.agregarMasUsuarios();
                        break;
                    case "2":
                        Administrador.crearAerolinea();
                        break;
                    case "3":
                        break;
                }
                break;
                
            case "comercialp":
                MenuPlanificador.menuPlanificador(new Planificador(enteredUser.getUsuario(),enteredUser.getPassword()));
                break;
            case "comercialc":
                MenuCajero.menuCajero(new Cajero(enteredUser.getUsuario(),enteredUser.getPassword()));
                break;
             
        }
        
     }
      private static Usuario ingresarUsuario() {
        /*
        Metodo de apoyo usado para el ingreso del usuario en instanciacion
        de un objeto segun lo ingresado
        */
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contrasenia: ");
        String password = sc.nextLine();
        password=desencriptarUsuario(password);
          System.out.println(password);
        return new Usuario(usuario,password);
    } 
     public static String desencriptarUsuario(String password){
         char array[]= password.toCharArray();
         for(int i = 0; i<array.length;i++){
             array[i]=(char)(array[i]+(char)5);
         }
         password=String.valueOf(array);
         return password;
     }
       private static void cargarUsuarios() {
        /*
        Metodo que carga los usuarios del archo users.txt en la lista de
        objetos User
        */
        
            ArrayList<String> lines = ManejoArchivos.Leer("users.txt"); //Devuelve el 
//            archivo como una lista de Strings donde cada item es una 
//            linea del archivo, y cada linea representa un objeto
            System.out.println(lines.size());
            for (String line : lines) {
                System.out.println(line);
//                Se realiza un split por coma(,) para separar las propiedades
                String[] fields = line.split(",");
//                Se instancia un nuevo User con las propiedades
                Usuario user = new Usuario(fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8]);
//                Se lo almacena en la lista de usuarios
                usuarios.add(user);
            }
        
    }
       private static void cargarAerolineas() {
        /*
        Metodo que carga los usuarios del archo users.txt en la lista de
        objetos User
        */
        
            ArrayList<String> lines = ManejoArchivos.Leer("aerolineas.txt"); //Devuelve el 
//            archivo como una lista de Strings donde cada item es una 
//            linea del archivo, y cada linea representa un objeto
            for (String line : lines) {
                System.out.println(line);
//                Se realiza un split por coma(,) para separar las propiedades
                String[] fields = line.split(",");
//                Se instancia un nuevo User con las propiedades
                Aerolinea user = new Aerolinea(fields[0]);
//                Se lo almacena en la lista de usuarios
                aerolineas.add(user);
            }
       }
    }


