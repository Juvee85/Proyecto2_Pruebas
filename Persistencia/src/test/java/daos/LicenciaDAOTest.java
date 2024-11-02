/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import excepciones.PersistenciaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Pruebas para la clase LicenciaDAO que implementa de ILicenciaDAO
 * @author neri
 */
public class LicenciaDAOTest {
    
    @Mock
    private ILicenciaDAO licenciaDAO;
    
    public LicenciaDAOTest() {
        this.licenciaDAO = Mockito.mock(ILicenciaDAO.class);
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
     * Prueba del metodo buscarUltimaLicencia de la clase LicenciaDAO.
     */
    @Test
    public void testBuscarUltimaLicencia_UltimaLicenciaEncontrada_LicenciaEntidad() throws Exception {
        System.out.println("buscarUltimaLicencia");
        
        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setNombre("Saul Armando");
        persona.setApellidoMaterno("Escarcega");
        persona.setApellidoPaterno("Neri");
        
        LicenciaEntidad expResult = new LicenciaEntidad();
        expResult.setActiva(true);
        expResult.setCosto(1200.99f);
        expResult.setId(12l);
        expResult.setPersona(persona);
        
        Mockito.when(this.licenciaDAO.buscarUltimaLicencia(persona)).thenReturn(expResult);
        
        // act
        LicenciaEntidad result = this.licenciaDAO.buscarUltimaLicencia(persona);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba del metodo buscarUltimaLicencia de la clase LicenciaDAO.
     */
    @Test
    public void testBuscarUltimaLicencia_UltimaLicenciaNoEncontrada_NoEncontrada() throws Exception {
        System.out.println("buscarUltimaLicencia");
        
        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setNombre("Saul Armando");
        persona.setApellidoMaterno("Escarcega");
        persona.setApellidoPaterno("Neri");
        
        LicenciaEntidad expResult = new LicenciaEntidad();
        expResult.setActiva(true);
        expResult.setCosto(1200.99f);
        expResult.setId(12l);
        expResult.setPersona(persona);
        
        Mockito.when(this.licenciaDAO.buscarUltimaLicencia(persona)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception excp = assertThrows(PersistenciaException.class, () -> this.licenciaDAO.buscarUltimaLicencia(persona));
    }

    /**
     * Prueba para el metodo desactivarLicencia de la clase LicenciaDAO.
     */
    @Test
    public void testDesactivarLicencia_DesactivacionSatisfactoria_ResultadoSatisfactorio() {
        System.out.println("desactivarLicencia");
        
        // arrange
        LicenciaEntidad expResult = new LicenciaEntidad();
        expResult.setActiva(true);
        expResult.setCosto(1200.99f);
        expResult.setId(12l);
        
        // act
        this.licenciaDAO.desactivarLicencia(expResult);
        
        // assert
    }

    /**
     * Prueba del metodo insertarLicencia de la clase LicenciaDAO.
     */
    @Test
    public void testInsertarLicencia() {
        System.out.println("insertarLicencia");
        
        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setNombre("Saul Armando");
        persona.setApellidoMaterno("Escarcega");
        persona.setApellidoPaterno("Neri");
        
        LicenciaEntidad lic = new LicenciaEntidad();
        lic.setActiva(true);
        lic.setCosto(1200.99f);
        lic.setId(12l);
        lic.setPersona(persona);
        
        // act
        this.licenciaDAO.insertarLicencia(lic);
    }
    
}
