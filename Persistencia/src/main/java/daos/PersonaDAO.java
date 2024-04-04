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
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PersonaDAO implements IPersonaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(PersonaDAO.class.getName());
    
    /**
     * Método que indica si hay personas o no registradas en la base de datos.
     * @return Verdadero si ya hay registros en la base de datos, y false si
     * no los hay.
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
        logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y se han obtenido " + cantidad + " filas.");
        
        // Retornamos true si hay más de 0 registros, y false en caso contrario.
        return cantidad > 0L;
    }

    /**
     * Método que inserta una persona en la base de datos.
     * @param persona Persona a insertar.
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

        // Imprimimos un mensaje de que se se registró una persona.
        logger.log(Level.INFO, "Se ha insertando 1 persona correctamente.");
    }

    /**
     * Clase para obtener a una persona según su CURP.
     * @param curp CURP de la persona a buscar.
     * @return La persona si se encontró.
     * @throws excepciones.PersistenciaException
     */
    @Override
    public PersonaEntidad buscarPorCurp(String curp) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();        

        // Construimos una instancia de CriteriaBuilder.
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
        CriteriaQuery<PersonaEntidad> cq = cb.createQuery(PersonaEntidad.class);
        // Creamos una instancia instanciadel tipo Root para indicar de qué entidad
        // se hará la consulta.
        Root<PersonaEntidad> root = cq.from(PersonaEntidad.class);

        // Con esta línea especificamos que la consulta seleccionará todos los
        // campos de PersonaEntidad donde la curp coincida con la buscada.
        cq.select(root).where(cb.like(root.get("curp"), curp));

        // Creamos un objeto PersonaEntidad.
        PersonaEntidad personaEntidad = null;
        try {
            // Se manda a ejecutar la consulta.
            personaEntidad = em.createQuery(cq).getSingleResult();
        } catch (NoResultException nre) {
            // Si no se obtuvo nada, imprimimos un mensaje de que se ejecutó una consulta y que no hubo resultados.
            logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y se obtuvieron 0 resultados.");
            throw new PersistenciaException("No se encontró ninguna persona con la curp: " + curp);
        }
        
        // Imprimimos un mensaje de que se ejecutó una consulta y que hubo 1 resultado.
        logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y se obtuvo 1 resultado.");
        
        // Cerramos el entity manager.
        em.close();

        // Retornamos true si hay más de 0 registros, y false en caso contrario.
        return personaEntidad;
    }

}
