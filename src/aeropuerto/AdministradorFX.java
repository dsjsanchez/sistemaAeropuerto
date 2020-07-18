/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

//import static aeropuerto.Aeropuerto.path;
//import static aeropuerto.Aeropuerto.sc;
//import static aeropuerto.Aeropuerto.usuarios;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author daniel
 */
public class AdministradorFX  {
    //Admin
    
    static VBox contenedor = new VBox(10);
    static Button crear_usuario = new Button("Crear Nuevo Usuario"),Salir_usuario = new Button("Cerrar Sesion"),crear_aerolinea = new Button("Crear Nueva Aerolinea");
    
    //user
    static TextField TFcedula =new TextField(),TFnombre = new TextField(),TFapellido = new TextField(),TFusuario1=new TextField(),TFpassword = new TextField(),TFemail =new TextField(),TFdepartamento =new TextField(),TFidentificador = new TextField(),TFaerolinea = new TextField();
    static Label Lcedula =new Label("Cedula"),Lnombre = new Label("Nombre"),Lapellido = new Label("Apellido"),Lusuario1=new Label("Usuario"),Lpassword = new Label("Contraseña"),Lemail =new Label("email"),Ldepartamento =new Label("Departamento"),Lidentificador = new Label("Identificador"),Laerolinea = new Label("Aerolinea");
    static HBox fila1=new HBox(10),fila2=new HBox(10),fila3=new HBox(10),fila4=new HBox(10),fila5=new HBox(10),fila6=new HBox(10),fila7=new HBox(10),fila8=new HBox(10),fila9=new HBox(10),fila10=new HBox(10);
    static Button crear = new Button("Crear"),cancelar = new Button("Cancelar"),crear_aero = new Button("Crear");
    //Aerolinea
    static TextField TFnombre_aero =new TextField();
    static Label Lnombre_aero =new Label("Nombre");
    //
     private static ArrayList<Usuario> usuarios = new ArrayList<>();
     private static ArrayList<Usuario> identificadores = new ArrayList<>();
     private static Usuario new_user;
     private static Usuario administrador;
     
      private static Aerolinea new_aero;
     static ArrayList<HBox> arrayfilas= new ArrayList();
     static ArrayList<TextField> arraytfields= new ArrayList();
   
