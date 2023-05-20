
package javafxsistemaestacionamientojets.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.modelo.pojo.Cajon;
import javafxsistemaestacionamientojets.utils.Constantes;
import javax.imageio.ImageIO;


public class FXMLCajonEstacionamientoController implements Initializable {

    @FXML
    private ImageView imgViewVehiculo;
    @FXML
    private Label lblNumeroCajon;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void inicializarVehiculoCajon(Cajon cajon){
        lblNumeroCajon.setText("Cajón " + cajon.getNumeroCajon());
        int estadoLugar = cajon.getEstadoLugar();
        switch(estadoLugar){
            case Constantes.LUGAR_OCUPADO:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/automovilGris.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Constantes.LUGAR_INHABILITADO:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/automovilMorado.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
                
            case Constantes.LUGAR_PENDIENTE:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/automovilAzul.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case Constantes.LUGAR_DISPONIBLE:
                break;
            default:
                break;
        }
    }
    
    public void inicializarMotoCajon(Cajon cajon){
        lblNumeroCajon.setText("Cajón " + cajon.getNumeroCajon());
        int estadoLugar = cajon.getEstadoLugar();
        switch(estadoLugar){
            case Constantes.LUGAR_OCUPADO:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/motoGris.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case Constantes.LUGAR_INHABILITADO:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/motoMorado.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
                
            case Constantes.LUGAR_PENDIENTE:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/motoAzul.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case Constantes.LUGAR_DISPONIBLE:
                try {
                    BufferedImage imagen = ImageIO.read(JavaFXSistemaEstacionamientoJETS.class.getResourceAsStream("recursos/motoVerde.png"));
                    Image imagenDecodificada = SwingFXUtils.toFXImage(imagen, null);
                    imgViewVehiculo.setImage(imagenDecodificada);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    
}
