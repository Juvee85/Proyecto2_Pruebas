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
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class RegistroLicenciaBO implements IRegistroLicenciaBO {

    private IPersonaDAO personaDAO = new PersonaDAO();
    private ITarifaLicenciaDAO tarifaLicenciaDAO = new TarifaLicenciaDAO();
    private ILicenciaDAO licenciaDAO = new LicenciaDAO();
    private static final Logger logger = Logger.getLogger(InsercionMasivaBO.class.getName());

    @Override
    public PersonaDTO buscarCurp(String curp) throws NegocioException {
        try {
            // Buscamos una persona con la curp dada y la asignamos a un objeto.
            PersonaEntidad personaEntidad = personaDAO.buscarPorCurp(curp);
            // Creamos una PersonaDTO.
            PersonaDTO personaDTO = new PersonaDTO(personaEntidad);
            // Regresamos el objeto DTO.
            return personaDTO;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que hay personas registradas.
            logger.log(Level.WARNING, "No se obtuvo ninguna persona.");
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Método para validar que la persona solicitante cumple con los requisitos
     * para tramitar una licencia.
     *
     * @param persona Persona solicitante.
     * @throws NegocioException si la persona no cumple con los requisitos.
     */
    @Override
    public void validarRequisitos(PersonaDTO persona) throws NegocioException {
        if (persona.getRfc() == null) {
            throw new NegocioException("El solicitante no cuenta con RFC.");
        } else if (persona.getTelefono() == null) {
            throw new NegocioException("El solicitante no cuenta con un número de teléfono.");
        } else if (menorDeEdad(persona)) {
            throw new NegocioException("El solicitante es menor de edad.");
        }
    }

    public boolean menorDeEdad(PersonaDTO persona) {
        // Obtenemos la fecha de nacimiento del solicitante.
        Calendar fechaNac = persona.getFechaNacimiento();
        // Obtenemos la fecha de hoy.
        Calendar hoy = Calendar.getInstance();

        // Calculamos la edad del solicitante.
        int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        // Comprobamos si ya pasó su cumpleaños este año.
        if (fechaNac.get(Calendar.MONTH) > hoy.get(Calendar.MONTH) ||
           (fechaNac.get(Calendar.MONTH) == hoy.get(Calendar.MONTH) &&
            fechaNac.get(Calendar.DAY_OF_MONTH) > hoy.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        // Si es menor de edad retornamos true. Retornamos false en caso contrario.
        return edad < 18;
    }

    @Override
    public List<TarifaLicenciaDTO> buscarTarifasLicencia(boolean discapacitado) {
        List<TarifaLicenciaEntidad> tarifasLicenciaEntidad = tarifaLicenciaDAO.obtenerTarifas(discapacitado);
        List<TarifaLicenciaDTO> tarifasLicenciaDTO = new ArrayList<>();
        
        for (TarifaLicenciaEntidad tarifaLicenciaEntidad : tarifasLicenciaEntidad) {
            TarifaLicenciaDTO licenciaDTO = new TarifaLicenciaDTO(tarifaLicenciaEntidad);
            tarifasLicenciaDTO.add(licenciaDTO);
        }
        
        return tarifasLicenciaDTO;
    }

    @Override
    public void agregarLicencia(String curp, LicenciaDTO licenciaDTO) {
        try {
            PersonaEntidad persona = personaDAO.buscarPorCurp(curp);
            LicenciaEntidad ultimaLicencia = licenciaDAO.buscarLicencia(persona);
            if (ultimaLicencia != null) {
                ultimaLicencia.setActiva(false);
                licenciaDAO.desactivarLicencia(ultimaLicencia);
            }
            LicenciaEntidad licenciaEntidad = convertirLicenciaDTO_Entidad(licenciaDTO, persona);
            licenciaEntidad.setPersona(persona);
            licenciaDAO.insertarLicencia(licenciaEntidad);
        } catch (PersistenciaException ex) {
            Logger.getLogger(RegistroLicenciaBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
        return personaEntidad;
    }

    public LicenciaEntidad convertirLicenciaDTO_Entidad(LicenciaDTO licenciaDTO, PersonaEntidad persona) {
        LicenciaEntidad licenciaEntidad = new LicenciaEntidad(
                licenciaDTO.getCosto(),
                licenciaDTO.getFechaEmision(),
                licenciaDTO.isActiva(),
                tarifaLicenciaDAO.buscarTarifa(licenciaDTO.getTarifa().getVigencia()));
        licenciaEntidad.setPersona(persona);
        return licenciaEntidad;
    }
}
