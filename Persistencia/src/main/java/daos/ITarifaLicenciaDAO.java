
package daos;

import entidades.TarifaLicenciaEntidad;
import java.util.List;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ITarifaLicenciaDAO {
    public List<TarifaLicenciaEntidad> obtenerTarifas(boolean discapacitado);
    public TarifaLicenciaEntidad buscarTarifa(String vigencia);
}
