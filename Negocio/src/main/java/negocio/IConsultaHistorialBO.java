/*
 * IConsultaHistorialBO.java
 */
package negocio;

import dtos.PersonaDTO;
import dtos.TramiteDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz que define métodos para aplicar las reglas de negocio y llevar a
 * cabo la consulta de trámites de una persona.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IConsultaHistorialBO {

    /**
     * Obtiene la lista comlpleta de personas registradas en el sistema
     *
     * @param pagina El número de página de los resultados que se desea
     * consultar
     * @return La lista con las personas registradas
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos
     */
    public List<PersonaDTO> obtenerPersonas(int pagina) throws NegocioException;

    /**
     * Método que obtiene una persona por su CURP.
     *
     * @param curp CURP de la persona que se desea consultar
     * @return Persona con CURP coincidenta a la dada
     * @throws NegocioException Cuando ocurre un error que no permite completar
     * la operación en la base de datos.
     */
    public PersonaDTO obtenerPersonaPorCURP(String curp) throws NegocioException;

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
    public List<PersonaDTO> obtenerPersonasPorNombre(String nombre, int pagina) throws NegocioException;

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
    public List<PersonaDTO> obtenerPersonasPorAnioNacimiento(int anio, int pagina) throws NegocioException;

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
    public List<TramiteDTO> obtenerTramitesPorPersona(String curp, int pagina) throws NegocioException;
}
