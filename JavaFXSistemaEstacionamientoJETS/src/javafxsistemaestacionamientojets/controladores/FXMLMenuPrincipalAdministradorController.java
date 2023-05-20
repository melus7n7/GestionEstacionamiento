
package javafxsistemaestacionamientojets.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;


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
}
