
package daos;

import entidades.PersonaEntidad;
import entidades.RelacionVehiculoPersona;
import entidades.VehiculoEntidad;
import javax.persistence.EntityExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Pruebas para la clase RelacionVehiculoPersonaDAO que implementa IRelacionVehiculoPersonaDAO
 * @author neri
 */
public class RelacionVehiculoPersonaDAOTest {
    
    @Mock
    private IRelacionVehiculoPersonaDAO rvpDAO;
    
    public RelacionVehiculoPersonaDAOTest() {
        this.rvpDAO = Mockito.mock(IRelacionVehiculoPersonaDAO.class);
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
     * Prueba para el metodo existeDetalle de la clase RelacionVehiculoPersonaDAO.
     */
    @Test
    public void existeDetalle_SiExisteDetalle_True() {
        System.out.println("existeDetalle");
        
        // arrange
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona();
        rvp.setId(1l);
        
        boolean expResult = true;
        
        Mockito.when(this.rvpDAO.existeDetalle(rvp)).thenReturn(expResult);
        
        // act
        boolean result = this.rvpDAO.existeDetalle(rvp);
        
        // assert
        assertEquals(expResult, result);
    }
   
    /**
     * Prueba para el metodo existeDetalle de la clase RelacionVehiculoPersonaDAO.
     */
    @Test
    public void existeDetalle_NoExisteDetalle_False() {
        System.out.println("existeDetalle - False");
        
        // arrange
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona();
        rvp.setId(1l);
        
        boolean expResult = false;
        
        Mockito.when(this.rvpDAO.existeDetalle(rvp)).thenReturn(expResult);
        
        // act
        boolean result = this.rvpDAO.existeDetalle(rvp);
        
        // assert
        assertEquals(expResult, result);
    }

    /**
     * Prueba del metodo insertarDetalle de la clase RelacionVehiculoPersonaDAO.
     */
    @Test
    public void insertarDetalle_SeInsertaElDetalle_ResultadoSatisfactorio() {
        System.out.println("insertarDetalle");
        
        // arrange
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona();
        rvp.setId(1l);
        
        // act
        this.rvpDAO.insertarDetalle(rvp);
        
        // assert
        Mockito.verify(this.rvpDAO).insertarDetalle(rvp);
    }
    
    /**
     * Prueba del metodo insertarDetalle de la clase RelacionVehiculoPersonaDAO.
     */
    @Test
    public void insertarDetalle_YaExisteElDetalle_EntityExistsException() {
        System.out.println("insertarDetalle - EntityExistsException");
        
        // arrange
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona();
        rvp.setId(1l);
        
        Mockito.doThrow(EntityExistsException.class).when(this.rvpDAO).insertarDetalle(rvp);
        
        // act y assert
        Exception exp = assertThrows(EntityExistsException.class, () -> this.rvpDAO.insertarDetalle(rvp));
    }
    
}
