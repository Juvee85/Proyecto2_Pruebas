/*
 * Paleta.java
 */
package utilidades;

import java.awt.Color;

/**
 * Clase con la paleta de colores que se usa para el header con el botón de
 * cerrar y minimizar ventana.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class Paleta {

    // Definimos los colores como públicos para que todas las clases puedan acceder
    // a ellos, estáticos para que no dependan de una instancia y final para
    // indicar que son constantes.
    public static final Color ROJO = new Color(230, 62, 62);
    public static final Color VERDE = new Color(70, 230, 62);
    public static final Color GRIS = new Color(88, 88, 88);

    // Constructor privado para evitar instanciar la clase
    private Paleta() {
    }
}
