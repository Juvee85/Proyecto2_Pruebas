/*
 * PersonaDAO.java
 */
package daos;

import conexion.Conexion;
import conexion.IConexion;
import entidades.PersonaEntidad;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Clase que implementa la interfaz IPersonaDAO y define los métodos para
 * realizar operaciones relacionadas con la entidad Persona en la base de datos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PersonaDAO implements IPersonaDAO {

    private final IConexion conexion = new Conexion();
    private static final Logger logger = Logger.getLogger(PersonaDAO.class.getName());

    /**
     * Método que devuelve verdadero si hay personas registradas en la base de
     * datos, devuelve falso en caso contrario.
     *
     * @return Verdadero si ya hay registros en la base de datos, y falso si no
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
     *
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

        // Imprimimos un mensaje de que se insertó una persona.
        logger.log(Level.INFO, "Se ha insertando 1 persona correctamente.");
    }

    /**
     * Método que busca una persona dada una CURP.
     *
     * @param curp CURP de la persona que se busca.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encontró a ninguna persona con la
     * CURP dada.
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
            cq.select(root).where(cb.equal(root.get("curp"), curp));

            // Se manda a ejecutar la consulta y guardamos el resultado.
            PersonaEntidad personaEntidad = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo 1 resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y se obtuvo 1 resultado.");

            // Retornamos la persona encontrada.
            return personaEntidad;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'personas' y no se obtuvieron resultados.");
            // Lanzamos una excepción de que no se encontró a nadie con la CURP proporcionada.
            throw new PersistenciaException("No se encontró ninguna persona con la CURP: " + curp);
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Obtiene la lista de personas cuyo nombre coincide con el dado.
     *
     * @param nombre Nombre de persona que se desea buscar
     * @param limite Limite de elementos a incluir por página
     * @param offset offset Indice de elemento donde inicia la página
     * @return Lista con entidades de personas cuyo nombre coincide con el dado
     * @throws PersistenciaException si no encuentra a ninguna persona.
     */
    @Override
    public List<PersonaEntidad> buscarPorNombre(String nombre, int limite, int offset) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            TypedQuery consulta = em.createQuery("SELECT p "
                    + "FROM PersonaEntidad p "
                    + "WHERE p.nombre LIKE '" + nombre + "'", PersonaEntidad.class)
                    .setFirstResult(offset)
                    .setMaxResults(limite);
            List<PersonaEntidad> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception e) {
            logger.log(Level.INFO, "No se pudo completar la consulta");
            // Lanzamos una excepción de que no se encontró a nadie con la CURP proporcionada.
            throw new PersistenciaException("Ocurrio un error al realizar la consulta");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Obtiene la lista de personas cuyo año de nacimiento coincide con el dado.
     *
     * @param anio Año de nacimiento que se desea buscar
     * @param limite Limite de elementos a incluir por página
     * @param offset offset Indice de elemento donde inicia la página
     * @return Una lista con las personas encontradas.
     * @throws PersistenciaException si no encuentra a ninguna persona.
     */
    @Override
    public List<PersonaEntidad> buscarPorAnioNacimiento(int anio, int limite, int offset) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            TypedQuery consulta = em.createQuery(""
                    + "SELECT p"
                    + " FROM PersonaEntidad p"
                    + " WHERE EXTRACT(YEAR FROM p.fechaNacimiento) = " + anio, PersonaEntidad.class)
                    .setFirstResult(offset)
                    .setMaxResults(limite);
            List<PersonaEntidad> resultado = consulta.getResultList();
            return resultado;
        } catch (Exception e) {
            logger.log(Level.INFO, "No se pudo completar la consulta");
            // Lanzamos una excepción de que no se encontró a nadie con la CURP proporcionada.
            throw new PersistenciaException("Ocurrio un error al realizar la consulta");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Obtiene la lista completa de personas registradas.
     *
     * @param limite Limite de elementos a incluir por página
     * @param offset Indice de elemento donde inicia la página
     * @return Lista de todas las personas registradas
     * @throws PersistenciaException si no encuentra a ninguna persona.
     */
    @Override
    public List<PersonaEntidad> obtenerPersonas(int limite, int offset) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            // Seleccionamos todas las personas registradas.
            TypedQuery consulta = em.createQuery("SELECT p"
                    + " FROM PersonaEntidad p", PersonaEntidad.class)
                    .setFirstResult(offset)
                    .setMaxResults(limite);
            // Obtenemos la lista.
            List<PersonaEntidad> resultado = consulta.getResultList();
            return resultado; // La retornamos.
        } catch (Exception e) {
            logger.log(Level.INFO, "No se pudo completar la consulta.");
            // Lanzamos una excepción de que no se encontró a nadie con la CURP proporcionada.
            throw new PersistenciaException("Ocurrió un error al realizar la consulta.");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

}
