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
import entidades.RelacionVehiculoPersona;
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
     * Efectua el flujo de cuando se busca y no encuentra un auto por el numero
     * de serie
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

    /**
     * Efectua el flujo de la operacion de agregarPlacaNueva sin problemas...
     *
     * @throws PersistenciaException Si ocurre un error en DAO (no ocurrira)
     */
    @Test
    public void agregarPlacaNuevo_LaPlacaSeAgregaSinErrores_ResultadoSatisfactorio() throws PersistenciaException {
        System.out.println("agregarPlacaNuevo");

        // arrange
        String curp = "DABA01038SHSFS3LA1";

        PersonaEntidad persona = new PersonaEntidad();
        persona.setNombre("Diana");
        persona.setApellidoPaterno("Bastidas");
        persona.setApellidoMaterno("Baca");
        persona.setCurp(curp);
        persona.setRfc("19219248663");

        String numSerie = "1290231001235";

        AutomovilEntidad auto = new AutomovilEntidad();
        auto.setId(1L);
        auto.setColor("Verde");
        auto.setLinea("Corolla");
        auto.setMarca("Toyota");
        auto.setModelo("2015");
        auto.setNumeroSerie(numSerie);

        AutomovilDTO autoDTO = new AutomovilDTO(auto);

        Calendar fechaRecepcion = Calendar.getInstance();
        Calendar fechaEmision = Calendar.getInstance();

        TarifaPlacasEntidad tarifa = new TarifaPlacasEntidad();
        tarifa.setCosto(1000.0f);
        tarifa.setId(1l);
        tarifa.setTipo("Automóvil nuevo");

        PlacasEntidad placas = new PlacasEntidad();
        placas.setActiva(Boolean.TRUE);
        placas.setCosto(1000.0f);
        placas.setFechaEmision(fechaEmision);
        placas.setFechaRecepcion(fechaRecepcion);
        placas.setNumero("DAB-124");
        placas.setPersona(persona);
        placas.setVehiculo(auto);
        placas.setTarifa(tarifa);

        PlacasDTO placasDTO = new PlacasDTO(placas);

        // volvemos a registroPlacasBO un mock para poder devolver un valor simulado
        IRegistroPlacasBO registroPlacasBO_Spy = Mockito.spy(this.registroPlacasBO);

        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        Mockito.when(registroPlacasBO_Spy.convertirAutomovilDTO_Entidad(autoDTO)).thenReturn(auto);
        Mockito.when(registroPlacasBO_Spy.convertirPlacasDTO_Entidad(placasDTO)).thenReturn(placas);
        Mockito.when(this.tarifaPlacasDAO.buscarTarifa("Automóvil nuevo")).thenReturn(tarifa);
        Mockito.doNothing().when(this.automovilDAO).insertarAutomovil(auto);
        Mockito.doNothing().when(this.placasDAO).insertarPlacas(placas);

        // act
        registroPlacasBO_Spy.agregarPlacaNuevo(autoDTO, curp, placasDTO);

        // assert
        Mockito.verify(registroPlacasBO_Spy).agregarPlacaNuevo(autoDTO, curp, placasDTO);
    }

    /**
     * Convierte un AutomovilDTO a AutomovilEntidad sin problemas
     */
    @Test
    public void convertirAutomovilDTO_Entidad_ConvierteElAutoSinProblemas_AutomovilDTO() {
        System.out.println("convertirAutomovilDTO_Entidad");

        // arrange
        AutomovilEntidad expResult = new AutomovilEntidad();
        expResult.setNumeroSerie("SERIE");
        expResult.setMarca("MARCA");
        expResult.setLinea("LINEA");
        expResult.setColor("COLOR");
        expResult.setModelo("MODELO");

        AutomovilDTO autoDTO = new AutomovilDTO(expResult);

        // act
        AutomovilEntidad result = this.registroPlacasBO.convertirAutomovilDTO_Entidad(autoDTO);

        // assert
        assertEquals(expResult.getNumeroSerie(), result.getNumeroSerie());
        assertEquals(expResult.getMarca(), result.getMarca());
        assertEquals(expResult.getLinea(), result.getLinea());
        assertEquals(expResult.getColor(), result.getColor());
        assertEquals(expResult.getModelo(), result.getModelo());
    }

    /**
     * Convierte un PlacasDTO a PlacasEntidad sin problemas
     */
    @Test
    public void convertirPlacasDTO_Entidad_ConvierteLasPlacasSinProblemas_PlacasEntidad() {
        System.out.println("convertirPlacasDTO_Entidad");

        // arrange
        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaRecepcion = Calendar.getInstance();

        TarifaPlacasEntidad tarifa = new TarifaPlacasEntidad();
        tarifa.setCosto(1000.f);
        tarifa.setTipo("Automovil Nuevo");
        tarifa.setId(1l);

        PlacasEntidad expResult = new PlacasEntidad();
        expResult.setId(1l);
        expResult.setActiva(Boolean.TRUE);
        expResult.setCosto(1000.0f);
        expResult.setFechaEmision(fechaEmision);
        expResult.setFechaRecepcion(fechaRecepcion);
        expResult.setNumero("ACK-999");
        expResult.setTarifa(tarifa);

        PlacasDTO placasDTO = new PlacasDTO(expResult);

        Mockito.when(this.tarifaPlacasDAO.buscarTarifa(Mockito.anyString())).thenReturn(tarifa);

        // act
        PlacasEntidad result = this.registroPlacasBO.convertirPlacasDTO_Entidad(placasDTO);

        // act
        assertEquals(expResult.getCosto(), result.getCosto());
        assertEquals(expResult.getFechaEmision(), result.getFechaEmision());
        assertEquals(expResult.getFechaRecepcion(), result.getFechaRecepcion());
        assertEquals(expResult.getNumero(), result.getNumero());

    }

    /**
     * Busca una tarifa por tipo y la encuentra
     */
    @Test
    public void buscarTarifa_BuscaLaTarifaYLaEncuentra_TarifaPlacasDTO() {
        System.out.println("buscarTarifa");

        // arrange
        String tipo = "Automovil Nuevo";
        TarifaPlacasEntidad tarifaEntidad = new TarifaPlacasEntidad();
        tarifaEntidad.setTipo(tipo);
        tarifaEntidad.setCosto(500.0f);

        Mockito.when(tarifaPlacasDAO.buscarTarifa(tipo)).thenReturn(tarifaEntidad);

        // act
        TarifaPlacasDTO tarifaDTO = registroPlacasBO.buscarTarifa(tipo);

        // assert
        assertEquals(tipo, tarifaDTO.getTipo());
        assertEquals(tarifaEntidad.getCosto(), tarifaDTO.getCosto());
    }

    /**
     * Busca un auto por sus placas y lo encuentra
     *
     * @throws Exception
     */
    @Test
    public void buscarAutoPlacas_EncuentraAlAutoPorSusPlacas_AutomovilEntidad() throws Exception {
        System.out.println("buscarAutoPlacas");

        // Arrange
        String numPlacas = "ACK-999";
        String numSerie = "299243736511893";

        AutomovilEntidad autoEntidad = new AutomovilEntidad();
        autoEntidad.setNumeroSerie(numSerie);
        autoEntidad.setColor("Verde");
        autoEntidad.setMarca("Toyota");
        autoEntidad.setLinea("Corolla");
        autoEntidad.setModelo("2015");

        Mockito.when(placasDAO.buscarAutoPlacas(numPlacas)).thenReturn(autoEntidad);

        // act
        AutomovilDTO autoDTO = registroPlacasBO.buscarAutoPlacas(numPlacas);

        // assert
        assertEquals(autoEntidad.getNumeroSerie(), autoDTO.getNumSerie());
        assertEquals(autoEntidad.getColor(), autoDTO.getColor());
        assertEquals(autoEntidad.getMarca(), autoDTO.getMarca());
        assertEquals(autoEntidad.getLinea(), autoDTO.getLinea());
        assertEquals(autoEntidad.getModelo(), autoDTO.getModelo());
    }

    /**
     * Busca un auto por sus placas y no lo encuentra, por lo tanto arroja
     * excepcion
     *
     * @throws Exception
     */
    @Test
    public void buscarAutoPlacas_NoseEncuentraAlAutoPorSusPlacas_NegocioException() throws Exception {
        System.out.println("buscarAutoPlacas");

        // Arrange
        String numPlacas = "ACK-999";
        String numSerie = "299243736511893";

        AutomovilEntidad autoEntidad = new AutomovilEntidad();
        autoEntidad.setNumeroSerie(numSerie);
        autoEntidad.setColor("Verde");
        autoEntidad.setMarca("Toyota");
        autoEntidad.setLinea("Corolla");
        autoEntidad.setModelo("2015");

        Mockito.when(placasDAO.buscarAutoPlacas(numPlacas)).thenThrow(PersistenciaException.class);

        // act y assert
        Exception excepcion = assertThrows(NegocioException.class, () -> registroPlacasBO.buscarAutoPlacas(numPlacas));
    }

    /**
     * Efectua el flujo de cuando se desactiva una placa a partir del numero de
     * placas.
     */
    @Test
    public void desactivarPlacas_SeDesactivanLasPlacasCorrectamente_ResultadoSatisfactorio() {
        System.out.println("desactivarPlacas");

        // arrange
        String numPlacas = "ACK-123";

        PlacasEntidad placas = new PlacasEntidad();
        placas.setNumero(numPlacas);

        IRegistroPlacasBO registroPlacasBO_Spy = Mockito.spy(this.registroPlacasBO);
        Mockito.when(this.placasDAO.obtenerPlacas(numPlacas)).thenReturn(placas);
        Mockito.doNothing().when(this.placasDAO).desactivarPlacas(placas);

        // act
        registroPlacasBO_Spy.desactivarPlacas(numPlacas);

        // assert
        Mockito.verify(registroPlacasBO_Spy).desactivarPlacas(numPlacas);
    }

    /**
     * Se efectua el flujo en donde se quiere obtener las ultimas placas del
     * sistema
     */
    @Test
    public void obtenerUltimasPlacas_SeObtienenLasUltimasPlacas_PlacasDTO() {
        System.out.println("obtenerUltimasPlacas");

        // arrange
        String numSerie = "1289837801892";

        Calendar fechaEmision = Calendar.getInstance();
        Calendar fechaRecepcion = Calendar.getInstance();

        TarifaPlacasEntidad tarifa = new TarifaPlacasEntidad();
        tarifa.setCosto(1000.f);
        tarifa.setId(1l);
        tarifa.setTipo("Automovil Nuevo");

        PlacasEntidad placas = new PlacasEntidad();
        placas.setActiva(Boolean.TRUE);
        placas.setCosto(1000.f);
        placas.setFechaEmision(fechaEmision);
        placas.setFechaRecepcion(fechaRecepcion);
        placas.setNumero("ACK-101");
        placas.setTarifa(tarifa);

        PlacasDTO expResult = new PlacasDTO(placas);

        Mockito.when(this.placasDAO.obtenerUltimasPlacas(numSerie)).thenReturn(placas);

        // act
        PlacasDTO result = this.registroPlacasBO.obtenerUltimasPlacas(numSerie);

        // assert
        assertEquals(expResult.getCosto(), result.getCosto());
        assertEquals(expResult.getFechaEmision(), result.getFechaEmision());
        assertEquals(expResult.getFechaRecepcion(), result.getFechaRecepcion());
        assertEquals(expResult.getNumero(), result.getNumero());
    }

    /**
     * Efectua el flujo del registro de una placa para automovil usado sin problemas
     * @throws PersistenciaException 
     */
    @Test
    public void agregarPlacaUsado_SeAgregaLaPlacaParaAutomovilUsadoCorrectamente_ResultadoSatisfactorio() throws PersistenciaException {
        System.out.println("agregarPlacaUsado");

        // Arrange
        String numSerie = "81278098190870912";
        String curp = "RAOT1T4N199378SHSLA1";
        PlacasDTO placasDTO = new PlacasDTO();

        PersonaEntidad persona = new PersonaEntidad();
        persona.setCurp(curp);

        AutomovilEntidad autoEntidad = new AutomovilEntidad();
        autoEntidad.setNumeroSerie(numSerie);

        TarifaPlacasEntidad tarifaPlacasEntidad = new TarifaPlacasEntidad();
        tarifaPlacasEntidad.setTipo("Automóvil usado");
        tarifaPlacasEntidad.setCosto(800.0f);

        Mockito.when(personaDAO.buscarPorCurp(curp)).thenReturn(persona);
        Mockito.when(automovilDAO.obtenerAutomovil(numSerie)).thenReturn(autoEntidad);
        Mockito.when(relacionVehPerDAO.existeDetalle(Mockito.any(RelacionVehiculoPersona.class))).thenReturn(false);
        Mockito.when(tarifaPlacasDAO.buscarTarifa("Automóvil usado")).thenReturn(tarifaPlacasEntidad);

        // Act
        registroPlacasBO.agregarPlacaUsado(numSerie, curp, placasDTO);

        // Assert
        Mockito.verify(personaDAO).buscarPorCurp(curp);
        Mockito.verify(automovilDAO).obtenerAutomovil(numSerie);
        Mockito.verify(relacionVehPerDAO).existeDetalle(Mockito.any(RelacionVehiculoPersona.class));
        Mockito.verify(relacionVehPerDAO).insertarDetalle(Mockito.any(RelacionVehiculoPersona.class));
        Mockito.verify(tarifaPlacasDAO).buscarTarifa("Automóvil usado");
        Mockito.verify(placasDAO).insertarPlacas(Mockito.any(PlacasEntidad.class));
    }
}
