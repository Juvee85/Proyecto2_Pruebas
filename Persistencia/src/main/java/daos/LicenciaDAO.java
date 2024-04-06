/*
 * LicenciaDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz ILicenciaDAO y proporciona los métodos para
 * realizar operaciones relacionadas con la entidad Licencia en la base de
 * datos.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class LicenciaDAO implements ILicenciaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(LicenciaDAO.class.getName());

    /**
     * Método para buscar la última licencia asociada a una persona.
     * @param persona Persona que está solicitando una nueva licencia.
     * @return La licencia que se haya encontrado.
     * @throws PersistenciaException si no se encontró ninguna licencia.
     */
    @Override
    public LicenciaEntidad buscarUltimaLicencia(PersonaEntidad persona) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<LicenciaEntidad> cq = cb.createQuery(LicenciaEntidad.class);
            // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<LicenciaEntidad> root = cq.from(LicenciaEntidad.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de LicenciaEntidad donde el atributo 'activa' sea true y el
            // atributo 'persona' sea el mismo que el recibido en el parámetro.
            cq.select(root).where(
                    cb.equal(root.get("persona"), persona))
                    .orderBy(cb.desc(root.get("fechaEmision")));

            // Obtenemos la última licencia.
            LicenciaEntidad ultimaLicencia = em.createQuery(cq).setMaxResults(1).getSingleResult();

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'licencias' y se obtuvo 1 resultado.");

            // Retornamos la licencia obtenida.
            return ultimaLicencia;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'licencias' y no se obtuvieron resultados.");
            // Lanzamos una excepción de que no se encontró ninguna licencia.
            throw new PersistenciaException("No se encontró ninguna licencia activa.");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método para desactivar una licencia.
     * @param licencia Licencia a desactivar.
     */
    @Override
    public void desactivarLicencia(LicenciaEntidad licencia) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a actualizar la licencia.
        em.merge(licencia);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se actualizó una licencia.
        logger.log(Level.INFO, "Se ha actualizado 1 licencia correctamente.");
    }

    /**
     * Método para insertar una licencia en la base de datos.
     * @param licencia Licencia que se quiere insertar.
     */
    @Override
    public void insertarLicencia(LicenciaEntidad licencia) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir la licencia.
        em.persist(licencia);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró una licencia.
        logger.log(Level.INFO, "Se ha insertado 1 licencia correctamente.");
    }

}
