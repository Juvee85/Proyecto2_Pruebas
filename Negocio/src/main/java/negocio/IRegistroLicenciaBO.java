
package negocio;

import dtos.LicenciaDTO;
import dtos.TarifaLicenciaDTO;
import dtos.PersonaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IRegistroLicenciaBO {
    public PersonaDTO buscarCurp(String curp) throws NegocioException;
    public void validarRequisitos(PersonaDTO persona) throws NegocioException;
    public List<TarifaLicenciaDTO> buscarTarifasLicencia(boolean discapacitado);
    public void agregarLicencia(String curp, LicenciaDTO licencia);
}
