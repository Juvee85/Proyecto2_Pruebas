package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.AutomovilEntidad;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
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
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PlacasDAO implements IPlacasDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(PlacasDAO.class.getName());
    
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
            // campos de LicenciaEntidad donde el atributo 'activa' sea true y el
            // atributo 'persona' sea el mismo que el recibido en el parámetro.
            cq.select(root).where(cb.equal(root.get("numero"), numPlaca));

            // Obtenemos la última licencia.
            PlacasEntidad placa = em.createQuery(cq).setMaxResults(1).getSingleResult();

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y se obtuvo 1 resultado.");

            // Retornamos la licencia obtenida.
            return true;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'placas' y no se obtuvieron resultados.");
            // Lanzamos una excepción de que no se encontró ninguna licencia.
            return false;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    @Override
    public void insertarPlacas(PlacasEntidad placas) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir la licencia.
        em.persist(placas);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró una licencia.
        logger.log(Level.INFO, "Se ha insertado 1 placa correctamente.");
    }

    @Override
    public PlacasEntidad buscarUltimasPlacas(String numSerie) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void desactivarPlacas(PlacasEntidad ultimasPlacas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
