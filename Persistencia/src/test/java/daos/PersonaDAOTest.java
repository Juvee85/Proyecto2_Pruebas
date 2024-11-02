/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package daos;

import entidades.PersonaEntidad;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import excepciones.PersistenciaException;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Pruebas para la clase PersonaDAO que implementa de IPersonaDAO
 * @author neri
 */
public class PersonaDAOTest {

    @Mock
    private IPersonaDAO personaDAO;

    public PersonaDAOTest() {
        this.personaDAO = Mockito.mock(IPersonaDAO.class);
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
     * Prueba del metodo hayPersonas de la clase PersonaDAO.
     */
    @Test
    public void hayPersonas_HayPersonasRegistradas_True() {
        System.out.println("hayPersonas - True");

        // arrange
        boolean expResult = true;
        Mockito.when(this.personaDAO.hayPersonas()).thenReturn(expResult);

        // act
        boolean result = this.personaDAO.hayPersonas();

        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba del metodo hayPersonas de la clase PersonaDAO.
     */
    @Test
    public void hayPersonas_NoHayPersonasRegistradas_False() {
        System.out.println("hayPersonas - False");

        // arrange
        boolean expResult = false;
        Mockito.when(this.personaDAO.hayPersonas()).thenReturn(expResult);

        // act
        boolean result = this.personaDAO.hayPersonas();

        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba para el metodo insertarPersona de la clase PersonaDAO.
     */
    @Test
    public void insertarPersona_PersonaInsertadaConExito_ResultadoSatisfactorio() {
        System.out.println("insertarPersona");

        // arrange
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setNombre("Arely");
        persona.setApellidoMaterno("Cruz");
        persona.setApellidoPaterno("Lopez");

        // act
        this.personaDAO.insertarPersona(persona);

        // assert
    }

    /**
     * Prueba del metodo buscarPorCurp de la clase PersonaDAO.
     */
    @Test
    public void buscarPorCurp_PersonaEncontradaConCurpConExito_PersonaEntidad() throws Exception {
        System.out.println("buscarPorCurp");

        // arrange
        String curp = "AME10294022SHHR";
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp(curp);
        persona.setNombre("America");
        persona.setApellidoMaterno("Cardenas");
        persona.setApellidoPaterno("Lopez");

        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenReturn(persona);

        // act
        PersonaEntidad result = this.personaDAO.buscarPorCurp(curp);

        // assert
        assertEquals(persona, result);
    }

    /**
     * Prueba del metodo buscarPorCurp de la clase PersonaDAO.
     */
    @Test
    public void buscarPorCurp_PersonaNoEncontradaConCurp_PersistenciaException() throws Exception {
        System.out.println("buscarPorCurp - No se encuentra persona por CURP");

        // arrange
        String curp = "AME1123212211HR";
        PersonaEntidad persona = new PersonaEntidad();
        persona.setId(12l);
        persona.setCurp(curp);
        persona.setNombre("America");
        persona.setApellidoMaterno("Cardenas");
        persona.setApellidoPaterno("Lopez");

        Mockito.when(this.personaDAO.buscarPorCurp(curp)).thenThrow(PersistenciaException.class);

        // act y assert
        Exception excp = assertThrows(PersistenciaException.class, () -> this.personaDAO.buscarPorCurp(curp));
    }

    /**
     * Pruba del metodo buscarPorNombre de la clase PersonaDAO.
     */
    @Test
    public void buscarPorNombre_PersonaEncontradaPorNombre_PersonaEntidad() throws Exception {
        System.out.println("buscarPorNombre");

        // arrange
        String nombre = "Luis";
        int limite = 10;
        int offset = 10;

        PersonaEntidad p1 = new PersonaEntidad();
        p1.setId(12l);
        p1.setCurp("929ASJAL1KL001");
        p1.setNombre("Luis Eduardo");
        p1.setApellidoMaterno("Perez");
        p1.setApellidoPaterno("Patinho");

        PersonaEntidad p2 = new PersonaEntidad();
        p2.setId(13L);
        p2.setCurp("123BDFGJ9KL002");
        p2.setNombre("Ana Maria");
        p2.setApellidoMaterno("Gomez");
        p2.setApellidoPaterno("Sanchez");

        List<PersonaEntidad> expResult = Arrays.asList(p1);

        Mockito.when(this.personaDAO.buscarPorNombre(nombre, limite, offset)).thenReturn(expResult);
        
        // act
        List<PersonaEntidad> result = this.personaDAO.buscarPorNombre(nombre, limite, offset);

        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Pruba del metodo buscarPorNombre de la clase PersonaDAO.
     */
    @Test
    public void buscarPorNombre_PersonaNoSeHaEncontradaPorNombre_PersistenciaException() throws Exception {
        System.out.println("buscarPorNombre - no se encuentran personas por nombre");

        // arrange
        String nombre = "Luis";
        int limite = 10;
        int offset = 10;

        PersonaEntidad p1 = new PersonaEntidad();
        p1.setId(12l);
        p1.setCurp("929ASJAL1KL001");
        p1.setNombre("Luis Eduardo");
        p1.setApellidoMaterno("Perez");
        p1.setApellidoPaterno("Patinho");

        PersonaEntidad p2 = new PersonaEntidad();
        p2.setId(13L);
        p2.setCurp("123BDFGJ9KL002");
        p2.setNombre("Ana Maria");
        p2.setApellidoMaterno("Gomez");
        p2.setApellidoPaterno("Sanchez");

        List<PersonaEntidad> expResult = Arrays.asList(p1);

        Mockito.when(this.personaDAO.buscarPorNombre(nombre, limite, offset)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.personaDAO.buscarPorNombre(nombre, limite, offset));
    }

    /**
     * Prueba del metodo buscarPorAnioNacimiento de la clase PersonaDAO.
     */
    @Test
    public void buscarPorAnioNacimiento_PersonasEncontradasPorAnio_ListaPersonaEntidad() throws Exception {
        System.out.println("buscarPorAnioNacimiento");
        
        // arrange
        int anio = 2004;
        int limite = 0;
        int offset = 0;
        
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.set(2004, Calendar.MARCH, 2);
        
        PersonaEntidad p1 = new PersonaEntidad();
        p1.setId(12l);
        p1.setCurp("929ASJAL1KL001");
        p1.setNombre("Luis Eduardo");
        p1.setApellidoMaterno("Perez");
        p1.setApellidoPaterno("Patinho");
        p1.setFechaNacimiento(fechaNac);

        PersonaEntidad p2 = new PersonaEntidad();
        p2.setId(13L);
        p2.setCurp("123BDFGJ9KL002");
        p2.setNombre("Ana Maria");
        p2.setApellidoMaterno("Gomez");
        p2.setApellidoPaterno("Sanchez");
        fechaNac.set(2004, Calendar.APRIL, 6);
        p2.setFechaNacimiento(fechaNac);

        List<PersonaEntidad> expResult = Arrays.asList(p1, p2);
        Mockito.when(this.personaDAO.buscarPorAnioNacimiento(anio, limite, offset)).thenReturn(expResult);
        
        // act
        List<PersonaEntidad> result = this.personaDAO.buscarPorAnioNacimiento(anio, limite, offset);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba del metodo buscarPorAnioNacimiento de la clase PersonaDAO.
     */
    @Test
    public void buscarPorAnioNacimiento_PersonasNoEncontradasPorAnio_PersistenciaException() throws Exception {
        System.out.println("buscarPorAnioNacimiento - No se encuentran personas por anio");
        
        // arrange
        int anio = 2004;
        int limite = 0;
        int offset = 0;
        
        Mockito.when(this.personaDAO.buscarPorAnioNacimiento(anio, limite, offset)).thenThrow(PersistenciaException.class);

         // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.personaDAO.buscarPorAnioNacimiento(anio, limite, offset));
    }

    /**
     * Prueba para el metodo obtenerPersonas de la clase PersonaDAO.
     */
    @Test
    public void obtenerPersonas_ObtieneTodasLasPersonasRegistradas_ListaPersonaEntidad() throws Exception {
        System.out.println("obtenerPersonas - Obtiene todas las personas");
        
        // arrange
        int limite = 0;
        int offset = 0;
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.set(2004, Calendar.MARCH, 2);
        
        PersonaEntidad p1 = new PersonaEntidad();
        p1.setId(12l);
        p1.setCurp("929ASJAL1KL001");
        p1.setNombre("Luis Eduardo");
        p1.setApellidoMaterno("Perez");
        p1.setApellidoPaterno("Patinho");
        p1.setFechaNacimiento(fechaNac);

        PersonaEntidad p2 = new PersonaEntidad();
        p2.setId(13L);
        p2.setCurp("123BDFGJ9KL002");
        p2.setNombre("Ana Maria");
        p2.setApellidoMaterno("Gomez");
        p2.setApellidoPaterno("Sanchez");
        fechaNac.set(2004, Calendar.APRIL, 6);
        p2.setFechaNacimiento(fechaNac);

        List<PersonaEntidad> expResult = Arrays.asList(p1, p2);
        Mockito.when(this.personaDAO.obtenerPersonas(limite, offset)).thenReturn(expResult);
        
        // act
        List<PersonaEntidad> result = this.personaDAO.obtenerPersonas(limite, offset);
        
        // assert
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba para el metodo obtenerPersonas de la clase PersonaDAO.
     */
    @Test
    public void obtenerPersonas_NoSeEncuentranPersonasRegistradas_PersistenciaException() throws Exception {
        System.out.println("obtenerPersonas - No encuentra ninguna persona");
        
        // arrange
        int limite = 0;
        int offset = 0;
        
        Mockito.when(this.personaDAO.obtenerPersonas(limite, offset)).thenThrow(PersistenciaException.class);
        
        // act y assert
        Exception exp = assertThrows(PersistenciaException.class, () -> this.personaDAO.obtenerPersonas(limite, offset));
    }

}
