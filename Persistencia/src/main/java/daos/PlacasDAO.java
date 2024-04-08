/*
 * PlacasDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.AutomovilEntidad;
import entidades.PlacasEntidad;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz IPlacasDAO y proporciona los métodos para
 * realizar operaciones relacionadas con la entidad Placas en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PlacasDAO implements IPlacasDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(PlacasDAO.class.getName());

    /**
     * Método que devuelve verdadero si ya existe una placa registrada con el
     * número de placa recibido en el parámetro, devuelve falso en caso
     * contrario.
     *
     * @param numPlaca Número de placa a evaluar.
     * @return Verdadero si ya existe una placa registrada con el número de
     * placa recibido en el parámetro, devuelve falso en caso contrario.
     */
    @Override
    public boolean existePlaca(String numPlaca) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<PlacasEntidad> cq = cb.createQuery(PlacasEntidad.class);
            // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<PlacasEntidad> root = cq.from(PlacasEntidad.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de PlacasEntidad donde el atributo 'numero' es igual al número
            // de placa del parámetro.
            cq.select(root).where(cb.equal(root.get("numero"), numPlaca));

            // Obtenemos la placa.
            PlacasEntidad placa = em.createQuery(cq).setMaxResults(1).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo un resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y se obtuvo 1 resultado.");

            return true;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y no se obtuvieron resultados.");

            return false;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método que inserta unas placas en la base de datos.
     *
     * @param placas Placas a la cuales se van a insertar.
     */
    @Override
    public void insertarPlacas(PlacasEntidad placas) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir las placas.
        em.persist(placas);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró una placa.
        logger.log(Level.INFO, "Se ha insertado 1 placa correctamente.");
    }

    /**
     * Método para buscar un automóvil dado un número de placas las cuales deben
     * ser las últimas asociadas a dicho automóvil.
     *
     * @param numPlacas Número de las últimas placas asociadas a un automóvil.
     * @return El automóvil que se haya encontrado.
     * @throws PersistenciaException si no se encontró ningún automóvil.
     */
    @Override
    public AutomovilEntidad buscarAutoPlacas(String numPlacas) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<AutomovilEntidad> cq = cb.createQuery(AutomovilEntidad.class);
            // Creamos una instancia instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<PlacasEntidad> root = cq.from(PlacasEntidad.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de AutomovilEntidad donde el atributo 'numero' sea igual que
            // el número de placas del parámetro y el atributo 'activa' sea true..
            cq.select(root.get("vehiculo")).where(
                    cb.equal(root.get("numero"), numPlacas),
                    cb.isTrue(root.get("activa")));

            // Obtenemos la el automóvil.
            AutomovilEntidad auto = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo un resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y se obtuvo 1 resultado.");

            // Retornamos el automóvil.
            return auto;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y no se obtuvieron resultados.");
            // Lanzamos una excepción de que no se encontró ningun automóvil.
            throw new PersistenciaException("No se encontró ningún automóvil con la placa: " + numPlacas + ".");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método para obtener las últimas placas asociadas a un automóvil con base
     * al número de serie de dicho automóvil.
     *
     * @param numSerie Número de serie del automóvil del cual se buscan las
     * últimas placas.
     * @return Las placas que se hayan encontrado.
     */
    @Override
    public PlacasEntidad obtenerUltimasPlacas(String numSerie) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Construimos una instancia de CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
        CriteriaQuery<PlacasEntidad> cq = cb.createQuery(PlacasEntidad.class);
        // Creamos una instancia instancia del tipo Root para indicar de qué entidad
        // se hará la consulta.
        Root<PlacasEntidad> root = cq.from(PlacasEntidad.class);

        // Con esta línea especificamos que la consulta seleccionará todos los
        // campos de PlacasEntidad donde el atributo 'numeroSerie' del atributo
        // 'vehiculo' coincida con el del parámetro, y donde el atributo 'activa'
        // sea true.
        cq.select(root).where(
                cb.equal(root.get("vehiculo").get("numeroSerie"), numSerie),
                cb.isTrue(root.get("activa")));

        // Obtenemos las placas.
        PlacasEntidad placas = em.createQuery(cq).setMaxResults(1).getSingleResult();

        // Imprimimos un mensaje de que se obtuvo un resultado.
        logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y se obtuvo 1 resultado.");

        // Cerramos el entity manager.
        em.close();

        // Retornamos las placas.
        return placas;
    }

    /**
     * Método para obtener el objeto entidad de las placas del parámetro.
     *
     * @param numPlacas Número de las placas que se quieren obtener.
     * @return Las placas que se hayan encontrado, null si no se encontró nada.
     */
    @Override
    public PlacasEntidad obtenerPlacas(String numPlacas) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<PlacasEntidad> cq = cb.createQuery(PlacasEntidad.class);
            // Creamos una instancia instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<PlacasEntidad> root = cq.from(PlacasEntidad.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de PlacasEntidad donde el atributo 'numero' con el número
            // de placas del parámetro.
            cq.select(root).where(cb.equal(root.get("numero"), numPlacas));

            // Obtenemos las placas.
            PlacasEntidad placas = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo un resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y se obtuvo 1 resultado.");

            // Retornamos la licencia obtenida.
            return placas;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y no se obtuvieron resultados.");
            return null;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método que desactiva las últimas placas de un automóvil.
     *
     * @param ultimasPlacas Placas a desactivar.
     */
    @Override
    public void desactivarPlacas(PlacasEntidad ultimasPlacas) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a actualizar las placas.
        em.merge(ultimasPlacas);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se actualizaron unas placas.
        logger.log(Level.INFO, "Se ha actualizado 1 placa correctamente.");
    }

}
