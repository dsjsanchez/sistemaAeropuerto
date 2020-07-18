/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author Andres
 */
public class MenuPlanificador {
    private static Scanner sc = new Scanner(System.in);
    /**
     * Menu principal del planificador
     *  
     */
    
    public static void menuPlanificador(Planificador planificador){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                Bienvenido al menu del planificador                   ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        String opcion="";
        while(!opcion.equals("3")){
            System.out.println(" 1. Planificar Vuelo.                ");
            System.out.println(" 2. Registrar Avion.                 ");
            System.out.println(" 3. Salir                            ");
            System.out.print(" ------------------->Ingrese opcion: ");   
            opcion = sc.nextLine();
            switch (opcion){
                case "1":
                    if(planificarVuelo(planificador)){
                        //System.out.println("Vuelo creado");
                        break;}
                    else{
                        System.out.println("Elija una opcion");
                        
                    };break;
                case "2":
                    if(registrarAvion(planificador)){
                        System.out.println("Vuelo creado");
                        break;}
                    else{System.out.println("Elija una opcion");};  
                case "3":    
                    System.out.println("\n Gracias Por Usar Nuestros Servicios");
                    break;
                default:
                    System.out.println("\t Opcion no valida");
            }
        }
    }
    
    
    public static boolean planificarVuelo(Planificador planificador){
        System.out.println("*************************************");
        System.out.println("           Planificar Vuelo          ");
        System.out.println("*************************************");
        
        Usuario user = buscarUsuario(planificador.getUsuario(),planificador.getPassword());
        // Cargar archivos
        ArrayList <String> lineasA = ManejoArchivos.Leer("aviones.txt");
        ArrayList<String> lineasV = ManejoArchivos.Leer("vuelos.txt");
        lineasA.remove(0);
        lineasV.remove(0);
        
        if(lineasA.size()==0){
            System.err.println("NO hay aviones en existencia: ");
            return false;
        }
        
        boolean hayVuelos=false;
        // YY/MM/DD-HH:mm,puertaSalida
        ArrayList<String> disponibilidad = new ArrayList<>();
        
        if(lineasV.size()!=0){// YY/MM/DD-HH:mm,puertaSalida
            for(String linea : lineasV){
                String vsplit[] = linea.split(",");
                String fhp = vsplit[2]+","+vsplit[vsplit.length - 2];
                disponibilidad.add(fhp);
                hayVuelos=true;
            }System.out.println("DISPONIBILIDADDES:   "+disponibilidad);
        }

        System.out.println("Elija un avion disponible de su aerolinea: ");
        ArrayList <String> avdisp = new ArrayList<>();
        byte b= 1;
        for(String linea:lineasA){
            System.out.println("LINEA DEL ARCHIVO : "+linea);
            String avionSplit[] = linea.split(",");
            System.out.println("ULTIMO ELEMENTO DEL SPLIT: "+avionSplit[avionSplit.length - 1]);
            System.out.println("AEROLINEA DE ESTE PLANIFICADOR: "+user.getAerolinea());
            if(avionSplit[avionSplit.length - 1].equals(user.getAerolinea())){
                avdisp.add(linea);
                System.out.println(b+".- Numero de serie: "+avionSplit[0]+"\nFabricante: "+avionSplit[1]+"\n Modelo: "+avionSplit[2]);
            }
            else{
                continue;
            }
        }
         
        
        String op = sc.nextLine();
        
        if(avdisp.size()==0){
            System.err.println("No hay aviones pertenecientes a esta aerolinea: ");
            return false;
        }
        
        String avSel = avdisp.get(Integer.parseInt(op) - 1 );
        String numSerie = avSel.split(",")[0];
        
        System.out.println("Ingrese el codigo de vuelo: ");
        String cv=sc.nextLine();
        
        boolean v=true;
        while(v){
            for(String linea :lineasV){
                String vsplit[] = linea.split(",");
                if(vsplit[1].equals(cv)){
                    System.err.println("CODIGO DE VUELO REPETIDO:");
                    System.out.println("Ingrese nuevamente el codigo de vuelo: ");
                    cv=sc.nextLine();
                }
            }
            v=false;
        }
        
        System.out.println("Ingrese la fecha y hora de embarque: ");
        String fhe=pedirFormatoFecha()+"-"+pedirFormatoHora();
        System.out.println("Ingrese la fecha y hora de salida: ");
        String fhs=pedirFormatoFecha()+"-"+pedirFormatoHora();
        System.out.println("Ingrese la fecha y hora de arribo: ");
        String fha=pedirFormatoFecha()+"-"+pedirFormatoHora();
        System.out.println("Ingrese el codigo IATA del aeropuerto de salida:  ");
        String iata1=sc.nextLine();
        System.out.println("Ingrese el codigo IATA del aeropuerto de llegada: ");
        String iata2=sc.nextLine();
        System.out.println("Indique la puerta de embarque de salida: ");
        String pt1=sc.nextLine();
        System.out.println("Indique la puerta de embarque de arribo: ");
        String pt2=sc.nextLine();
        boolean n=true;
        while(n){
            if(!comprobarDisponibilidad(disponibilidad,fhe+","+pt1)){
                System.out.println("Ingrese la fecha y hora de embarque nuevamente: ");
                fhe=pedirFormatoFecha()+"-"+pedirFormatoHora();
                System.out.println("Indique la puerta de embarque de salida nuevamente: ");
                pt1=sc.nextLine();
            }else{n=false;}
        }
        String cadena = numSerie+","+cv+","+fhe+","+fhs+","+fha+","+iata1+","+iata2+","+Integer.parseInt(avSel.split(",")[3])+ "," +Integer.parseInt(avSel.split(",")[4])+ "," +pt1+ "," +pt2;
        ManejoArchivos.Escribir("vuelos.txt",cadena);
        return true;
    }

