
package javafxsistemaestacionamientojets.modelo.pojo;

import java.util.ArrayList;


public class RegistroRespuesta {
    
    private ArrayList<Registro> registros;
    private int codigoRespuesta;

    public RegistroRespuesta() {
    }

    public ArrayList<Registro> getRegistros() {
        return registros;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setRegistros(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
}
