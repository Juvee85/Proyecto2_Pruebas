/*
 * ITarifaLicenciaDAO.java
 */
package daos;

import entidades.TarifaLicenciaEntidad;
import java.util.List;

/**
 * Interfaz que define métodos para acceder a los datos relacionados con las
 * tarifas de licencias en la base de datos.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ITarifaLicenciaDAO {
    
    /**
     * Método que obtiene una lista con todas las tarifas de licencias disponibles.
     * @return Lista con todas las tarifas de licencias disponibles.
     */
    public List<TarifaLicenciaEntidad> obtenerTarifas();
    
    /**
     * Método que devuelve la tarifa cuya vigencia es la misma que la recibiad
     * en el parámeto.
     * @param vigencia Vigencia de la tarifa que se busca.
     * @return La tarifa con la vigencia dada.
     */
    public TarifaLicenciaEntidad buscarTarifa(String vigencia);
}
