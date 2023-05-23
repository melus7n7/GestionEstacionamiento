
package javafxsistemaestacionamientojets.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.modelo.pojo.Cajon;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.modelo.dao.NivelDAO;
import javafxsistemaestacionamientojets.modelo.dao.RegistroDAO;
import javafxsistemaestacionamientojets.modelo.dao.TarjetaDAO;
import javafxsistemaestacionamientojets.modelo.pojo.Nivel;
import javafxsistemaestacionamientojets.modelo.pojo.NivelRespuesta;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.modelo.pojo.Tarjeta;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;
import javafxsistemaestacionamientojets.utils.Constantes;
import javafxsistemaestacionamientojets.utils.Utilidades;


public class FXMLDisponibilidadController implements Initializable {

    @FXML
    private Button btnProporcionarTarjeta;
    @FXML
    private VBox vBoxFila1;
    @FXML
    private VBox vBoxFila2;
    @FXML
    private VBox vBoxFila3;
    @FXML
    private VBox vBoxFila4;
    @FXML
    private VBox vBoxFila5;
    @FXML
    private VBox vBoxFila6;
    @FXML
    private VBox vBoxFila7;
    @FXML
    private VBox vBoxFila8;
    @FXML
    private VBox vBoxFila9;
    @FXML
    private VBox vBoxFila10;
    @FXML
    private Label lblNivel;
    
    private ArrayList<VBox> filas;
    private ArrayList<Nivel> niveles;
    private Nivel nivelActual;
    private int posicionNivelActual;
    
