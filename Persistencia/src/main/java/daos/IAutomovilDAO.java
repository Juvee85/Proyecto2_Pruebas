
package daos;

import entidades.AutomovilEntidad;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IAutomovilDAO {

    public boolean estaRegistrado(String numSerie);

    public AutomovilEntidad obtenerAutomovil(String numSerie);

    public void insertarAutomovil(AutomovilEntidad autoEntidad);
}
