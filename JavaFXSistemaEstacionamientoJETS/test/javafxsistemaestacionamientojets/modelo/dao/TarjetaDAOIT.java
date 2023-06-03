/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.dao;

import javafxsistemaestacionamientojets.modelo.pojo.Tarjeta;
import javafxsistemaestacionamientojets.modelo.pojo.TarjetaRespuesta;
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
public class TarjetaDAOIT {
    
    public TarjetaDAOIT() {
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
     * Test of cambiarEstado method, of class TarjetaDAO.
     */
    @Test
    public void testCambiarEstado() {
        System.out.println("cambiarEstado");
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setIdTarjeta(0);
        tarjeta.setIdEstadoTarjeta(2);
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = TarjetaDAO.cambiarEstado(tarjeta);
        //assertEquals(expResult, result);
        assertTrue("Prueba fallida en cambiar el estado de una tarjeta",expResult == result);
    }

    /**
     * Test of recuperarTarjetas method, of class TarjetaDAO.
     */
    @Test
    public void testRecuperarTarjetas() {
        System.out.println("recuperarTarjetas");
        String codigo = "";
        TarjetaRespuesta expResult = null;
        TarjetaRespuesta result = TarjetaDAO.recuperarTarjetas(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recuperarTarjeta method, of class TarjetaDAO.
     */
    @Test
    public void testRecuperarTarjeta() {
        System.out.println("recuperarTarjeta");
        String codigo = ""
                + "";
        Tarjeta expResult = new Tarjeta();
        expResult.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Tarjeta result = TarjetaDAO.recuperarTarjeta(codigo);
        assertEquals(expResult.getCodigoRespuesta(), result.getCodigoRespuesta());
    }
    
}
