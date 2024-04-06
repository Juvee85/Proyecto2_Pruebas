
package daos;

import entidades.AutomovilEntidad;
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
    
    public AutomovilEntidad buscarAutoPlacas(String numPlacas) throws PersistenciaException;

    public PlacasEntidad obtenerUltimasPlacas(String numSerie);
    
    public PlacasEntidad obtenerPlacas(String numPlacas);
    
    public void desactivarPlacas(PlacasEntidad ultimasPlacas);
}
