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
public class Vuelo {
    private Avion avion;
    private String codigoVuelo;
    private String fechaEmbar;
    private String fechaSalida;
    private String FechaArrivo;
    private String codigoIataSalida;
    private String codigoIataArrivo;
    private int disponibilidadAsientoEconomico;
    private int disponibilidadAsientoEjecutivo;
    private String puertaEmbarque;
    private String puertaSalida;

    public Vuelo(Avion avion, String codigoVuelo, String fechaEmbar, String fechaSalida, String FechaArrivo, String codigoIataSalida, String codigoIataArrivo, int disponibilidadAsientoEconomico, int disponibilidadAsientoEjecutivo, String puertaEmbarque, String puertaSalida) {
        this.avion = avion;
        this.codigoVuelo = codigoVuelo;
        this.fechaEmbar = fechaEmbar;
        this.fechaSalida = fechaSalida;
        this.FechaArrivo = FechaArrivo;
        this.codigoIataSalida = codigoIataSalida;
        this.codigoIataArrivo = codigoIataArrivo;
        this.disponibilidadAsientoEconomico = disponibilidadAsientoEconomico;
        this.disponibilidadAsientoEjecutivo = disponibilidadAsientoEjecutivo;
        this.puertaEmbarque = puertaEmbarque;
        this.puertaSalida = puertaSalida;
    }
    

    public int getDisponibilidadAsientoEconomico() {
        return disponibilidadAsientoEconomico;
    }

    public void setDisponibilidadAsientoEconomico(int disponibilidadAsientoEconomico) {
        this.disponibilidadAsientoEconomico = disponibilidadAsientoEconomico;
    }

    public int getDisponibilidadAsientoEjecutivo() {
        return disponibilidadAsientoEjecutivo;
    }

    public void setDisponibilidadAsientoEjecutivo(int disponibilidadAsientoEjecutivo) {
        this.disponibilidadAsientoEjecutivo = disponibilidadAsientoEjecutivo;
    }

    public String getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public void setPuertaEmbarque(String puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }

    public String getPuertaSalida() {
        return puertaSalida;
    }

    public void setPuertaSalida(String puertaSalida) {
        this.puertaSalida = puertaSalida;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public String getFechaEmbar() {
        return fechaEmbar;
    }

    public void setFechaEmbar(String fechaEmbar) {
        this.fechaEmbar = fechaEmbar;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaArrivo() {
        return FechaArrivo;
    }

    public void setFechaArrivo(String FechaArrivo) {
        this.FechaArrivo = FechaArrivo;
    }

 

    public String getCodigoIataSalida() {
        return codigoIataSalida;
    }

    public void setCodigoIataSalida(String codigoIataSalida) {
        this.codigoIataSalida = codigoIataSalida;
    }

    public String getCodigoIataArrivo() {
        return codigoIataArrivo;
    }

    public void setCodigoIataArrivo(String codigoIataArrivo) {
        this.codigoIataArrivo = codigoIataArrivo;
    }

    @Override
    public String toString() {
        return avion.getNumSerie() + "," + codigoVuelo + "," + fechaEmbar + "," + fechaSalida + "," + FechaArrivo + "," + codigoIataSalida + "," + codigoIataArrivo + "," + disponibilidadAsientoEconomico + "," +disponibilidadAsientoEjecutivo + "," + puertaEmbarque + "," + puertaSalida;
    }
    
    
}