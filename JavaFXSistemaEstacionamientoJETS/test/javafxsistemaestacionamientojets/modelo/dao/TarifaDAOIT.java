/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.dao;

import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
import javafxsistemaestacionamientojets.modelo.pojo.TarifaRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author monti
 */
public class TarifaDAOIT {
    
    public TarifaDAOIT() {
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
     * Test of obtenerInformacionTarifas method, of class TarifaDAO.
     */
    @Test
    public void testObtenerInformacionTarifas() {
        System.out.println("obtenerInformacionTarifas");
        int idTipoTarifa = 1;
        TarifaRespuesta expResult = new TarifaRespuesta();
        expResult.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        TarifaRespuesta result = TarifaDAO.obtenerInformacionTarifas(idTipoTarifa);
        assertEquals(expResult.getCodigoRespuesta(), result.getCodigoRespuesta());
    }

    /**
     * Test of guardarTarifa method, of class TarifaDAO.
     */
    @Test
    public void testGuardarTarifa() {
        System.out.println("guardarTarifa");
        Tarifa tarifaNueva = new Tarifa();
        tarifaNueva.setTitulo("Titulo");
        tarifaNueva.setDescripcion("descripcion");
        tarifaNueva.setPrecio(10.0);
        int idTipoTarifa = 3;
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = TarifaDAO.guardarTarifa(tarifaNueva, idTipoTarifa);
        assertEquals(expResult, result);
    }

    /**
     * Test of eliminarTarifa method, of class TarifaDAO.
     */
    @Test
    public void testEliminarTarifa() {
        System.out.println("eliminarTarifa");
        int idTarifa = 23;
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = TarifaDAO.eliminarTarifa(idTarifa);
        assertEquals(expResult, result);
    }

    /**
     * Test of modificarTarifa method, of class TarifaDAO.
     */
    @Test
    public void testModificarTarifa() {
        System.out.println("modificarTarifa");
        Tarifa tarifaModificada = new Tarifa();
        tarifaModificada.setIdTarifa(1);
        tarifaModificada.setTitulo("Titulo");
        tarifaModificada.setDescripcion("descripcion");
        tarifaModificada.setPrecio(10.0);
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = TarifaDAO.modificarTarifa(tarifaModificada);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of calcularPrecio method, of class TarifaDAO.
     */
    @Test
    public void testCalcularPrecio() {
        System.out.println("calcularPrecio");
        int idTipoTarifa = 0;
        int horas = 0;
        Tarifa expResult = null;
        Tarifa result = TarifaDAO.calcularPrecio(idTipoTarifa, horas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
