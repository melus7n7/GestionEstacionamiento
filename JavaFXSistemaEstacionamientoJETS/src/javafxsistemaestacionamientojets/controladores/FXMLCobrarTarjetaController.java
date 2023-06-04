package javafxsistemaestacionamientojets.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafxsistemaestacionamientojets.JavaFXSistemaEstacionamientoJETS;
import javafxsistemaestacionamientojets.modelo.pojo.Tarjeta;
import javafxsistemaestacionamientojets.modelo.pojo.Usuario;
import javafxsistemaestacionamientojets.utils.Utilidades;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafxsistemaestacionamientojets.modelo.dao.RegistroDAO;
import javafxsistemaestacionamientojets.modelo.dao.RegistroTarifaDAO;
import javafxsistemaestacionamientojets.modelo.dao.TarifaDAO;
import javafxsistemaestacionamientojets.modelo.dao.TarjetaDAO;
import javafxsistemaestacionamientojets.modelo.pojo.MetodoPago;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.modelo.pojo.RegistroRespuesta;
import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
import javafxsistemaestacionamientojets.modelo.pojo.TarifaRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;

public class FXMLCobrarTarjetaController implements Initializable {
    
    private Tarjeta tarjetaAPagar;
    private Registro registroAPagar;
    private double total;

    @FXML
    private Label lbTitulo;
    @FXML
    private Label lblCodigo;
    @FXML
    private TextField tfIngresarIDTarjeta;
    @FXML
    private Label lblNivel;
    @FXML
    private Label lblCajon;
    @FXML
    private Label lblTipoVehiculo;
    @FXML
    private Label lblFechaEntrada;
    @FXML
    private Label lblHoraEntrada;
    @FXML
    private Label lblFechaSalida;
    @FXML
    private Label lblHoraSalida;
    @FXML
    private Label lblTotalHoras;
    @FXML
    private ComboBox<Tarifa> cbMulta;
    @FXML
    private ComboBox<MetodoPago> cbMetodoPago;
    @FXML
    private Label lblTarifaCobrar;
    @FXML
    private Button btnMostrar;
    @FXML
    private Button btnCobrar;
    @FXML
    private Button btnCancelar;
    
    private Usuario usuarioDespachador;
    
    private ObservableList<Tarifa> tarifas;
    private ObservableList<MetodoPago> metodosPago;
    
