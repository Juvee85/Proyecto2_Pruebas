package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
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
public class LicenciaDAO implements ILicenciaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(LicenciaDAO.class.getName());
    
    @Override
    public LicenciaEntidad buscarLicencia(PersonaEntidad persona) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();        

        // Construimos una instancia de CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
        CriteriaQuery<LicenciaEntidad> cq = cb.createQuery(LicenciaEntidad.class);
        // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
        // se hará la consulta.
        Root<LicenciaEntidad> root = cq.from(LicenciaEntidad.class);

        // Con esta línea especificamos que la consulta seleccionará todos los
        // campos de TarifaLicenciaEntidad.
        cq.select(root).where(
                cb.isTrue(root.get("activa")),
                cb.and(cb.equal(root.get("persona"), persona)));

        LicenciaEntidad ultimaLicencia = null;
        try {
            // Obtenemos la lista de tarifas disponibles.
            ultimaLicencia = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'licencias' y se obtuvo 1 resultado.");
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'licencias' y no se obtuvieron resultados.");
        }
        
        // Cerramos el entity manager.
        em.close();
        
        // Retornamos true si hay más de 0 registros, y false en caso contrario.
        return ultimaLicencia;
    }

    @Override
    public void desactivarLicencia(LicenciaEntidad licencia) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();
        
        // Iniciamos la transacción.
        em.getTransaction().begin();
        
        // Mandamos a persistir la persona.
        em.merge(licencia);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se se registró una persona.
        logger.log(Level.INFO, "Se ha actualizado 1 licencia correctamente.");
    }

    @Override
    public void insertarLicencia(LicenciaEntidad licencia) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();
        
        // Iniciamos la transacción.
        em.getTransaction().begin();
        
        // Mandamos a persistir la persona.
        em.persist(licencia);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se se registró una persona.
        logger.log(Level.INFO, "Se ha insertado 1 licencia correctamente.");
    }

}
