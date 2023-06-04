
package javafxsistemaestacionamientojets.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;


public class FXMLMenuPrincipalDespachadorController implements Initializable {

    private Usuario usuarioDespachador;
    
    @FXML
    private Label lblNombre;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicVerDisponibilidad(ActionEvent event) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLDisponibilidad.fxml"));
            Parent vista = accesoControlador.load();
            FXMLDisponibilidadController disponibilidad = accesoControlador.getController();
            disponibilidad.inicializarUsuario(usuarioDespachador);
            
            Stage escenario = (Stage) lblNombre.getScene().getWindow();
            escenario.setScene(new Scene (vista));
            escenario.setTitle("Disponibilidad");
            escenario.setX(30);
            escenario.setY(20);
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void inicializarUsuario (Usuario usuarioDespachador){
        this.usuarioDespachador = usuarioDespachador;
        lblNombre.setText("Hola "+this.usuarioDespachador.getNombre());
    }

    @FXML
    private void clicCobrarTarjeta(ActionEvent event) {
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLCobrarTarjeta.fxml"));
            Parent vista = accesoControlador.load();
            FXMLCobrarTarjetaController disponibilidad = accesoControlador.getController();
            disponibilidad.inicializarUsuario(usuarioDespachador);
            
            Stage escenario = (Stage) lblNombre.getScene().getWindow();
            escenario.setScene(new Scene (vista));
            escenario.setTitle("Cobrar Tarjeta");
            escenario.setX(150);
            escenario.setY(20);
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
