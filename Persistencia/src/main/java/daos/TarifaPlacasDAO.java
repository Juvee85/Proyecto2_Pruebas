/*
 * TarifaPlacasDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.TarifaPlacasEntidad;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz ITarifaPlacasDAO y define los métodos para
 * realizar operaciones de consulta relacionadas con la entidad TarifaPlacas en
 * la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TarifaPlacasDAO implements ITarifaPlacasDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(TarifaPlacasDAO.class.getName());

    /**
     * Método que devuelve la tarifa cuyo tipo es el mismo que el recibido en el
     * parámeto.
     *
     * @param tipo Tipo de vehículo al cual se le quieren sacar placas (nuevo,
     * usado).
     * @return La tarifa encontrada.
     */
    @Override
    public TarifaPlacasEntidad buscarTarifa(String tipo) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Construimos una instancia de CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
        CriteriaQuery<TarifaPlacasEntidad> cq = cb.createQuery(TarifaPlacasEntidad.class);
        // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
        // se hará la consulta.
        Root<TarifaPlacasEntidad> root = cq.from(TarifaPlacasEntidad.class);

        // Con esta línea especificamos que la consulta seleccionará todos los
        // campos de TarifaPlacasEntidad pero con el filtro de que sólo sean
        // las tarifas con el mismo tipo que el del parámetro.
        cq.select(root).where(cb.equal(root.get("tipo"), tipo));

        // Obtenemos la tarifa.
        TarifaPlacasEntidad tarifa = em.createQuery(cq).getSingleResult();

        // Imprimimos un mensaje de que se obtuvo un resultado.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tarifas_placas' y se obtuvo un resultado.");

        // Cerramos el entity manager.
        em.close();

        // Retornamos la tarifa
        return tarifa;
    }
}
