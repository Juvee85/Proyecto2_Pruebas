/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.TarifaPlacasEntidad;
import java.util.Arrays;
import javax.persistence.NoResultException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Pruebas para la clase TarifaPlacasDAO que implementa ITarifaPlacasDAO
 * @author neri
 */
public class TarifaPlacasDAOTest {
    @Mock
    private ITarifaPlacasDAO tarifaPlacasDAO;
    
    public TarifaPlacasDAOTest() {
        this.tarifaPlacasDAO = Mockito.mock(ITarifaPlacasDAO.class);
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
     * Prueba del metodo buscarTarifa de la clase TarifaPlacasDAO.
     */
    @Test
    public void buscarTarifa_ObtieneLaTarifaPorTipo_TarifaPlacasEntidad() {
        System.out.println("buscarTarifa");
        
        // arrange
        String tipo = "Tipo Prueba 1";
        TarifaPlacasEntidad expResult = new TarifaPlacasEntidad();
        expResult.setId(1l);
        expResult.setCosto(500.0f);
        expResult.setTipo("Tipo Prueba 1");
        expResult.setPlacas(Arrays.asList());
        
        Mockito.when(this.tarifaPlacasDAO.buscarTarifa(tipo)).thenReturn(expResult);
        
        // act
        TarifaPlacasEntidad result = this.tarifaPlacasDAO.buscarTarifa(tipo);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba del metodo buscarTarifa de la clase TarifaPlacasDAO.
     */
    @Test
    public void buscarTarifa_NoObtieneLaTarifaPorTipo_NoResultException() {
        System.out.println("buscarTarifa - NoResultException");
        
        // arrange
        String tipo = "Tipo Prueba 1";
        
        Mockito.when(this.tarifaPlacasDAO.buscarTarifa(tipo)).thenThrow(NoResultException.class);
        
        // act y assert
        Exception exp = assertThrows(NoResultException.class, () -> this.tarifaPlacasDAO.buscarTarifa(tipo));
    }
}
