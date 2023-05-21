
package javafxsistemaestacionamientojets.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;
import javafxsistemaestacionamientojets.utils.Utilidades;


public class FXMLMenuPrincipalAdministradorController implements Initializable {

    @FXML
    private Label lblNombre;

    private Usuario usuarioAdministrador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void inicializarUsuario (Usuario usuarioAdmin){
        this.usuarioAdministrador = usuarioAdmin;
        lblNombre.setText("Hola "+this.usuarioAdministrador.getNombre());
    }

    @FXML
    private void clicAdministrarTarifas(ActionEvent event) {
        
        Stage escenarioAlumnos =  new Stage();
        escenarioAlumnos.setScene(Utilidades.inicializarEscena("vistas/FXMLAdministrarCostos.fxml"));
        escenarioAlumnos.setTitle("Administracion de costos");
        escenarioAlumnos.initModality(Modality.APPLICATION_MODAL);
        escenarioAlumnos.showAndWait();
        
    }
}
