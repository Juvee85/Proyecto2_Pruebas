/*
 * IPlacasDAO.java
 */
package daos;

import entidades.AutomovilEntidad;
import entidades.PlacasEntidad;
import excepciones.PersistenciaException;

/**
 * Interfaz que proporciona los métodos para acceder y manipular datos
 * relacionados con placas en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IPlacasDAO {

    /**
     * Método que devuelve verdadero si ya existe una placa registrada con el
     * número de placa recibido en el parámetro, devuelve falso en caso
     * contrario.
     *
     * @param numPlaca Número de placa a evaluar.
     * @return Verdadero si ya existe una placa registrada con el número de
     * placa recibido en el parámetro, devuelve falso en caso contrario.
     */
    public boolean existePlaca(String numPlaca);

    /**
     * Método que inserta unas placas en la base de datos.
     *
     * @param placas Placas a la cuales se van a insertar.
     */
    public void insertarPlacas(PlacasEntidad placas);

    /**
     * Método para buscar un automóvil dado un número de placas las cuales deben
     * ser las últimas asociadas a dicho automóvil.
     *
     * @param numPlacas Número de las últimas placas asociadas a un automóvil.
     * @return El automóvil que se haya encontrado.
     * @throws PersistenciaException si no se encontró ningún automóvil.
     */
    public AutomovilEntidad buscarAutoPlacas(String numPlacas) throws PersistenciaException;

    /**
     * Método para obtener las últimas placas asociadas a un automóvil con base
     * al número de serie de dicho automóvil.
     *
     * @param numSerie Número de serie del automóvil del cual se buscan las
     * últimas placas.
     * @return Las placas que se hayan encontrado.
     */
    public PlacasEntidad obtenerUltimasPlacas(String numSerie);

    /**
     * Método para obtener el objeto entidad de las placas del parámetro.
     *
     * @param numPlacas Número de las placas que se quieren obtener.
     * @return Las placas que se hayan encontrado, null si no se encontró nada.
     */
    public PlacasEntidad obtenerPlacas(String numPlacas);

    /**
     * Método que desactiva las últimas placas de un automóvil.
     *
     * @param ultimasPlacas Placas a desactivar.
     */
    public void desactivarPlacas(PlacasEntidad ultimasPlacas);
}
