package daos;

import entidades.PersonaEntidad;
import excepciones.PersistenciaException;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IPersonaDAO {
    
    public boolean hayPersonas();
    public void insertarPersona(PersonaEntidad persona);
    public PersonaEntidad buscarPorCurp(String curp) throws PersistenciaException;
    
}
