/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.PersonaEntidad;
import entidades.TramiteEntidad;
import excepciones.PersistenciaException;
import java.util.Calendar;
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
 * Pruebas pra la clase TramiteDAO que implementa de ITramiteDAO
 * @author neri
 */
public class TramiteDAOTest {
    
    @Mock
    private ITramiteDAO tramiteDAO;
    
    public TramiteDAOTest() {
        this.tramiteDAO = Mockito.mock(ITramiteDAO.class);
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
     * Prueba para el metodo obtenerTramitesPorPersona de la clase TramiteDAO.
     */
    @Test
    public void testObtenerTramitesPorPersona() throws Exception {
        System.out.println("obtenerTramitesPorPersona");
        
        // arrange
        PersonaEntidad persona = null;
        int limite = 0;
        int offset = 0;
        List<TramiteEntidad> expResult = null;
        Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset)).thenReturn(expResult);
        
        // act
        List<TramiteEntidad> result = this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerTramitesPorPersona de la clase TramiteDAO.
     */
    @Test
    public void obtenerTramitesPorPersona_NoObtieneNingunTramitePersona_PersistenciaException() throws Exception {
        System.out.println("obtenerTramitesPorPersona - PersistenciaException");
        
        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(1l);
        persona.setCurp("NEES0101024AS");
        int limite = 0;
        int offset = 0;
        
        Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset));
    }

    /**
     * Prueba para el metodo obtenerReporte de la clase TramiteDAO.
     */
    @Test
    public void testObtenerReporte_6args() throws Exception {
        System.out.println("obtenerReporte");
        
        // arrange
        String nombre = "";
        Calendar fechaInicial = null;
        Calendar fechaFin = null;
        List<Class> tipos = null;
        int limite = 0;
        int offset = 0;
        TramiteDAO instance = new TramiteDAO();
        List<TramiteEntidad> expResult = null;
        
        // act
        List<TramiteEntidad> result = instance.obtenerReporte(nombre, fechaInicial, fechaFin, tipos, limite, offset);
        
        // assert
        assertEquals(expResult, result);
    }

    /**
     * Test of obtenerReporte method, of class TramiteDAO.
     */
    @Test
    public void testObtenerReporte_4args() throws Exception {
        System.out.println("obtenerReporte");
        String nombre = "";
        Calendar fechaInicial = null;
        Calendar fechaFin = null;
        List<Class> tipos = null;
        TramiteDAO instance = new TramiteDAO();
        List<TramiteEntidad> expResult = null;
        List<TramiteEntidad> result = instance.obtenerReporte(nombre, fechaInicial, fechaFin, tipos);
        assertEquals(expResult, result);
    }
    
}
