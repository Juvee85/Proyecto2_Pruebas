/*
 * ITarifaPlacasDAO.java
 */
package daos;

import entidades.TarifaPlacasEntidad;

/**
 * Interfaz que define métodos para acceder a los datos relacionados con las
 * tarifas de placas en la base de datos.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ITarifaPlacasDAO {

    /**
     * Método que devuelve la tarifa cuyo tipo es el mismo que el recibida
     * en el parámeto.
     * @param tipo Tipo de vehículo al cual se le sacan las placas.
     * @return La tarifa.
     */
    public TarifaPlacasEntidad buscarTarifa(String tipo);
}
