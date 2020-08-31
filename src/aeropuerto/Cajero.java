/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import static aeropuerto.MenuPlanificador.planificarVuelo;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author daniel
 */

public class Cajero extends Usuario {
    Aerolinea aerolinea;

    public Cajero(String usuario, String password) {
        super(usuario, password);
    }
    

    public Cajero(String cedula, String nombre, String apellido, String usuario, String password, String email, String departamento, String identificador,String aerolinea) {
        super(cedula, nombre, apellido, usuario, password, email, departamento, identificador,aerolinea);
    }
    
    public Cajero(String usuario, String password, String aerolinea,String pais) {
        super(usuario, password);
        this.aerolinea= new Aerolinea(aerolinea);
        
    }
    
    public void menuCajero(Cajero cajero){
        Usuario user = MenuPlanificador.buscarUsuario(cajero.getUsuario(),cajero.getPassword());
        String aereolineaCajero = user.getAerolinea();
        System.out.println(aereolineaCajero);
        
        ArrayList<String> vuelos = ManejoArchivos.Leer("vuelos.txt");
        ArrayList<String> aviones = ManejoArchivos.Leer("aviones.txt");
        ArrayList<String> lugares_viajes = new ArrayList<String>();
        
        ArrayList<String> filtroAereolinea = new ArrayList<String>();
        
        String uselessline = vuelos.remove(0);
        String uselessline2 = aviones.remove(0);
        
        for(int i=0;i<vuelos.size();i++){
            String serieavion = vuelos.get(i).split(",")[0];
            for(int j=0;j<aviones.size();j++){
                String serieavion2 = aviones.get(j).split(",")[0];
                if (serieavion.equals(serieavion2)){
                    String aereolineaS = aviones.get(j).split(",")[6];
                    if (aereolineaS.equals(aereolineaCajero)){
                        filtroAereolinea.add(vuelos.get(i));
                    }
                }
            }
        }
        
        vuelos=filtroAereolinea;
        
        for(int i=0;i<vuelos.size();i++){
            String ciudad = vuelos.get(i).split(",")[6];
            if (lugares_viajes.contains(ciudad)==false){
                lugares_viajes.add(ciudad);
            }
        }
        System.out.println("--------[Pulse (0) para cancelar]-------");
        System.out.print("------------------->Seleccionar destino:");
        int lugaresViajes = mostrarViajes(lugares_viajes);
        
        
        Scanner d= new Scanner(System.in);
        String numero_destino = d.nextLine();
        int numero_dest = 0;
        boolean flag=true;
                while(flag==true){
                 if (MenuPlanificador.isNumero(numero_destino)){
                     numero_dest= Integer.parseInt(numero_destino);
                     if(numero_dest>=0 && numero_dest<=lugaresViajes){
                          flag=false;
                     }else{
                         System.err.println("OPCION INVALIDA");
                         System.out.println("Ingrese una opcion valida:");
                         numero_destino = d.nextLine();
                          flag=true;
                     }        
                 }
                else{
                System.err.println("OPCION INVALIDA");
                System.out.println("Ingrese una opcion valida:");
                numero_destino = d.nextLine();
    
                flag=true;
                    }
                }
        
        
        
        System.out.println("");
        String destino="";
        if(numero_dest==0){
            MenuCajero.menuCajero(cajero);
        }else{
            destino = lugares_viajes.get(numero_dest-1);
        }
        ArrayList<String> lugares_vuelos = new ArrayList<String>();
        System.out.println("+-------------------------------------------------+");
        System.out.println("|              VUELOS DISPONIBLES                 |");
        System.out.println("+-------------------------------------------------+");
        int cont = 1;
        for (int i=0;i<vuelos.size();i++){
            if (vuelos.get(i).contains(destino)){
                
                String asientoeconomico= vuelos.get(i).split(",")[7];
                String asientonegocios= vuelos.get(i).split(",")[8];
                if((asientoeconomico.equals("0") && asientonegocios.equals("0"))==false){
                    String flightt = vuelos.get(i);
                    
                    String codigofly=flightt.split(",")[1];
                    String bordhour = flightt.split(",")[2];
                    String arrivhour = flightt.split(",")[3];
                    String boarding=flightt.split(",")[5];
                    String arriving=flightt.split(",")[6];
                    String eco=flightt.split(",")[7];
                    String neg=flightt.split(",")[8];
                    
                    System.out.println(cont+".- "+"Codigo de avion: "+codigofly+" | Fecha de abordo: "+bordhour+" | Fecha de desembarco: "+arrivhour+" | Lugar de abordo: "+boarding+" | Lugar de desembarco: "+arriving+" | Cantidad de asientos economicos: "+eco+" | Cantidad de asientos de negocios: "+neg);
                    
                    lugares_vuelos.add(vuelos.get(i));
                    cont++;
                }
            }
        }
        Scanner v= new Scanner(System.in);
        System.out.println("--------[Pulse (0) para cancelar]-------");
        System.out.println("---------------------->Seleccione vuelo:");
        
        String numero_vuelo = v.nextLine();
   
        
        
        
        int numero_v = 0;
        boolean flag2=true;
                while(flag2==true){
                 if (MenuPlanificador.isNumero(numero_vuelo)){
                     numero_v= Integer.parseInt(numero_vuelo);
                     if(numero_v>=0 && numero_v<=lugares_vuelos.size()){
                          flag2=false;
                     }else{
                         System.err.println("OPCION INVALIDA");
                         System.out.println("Ingrese una opcion valida:");
                         numero_vuelo = v.nextLine();
                          flag2=true;
                     }        
                 }
                else{
                System.err.println("OPCION INVALIDA");
                System.out.println("Ingrese una opcion valida:");
                numero_vuelo = v.nextLine();
    
                flag2=true;
                    }
                }
        
        
       
        
        
        String vuelo="";
        if(numero_v==0){
            MenuCajero.menuCajero(cajero);
        }else{
            vuelo = lugares_vuelos.get(numero_v-1);
        }
        
        
        
        String asientoeconomico= vuelo.split(",")[7];
        Integer numeroAE = Integer.parseInt(asientoeconomico);
        String asientonegocios= vuelo.split(",")[8];
        Integer numeroAN = Integer.parseInt(asientonegocios);
        
        
        int asientostotales = Integer.parseInt(asientoeconomico) + Integer.parseInt(asientonegocios);
        
        
        System.out.println("--------[Pulse (0) para cancelar]-------");
        System.out.println("\n¿Cuantos boletos desea comprar?");
        System.out.print("------------------->Cantidad de boletos:");
        
        
        
        Scanner bolet= new Scanner(System.in);
        String numero_boletos = bolet.nextLine();
        
        
        int numero_bol=0;
        boolean s=true;
                while(s==true){
                 if (MenuPlanificador.isNumero(numero_boletos)){
                     numero_bol= Integer.parseInt(numero_boletos);
                     
                       if(numero_bol==0){
                             MenuCajero.menuCajero(cajero);
                        }
                     
                     if ((numero_bol<=asientostotales)){
        
                    s=false;
                 }
                else{
                System.err.println("No hay suficientes asientos para la cantidad de boletos");
                System.out.println("Ingrese menor cantidad de boletos:");
                
                numero_boletos = bolet.nextLine();
                s=true;
                    }
                }
                else{
                System.err.println("OPCION INVALIDA");
                System.out.println("Ingrese una opcion valida:");
                numero_boletos = bolet.nextLine();
    
                s=true;
                    }
                }
        
        
        
         
                 
        
        double valorTotal=0;
        ArrayList<Boleto> boletos= new ArrayList<>();
        
        for (int i=0;i<numero_bol;i++){
            System.out.println("COMPRA DE BOLETO NUMERO: "+(i+1));
            Scanner c= new Scanner(System.in);
            System.out.print("Ingrese cedula:");
            String cedula = c.nextLine();
            
                boolean m=true;
                while(m==true){
                 if (Administrador.validarCedula(cedula)){
        
                    m=false;
                 }
                else{
                System.err.println("Cedula invalida");
                System.out.println("Ingrese una cedula valida:");
                
                cedula = c.nextLine();
                m=true;
                    }
                }
            
            System.out.println("");
            Scanner n= new Scanner(System.in);
            System.out.print("Ingrese nombre:");
            String nombre = n.nextLine();
            
            boolean f=true;
                while(f==true){
                 if (MenuPlanificador.isNumero(nombre)==false){
        
                    f=false;
                 }
                else{
                System.err.println("Nombre invalido");
                System.out.println("Ingrese una nombre valido:");
                
                nombre = n.nextLine();
                f=true;
                    }
                }
            
            System.out.println("");
            Scanner a= new Scanner(System.in);
            System.out.print("Ingrese apellido:");
            String apellido = a.nextLine();
            
            boolean g=true;
                while(g==true){
                 if (MenuPlanificador.isNumero(apellido)==false){
        
                    g=false;
                 }
                else{
                System.err.println("Apellido invalido");
                System.out.println("Ingrese una apellido valido:");
                
                apellido = n.nextLine();
                g=true;
                    }
                }
            
            System.out.println("");
            
            System.out.print("Ingrese fecha de nacimiento(dd/mm/aa):");
            String fecha = MenuPlanificador.pedirFormatoFechaNacimiento();
            
            
            String[] fechadiv=fecha.split("/");
            String nuevafecha=fechadiv[2]+"/"+fechadiv[1]+"/"+fechadiv[0];
            
            
            System.out.println("");
            Scanner clase= new Scanner(System.in);
            System.out.println("+-------------------------------------------------+");
            System.out.println("|              CLASES DISPONIBLES                 |");
            System.out.println("+-------------------------------------------------+");
            if(Integer.parseInt(asientoeconomico)==0){
                
                System.out.println("-Negocios  (N)  [800$]");
                
                
                
            }else if (Integer.parseInt(asientonegocios)==0){
                System.out.println("-Economica (E)  [300$]");
                
                
                
            }else{
                System.out.println("-Economica (E)  [300$]");
                System.out.println("-Negocios  (N)  [800$]");
                
                
                
            }
            
                System.out.println("--------[Pulse (X) para cancelar]-------");
                System.out.print("------------------->Ingrese el tipo de clase:");
            
                String clas = clase.nextLine();
                boolean ff=true;
                while(ff){
                switch (clas){
                case "N":
                    ff=false;
                    break;
                case "E":
                    ff=false;
                    break;  
                case "X":    
                    MenuCajero.menuCajero(cajero);
                default:
                    System.err.println("\t Opcion no valida");
                    ff=true;
                    System.out.println("--------[Pulse (X) para cancelar]-------");
                    System.out.print("------------------->Ingrese el tipo de clase:");
                    clas = clase.nextLine();
                    break;
            }
                }
               
            System.out.println("");
            double valorUsuario = obtenerValor(nuevafecha,clas);
            String asiento="A";
            if (clas.equals("E")){
                asiento = clas+asientoeconomico;
                numeroAE-=1;
                String array[] = vuelo.split(",");
                
                String lineanueva = array[0]+","+array[1]+","+array[2]+","+array[3]+","+array[4]+","+array[5]+","+array[6]+","+numeroAE+","+array[8]+","+array[9]+","+array[10];
                
                ManejoArchivos.cambiarLinea("vuelos.txt",vuelo,lineanueva);
                vuelo=lineanueva;

                
            }else{
                asiento = clas+asientonegocios;
                numeroAN-=1;
                String array[] = vuelo.split(",");
                
                String lineanueva = array[0]+","+array[1]+","+array[2]+","+array[3]+","+array[4]+","+array[5]+","+array[6]+","+array[7]+","+numeroAN+","+array[9]+","+array[10];
                
                ManejoArchivos.cambiarLinea("vuelos.txt",vuelo,lineanueva);
                vuelo=lineanueva;

                
            }
            
            
            String apuertosalida = vuelo.split(",")[5];
            String apuertollegada = vuelo.split(",")[6];
            Boleto boleto= new Boleto(cedula,nombre,apellido,this.aerolinea,apuertosalida,apuertollegada,asiento);
            boletos.add(boleto);
            
            System.out.println("");
            System.out.println("Boleto a nombre de: "+nombre+" "+apellido+" precio: "+valorUsuario);
            valorTotal=valorTotal+valorUsuario;
        }
        Reserva reserva = reservaVuelo(vuelo,boletos,valorTotal);
        escribirReserva(reserva);
        System.out.println("Reserva exitosa");
        System.out.println("Ha comprado "+boletos.size()+" boleto(s), valor total:"+valorTotal);
        System.out.println("");
        
        Scanner o= new Scanner(System.in);
        System.out.println("1. Menu Principal.");
        System.out.println("2. Salir.");
        System.out.print("----------------->Seleccion:");
        int opcion = o.nextInt();
        if (opcion==1){
            MenuCajero.menuCajero(cajero);
        }else{
            System.out.println("\n Gracias Por Usar Nuestros Servicios");
            System.exit(0);
        }
    }
    

