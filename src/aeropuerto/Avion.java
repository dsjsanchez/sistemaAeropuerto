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
public class Avion {
   
    private String numSerie;
    private String fabricante;
    private String modelo;
    private int capacidadNegocio;
    private int capacidadEconomico;
    private String distMax;
    private String aereolinea;

    public String getAereolinea() {
        return aereolinea;
    }

    public void setAereolinea(String aerolinea) {
        this.aereolinea = aerolinea;
    }

    public Avion(String numSerie, String fabricante, String modelo, int capacidadNegocio, int capacidadEconomico, String distMax, String aereolinea) {
        this.numSerie = numSerie;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.capacidadNegocio = capacidadNegocio;
        this.capacidadEconomico = capacidadEconomico;
        this.distMax = distMax;
        this.aereolinea=aereolinea;
 
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidadNegocio() {
        return capacidadNegocio;
    }

    public void setCapacidadNegocio(int capacidadNegocio) {
        this.capacidadNegocio = capacidadNegocio;
    }

    public int getCapacidadEconomico() {
        return capacidadEconomico;
    }

    public void setCapacidadEconomico(int capacidadEconomico) {
        this.capacidadEconomico = capacidadEconomico;
    }

    public String getDistMax() {
        return distMax;
    }

    public void setDistMax(String distMax) {
        this.distMax = distMax;
    }

    @Override
    public String toString() {
        return numSerie + "," + fabricante + "," + modelo + "," + capacidadNegocio + "," + capacidadEconomico + "," + distMax+","+aereolinea;
    }

 
    
    
}
