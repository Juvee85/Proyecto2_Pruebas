/*
 * RegistroPlacasBO.java
 */
package negocio;

import daos.AutomovilDAO;
import daos.IAutomovilDAO;
import daos.ILicenciaDAO;
import daos.IPersonaDAO;
import daos.IPlacasDAO;
import daos.ITarifaLicenciaDAO;
import daos.ITarifaPlacasDAO;
import daos.LicenciaDAO;
import daos.PersonaDAO;
import daos.PlacasDAO;
import daos.TarifaLicenciaDAO;
import daos.TarifaPlacasDAO;
import dtos.AutomovilDTO;
import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.PlacasDTO;
import dtos.TarifaPlacasDTO;
import entidades.AutomovilEntidad;
import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.PlacasEntidad;
import entidades.RelacionVehiculoPersona;
import entidades.TarifaPlacasEntidad;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con la implementación de la interfaz IRegistroPlacasBO para realizar el
 * registro de licencias.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class RegistroPlacasBO implements IRegistroPlacasBO {

    private IPersonaDAO personaDAO = new PersonaDAO();
    private ITarifaLicenciaDAO tarifaLicenciaDAO = new TarifaLicenciaDAO();
    private ILicenciaDAO licenciaDAO = new LicenciaDAO();
    private ITarifaPlacasDAO tarifaPlacasDAO = new TarifaPlacasDAO();
    private IPlacasDAO placasDAO = new PlacasDAO();
    private IAutomovilDAO automovilDAO = new AutomovilDAO();
    private static final Logger logger = Logger.getLogger(RegistroPlacasBO.class.getName());

    /**
     * Método para buscar a una persona y su licencia dada una CURP.
     *
     * @param curp CURP de la persona a buscar.
     * @return Persona encontrada.
     * @throws NegocioException si no se encuentra ninguna persona.
     */
    @Override
    public Object[] buscarPersonaCurp(String curp) throws NegocioException {
        // Creamos un arreglo para almacenar al solicitante y su licencia.
        Object[] personaLicencia = new Object[2];
        // Creamos una variable para almacenar la entidad del solicitante.
        PersonaEntidad personaEntidad;
        PersonaDTO personaDTO;
        try {
            // Buscamos una persona con la CURP dada y la asignamos a una entidad.
            personaEntidad = personaDAO.buscarPorCurp(curp);
            // Convertimos la entidad a DTO.
            personaDTO = new PersonaDTO(personaEntidad);
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ninguna persona.
            logger.log(Level.WARNING, "No se obtuvo ninguna persona con la CURP: " + curp + ".");
            // Lanzamos una exceción indicando que no se encontró ninguna persona
            // con la CURP proporcionada.
            throw new NegocioException(pe.getMessage());
        }

        // Creamos una variable para almacenar la entidad de la licencia.
        LicenciaDTO licenciaDTO = null;
        try {
            // Obtenemos la última licencia del solicitante.
            LicenciaEntidad licenciaEntidad = licenciaDAO.buscarUltimaLicencia(personaEntidad);

            // Convertimos la licencia a DTO.
            licenciaDTO = new LicenciaDTO(licenciaEntidad);
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje de que no se encontró ninguna licencia activa.
            logger.log(Level.INFO, pe.getMessage());
        }
        // Agregamos el solicitante y su licencia al arreglo.
        personaLicencia[0] = personaDTO;
        personaLicencia[1] = licenciaDTO;

        // Retornamos el arreglo.
        return personaLicencia;
    }

    /**
     * Método para validar que la licencia del solicitante está vigente.
     *
     * @param licencia Licencia a validar.
     * @throws NegocioException si la licencia no está vigente.
     */
    @Override
    public void validarRequisitosLicencia(LicenciaDTO licencia) throws NegocioException {
        if (licencia == null) { // Se valida que la licencia esté vigente.
            // Se manda un mensaje a consola de que se interrumpió el registro de
            // placas.
            logger.log(Level.WARNING, "Registro de placas interrumpido.");
            // Se manda una excepción indicando lo que sucedió.
            throw new NegocioException("El solicitante no tiene licencia.");
        } else if (!licencia.isActiva()) {
            // Se manda un mensaje a consola de que se interrumpió el registro de
            // placas.
            logger.log(Level.WARNING, "Registro de placas interrumpido.");
            // Se manda una excepción indicando lo que sucedió.
            throw new NegocioException("La licencia del solicitante no está vigente.");
        }
    }

    /**
     * Método para convertir una licencia de DTO a entidad.
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

    @Override
    public PlacasDTO generarPlaca(String tarifa) {
        PlacasDTO placa;
        
        // Obtenemos el costo de la tarifa de las placas y el costo.
        TarifaPlacasEntidad tarifaEntidad = tarifaPlacasDAO.buscarTarifa(tarifa);
        Float costo = tarifaEntidad.getCosto();

        // Se hace lo siguiente en bucle hasta que se genere un número de placa
        // que no se haya registrado previamente.
        do {
            // Creamos un DTO de placa
            placa = new PlacasDTO(
                    generarNumeroPlaca(),   // Generamos un número de placas aleatorio.
                    Calendar.getInstance(), // Obtenemos la fecha y hora actual.
                    Calendar.getInstance(), // Obtenemos la fecha y hora actual.
                    costo,                  // Costo de las placas.
                    true,                   // Indicamos que serán las placas activas.
                    // Convertimos la tarifa a DTO.
                    new TarifaPlacasDTO(tarifaEntidad));
        } while (placasDAO.existePlaca(placa.getNumero()));

        // Retornamos la placa generada.
        return placa;
    }

    @Override
    public String generarNumeroPlaca() {
        // Definimos los valores que se pueden genera.
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";

        // Creamos un objeto para generar obtener valores aleatorios.
        Random random = new Random();

        // Generamos las tres letras.
        String placaLetras = "";
        for (int i = 0; i < 3; i++) {
            int indice = random.nextInt(letras.length());
            placaLetras += letras.charAt(indice);
        }

        // Generamos los tres números aleatorios
        String placaNumeros = "";
        for (int i = 0; i < 3; i++) {
            int indice = random.nextInt(numeros.length());
            placaNumeros += numeros.charAt(indice);
        }

        // Concatenamos las letras y los números junto con un guión para formar
        // la secuencia correcta.
        String numPlaca = placaLetras + "-" + placaNumeros;

        // Retornamos el número de la placa.
        return numPlaca;
    }

    @Override
    public void agregarPlacaNuevo(AutomovilDTO autoDTO, String curp, PlacasDTO placasDTO) throws NegocioException {
        if (automovilDAO.estaRegistrado(autoDTO.getNumSerie())) {
            throw new NegocioException("El automovil ya está registrado.");
        }

        // Creamos un objeto para guardar a la persona.
        // Como para este punto ya sabemos que en realidad sí existe una persona
        // con la CURP dada, no importa si dejamos esto null.
        PersonaEntidad persona = null;
        try {
            // Obtenemos la persona que tiene la CURP proporcionada.
            persona = personaDAO.buscarPorCurp(curp);
        } catch (PersistenciaException pe) {
            // Se manda un mensaje a consola de que se interrumpió el registro de
            // licencia.
            logger.log(Level.WARNING, "Registro de placas interrumpido.");
            // En teoría nunca debería lanzarse esta excepción, porque llegados a
            // este punto ya sabemos que sí existe una persona con la CURP dada.
            throw new NegocioException(pe.getMessage());
        }
        
        AutomovilEntidad autoEntidad = convertirAutomovilDTO_Entidad(autoDTO);
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona();
        rvp.setPersona(persona);
        rvp.setVehiculo(autoEntidad);
        
        PlacasEntidad placasEntidad = convertirPlacasDTO_Entidad(placasDTO);
        placasEntidad.setVehiculo(autoEntidad);
        placasEntidad.setPersona(persona);
        placasEntidad.setTarifa(tarifaPlacasDAO.buscarTarifa("Automóvil nuevo"));
        
        placasDAO.insertarPlacas(placasEntidad);
    }
    
    public AutomovilEntidad convertirAutomovilDTO_Entidad(AutomovilDTO autoDTO) {
        AutomovilEntidad autoEnt = new AutomovilEntidad(
                autoDTO.getMarca(),
                autoDTO.getColor(),
                autoDTO.getLinea(),
                autoDTO.getModelo(),
                false,
                autoDTO.getNumSerie());
        return autoEnt;
    }

    private PlacasEntidad convertirPlacasDTO_Entidad(PlacasDTO placasDTO) {
        PlacasEntidad placasEnt = new PlacasEntidad(
                placasDTO.getFechaRecepcion(),
                placasDTO.getNumero(),
                placasDTO.isActiva(),
                placasDTO.getCosto(),
                placasDTO.getFechaEmision());
        return placasEnt;
    }

    @Override
    public TarifaPlacasDTO buscarTarifa(String tipo) {
        // Obtenemos la tarifa de las placas.
        TarifaPlacasEntidad tarifaEnt = tarifaPlacasDAO.buscarTarifa(tipo);
        
        // La convertimos a DTO.
        TarifaPlacasDTO tarifaDTO = new TarifaPlacasDTO(tarifaEnt);
        
        // La retornamos.
        return tarifaDTO;
    }

    @Override
    public AutomovilDTO buscarPlacas(String numPlacas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarPlacaUsado(AutomovilDTO auto, String curp, PlacasDTO placaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
