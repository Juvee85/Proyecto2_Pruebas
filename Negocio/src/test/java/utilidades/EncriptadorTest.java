/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package utilidades;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author neri
 */
public class EncriptadorTest {
    
    private Encriptador encriptador = new Encriptador();
    
    public EncriptadorTest() {
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
     * Test of encriptar method, of class Encriptador.
     */
    @Test
    public void encriptar_SeEncriptaUnTexto_TextoEncriptadorDiferente() throws Exception {
        System.out.println("encriptar");
        
        // arrange
        String valor = "Mensaje";
        
        // act
        String result = this.encriptador.encriptar(valor);
        
        System.out.println(result);
        
        // assert
        assertFalse(result.equals(valor), "%s no debe ser igual al resultado del encriptado".formatted(valor));
    }

    /**
     * Test of desencriptar method, of class Encriptador.
     */
    @Test
    public void desencriptar() throws Exception {
        System.out.println("desencriptar");
        
        // arrange
        String valorEsperado = "Mensaje";
        String textoEncriptado = "InIpFwu0sDJJ2UkRIyvEhw=="; // "Mensaje"
        
        // act
        String result = this.encriptador.desencriptar(textoEncriptado);
        
        // assert
        assertTrue(result.equals(valorEsperado), "'%s' debe ser igual al valor esperado: '%s'".formatted(result, valorEsperado));
    }
    
}
