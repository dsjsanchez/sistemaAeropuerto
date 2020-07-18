/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import static aeropuerto.MenuPlanificador.planificarVuelo;
import java.util.Scanner;

/**
 *
 * @author miguel_parra
 */
public class MenuCajero {
    private static Scanner sc = new Scanner(System.in);
    /* *
     * Menu principal del planificador
     * @param cajero
     */
    static String[] mein={};
    public static void menuCajero(Cajero cajero){
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                   Bienvenido al menu del Cajero                      ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        String opcion="";
        while(!opcion.equals("3")){
            System.out.println(" 1. Registrar Venta                  ");
            System.out.println(" 2. Salir.                           ");
            System.out.println(" 3. Cerrar sesion.                   ");
            System.out.print(" ------------------->Ingrese opcion: ");   
            opcion = sc.nextLine();
            switch (opcion){
                case "1":
                    cajero.menuCajero(cajero);
                case "2":
                    System.out.println("\n Gracias Por Usar Nuestros Servicios");
                    System.exit(0);
                    break;  
                case "3":    
                    Aeropuerto.login();
                default:
                    System.out.println("\t Opcion no valida");
            }
        }
    }
}