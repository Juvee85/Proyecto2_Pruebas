/*
 * AutomovilDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.AutomovilEntidad;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz IAutomovilDAO y define los métodos para
 * realizar operaciones relacionadas con la entidad Automovil en la base de
 * datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class AutomovilDAO implements IAutomovilDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(AutomovilDAO.class.getName());

    /**
     * Método que devuelve verdadero si el automóvil con el número de serie
     * proporcionado está registrado en la base de datos.
     *
     * @param numSerie Número de serie del automóvil.
     * @return Verdadero si el automóvil está registrado, falso en caso
     * contrario.
     */
    @Override
    public boolean estaRegistrado(String numSerie) {
        // Obtenemos el automóvil y si es diferente a null, retornamos true.
        // Retornamos false en caso contrario.
        return (obtenerAutomovil(numSerie) != null);
    }

    /**
     * Método para obtener un automóvil dado un número de serie.
     *
     * @param numSerie Número de serie del automóvil buscado.
     * @return El automóvil si se encuentra, null si no se encuentra.
     */
    @Override
    public AutomovilEntidad obtenerAutomovil(String numSerie) {
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
            // campos de AutomovilEntidad donde el número de serie coincida con el
            // proporcionado.
            cq.select(root).where(cb.equal(root.get("numeroSerie"), numSerie));

            // Se manda a ejecutar la consulta y guardamos el resultado.
            AutomovilEntidad automovil = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo 1 resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'automoviles' y se obtuvo 1 resultado.");

            // Retornamos el automóvil encontrado.
            return automovil;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'automoviles' y no se obtuvieron resultados.");

            return null;
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método para insertar un automóvil en la base de datos.
     *
     * @param automovil Automóvil a insertar.
     */
    @Override
    public void insertarAutomovil(AutomovilEntidad automovil) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir el automóvil.
        em.persist(automovil);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró un automóvil.
        logger.log(Level.INFO, "Se ha insertado 1 automóvil correctamente.");
    }

}
