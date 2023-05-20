
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Cajon;
import javafxsistemaestacionamientojets.modelo.pojo.Nivel;
import javafxsistemaestacionamientojets.modelo.pojo.NivelRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;

public class NivelDAO {
    
    public static NivelRespuesta obtenerNiveles (){
        NivelRespuesta respuesta = new NivelRespuesta();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT idNivel, espaciosDisponibles, esDeVehiculos FROM nivel";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList <Nivel> niveles = new ArrayList();
                while(resultado.next()){
                    Nivel nivel = new Nivel();
                    nivel.setIdNivel(resultado.getInt("idNivel"));
                    nivel.setEspaciosDisponibles(resultado.getInt("espaciosDisponibles"));
                    nivel.setEsDeVehiculos(resultado.getBoolean("esDeVehiculos"));
                    niveles.add(nivel);
                }
                respuesta.setNiveles(niveles);
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
    
    public static Nivel obtenerCajonesNivel (int numeroNivel){
        Nivel nivelRespuesta = new Nivel();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT  cajon.idCajon, cajon.numeroCajon, " +
                    "cajon.idEstadoCajon, tarjeta.idTarjeta, tarjeta.idEstadoTarjeta, tarjeta.codigo " +
                    "FROM cajon " +
                    "INNER JOIN tarjeta ON tarjeta.idCajon = cajon.idCajon " +
                    "WHERE cajon.idNivel = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, numeroNivel);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList <Cajon> cajones = new ArrayList();
                while(resultado.next()){
                    Cajon cajon = new Cajon();
                    cajon.setNivel(numeroNivel);
                    cajon.setIdCajon(resultado.getInt("idCajon"));
                    cajon.setNumeroCajon(resultado.getInt("numeroCajon"));
                    cajon.setIdEstadoCajon(resultado.getInt("idEstadoCajon"));
                    cajon.setIdTarjeta(resultado.getInt("idTarjeta"));
                    cajon.setIdEstadoTarjeta(resultado.getInt("idEstadoTarjeta"));
                    cajon.setCodigo(resultado.getString("codigo"));
                    cajones.add(cajon);
                }
                nivelRespuesta.setCajones(cajones);
                nivelRespuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                conexionBD.close();
            }catch(SQLException e){
                nivelRespuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
                e.printStackTrace();
            }
        }else{
            nivelRespuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return nivelRespuesta;
    }
}
