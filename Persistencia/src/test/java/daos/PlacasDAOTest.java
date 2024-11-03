/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.AutomovilEntidad;
import entidades.PlacasEntidad;
import excepciones.PersistenciaException;
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
 * Pruebas para la clase PlacasDAO que implementa de IPlacasDAO
 *
 * @author neri
 */
public class PlacasDAOTest {

    @Mock
    private IPlacasDAO placasDAO;

    public PlacasDAOTest() {
        this.placasDAO = Mockito.mock(IPlacasDAO.class);
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
     * Prueba para el metodo existePlaca de la clase PlacasDAO.
     */
    @Test
    public void existePlaca_ExisteLaPlacaConElNumeroDado_True() {
        System.out.println("existePlaca");

        // arrange
        String numPlaca = "ABS-10-31";
        boolean expResult = true;
        Mockito.when(this.placasDAO.existePlaca(numPlaca)).thenReturn(expResult);

        // act
        boolean result = this.placasDAO.existePlaca(numPlaca);

        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba para el metodo existePlaca de la clase PlacasDAO.
     */
    @Test
    public void existePlaca_NoExisteLaPlacaBuscada_False() {
        System.out.println("existePlaca - False");

        // arrange
        String numPlaca = "ABS-10-31";
        boolean expResult = false;
        Mockito.when(this.placasDAO.existePlaca(numPlaca)).thenReturn(expResult);

        // act
        boolean result = this.placasDAO.existePlaca(numPlaca);

        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba para el metodo insertarPlacas de la clase PlacasDAO.
     */
    @Test
    public void insertarPlacas_SeInsertaLaPlacaCorrectamente_ResultadoSatisfactorio() {
        System.out.println("insertarPlacas");

        // arrange
        PlacasEntidad placas = new PlacasEntidad();
        placas.setId(1l);
        placas.setNumero("ABS-10-31");
        placas.setCosto(450.0f);

        // act
        this.placasDAO.insertarPlacas(placas);
        
        // assert
        Mockito.verify(this.placasDAO).insertarPlacas(placas);
    }

    /**
     * Prueba para el metodo buscarAutoPlacas de la clase PlacasDAO.
     */
    @Test
    public void buscarAutoPlacas_EncuentraElAutoPorSusPlacas_AutomovilEntidad() throws Exception {
        System.out.println("buscarAutoPlacas");

        // arrange
        String numPlacas = "AMX-13-75";
        AutomovilEntidad expResult = new AutomovilEntidad();
        expResult.setId(1L);
        expResult.setNumeroSerie("VW123456789");
        expResult.setMarca("Volkswagen");
        expResult.setColor("Rojo");
        expResult.setLinea("Golf");
        expResult.setModelo("2022");

        Mockito.when(this.placasDAO.buscarAutoPlacas(numPlacas)).thenReturn(expResult);

        // act
        AutomovilEntidad result = this.placasDAO.buscarAutoPlacas(numPlacas);

        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba para el metodo buscarAutoPlacas de la clase PlacasDAO.
     */
    @Test
    public void buscarAutoPlacas_NoEncuentraElAutoPorSusPlacas_PersistenciaException() throws Exception {
        System.out.println("buscarAutoPlacas - PersistenciaException");

        // arrange
        String numPlacas = "AMX-13-75";
        AutomovilEntidad expResult = new AutomovilEntidad();
        expResult.setId(1L);
        expResult.setNumeroSerie("1020A12401A");
        expResult.setMarca("Ford");
        expResult.setColor("Negro");
        expResult.setLinea("Mustang");
        expResult.setModelo("2023");

        Mockito.when(this.placasDAO.buscarAutoPlacas(numPlacas)).thenThrow(PersistenciaException.class);

        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.placasDAO.buscarAutoPlacas(numPlacas));
    }

    /**
     * Prueba para el metodo obtenerUltimasPlacas de la clase PlacasDAO.
     */
    @Test
    public void obtenerUltimasPlacas_ObtieneLasUltimasPlacas_PlacasEntidad() {
        System.out.println("obtenerUltimasPlacas");
        
        // arrange
        String numSerie = "";;
        PlacasEntidad expResult = new PlacasEntidad();
        expResult.setId(1l);
        expResult.setActiva(true);
        expResult.setCosto(2400.50f);
        expResult.setNumero("AMX-13-75");
        
        Mockito.when(this.placasDAO.obtenerUltimasPlacas(numSerie)).thenReturn(expResult);
        
        // act
        PlacasEntidad result = this.placasDAO.obtenerUltimasPlacas(numSerie);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerUltimasPlacas de la clase PlacasDAO.
     */
    @Test
    public void obtenerUltimasPlacas_NoSeObtienenLasUltimasPlacas_NoResultException() {
        System.out.println("obtenerUltimasPlacas - No se obtienen las placas");
        
        // arrange
        String numSerie = "AMX-13-85";
        
        Mockito.when(this.placasDAO.obtenerUltimasPlacas(numSerie)).thenThrow(NoResultException.class);
        
        // act y assert
        Exception exp = assertThrows(NoResultException.class, () -> this.placasDAO.obtenerUltimasPlacas(numSerie));
    }

    /**
     * Prueba para el metodo obtenerPlacas de la clase PlacasDAO.
     */
    @Test
    public void obtenerPlacas_ObtieneLasPlacas_PlacasEntidad() {
        System.out.println("obtenerPlacas");
        
        // arrange
        String numPlacas = "AMX-13-57";
        PlacasEntidad expResult = new PlacasEntidad();
        expResult.setId(1l);
        expResult.setActiva(true);
        expResult.setCosto(2400.50f);
        expResult.setNumero("AMX-13-57");
        
        Mockito.when(this.placasDAO.obtenerPlacas(numPlacas)).thenReturn(expResult);
        
        // act
        PlacasEntidad result = this.placasDAO.obtenerPlacas(numPlacas);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerPlacas de la clase PlacasDAO.
     */
    @Test
    public void obtenerPlacas_NoSeEncontraronLasPlacas_Null() {
        System.out.println("obtenerPlacas - No se encontraron las placas");
        
        // arrange
        String numPlacas = "AMX-13-57";
        
        Mockito.when(this.placasDAO.obtenerPlacas(numPlacas)).thenReturn(null);
        
        // act
        PlacasEntidad result = this.placasDAO.obtenerPlacas(numPlacas);
        
        // assert
        assertNull(result, "El resultado de las placas debe ser NULL");
    }

    /**
     * Prueba para el metodo desactivarPlacas de la clase PlacasDAO.
     */
    @Test
    public void desactivarPlacas_SeDesactivanLasPlacas_PlacasEntidad() {
        System.out.println("desactivarPlacas");
        
        // arrange
        PlacasEntidad ultimasPlacas = new PlacasEntidad();
        ultimasPlacas.setId(1l);
        ultimasPlacas.setActiva(true);
        ultimasPlacas.setCosto(2400.50f);
        ultimasPlacas.setNumero("AMX-13-57");
       
        // act
        this.placasDAO.desactivarPlacas(ultimasPlacas);
        
        Mockito.verify(this.placasDAO).desactivarPlacas(ultimasPlacas);
    }
}
