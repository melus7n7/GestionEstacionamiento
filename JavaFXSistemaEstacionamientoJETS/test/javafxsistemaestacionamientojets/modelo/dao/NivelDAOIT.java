
package javafxsistemaestacionamientojets.modelo.dao;

import javafxsistemaestacionamientojets.modelo.pojo.Nivel;
import javafxsistemaestacionamientojets.modelo.pojo.NivelRespuesta;
import javafxsistemaestacionamientojets.utils.Constantes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class NivelDAOIT {
    
    public NivelDAOIT() {
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
     * Test of obtenerNiveles method, of class NivelDAO.
     */
    @Test
    public void testObtenerNiveles() {
        System.out.println("Obtener Niveles del Estacionamiento");
        NivelRespuesta expResult = new NivelRespuesta();
        expResult.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        NivelRespuesta result = NivelDAO.obtenerNiveles();
        assertEquals(expResult.getCodigoRespuesta(), result.getCodigoRespuesta());
    }

    /**
     * Test of obtenerCajonesNivel method, of class NivelDAO.
     */
    @Test
    public void testObtenerCajonesNivel() {
        System.out.println("Obtener Cajones de un Nivel del estacionamiento");
        int numeroNivel = 100;
        Nivel expResult = new Nivel();
        expResult.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Nivel result = NivelDAO.obtenerCajonesNivel(numeroNivel);
        //assertEquals(expResult.getCodigoRespuesta(), result.getCodigoRespuesta());
        assertTrue("Prueba fallida en obtener cajones de un nivel",expResult.getCodigoRespuesta() == result.getCodigoRespuesta());
    }
    
}
