
package javafxsistemaestacionamientojets.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class FXMLCobrarTarjetaController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<?> tvTarjetas;
    @FXML
    private TableColumn<?, ?> tvCodigoTarjeta;
    @FXML
    private TableColumn<?, ?> tvNivelTarjeta;
    @FXML
    private TableColumn<?, ?> tvCajonTarjeta;
    @FXML
    private TableColumn<?, ?> tvTipoVehiculoTarjeta;
    @FXML
    private TableColumn<?, ?> tvEstatusTarifaTarjeta;
    @FXML
    private TableColumn<?, ?> tvFechaEntradaTarjeta;
    @FXML
    private TableColumn<?, ?> tvHoraEntradaTarjeta;
    @FXML
    private TableColumn<?, ?> tvNombreDespachadorTarjeta;
    @FXML
    private TextField tfIngresarIDTarjeta;
    @FXML
    private Label lbIDTarjeta;
    @FXML
    private Label lbHoraActual;
    @FXML
    private Label lbTarifaCobro;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void clicCerrarVentana(MouseEvent event) {
    }

    @FXML
    private void clicIniciarProcesoPago(ActionEvent event) {
    }

    @FXML
    private void clicConfirmarPago(ActionEvent event) {
    }
    
}
