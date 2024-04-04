package daos;

import conexion.Conexion;
import conexion.IConexion;
import static entidades.PersonaEntidad_.curp;
import entidades.TarifaLicenciaEntidad;
import excepciones.PersistenciaException;
import java.util.List;
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
public class TarifaLicenciaDAO implements ITarifaLicenciaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(TarifaLicenciaDAO.class.getName());
    
    @Override
    public List<TarifaLicenciaEntidad> obtenerTarifas(boolean discapacitado) {
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
                
        // Imprimimos un mensaje de que se ejecutó una consulta.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tarifas_licencia'.");
        
        // Cerramos el entity manager.
        em.close();

        // Retornamos true si hay más de 0 registros, y false en caso contrario.
        return listaTarifasLicencias;
    }

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
        // campos de TarifaLicenciaEntidad.
        cq.select(root).where(cb.equal(root.get("vigencia"), vigencia));

        // Obtenemos la lista de tarifas disponibles.
        TarifaLicenciaEntidad tarifas = em.createQuery(cq).getSingleResult();
                
        // Imprimimos un mensaje de que se ejecutó una consulta.
        logger.log(Level.INFO, "Se ha consultado la tabla 'tarifas_licencia'.");
        
        // Cerramos el entity manager.
        em.close();

        // Retornamos true si hay más de 0 registros, y false en caso contrario.
        return tarifas;
    }

}
