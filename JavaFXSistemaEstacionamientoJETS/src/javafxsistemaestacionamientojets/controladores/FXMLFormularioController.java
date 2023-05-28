/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsistemaestacionamientojets.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.interfaces.INotificacionOperacionFormulario;
import static javafxsistemaestacionamientojets.modelo.dao.TarifaDAO.guardarTarifa;
import static javafxsistemaestacionamientojets.modelo.dao.TarifaDAO.modificarTarifa;
import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
import javafxsistemaestacionamientojets.utils.Constantes;
import javafxsistemaestacionamientojets.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author monti
 */
public class FXMLFormularioController implements Initializable {

    @FXML
    private TextField tfDescripcion;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfPrecio;
    
    private Tarifa tarifaEdicion;
    private boolean esEdicion;
    private boolean modificarTitulo;
    private INotificacionOperacionFormulario interfazNotificacion;
    @FXML
    private Label lbTitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void InicializarInformacionFormulario(boolean esEdicion, Tarifa tarifaEdicion, boolean modificarTitulo, INotificacionOperacionFormulario interfazNotificacion){
        this.esEdicion=esEdicion;
        this.tarifaEdicion=tarifaEdicion;
        this.modificarTitulo=modificarTitulo;
        this.interfazNotificacion=interfazNotificacion;
        if(esEdicion){
            lbTitulo.setText("Editar informacion de " + tarifaEdicion.getTitulo());
            cargarInformacionEdicion();
        }else{
            lbTitulo.setText("Registrar nueva tarifa");
        }
    }
    
    private void cargarInformacionEdicion(){
        tfTitulo.setText(tarifaEdicion.getTitulo());
        tfTitulo.setEditable(modificarTitulo);
        tfPrecio.setText(String.valueOf(tarifaEdicion.getPrecio()));
        tfDescripcion.setText(tarifaEdicion.getDescripcion());
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        cerrarVentana();
        
    }
    private void cerrarVentana(){
        Stage escenarioBase = (Stage) tfTitulo.getScene().getWindow();
        escenarioBase.close();
    }

    @FXML
    private void clicConfirmar(ActionEvent event) {
        validarCamposRegistro();
    }
    
    private void validarCamposRegistro(){
        if(tfTitulo.getText().isEmpty() || tfDescripcion.getText().isEmpty() || tfPrecio.getText().isEmpty()){
            Utilidades.mostrarDialogoSimple("Campos invalidos","No puede haber campos vacios, Completelos para continuar", Alert.AlertType.ERROR);
        }else{
            try{                
                String titulo = tfTitulo.getText();
                double precio = Double.parseDouble(tfPrecio.getText());
                String descripcion=tfDescripcion.getText();
                Tarifa tarifaValidada = new Tarifa();
                tarifaValidada.setTitulo(titulo);
                tarifaValidada.setPrecio(precio);
                tarifaValidada.setDescripcion(descripcion);
                if(precio>0){
                    if(esEdicion){  
                        tarifaValidada.setIdTarifa(tarifaEdicion.getIdTarifa());
                        actualizarTarifa(tarifaValidada);
                    }else{
                        registrarTarifa(tarifaValidada);
                    }
                }else{
                    Utilidades.mostrarDialogoSimple("Campos invalidos","No es un formato de precio valido", Alert.AlertType.ERROR);
                }
            }catch(NumberFormatException e){
                Utilidades.mostrarDialogoSimple("Campos invalidos","No es un formato de precio valido", Alert.AlertType.ERROR);                                    
            }
        }
    }
    
    private void registrarTarifa(Tarifa tarifaValidada){
        int codigoRespuesta = guardarTarifa(tarifaValidada, Constantes.IDTIPOMULTAS);                
        switch(codigoRespuesta){
            case Constantes.ERROR_CONEXION:
            Utilidades.mostrarDialogoSimple("Sin conexion", "Por el momento no hay conexion", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
            Utilidades.mostrarDialogoSimple("Error cargar los datos", "Intentelo mas tarde", Alert.AlertType.WARNING);
            break;
            case Constantes.OPERACION_EXITOSA:
            Utilidades.mostrarDialogoSimple("Tarifa añadida", "La tarifa fue añadida correctamente", Alert.AlertType.INFORMATION);            
            interfazNotificacion.notificarActualizacionTablas();            
            cerrarVentana();
            break;
        }
    }
    
    private void actualizarTarifa(Tarifa tarifaValidada){
        int codigoRespuesta = modificarTarifa(tarifaValidada);                
        switch(codigoRespuesta){
            case Constantes.ERROR_CONEXION:
            Utilidades.mostrarDialogoSimple("Sin conexion", "Por el momento no hay conexion", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
            Utilidades.mostrarDialogoSimple("Error cargar los datos", "Intentelo mas tarde", Alert.AlertType.WARNING);
            break;
            case Constantes.OPERACION_EXITOSA:
            Utilidades.mostrarDialogoSimple("Tarifa modificada", "La informacion de la tarifa fue modificada correctamente", Alert.AlertType.INFORMATION);            
            interfazNotificacion.notificarActualizacionTablas();
            cerrarVentana();
            break;
        }
    }
    
}
