
package javafxsistemaestacionamientojets.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.modelo.pojo.MetodoPago;

public class Utilidades {
    public static void mostrarDialogoSimple (String titulo, String mensaje, Alert.AlertType tipo){
        Alert alertaSimple = new Alert (tipo);
        alertaSimple.setTitle(titulo);
        alertaSimple.setContentText(mensaje);
        alertaSimple.setHeaderText(null);
        alertaSimple.setAlertType(tipo);
        alertaSimple.showAndWait();
    }
    
    public static boolean mostrarDialogoConfirmacion(String titulo, String mensaje){
        Alert alertaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaConfirmacion.setTitle(titulo);
        alertaConfirmacion.setContentText(mensaje);
        alertaConfirmacion.setHeaderText(null);
        Optional<ButtonType> botonClic = alertaConfirmacion.showAndWait();
        return (botonClic.get() == ButtonType.OK);
    }
    
    public static Scene inicializarEscena(String ruta){
       Scene escena = null;
        try {
            Parent vista = FXMLLoader.load(JavaFXSistemaEstacionamientoJETS.class.getResource(ruta)); 
            escena = new Scene (vista);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return escena;
       
   }
    
    public static ArrayList <MetodoPago> obtenerMetodosPago (){
       ArrayList <MetodoPago> metodosPago = new ArrayList();
       MetodoPago tipo1 = new MetodoPago(1, "En efectivo");
       MetodoPago tipo2 = new MetodoPago(2, "Por tarjeta");
       MetodoPago tipo3 = new MetodoPago(3, "Pendiente");
       
       metodosPago.add(tipo1);
       metodosPago.add(tipo2);
       metodosPago.add(tipo3);
       
       return metodosPago;
   }
}
