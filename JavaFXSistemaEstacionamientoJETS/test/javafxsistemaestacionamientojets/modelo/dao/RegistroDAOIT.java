/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.dao;


import java.util.Calendar;
import java.util.Date;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.modelo.pojo.RegistroRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sulem
 */
public class RegistroDAOIT {
    
    public RegistroDAOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crearRegistro method, of class RegistroDAO.
     */
    @Test
    public void testCrearRegistro() {
        System.out.println("Crear Registro");
        Registro registroNuevo = new Registro();
        
        Date hoy = new Date();
        java.sql.Date fechaEntrada = new java.sql.Date(hoy.getTime());
        Calendar calendarioInstancia = Calendar.getInstance();
        java.sql.Time tiempoEntrada = new java.sql.Time(calendarioInstancia.getTime().getTime());
        
        registroNuevo.setHoraEntrada(tiempoEntrada);
        registroNuevo.setFechaEntrada(fechaEntrada);
        registroNuevo.setIdTipoVehiculo(0);
        registroNuevo.setIdEstatusTarifa(0);
        registroNuevo.setIdTarjeta(0);
        registroNuevo.setIdUsuario(0);
        registroNuevo.setIdMetodoPago(0);
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = RegistroDAO.crearRegistro(registroNuevo);
        //assertEquals(expResult, result);
        assertTrue("Prueba fallida en crear un nuevo registro",expResult == result);
    }

    /**
     * Test of recuperarRegistros method, of class RegistroDAO.
     */
    @Test
    public void testRecuperarRegistros() {
        System.out.println("Recuperar Registros");
        String fecha = "";
        RegistroRespuesta expResult = null;
        RegistroRespuesta result = RegistroDAO.recuperarRegistros(fecha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recuperarRegistroPendientePago method, of class RegistroDAO.
     */
    @Test
    public void testRecuperarRegistroPendientePago() {
        System.out.println("Recuperar un Registro Pendiente de Pago");
        int idTarjeta = 17;
        Registro expResult = new Registro();
        expResult.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Registro result = RegistroDAO.recuperarRegistroPendientePago(idTarjeta);
        assertEquals(expResult.getCodigoRespuesta(), result.getCodigoRespuesta());
    }

    /**
     * Test of modificarRegistro method, of class RegistroDAO.
     */
    @Test
    public void testModificarRegistro() {
        System.out.println("Modificar registro");
        Registro registroModificado = new Registro();
        Date hoy = new Date();
        java.sql.Date fechaEntrada = new java.sql.Date(hoy.getTime());
        Calendar calendarioInstancia = Calendar.getInstance();
        java.sql.Time tiempoEntrada = new java.sql.Time(calendarioInstancia.getTime().getTime());
        registroModificado.setIdRegistro(0);
        registroModificado.setHoraSalida(tiempoEntrada);
        registroModificado.setFechaSalida(fechaEntrada);
        registroModificado.setTiempoTranscurrido(10);
        registroModificado.setPagoTotal(100);
        registroModificado.setIdMetodoPago(1);
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = RegistroDAO.modificarRegistro(registroModificado);
        //assertEquals(expResult, result);
        assertTrue("Prueba fallida en modificar un registro",expResult == result);
    }
    
}
