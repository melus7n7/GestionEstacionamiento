
package javafxsistemaestacionamientojets.modelo.pojo;

import java.sql.Time;


public class Usuario {
   private int idUsuario;
   private String nombre;
   private String apellidoPaterno;
   private String apellidoMaterno;
   private String usuario;
   private String contrasena;
   private int idTipoUsuario;
   private Time horarioEntrada;
   private Time horarioSalida;
   private int codigoRespuesta;

    public Usuario() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public Time getHorarioEntrada() {
        return horarioEntrada;
    }

    public Time getHorarioSalida() {
        return horarioSalida;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setHorarioEntrada(Time horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public void setHorarioSalida(Time horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
   
   
}
