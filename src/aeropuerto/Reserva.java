/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.util.ArrayList;
/**
 *
 * @author daniel
 */
public class Reserva {
    static public int codigoReserva = 0000;
    public int codigo;
    public String fechaCreacion;
    public String numVuelo;
    public String codEmpleado;
    public double valorPagar;
    ArrayList<Boleto> boletos = new ArrayList<Boleto>();
    
    public Reserva(String fechaCreacion, String numVuelo, String codEmpleado, double valorPagar, ArrayList<Boleto> boletos){
        codigo=codigoReserva;
        this.fechaCreacion=fechaCreacion;
        this.numVuelo=numVuelo;
        this.codEmpleado=codEmpleado;
        this.valorPagar=valorPagar;
        this.boletos= boletos;
        codigoReserva++;
    }
    
    public String toString(){
        Boleto boleto = boletos.get(0);
        return String.format("%s,%s,%s,%s,%s,%s",codigo,numVuelo,boletos.size(),boleto.aereopuertopartida,boleto.aereopuertosalida,valorPagar);
    }
    
}