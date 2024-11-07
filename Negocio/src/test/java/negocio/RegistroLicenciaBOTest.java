/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package negocio;

import daos.ILicenciaDAO;
import daos.IPersonaDAO;
import daos.ITarifaLicenciaDAO;
import daos.LicenciaDAO;
import daos.PersonaDAO;
import daos.TarifaLicenciaDAO;
import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.TarifaLicenciaDTO;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.TarifaLicenciaEntidad;
import excepciones.NegocioException;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author neri
 */
public class RegistroLicenciaBOTest {
    
    @Mock
    private IPersonaDAO personaDAO;
    @Mock
    private ITarifaLicenciaDAO tarifaLicenciaDAO;
    @Mock
    private ILicenciaDAO licenciaDAO;
    
    @InjectMocks
    private IRegistroLicenciaBO registroBO;
    
    public RegistroLicenciaBOTest() {
        this.personaDAO = Mockito.mock(IPersonaDAO.class);
        this.tarifaLicenciaDAO = Mockito.mock(ITarifaLicenciaDAO.class);
        this.licenciaDAO = Mockito.mock(ILicenciaDAO.class);
        this.registroBO = new RegistroLicenciaBO(this.personaDAO, this.tarifaLicenciaDAO, this.licenciaDAO);
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
     * Test of buscarPersonaCurp method, of class RegistroLicenciaBO.
     */
    @Test
    public void buscarPersonaCurp_EncuentraPersonaPorCURP_PersonaDTO() throws Exception {
        System.out.println("buscarPersonaCurp");
        
        // arrange
        String curp = "AEMO12335881HSSR31";
        PersonaEntidad expResult = new PersonaEntidad();
        expResult.setCurp(curp);
        expResult.setDiscapacitado(Boolean.TRUE);
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(expResult);
        
        // act
        PersonaDTO result = this.registroBO.buscarPersonaCurp(curp);
        
        // assert
        assertEquals(expResult.getCurp(), result.getCurp());
    }
    
    /**
     * Test of buscarPersonaCurp method, of class RegistroLicenciaBO.
     */
    @Test
    public void buscarPersonaCurp_NoSePudoEncontrarPersonaPorCURP_NegocioException() throws Exception {
        System.out.println("buscarPersonaCurp - No se encontro - NegocioException");
        
        // arrange
        String curp = "AEMO12335881HSSR31";
        PersonaEntidad expResult = new PersonaEntidad();
        expResult.setCurp(curp);
        expResult.setDiscapacitado(Boolean.TRUE);
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenThrow(PersistenciaException.class);
        
        // act y assert 
        NegocioException excepcion = assertThrows(NegocioException.class, () -> this.registroBO.buscarPersonaCurp(curp));
    }

    /**
     * Test of validarRequisitos method, of class RegistroLicenciaBO.
     */
    @Test
    public void validarRequisitos_LosDatosSonValidosNoHayExcepcion_ResultadoSatisfactorio() throws Exception {
        System.out.println("validarRequisitos");
        
        // arrange
        Calendar c = Calendar.getInstance();
            // 14 febrero del 2004
        c.set(2004, 2, 14);
        
        String curp = "AMEO0120034SHSHHSH12";
        PersonaDTO persona = new PersonaDTO(curp);
        persona.setRfc("190901290329928");
        persona.setFechaNacimiento(c);
        persona.setTelefono("12345678910");
        
        // act
        this.registroBO.validarRequisitos(persona);
        
        // assert
    }
    
    /**
     * Test of validarRequisitos method, of class RegistroLicenciaBO.
     */
    @Test
    public void validarRequisitos_SolicitanteNoDioTelefono_NegocioException() throws Exception {
        System.out.println("validarRequisitos");
        
        // arrange
        Calendar c = Calendar.getInstance();
            // 14 febrero del 2004
        c.set(2004, 2, 14);
        
        String curp = "AMEO0120034SHSHHSH12";
        PersonaDTO persona = new PersonaDTO(curp);
        persona.setRfc("190901290329928");
        persona.setFechaNacimiento(c);
        //persona.setTelefono("12345678910");

        // act
        NegocioException excepcion = assertThrows(NegocioException.class, () -> this.registroBO.validarRequisitos(persona));

        // assert
        assertTrue(excepcion.getMessage().contains("El solicitante no cuenta con un número de teléfono."));
    }
    
    /**
     * Test of validarRequisitos method, of class RegistroLicenciaBO.
     */
    @Test
    public void validarRequisitos_SolicitanteNoDioSuRFC_NegocioException() throws Exception {
        System.out.println("validarRequisitos");
        
        // arrange
        Calendar c = Calendar.getInstance();
            // 14 febrero del 2004
        c.set(2004, 2, 14);
        
        String curp = "AMEO0120034SHSHHSH12";
        PersonaDTO persona = new PersonaDTO(curp);
        //persona.setRfc("190901290329928");
        persona.setFechaNacimiento(c);
        persona.setTelefono("12345678910");

        // act
        NegocioException excepcion = assertThrows(NegocioException.class, () -> this.registroBO.validarRequisitos(persona));

        // assert
        assertTrue(excepcion.getMessage().contains("El solicitante no cuenta con RFC."));
    }
    
    /**
     * Test of validarRequisitos method, of class RegistroLicenciaBO.
     */
    @Test
    public void validarRequisitos_SolicitanteEsMenorDeEdad_NegocioException() throws Exception {
        System.out.println("validarRequisitos");
        
        // arrange
        Calendar c = Calendar.getInstance();
            // 14 febrero del 2004
        c.set(2012, 2, 14);
        
        String curp = "AMEO0120034SHSHHSH12";
        PersonaDTO persona = new PersonaDTO(curp);
        persona.setRfc("190901290329928");
        persona.setFechaNacimiento(c);
        persona.setTelefono("12345678910");

        // act
        NegocioException excepcion = assertThrows(NegocioException.class, () -> this.registroBO.validarRequisitos(persona));

        // assert
        assertTrue(excepcion.getMessage().contains("El solicitante es menor de edad."));
    }

    /**
     * Test of esMenorDeEdad method, of class RegistroLicenciaBO.
     */
    @Test
    public void esMenorDeEdad_LaPersonaEsDetectadaComoMenorDeEdad_True() {
        System.out.println("esMenorDeEdad");
        
        // arrange
        Calendar c = Calendar.getInstance();
            // 14 febrero del 2014
        c.set(2014, 2, 14);
        
        String curp = "AMEO0120034SHSHHSH12";
        PersonaDTO persona = new PersonaDTO(curp);
        persona.setFechaNacimiento(c);
        
        boolean expResult = true;
        
        // act
        boolean result = this.registroBO.esMenorDeEdad(persona);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Test of esMenorDeEdad method, of class RegistroLicenciaBO.
     */
    @Test
    public void esMenorDeEdad_LaPersonaEsDetectadaComoMayorDeEdad_False() {
        System.out.println("esMenorDeEdad - False");
        
        // arrange
        Calendar c = Calendar.getInstance();
            // 14 febrero del 2004
        c.set(2004, 2, 14);
        
        String curp = "AMEO0120034SHSHHSH12";
        PersonaDTO persona = new PersonaDTO(curp);
        persona.setFechaNacimiento(c);
        
        boolean expResult = false;
        
        // act
        boolean result = this.registroBO.esMenorDeEdad(persona);
        
        // assert
        assertEquals(expResult, result);
    }

    /**
     * Test of generarLicencia method, of class RegistroLicenciaBO.
     */
    @Test
    public void generarLicencia_GeneraLaLicenciaConCosteNormalSinErrores_LicenciaDTO() {
        System.out.println("generarLicencia");
        
        // arrange
        PersonaDTO persona = new PersonaDTO("LOLP138838SHHSR13");
        persona.setDiscapacitado(false);
        
        float costoNormal = 900.0f;
        float costoDiscapacitado = 600.0f;
        String vencimiento = "2030-04-01";
        
        TarifaLicenciaDTO tarifa = new TarifaLicenciaDTO(vencimiento, costoNormal, costoDiscapacitado);
        
        float expResult = costoNormal;
        
        // act
        LicenciaDTO result = this.registroBO.generarLicencia(persona, tarifa);
        
        // assert
        assertEquals(expResult, result.getCosto());
    }
    
    
    /**
     * Test of generarLicencia method, of class RegistroLicenciaBO.
     */
    @Test
    public void generarLicencia_GeneraLaLicenciaConCostoParaDiscapacitado_LicenciaDTO() {
        System.out.println("generarLicencia - discapacitado");
        
        // arrange
        PersonaDTO persona = new PersonaDTO("LOLP138838SHHSR13");
        persona.setDiscapacitado(true);
        
        float costoNormal = 900.0f;
        float costoDiscapacitado = 600.0f;
        String vencimiento = "2030-04-01";
        
        TarifaLicenciaDTO tarifa = new TarifaLicenciaDTO(vencimiento, costoNormal, costoDiscapacitado);
        
        float expResult = costoDiscapacitado;
        
        // act
        LicenciaDTO result = this.registroBO.generarLicencia(persona, tarifa);
        
        // assert
        assertEquals(expResult, result.getCosto());
    }

    /**
     * Test of buscarTarifasLicencia method, of class RegistroLicenciaBO.
     * TODO: CASOS ALTERNOS PENDIENTES
     */
    @Test
    public void buscarTarifasLicencia_SeObtienenTodasLasTarifasLicencia_ListTarifaLicenciaDTO() {
        System.out.println("buscarTarifasLicencia");
        
        // arrange
        List<TarifaLicenciaEntidad> expResult = Arrays.asList(
                new TarifaLicenciaEntidad("2025-01-10", 900.f, 600.f),
                new TarifaLicenciaEntidad("2030-01-10", 1000.f, 700.f),
                new TarifaLicenciaEntidad("2031-01-10", 800.f, 500.f)
                
        );    
        
        Mockito.doReturn(expResult).when(this.tarifaLicenciaDAO).obtenerTarifas();
        
        // act
        List<TarifaLicenciaDTO> result = this.registroBO.buscarTarifasLicencia();
        
        // assert
        assertEquals(expResult.size(), result.size());
    }

    
    
    /**
     * Test of agregarLicencia method, of class RegistroLicenciaBO.
     * TODO: CASOS ALTERNOS PENDIENTES
     */
    @Test
    public void agregarLicencia_AgregaCorrectamenteLaLicencia_ResultadoSatisfactorio() throws PersistenciaException {
        System.out.println("agregarLicencia");
        
        // arrange
        String curp = "AMOE10213LLSSSF13";
        
        PersonaEntidad persona = new PersonaEntidad();
        persona.setCurp(curp);
        
        TarifaLicenciaEntidad tarifaLicencia = new TarifaLicenciaEntidad("2034-01-10", 900.0f, 600.0f);
        
        LicenciaEntidad licencia = new LicenciaEntidad();
        licencia.setId(1l);
        licencia.setCosto(900.0f);
        licencia.setPersona(persona);
        licencia.setActiva(Boolean.TRUE);
        licencia.setTarifa(tarifaLicencia);
        
        LicenciaDTO licenciaDTO = new LicenciaDTO();
        licenciaDTO.setActiva(true);
        licenciaDTO.setCosto(900.0f);
        licenciaDTO.setFechaEmision(Calendar.getInstance());
        licenciaDTO.setTarifa(new TarifaLicenciaDTO(tarifaLicencia));
        
        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        Mockito.when(this.licenciaDAO.buscarUltimaLicencia(persona)).thenReturn(licencia);
        Mockito.doNothing().when(this.licenciaDAO).desactivarLicencia(licencia);
        
        // volvemos a registroBO un mock para poder devolver un valor simulado
        IRegistroLicenciaBO registroBO_Spy = Mockito.spy(this.registroBO);
        
        Mockito.when(this.tarifaLicenciaDAO.buscarTarifa(licenciaDTO.getTarifa().getVigencia())).thenReturn(tarifaLicencia);
        Mockito.doReturn(licencia).when(registroBO_Spy).convertirLicenciaDTO_Entidad(licenciaDTO);
       
        Mockito.doNothing().when(this.licenciaDAO).insertarLicencia(Mockito.any());
        
        // act
        registroBO_Spy.agregarLicencia(curp, licenciaDTO);
        
        // assert
        Mockito.verify(registroBO_Spy).agregarLicencia(curp, licenciaDTO);
    }

    /**
     * Test of convertirPersonaDTO_Entidad method, of class RegistroLicenciaBO.
     */
    @Test
    public void convertirPersonaDTO_Entidad_DevuelveCorrectamenteUnPersonaEntidad_PersonaEntidad() {
        System.out.println("convertirPersonaDTO_Entidad");
        
        // arrange
        String curp = "ALEO1245HFGSD34113";
        PersonaDTO personaDTO = new PersonaDTO(curp);
        personaDTO.setRfc("3232126761125");
        personaDTO.setNombre("Alejandro");
        personaDTO.setApellidoMaterno("Perez");
        personaDTO.setApellidoPaterno("Perez");
        personaDTO.setDiscapacitado(true);
        personaDTO.setFechaNacimiento(Calendar.getInstance());
        personaDTO.setTelefono("64550012413");
        
        PersonaEntidad expResult = new PersonaEntidad();
        
        expResult.setCurp(curp);
        expResult.setRfc("3232126761125");
        expResult.setNombre("Alejandro");
        expResult.setApellidoMaterno("Perez");
        expResult.setApellidoPaterno("Perez");
        expResult.setDiscapacitado(true);
        expResult.setFechaNacimiento(Calendar.getInstance());
        expResult.setTelefono("64550012413");
        
        // act
        PersonaEntidad result = this.registroBO.convertirPersonaDTO_Entidad(personaDTO);
        
        // assert
        assertEquals(expResult.getRfc(), result.getRfc());
        assertEquals(expResult.getNombre(), result.getNombre());
        assertEquals(expResult.getApellidoMaterno(), result.getApellidoMaterno());
        assertEquals(expResult.getApellidoPaterno(), result.getApellidoPaterno());
        assertEquals(expResult.getCurp(), result.getCurp());
        assertEquals(expResult.getTelefono(), result.getTelefono());
        assertEquals(expResult.isDiscapacitado(), result.isDiscapacitado());
        
    }

    /**
     * Test of convertirLicenciaDTO_Entidad method, of class RegistroLicenciaBO.
     */
    @Test
    public void convertirLicenciaDTO_Entidad_DevuelveCorrectamenteUnLicenciaEntidad() {
        System.out.println("convertirLicenciaDTO_Entidad");
        
        // arrange
        String curp = "AMOE10213LLSSSF13";
        
        PersonaEntidad persona = new PersonaEntidad();
        persona.setCurp(curp);
        
        TarifaLicenciaEntidad tarifaLicencia = new TarifaLicenciaEntidad("2034-01-10", 900.0f, 600.0f);
        
        Calendar c = Calendar.getInstance();
        
        LicenciaEntidad expResult = new LicenciaEntidad();
        expResult.setId(1l);
        expResult.setCosto(900.0f);
        expResult.setPersona(persona);
        expResult.setActiva(Boolean.TRUE);
        expResult.setTarifa(tarifaLicencia);
        expResult.setFechaEmision(c);
        
        LicenciaDTO licenciaDTO = new LicenciaDTO();
        licenciaDTO.setActiva(true);
        licenciaDTO.setCosto(900.0f);
        licenciaDTO.setFechaEmision(c);
        licenciaDTO.setTarifa(new TarifaLicenciaDTO(tarifaLicencia));
        
        Mockito.when(this.tarifaLicenciaDAO.buscarTarifa(Mockito.any())).thenReturn(Mockito.any());
        
        // act
        LicenciaEntidad result = this.registroBO.convertirLicenciaDTO_Entidad(licenciaDTO);
        
        // assert
        assertEquals(expResult.getCosto(), result.getCosto());
        assertEquals(expResult.getFechaEmision(), result.getFechaEmision());
        assertEquals(expResult.getTarifa(), expResult.getTarifa());        
    } 
}