    private static boolean registrarAvion(Planificador planificador) {
        ArrayList <String> dataav = ManejoArchivos.Leer("aviones.txt");
        
        System.out.println("Registro de avion.");
        System.out.println("Ingrese numero de serie ");
        String ns=sc.nextLine();
        for(String line: dataav){
            if(line.contains(ns)){
                System.out.println("Ya ha sido creado este avion antes");
                return false;
            }
            else{
            continue;
            }
        }
        System.out.println("Ingrese el fabricante ");
        String fab=sc.nextLine();
        System.out.println("Ingrese el modelo ");
        String mdelo=sc.nextLine();
        System.out.println("Ingrese capacidad ejecutiva ");
        String cape=sc.nextLine();
        System.out.println("Ingrese capacidad normal ");
        String capn=sc.nextLine();
        System.out.println("Ingrese la distancia maxima que puede recorrer el avion: ");
        String distmax=sc.nextLine();
        
        Usuario user = buscarUsuario(planificador.getUsuario(),planificador.getPassword());
        
        
        Avion v = new Avion(ns,fab,mdelo,Integer.parseInt(cape),Integer.parseInt(capn),distmax,user.getAerolinea());
        String s= v.toString();
        ManejoArchivos.Escribir("aviones.txt",s);
        System.out.println("Avion creado satisfactoriamente.");
        return true;
    }

