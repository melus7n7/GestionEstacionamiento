
package javafxsistemaestacionamientojets.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafxsistemaestacionamientojets.modelo.dao.RegistroDAO;
import javafxsistemaestacionamientojets.modelo.dao.TarjetaDAO;
import javafxsistemaestacionamientojets.modelo.pojo.RegistroRespuesta;
import javafxsistemaestacionamientojets.modelo.pojo.TarjetaRespuesta;


public class FXMLAdministrarRegistrosTarjetasController implements Initializable {

    @FXML
    private Label lblRegistros;
    @FXML
    private Label lblTarjetas;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String codigo = "Nivel1";
        String cadena = "";
        TarjetaRespuesta tarjetas = TarjetaDAO.recuperarTarjetas(codigo);
        System.out.println(tarjetas.getTarjetas().size());
        for(int i=0; i<tarjetas.getTarjetas().size();i++){
            cadena += tarjetas.getTarjetas().get(i).getCodigo()+", ";
        }
        lblTarjetas.setText(cadena);
        String cadena2 = "";
        LocalDate fechaHoy = LocalDate.now();
        RegistroRespuesta registros = RegistroDAO.recuperarRegistros(fechaHoy.toString());
        System.out.println(registros.getRegistros().size());
        for(int i=0; i<registros.getRegistros().size();i++){
            cadena2 += registros.getRegistros().get(i).getIdRegistro()+", ";
        }
        lblRegistros.setText(cadena2);
    }    
    
}
