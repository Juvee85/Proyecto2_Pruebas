/*
 * TarifaLicenciaDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.TarifaLicenciaEntidad;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz ITarifaLicenciaDAO y define los métodos para
 * realizar operaciones de consulta relacionadas con la entidad TarifaLicencia
 * en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TarifaLicenciaDAO implements ITarifaLicenciaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(TarifaLicenciaDAO.class.getName());

    /**
     * Método que obtiene una lista con todas las tarifas de licencia
     * disponibles.
     *
     * @return Lista con todas las tarifas de licencia disponibles.
     */
    @Override
    public List<TarifaLicenciaEntidad> obtenerTarifas() {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Construimos una instancia de CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
        CriteriaQuery<TarifaLicenciaEntidad> cq = cb.createQuery(TarifaLicenciaEntidad.class);
        // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
        // se hará la consulta.
        Root<TarifaLicenciaEntidad> root = cq.from(TarifaLicenciaEntidad.class);

        // Con esta línea especificamos que la consulta seleccionará todos los
        // campos de TarifaLicenciaEntidad.
        cq.select(root);

        // Obtenemos la lista de tarifas disponibles.
        List<TarifaLicenciaEntidad> listaTarifasLicencias = em.createQuery(cq).getResultList();

        // Obtenemos la cantidad de resultados.
        int i = 0;
        for (TarifaLicenciaEntidad listaTarifasLicencia : listaTarifasLicencias) {
            i++;
        }

        // Imprimimos un mensaje de que se ejecutó una consulta.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tarifas_licencia' y se obtuvieron " + i + " resultados.");

        // Cerramos el entity manager.
        em.close();

        // Retornamos la lista de tarifas.
        return listaTarifasLicencias;
    }

    /**
     * Método que devuelve la tarifa cuya vigencia es la misma que la recibida
     * en el parámeto.
     *
     * @param vigencia Vigencia de la tarifa que se busca.
     * @return La tarifa que se haya encontrado.
     */
    @Override
    public TarifaLicenciaEntidad buscarTarifa(String vigencia) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Construimos una instancia de CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
        CriteriaQuery<TarifaLicenciaEntidad> cq = cb.createQuery(TarifaLicenciaEntidad.class);
        // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
        // se hará la consulta.
        Root<TarifaLicenciaEntidad> root = cq.from(TarifaLicenciaEntidad.class);

        // Con esta línea especificamos que la consulta seleccionará todos los
        // campos de TarifaLicenciaEntidad pero con el filtro de que sólo sea
        // la tarifa con la misma vigencia que la del parámetro.
        cq.select(root).where(cb.equal(root.get("vigencia"), vigencia));

        // Obtenemos la tarifa.
        TarifaLicenciaEntidad tarifa = em.createQuery(cq).getSingleResult();

        // Imprimimos un mensaje de que se obtuvo un resultado.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tarifas_licencia' y se obtuvo un resultado.");

        // Cerramos el entity manager.
        em.close();

        // Retornamos la tarifa
        return tarifa;
    }

}
