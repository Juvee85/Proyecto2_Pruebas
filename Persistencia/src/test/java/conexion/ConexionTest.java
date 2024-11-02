/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author Saul Neri
 */
public class ConexionTest {
    
    @Mock
    private IConexion conexion;
    
    public ConexionTest() {
        conexion = Mockito.mock(IConexion.class);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of crearConexion method, of class Conexion.
     */
    @Test
    public void crearConexion_DevuelveEntityManager_RetornoCorrecto() {
        System.out.println("crearConexion");
        
        // arrange
        EntityManager result;
        Mockito.when(this.conexion.crearConexion()).thenReturn(null);
        
        // act
        result = conexion.crearConexion();
        
        // assert
        assertNull(result, "El entity manager debe ser null");
    }
    
}