    @FXML
    private Button btnIniciarProcesoPago;
    @FXML
    private Label lblTiempoTranscurrido;
    @FXML
    private Button btnTarjetaPerdida;
    @FXML
    private Pane pnDatosTarjeta;
    @FXML
    private Label lblEntrada;
    @FXML
    private Pane pnDetallesCobro;
    @FXML
    private Label lblSalida;
    @FXML
    private Label lblDatosPago;
    @FXML
    private Label lblDatos;
    @FXML
    private Rectangle rctPantallaFondo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limpiarDatos();
    }    
    
    public void inicializarUsuario (Usuario usuarioDespachador){
        this.usuarioDespachador = usuarioDespachador;
    }

    @FXML
    private void clicRegresar(MouseEvent event) {
        irMenuPrincipal();
    }
    
    @FXML
    private void clicCancelarCobro(ActionEvent event) {
        limpiarDatos();
        registroAPagar = null;
        tarjetaAPagar = null;
        btnMostrar.setVisible(true);
    }
    
    @FXML
    private void clicMostrar(ActionEvent event) throws ParseException {
        if(tfIngresarIDTarjeta.getText().isEmpty()){
            Utilidades.mostrarDialogoSimple("Ingresa un código de tarjeta.",
                    "Ingresa un código de tarjeta para proceder con su cobro.",
                    Alert.AlertType.WARNING);
        }else{
            comprobarCodigoTarjeta(tfIngresarIDTarjeta.getText());
        }
    }
    
    @FXML
    private void clicCobrarTarjeta(ActionEvent event) {
        registroAPagar.setIdEstatusTarifa(Constantes.ESTATUS_TARIFA_PAGADO);
        pagarRegistro();
    }
    
    @FXML
    private void clicTarjetaPerdida(ActionEvent event) {
        registroAPagar.setIdEstatusTarifa(Constantes.ESTATUS_TARIFA_PENDIENTE_DE_PAGO);
        pagarRegistro();
    }
    
    @FXML
    private void clicIniciarPago(ActionEvent event) {
        btnIniciarProcesoPago.setVisible(false);
        btnCobrar.setVisible(true);
        btnCancelar.setVisible(true);
        btnTarjetaPerdida.setVisible(true);
        pnDetallesCobro.setVisible(true);
        lblDatosPago.setVisible(true);
        lblSalida.setVisible(true);
        cargarDatosCobro();
        cargarMultas();
        cargarMetodoPago();
    }

    private void irMenuPrincipal(){
        try {
            FXMLLoader accesoControlador = new FXMLLoader(JavaFXSistemaEstacionamientoJETS.class.getResource("vistas/FXMLMenuPrincipalDespachador.fxml"));
            Parent vista = accesoControlador.load();
            FXMLMenuPrincipalDespachadorController cobrar = accesoControlador.getController();
            cobrar.inicializarUsuario(this.usuarioDespachador);
            
            Stage escenario = (Stage) lblCajon.getScene().getWindow();
            escenario.setScene(new Scene (vista));
            escenario.setTitle("Menú Principal");
            escenario.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void limpiarDatos(){
        lblCodigo.setText("");
        lblCajon.setText("");
        lblFechaEntrada.setText("");
        lblFechaSalida.setText("");
        lblHoraEntrada.setText("");
        lblHoraSalida.setText("");
        lblNivel.setText("");
        lblTarifaCobrar.setText("");
        lblTipoVehiculo.setText("");
        lblTotalHoras.setText("");
        lblTiempoTranscurrido.setText("");
        lblDatosPago.setVisible(false);
        lblSalida.setVisible(false);
        cbMetodoPago.setVisible(false);
        cbMulta.setVisible(false);
        btnCancelar.setVisible(false);
        btnCobrar.setVisible(false);
        pnDetallesCobro.setVisible(false);
        btnIniciarProcesoPago.setVisible(false);
        btnTarjetaPerdida.setVisible(false);
        rctPantallaFondo.setVisible(true);
    }

    private void comprobarCodigoTarjeta(String codigo) {
        this.tarjetaAPagar = TarjetaDAO.recuperarTarjeta(codigo);
        switch(tarjetaAPagar.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de conexión",
                        "El código no fue encontrado debido a un error en su conexión...",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información de la tarjeta no pudo ser recuperada, por favor verifica sus datos nuevamente.",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                if(tarjetaAPagar.getIdTarjeta() != 0){
                    cargarInformacionTicket();
                }else{
                    Utilidades.mostrarDialogoSimple("Tarjeta no encontrada",
                        "El código no corresponde a ninguna tarjeta registrada, por favor ingrese los datos correctamente",
                        Alert.AlertType.WARNING);
                }
        }
    }
    
    private void cargarInformacionTicket() {
        Registro registro = RegistroDAO.recuperarRegistroPendientePago(tarjetaAPagar.getIdTarjeta());
        switch(registro.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                Utilidades.mostrarDialogoSimple("Error de conexión",
                        "El código no fue encontrado debido a un error en su conexión...",
                        Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                Utilidades.mostrarDialogoSimple("Error en la información",
                        "La información de la tarjeta no pudo ser recuperada, por favor verifica sus datos nuevamente.",
                        Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                if(registro.getIdRegistro()!= 0){
                    this.registroAPagar = registro;
                    registroAPagar.setIdTarjeta(tarjetaAPagar.getIdEstadoTarjeta());
                    registroAPagar.setNumeroCajon(tarjetaAPagar.getNumeroCajon());
                    registroAPagar.setCodigoTarjeta(tarjetaAPagar.getCodigo());
                    registroAPagar.setTarifas(new ArrayList());
                    mostrarDatos();
                }else{
                    Utilidades.mostrarDialogoSimple("La tarjeta no tiene un pago pendiente",
                        "No se encontró un registro pendiente de pago con la tarjeta ingresada",
                        Alert.AlertType.WARNING);
                }
        }
    }
    
    private void mostrarDatos(){
        if(registroAPagar.getIdTipoVehiculo()== Constantes.VEHICULO){
            lblTipoVehiculo.setText("Tipo de vehículo: Automóvil");
        }else{
            lblTipoVehiculo.setText("Tipo de vehículo: Moto");
        }
        btnMostrar.setVisible(false);
        lblCodigo.setText("Código: " + registroAPagar.getCodigoTarjeta());
        lblHoraEntrada.setText("Hora entrada: " + registroAPagar.getHoraEntrada().toString());
        lblFechaEntrada.setText("Fecha entrada: " + registroAPagar.getFechaEntrada().toString());
        lblCajon.setText("Cajón: " + String.valueOf(registroAPagar.getNumeroCajon()));
        lblNivel.setText("Nivel: " + String.valueOf(tarjetaAPagar.getNumeroNivel()));
        btnIniciarProcesoPago.setVisible(true);
        rctPantallaFondo.setVisible(false);
    }
    
    private void cargarMultas(){
        cbMulta.setVisible(true);
        tarifas = FXCollections.observableArrayList();
        TarifaRespuesta respuesta = TarifaDAO.obtenerInformacionTarifas(Constantes.IDTIPOMULTAS);
        switch(respuesta.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Error de conexión", 
                            "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de consulta", 
                            "Por el momento no se puede obtener información de la base de datos", Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                tarifas.addAll(respuesta.getTarifas());
                cbMulta.setItems(tarifas);
                cbMulta.valueProperty().addListener(new ChangeListener<Tarifa>() {
                    @Override
                    public void changed(ObservableValue<? extends Tarifa> observable, Tarifa oldValue, Tarifa newValue) {
                        if(oldValue != null){
                            registroAPagar.getTarifas().remove(oldValue);
                        }
                        if (newValue != null){
                            registroAPagar.getTarifas().add(newValue);
                        }
                        calcularPrecio();
                    }
                });
                break;
        }
    }
    
    private void cargarMetodoPago(){
        cbMetodoPago.setVisible(true);
        metodosPago = FXCollections.observableArrayList();
        metodosPago.addAll(Utilidades.obtenerMetodosPago());
        cbMetodoPago.setItems(metodosPago);
        cbMetodoPago.valueProperty().addListener(new ChangeListener<MetodoPago>() {
            @Override
            public void changed(ObservableValue<? extends MetodoPago> observable, MetodoPago oldValue, MetodoPago newValue) {
                if (newValue != null){
                    registroAPagar.setIdMetodoPago(newValue.getIdMetodoPago());
                }
            }
        });
    }
    
    private void cargarDatosCobro(){
        LocalTime horaSalida = LocalTime.now().minusHours(1);
        Time sqlTime = Time.valueOf(horaSalida);
        registroAPagar.setHoraSalida(sqlTime);
        Date fechaSalida = new Date();
        java.sql.Date sqlDate = new java.sql.Date(fechaSalida.getTime());
        registroAPagar.setFechaSalida(sqlDate);
        SimpleDateFormat formatoFechaDia = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSalidaFormato = formatoFechaDia.format(fechaSalida);
        lblFechaSalida.setVisible(true);
        lblHoraSalida.setVisible(true);
        lblFechaSalida.setText("Fecha salida: " + fechaSalidaFormato);
        lblHoraSalida.setText("Hora salida: " + horaSalida);
        
        LocalTime horaInicio = registroAPagar.getHoraEntrada().toLocalTime();
        Date fechaInicio = registroAPagar.getFechaEntrada();
        int tiempo;
        if(sqlDate.toString().equals(fechaInicio.toString())){
            Duration duration = Duration.between(horaInicio, horaSalida);
            tiempo = Math.round((int) duration.toHours());
            registroAPagar.setTiempoTranscurrido(tiempo);
        }else{
            LocalDate localStartDate = registroAPagar.getFechaEntrada().toLocalDate();
            LocalDate localEndDate = sqlDate.toLocalDate();
            Period period = Period.between(localStartDate, localEndDate);
            Duration duration = Duration.between(horaInicio, horaSalida);
            tiempo = Math.round((int) duration.toHours()) + period.getDays()*24;
            registroAPagar.setTiempoTranscurrido(tiempo);
        }
        lblTiempoTranscurrido.setText("Tiempo total en horas: " + tiempo);
        obtenerPrecio();
        
    }
    
    private void obtenerPrecio(){
        if(registroAPagar.getTiempoTranscurrido()>12){
            registroAPagar.setTiempoTranscurrido(12);
        }
        Tarifa precio = TarifaDAO.calcularPrecio(registroAPagar.getIdTipoVehiculo(), registroAPagar.getTiempoTranscurrido());
        switch(precio.getCodigoRespuesta()){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Error de conexión", 
                            "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de calcular precio", 
                            "Por el momento no se puede obtener información de la base de datos", Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                registroAPagar.getTarifas().add(precio);
                calcularPrecio();
                break;
        }
    }
    
    private void calcularPrecio(){
        double costoTotal = 0;
        for(Tarifa tarifa: registroAPagar.getTarifas()){
            costoTotal += tarifa.getPrecio();
        }
        registroAPagar.setPagoTotal(costoTotal);
        lblTarifaCobrar.setText("Tarifa a cobrar: " + registroAPagar.getPagoTotal());
    }
    
    private void pagarRegistro(){
        if(registroAPagar.getIdMetodoPago() != 0){
            int respuesta = RegistroDAO.modificarRegistro(registroAPagar);
            switch(respuesta){
                case Constantes.ERROR_CONEXION:
                        Utilidades.mostrarDialogoSimple("Error de conexión", 
                                "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                    break;
                case Constantes.ERROR_CONSULTA:
                        Utilidades.mostrarDialogoSimple("Error de pagar registro", 
                                "Por el momento no se puede modificar la información de la base de datos", Alert.AlertType.WARNING);
                    break;
                case Constantes.OPERACION_EXITOSA:
                    registrarTarifasARegistro();
                    break;
            }
        }else{
            Utilidades.mostrarDialogoSimple("Seleccione un método de pago", 
                                "Tiene que seleccionar un método de pago para guardar el registro", Alert.AlertType.WARNING);
        }
        
    }
    
    private void registrarTarifasARegistro(){
        int respuesta = RegistroTarifaDAO.insertarRegistroTarifa(registroAPagar);
        switch(respuesta){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Error de conexión", 
                            "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de registrar tarifas", 
                            "Por el momento no se puede modificar la información de la base de datos", Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                devolverTarjeta();
                break;
        }
    }
    
    private void devolverTarjeta(){
        if(registroAPagar.getIdEstatusTarifa() == Constantes.ESTATUS_TARIFA_PAGADO){
            tarjetaAPagar.setIdEstadoTarjeta(Constantes.TARJETA_VALIDA);
        }else{
            tarjetaAPagar.setIdEstadoTarjeta(Constantes.TARJETA_PERDIDO);
        }
        
        int respuesta = TarjetaDAO.cambiarEstado(tarjetaAPagar);
        switch(respuesta){
            case Constantes.ERROR_CONEXION:
                    Utilidades.mostrarDialogoSimple("Error de conexión", 
                            "Error en la conexión con la base de datos", Alert.AlertType.ERROR);
                break;
            case Constantes.ERROR_CONSULTA:
                    Utilidades.mostrarDialogoSimple("Error de consulta", 
                            "Por el momento no se puede obtener información de la base de datos", Alert.AlertType.WARNING);
                break;
            case Constantes.OPERACION_EXITOSA:
                if(registroAPagar.getIdEstatusTarifa() == Constantes.ESTATUS_TARIFA_PAGADO){
                    Utilidades.mostrarDialogoSimple("Registro pagado", 
                            "Se pagó el registro exitosamente", Alert.AlertType.INFORMATION);
                }else{
                    Utilidades.mostrarDialogoSimple("Tarjeta reportada como perdida", 
                            "La tarjeta ha sido registrada como perdida y el registro de ha guardado", Alert.AlertType.INFORMATION);
                }
                
                irMenuPrincipal();
                break;
        }
    }

    
    
}