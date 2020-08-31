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
public class Planificador extends Usuario {
    

    public Planificador(String cedula, String nombre, String apellido, String usuario, String password, String email, String departamento, String identificador, String planificador,String aerolinea) {
        super(cedula, nombre, apellido, usuario, password, email, departamento, identificador,aerolinea);
        ;
    }

    public Planificador(String usuario, String password) {
        super(usuario, password);
    }

    public Planificador(String usuario, String password, String departamento) {
        super(usuario, password, departamento);
    }
    
    

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getCedula() {
        return super.persona.cedula;
    }

    public void setCedula(String cedula) {
        super.persona.cedula = cedula;
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
