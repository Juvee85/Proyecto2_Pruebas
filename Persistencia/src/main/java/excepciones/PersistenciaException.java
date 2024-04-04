package excepciones;

/**
 * Excepción lanzada por los métodos implementados en la capa de persistencia
 * cuando ocurre un error en la parte de la manipulación de datos.
 * 
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PersistenciaException extends Exception {
    /**
     * Constructor que establece el mensaje de error que explica el origen del
     * error que ocurrio al ejecutar una operación.
     * 
     * @param mensaje Mensaje de error.
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }
}
