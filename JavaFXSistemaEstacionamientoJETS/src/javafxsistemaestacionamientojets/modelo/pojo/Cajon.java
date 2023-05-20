
package javafxsistemaestacionamientojets.modelo.pojo;

import javafxsistemaestacionamientojets.utils.Constantes;


public class Cajon {
    private int idCajon;
    private int numeroCajon;
    private int idEstadoCajon;
    
    private int idTarjeta;
    private int idEstadoTarjeta;
    private String codigo;
    
    private int nivel;

    public Cajon() {
    }
    
    //Getters
    public int getIdCajon() {
        return idCajon;
    }

    public int getNumeroCajon() {
        return numeroCajon;
    }

    public int getNivel() {
        return nivel;
    }

    public int getIdEstadoCajon() {
        return idEstadoCajon;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public int getIdEstadoTarjeta() {
        return idEstadoTarjeta;
    }

    public String getCodigo() {
        return codigo;
    }
    
    //Setters

    public void setIdCajon(int idCajon) {
        this.idCajon = idCajon;
    }

    public void setNumeroCajon(int numeroCajon) {
        this.numeroCajon = numeroCajon;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setIdEstadoCajon(int idEstadoCajon) {
        this.idEstadoCajon = idEstadoCajon;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public void setIdEstadoTarjeta(int idEstadoTarjeta) {
        this.idEstadoTarjeta = idEstadoTarjeta;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    
    public int getEstadoLugar (){
        int estadoLugar = Constantes.LUGAR_DISPONIBLE;
        switch(this.idEstadoCajon){
            case Constantes.CAJON_LIBRE:
                switch(this.idEstadoTarjeta){
                    case Constantes.TARJETA_OTORGADO:
                        estadoLugar = Constantes.LUGAR_PENDIENTE;
                        break;
                    case Constantes.TARJETA_PERDIDO:
                        estadoLugar = Constantes.LUGAR_INHABILITADO;
                        break;
                    case Constantes.TARJETA_VALIDA:
                    default:
                        break;
                }
                break;
            case Constantes.CAJON_OCUPADO:
                switch(this.idEstadoTarjeta){
                    case Constantes.TARJETA_VALIDA:
                        estadoLugar = 0;
                        break;
                    case Constantes.TARJETA_OTORGADO:
                        estadoLugar = Constantes.LUGAR_OCUPADO;
                        break;
                    case Constantes.TARJETA_PERDIDO:
                        estadoLugar = Constantes.LUGAR_INHABILITADO;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return estadoLugar;
    }
    
}
