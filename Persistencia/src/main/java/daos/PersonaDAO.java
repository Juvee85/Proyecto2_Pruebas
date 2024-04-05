/*
 * PersonaDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
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
 * Clase que implementa la interfaz IPersonaDAO y proporciona los métodos para
 * realizar operaciones relacionadas con la entidad Persona en la base de datos.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PersonaDAO implements IPersonaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(PersonaDAO.class.getName());

    /**
     * Método que indica si hay personas o no registradas en la base de datos.
     * @return Verdadero si ya hay registros en la base de datos, y false si no
     * los hay.
     */
    @Override
    public boolean hayPersonas() {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Definimos la consulta a realizar con JQPL.
        String consulta = """
                          SELECT COUNT(p)
                          FROM PersonaEntidad p
                          """;

        // Obtenemos la cantidad de registros.
        Long cantidad = em.createQuery(consulta, Long.class).getSingleResult();

        // Cerramos el entity manager.
        em.close();

        // Imprimimos un mensaje de cuántas personas hay registradas.
        logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y se han obtenido " + cantidad + " resultados.");

        // Retornamos true si hay más de 0 registros, y false en caso contrario.
        return cantidad > 0L;
    }

    /**
     * Método que inserta una persona en la base de datos.
     * @param persona Persona a la cual se va a insertar.
     */
    @Override
    public void insertarPersona(PersonaEntidad persona) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir la persona.
        em.persist(persona);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró una persona.
        logger.log(Level.INFO, "Se ha insertando 1 persona correctamente.");
    }

    /**
     * Método que busca una persona dada una CURP.
     * @param curp CURP de la persona que se busca.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encontró a ninguna persona con
     * la CURP dada.
     */
    @Override
    public PersonaEntidad buscarPorCurp(String curp) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();
        
        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<PersonaEntidad> cq = cb.createQuery(PersonaEntidad.class);
            // Creamos una instancia instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<PersonaEntidad> root = cq.from(PersonaEntidad.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de PersonaEntidad donde la CURP coincida con la buscada.
            cq.select(root).where(cb.like(root.get("curp"), curp));

            // Se manda a ejecutar la consulta y guardamos el resultado.
            PersonaEntidad personaEntidad = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y se obtuvo 1 resultado.");

            // Retornamos la persona encontrada.
            return personaEntidad;
        } catch (NoResultException nre) {
            // Si no se obtuvo nada, imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y no se obtuvieron resultados.");
            // Lanzamos una excepción de que no se encontró a nadie con la CURP proporcionada.
            throw new PersistenciaException("No se encontró ninguna persona con la CURP: " + curp);
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }
    
}