      public static String pedirFormatoFecha(){
        String fecha="";
        //Pide el anio
        boolean stopBucleAnio=false;
        while(!stopBucleAnio){
            System.out.print("\tIngrese anio: ");
            String anio = sc.nextLine();
            if(isNumero(anio)){
                Integer anioNumero= new Integer(anio);
                if(anioNumero>2018 && anioNumero<3001){
                    fecha = anio+"/";
                    stopBucleAnio=true;
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        //Pide el mes
        boolean stopBucleMes=false;
        while(!stopBucleMes){
            System.out.print("\tIngrese mes: ");
            String mes = sc.nextLine();
            if(isNumero(mes)){
                Integer mesNumero= new Integer(mes);
                if(mesNumero<13 && mesNumero>0){
                    if(mes.length()==1){
                    fecha=fecha+"0"+mes+"/";
                    stopBucleMes=true;}
                    else{fecha=fecha+mes+"/";
                    stopBucleMes=true;}
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        //Pide el dia
        boolean stopBucleDia=false;
        while(!stopBucleDia){
            System.out.print("\tIngrese Dia: ");
            String dia = sc.nextLine();
            if(isNumero(dia)){
                Integer diaNumero= new Integer(dia);
                if(diaNumero<32 && diaNumero>0){
                    if(dia.length()==1){
                    fecha=fecha+"0"+dia;
                    stopBucleDia=true;}
                    else{fecha=fecha+dia;
                    stopBucleDia=true;}
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        return fecha;
    
    }
    
    public static String pedirFormatoHora(){
        
        String horaTotal="";
        //Pide la hora
        boolean stopBucleHora=false;
        while(!stopBucleHora){
            System.out.print("\tIngrese hora: ");
            String hora = sc.nextLine();
            if(isNumero(hora)){
                Integer horaNumero= new Integer(hora);
                if(horaNumero>-1 && horaNumero<25){
                    if(hora.length()==1){
                    horaTotal = "0"+hora+":";
                    stopBucleHora=true;}
                    else{
                        horaTotal = hora+":";
                        stopBucleHora=true;
                    }
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        
        //Pide los minutos
        boolean stopBucleMin=false;
        while(!stopBucleMin){
            System.out.print("\tIngrese los minutos: ");
            String minutos = sc.nextLine();
            if(isNumero(minutos)){
                Integer minutosNumero= new Integer(minutos);
                if(minutosNumero<60 && minutosNumero>-1){
                    if(minutos.length()==1){
                    horaTotal=horaTotal+"0"+minutos;
                    stopBucleMin=true;}
                    else{horaTotal=horaTotal+minutos;
                    stopBucleMin=true;}
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        return horaTotal;
        
    }
    
    public static boolean isNumero(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        
        return resultado;
    }
    
    public static boolean comprobarDisponibilidad(ArrayList<String> disponibilidades,String necesaria){
        
            String necsplit[] = necesaria.split(",");
            String fechaHoraNec[] = necsplit[0].split("-"); // YY/MM/DD    y   HH:mm  
            String puertaSalidaNec = necsplit[1]; //puerta de salida
            String fechaSplitNec[] = fechaHoraNec[0].split("/");
            String horaSplitNec[] = fechaHoraNec[1].split(":");
            String anioNec = fechaSplitNec[0];
            String mesNec = fechaSplitNec[1];
            String diaNec = fechaSplitNec[2];
            String horaNec = horaSplitNec[0];
            String minutosNec = horaSplitNec[1];    
        
        for(String linea : disponibilidades){ //YY/MM/DD-HH:mm,puertaSalida
            String lsplit[] = linea.split(",");
            String fechaHora[] = lsplit[0].split("-"); // YY/MM/DD    y   HH:mm  
            String puertaSalida = lsplit[1]; //puerta de salida
            String fechaSplit[] = fechaHora[0].split("/");
            String horaSplit[] = fechaHora[1].split(":");
            String anio = fechaSplit[0];
            String mes = fechaSplit[1];
            String dia = fechaSplit[2];
            String hora = horaSplit[0];
            String minutos = horaSplit[1];
            if(anio.equals(anioNec)){
                if(mes.equals(mesNec)){
                    if(dia.equals(diaNec)){
                        if(hora.equals(horaNec) && puertaSalida.equals(puertaSalidaNec)){
                            System.err.println("Este horario no se encuentra disponible: ");
                            return false;
                        }             
                    }else{continue;}                  
                }else{continue;}
            }else{continue;}
            
            
            
        }return true;
    }
    
    public static Usuario buscarUsuario(String nombre, String contrasena){
        System.out.println(nombre);
        
    ArrayList<String> usuarios = ManejoArchivos.Leer("users.txt");
    System.out.println(contrasena);
    for(String linea:usuarios){
        System.out.println(linea.contains(nombre));
        System.out.println(linea.contains(contrasena));
        if(linea.contains(nombre) && linea.contains(contrasena)){
            String split[]= linea.split(",");
            return new Usuario(split[0],split[1],split[2],split[3],split[4],split[5],split[6],split[7],split[8]); 
                    }
            
    }
    return null;
    }
    
    public static String pedirFormatoFechaNacimiento(){
        String fecha="";
        //Pide el anio
        boolean stopBucleAnio=false;
        while(!stopBucleAnio){
            System.out.print("\tIngrese anio: ");
            String anio = sc.nextLine();
            if(isNumero(anio)){
                Integer anioNumero= new Integer(anio);
                if(anioNumero>1900 && anioNumero<=2019){
                    fecha = anio+"/";
                    stopBucleAnio=true;
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        //Pide el mes
        boolean stopBucleMes=false;
        while(!stopBucleMes){
            System.out.print("\tIngrese mes: ");
            String mes = sc.nextLine();
            if(isNumero(mes)){
                Integer mesNumero= new Integer(mes);
                if(mesNumero<13 && mesNumero>0){
                    if(mes.length()==1){
                    fecha=fecha+"0"+mes+"/";
                    stopBucleMes=true;}
                    else{fecha=fecha+mes+"/";
                    stopBucleMes=true;}
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        //Pide el dia
        boolean stopBucleDia=false;
        while(!stopBucleDia){
            System.out.print("\tIngrese Dia: ");
            String dia = sc.nextLine();
            if(isNumero(dia)){
                Integer diaNumero= new Integer(dia);
                if(diaNumero<32 && diaNumero>0){
                    if(dia.length()==1){
                    fecha=fecha+"0"+dia;
                    stopBucleDia=true;}
                    else{fecha=fecha+dia;
                    stopBucleDia=true;}
                }else{
                    System.out.println("  \tNumero fuera de rango, intente nuevamente");
                    //continue;
                }
            }else{
                System.out.println("  \tEsto no es un numero, intente nuevamente");
                //continue;
            }
        }
        return fecha;
    
    }
}
