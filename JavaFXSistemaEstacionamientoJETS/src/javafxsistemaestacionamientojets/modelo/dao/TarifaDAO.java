/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
import javafxsistemaestacionamientojets.modelo.pojo.TarifaRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;

/**
 *
 * @author monti
 */
public class TarifaDAO {
    public static TarifaRespuesta obtenerInformacionTarifas(int idTipoTarifa){
        TarifaRespuesta respuesta = new TarifaRespuesta();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD!=null){
            try{
                String consulta = "SELECT idTarifa, titulo, precio, descripción, idTipoTarifa FROM procesosbd.tarifa WHERE idTipoTarifa = ? ;"; 
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idTipoTarifa);
                ResultSet resultado = prepararSentencia.executeQuery();
                ArrayList<Tarifa> TarifasConsulta = new ArrayList();
                while(resultado.next()){
                    Tarifa tarifa = new Tarifa();
                    tarifa.setIdTarifa(resultado.getInt("idTarifa"));
                    tarifa.setTitulo(resultado.getString("titulo"));
                    tarifa.setPrecio(resultado.getDouble("precio"));
                    tarifa.setDescripcion(resultado.getString("descripción"));
                    tarifa.setIdTipoTarifa(resultado.getInt("idTipoTarifa"));
                    TarifasConsulta.add(tarifa);
                }
                respuesta.setTarifas(TarifasConsulta);
                conexionBD.close();
                respuesta.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
            }catch(SQLException e){
                e.printStackTrace();
                respuesta.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
            } 
        }else{
            respuesta.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return respuesta;
        
        
    }
    
    public static int guardarTarifa(Tarifa tarifaNueva, int idTipoTarifa){
            int respuesta;
            Connection conexionBD = ConexionBD.abrirConexionBD();
            if(conexionBD!=null){
                try{
                String sentencia = "insert into Tarifa(titulo,precio, descripción,idTipoTarifa) VALUES (?,?,?,?);";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, tarifaNueva.getTitulo());
                prepararSentencia.setDouble(2, tarifaNueva.getPrecio());
                prepararSentencia.setString(3, tarifaNueva.getDescripcion());
                prepararSentencia.setInt(4, idTipoTarifa);
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
                }catch(SQLException e){
                    respuesta = Constantes.ERROR_CONSULTA;
                }
            }else{
                respuesta = Constantes.ERROR_CONEXION;
            }
            return respuesta;
    }
    
    public static int eliminarTarifa(int idTarifa){
            int respuesta;
            Connection conexionBD = ConexionBD.abrirConexionBD();
            if(conexionBD!=null){
                try{
                String sentencia = "DELETE FROM tarifa WHERE idTarifa = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setInt(1, idTarifa);
                int filasAfectadas = prepararSentencia.executeUpdate();
  
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
                }catch(SQLException e){
                    respuesta = Constantes.ERROR_CONSULTA;
                }
            }else{
                respuesta = Constantes.ERROR_CONEXION;
            }
            return respuesta;   
        }
    
        public static int modificarTarifa(Tarifa modificaciónTarifa){
            int respuesta;
            Connection conexionBD = ConexionBD.abrirConexionBD();
            if(conexionBD!=null){
                try{
                String sentencia = "UPDATE tarifa SET titulo = ?, precio = ?, descripción = ? WHERE idTarifa = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(sentencia);
                prepararSentencia.setString(1, modificaciónTarifa.getTitulo());
                prepararSentencia.setDouble(2, modificaciónTarifa.getPrecio());
                prepararSentencia.setString(3, modificaciónTarifa.getDescripcion());
                prepararSentencia.setInt(4, modificaciónTarifa.getIdTarifa());                
                int filasAfectadas = prepararSentencia.executeUpdate();
                respuesta = (filasAfectadas == 1) ? Constantes.OPERACION_EXITOSA : Constantes.ERROR_CONSULTA;
                conexionBD.close();
                }catch(SQLException e){
                    respuesta = Constantes.ERROR_CONSULTA;
                }
            }else{
                respuesta = Constantes.ERROR_CONEXION;
            }
            return respuesta;
        }
    
    
}
