/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.pojo;

import java.util.ArrayList;

/**
 *
 * @author monti
 */
public class TarifaRespuesta {
    private int codigoRespuesta;
    private ArrayList<Tarifa> tarifas;

    public TarifaRespuesta() {
    }

    public TarifaRespuesta(int codigoRespuesta, ArrayList<Tarifa> tarifas) {
        this.codigoRespuesta = codigoRespuesta;
        this.tarifas = tarifas;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public ArrayList<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(ArrayList<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }
    
    @Override
    public String toString() {
        return "TarifaRespuesta{" + "tarifa=" + tarifas + '}';
    }
    
}
