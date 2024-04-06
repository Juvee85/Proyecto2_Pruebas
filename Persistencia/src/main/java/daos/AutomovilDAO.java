package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.AutomovilEntidad;
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
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class AutomovilDAO implements IAutomovilDAO {
    
    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(AutomovilDAO.class.getName());

    @Override
    public boolean estaRegistrado(String numSerie) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();
        
        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<AutomovilEntidad> cq = cb.createQuery(AutomovilEntidad.class);
            // Creamos una instancia instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<AutomovilEntidad> root = cq.from(AutomovilEntidad.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de PersonaEntidad donde la CURP coincida con la buscada.
            cq.select(root).where(cb.like(root.get("numeroSerie"), numSerie));

            // Se manda a ejecutar la consulta y guardamos el resultado.
            AutomovilEntidad automovil = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'automoviles' y se obtuvo 1 resultado.");

            // Retornamos la persona encontrada.
            return true;
        } catch (NoResultException nre) {
            // Si no se obtuvo nada, imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'automoviles' y no se obtuvieron resultados.");
            return false;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

}
