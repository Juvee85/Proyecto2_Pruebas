
package daos;

import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ILicenciaDAO {
    public LicenciaEntidad buscarLicencia(PersonaEntidad persona);
    public void desactivarLicencia(LicenciaEntidad licencia);
    public void insertarLicencia(LicenciaEntidad licencia);
}
