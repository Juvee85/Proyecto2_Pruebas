
package daos;

import entidades.PlacasEntidad;
import excepciones.PersistenciaException;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IPlacasDAO {
    
    public boolean existePlaca(String numPlaca);
    
    public void insertarPlacas(PlacasEntidad placas);
    
    public PlacasEntidad buscarUltimasPlacas(String numSerie) throws PersistenciaException;

    public void desactivarPlacas(PlacasEntidad ultimasPlacas);
}
