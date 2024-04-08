/*
 * IAutomovilDAO.java
 */
package daos;

import entidades.AutomovilEntidad;

/**
 * Interfaz que proporciona los métodos para acceder y manipular datos
 * relacionados con automóviles en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IAutomovilDAO {

    /**
     * Método que devuelve verdadero si el automóvil con el número de serie
     * proporcionado está registrado en la base de datos.
     *
     * @param numSerie Número de serie del automóvil.
     * @return Verdadero si el automóvil está registrado, falso en caso
     * contrario.
     */
    public boolean estaRegistrado(String numSerie);

    /**
     * Método para obtener un automóvil dado un número de serie.
     *
     * @param numSerie Número de serie del automóvil buscado.
     * @return El automóvil si se encuentra, null si no se encuentra.
     */
    public AutomovilEntidad obtenerAutomovil(String numSerie);

    /**
     * Método para insertar un automóvil en la base de datos.
     *
     * @param automovil Automóvil a insertar.
     */
    public void insertarAutomovil(AutomovilEntidad automovil);
}
