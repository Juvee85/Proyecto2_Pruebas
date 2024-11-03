
package negocio;

import daos.IPersonaDAO;
import daos.ITramiteDAO;
import dtos.PersonaDTO;
import dtos.TramiteDTO;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.TramiteEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.persistence.NoResultException;
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

/**
 * Pruebas para la clase ConsultaHistorialBO que implementa de IConsultaHistorialBO
 * @author neri
 */
public class ConsultaHistorialBOTest {
    
    @Mock
    private ITramiteDAO tramiteDAO;
    
    @Mock
    private IPersonaDAO personaDAO;

    private IConsultaHistorialBO consultaHistorialBO;
    
    public ConsultaHistorialBOTest() {
        this.tramiteDAO = Mockito.mock(ITramiteDAO.class);
        this.personaDAO = Mockito.mock(IPersonaDAO.class);
        this.consultaHistorialBO = new ConsultaHistorialBO(this.personaDAO, this.tramiteDAO, 0);
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
     * Prueba del metodo obtenerPersonas de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonas_ObtieneLasPersonas_ListPersonaDTO() throws Exception {
        System.out.println("obtenerPersonas");
        
        // arrange
        int pagina = 1;
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("AME102010SSADF12");
        persona.setNombre("America");
        persona.setApellidoMaterno("Cardenas");
        persona.setApellidoPaterno("Lopez");
        persona.setDiscapacitado(false);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        
        PersonaDTO personaDTO = null;
        
        List<PersonaEntidad> expResult = Arrays.asList(persona);
        
        Mockito.when(this.personaDAO.obtenerPersonas(0, Paginacion.obtenerOffset(0, pagina))).thenReturn(expResult);
        
        // act
        List<PersonaDTO> result = this.consultaHistorialBO.obtenerPersonas(pagina);
        personaDTO = result.getFirst();
        
        // assert
        assertEquals(persona.getNombre(), personaDTO.getNombre());
        assertEquals(persona.getApellidoMaterno(), personaDTO.getApellidoMaterno());
        assertEquals(persona.getApellidoPaterno(), personaDTO.getApellidoPaterno());
        assertEquals(persona.getCurp(), personaDTO.getCurp());
        assertEquals(persona.getFechaNacimiento(), personaDTO.getFechaNacimiento());
        assertEquals(persona.getTelefono(), personaDTO.getTelefono());
    }
    
    /**
     * Prueba del metodo obtenerPersonas de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonas_NoSeEncontraronResultados_NegocioException() throws Exception {
        System.out.println("obtenerPersonas - No se encuentran resultados - NegocioException");
        
        // arrange
        int pagina = 1;
        
        Mockito.when(this.personaDAO.obtenerPersonas(0, Paginacion.obtenerOffset(0, pagina))).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerPersonas(pagina));
    }
    
    /**
     * Prueba del metodo obtenerPersonas de la clase ConsultaHistorialBO.
     *
    @Test
    public void obtenerPersonas_NoEncuentraPersonas_NegocioException() throws Exception {
        System.out.println("obtenerPersonas - NegocioException");
        
        // arrange
        int pagina = 1;
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerPersonas(pagina));
    }*/

    /**
     * Prueba del metodo obtenerPersonaPorCURP de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonaPorCURP_SeEncuentraPersonaPorCurp_PersonaDTO() throws Exception {
        System.out.println("obtenerPersonaPorCURP");
        
        // arrange
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        String curp = "PALP192199120AFGF";
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("AME102010SSADF12");
        persona.setNombre("Pedro Alberto");
        persona.setApellidoMaterno("Lopez");
        persona.setApellidoPaterno("Perez");
        persona.setDiscapacitado(true);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        
        // act
        PersonaDTO personaDTO = this.consultaHistorialBO.obtenerPersonaPorCURP(curp);
        
        // assert
        assertEquals(persona.getNombre(), personaDTO.getNombre());
        assertEquals(persona.getApellidoMaterno(), personaDTO.getApellidoMaterno());
        assertEquals(persona.getApellidoPaterno(), personaDTO.getApellidoPaterno());
        assertEquals(persona.getCurp(), personaDTO.getCurp());
        assertEquals(persona.getFechaNacimiento(), personaDTO.getFechaNacimiento());
        assertEquals(persona.getTelefono(), personaDTO.getTelefono());
    }
    
    /**
     * Prueba del metodo obtenerPersonaPorCURP de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonaPorCURP_NoSeEncuentraPersonaPorCurp_NegocioException() throws Exception {
        System.out.println("obtenerPersonaPorCURP - NegocioExcepcion");
        
        // arrange
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        String curp = "PALP192199120AFGF";
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerPersonaPorCURP(curp));
    }

    /**
     * Prueba para el metodo obtenerPersonasPorNombre de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonasPorNombre_SeObtienenPersonasPorNombre_ListPersonaDTO() throws Exception {
        System.out.println("obtenerPersonasPorNombre");
        
        // arrange
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        String nombre = "Alberto Montes";
        int pagina = 0;
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("AME102010SSADF12");
        persona.setNombre("Pedro Alberto");
        persona.setApellidoMaterno("Lopez");
        persona.setApellidoPaterno("Perez");
        persona.setDiscapacitado(true);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        PersonaDTO personaDTO = null;
        
        Mockito.when(this.personaDAO.buscarPorNombre(nombre, 0, Paginacion.obtenerOffset(0, pagina))).thenReturn(Arrays.asList(persona));
        
        // act
        List<PersonaDTO> result = this.consultaHistorialBO.obtenerPersonasPorNombre(nombre, pagina);
        personaDTO = result.getFirst();
        
        // assert
        assertEquals(persona.getNombre(), personaDTO.getNombre());
        assertEquals(persona.getApellidoMaterno(), personaDTO.getApellidoMaterno());
        assertEquals(persona.getApellidoPaterno(), personaDTO.getApellidoPaterno());
        assertEquals(persona.getCurp(), personaDTO.getCurp());
        assertEquals(persona.getFechaNacimiento(), personaDTO.getFechaNacimiento());
        assertEquals(persona.getTelefono(), personaDTO.getTelefono());
    }

    /**
     * Prueba para el metodo obtenerPersonasPorNombre de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonasPorNombre_NoSeObtienenResultadosPersonaNombre_NegocioException() throws Exception {
        System.out.println("obtenerPersonasPorNombre - NegocioException");
        
        // arrange
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        String nombre = "Alberto Montes";
        int pagina = 0;
        
        Mockito.when(this.personaDAO.buscarPorNombre(nombre, 0, Paginacion.obtenerOffset(0, pagina))).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerPersonasPorNombre(nombre, pagina));
    }
    
    /**
     * Prueba del metodo obtenerPersonasPorAnioNacimiento de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonasPorAnioNacimiento_ObtienePersonasPorAnioNacimiento_ListPersonaDTO() throws Exception {
        System.out.println("obtenerPersonasPorAnioNacimiento");
        
        // arrange
        int anio = 2024;
        int pagina = 0;
        
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("AME102010SSADF12");
        persona.setNombre("Pedro Alberto");
        persona.setApellidoMaterno("Lopez");
        persona.setApellidoPaterno("Perez");
        persona.setDiscapacitado(true);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        PersonaDTO personaDTO = null;
        
        List<PersonaEntidad> expResult = Arrays.asList(persona);
        
        Mockito.when(this.personaDAO.buscarPorAnioNacimiento(anio, 0, pagina)).thenReturn(expResult);
        
        // act
        List<PersonaDTO> result = this.consultaHistorialBO.obtenerPersonasPorAnioNacimiento(anio, pagina);
        personaDTO = result.getFirst();
        
        // assert
        assertEquals(persona.getNombre(), personaDTO.getNombre());
        assertEquals(persona.getApellidoMaterno(), personaDTO.getApellidoMaterno());
        assertEquals(persona.getApellidoPaterno(), personaDTO.getApellidoPaterno());
        assertEquals(persona.getCurp(), personaDTO.getCurp());
        assertEquals(persona.getFechaNacimiento(), personaDTO.getFechaNacimiento());
        assertEquals(persona.getTelefono(), personaDTO.getTelefono());
    }
    
    /**
     * Prueba del metodo obtenerPersonasPorAnioNacimiento de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerPersonasPorAnioNacimiento_NoObtieneNingunResultado_NegocioException() throws Exception {
        System.out.println("obtenerPersonasPorAnioNacimiento - NegocioException");
        
        // arrange
        int anio = 2024;
        int pagina = 0;
        
        Mockito.when(this.personaDAO.buscarPorAnioNacimiento(anio, 0, pagina)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerPersonasPorAnioNacimiento(anio, pagina));
    }

    /**
     * Prueba del metodo obtenerTramitesPorPersona de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerTramitesPorPersona_ObtieneLosTramitesDeLaPersona_ListTramiteDTO() throws Exception {
        System.out.println("obtenerTramitesPorPersona");
        
        // arrange
        String curp = "NEES010124SHH241";
        int pagina = 0;
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("AME102010SSADF12");
        persona.setNombre("Pedro Alberto");
        persona.setApellidoMaterno("Lopez");
        persona.setApellidoPaterno("Perez");
        persona.setDiscapacitado(true);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        
        
        LicenciaEntidad tramite = new LicenciaEntidad();
        tramite.setId(1l);
        tramite.setActiva(true);
        tramite.setCosto(100.50f);
        tramite.setPersona(persona);
        tramite.setTarifa(null);
        c.set(2020, 1, 10);
        tramite.setFechaEmision(c);
        
        TramiteEntidad tramitePtr = (TramiteEntidad) tramite;
        
        TramiteDTO tramiteDTO = null;
        
        List<TramiteEntidad> expResult = Arrays.asList(tramite);
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, 0, pagina)).thenReturn(expResult);
        
        // act
        List<TramiteDTO> result = this.consultaHistorialBO.obtenerTramitesPorPersona(curp, pagina);
        tramiteDTO = result.getFirst();
        
        String tipo = (tramite.getClass().equals(LicenciaEntidad.class)) ? "Licencia" : "Placas";
        
        // assert
        assertEquals(tramitePtr.getCosto(), Float.valueOf(tramiteDTO.getCosto()));
        assertEquals(tipo, tramiteDTO.getTipo());
    }
    
    /**
     * Prueba del metodo obtenerTramitesPorPersona de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerTramitesPorPersona_NoSeEncontroPersonaPorCURP_NegocioException() throws Exception {
        System.out.println("obtenerTramitesPorPersona - PersistenciaException('no se encontro la persona con CURP')");
        
        // arrange
        String curp = "NEES010124SHH241";
        int pagina = 0;
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("PALP102010SSADF12");
        persona.setNombre("Pedro Alberto");
        persona.setApellidoMaterno("Lopez");
        persona.setApellidoPaterno("Perez");
        persona.setDiscapacitado(true);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        
        LicenciaEntidad tramite = new LicenciaEntidad();
        tramite.setId(1l);
        tramite.setActiva(true);
        tramite.setCosto(100.50f);
        tramite.setPersona(persona);
        tramite.setTarifa(null);
        c.set(2020, 1, 10);
        tramite.setFechaEmision(c);
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenThrow(PersistenciaException.class);
        //Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, 0, pagina)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerTramitesPorPersona(curp, pagina));
    }
    
    /**
     * Prueba del metodo obtenerTramitesPorPersona de la clase ConsultaHistorialBO.
     */
    @Test
    public void obtenerTramitesPorPersona_NoSeEncontraronTramitesDeLaPersona_NegocioException() throws Exception {
        System.out.println("obtenerTramitesPorPersona - NegocioException('La persona no tiene tramites registrados')");
        
        // arrange
        String curp = "NEES010124SHH241";
        int pagina = 0;
        Calendar c = Calendar.getInstance();
        c.set(2004, 2, 10);
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp("PALP102010SSADF12");
        persona.setNombre("Pedro Alberto");
        persona.setApellidoMaterno("Lopez");
        persona.setApellidoPaterno("Perez");
        persona.setDiscapacitado(true);
        persona.setDetalle(Arrays.asList());
        persona.setRfc("10101010101010");
        persona.setTelefono("65501284923");
        persona.setFechaNacimiento(c);
        persona.setTramites(Arrays.asList());
        
        LicenciaEntidad tramite = new LicenciaEntidad();
        tramite.setId(1l);
        tramite.setActiva(true);
        tramite.setCosto(100.50f);
        tramite.setPersona(persona);
        tramite.setTarifa(null);
        c.set(2020, 1, 10);
        tramite.setFechaEmision(c);
        
        //Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenThrow(PersistenciaException.class);
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        Mockito.when(this.tramiteDAO.obtenerTramitesPorPersona(persona, 0, pagina)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.consultaHistorialBO.obtenerTramitesPorPersona(curp, pagina));
    }
}