    public AdministradorFX(Usuario administrador){
        this.administrador=administrador;
       
        arrayfilas.add(fila1);arrayfilas.add(fila2);arrayfilas.add(fila3);arrayfilas.add(fila4);arrayfilas.add(fila5);arrayfilas.add(fila6);arrayfilas.add(fila7);arrayfilas.add(fila8);arrayfilas.add(fila9);
        arraytfields.add(TFnombre);arraytfields.add(TFaerolinea);arraytfields.add(TFapellido);arraytfields.add(TFcedula);arraytfields.add(TFdepartamento);arraytfields.add(TFemail);arraytfields.add(TFidentificador);arraytfields.add(TFnombre_aero);arraytfields.add(TFpassword);arraytfields.add(TFusuario1);
        Build();
        crear_usuario.setOnAction(e->{
            for (HBox fila : arrayfilas) {
                fila.getChildren().clear();
            }fila10.getChildren().clear();
            Build_create_user();
        });
        crear.setOnAction(e->{
        enterUsuario();
        });
        crear_aerolinea.setOnAction(e->{
            for (HBox fila : arrayfilas) {
                fila.getChildren().clear();
            }
            Build_create_Airline();
        });
        crear_aero.setOnAction(e->{
            enterAerolinea();
        });
        cancelar.setOnAction(e->{
        Build();
        });
        Salir_usuario.setOnAction(e->{
            
            Cargar_Scene((new Login().getroot()),400,200);
        });
    }

     
    private  static void enterUsuario() {
        /*
        Metodo de apoyo usado para el ingreso del usuario en instanciacion
        de un objeto segun lo ingresado*/
        Boolean flag1=false,flag2=false,flag3=false,flag4=false,flag5=true;;
        String cedula =TFcedula.getText(),nombre = TFnombre.getText(),apellido = TFapellido.getText(),usuario1=TFusuario1.getText(),password = TFpassword.getText(),email =TFemail.getText(),departamento =TFdepartamento.getText(),identificador = TFidentificador.getText(),aerolinea = TFaerolinea.getText();
        if (validarCedula(cedula)){
            flag1=true;
        }
        else{
                showAlert_Error("La Cedula ingresada es incorrecta.");
          
        }
        if(!existeUsername(usuario1)){
                flag2=true;
        }else{
                showAlert_Error("El Usuario ingresado ya existe.");
            }
        if (password.length()>=8 && ValidarContraseniaMayuscula(password) && ValidarContraseniaNumero(password)){
        password=encriptarClave(password);
            System.out.println(password);
            flag3=true;
        
        }
        else{
                showAlert_Error("La Contraseña ingresada es muy debil.\nDebe tener almenos 8 Digitos, 1 Mayuscula, 1 Numero.");
        }
        
        
        if (departamento.equals("sistemas")||departamento.equals("comercialc")||departamento.equals("comercialp")){
            flag4=true;
        }
        else{
            showAlert_Error("El Departamento ingresado no existe.");
                }
      
        
        if(!existeIdent(identificador)){
            flag5=true;
        }else{
             showAlert_Error("El Identificador ingresado ya existe.");
        }
        if(flag1 && flag2 && flag3 && flag4 && flag5){
         new_user= new Usuario(cedula, nombre,apellido,usuario1,password, email,departamento,identificador, aerolinea);
         usuarios.add(new_user);
         Login.usuarios.add(new_user);
            showAlert_Added("Usuario agregado existosamente");
            for (TextField t : arraytfields) {
                t.clear();   
            }
            
            
        //Agregar usuario a la lista
        }else{showAlert_Error("El usuario no fue agregado.");}
        
        
        
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
    public static boolean existeIdent(String Ident){
    //cargarUsuarios();
    //System.out.println(usuarios);       
    for(Usuario u:usuarios){
        if(u.getIdentificador().equals(Ident)){
            //System.out.println("Usuario con este idntificador encontrado");
            return true;
        }

    }
    //System.out.println("USUARIO NO ENCONTRADO");
    return false;
    }
    public static boolean existeUsername(String username){
    //cargarUsuarios();
    //System.out.println(usuarios);       
    for(Usuario u:usuarios){
        if(u.getUsuario().equals(username)){
            //System.out.println("Usuario con este username encontrado");
            return true;
        }

    }
    //System.out.println("USUARIO NO ENCONTRADO");
    return false;
    }
    private static void enterAerolinea() {
    /*
    Metodo de apoyo usado para el ingreso del usuario en instanciacion
    de un objeto segun lo ingresado
    */
        if (true) {
            new_aero= new Aerolinea(TFaerolinea.getText());

            //Agregar aerolinea a la lista
            Login.aerolineas.add(new_aero);
            showAlert_Added("Aerolinea agregada existosamente");
        }
    
    }
   

    private static void showAlert_Error(String text) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(text);
    alert.showAndWait();
    }
    private static void showAlert_Added(String text) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Agregado");
    alert.setHeaderText(null);
    alert.setContentText(text);
    alert.showAndWait();
    }
    private static void Build_create_user(){
    contenedor.getChildren().clear();
Lcedula.setAlignment(Pos.CENTER_LEFT);Lcedula.setMinWidth(100);
Lnombre.setAlignment(Pos.CENTER_LEFT);Lnombre.setMinWidth(100);
Lapellido.setAlignment(Pos.CENTER_LEFT);Lapellido.setMinWidth(100);
Lusuario1.setAlignment(Pos.CENTER_LEFT);Lusuario1.setMinWidth(100);
Lpassword.setAlignment(Pos.CENTER_LEFT);Lpassword.setMinWidth(100);
Lemail.setAlignment(Pos.CENTER_LEFT);Lemail.setMinWidth(100);
Ldepartamento.setAlignment(Pos.CENTER_LEFT);Ldepartamento.setMinWidth(100);
Lidentificador.setAlignment(Pos.CENTER_LEFT);Lidentificador.setMinWidth(100);
Laerolinea.setAlignment(Pos.CENTER_LEFT);Laerolinea.setMinWidth(100);
    fila1.getChildren().addAll(Lcedula,TFcedula);
    fila2.getChildren().addAll(Lnombre,TFnombre);
    fila3.getChildren().addAll(Lapellido,TFapellido);
    fila4.getChildren().addAll(Lusuario1,TFusuario1);
    fila5.getChildren().addAll(Lpassword,TFpassword);
    fila6.getChildren().addAll(Lemail,TFemail);
    fila7.getChildren().addAll(Ldepartamento,TFdepartamento);
    fila8.getChildren().addAll(Lidentificador,TFidentificador);   
    fila9.getChildren().addAll(Laerolinea,TFaerolinea);
    fila10.getChildren().addAll(cancelar,crear);

    contenedor.getChildren().addAll(fila1,fila2,fila3,fila4,fila5,fila6,fila7,fila8,fila9,fila10);
    contenedor.setAlignment(Pos.CENTER);
    contenedor.setPadding(new Insets(20, 50, 20, 0));
    }
    private static void Build_create_Airline(){
        contenedor.getChildren().clear();
        
        fila1.getChildren().addAll(Lnombre_aero,TFnombre_aero);
        fila2.getChildren().addAll(cancelar,crear_aero);
        contenedor.getChildren().addAll(fila1,fila2);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(20, 100, 20, 0));
        }
    private static void Build(){
            //formateo general de los hbox
            for (TextField t :arraytfields) {
                t.clear();
        }
            for (HBox fila : arrayfilas) {
                fila.setAlignment(Pos.CENTER_RIGHT);
            }fila10.setAlignment(Pos.CENTER);
            Label head= new Label("Bienvenido: "+administrador.getNombre()+" "+administrador.getApellido());
            //fondo
            BackgroundImage myBI= new BackgroundImage(new Image("/Recursos/fondo.jpg",680,400,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            //
            contenedor.setBackground(new Background(myBI));
            contenedor.getChildren().clear();
            crear_usuario.setMinSize(200, 40);crear_aerolinea.setMinSize(200, 40);Salir_usuario.setMinSize(200, 40);
            contenedor.getChildren().addAll(head,crear_usuario,crear_aerolinea,Salir_usuario);
            contenedor.setAlignment(Pos.CENTER);
            contenedor.setPadding(Insets.EMPTY);
        }
    public Parent getroot(){
        return contenedor;
    }
    public void Cargar_Scene(Parent r,int width,int heigth)  {
        
         Stage st= (Stage)contenedor.getScene().getWindow();
         st.setTitle("Sistema de administración del nuevo aeropuerto de Guayaquil");
         st.getScene().setRoot(r);
         st.setWidth(width);
         st.setHeight(heigth);
     }
}
