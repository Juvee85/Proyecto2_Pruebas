package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.PlacasEntidad;
import entidades.RelacionVehiculoPersona;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class RelacionVehiculoPersonaDAO implements IRelacionVehiculoPersonaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(RelacionVehiculoPersonaDAO.class.getName());

    @Override
    public boolean existeDetalle(RelacionVehiculoPersona rvp) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<RelacionVehiculoPersona> cq = cb.createQuery(RelacionVehiculoPersona.class);
            // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<RelacionVehiculoPersona> root = cq.from(RelacionVehiculoPersona.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de LicenciaEntidad donde el atributo 'activa' sea true y el
            // atributo 'persona' sea el mismo que el recibido en el parámetro.
            cq.select(root).where(cb.equal(root.get("persona"), rvp.getPersona()),
                    cb.equal(root.get("vehiculo"), rvp.getVehiculo()));

            // Obtenemos la última licencia.
            RelacionVehiculoPersona rvpResultado = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'vehículos_persona' y se obtuvo 1 resultado.");

            // Retornamos la licencia obtenida.
            return true;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'vehículos_persona' y no se obtuvieron resultados.");
            // Lanzamos una excepción de que no se encontró ninguna licencia.
            return false;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    @Override
    public void insertarDetalle(RelacionVehiculoPersona rvp) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir la licencia.
        em.persist(rvp);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró una licencia.
        logger.log(Level.INFO, "Se ha insertado 1 placa correctamente.");

    }

}
