/*
 * NegocioException.java
 */
package excepciones;

/**
 * Excepción lanzada por los métodos implementados en la capa de negocio
 * cuando ocurre un error en la parte de la lógica y las reglas del negocio.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class NegocioException extends Exception {
    
    /**
     * Constructor que establece el mensaje de error que explica el origen del
     * error que ocurrio al ejecutar una operación.
     * @param mensaje Mensaje de error.
     */
    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
