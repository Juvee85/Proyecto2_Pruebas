/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package negocio;

import daos.ITramiteDAO;
import dtos.ReporteDTO;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.PlacasEntidad;
import entidades.TramiteEntidad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import utilidades.Paginacion;
import utilidades.TiposTramite;

/**
 *
 * @author neri
 */
public class ReporteTramitesBOTest {

    @Mock
    private ITramiteDAO tramiteDAO;

    @InjectMocks
    private IReporteTramitesBO reportesBO;

    public ReporteTramitesBOTest() {
        this.tramiteDAO = Mockito.mock(ITramiteDAO.class);
        this.reportesBO = new ReporteTramitesBO(this.tramiteDAO, 0);

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
     * Efectua el flujo de cuando se quiere generar un reporte, el reporse se
     * genera sin probnlemas
     */
    @Test
    public void obtenerReporteTramites_SeGeneraElReporteCorrectamente_ReporteDTO() throws Exception {
        System.out.println("obtenerReporteTramites");

        // Arrange
        List<TiposTramite> tramitesSeleccionados = Arrays.asList(TiposTramite.LICENCIA, TiposTramite.PLACAS);
        String nombre = "Juan Perez";
        Calendar fechaInicial = Calendar.getInstance();
        Calendar fechaFinal = Calendar.getInstance();
        fechaFinal.add(Calendar.DAY_OF_MONTH, 7);
        int pagina = 1;
        int numElementos = 0;

        // Datos de entidad simulada
        TramiteEntidad tramiteEntidad = new PlacasEntidad();
        tramiteEntidad.setId(1L); // Ejemplo de atributo
        tramiteEntidad.setCosto(1000.0f);
        tramiteEntidad.setFechaEmision(fechaInicial);

        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(1l);
        persona.setCurp("APET1T4N13452SHHRSLA3");
        persona.setDiscapacitado(false);
        persona.setNombre("Arely");
        persona.setApellidoMaterno("Torres");
        persona.setApellidoPaterno("Perez");
        persona.setTelefono("6455900000");
        persona.setRfc("1234567890");

        tramiteEntidad.setPersona(persona);

        List<TramiteEntidad> tramiteEntidades = List.of(tramiteEntidad);

        ReporteDTO expResult = new ReporteDTO(tramiteEntidad);

        Mockito.when(tramiteDAO.obtenerReporte(
                Mockito.anyString(),
                Mockito.eq(fechaInicial),
                Mockito.eq(fechaFinal),
                Mockito.anyList(),
                Mockito.eq(numElementos),
                Mockito.eq(Paginacion.obtenerOffset(numElementos, pagina))
        )).thenReturn(tramiteEntidades);

        // Act
        List<ReporteDTO> resultado = this.reportesBO.obtenerReporteTramites(tramitesSeleccionados, nombre, fechaInicial, fechaFinal, pagina);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(expResult.getNombre(), resultado.getFirst().getNombre());
        assertEquals(expResult.getNombre(), resultado.getFirst().getNombre());
        assertEquals(expResult.getNombre(), resultado.getFirst().getNombre());
        assertEquals(expResult.getNombre(), resultado.getFirst().getNombre());

        Mockito.verify(tramiteDAO).obtenerReporte(
                Mockito.anyString(),
                Mockito.eq(fechaInicial),
                Mockito.eq(fechaFinal),
                Mockito.anyList(),
                Mockito.eq(0),
                Mockito.eq(Paginacion.obtenerOffset(0, pagina))
        );
    }

    @Test
    void tiposSeleccionados_TiposSeleccionadosConAmbosTipos_2_LicenciaClass_PlacasClass() {
        
        // arrange
        List<TiposTramite> tramitesSeleccionados = Arrays.asList(TiposTramite.LICENCIA, TiposTramite.PLACAS);
        
        IReporteTramitesBO reportesBO_Spy = Mockito.spy(this.reportesBO);
        
        // act
        List<Class> resultado = reportesBO_Spy.tiposSeleccionados(tramitesSeleccionados);

        // assert
        assertEquals(2, resultado.size()); 
        assertTrue(resultado.contains(LicenciaEntidad.class));
        assertTrue(resultado.contains(PlacasEntidad.class));
        
        Mockito.verify(reportesBO_Spy).tiposSeleccionados(tramitesSeleccionados);
    }

    @Test
    void tiposSeleccionados_TiposSeleccionadosConSoloLicencia_2_LicenciaClass() {
        // arrange
        List<TiposTramite> tramitesSeleccionados = Arrays.asList(TiposTramite.LICENCIA);
        
        IReporteTramitesBO reportesBO_Spy = Mockito.spy(this.reportesBO);
        
        // act
        List<Class> resultado = reportesBO_Spy.tiposSeleccionados(tramitesSeleccionados);

        
        // assert
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(LicenciaEntidad.class));
        
        Mockito.verify(reportesBO_Spy).tiposSeleccionados(tramitesSeleccionados);
    }

    @Test
    void tiposSeleccionados_tiposSeleccionadosConSoloPlacas_1_PlacasClass() {
        
        // arrange
        List<TiposTramite> tramitesSeleccionados = Arrays.asList(TiposTramite.PLACAS);
        
        IReporteTramitesBO reportesBO_Spy = Mockito.spy(this.reportesBO);
        
        // act
        List<Class> resultado = reportesBO_Spy.tiposSeleccionados(tramitesSeleccionados);

        // assert
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(PlacasEntidad.class));
        
        Mockito.verify(reportesBO_Spy).tiposSeleccionados(tramitesSeleccionados);
    }

    @Test
    void tiposSeleccioandos_TiposSeleccionadosListaVacia_0() {
        
        // arrange
        List<TiposTramite> tramitesSeleccionados = new ArrayList<>();
        
        IReporteTramitesBO reportesBO_Spy = Mockito.spy(this.reportesBO);
        
        // act
        List<Class> resultado = reportesBO_Spy.tiposSeleccionados(tramitesSeleccionados);

        // assert
        assertEquals(0, resultado.size());
        
        Mockito.verify(reportesBO_Spy).tiposSeleccionados(tramitesSeleccionados);
    }
}
