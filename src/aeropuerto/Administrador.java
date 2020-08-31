/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import static aeropuerto.Aeropuerto.path;
import static aeropuerto.Aeropuerto.sc;
import static aeropuerto.Aeropuerto.usuarios;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class Administrador extends Usuario  {

     private static ArrayList<Usuario> usuarios = new ArrayList<>();
     private static ArrayList<Usuario> identificadores = new ArrayList<>();
   
   
    Administrador(String cedula,String nombre,String apellido,String usuario,String password,String email,String departamento,String identificador, String aerolinea){
    super(cedula,nombre,apellido,usuario,password,email,departamento,identificador,aerolinea);
    //constructor de aeropuerto
    
    }
    @Override
    public String toString() {
        /*
         Uso este formato de salida para que se ajuste al formato del archivo
         <nombre de usuario>,<contrasenia>
         */
        return String.format("%s,%s", super.persona.getCedula(),super.persona.getNombre(),super.persona.getApellido(),usuario,password,super.persona.getEmail(),super.persona.getDepartamento(), identificador);
    }
    
   
       
    
 

     static void agregarMasUsuarios() {
        do {
            System.out.println("--- AGREGAR USUARIO ---");
            Usuario newUsuario = enterUsuario();
           System.out.println(newUsuario.toString());
            ManejoArchivos.Escribir("users.txt", newUsuario.toString());
           // Aeropuerto.usuarios.add(newUsuario);
            //guardarUsuarios();
        } while (agregarMas());
    }

    private  static Usuario enterUsuario() {
        /*
        Metodo de apoyo usado para el ingreso del usuario en instanciacion
        de un objeto segun lo ingresado*/
        System.out.print("Cedula: ");
        String cedula = Aeropuerto.sc.nextLine();
        boolean m=true;
        while(m==true){
        if (validarCedula(cedula)){
        
        m=false;
        }
        else{
                System.out.println("cedula invalidad invalida");
                System.out.println("ingrese una nueva cedula");
                
                cedula = Aeropuerto.sc.nextLine();
                m=true;
                }
        }
       
        System.out.print("Nombre: ");
        String nombre = Aeropuerto.sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = Aeropuerto.sc.nextLine();
        System.out.print("Usuario: ");
        String usuario1=sc.nextLine();
        boolean po=true;
        while(po){
            if(!existeUsername(usuario1)){
                System.out.println("Este usuario puede crearse");
                po=false;
            }else{
                System.out.println("Usuario ya existe");
                System.out.println("Ingrese otro usuario:");
                usuario1=sc.nextLine();
            }
        }
        
        System.out.print("Password: ");
        String password = Aeropuerto.sc.nextLine();
        
        boolean n=true;
        
        while(n==true){
        if (password.length()>=8 && ValidarContraseniaMayuscula(password) && ValidarContraseniaNumero(password)){
        password=encriptarClave(password);
            System.out.println(password);
        n=false;
        }
        else{
                System.out.println("contrasenia invalida");
                System.out.println("ingrese una nueva contrasenia");
                System.out.print("Password: ");
                password = Aeropuerto.sc.nextLine();
                n=true;
                }
        }
       
    

        System.out.print("Email ");
        String email = Aeropuerto.sc.nextLine();
        System.out.print("Departamento: ");
        String departamento = Aeropuerto.sc.nextLine();
        boolean f=true;
        
        while(f==true){
        if (departamento.equals("sistemas")||departamento.equals("comercialc")||departamento.equals("comercialp")){

         f=false;
        }
        else{
                System.out.println("departamento invalido");
                System.out.println("ingrese un nuevo departamento");
                System.out.print("departamento: ");
                departamento = Aeropuerto.sc.nextLine();
                f=true;
                }
        }
        
        System.out.print("Identificador: ");
        String identificador = Aeropuerto.sc.nextLine();
         boolean xo=true;
        while(xo){
            if(!existeIdent(identificador)){
                System.out.println("Este identificador puede crearse");
                xo=false;
            }else{
                System.out.println("identificador ya existe");
                System.out.println("Ingrese otro identificador:");
                identificador=sc.nextLine();
            }
        }
        System.out.print("Aerolinea: ");
        String aerolinea = Aeropuerto.sc.nextLine();
       
        
        boolean g=true;
        
        while(g==true){
            for ( Aerolinea q  : Aeropuerto.aerolineas){
                System.out.println(q);
            
        if (q.toString().equals(aerolinea)){
            System.out.println("si esta en la lista");
        g=false;
        break;
        }
          
}
            if (g==true){
              System.out.println("aereolinea invalida");
                System.out.println("ingrese una nueva aereolinea");
                System.out.print("aereolinea: ");
                aerolinea = Aeropuerto.sc.nextLine();
                g=true;    
} 
        }
      

        
        return new Usuario(cedula, nombre,apellido,usuario1,password, email,departamento,identificador, aerolinea);
    }
    public static boolean validarCedula(String cedula){
        int total=0;
        int tamanoLongitudCedula =10;
        int[] coeficientes={2,1,2,1,2,1,2,1,2};
        int numeroProvincias=24;
        int tercerDigito=6;
        if(cedula.matches("[0-9]*") && cedula.length()==tamanoLongitudCedula){
            int provincias= Integer.parseInt(cedula.charAt(0)+""+cedula.charAt(1));
            int digitoTres=Integer.parseInt(cedula.charAt(2)+"");
            if(provincias>0 && provincias<= numeroProvincias && digitoTres<=6){
                int digitoVerificadoRecibido=Integer.parseInt(cedula.charAt(9)+"");
                for(int i=0;i<coeficientes.length;i++){
                    int valor= Integer.parseInt(coeficientes[i]+"")*Integer.parseInt(cedula.charAt(i)+"");
                    total = valor>= 10? total + (valor -9):total + valor;
                }
                int digitoVerificadoObtenido = total>= 10 ? (total%10) != 0 ? 10 -(total%10):(total%10):total;
                if (digitoVerificadoObtenido==digitoVerificadoRecibido){
                    return true;
                }
            }
            return false;
        }
        /*
         if(cedula.charAt(0)>='0' && cedula.charAt(0)<='2' && cedula.charAt(1)>='0' && cedula.charAt(1)<='4' && cedula.charAt(2)>='0' && cedula.charAt(2)<='6' ) {
             for (int i=0; i<cedula.length()-1;i++){
                 if(cedula.charAt(i)%2==0){
                     int suma=cedula.charAt(i)*2;
                     if (suma>=10){
                         total = total+suma;
                     }
                     total = total+suma;
                 }
                 else{
                     total=total+cedula.charAt(i);
                 }
                 
             }
   }
          */                  

        
        
        return false;
    }
        
   private static boolean ValidarContraseniaMayuscula(String password){
         for (int i=0; i<password.length();i++){
         
              char c;
              c = password.charAt(i);
              if(Character.isUpperCase(c)){
                  return true;
              }
              
              
    }
        return false;
   }
   
   private static boolean ValidarContraseniaNumero(String password){
       for (int y=0; y<password.length();y++){
           char z;
              z = password.charAt(y);
                       if(z>='0' && z<='9'){
                           
                           return true;
       
   }
       }
       return false;
   }
   
   public static String encriptarClave(String password){
        char array[]= password.toCharArray();
                           for(int i=0;i<array.length;i++){
                               
                               array[i]=(char)(array[i]+(char)5);
                           }
                           password=String.valueOf(array);
                           return password;        
   }
        
   
    private static boolean agregarMas() {
        ArrayList<String> options = new ArrayList<>();
        options.add("S");
        options.add("N");
        options.add("Y");
        System.out.print("Ingresar otro? (S/N): ");
        String response = sc.nextLine();
        response = response.toUpperCase();
        while (!options.contains(response)) {
            System.err.println("Opcion no valida");
            System.out.print("Ingresar otro? (S/N): ");
            response = sc.nextLine();
            response = response.toUpperCase();
        }
        return (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("S"));
    }

   /* private static void guardarUsuarios() {
        /*
        Metodo para guardar los usuarios en el archivo
        Primero se debe generar el texto que se almacenara en el archivo
        por eso el ArrayList<String> luego recorremos los usuarios almacenados
        en memoria y llenamos el arraylist de Strings con la representacion de
        string de los usuarios para que luego sean guardados en el archivo
        users.txt
        
        try {
            ArrayList<String> usersToString = new ArrayList<>();
            for (Usuario user : usuarios) {
                usersToString.add(user.toString());
            }
            Files.write(path, usersToString);
        } catch (IOException ex) {
            System.err.println("Exception IO");
        }
}
*/
   public static void crearAerolinea(){
       do {
            System.out.println("--- AGREGAR AEREOLINEA ---");
            Aerolinea newaerolinea = enterAerolinea();
            ManejoArchivos.Escribir("aerolineas.txt", newaerolinea.toString());
           // Aeropuerto.aerolineas.add(newaerolinea);
            //guardarAerolineas();
        } while (agregarMas());
       
       
   }
   
   private static Aerolinea enterAerolinea() {
        /*
        Metodo de apoyo usado para el ingreso del usuario en instanciacion
        de un objeto segun lo ingresado
        */
        System.out.print("Nombre de aerolinea: ");
        String nombre = sc.nextLine();
        return new Aerolinea(nombre);
    }
  /*  private static void guardarAerolineas() {
        /*
        Metodo para guardar las aerolineas
        *
        try {
            ArrayList<String> aerolineasToString = new ArrayList<>();
            for (Aerolinea aerolinea : Aeropuerto.aerolineas) {
                aerolineasToString.add(aerolinea.toString());
            }
            Files.write(path, aerolineasToString);
        } catch (IOException ex) {
            System.err.println("Exception IO");
        }
}
   */
   
    public static boolean existeIdent(String Ident){
        cargarUsuarios();
        System.out.println(usuarios);       
        for(Usuario u:usuarios){
            if(u.getIdentificador().equals(Ident)){
                System.out.println("Usuario con este idntificador encontrado");
                return true;
            }
            
        }
        System.out.println("USUARIO NO ENCONTRADO");
        return false;
   }
   
      public static boolean existeUsername(String username){
        cargarUsuarios();
        System.out.println(usuarios);       
        for(Usuario u:usuarios){
            if(u.getUsuario().equals(username)){
                System.out.println("Usuario con este username encontrado");
                return true;
            }
            
        }
        System.out.println("USUARIO NO ENCONTRADO");
        return false;
   }
        private static void cargarUsuarios() {
        /*
        Metodo que carga los usuarios del archo users.txt en la lista de
        objetos User
        */
        
            ArrayList<String> lines = ManejoArchivos.Leer("users.txt"); //Devuelve el 
//            archivo como una lista de Strings donde cada item es una 
//            linea del archivo, y cada linea representa un objeto
            for (String line : lines) {
                System.out.println(line);
//                Se realiza un split por coma(,) para separar las propiedades
                String[] fields = line.split(",");
//                Se instancia un nuevo User con las propiedades
                    //Usuario(String cedula, String nombre, String apellido, String usuario, String password, String email, String departamento, String identificador,String aerolinea)
                Usuario user = new Usuario( fields[0],fields[1],fields[2],fields[3],fields[4],fields[5],fields[6],fields[7],fields[8]);
//                Se lo almacena en la lista de usuarios
                usuarios.add(user);
                
            }
        
    }       
        }