    public static int mostrarViajes(ArrayList<String> lugares){
        System.out.println("");
        System.out.println("+-------------------------------------------------+");
        System.out.println("|             DESTINOS DISPONIBLES                |");
        System.out.println("+-------------------------------------------------+");
        int cont=0;
        for (int i=0;i<lugares.size();i++){
            System.out.println((i+1)+".- "+lugares.get(i));
            cont++;
        }
        return cont;
    }        
    
     
    public double obtenerValor(String fecha, String clase){
        double precio;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        int edad = periodo.getYears();
        if(clase.equals("E")){
            precio = 300;
            if (edad < 2){
                precio = 0.5 * precio;
                
            }
            precio = precio + ( 0.12 * precio);
            return precio;
        }else{
            precio = 800;
            if (edad < 2){
                precio = 0.5 * precio;
                
            }
            precio = precio + ( 0.12 * precio);
            return precio;
        }
    }
    
    public void escribirReserva(Reserva reserva){
        ManejoArchivos.Escribir("Ventas.txt", reserva.toString());
    }
    
    public Reserva reservaVuelo(String vuelo,ArrayList<Boleto> boletos,double valor){
        LocalDate ahora = LocalDate.now();
        String fecha = ahora.getDayOfMonth()+"/"+ahora.getMonthValue()+"/"+ahora.getYear();
        String numVuelo = vuelo.split(",")[1];
        return new Reserva(fecha,numVuelo,super.identificador,valor,boletos);
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getCedula() {
        return super.persona.cedula;
    }

    public void setCedula(String cedula) {
        super.persona.setCedula(cedula);
    }

    public String getNombre() {
        return super.persona.nombre;
    }

    public void setNombre(String nombre) {
        super.persona.nombre = nombre;
    }

    public String getApellido() {
        return super.persona.apellido;
    }

    public void setApellido(String apellido) {
        super.persona.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return super.persona.email;
    }

    public void setEmail(String email) {
        super.persona.email = email;
    }

    public String getDepartamento() {
        return super.persona.departamento;
    }

    public void setDepartamento(String departamento) {
        super.persona.departamento = departamento;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    
    
}