/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxsistemaestacionamientojets.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.interfaces.INotificacionOperacionFormulario;
import javafxsistemaestacionamientojets.modelo.dao.TarifaDAO;
import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
import javafxsistemaestacionamientojets.modelo.pojo.TarifaRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;
import javafxsistemaestacionamientojets.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author monti
 */
public class FXMLAdministrarCostosController implements Initializable, INotificacionOperacionFormulario{

    @FXML
    private TableView<Tarifa> tvHorasPorVehiculo;
    @FXML
    private TableColumn colHorasPorVehiculo;
    @FXML
    private TableColumn colTarifaPorVehiculo;
    @FXML
    private TableColumn colDescripcionPorVehiculo;
    @FXML
    private TableColumn colHorasPorMoto;
    @FXML
    private TableColumn colTarifasPorMoto;
    @FXML
    private TableColumn colDescripcionPorMoto;
    @FXML
    private TableView<Tarifa> tvPrecioMultas;
    @FXML
    private TableColumn colTituloMultas;
    @FXML
    private TableColumn colTarifaMultas;
    @FXML
    private TableColumn colDescripcionMultas;
    
    private ObservableList<Tarifa> tarifas;
    @FXML
    private TableView<Tarifa> tvHorasPorMoto;
    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       configurarTabla();
       cargarInformacionTablaAutomoviles();
       cargarInformacionTablaMotos();
       cargarInformacionTablaMultas();
    }    

    private void configurarTabla(){        
        try{
        colTituloMultas.setCellValueFactory(new PropertyValueFactory("titulo"));
        colTarifaMultas.setCellValueFactory(new PropertyValueFactory("precio"));
        colDescripcionMultas.setCellValueFactory(new PropertyValueFactory("descripcion"));
        colHorasPorVehiculo.setCellValueFactory(new PropertyValueFactory("titulo"));
        colTarifaPorVehiculo.setCellValueFactory(new PropertyValueFactory("precio"));
        colDescripcionPorVehiculo.setCellValueFactory(new PropertyValueFactory("descripcion"));
        colHorasPorMoto.setCellValueFactory(new PropertyValueFactory("titulo"));
        colTarifasPorMoto.setCellValueFactory(new PropertyValueFactory("precio"));
        colDescripcionPorMoto.setCellValueFactory(new PropertyValueFactory("descripcion"));
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void cargarInformacionTablaAutomoviles(){
        tarifas = FXCollections.observableArrayList();
        TarifaRespuesta respuestaBD = TarifaDAO.obtenerInformacionTarifas(Constantes.IDTIPOAUTOMOVILES);
        switch(respuestaBD.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Sin conexion", "Por el momento no hay conexion", Alert.AlertType.ERROR);
            break;
        case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error cargar los datos", "Intentelo mas tarde", Alert.AlertType.WARNING);
             break;
        case Constantes.OPERACION_EXITOSA:
            tarifas.addAll(respuestaBD.getTarifas());
            tvHorasPorVehiculo.setItems(tarifas);
            break;
        }
    }
    
    private void cargarInformacionTablaMotos(){
        tarifas = FXCollections.observableArrayList();
        TarifaRespuesta respuestaBD = TarifaDAO.obtenerInformacionTarifas(Constantes.IDTIPOMOTOS);
        switch(respuestaBD.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Sin conexion", "Por el momento no hay conexion", Alert.AlertType.ERROR);
            break;
        case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error cargar los datos", "Intentelo mas tarde", Alert.AlertType.WARNING);
             break;
        case Constantes.OPERACION_EXITOSA:
            tarifas.addAll(respuestaBD.getTarifas());
            tvHorasPorMoto.setItems(tarifas);
            break;
        }
    }
    
    public void cargarInformacionTablaMultas(){
        tarifas = FXCollections.observableArrayList();
        TarifaRespuesta respuestaBD = TarifaDAO.obtenerInformacionTarifas(Constantes.IDTIPOMULTAS);
        switch(respuestaBD.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Sin conexion", "Por el momento no hay conexion", Alert.AlertType.ERROR);
            break;
        case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error cargar los datos", "Intentelo mas tarde", Alert.AlertType.WARNING);
             break;
        case Constantes.OPERACION_EXITOSA:
            tarifas.addAll(respuestaBD.getTarifas());
            tvPrecioMultas.setItems(tarifas);
            break;
        }
    }
    
    
    @FXML
    private void clicModificarVehiculos(ActionEvent event) {
        int posicion = tvHorasPorVehiculo.getSelectionModel().getSelectedIndex();
        if(posicion!=-1){
            irFormulario(true, tvHorasPorVehiculo.getSelectionModel().getSelectedItem(),false);
        }else{
            Utilidades.mostrarDialogoSimple("Selecciona una multa", "Selecciona una multa para su edicion", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicModificarMotos(ActionEvent event) {
        int posicion = tvHorasPorMoto.getSelectionModel().getSelectedIndex();
        if(posicion!=-1){
            irFormulario(true, tvHorasPorMoto.getSelectionModel().getSelectedItem(),false);
        }else{
            Utilidades.mostrarDialogoSimple("Selecciona una multa", "Selecciona una multa para su edicion", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicModificarMultas(ActionEvent event) {
        int posicion = tvPrecioMultas.getSelectionModel().getSelectedIndex();
        if(posicion!=-1){
            irFormulario(true, tvPrecioMultas.getSelectionModel().getSelectedItem(),true);
        }else{
            Utilidades.mostrarDialogoSimple("Selecciona una multa", "Selecciona una multa para su edicion", Alert.AlertType.WARNING);
        }
    }
    

    @FXML
    private void clicAñadirMultas(ActionEvent event) {
        irFormulario(false,null,true);
    }
    
    private void irFormulario(boolean esEdicion, Tarifa tarifaEdicion, boolean modificarTitulo){
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLFormulario.fxml"));
            Parent vista = accesoControlador.load();
            FXMLFormularioController formulario = accesoControlador.getController();
            formulario.InicializarInformacionFormulario(esEdicion, tarifaEdicion, modificarTitulo, this);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(new Scene(vista));
            escenarioFormulario.setTitle("Formulario");
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLFormularioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clicEliminarMultas(ActionEvent event) {
        int posicion = tvPrecioMultas.getSelectionModel().getSelectedIndex();
        if(posicion!=-1){
            int codigoRespuesta = TarifaDAO.eliminarTarifa(tarifas.get(posicion).getIdTarifa());
            switch(codigoRespuesta){
                case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Sin conexion", "Por el momento no hay conexion", Alert.AlertType.ERROR);
                    break;
                case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error cargar los datos", "Intentelo mas tarde", Alert.AlertType.WARNING);
                    break;
                case Constantes.OPERACION_EXITOSA:
                    Utilidades.mostrarDialogoSimple("Tarifa eliminada", "La tarifa ha sido eliminada correctamente", Alert.AlertType.WARNING);            
                    cargarInformacionTablaMultas();
                    break;
                }                       
        }else{
            Utilidades.mostrarDialogoSimple("Selecciona una tarifa", "Selecciona una tarifa para su eliminación", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicCerrarVentana(MouseEvent event) {
        Stage escenarioPrincipal = (Stage) tvHorasPorVehiculo.getScene().getWindow();
        escenarioPrincipal.close();
    }

    @Override
    public void notificarActualizacionTablas() {
        cargarInformacionTablaAutomoviles();
        cargarInformacionTablaMotos();
        cargarInformacionTablaMultas();
        }

    
}
