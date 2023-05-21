
package javafxsistemaestacionamientojets.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafxsistemaestacionamientojets.modelo.ConexionBD;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;
import javafxsistemaestacionamientojets.utils.Constantes;


public class UsuarioDAO {
    public static Usuario verificarSesion (String usuario, String contrasena){
        Usuario usuarioVerificado = new Usuario();
        Connection conexionBD = ConexionBD.abrirConexionBD();
        if(conexionBD != null){
            try{
                String consulta = "SELECT usuario.idUsuario, usuario.nombre, usuario.usuario, " +
                    "usuario.contrasena, usuario.idTipoUsuario, horario.horarioEntrada, horario.horarioSalida " +
                    "FROM usuario " +
                    "INNER JOIN horario ON horario.idHorario = usuario.idHorario " +
                    "WHERE usuario = ? AND contrasena = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, usuario);
                prepararSentencia.setString(2, contrasena);
                ResultSet resultado = prepararSentencia.executeQuery();
                usuarioVerificado.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
                if(resultado.next()){
                    usuarioVerificado.setIdUsuario(resultado.getInt("idUsuario"));
                    usuarioVerificado.setNombre(resultado.getString("nombre"));
                    usuarioVerificado.setUsuario(resultado.getString("usuario"));
                    usuarioVerificado.setContrasena(resultado.getString("contrasena"));
                    usuarioVerificado.setIdTipoUsuario(resultado.getInt("idTipoUsuario"));
                    usuarioVerificado.setHorarioEntrada(resultado.getTime("horarioEntrada"));
                    usuarioVerificado.setHorarioSalida(resultado.getTime("horarioSalida"));
                }
                conexionBD.close();
            }catch(SQLException ex){
                usuarioVerificado.setCodigoRespuesta(Constantes.ERROR_CONSULTA);
                ex.printStackTrace();
            }
        }else{
            usuarioVerificado.setCodigoRespuesta(Constantes.ERROR_CONEXION);
        }
        return usuarioVerificado;
    }
}
