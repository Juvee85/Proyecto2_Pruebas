package negocio;

import daos.AutomovilDAO;
import daos.IAutomovilDAO;
import daos.ILicenciaDAO;
import daos.IPersonaDAO;
import daos.IPlacasDAO;
import daos.IRelacionVehiculoPersonaDAO;
import daos.ITarifaLicenciaDAO;
import daos.ITarifaPlacasDAO;
import daos.LicenciaDAO;
import daos.PersonaDAO;
import daos.PlacasDAO;
import daos.RelacionVehiculoPersonaDAO;
import daos.TarifaLicenciaDAO;
import daos.TarifaPlacasDAO;
import dtos.AutomovilDTO;
import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.PlacasDTO;
import dtos.TarifaLicenciaDTO;
import dtos.TarifaPlacasDTO;
import entidades.AutomovilEntidad;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.PlacasEntidad;
import entidades.TarifaLicenciaEntidad;
import entidades.TarifaPlacasEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.Calendar;
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
public class RegistroPlacasBOTest {

    @Mock
    private IPersonaDAO personaDAO;

    @Mock
    private ITarifaLicenciaDAO tarifaLicenciaDAO;

    @Mock
    private ILicenciaDAO licenciaDAO;

    @Mock
    private ITarifaPlacasDAO tarifaPlacasDAO;

    @Mock
    private IPlacasDAO placasDAO;

    @Mock
    private IAutomovilDAO automovilDAO;

    @Mock
    private IRelacionVehiculoPersonaDAO relacionVehPerDAO;

    @InjectMocks
    private IRegistroPlacasBO registroPlacasBO;

