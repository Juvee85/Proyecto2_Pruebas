/*
 * ITarifaLicenciaDAO.java
 */
package daos;

import entidades.TarifaLicenciaEntidad;
import java.util.List;

/**
 * Interfaz que define los métodos para acceder a los datos relacionados con las
 * tarifas de licencia en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ITarifaLicenciaDAO {

    /**
     * Método que obtiene una lista con todas las tarifas de licencia
     * disponibles.
     *
     * @return Lista con todas las tarifas de licencia disponibles.
     */
    public List<TarifaLicenciaEntidad> obtenerTarifas();

    /**
     * Método que devuelve la tarifa cuya vigencia es la misma que la recibida
     * en el parámeto.
     *
     * @param vigencia Vigencia de la tarifa que se busca.
     * @return La tarifa que se haya encontrado.
     */
    public TarifaLicenciaEntidad buscarTarifa(String vigencia);
}
