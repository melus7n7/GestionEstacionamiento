
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.modelo.pojo.RegistroRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;


public class RegistroDAO {
    
    public static int crearRegistro (Registro registroNuevo){
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO Registro(horaEntrada, fechaEntrada, idTipoVehiculo, " +
                    "idEstatusTarifa, idTarjeta, idUsuario, idMetodoPago) " +
                    "VALUES (?,?,?,?,?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setTime(1, registroNuevo.getHoraEntrada());
                prepararSentencia.setDate (2, registroNuevo.getFechaEntrada());
                prepararSentencia.setInt (3, registroNuevo.getIdTipoVehiculo());
                prepararSentencia.setInt (4, registroNuevo.getIdEstatusTarifa());
                prepararSentencia.setInt (5, registroNuevo.getIdTarjeta());
                prepararSentencia.setInt (6, registroNuevo.getIdUsuario());
                prepararSentencia.setInt (7, registroNuevo.getIdMetodoPago());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
            }catch(SQLException e){
                respuesta = Constantes.ERROR_CONSULTA;
                e.printStackTrace();
            }
        }else{
            respuesta = Constantes.ERROR_CONEXION;
        }
        return respuesta;
    }
    
    public static RegistroRespuesta recuperarRegistros(String fecha){
        RegistroRespuesta respuesta = new RegistroRespuesta();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT Registro.idRegistro, Tarjeta.codigo, Registro.horaEntrada, Registro.horaSalida, Registro.fechaEntrada, Registro.fechaSalida, " +
                    "Registro.pagoTotal, Registro.idEstatusTarifa, estatustarifa.estatusTarifa, Cajon.numeroCajon, " +
                    "tipovehiculo.vehiculo, Registro.tiempoTranscurrido, metodopago.tipometodopago " +
                    "FROM procesosbd.registro " +
                    "INNER JOIN Tarjeta ON Tarjeta.idTarjeta = Registro.idTarjeta " +
                    "INNER JOIN estatustarifa ON estatustarifa.idEstatusTarifa = registro.idEstatusTarifa " +
                    "INNER JOIN tipovehiculo ON tipovehiculo.idTipoVehiculo = Registro.idTipoVehiculo " +
                    "INNER JOIN metodopago ON metodopago.idMetodoPago = registro.idMetodoPago " +
                    "INNER JOIN Cajon ON Cajon.idCajon = tarjeta.idCajon " +
                    "WHERE Registro.fechaEntrada = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, fecha);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList <Registro> registros = new ArrayList();
                while(resultado.next()){
                    Registro registro = new Registro();
                    registro.setIdRegistro(resultado.getInt("idRegistro"));
                    registro.setCodigoTarjeta(resultado.getString("codigo"));
                    registro.setHoraEntrada(resultado.getTime("horaEntrada"));
                    registro.setHoraSalida(resultado.getTime("horaSalida"));
                    registro.setFechaEntrada(resultado.getDate("fechaEntrada"));
                    registro.setFechaSalida(resultado.getDate("fechaSalida"));
                    registro.setPagoTotal(resultado.getDouble("pagoTotal"));
                    registro.setIdEstatusTarifa(resultado.getInt("idEstatusTarifa"));
                    registro.setEstatusTarifa(resultado.getString("estatusTarifa"));
                    registro.setNumeroCajon(resultado.getInt("idEstatusTarifa"));
                    registro.setTipoVehiculo(resultado.getString("vehiculo"));
                    registro.setTiempoTranscurrido(resultado.getInt("tiempoTranscurrido"));
                    registro.setTipoPago(resultado.getString("tipometodopago"));
                    registros.add(registro);
                }
                respuesta.setRegistros(registros);
                respuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
                e.printStackTrace();
            }
        }else{
            respuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return respuesta;
    }
}
