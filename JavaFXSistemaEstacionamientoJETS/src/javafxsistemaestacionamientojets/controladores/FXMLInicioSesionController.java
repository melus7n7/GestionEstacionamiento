
package javafxsistemaestacionamientojets.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.modelo.dao.UsuarioDAO;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;
import javafxsistemaestacionamientojets.utils.Constantes;
import javafxsistemaestacionamientojets.utils.Utilidades;


public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField txtFieldUsuario;
    @FXML
    private PasswordField pssFieldContrasena;
    @FXML
    private Label lblMensajeError;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIngresar(ActionEvent event) {
        validarCampos();
    }
    
    private void validarCampos (){
        String usuario = txtFieldUsuario.getText();
        String contrasena = pssFieldContrasena.getText();
        boolean sonValidos = true;
        if(usuario.isEmpty()){
            sonValidos = false;
            lblMensajeError.setVisible(true);
        }
        if(contrasena.isEmpty()){
            sonValidos = false;
            lblMensajeError.setVisible(true);
        }
        if(sonValidos){
            validarCredencialesUsuario(usuario, contrasena);
        }
    }
    
    private void validarCredencialesUsuario (String usuario, String contrasena){
        Usuario usuarioRespuesta = UsuarioDAO.verificarSesion(usuario, contrasena);
        switch (usuarioRespuesta.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de conexión", 
                        "Por el momento no hay conexión, por favor inténtelo más tarde", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la solicitud", 
                        "Por el momento no se puede procesar la solicitud de verifación", Alert.AlertType.ERROR);
                break;
            case Constantes.OPERACION_EXITOSA:
                if(usuarioRespuesta.getIdUsuario() > 0){
                    validarHorario(usuarioRespuesta);
                }else{
                    Utilidades.mostrarDialogoSimple("Credenciales incorrectas",
                            "El usuario y/o constraseña no son correctos, por favor verifica la información", Alert.AlertType.WARNING);
                }
                break;
            default:
                Utilidades.mostrarDialogoSimple("Error",
                            "El sistema no está disponible por el momento", Alert.AlertType.ERROR);
                
        }
    }
    
    private void validarHorario (Usuario usuario){
        LocalTime horaActual = LocalTime.now().minusHours(1);
        LocalTime horaEntrada = usuario.getHorarioEntrada().toLocalTime();
        System.out.println(horaEntrada);
        LocalTime horaSalida = usuario.getHorarioSalida().toLocalTime();
        System.out.println(horaSalida);
        
        if(horaActual.isBefore(horaSalida) && horaActual.isAfter(horaEntrada)){
            Utilidades.mostrarDialogoSimple("Usuario verificado",
                            "Bienvenido(a) " +usuario.getNombre()+" al sistema ...", Alert.AlertType.INFORMATION);
            if(usuario.getIdTipoUsuario() == Constantes.TIPO_DESPACHADOR){
                irPantallaPrincipalDespachador(usuario);
            }else{
                irPantallaPrincipalAdministrador(usuario);
            }
        }else{
            Utilidades.mostrarDialogoSimple("Error",
                "Por el momento no puede entrar al sistema, es horario de mantenimiento o su hora de salida ya ocurrió", Alert.AlertType.ERROR);
        }
    }
    
    private void irPantallaPrincipalDespachador(Usuario usuario){
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLMenuPrincipalDespachador.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalDespachadorController disponibilidad = accesoControlador.getController();
            disponibilidad.inicializarUsuario(usuario);
            
            Stage escenario = (Stage) lblMensajeError.getScene().getWindow();
            escenario.setScene(new Scene (vista));
            escenario.setTitle("Menú Principal");
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void irPantallaPrincipalAdministrador(Usuario usuario){
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLMenuPrincipalAdministrador.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalAdministradorController disponibilidad = accesoControlador.getController();
            disponibilidad.inicializarUsuario(usuario);
            
            Stage escenario = (Stage) lblMensajeError.getScene().getWindow();
            escenario.setScene(new Scene (vista));
            escenario.setTitle("Menú Principal");
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
