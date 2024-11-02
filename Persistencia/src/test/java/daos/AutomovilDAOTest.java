/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.AutomovilEntidad;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Pruebas para la clase AutomovilDAO que immplementa IAutomovilDAO
 * @author neri
 */
public class AutomovilDAOTest {
    
    @Mock
    private IAutomovilDAO autoDAO;
    
    public AutomovilDAOTest() {
        this.autoDAO = Mockito.mock(IAutomovilDAO.class);
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
     * Prueba para el metodo estaRegistrado de la clase AutomovilDAO.
     */
    @Test
    public void estaRegistrado_AutoRegistrado_True() {
        System.out.println("estaRegistrado");
        
        // arrange
        String numSerie = "1122334455";
        boolean expResult = true;
        Mockito.when(this.autoDAO.estaRegistrado(numSerie)).thenReturn(expResult);
        
        // act
        boolean result = this.autoDAO.estaRegistrado(numSerie);
        
        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba para el metodo obtenerAutomovil de la clase AutomovilDAO.
     */
    @Test
    public void obtenerAutomovil_AutomovilEncontrado_AutomovilEntidad() {
        System.out.println("obtenerAutomovil");
        
        // arrange
        String numSerie = "1122334455";
        AutomovilEntidad expResult = new AutomovilEntidad();
        expResult.setNumeroSerie(numSerie);
        expResult.setId(1l);
        expResult.setColor("Amarillo");
        expResult.setMarca("BMW");
        Mockito.when(this.autoDAO.obtenerAutomovil(numSerie)).thenReturn(expResult);
        
        // act
        AutomovilEntidad result = this.autoDAO.obtenerAutomovil(numSerie);
        
        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba para el metodo insertarAutomovil de la clase AutomovilDAO.
     */
    @Test
    public void InsertarAutomovil_AutomovilRegistrado_ResultadoSatisfactorio() {
        System.out.println("insertarAutomovil");
        
        // arrange
        String numSerie = "1122334455";
        AutomovilEntidad auto = new AutomovilEntidad();
        auto.setNumeroSerie(numSerie);
        auto.setId(1l);
        auto.setColor("Amarillo");
        auto.setMarca("BMW");
        
        // act
        this.autoDAO.insertarAutomovil(auto);
        
        // assert
    }
    
}
