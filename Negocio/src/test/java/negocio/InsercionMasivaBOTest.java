package negocio;

import daos.IPersonaDAO;
import daos.PersonaDAO;
import entidades.PersonaEntidad;
import excepciones.NegocioException;
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
public class InsercionMasivaBOTest {

    @Mock
    private IPersonaDAO personaDAO;

    @InjectMocks
    private IInsercionMasivaBO insercionBO;

    public InsercionMasivaBOTest() {
        this.personaDAO = Mockito.mock(IPersonaDAO.class);
        this.insercionBO = new InsercionMasivaBO(this.personaDAO);
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
     * Prueba para el metodo insertarPersonas para la clase InsercionMasivaBO.
     * insertarPersonas() consiste en agregar 20 registros de PersonaEntidad en
     * el sistema.
     */
    @Test
    public void insertarPersonas_SeInsertanLas20Personas_ResultadoSatisfactorio() throws Exception {
        System.out.println("insertarPersonas");

        // arrange
        // act
        this.insercionBO.insertarPersonas();

        // assert
        Mockito.verify(this.personaDAO, Mockito.times(20)).insertarPersona(Mockito.any());
    }

    /**
     * Prueba del metodo crearPersonas de la clase InsercionMasivaBO. Crea y
     * devuelve una lista de 20 objetos PersonaEntidad.
     */
    @Test
    public void crearPersonas_SeCreaLaListaDe20PersonaEntidad_ListPersonaEntidad() throws Exception {
        System.out.println("crearPersonas");

        // arrange
        int registrosEsperados = 20;
        List<PersonaEntidad> result = null;

        // act
        result = this.insercionBO.crearPersonas();

        // assert
        assertEquals(registrosEsperados, result.size());
    }

    /**
     * Prueba para el metodo insertarPersonas para la clase InsercionMasivaBO.
     * Simula el comportamiento en caso de que ya esten registradas las 20
     * personas
     */
    @Test
    public void insertarPersonas_YaHayPersonasRegistradas_NegocioException() throws Exception {
        System.out.println("insertarPersonas - NegocioException");

        // arrange
        Mockito.when(this.personaDAO.hayPersonas()).thenReturn(true);

        // act y assert
        Exception exp = assertThrows(NegocioException.class, () -> this.insercionBO.insertarPersonas());
    }

    /**
     * Prueba del metodo crearPersonas de la clase InsercionMasivaBO. Crea y
     * Simula un error interno al crear la lista de personas
     */
    @Test
    public void crearPersonas_OcurreErrorAlCrearLaLista_NegocioException() throws Exception {
        System.out.println("crearPersonas - NegocioException");

        // volvemos a insercionBO un mock para poder arrojar una excepcion simulada
        IInsercionMasivaBO insercionBOSpy = Mockito.spy(insercionBO);

        Mockito.doThrow(new NegocioException("Lo sentimos, ocurrió un error. Inténtelo más tarde.")).when(insercionBOSpy).crearPersonas();

        NegocioException excepcion = assertThrows(NegocioException.class, () -> insercionBOSpy.crearPersonas());

        assertEquals("Lo sentimos, ocurrió un error. Inténtelo más tarde.", excepcion.getMessage());

    }

}
