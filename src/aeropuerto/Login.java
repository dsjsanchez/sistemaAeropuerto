/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class Login {

    /**
     */
   
     public static ArrayList<Usuario> usuarios = new ArrayList();
     public static ArrayList<Aerolinea> aerolineas = new ArrayList();
     public static ArrayList <Avion> aviones= new ArrayList();
     public static ArrayList <Vuelo> vuelos= new ArrayList();
     public static ArrayList <Reserva> reservas= new ArrayList();
     
     //JAVAFX
     VBox root=new VBox(10);
     HBox userbox=new HBox(10);
     HBox passbox=new HBox(10);
    TextField usertextf=new TextField();
    TextField passtextf=new PasswordField();
   
    Button Ingresar= new Button("Conectar");
    Label userlabel= new Label("User:");
    Label passlabel= new Label("Password:");
    

     
     
     public Login() {
         usertextf.setText("jsojos");
         passtextf.setText("Sojos1234");
         Build();
        
        //TAME 
        AddData();
        //vuelos=De_Serilizar("Vuelos");
        reservas=De_Serilizar("Reservas");
        
        usuarios=De_Serilizar("Usuarios");
        aviones=De_Serilizar("Aviones");
        aerolineas=De_Serilizar("Aerolineas");
        System.out.println(usuarios);
        //System.out.println(reservas);
        Ingresar.setOnMouseClicked(e->{
            Usuario enteredUser = ingresarUsuario();
            if (!usuarios.contains(enteredUser)) {
                showAlert();
            }
            else{
                enteredUser=usuarios.get(usuarios.indexOf(enteredUser));
                String c=enteredUser.getDepartamento();
                System.out.println(c);
                switch (c){
                    case "sistemas":
                        //conectar con Administrador y cambiar scene
                        
                        Cargar_Scene((new AdministradorFX(enteredUser).getroot()), "Sistemas",400,400);
                        break;

                   case "comercialp":
                        //PlanificadorFX.PlanificadorFX(new Planificador(enteredUser.getUsuario(),enteredUser.getPassword()));
                        //Cargar_Scene((new PlanificadorFX(enteredUser).getroot()), "Planificador",680,400);
                        break;
                    case "comercialc":
                        //MenuCajero.menuCajero(new Cajero(enteredUser.getUsuario(),enteredUser.getPassword()));
                        //Cargar_Scene(new CajeroFX(enteredUser).getroot(), "Cajero", 680, 400);
                        break;

                }
            }
        });
        
        
     }
      private Usuario ingresarUsuario() {
        /*
        Metodo de apoyo usado para el ingreso del usuario en instanciacion
        de un objeto segun lo ingresado
        */
        
        String password =desencriptarUsuario(passtextf.getText());
        
        return new Usuario(usertextf.getText(),password);
    } 
     public static String desencriptarUsuario(String password){
         char array[]= password.toCharArray();
         
         for(int i = 0; i<array.length;i++){
             array[i]=(char)(array[i]+(char)5);
             
         }
         password=String.valueOf(array);
         
         return password;
     }
       
    public static String dEnc(String password){
         char array[]= password.toCharArray();
         
         for(int i = 0; i<array.length;i++){
             array[i]=(char)(array[i]-(char)5);
             
         }
         password=String.valueOf(array);
         
         return password;
     }
    public Parent getroot(){
        return root;
    }
    public void Build(){
        BackgroundImage myBI= new BackgroundImage(new Image("/Recursos/fondo.jpg",680,400,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
                
        userlabel.setMinSize(70, 20);
        passlabel.setMinSize(70, 20);
        userbox.getChildren().addAll(userlabel,usertextf);
        passbox.getChildren().addAll(passlabel,passtextf);
        root.getChildren().addAll(userbox,passbox,Ingresar);
        userbox.setAlignment(Pos.CENTER);
        passbox.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setMinSize(400, 200);
    }
     private void showAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText("El usuario o contraseña son incorrectos");
        alert.showAndWait();
    }
     public void Cargar_Scene(Parent r,String titulo,int width,int heigth)  {
        
         Stage st= (Stage)root.getScene().getWindow();
         st.setTitle("Departamento: "+titulo);
         st.getScene().setRoot(r);
         st.setWidth(width);
         st.setHeight(heigth);
     }
     public void AddData(){
         aviones.clear();
        Avion a=new Avion("13452", "Samsung", "AB32", 20, 40, "1200", "TAME");
        Avion a1=new Avion("14356", "APPLE", "AB31", 20, 40, "1200", "TAME");
        Avion a2=new Avion("15462", "NASA", "AB35", 20, 40, "1200", "AVIANCA");
        Avion a3=new Avion("17854", "IBM", "AB34", 20, 40, "1200", "AMERICAN");
        
        aviones.add(a);aviones.add(a1);aviones.add(a2);aviones.add(a3);
        vuelos.clear();
        vuelos.add(new Vuelo(a,"1",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","UIO",a.getCapacidadEconomico(),a.getCapacidadNegocio(),"1","9"));
        vuelos.add(new Vuelo(a1,"2",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","JOO",a1.getCapacidadEconomico(),a1.getCapacidadNegocio(),"2","8"));
        vuelos.add(new Vuelo(a2,"3",LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","PEO",a2.getCapacidadEconomico(),a2.getCapacidadNegocio(),"3","7"));
        vuelos.add(new Vuelo(a3,"4",LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","UIO",a3.getCapacidadEconomico(),a3.getCapacidadNegocio(),"4","6"));
        vuelos.add(new Vuelo(a,"5",LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","JOO",a.getCapacidadEconomico(),a.getCapacidadNegocio(),"5","5"));
        vuelos.add(new Vuelo(a1,"6",LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","PEO",a1.getCapacidadEconomico(),a1.getCapacidadNegocio(),"6","4"));
        vuelos.add(new Vuelo(a2,"7",LocalDateTime.now().minusMinutes(60).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","UIO",a2.getCapacidadEconomico(),a2.getCapacidadNegocio(),"7","3"));
        vuelos.add(new Vuelo(a3,"8",LocalDateTime.now().minusMinutes(60).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","JOO",a3.getCapacidadEconomico(),a3.getCapacidadNegocio(),"8","2"));
        vuelos.add(new Vuelo(a,"9",LocalDateTime.now().minusMinutes(60).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),LocalDateTime.now().plusMinutes(120).format(DateTimeFormatter.ofPattern("yyyy/MM/dd-kk:mm")),"GYE","PEO",a.getCapacidadEconomico(),a.getCapacidadNegocio(),"9","1"));
        
     }

    
public static void Serializar(ArrayList j,String name) throws IOException{
        ObjectOutputStream oos;
        try {
            oos= new ObjectOutputStream(new FileOutputStream("src/Recursos/"+name+".obj"));
            oos.writeObject(j);
            oos.close();
        } catch (FileNotFoundException ex) {
            
        }
        
    }
private ArrayList De_Serilizar(String name) {
        ArrayList j=null;
        ObjectInputStream ois;
        try {
            ois= new ObjectInputStream(new FileInputStream("src/Recursos/"+name+".obj"));
            j= (ArrayList)ois.readObject();
        } catch (Exception e) { 
        
        
        }
        return j;
    }
public static void GuardarArrays(){
    try {
         Serializar(vuelos, "Vuelos");
             
        Serializar(usuarios, "Usuarios");      
 
        Serializar(aerolineas, "Aerolineas");
        
        Serializar(aviones, "Aviones");
        
        Serializar(reservas, "Reservas");

        
    } catch (IOException e) {
    }
    
   
}

}


