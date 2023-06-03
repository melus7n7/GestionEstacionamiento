/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javafxsistemaestacionamientojets.modelo.dao;

import javafxsistemaestacionamientojets.modelo.pojo.Usuario;
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
public class UsuarioDAOIT {
    
    public UsuarioDAOIT() {
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
     * Test of verificarSesion method, of class UsuarioDAO.
     */
    @Test
    public void testVerificarSesion() {
        System.out.println("verificarSesion");
        String usuario = "melus38";
        String contrasena = "123";
        Usuario expResult = new Usuario();
        expResult.setCodigoRespuesta(Constantes.OPERACION_EXITOSA);
        Usuario result = UsuarioDAO.verificarSesion(usuario, contrasena);
        assertEquals(expResult.getCodigoRespuesta(), result.getCodigoRespuesta());
    }
    
}
