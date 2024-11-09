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
 * @author nerix
 */
public class PaginacionTest {

    public PaginacionTest() {
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
    
    @Test
    void obtenerOffset_PaginaEs1_Devuelve0() {
        // arrange
        int limite = 10;
        int pagina = 1;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(0, resultado, "Para la pagina 1, el offset debe ser 0");
    }

    @Test
    void obtenerOffset_PaginaEs2_DevuelveLimite() {
        // arrange
        int limite = 10;
        int pagina = 2;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(10, resultado, "Para la pagina 2, el offset debe ser igual al limite");
    }

    @Test
    void obtenerOffset_PaginaMayorQue2_DevuelveOffsetCorrecto() {
        // arrange
        int limite = 10;
        int pagina = 3;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(20, resultado, "Para la pagina 3 y un limite de 10, el offset debe ser 20");
    }

    @Test
    void obtenerOffset_LimiteMayorQue1YPaginaMayorQue2_DevuelveOffsetCorrecto() {
        // arrange
        int limite = 15;
        int pagina = 4;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(45, resultado, "Para la pagina 4 y un limite de 15, el offset debe ser 45");
    }

    @Test
    void obtenerOffset_LimiteEs1_DevuelveOffsetCorrecto() {
        // arrange
        int limite = 1;
        int pagina = 10;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(9, resultado, "Para limite 1 y pagina 10, el offset debe ser 9");
    }

    @Test
    void obtenerOffset_LimiteEs0_Devuelve0() {
        // arrange
        int limite = 0;
        int pagina = 5;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(0, resultado, "Para limite 0, el offset siempre debe ser 0");
    }

    @Test
    void obtenerOffset_PaginaNegativa_Devuelve0() {
        // arrange
        int limite = 10;
        int pagina = -1;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(0, resultado, "Para una pagina negativa, el offset debe ser 0");
    }

    @Test
    void obtenerOffset_LimiteNegativo_DevuelveOffsetCalculado() {
        // arrange
        int limite = -10;
        int pagina = 3;
        
        // act
        int resultado = Paginacion.obtenerOffset(limite, pagina);
        
        // assert
        assertEquals(-20, resultado, "Para un limite negativo, el offset debe reflejar el calculo negativo");
    }
}
