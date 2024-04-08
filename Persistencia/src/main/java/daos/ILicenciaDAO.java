/*
 * ILicenciaDAO.java
 */
package daos;

import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import excepciones.PersistenciaException;

/**
 * Interfaz que proporciona los métodos para acceder y manipular datos
 * relacionados con licencias en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ILicenciaDAO {

    /**
     * Método para buscar la última licencia asociada a una persona.
     *
     * @param persona Persona que está solicitando una nueva licencia.
     * @return La licencia que se haya encontrado.
     * @throws PersistenciaException si no se encontró ninguna licencia.
     */
    public LicenciaEntidad buscarUltimaLicencia(PersonaEntidad persona) throws PersistenciaException;

    /**
     * Método para desactivar una licencia.
     *
     * @param licencia Licencia a desactivar.
     */
    public void desactivarLicencia(LicenciaEntidad licencia);

    /**
     * Método para insertar una licencia en la base de datos.
     *
     * @param licencia Licencia a insertar.
     */
    public void insertarLicencia(LicenciaEntidad licencia);
}
