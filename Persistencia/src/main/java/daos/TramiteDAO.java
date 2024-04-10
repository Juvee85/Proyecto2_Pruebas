/*
 * TramiteDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.PersonaEntidad;
import entidades.TramiteEntidad;
import excepciones.PersistenciaException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Clase que implementa la interfaz ITramiteDAO y proporciona los métodos para
 * realizar operaciones relacionadas con la entidad Tramite en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TramiteDAO implements ITramiteDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(TramiteDAO.class.getName());

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
    @Override
    public List<TramiteEntidad> obtenerTramitesPorPersona(PersonaEntidad persona, int limite, int offset) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            TypedQuery<TramiteEntidad> consulta = em.createQuery("SELECT t"
                    + " FROM TramiteEntidad t"
                    + " WHERE t.persona = :persona", TramiteEntidad.class)
                    .setFirstResult(offset)
                    .setMaxResults(limite);
            consulta.setParameter("persona", persona);
            List<TramiteEntidad> tramites = consulta.getResultList();
            return tramites;
        } catch (Exception e) {
            logger.log(Level.INFO, "No se pudo completar la consulta");
            // Lanzamos una excepción de que no se encontró tramite de la persona seleccionada
            throw new PersistenciaException("Ocurrio un error al realizar la consulta");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

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
    @Override
    public List<TramiteEntidad> obtenerReporte(String nombre, Calendar fechaInicial, Calendar fechaFin, List<Class> tipos,
            int limite, int offset) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        TypedQuery<TramiteEntidad> consulta = em.createQuery(
                  "SELECT t"
                + " FROM TramiteEntidad t"
                + " WHERE (:nombre IS NULL OR t.persona.nombre = :nombre)"
                + "     AND (:fechaInicio IS NULL AND :fechaFin IS NULL "
                + "         OR (:fechaInicio IS NOT NULL AND t.fechaEmision >= :fechaInicio AND :fechaFin IS NULL) "
                + "         OR (:fechaFin IS NOT NULL AND t.fechaEmision <= :fechaFin AND :fechaInicio IS NULL) "
                + "         OR (t.fechaEmision BETWEEN :fechaInicio AND :fechaFin)) "
                + "     AND (:tipoTramite1 IS NULL OR Type(t) = :tipoTramite1 "
                + "         OR (:tipoTramite1 IS NULL OR Type(t) = :tipoTramite1 "
                + "         AND :tipoTramite2 IS NULL OR Type(t) = :tipoTramite2)) ", TramiteEntidad.class)
                .setFirstResult(offset)
                .setMaxResults(limite);
        consulta.setParameter("nombre", nombre);
        consulta.setParameter("fechaInicio", fechaInicial);
        consulta.setParameter("fechaFin", fechaFin);
        int i = 1;
        for (Class<?> tipo : tipos) {
            consulta.setParameter("tipoTramite" + i, tipo);
            i++;
        }
        if (tipos.size() < 2) {
            consulta.setParameter("tipoTramite2", null);
        }
        // Obtenemos la lista.
        List<TramiteEntidad> tramites = consulta.getResultList();

        // Cerramos el entity manager.
        em.close();

        // Imprimimos un mensaje de que se consultó una tabla.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tramites'.");
        if (tramites.isEmpty()) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'tramites' y no se obtuvieron resultados.");
            // Mandamos un mensaje de que no se encontró ningún trámite.
            throw new PersistenciaException("No se encontró ningún trámite.");
        }
        // Devolvemos la lista de trámites.
        return tramites;
    }

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
    @Override
    public List<TramiteEntidad> obtenerReporte(String nombre, Calendar fechaInicial, Calendar fechaFin, List<Class> tipos) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        TypedQuery<TramiteEntidad> consulta = em.createQuery(
                  "SELECT t"
                + " FROM TramiteEntidad t"
                + " WHERE (:nombre IS NULL OR t.persona.nombre = :nombre)"
                + "     AND (:fechaInicio IS NULL AND :fechaFin IS NULL "
                + "         OR (:fechaInicio IS NOT NULL AND t.fechaEmision >= :fechaInicio AND :fechaFin IS NULL) "
                + "         OR (:fechaFin IS NOT NULL AND t.fechaEmision <= :fechaFin AND :fechaInicio IS NULL) "
                + "         OR (t.fechaEmision BETWEEN :fechaInicio AND :fechaFin)) "
                + "     AND (:tipoTramite1 IS NULL OR Type(t) = :tipoTramite1 "
                + "         OR (:tipoTramite1 IS NULL OR Type(t) = :tipoTramite1 "
                + "         AND :tipoTramite2 IS NULL OR Type(t) = :tipoTramite2)) ", TramiteEntidad.class);
        consulta.setParameter("nombre", nombre);
        consulta.setParameter("fechaInicio", fechaInicial);
        consulta.setParameter("fechaFin", fechaFin);
        int i = 1;
        for (Class<?> tipo : tipos) {
            consulta.setParameter("tipoTramite" + i, tipo);
            i++;
        }
        if (tipos.size() < 2) {
            consulta.setParameter("tipoTramite2", null);
        }
        // Obtenemos la lista.
        List<TramiteEntidad> tramites = consulta.getResultList();

        // Cerramos el entity manager.
        em.close();

        // Imprimimos un mensaje de que se consultó una tabla.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tramites'.");
        if (tramites.isEmpty()) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'tramites' y no se obtuvieron resultados.");
            // Mandamos un mensaje de que no se encontró ningún trámite.
            throw new PersistenciaException("No se encontró ningún trámite.");
        }
        // Devolvemos la lista de trámites.
        return tramites;
    }
}
