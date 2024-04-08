/*
 * RelacionVehiculoPersonaDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.RelacionVehiculoPersona;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz IRelacionVehiculoPersonaDAO y define los
 * métodos para realizar operaciones relacionadas con la entidad
 * RelacionVehiculoPersona en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class RelacionVehiculoPersonaDAO implements IRelacionVehiculoPersonaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(RelacionVehiculoPersonaDAO.class.getName());

    /**
     * Método que devuelve verdadero si ya existe una relación entre el vehículo
     * y la persona, devuelve falso si no la hay.
     *
     * @param rvp Relación que hay entre un vehículo y una persona.
     * @return Verdadero si ya existe una relación entre el vehículo y la
     * persona, devuelve falso si no la hay.
     */
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
            // campos de RelacionVehiculoPersona donde los atributos 'persona' y
            // 'vehiculos' sean iguales a los de la relación proporcionada.
            cq.select(root).where(cb.equal(root.get("persona"), rvp.getPersona()),
                    cb.equal(root.get("vehiculo"), rvp.getVehiculo()));

            // Obtenemos el resultado.
            RelacionVehiculoPersona rvpResultado = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo un resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'vehículos_persona' y se obtuvo 1 resultado.");

            // Retornamos la relación.
            return true;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que se no obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'vehículos_persona' y no se obtuvieron resultados.");
            return false;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método que inserta una relación entre un vehículo y una persona en la
     * base de datos.
     *
     * @param rvp Relación entre un vehículo y una persona.
     */
    @Override
    public void insertarDetalle(RelacionVehiculoPersona rvp) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir la relación.
        em.persist(rvp);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró una relación.
        logger.log(Level.INFO, "Se ha insertado 1 relación correctamente.");
    }

}
