/*
 * RegistroPlacasBO.java
 */
package negocio;

import daos.AutomovilDAO;
import daos.IAutomovilDAO;
import daos.ILicenciaDAO;
import daos.IPersonaDAO;
import daos.IPlacasDAO;
import daos.IRelacionVehiculoPersonaDAO;
import daos.ITarifaLicenciaDAO;
import daos.ITarifaPlacasDAO;
import daos.LicenciaDAO;
import daos.PersonaDAO;
import daos.PlacasDAO;
import daos.RelacionVehiculoPersonaDAO;
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
 * registro de placas.
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
    private IRelacionVehiculoPersonaDAO relacionVehPerDAO = new RelacionVehiculoPersonaDAO();
    private static final Logger logger = Logger.getLogger(RegistroPlacasBO.class.getName());

    public RegistroPlacasBO(
            IPersonaDAO personaDAO, 
            ITarifaLicenciaDAO tarifaLicenciaDAO,
            ILicenciaDAO licenciaDAO,
            ITarifaPlacasDAO tarifaPlacasDAO,
            IPlacasDAO placasDAO,
            IAutomovilDAO automovilDAO,
            IRelacionVehiculoPersonaDAO reVehPerDAO
    ) {
        this.personaDAO = personaDAO;
        this.tarifaLicenciaDAO = tarifaLicenciaDAO;
        this.licenciaDAO = licenciaDAO;
        this.tarifaPlacasDAO = tarifaPlacasDAO;
        this.placasDAO = placasDAO;
        this.automovilDAO = automovilDAO;
        this.relacionVehPerDAO = reVehPerDAO;
    }
    
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
            // Mandamos un mensaje a consola de que no se encontró ninguna licencia activa.
            logger.log(Level.INFO, pe.getMessage());
        }
        // Agregamos al solicitante y su licencia al arreglo.
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
        if (licencia == null) { // Se valida que la licencia exista.
            // Se manda un mensaje a consola de que se interrumpió el registro de
            // placas.
            logger.log(Level.WARNING, "Registro de placas interrumpido.");
            // Se manda una excepción indicando lo que sucedió.
            throw new NegocioException("El solicitante no tiene licencia.");
        } else if (!licencia.isActiva()) { // Se valida que la licencia esté activa.
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

    /**
     * Método para generar una placa.
     *
     * @param tarifa Tarifa asociada a la placa.
     * @return La placa genarada.
     */
    @Override
    public PlacasDTO generarPlaca(String tarifa) {
        PlacasDTO placa;

        // Obtenemos el costo de la tarifa de las placas.
        TarifaPlacasEntidad tarifaEntidad = tarifaPlacasDAO.buscarTarifa(tarifa);
        Float costo = tarifaEntidad.getCosto();

        // Creamos un DTO de placa
        placa = new PlacasDTO(
                generarNumeroPlaca(), // Generamos un número de placas aleatorio.
                null, // Dejamos esto null. :D
                Calendar.getInstance(), // Obtenemos la fecha y hora actual.
                costo, // Costo de las placas.
                true, // Indicamos que serán las placas activas.
                // Convertimos la tarifa a DTO.
                new TarifaPlacasDTO(tarifaEntidad));

        // Retornamos la placa generada.
        return placa;
    }

    /**
     * Método que genera el número de una placa siguiendo el siguiente formato:
     * 3 letras + un guión + 3 dígitos (ejemplo: AAA-111).
     *
     * @return El número de la placa generado.
     */
    @Override
    public String generarNumeroPlaca() {
        // Definimos los valores que se pueden generar.
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";

        String numPlaca;
        // Se hace lo siguiente en bucle hasta que se genere un número de placa
        // que no se haya registrado previamente.
        do {
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

            // Concatenamos las letras y los números junto con un guión.
            numPlaca = placaLetras + "-" + placaNumeros;
        } while (placasDAO.existePlaca(numPlaca));

        // Retornamos el número de la placa.
        return numPlaca;
    }

    /**
     * Método para buscar un automóvil dado un número de serie.
     *
     * @param numSerie Número de serie del automóvil buscado.
     * @return El automovil que se haya encontrado, null si no se encontró.
     */
    @Override
    public AutomovilDTO buscarAutomovil(String numSerie) {
        // Buscamos la entidad del automovil.
        AutomovilEntidad autoEnt = automovilDAO.obtenerAutomovil(numSerie);

        if (autoEnt != null) {
            // Si se encontró un auto, convertimos la entidad a DTO y lo retornamos.
            return new AutomovilDTO(autoEnt);
        }

        return null;
    }

    /**
     * Método para registrar unas placas en el caso de que el automóvil sea
     * nuevo.
     *
     * @param autoDTO Automóvil nuevo al cual se le están tramitando las placas.
     * @param curp CURP del solicitante.
     * @param placasDTO Placas a registrar.
     */
    @Override
    public void agregarPlacaNuevo(AutomovilDTO autoDTO, String curp, PlacasDTO placasDTO) {
        // Creamos un objeto para guardar a la persona.
        PersonaEntidad persona = null;
        try {
            // Obtenemos la persona que tiene la CURP proporcionada.
            persona = personaDAO.buscarPorCurp(curp);
        } catch (PersistenciaException pe) {
            // Aquí no hacemos nada porque llegados a este punto ya sabemos que
            // sí existe una persona con la CURP dada.
        }

        // Convertimos el automovil del parámetro a entidad.
        AutomovilEntidad autoEntidad = convertirAutomovilDTO_Entidad(autoDTO);

        // Creamos la relación entre el vehículo y la persona y se la asignamos
        // al automóvil.
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona(persona, autoEntidad);
        autoEntidad.setDetalle(rvp);

        // Convertimos el DTO de las placas a Entidad.
        PlacasEntidad placasEntidad = convertirPlacasDTO_Entidad(placasDTO);
        // Asignamos el vehículo, la persona y la tarifa a la entidad de las placas.
        placasEntidad.setVehiculo(autoEntidad);
        placasEntidad.setPersona(persona);
        placasEntidad.setTarifa(tarifaPlacasDAO.buscarTarifa("Automóvil nuevo"));

        // Mandamos a insertar el automóvil y las placas.
        automovilDAO.insertarAutomovil(autoEntidad);
        placasDAO.insertarPlacas(placasEntidad);
    }

    /**
     * Método para convertir de AutomovilDTO a AutomovilEntidad.
     *
     * @param autoDTO Automovil a convertir.
     * @return AutomovilEntidad ya convertido.
     */
    @Override
    public AutomovilEntidad convertirAutomovilDTO_Entidad(AutomovilDTO autoDTO) {
        AutomovilEntidad autoEnt = new AutomovilEntidad(
                autoDTO.getMarca(),
                autoDTO.getColor(),
                autoDTO.getLinea(),
                autoDTO.getModelo(),
                autoDTO.getNumSerie());
        // Retornamos la entidad.
        return autoEnt;
    }

    /**
     * Método para convertir de PlacasDTO a PlacasEntidad.
     *
     * @param placasDTO Placas a convertir.
     * @return PlacasEntidad ya convertidas.
     */
    @Override
    public PlacasEntidad convertirPlacasDTO_Entidad(PlacasDTO placasDTO) {
        PlacasEntidad placasEnt = new PlacasEntidad(
                placasDTO.getFechaRecepcion(),
                placasDTO.getNumero(),
                placasDTO.isActiva(),
                placasDTO.getCosto(),
                placasDTO.getFechaEmision());
        // Retornamos la entidad.
        return placasEnt;
    }

    /**
     * Método para buscar una tarifa dada el tipo de trámite que se está
     * realizando.
     *
     * @param tipo Tipo de trámite que se está realizando.
     * @return Tarifa encontrada.
     */
    @Override
    public TarifaPlacasDTO buscarTarifa(String tipo) {
        // Obtenemos la tarifa de las placas.
        TarifaPlacasEntidad tarifaEnt = tarifaPlacasDAO.buscarTarifa(tipo);

        // La convertimos a DTO.
        TarifaPlacasDTO tarifaDTO = new TarifaPlacasDTO(tarifaEnt);

        // La retornamos.
        return tarifaDTO;
    }

    /**
     * Método para buscar un automóvil dadas las últimas placas asociadas a
     * este.
     *
     * @param numPlacas Número de las últimas placas asociadas al auto.
     * @return El automóvil que se haya encontrado.
     * @throws NegocioException si no se encontró
     */
    @Override
    public AutomovilDTO buscarAutoPlacas(String numPlacas) throws NegocioException {
        try {
            // Buscamos algún automóvil que tenga las placas asociadas.
            AutomovilEntidad autoEntidad = placasDAO.buscarAutoPlacas(numPlacas);

            // Convertimos la entidad a DTO.
            AutomovilDTO autoDTO = new AutomovilDTO(autoEntidad);

            // Retornamos el DTO.
            return autoDTO;
        } catch (PersistenciaException pe) {
            // Mandamos un mensaje a la consola de que no se encontró ningún automóvil.
            logger.log(Level.WARNING, "No se obtuvo ningún automóvil con las placas: " + numPlacas + ".");
            // Lanzamos una exceción indicando que no se encontró ningún automóvil
            // con la placa proporcionada.
            throw new NegocioException(pe.getMessage());
        }
    }

    /**
     * Método para desactivar unas placas dado su número identificador.
     *
     * @param numPlacas Número de las placas a desactivar.
     */
    @Override
    public void desactivarPlacas(String numPlacas) {
        // Obtenemos la entidad de las placas.
        PlacasEntidad placas = placasDAO.obtenerPlacas(numPlacas);

        // Indicamos que ahora estarán desactivadas.
        placas.setActiva(false);

        // Las mandamos a actualizar/desactivar.
        placasDAO.desactivarPlacas(placas);
    }

    /**
     * Método para obtener las últimas placas asociadas a un automóvil.
     *
     * @param numSerie Número de serie del automóvil del cual se quieren obtener
     * las últimas placas.
     * @return Las placas que se hayan encontrado.
     */
    @Override
    public PlacasDTO obtenerUltimasPlacas(String numSerie) {
        // Obtenemos las últimas placas que se hayan registrado para el auto.
        PlacasEntidad placasEnt = placasDAO.obtenerUltimasPlacas(numSerie);

        // Las convertimos a DTO.
        PlacasDTO placasDTO = new PlacasDTO(placasEnt);

        // Retornamos el DTO.
        return placasDTO;
    }

    /**
     * Método para registrar unas placas en el caso de que el automóvil sea
     * usado.
     *
     * @param numSerie Número de serie del automóvil usado al cual se le están
     * tramitando las placas.
     * @param curp CURP del solicitante.
     * @param placasDTO Placas a registrar.
     */
    @Override
    public void agregarPlacaUsado(String numSerie, String curp, PlacasDTO placasDTO) {
        // Creamos un objeto para guardar a la persona.
        PersonaEntidad persona = null;
        try {
            // Obtenemos la persona que tiene la CURP proporcionada.
            persona = personaDAO.buscarPorCurp(curp);
        } catch (PersistenciaException pe) {
            // Aquí no hacemos nada porque llegados a este punto ya sabemos que
            // sí existe una persona con la CURP dada.
        }

        // Obtenemos el automóvil con el número de serie.
        AutomovilEntidad autoEntidad = automovilDAO.obtenerAutomovil(numSerie);

        // Creamos la relación entre la persona y el vehículo.
        RelacionVehiculoPersona rvp = new RelacionVehiculoPersona(persona, autoEntidad);

        // Checamos si la relación entre el solicitante y el vehículo ya existe.
        if (!relacionVehPerDAO.existeDetalle(rvp)) {
            // Si no existe, insertamos el detalle/relación.
            relacionVehPerDAO.insertarDetalle(rvp);
        }

        // Convertimos el DTO de las placas a Entidad.
        PlacasEntidad placasEntidad = convertirPlacasDTO_Entidad(placasDTO);
        // Asignamos el automóvil, la persona y la tarifa a las placas.
        placasEntidad.setVehiculo(autoEntidad);
        placasEntidad.setPersona(persona);
        placasEntidad.setTarifa(tarifaPlacasDAO.buscarTarifa("Automóvil usado"));

        // Mandamos a insertar las placas.
        placasDAO.insertarPlacas(placasEntidad);
    }

}
