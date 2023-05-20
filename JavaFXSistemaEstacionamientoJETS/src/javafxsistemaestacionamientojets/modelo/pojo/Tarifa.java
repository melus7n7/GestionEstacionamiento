/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.pojo;

/**
 *
 * @author monti
 */
public class Tarifa {
    private int idTarifa;
    private String titulo;
    private double precio;
    private String descripcion;
    private int idTipoTarifa;

    public Tarifa() {
    }

    public Tarifa(int idTarifa, String titulo, double precio, String descripcion, int idTipoTarifa) {
        this.idTarifa = idTarifa;
        this.titulo = titulo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idTipoTarifa = idTipoTarifa;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoTarifa() {
        return idTipoTarifa;
    }

    public void setIdTipoTarifa(int idTipoTarifa) {
        this.idTipoTarifa = idTipoTarifa;
    }
    
    
    
}

