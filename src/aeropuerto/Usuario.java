/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.util.Objects;

/**
 *
 * @author daniel
 */
public class Usuario {

    protected String usuario;
    protected String password;
    protected String identificador;
    protected String aerolinea;
    protected Persona persona;

    public Usuario(String cedula, String nombre, String apellido, String usuario, String password, String email, String departamento, String identificador, String aerolinea) {
        persona = new Persona(cedula, nombre, apellido, email, departamento);
        this.usuario = usuario;
        this.password = password;
        this.identificador = identificador;
        this.aerolinea = aerolinea;
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;

    }

    public Usuario(String usuario, String password, String departamento) {
        this.usuario = usuario;
        this.password = password;
        persona = new Persona(departamento);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String toString() {
        /*
         Uso este formato de salida para que se ajuste al formato del archivo
         <nombre de usuario>,<contrasenia>
         */
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s", persona.getCedula(), persona.getNombre(), persona.getApellido(), usuario, password, persona.getEmail(), persona.getDepartamento(), identificador, aerolinea);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
}
