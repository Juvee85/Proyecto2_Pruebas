/*
 * PresentacionException.java
 */
package excepciones;

/**
 * Excepción lanzada por los métodos implementados en la capa de presentación
 * cuando ocurre un error en la parte con la que interactúa el usuario.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PresentacionException extends Exception {

    /**
     * Constructor que establece el mensaje de error que explica el origen del
     * error que ocurrio al ejecutar una operación.
     *
     * @param mensaje Mensaje de error
     */
    public PresentacionException(String mensaje) {
        super(mensaje);
    }
}
