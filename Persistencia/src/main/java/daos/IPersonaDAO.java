/*
 * IPersonaDAO.java
 */
package daos;

import entidades.PersonaEntidad;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que proporciona los métodos para acceder y manipular datos
 * relacionados con personas en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IPersonaDAO {

    /**
     * Método que indica si hay personas registradas en la base de datos.
     *
     * @return Verdadero si ya hay registros en la base de datos, y falso si no
     * los hay.
     */
    public boolean hayPersonas();

    /**
     * Método que inserta una persona en la base de datos.
     *
     * @param persona Persona a la cual se va a insertar.
     */
    public void insertarPersona(PersonaEntidad persona);

    /**
     * Método que busca una persona dada una CURP.
     *
     * @param curp CURP de la persona que se busca.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encontró a ninguna persona con la
     * CURP dada.
     */
    public PersonaEntidad buscarPorCurp(String curp) throws PersistenciaException;

    /**
     * Obtiene la lista de personas cuyo nombre coincide con el dado.
     *
     * @param nombre Nombre de persona que se desea buscar
     * @param limite Limite de elementos a incluir por página
     * @param offset offset Indice de elemento donde inicia la página
     * @return Lista con entidades de personas cuyo nombre coincide con el dado
     * @throws PersistenciaException si no encuentra a ninguna persona.
     */
    public List<PersonaEntidad> buscarPorNombre(String nombre, int limite, int offset) throws PersistenciaException;

    /**
     * Obtiene la lista de personas cuyo año de nacimiento coincide con el dado.
     *
     * @param anio Año de nacimiento que se desea buscar
     * @param limite Limite de elementos a incluir por página
     * @param offset offset Indice de elemento donde inicia la página
     * @return Una lista con las personas encontradas.
     * @throws PersistenciaException si no encuentra a ninguna persona.
     */
    public List<PersonaEntidad> buscarPorAnioNacimiento(int anio, int limite, int offset) throws PersistenciaException;

    /**
     * Obtiene la lista completa de personas registradas.
     *
     * @param limite Limite de elementos a incluir por página
     * @param offset Indice de elemento donde inicia la página
     * @return Lista de todas las personas registradas
     * @throws PersistenciaException si no encuentra a ninguna persona.
     */
    public List<PersonaEntidad> obtenerPersonas(int limite, int offset) throws PersistenciaException;
}
