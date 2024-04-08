/*
 * RegistroLicenciaBO.java
 */
package negocio;

import daos.ILicenciaDAO;
import dtos.PersonaDTO;
import daos.IPersonaDAO;
import daos.ITarifaLicenciaDAO;
import daos.LicenciaDAO;
import daos.PersonaDAO;
import daos.TarifaLicenciaDAO;
import dtos.LicenciaDTO;
import dtos.TarifaLicenciaDTO;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.TarifaLicenciaEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con la implementación de la interfaz IRegistroLicenciaBO para realizar
 * el registro de licencias.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class RegistroLicenciaBO implements IRegistroLicenciaBO {

    private IPersonaDAO personaDAO = new PersonaDAO();
    private ITarifaLicenciaDAO tarifaLicenciaDAO = new TarifaLicenciaDAO();
    private ILicenciaDAO licenciaDAO = new LicenciaDAO();
    private static final Logger logger = Logger.getLogger(RegistroLicenciaBO.class.getName());

    /**
     * Método para buscar a una persona dada una CURP.
     *
     * @param curp CURP de la persona a buscar.
     * @return Persona encontrada.
     * @throws NegocioException si no se encuentra ninguna persona.
     */
    @Override
    public PersonaDTO buscarPersonaCurp(String curp) throws NegocioException {
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
     * Método para validar que la persona solicitante cumple con los requisitos
     * para tramitar una licencia.
     *
     * @param persona Persona solicitante.
     * @throws NegocioException si la persona no cumple con alguno de los
     * requisitos.
     */
    @Override
    public void validarRequisitos(PersonaDTO persona) throws NegocioException {
        // Creamos una cadena para almacenar los errores.
        String errores = "";
        if (persona.getRfc() == null) { // Se valida que el solicitante tenga RFC.
            errores += "El solicitante no cuenta con RFC.\n";
        }

        if (persona.getTelefono() == null) { // Se valida que el solicitante tenga teléfono.
            errores += "El solicitante no cuenta con un número de teléfono.\n";
        }

        if (esMenorDeEdad(persona)) { // Se valida que el solicitante sea mayor de edad.
            errores += "El solicitante es menor de edad.\n";
        }

        if (!errores.equals("")) { // Si hubo errores.
            // Se manda un mensaje a consola de que se interrumpió el registro de
            // licencia.
            logger.log(Level.WARNING, "Registro de licencia interrumpido.");
            // Se manda una excepción con todos los errores ocurridos.
            throw new NegocioException(errores);
        }
    }

    /**
     * Método para calcular si una persona es menor de edad.
     *
     * @param persona Persona de quien se quiere calcular la edad.
     * @return Verdadero si la persona es menor de edad, falso si es mayor.
     */
    @Override
    public boolean esMenorDeEdad(PersonaDTO persona) {
        // Obtenemos la fecha de nacimiento del solicitante.
        Calendar fechaNac = persona.getFechaNacimiento();
        // Obtenemos la fecha de hoy.
        Calendar hoy = Calendar.getInstance();

        // Calculamos la edad del solicitante.
        int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        // Comprobamos si ya pasó su cumpleaños este año.
        if (fechaNac.get(Calendar.MONTH) > hoy.get(Calendar.MONTH)
                || (fechaNac.get(Calendar.MONTH) == hoy.get(Calendar.MONTH)
                && fechaNac.get(Calendar.DAY_OF_MONTH) > hoy.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        // Si es menor de edad retornamos true. Retornamos false en caso contrario.
        return edad < 18;
    }

    /**
     * Método para generar los datos de una licencia.
     *
     * @param persona Solicitante de la licencia.
     * @param tarifa Tipo de tarifa de la licencia.
     * @return Licencia con sus datos generados.
     */
    @Override
    public LicenciaDTO generarLicencia(PersonaDTO persona, TarifaLicenciaDTO tarifa) {
        // Se obtiene la fecha de emisión del tramite.
        Calendar fechaEmision = Calendar.getInstance();

        // Se define la variable costo y se obtiene el costo dependiendo de si el
        // solicitante está discapacitado o no.
        Float costo;
        if (persona.isDiscapacitado()) {
            costo = tarifa.getCostoDiscapacitado();
        } else {
            costo = tarifa.getCostoNormal();
        }

        // Se crea un objeto LicenciaDTO y se le mandan los datos recién generados,
        // además del argumento true para indicar que la licenca estará activa.
        LicenciaDTO licencia = new LicenciaDTO(fechaEmision, costo, true, tarifa);

        // Se retorna la licencia generada.
        return licencia;
    }

    /**
     * Método para obtener las tarifas de licencia que hay disponibles.
     *
     * @return Una lista con las tarifas encontradas.
     */
    @Override
    public List<TarifaLicenciaDTO> buscarTarifasLicencia() {
        // Obtenemos la lista de tarifas entidad.
        List<TarifaLicenciaEntidad> tarifasLicenciaEntidad = tarifaLicenciaDAO.obtenerTarifas();
        // Creamos una lista de tarifas DTO
        List<TarifaLicenciaDTO> tarifasLicenciaDTO = new ArrayList<>();

        // Iteramos sobre la lista de tarifas entidad y vamos convirtiéndolas a DTO.
        for (TarifaLicenciaEntidad tarifaLicenciaEntidad : tarifasLicenciaEntidad) {
            TarifaLicenciaDTO tarifaLicenciaDTO = new TarifaLicenciaDTO(tarifaLicenciaEntidad);
            // Añadimos cada tarifa DTO a la lista de DTOs.
            tarifasLicenciaDTO.add(tarifaLicenciaDTO);
        }

        // Retornamos la lista de las tarifas DTO.
        return tarifasLicenciaDTO;
    }

    /**
     * Método para agregar una licencia a la base de datos.
     *
     * @param curp CURP de la persona solicitante.
     * @param licenciaDTO Licencia que se agregará.
     */
    @Override
    public void agregarLicencia(String curp, LicenciaDTO licenciaDTO) {
        // Creamos un objeto para guardar a la persona.
        PersonaEntidad persona = null;
        try {
            // Obtenemos la persona que tiene la CURP proporcionada.
            persona = personaDAO.buscarPorCurp(curp);
        } catch (PersistenciaException pe) {
            // Aquí no hacemos nada porque llegados a este punto ya sabemos que
            // sí existe una persona con la CURP dada.
        }
        
        try {
            // Obtenemos la última licencia del solicitante.
            LicenciaEntidad ultimaLicencia = licenciaDAO.buscarUltimaLicencia(persona);
            // Indicamos que la licencia ya no estará activa.
            ultimaLicencia.setActiva(false);
            // Mandamos a desactivar la licencia.
            licenciaDAO.desactivarLicencia(ultimaLicencia);
            // Mandamos un mensaje de que se desactivó una licencia.
            logger.log(Level.INFO, "Se desactivó una licencia.");
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a consola de que no se encontró ninguna licencia
            // activa.
            logger.log(Level.INFO, pe.getMessage());
        }

        // Convertimos la licencia de DTO a entidad.
        LicenciaEntidad licenciaEntidad = convertirLicenciaDTO_Entidad(licenciaDTO);
        // Asociamos el solicitante a la licencia.
        licenciaEntidad.setPersona(persona);

        // Mandamos a insertar la licencia.
        licenciaDAO.insertarLicencia(licenciaEntidad);
    }

    /**
     * Método para convertir de PersonaDTO a PersonaEntidad.
     *
     * @param personaDTO Persona a convertir.
     * @return PersonaEntidad ya convertida.
     */
    @Override
    public PersonaEntidad convertirPersonaDTO_Entidad(PersonaDTO personaDTO) {
        PersonaEntidad personaEntidad = new PersonaEntidad(
                personaDTO.getNombre(),
                personaDTO.getApellidoPaterno(),
                personaDTO.getApellidoMaterno(),
                personaDTO.getCurp(),
                personaDTO.getRfc(),
                personaDTO.getTelefono(),
                personaDTO.isDiscapacitado(),
                personaDTO.getFechaNacimiento());
        // Retornamos la entidad.
        return personaEntidad;
    }

    /**
     * Método para convertir de LicenciaDTO a LicenciaEntidad.
     *
     * @param licenciaDTO LicenciaDTO a convertir.
     * @return LicenciaEntidad ya convertida.
     */
    @Override
    public LicenciaEntidad convertirLicenciaDTO_Entidad(LicenciaDTO licenciaDTO) {
        LicenciaEntidad licenciaEntidad = new LicenciaEntidad(
                licenciaDTO.getCosto(),
                licenciaDTO.getFechaEmision(),
                licenciaDTO.isActiva(),
                // Se busca la tarifa a la que está asociada la licencia.
                tarifaLicenciaDAO.buscarTarifa(licenciaDTO.getTarifa().getVigencia()));
        // Se retorna la LicenciaEntidad.
        return licenciaEntidad;
    }
}
