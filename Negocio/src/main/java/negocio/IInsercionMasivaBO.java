/*
 * IInsercionMasivaBO.java
 */
package negocio;

import entidades.PersonaEntidad;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz para realizar la inserción masiva de 20 personas.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IInsercionMasivaBO {
    
    /**
     * Método para llevar a cabo la inserción masiva.
     * @throws NegocioException si ocurre algún error durante el proceso de la
     * inserción.
     */
    public void insertarPersonas() throws NegocioException;
    
    /**
     * Método que crea las personas de la inserción masiva.
     * @return Una lista de personas.
     * @throws NegocioException si ocurre un error durante la encriptación.
     */
    public List<PersonaEntidad> crearPersonas() throws NegocioException;
}
