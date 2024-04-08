package daos;

import entidades.PersonaEntidad;
import entidades.TramiteEntidad;
import excepciones.PersistenciaException;
import java.util.Calendar;
import java.util.List;

/**
 * Interfaz que define métodos para acceder y manipular datos relacionados con
 * trámites en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface ITramiteDAO {

    /**
     * Obtiene la lista de trámites de una persona.
     *
     * @param persona Persona de la cual se buscan los trámites
     * @param limite Limite de elementos a incluir por página
     * @param offset offset Indice de elemento donde inicia la página
     * @return Lista con los trámites realizados por la persona dada
     * @throws PersistenciaException Cuando ocurre un error en la base de datos
     * al realizar la consulta
     */
    public List<TramiteEntidad> obtenerTramitesPorPersona(PersonaEntidad persona, int limite, int offset) throws PersistenciaException;

    /**
     * Método que obtiene la lista de trámites de acuerdo a los filtros
     * seleccionados.
     *
     * @param nombre Nombre a coincidir.
     * @param fechaInicial Fecha inicial del periodo.
     * @param fechaFin Fecha final del periodo.
     * @param tipos Tipos de trámite seleccionados.
     * @param limite Límite de elementos a incluir por página
     * @param offset Índice de elemento donde inicia la página.
     * @return Una lista con todos los trámites que coincidan con los filtros.
     * @throws PersistenciaException
     */
    public List<TramiteEntidad> obtenerReporte(String nombre, Calendar fechaInicial, Calendar fechaFin, List<Class> tipos, int limite, int offset) throws PersistenciaException;

    /**
     * Método que obtiene la lista de trámites de acuerdo a los filtros
     * seleccionados, pero ahora sin limit ni offset.
     *
     * @param nombre Nombre a coincidir.
     * @param fechaInicial Fecha inicial del periodo.
     * @param fechaFin Fecha final del periodo.
     * @param tipos Tipos de trámite seleccionados.
     * @return Una lista con todos los trámites que coincidan con los filtros.
     * @throws PersistenciaException
     */
    public List<TramiteEntidad> obtenerReporte(String nombre, Calendar fechaInicial, Calendar fechaFin, List<Class> tipos) throws PersistenciaException;
}
