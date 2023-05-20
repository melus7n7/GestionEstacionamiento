
package javafxsistemaestacionamientojets.modelo.pojo;

import java.util.ArrayList;

public class NivelRespuesta {
    private ArrayList<Nivel> niveles;
    private int codigoRespuesta;

    public ArrayList<Nivel> getNiveles() {
        return niveles;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setNiveles(ArrayList<Nivel> niveles) {
        this.niveles = niveles;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
}
