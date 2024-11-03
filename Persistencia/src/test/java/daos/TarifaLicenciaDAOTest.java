/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.TarifaLicenciaEntidad;
import java.util.Arrays;
import java.util.List;
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
 * Pruebas para la clase TarifaLicenciaDAOTest que implementa ITarifaLicenciaDAOTest
 * @author neri
 */
public class TarifaLicenciaDAOTest {

    @Mock
    private ITarifaLicenciaDAO tarifaLicenciaDAO;

    public TarifaLicenciaDAOTest() {
        this.tarifaLicenciaDAO = Mockito.mock(ITarifaLicenciaDAO.class);
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
     * Prueba para el metodo obtenerTarifas de la clase TarifaLicenciaDAO.
     */
    @Test
    public void obtenerTarifas_ObtieneUnaListaDeTarifas_ListTarifaLicenciaEntidad() {
        System.out.println("obtenerTarifas");

        // arrange
        TarifaLicenciaEntidad tl1 = new TarifaLicenciaEntidad();
        tl1.setId(1l);
        tl1.setCostoDiscapacitado(500.f);
        tl1.setCostoNormal(800.0f);
        tl1.setVigencia("2030-01-10");
        tl1.setLicencias(Arrays.asList());

        TarifaLicenciaEntidad tl2 = new TarifaLicenciaEntidad();
        tl2.setId(2L);
        tl2.setCostoDiscapacitado(600.f);
        tl2.setCostoNormal(900.0f);
        tl2.setVigencia("2025-05-15");
        tl2.setLicencias(Arrays.asList());

        TarifaLicenciaEntidad tl3 = new TarifaLicenciaEntidad();
        tl3.setId(3L);
        tl3.setCostoDiscapacitado(450.f);
        tl3.setCostoNormal(750.0f);
        tl3.setVigencia("2024-11-30");
        tl3.setLicencias(Arrays.asList());

        List<TarifaLicenciaEntidad> expResult = Arrays.asList(tl1, tl2, tl3);

        Mockito.when(this.tarifaLicenciaDAO.obtenerTarifas()).thenReturn(expResult);
        
        // act
        List<TarifaLicenciaEntidad> result = this.tarifaLicenciaDAO.obtenerTarifas();

        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerTarifas de la clase TarifaLicenciaDAO.
     */
    @Test
    public void obtenerTarifas_ObtieneUnaListaDeTarifas_NoResultException() {
        System.out.println("obtenerTarifas - NoResultException");

        // arrange
        Mockito.when(this.tarifaLicenciaDAO.obtenerTarifas()).thenThrow(NoResultException.class);
        
        // act y assert
        Exception exp = assertThrows(NoResultException.class, () -> this.tarifaLicenciaDAO.obtenerTarifas());
    }

    /**
     * Prueba del metodo buscarTarifa de la clase TarifaLicenciaDAO.
     */
    @Test
    public void buscarTarifa_SeEncuentraLaTarifa_TarifaLicenciaEntidad() {
        System.out.println("buscarTarifa");
        
        // arrange
        String vigencia = "2030-01-10";
        TarifaLicenciaEntidad expResult = new TarifaLicenciaEntidad();
        expResult.setId(1l);
        expResult.setCostoDiscapacitado(1000.f);
        expResult.setCostoNormal(1200.0f);
        expResult.setLicencias(Arrays.asList());
        expResult.setVigencia(vigencia);
        
        Mockito.when(this.tarifaLicenciaDAO.buscarTarifa(vigencia)).thenReturn(expResult);
        
        // act
        TarifaLicenciaEntidad result = this.tarifaLicenciaDAO.buscarTarifa(vigencia);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba del metodo buscarTarifa de la clase TarifaLicenciaDAO.
     */
    @Test
    public void buscarTarifa_NoSeEncuentraLaTarifa_NoResultException() {
        System.out.println("buscarTarifa - NoResultException");
        
        // arrange
        String vigencia = "2030-01-10";
        
        Mockito.when(this.tarifaLicenciaDAO.buscarTarifa(vigencia)).thenThrow(NoResultException.class);
        
        // act y assert
        Exception exp = assertThrows(NoResultException.class, () -> this.tarifaLicenciaDAO.buscarTarifa(vigencia));
    }
}
