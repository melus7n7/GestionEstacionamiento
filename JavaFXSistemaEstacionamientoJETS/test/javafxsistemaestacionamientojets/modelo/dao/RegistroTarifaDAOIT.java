/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.dao;

import java.util.ArrayList;
import javafxsistemaestacionamientojets.modelo.pojo.Registro;
import javafxsistemaestacionamientojets.modelo.pojo.Tarifa;
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
public class RegistroTarifaDAOIT {
    
    public RegistroTarifaDAOIT() {
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
     * Test of insertarRegistroTarifa method, of class RegistroTarifaDAO.
     */
    @Test
    public void testInsertarRegistroTarifa() {
        System.out.println("insertarRegistroTarifa");
        Tarifa tarifa = new Tarifa();
        tarifa.setIdTarifa(1);
        ArrayList<Tarifa> tarifas = new ArrayList();
        tarifas.add(tarifa);
        Registro registro = new Registro();
        registro.setIdRegistro(0);
        registro.setTarifas(tarifas);
        int expResult = Constantes.OPERACION_EXITOSA;
        int result = RegistroTarifaDAO.insertarRegistroTarifa(registro);
        //assertEquals(expResult, result);
        assertTrue("Prueba fallida en insertar una tarifa a un registro",expResult == result);
    }
    
}
