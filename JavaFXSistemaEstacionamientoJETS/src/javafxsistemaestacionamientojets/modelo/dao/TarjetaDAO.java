
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Tarjeta;
import javafxsistemaestacionamientojets.modelo.pojo.TarjetaRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;


public class TarjetaDAO {
    
    public static int cambiarEstado (Tarjeta tarjeta){
        int respuesta;
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String sentencia = "UPDATE tarjeta SET idEstadoTarjeta = ? " +
                    "WHERE idTarjeta = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt (1, tarjeta.getIdEstadoTarjeta());
                prepararSentencia.setInt (2, tarjeta.getIdTarjeta());
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
    
    public static TarjetaRespuesta recuperarTarjetas (String codigo){
        TarjetaRespuesta respuesta = new TarjetaRespuesta();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT Tarjeta.idTarjeta, Tarjeta.codigo, Tarjeta.idEstadoTarjeta, Tarjeta.idCajon, estadotarjeta.nombreEstado, " +
                        "Cajon.numeroCajon, Nivel.numeroNivel " +
                        "FROM procesosbd.tarjeta " +
                        "INNER JOIN estadotarjeta ON estadotarjeta.idEstadoTarjeta = tarjeta.idEstadoTarjeta " +
                        "INNER JOIN Cajon ON Cajon.idCajon = Tarjeta.idCajon " +
                        "INNER JOIN Nivel ON Nivel.idNivel = Cajon.idNivel " +
                        "WHERE Tarjeta.codigo LIKE ? " +
                        "Group by Tarjeta.idTarjeta";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, "%" + codigo + "%");
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList <Tarjeta> tarjetas = new ArrayList();
                while(resultado.next()){
                    Tarjeta tarjeta = new Tarjeta();
                    tarjeta.setIdTarjeta(resultado.getInt("idTarjeta"));
                    tarjeta.setCodigo(resultado.getString("codigo"));
                    tarjeta.setIdEstadoTarjeta(resultado.getInt("idEstadoTarjeta"));
                    tarjeta.setEstadoTarjeta(resultado.getString("nombreEstado"));
                    tarjeta.setIdCajon(resultado.getInt("idCajon"));
                    tarjeta.setNumeroCajon(resultado.getInt("numeroCajon"));
                    tarjeta.setNumeroNivel(resultado.getInt("numeroNivel"));
                    tarjetas.add(tarjeta);
                }
                respuesta.setTarjetas(tarjetas);
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
