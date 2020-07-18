/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

/**
 *
 * @author daniel
 */
public class Boleto {
    public String cedula;
    public String nombre;
    public String apellido;
    public Aerolinea aerolinea;
    public String aereopuertopartida;
    public String aereopuertosalida;
    public String asiento;
    
    public Boleto(String cedula,String nombre,String apellido, Aerolinea aerolinea,String aereopuertopartida ,String aereopuertosalida,String asiento){
        
        this.nombre= nombre;
        this.apellido=apellido;
        this.cedula = cedula;      
        this.aerolinea=aerolinea;
        this.aereopuertopartida=aereopuertopartida;
        this.aereopuertosalida=aereopuertosalida;
        this.asiento=asiento;
                
    }
}