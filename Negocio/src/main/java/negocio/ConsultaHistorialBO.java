/*
 * ConsultaHistorialBO.java
 */
package negocio;

import daos.IPersonaDAO;
import daos.ITramiteDAO;
import daos.PersonaDAO;
import daos.TramiteDAO;
import dtos.PersonaDTO;
import dtos.TramiteDTO;
import entidades.PersonaEntidad;
import entidades.TramiteEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Encriptador;
import utilidades.Paginacion;

/**
 * Clase con la implementación de la interfaz IConsultaHistorialBO para realizar
 * la consulta de trámites.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class ConsultaHistorialBO implements IConsultaHistorialBO {

    private IPersonaDAO personaDAO = new PersonaDAO();
    private ITramiteDAO tramiteDAO = new TramiteDAO();
    private static final Logger logger = Logger.getLogger(ConsultaHistorialBO.class.getName());
    private int numeroElementos;

    /**
     * Constructor que inicializa el número de elementos.
     *
     * @param numeroElementos Número de elementos a mostrar por página.
     */
    public ConsultaHistorialBO(int numeroElementos) {
        this.numeroElementos = numeroElementos;
    }

    /**
     * Obtiene la lista comlpleta de personas registradas en el sistema
     *
     * @param pagina El número de página de los resultados que se desea
     * consultar
     * @return La lista con las personas registradas
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos
     */
    @Override
    public List<PersonaDTO> obtenerPersonas(int pagina) throws NegocioException {
        try {
            // Obtenemos la lista de personas.
            List<PersonaEntidad> personaEntidades = personaDAO.obtenerPersonas(numeroElementos,
                    Paginacion.obtenerOffset(numeroElementos, pagina));
            // La convertimos a DTO.
            List<PersonaDTO> personaDTOs = new ArrayList<>();
            for (PersonaEntidad personaEntidad : personaEntidades) {
                personaDTOs.add(new PersonaDTO(personaEntidad));
            }
            // Devolvemos el DTO.
            return personaDTOs;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ninguna persona.
            logger.log(Level.WARNING, "No se obtuvo ninguna persona.");
            // Lanzamos una exceción indicando que no se encontró ninguna persona.
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Método que obtiene una persona por su CURP.
     *
     * @param curp CURP de la persona que se desea consultar
     * @return Persona con CURP coincidenta a la dada
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos.
     */
    @Override
    public PersonaDTO obtenerPersonaPorCURP(String curp) throws NegocioException {
        try {
            // Buscamos una persona con la CURP dada y la asignamos a una entidad.
            PersonaEntidad personaEntidad = personaDAO.buscarPorCurp(curp);
            // Convertimos la entidad a DTO.
            PersonaDTO personaDTO = new PersonaDTO(personaEntidad);
            // Regresamos el DTO.
            return personaDTO;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ninguna persona.
            logger.log(Level.WARNING, "No se obtuvo ninguna persona con la CURP: " + curp + ".");
            // Lanzamos una exceción indicando que no se encontró ninguna persona
            // con la CURP proporcionada.
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Obtiene una lista de personas con nombre coincidente al dado por el
     * parametro.
     *
     * @param nombre Nombre de persona que se desea buscar
     * @param pagina El número de página de los resultados que se desea
     * consultar
     * @return Lista de personas con nombre coincidente al dado
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos
     */
    @Override
    public List<PersonaDTO> obtenerPersonasPorNombre(String nombre, int pagina) throws NegocioException {
        try {
            // Creamos una instancia para encriptar el nombre.
            Encriptador encriptador = new Encriptador();
            // Buscamos personas con el nombre dado.
            List<PersonaEntidad> personaEntidades = personaDAO.buscarPorNombre(
                    encriptador.encriptar(nombre), numeroElementos,
                    Paginacion.obtenerOffset(numeroElementos, pagina));
            // Convertimos la lista a DTO.
            List<PersonaDTO> personaDTOs = new ArrayList<>();
            for (PersonaEntidad personaEntidad : personaEntidades) {
                personaDTOs.add(new PersonaDTO(personaEntidad));
            }
            // Retornamos el DTO.
            return personaDTOs;
        } catch (Exception pe) {
            // Mandamos un mensaje a la consola de que no se encontró ninguna persona.
            logger.log(Level.WARNING, "No se obtuvo ninguna persona con el nombre: " + nombre + ".");
            // Lanzamos una exceción indicando que no se encontró ninguna persona
            // con el nombre proporcionada.
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Método que obtiene una lista de personas con año de nacimiento
     * coincidente al dado por el parametro.
     *
     * @param anio Año de nacimiento del que se desea buscar
     * @param pagina El número de página de los resultados que se desea
     * consultar
     * @return Lista de personas con año de nacimiento coincidente al dado
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos
     */
    @Override
    public List<PersonaDTO> obtenerPersonasPorAnioNacimiento(int anio, int pagina) throws NegocioException {
        try {
            // Obtenemos la lista de personas coincidentes.
            List<PersonaEntidad> personaEntidades = personaDAO.buscarPorAnioNacimiento(
                    anio, numeroElementos,
                    Paginacion.obtenerOffset(numeroElementos, pagina));
            // Convertimos la lista a DTO.
            List<PersonaDTO> personaDTOs = new ArrayList<>();
            for (PersonaEntidad personaEntidad : personaEntidades) {
                personaDTOs.add(new PersonaDTO(personaEntidad));
            }
            // Devolvemos la DTO.
            return personaDTOs;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ninguna persona.
            logger.log(Level.WARNING, "No se obtuvo ninguna persona nacida en el año: " + anio + ".");
            // Lanzamos una exceción indicando que no se encontró ninguna persona
            // con el año de nacimiento proporcionada.
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Método que obtiene la lista de trámites de una persona.
     *
     * @param curp Curp de la persona de la cual se obtiene la lista de trámites
     * @param pagina El número de página de los resultados que se desea
     * consultar
     * @return Lista de trámites de la persona cuya CURP coincide con la dada
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos
     */
    @Override
    public List<TramiteDTO> obtenerTramitesPorPersona(String curp, int pagina) throws NegocioException {
        try {
            // Obtenemos la entidad de la persona.
            PersonaEntidad persona = personaDAO.buscarPorCurp(curp);
            // Obtenemos la lista de trámites asociados a la persona.
            List<TramiteEntidad> tramiteEntidades = tramiteDAO.obtenerTramitesPorPersona(
                    persona, numeroElementos,
                    Paginacion.obtenerOffset(numeroElementos, pagina));
            // Convertimos la lista a DTO.
            List<TramiteDTO> tramiteDTOs = new ArrayList<>();
            for (TramiteEntidad tramiteEntidad : tramiteEntidades) {
                // Se transforma la fecha al formate a desplegar
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = formatter.format(tramiteEntidad.getFechaEmision().getTime());

                TramiteDTO dto = new TramiteDTO(
                        tramiteEntidad.getClass(),
                        tramiteEntidad.getCosto() + "",
                        fecha);
                tramiteDTOs.add(dto);
            }
            // Retornamos el DTO.
            return tramiteDTOs;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ningun trámite.
            logger.log(Level.WARNING, "No se obtuvo ningun tramite");
            // Lanzamos una exceción indicando que no se encontró ningun trámite
            // asociado a la persona.
            throw new NegocioException(pe.getMessage());
        }
    }

}
