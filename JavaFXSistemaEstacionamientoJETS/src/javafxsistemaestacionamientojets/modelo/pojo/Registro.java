
package javafxsistemaestacionamientojets.modelo.pojo;

import java.sql.Date;
import java.sql.Time;


public class Registro {
    private int idRegistro;
    private Time horaEntrada;
    private Time horaSalida;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int tiempoTranscurrido;
    private double pagoTotal;
    
    private int idTipoVehiculo;
    private String tipoVehiculo;
    
    private int idEstatusTarifa;
    private String estatusTarifa;
    
    private int idMetodoPago;
    private String tipoPago;
    
    private int idTarjeta;
    private String codigoTarjeta;
    
    private int idUsuario;
    private int nombreCompleto;
    
    private int numeroCajon;

    public Registro() {
    }

    public int getNumeroCajon() {
        return numeroCajon;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public Time getHoraSalida() {
        return horaSalida;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public int getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public double getPagoTotal() {
        return pagoTotal;
    }

    public int getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public int getIdEstatusTarifa() {
        return idEstatusTarifa;
    }

    public String getEstatusTarifa() {
        return estatusTarifa;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public String getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getNombreCompleto() {
        return nombreCompleto;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public void setHoraSalida(Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public void setTiempoTranscurrido(int tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public void setIdTipoVehiculo(int idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setIdEstatusTarifa(int idEstatusTarifa) {
        this.idEstatusTarifa = idEstatusTarifa;
    }

    public void setEstatusTarifa(String estatusTarifa) {
        this.estatusTarifa = estatusTarifa;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public void setCodigoTarjeta(String codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreCompleto(int nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setNumeroCajon(int numeroCajon) {
        this.numeroCajon = numeroCajon;
    }
    
    
}
