
package javafxsistemaestacionamientojets.modelo.pojo;

import java.util.ArrayList;


public class TarjetaRespuesta {

    private ArrayList <Tarjeta> tarjetas;
    private int codigoRespuesta;
    
    public TarjetaRespuesta() {
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
}
