
package javafxsistemaestacionamientojets.modelo.pojo;

import java.util.ArrayList;


public class Nivel {
    private int idNivel;
    private int espaciosDisponibles;
    private int numeroNivel;
    private ArrayList <Cajon> cajones;
    private ArrayList <Tarjeta> tarjetasDisponibles;
    private int codigoRespuesta;
    private boolean esDeVehiculos;

    public Nivel() {
    }

    public ArrayList<Tarjeta> getTarjetasDisponibles() {
        return tarjetasDisponibles;
    }
    
    public int getIdNivel() {
        return idNivel;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public ArrayList<Cajon> getCajones() {
        return cajones;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public boolean isEsDeVehiculos() {
        return esDeVehiculos;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public void setEspaciosDisponibles(int espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public void setNumeroNivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
    }

    public void setCajones(ArrayList<Cajon> cajones) {
        this.cajones = cajones;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public void setTarjetasDisponibles(ArrayList<Tarjeta> tarjetasDisponibles) {
        this.tarjetasDisponibles = tarjetasDisponibles;
    }

    public void setEsDeVehiculos(boolean esDeVehiculos) {
        this.esDeVehiculos = esDeVehiculos;
    }
    
    
}
