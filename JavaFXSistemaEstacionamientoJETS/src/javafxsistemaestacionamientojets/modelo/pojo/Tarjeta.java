
package javafxsistemaestacionamientojets.modelo.pojo;


public class Tarjeta {
    private int idTarjeta;
    private String codigo;
    private int idEstadoTarjeta;
    private int idCajon;

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
    
    
}