    public RegistroPlacasBOTest() {
        this.personaDAO = Mockito.mock(IPersonaDAO.class);
        this.tarifaLicenciaDAO = Mockito.mock(ITarifaLicenciaDAO.class);
        this.licenciaDAO = Mockito.mock(ILicenciaDAO.class);
        this.tarifaPlacasDAO = Mockito.mock(ITarifaPlacasDAO.class);
        this.placasDAO = Mockito.mock(IPlacasDAO.class);
        this.automovilDAO = Mockito.mock(IAutomovilDAO.class);
        this.relacionVehPerDAO = Mockito.mock(IRelacionVehiculoPersonaDAO.class);

        this.registroPlacasBO = new RegistroPlacasBO(
                personaDAO,
                tarifaLicenciaDAO,
                licenciaDAO,
                tarifaPlacasDAO,
                placasDAO,
                automovilDAO,
                relacionVehPerDAO
        );
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
     * Efectua el flujo esperado de una busqueda de persona junto con su
     * licencia.
     */
    @Test
    public void buscarPersonaCurp_SeEncuentraLaPersonaJuntoConSuLicencia_PersonaDTO_LicenciaDTO() throws Exception {
        System.out.println("buscarPersonaCurp");

        // arrange
        String curp = "APET1T4N1204SHHRS13";

        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(1l);
        persona.setCurp(curp);
        persona.setDiscapacitado(false);
        persona.setNombre("Arely");
        persona.setApellidoMaterno("Topete");
        persona.setApellidoPaterno("Perez");
        persona.setTelefono("6455900000");
        persona.setRfc("1234567890");

        Calendar fechaEmision = Calendar.getInstance();

        TarifaLicenciaEntidad tarifa = new TarifaLicenciaEntidad("2030-03-10", 900.f, 600.f);

        LicenciaEntidad lic = new LicenciaEntidad();
        lic.setActiva(Boolean.TRUE);
        lic.setCosto(900.0f);
        lic.setFechaEmision(fechaEmision);
        lic.setPersona(persona);
        lic.setTarifa(tarifa);

        PersonaDTO personaDTO = new PersonaDTO(persona);
        LicenciaDTO licDTO = new LicenciaDTO(lic);

        PersonaDTO personaEsperada = personaDTO;
        LicenciaDTO licenciaEsperada = licDTO;

        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        Mockito.when(this.licenciaDAO.buscarUltimaLicencia(persona)).thenReturn(lic);

        // act
        Object[] result = this.registroPlacasBO.buscarPersonaCurp(curp);

        PersonaDTO personaResult = (PersonaDTO) result[0];
        LicenciaDTO licenciaResult = (LicenciaDTO) result[1];

        // assert
        assertEquals(personaEsperada.getCurp(), personaResult.getCurp());
        assertEquals(personaEsperada.getNombre(), personaResult.getNombre());
        assertEquals(personaEsperada.getApellidoMaterno(), personaResult.getApellidoMaterno());
        assertEquals(personaEsperada.getApellidoPaterno(), personaResult.getApellidoPaterno());
        assertEquals(personaEsperada.getTelefono(), personaResult.getTelefono());
        assertEquals(personaEsperada.getRfc(), personaResult.getRfc());
        assertEquals(personaEsperada.isDiscapacitado(), personaResult.isDiscapacitado());

        assertEquals(licenciaEsperada.getCosto(), licenciaResult.getCosto());
        assertEquals(licenciaEsperada.getFechaEmision(), licenciaResult.getFechaEmision());

        assertEquals(licenciaEsperada.getTarifa().getCostoDiscapacitado(), licenciaResult.getTarifa().getCostoDiscapacitado());
        assertEquals(licenciaEsperada.getTarifa().getCostoNormal(), licenciaResult.getTarifa().getCostoNormal());
        assertEquals(licenciaEsperada.getTarifa().getVigencia(), licenciaResult.getTarifa().getVigencia());
    }

    /**
     * Efectua el flujo de una busqueda, en donde no se encuentra a la persona
     * con la curp dada, lo que dispara una excepcion PersistenciaException que
     * pasa a disparar una excepcion NegocioException
     */
    @Test
    public void buscarPersonaCurp_NoSeEncontroPersonaPorCURP_NegocioException() throws Exception {
        System.out.println("buscarPersonaCurp - No se encuentra la persona");

        // arrange
        String curp = "APET1T4N1204SHHRS13";

        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenThrow(PersistenciaException.class);

        // act y assert
        NegocioException excepcion = assertThrows(NegocioException.class, () -> this.registroPlacasBO.buscarPersonaCurp(curp));
    }

    /**
     * Efectua el flujo deseado para el metodo Validar requisitos licencia, en
     * donde se valida que la licencia este activa.
     *
     * @throws Exception Si la licencia es nula o si no esta activa
     */
    @Test
    public void validarRequisitosLicencia_LaLicenciaEstaActiva_ResultadoSatisfactorio() throws Exception {
        System.out.println("validarRequisitosLicencia");

        // arrange
        LicenciaDTO licencia = new LicenciaDTO();
        licencia.setActiva(true);

        // act
        this.registroPlacasBO.validarRequisitosLicencia(licencia);

        // assert
    }

    /**
     * Efectua el flujo de cuando no se ingresa una licencia al metodo
     * (licenciaDTO es null), lo que causa una excepcion NegocioException
     *
     * @throws Exception Cuando licenciaDTO es null o esta inactiva
     */
    @Test
    public void validarRequisitosLicencia_NoSeIngresoLicenciaEsNull_NegocioException() throws Exception {
        System.out.println("validarRequisitosLicencia - LicenciaDTO ingresada es NULL");

        // arrange
        LicenciaDTO licencia = null;
        String mensajeEsperado = "El solicitante no tiene licencia.";

        // act
        Exception excepcion = assertThrows(NegocioException.class, () -> this.registroPlacasBO.validarRequisitosLicencia(licencia));

        // assert
        assertEquals(mensajeEsperado, excepcion.getMessage());
    }

    /**
     * Efectua el flujo de cuando la licencia no es null pero esta misma esta
     * inactiva, lo que dispara una excepcion NegocioException
     *
     * @throws Exception Cuando licenciaDTO es null o esta inactiva
     */
    @Test
    public void validarRequisitosLicencia_LaLicenciaNoEsValidaEstaInactiva_NegocioException() throws Exception {
        System.out.println("validarRequisitosLicencia");

        // arrange
        LicenciaDTO licencia = new LicenciaDTO();
        licencia.setActiva(false);
        String mensajeEsperado = "La licencia del solicitante no está vigente.";

        /// act
        Exception excepcion = assertThrows(NegocioException.class, () -> this.registroPlacasBO.validarRequisitosLicencia(licencia));

        // assert
        assertEquals(mensajeEsperado, excepcion.getMessage());
    }

    /**
     * Efectua la conversion sin problemas
     */
    @Test
    public void convertirLicenciaDTO_Entidad_SeConvierteLicenciaDTO_LicenciaEntidad() {
        System.out.println("convertirLicenciaDTO_Entidad");

        // arrange
        Calendar fechaEmision = Calendar.getInstance();

        TarifaLicenciaDTO tarifa = new TarifaLicenciaDTO("2030-01-20", 1000.f, 700.f);

        LicenciaDTO licenciaDTO = new LicenciaDTO();
        licenciaDTO.setActiva(true);
        licenciaDTO.setCosto(900.0f);
        licenciaDTO.setFechaEmision(fechaEmision);
        licenciaDTO.setTarifa(tarifa);

        TarifaLicenciaEntidad tarifaLicencia = new TarifaLicenciaEntidad();
        tarifaLicencia.setCostoDiscapacitado(700.f);
        tarifaLicencia.setCostoNormal(1000.f);
        tarifaLicencia.setVigencia("2030-01-20");

        Mockito.when(this.tarifaLicenciaDAO.buscarTarifa(tarifaLicencia.getVigencia())).thenReturn(tarifaLicencia);

        // act
        LicenciaEntidad result = this.registroPlacasBO.convertirLicenciaDTO_Entidad(licenciaDTO);

        // assert
        assertEquals(licenciaDTO.getCosto(), result.getCosto());
        assertEquals(licenciaDTO.getFechaEmision(), result.getFechaEmision());
        assertEquals(licenciaDTO.getTarifa().getVigencia(), result.getTarifa().getVigencia());
        assertEquals(licenciaDTO.getTarifa().getCostoNormal(), result.getTarifa().getCostoNormal());
        assertEquals(licenciaDTO.getTarifa().getCostoDiscapacitado(), result.getTarifa().getCostoDiscapacitado());
    }

    /**
     * Genera la placa sin problemas
     */
    @Test
    public void generarPlaca_SeGeneraLaPlacaSinProblemas_PlacasDTO() {
        System.out.println("generarPlaca");

        // arrange
        String tarifa = "TARIFA PRUEBA";
        TarifaPlacasEntidad tarifaPlacas = new TarifaPlacasEntidad();
        tarifaPlacas.setCosto(2000.f);
        tarifaPlacas.setId(1l);
        tarifaPlacas.setTipo("PRUEBA");

        Mockito.when(this.tarifaPlacasDAO.buscarTarifa(tarifa)).thenReturn(tarifaPlacas);

        PlacasDTO expResult = new PlacasDTO(
                "", // cadena vacia para verificar que se genera la placa
                null, // omitimos...
                Calendar.getInstance(), // Obtenemos la fecha y hora actual.
                tarifaPlacas.getCosto(), // Costo de las placas.
                true, // Indicamos que serán las placas activas.
                // Convertimos la tarifa a DTO.
                new TarifaPlacasDTO(tarifaPlacas)
        );

        // act
        PlacasDTO result = this.registroPlacasBO.generarPlaca(tarifa);

        // assert
        assertTrue(!result.equals(expResult.getNumero()));
        assertEquals(expResult.getCosto(), result.getCosto());
        assertEquals(expResult.getFechaEmision().get(Calendar.DAY_OF_MONTH), result.getFechaEmision().get(Calendar.DAY_OF_MONTH));
        assertEquals(expResult.getFechaEmision().get(Calendar.MONTH), result.getFechaEmision().get(Calendar.MONTH));
        assertEquals(expResult.getFechaEmision().get(Calendar.YEAR), result.getFechaEmision().get(Calendar.YEAR));
        assertEquals(expResult.getFechaRecepcion(), result.getFechaRecepcion());
        assertEquals(expResult.getTarifa().getCosto(), result.getTarifa().getCosto());
        assertEquals(expResult.getTarifa().getTipo(), result.getTarifa().getTipo());
    }

    /**
     * Genera un numero de placa completamente aleatorio con el formato AAA-111,
     * en este caso lo hace sin problemas. En donde AAA puede ser cualquier
     * letra del alfabeto, y los 1s pueden ser cualquiera de los numeros
     * naturales
     */
    @Test
    public void generarNumeroPlaca_SeGeneraLaPlacaSinProblemas_True() {
        System.out.println("generarNumeroPlaca");

        // arrange
        String regex = "^[A-Za-z]{3}-\\d{3}$";

        // act
        String numPlaca = this.registroPlacasBO.generarNumeroPlaca();
        boolean result = numPlaca.matches(regex);

        // assert
        assertTrue(result);
    }

    /**
     * Efectua el flujo de cuando se busca y encuentra un auto por el numero de
     * serie
     */
    @Test
    public void buscarAutomovil_SeEncuentraElAutomovil_AutomovilDTO() {
        System.out.println("buscarAutomovil");

        // arrange
        String numSerie = "299243736511893";

        AutomovilEntidad auto = new AutomovilEntidad();
        auto.setId(1L);
        auto.setColor("Verde");
        auto.setLinea("Corolla");
        auto.setMarca("Toyota");
        auto.setModelo("2015");
        auto.setNumeroSerie(numSerie);

        Mockito.when(this.automovilDAO.obtenerAutomovil(numSerie)).thenReturn(auto);
        
        // act
        AutomovilDTO result = this.registroPlacasBO.buscarAutomovil(numSerie);

        // assert
        assertEquals(auto.getLinea(), result.getLinea());
        assertEquals(auto.getMarca(), result.getMarca());
        assertEquals(auto.getModelo(), result.getModelo());
        assertEquals(auto.getNumeroSerie(), result.getNumSerie());
    }
    
    /**
     * Efectua el flujo de cuando se busca y no encuentra un auto por el numero de
     * serie
     */
    @Test
    public void buscarAutomovil_NoSeEncontroElAutomovil_Null() {
        System.out.println("buscarAutomovil");

        // arrange
        String numSerie = "299243736511893";

        Mockito.when(this.automovilDAO.obtenerAutomovil(numSerie)).thenReturn(null);
        
        // act
        AutomovilDTO result = this.registroPlacasBO.buscarAutomovil(numSerie);

        // assert
        assertNull(result);
    }

    /*
    @Test
    public void testAgregarPlacaNuevo() {
        System.out.println("agregarPlacaNuevo");
        
        // arrange
        AutomovilDTO autoDTO = new AutomovilDTO("SERIE", "MARCA", "LINEA", "COLOR", "MODELO");
        String curp = "APET1T4N10HHRSLA1";
        
        Calendar fechaRecepcion = Calendar.getInstance();
        Calendar fechaEmision = Calendar.getInstance();
        
        PlacasDTO placasDTO = new PlacasDTO();
        placasDTO.setActiva(true);
        placasDTO.setCosto(1000.0f);
        placasDTO.setFechaEmision(fechaEmision);
        
        // act
        instance.agregarPlacaNuevo(autoDTO, curp, placasDTO);
        
        // assert
    }

    
    @Test
    public void testConvertirAutomovilDTO_Entidad() {
        System.out.println("convertirAutomovilDTO_Entidad");
        AutomovilDTO autoDTO = null;
        RegistroPlacasBO instance = new RegistroPlacasBO();
        AutomovilEntidad expResult = null;
        AutomovilEntidad result = instance.convertirAutomovilDTO_Entidad(autoDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testConvertirPlacasDTO_Entidad() {
        System.out.println("convertirPlacasDTO_Entidad");
        PlacasDTO placasDTO = null;
        RegistroPlacasBO instance = new RegistroPlacasBO();
        PlacasEntidad expResult = null;
        PlacasEntidad result = instance.convertirPlacasDTO_Entidad(placasDTO);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testBuscarTarifa() {
        System.out.println("buscarTarifa");
        String tipo = "";
        RegistroPlacasBO instance = new RegistroPlacasBO();
        TarifaPlacasDTO expResult = null;
        TarifaPlacasDTO result = instance.buscarTarifa(tipo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testBuscarAutoPlacas() throws Exception {
        System.out.println("buscarAutoPlacas");
        String numPlacas = "";
        RegistroPlacasBO instance = new RegistroPlacasBO();
        AutomovilDTO expResult = null;
        AutomovilDTO result = instance.buscarAutoPlacas(numPlacas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testDesactivarPlacas() {
        System.out.println("desactivarPlacas");
        String numPlacas = "";
        RegistroPlacasBO instance = new RegistroPlacasBO();
        instance.desactivarPlacas(numPlacas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    @Test
    public void testObtenerUltimasPlacas() {
        System.out.println("obtenerUltimasPlacas");
        String numSerie = "";
        RegistroPlacasBO instance = new RegistroPlacasBO();
        PlacasDTO expResult = null;
        PlacasDTO result = instance.obtenerUltimasPlacas(numSerie);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testAgregarPlacaUsado() {
        System.out.println("agregarPlacaUsado");
        String numSerie = "";
        String curp = "";
        PlacasDTO placasDTO = null;
        RegistroPlacasBO instance = new RegistroPlacasBO();
        instance.agregarPlacaUsado(numSerie, curp, placasDTO);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
