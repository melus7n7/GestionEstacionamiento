
package javafxsistemaestacionamientojets.modelo.pojo;


public class Tarjeta {
    private int idTarjeta;
    private String codigo;
    private int idEstadoTarjeta;
    private String estadoTarjeta;
    private int idCajon;
    private int numeroCajon;
    private int numeroNivel;
    
    private int codigoRespuesta;

    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, String codigo, int idEstadoTarjeta, int idCajon) {
        this.idTarjeta = idTarjeta;
        this.codigo = codigo;
        this.idEstadoTarjeta = idEstadoTarjeta;
        this.idCajon = idCajon;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getIdEstadoTarjeta() {
        return idEstadoTarjeta;
    }

    public int getIdCajon() {
        return idCajon;
    }

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public int getNumeroCajon() {
        return numeroCajon;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setIdEstadoTarjeta(int idEstadoTarjeta) {
        this.idEstadoTarjeta = idEstadoTarjeta;
    }

    public void setIdCajon(int idCajon) {
        this.idCajon = idCajon;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public void setNumeroCajon(int numeroCajon) {
        this.numeroCajon = numeroCajon;
    }

    public void setNumeroNivel(int numeroNivel) {
        this.numeroNivel = numeroNivel;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }
    
    
}
