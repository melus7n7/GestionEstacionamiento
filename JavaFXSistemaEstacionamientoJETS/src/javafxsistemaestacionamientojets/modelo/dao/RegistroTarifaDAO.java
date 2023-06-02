
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
import javafxsistemaestacionamientojets.utils.Constantes;


public class RegistroTarifaDAO {
    
    public static int insertarRegistroTarifa(Registro registro){
        int respuesta = Constantes.OPERACION_EXITOSA;
        for(Tarifa tarifa:registro.getTarifas()){
            Connection conexionBD = ConexionBD.abrirConexionBD();
            if(conexionBD!=null){
                try{
                String sentencia = "INSERT RegistroTarifa(idTarifa, idRegistro) values (?,?)";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, tarifa.getIdTarifa());
                prepararSentencia.setInt(2, registro.getIdRegistro());
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
                }catch(SQLException e){
                    respuesta = Constantes.ERROR_CONSULTA;
                }
            }else{
                respuesta = Constantes.ERROR_CONEXION;
            }
        }
        return respuesta;
    }
}
