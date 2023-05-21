
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.utils.Constantes;


public class RegistroDAO {
    
    public static int crearRegistro (Registro registroNuevo){
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String sentencia = "INSERT INTO Registro(horaEntrada, fechaEntrada, idTipoVehiculo, " +
                    "idEstatusTarifa, idTarjeta, idUsuario) " +
                    "VALUES (?,?,?,?,?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setTime(1, registroNuevo.getHoraEntrada());
                prepararSentencia.setDate (2, registroNuevo.getFechaEntrada());
                prepararSentencia.setInt (3, registroNuevo.getIdTipoVehiculo());
                prepararSentencia.setInt (4, registroNuevo.getIdEstatusTarifa());
                prepararSentencia.setInt (5, registroNuevo.getIdTarjeta());
                prepararSentencia.setInt (6, registroNuevo.getIdUsuario());
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
}