    private Usuario usuarioDespachador;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        posicionNivelActual = 0;
        cargarVBox();
        verificarDisponibilidadEnNiveles();
    }
    
    private void verificarDisponibilidadEnNiveles(){
        NivelRespuesta nivelesBD = NivelDAO.obtenerNiveles();
        switch(nivelesBD.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Sin Conexion", 
                        "Lo sentimos por el momento no tiene conexión para acceder a los niveles", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error al cargar los datos", 
                        "Hubo un error al cargar la información de los niveles por favor inténtelo más tarde", 
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                    this.niveles = nivelesBD.getNiveles();
                    boolean hayDisponibilidad = obtenerLugaresTotales();
                    if(hayDisponibilidad){
                        inicializarNivel();
                    }else{
                        Utilidades.mostrarDialogoSimple("Cupo lleno", 
                                "Todos los lugares del estacionamiento han sido ocupados", Alert.AlertType.WARNING);
                        //Error de invocación: cerrarPantalla();
                    }
                    
                break;
        }
    }
    
    private void inicializarNivel(){
        this.nivelActual = niveles.get(posicionNivelActual);
        inicializarElementosNivel();
        eliminarCajones();
        cargarCajones(nivelActual.getIdNivel());
    }
    
    private void inicializarElementosNivel(){
        lblNivel.setText("Nivel " + nivelActual.getIdNivel());
        btnProporcionarTarjeta.setText("Dar tarjeta del nivel " + nivelActual.getIdNivel());
    }
    
    private void cargarCajones (int numeroNivel){
        Nivel nivelActualBD = NivelDAO.obtenerCajonesNivel(numeroNivel);
        switch(nivelActualBD.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Sin Conexion", 
                        "Lo sentimos por el momento no tiene conexión para recuperar los cajones", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error al cargar los datos", 
                        "Hubo un error al cargar la información de los cajones por favor inténtelo más tarde", 
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                    this.nivelActual.setCajones(nivelActualBD.getCajones());
                    cargarNivel();
                break;
        }
    }
    
    private void cargarNivel(){
        ArrayList<Cajon> cajones = nivelActual.getCajones();
        ArrayList<Tarjeta> tarjetasDisponibles = new ArrayList();
        int cajonActual = 0;
        int cajonesPorFila = 5;
        int finCajonSiguiente = cajonesPorFila;
        int cajonesTotales = cajones.size();
        for(int i = 0; i < filas.size(); i++){
            if(cajonActual >= cajonesTotales){
                break;
            }
            for( ; cajonActual < finCajonSiguiente; cajonActual++){
                if(cajones.get(cajonActual).getIdEstadoTarjeta() == Constantes.TARJETA_VALIDA){
                    Tarjeta tarjeta = new Tarjeta ();
                    tarjeta.setIdTarjeta(cajones.get(cajonActual).getIdTarjeta());
                    tarjeta.setIdCajon(cajones.get(cajonActual).getIdCajon());
                    tarjeta.setCodigo(cajones.get(cajonActual).getCodigo());
                    tarjetasDisponibles.add(tarjeta);
                }
                FXMLLoader fmxlLoaderCajon = new FXMLLoader();
                fmxlLoaderCajon.setLocation(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLCajonEstacionamiento.fxml"));
                try{
                    Pane pane = fmxlLoaderCajon.load();
                    FXMLCajonEstacionamientoController elementoEnLista = fmxlLoaderCajon.getController();
                    if(nivelActual.isEsDeVehiculos()){
                        elementoEnLista.inicializarVehiculoCajon(cajones.get(cajonActual));
                    }else{
                        elementoEnLista.inicializarMotoCajon(cajones.get(cajonActual));
                    }
                    filas.get(i).getChildren().add(pane);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            finCajonSiguiente += cajonesPorFila;
        }
        this.nivelActual.setTarjetasDisponibles(tarjetasDisponibles);
    }
    
    private void eliminarCajones(){
        for(int i = 0; i<10; i++){
            filas.get(i).getChildren().clear();
        }
    }
    
    private void cargarVBox(){
        filas = new ArrayList();
        this.filas.add(vBoxFila1);
        this.filas.add(vBoxFila2);
        this.filas.add(vBoxFila3);
        this.filas.add(vBoxFila4);
        this.filas.add(vBoxFila5);
        this.filas.add(vBoxFila6);
        this.filas.add(vBoxFila7);
        this.filas.add(vBoxFila8);
        this.filas.add(vBoxFila9);
        this.filas.add(vBoxFila10);
    }
    
    private boolean obtenerLugaresTotales(){
        int lugaresTotalesDisponibles = 0;
        for(int i = 0; i<Constantes.NIVELES_TOTALES; i++){
            lugaresTotalesDisponibles += niveles.get(i).getEspaciosDisponibles();
        }
        return lugaresTotalesDisponibles != 0;
    }
    
    private void irMenuPrincipal(){
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLMenuPrincipalDespachador.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalDespachadorController disponibilidad = accesoControlador.getController();
            disponibilidad.inicializarUsuario(this.usuarioDespachador);
            
            Stage escenario = (Stage) lblNivel.getScene().getWindow();
            escenario.setScene(new Scene (vista));
            escenario.setTitle("Menú Principal");
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clicRegresar(MouseEvent event) {
        irMenuPrincipal();
    }

    @FXML
    private void clicProporcionarTarjeta(ActionEvent event) {
        int numeroTarjetasDisponibles = nivelActual.getTarjetasDisponibles().size();
        if(numeroTarjetasDisponibles>0){
            int posicionTarjeta = new Random().nextInt(numeroTarjetasDisponibles);
            boolean darTarjeta = Utilidades.mostrarDialogoConfirmacion("Proporcionar Tarjeta", 
                    "¿Desea proporcionar la tarjeta '" + nivelActual.getTarjetasDisponibles().get(posicionTarjeta).getCodigo()+"' ?");
            if(darTarjeta){
                proporcionarTarjeta(nivelActual.getTarjetasDisponibles().get(posicionTarjeta));
            }
        }else{
            Utilidades.mostrarDialogoSimple("Error", "No hay tarjetas disponibles para este nivel", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void clicNivelAbajo(MouseEvent event) {
        if(posicionNivelActual <= 0){
            Utilidades.mostrarDialogoSimple("Nivel Inferior Superado", 
                "No se puede bajar más, se llegó al nivel inferior", 
                Alert.AlertType.WARNING);
        }else{
            posicionNivelActual--;
            inicializarNivel();
        }
    }

    @FXML
    private void clicNivelArriba(MouseEvent event) {
        if(posicionNivelActual >= (Constantes.NIVELES_TOTALES - 1)){
            Utilidades.mostrarDialogoSimple("Nivel Superior Excedido", 
                "No se puede subir más, se llegó al nivel superior", 
                Alert.AlertType.WARNING);
        }else{
            posicionNivelActual++;
            inicializarNivel();
        }
    }
    
    private void proporcionarTarjeta(Tarjeta tarjeta){
        tarjeta.setIdEstadoTarjeta(Constantes.TARJETA_OTORGADO);
        int respuesta = TarjetaDAO.cambiarEstado(tarjeta);
        switch(respuesta){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Sin Conexion", 
                        "Lo sentimos por el momento no tiene conexión para otorgar una tarjeta", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error al cargar los datos", 
                        "Hubo un error al otorgar la tarjeta, por favor inténtelo más tarde", 
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                    generarRegistro(tarjeta);
                break;
        }
    }
    
    private void generarRegistro (Tarjeta tarjeta){
        Registro registroNuevo = new Registro();
        
        Date hoy = new Date();
        java.sql.Date fechaEntrada = new java.sql.Date(hoy.getTime());
        
        Calendar calendarioInstancia = Calendar.getInstance();
        java.sql.Time tiempoEntrada = new java.sql.Time(calendarioInstancia.getTime().getTime());
        
        int idVehiculo = (nivelActual.isEsDeVehiculos())? Constantes.VEHICULO: Constantes.MOTO;
        
        registroNuevo.setFechaEntrada(fechaEntrada);
        registroNuevo.setHoraEntrada(tiempoEntrada);
        registroNuevo.setIdTipoVehiculo(idVehiculo);
        registroNuevo.setIdEstatusTarifa(Constantes.ESTATUS_TARIFA_EN_PROCESO);
        registroNuevo.setIdTarjeta(tarjeta.getIdTarjeta());
        registroNuevo.setIdUsuario(usuarioDespachador.getIdUsuario());
        registroNuevo.setIdMetodoPago(Constantes.METODO_PAGO_PENDIENTE);
        
        int respuesta = RegistroDAO.crearRegistro(registroNuevo);
        
        switch(respuesta){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Sin Conexion", 
                        "Lo sentimos por el momento no tiene conexión para hacer el registro", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error al cargar los datos", 
                        "Hubo un error al crear un nuevo registro, por favor inténtelo más tarde", 
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                    Utilidades.mostrarDialogoSimple("Tarjeta Proporcionada y Registro Creado", 
                            "Se ha hecho un registro con la tarjeta '"+ tarjeta.getCodigo() +
                            "' exitosamente", Alert.AlertType.INFORMATION);
                    inicializarNivel();
                break;
        }
    }
    
    public void inicializarUsuario (Usuario usuarioDespachador){
        this.usuarioDespachador = usuarioDespachador;
    }
    
}
