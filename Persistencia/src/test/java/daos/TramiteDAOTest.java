
package daos;

import entidades.PersonaEntidad;
import entidades.TramiteEntidad;
import excepciones.PersistenciaException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
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
    public void obtenerTramitesPorPersona_ObtieneTramitesPorPersona_ListTramiteEntidad() throws Exception {
        System.out.println("obtenerTramitesPorPersona");
        
        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(1l);
        persona.setCurp("NEES040410100SS12");
        int limite = 0;
        int offset = 0;
        List<TramiteEntidad> expResult = Arrays.asList();
        Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset)).thenReturn(expResult);
        
        // act
        List<TramiteEntidad> result = this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerTramitesPorPersona de la clase TramiteDAO.
     */
    @Test
    public void obtenerTramitesPorPersona_NoPudoObtenerTramitesPorPersona_PersistenciaException() throws Exception {
        System.out.println("obtenerTramitesPorPersona - PersistenciaException");
        
        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(1l);
        persona.setCurp("NEES040410100SS12");
        int limite = 0;
        int offset = 0;
        
        Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.tramiteDAO.obtenerTramitesPorPersona(persona, limite, offset));
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
    public void obtenerReporte_6args_ObtieneReporteTramites_ListTramiteEntidad() throws Exception {
        System.out.println("obtenerReporte");
        
        // arrange
        Calendar c = Calendar.getInstance();
        String nombre = "Reporte de Prueba";
        c.set(2010, 1, 1);
        Calendar fechaInicial = (Calendar) c.clone();
        Calendar fechaFin = (Calendar) c.clone();
        List<Class> tipos = null;
        int limite = 0;
        int offset = 0;
        List<TramiteEntidad> expResult = Arrays.asList();
        Mockito.when(this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos, limite, offset)).thenReturn(expResult);
        
        // act
        List<TramiteEntidad> result = this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos, limite, offset);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerReporte de la clase TramiteDAO.
     */
    @Test
    public void obtenerReporte_6args_NoPudoObtenerReporteTramites_PersistenciaException() throws Exception {
        System.out.println("obtenerReporte - PersistenciaException");
        
        // arrange
        Calendar c = Calendar.getInstance();
        String nombre = "Reporte de Prueba";
        c.set(2010, 1, 1);
        Calendar fechaInicial = (Calendar) c.clone();
        c.set(2020, 1, 20);
        Calendar fechaFin = (Calendar) c.clone();
        List<Class> tipos = null;
        int limite = 0;
        int offset = 0;
        
        Mockito.when(this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos, limite, offset)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos, limite, offset));
    }

    /**
     * Prueba del metodo obtenerReporte de la clase TramiteDAO.
     */
    @Test
    public void obtenerReporte_4args_ObtieneReporteTarifas_ListTarifaEntidad() throws Exception {
        System.out.println("obtenerReporte");

        // arrange
        Calendar c = Calendar.getInstance();
        String nombre = "Reporte de Prueba";
        c.set(2010, 1, 1);
        Calendar fechaInicial = (Calendar) c.clone();
        c.set(2020, 1, 20);
        Calendar fechaFin = (Calendar) c.clone();
        List<Class> tipos = null;
        
        List<TramiteEntidad> expResult = Arrays.asList();
        
        // act
        List<TramiteEntidad> result = this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba del metodo obtenerReporte de la clase TramiteDAO.
     */
    @Test
    public void obtenerReporte_4args_NoPudoObtenerReporteTarifas_PersistenciaException() throws Exception {
        System.out.println("obtenerReporte - PersistenciaException");

        // arrange
        Calendar c = Calendar.getInstance();
        String nombre = "Reporte de Prueba";
        c.set(2010, 1, 1);
        Calendar fechaInicial = (Calendar) c.clone();
        c.set(2020, 1, 20);
        Calendar fechaFin = (Calendar) c.clone();
        List<Class> tipos = null;
        
        Mockito.when(this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.tramiteDAO.obtenerReporte(nombre, fechaInicial, fechaFin, tipos));
    }
}
