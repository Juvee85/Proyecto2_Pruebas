/*
 * IRegistroPlacasBO.java
 */
package negocio;

import dtos.AutomovilDTO;
import dtos.LicenciaDTO;
import dtos.PlacasDTO;
import dtos.TarifaPlacasDTO;
import entidades.AutomovilEntidad;
import entidades.LicenciaEntidad;
import entidades.PlacasEntidad;
import excepciones.NegocioException;

/**
 * Interfaz que define métodos para aplicar las reglas de negocio y llevar a
 * cabo el registro de placas.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IRegistroPlacasBO {

    /**
     * Método para buscar a una persona y su licencia dada una CURP.
     *
     * @param curp CURP de la persona a buscar.
     * @return Persona encontrada.
     * @throws NegocioException si no se encuentra ninguna persona.
     */
    public Object[] buscarPersonaCurp(String curp) throws NegocioException;

    /**
     * Método para validar que la licencia del solicitante está vigente.
     *
     * @param licencia Licencia a validar.
     * @throws NegocioException si la licencia no está vigente.
     */
    public void validarRequisitosLicencia(LicenciaDTO licencia) throws NegocioException;

    /**
     * Método para convertir una licencia de DTO a entidad.
     *
     * @param licenciaDTO LicenciaDTO a convertir.
     * @return LicenciaEntidad ya convertida.
     */
    public LicenciaEntidad convertirLicenciaDTO_Entidad(LicenciaDTO licenciaDTO);

    /**
     * Método para generar una placa.
     *
     * @param tarifa Tarifa asociada a la placa.
     * @return La placa genarada.
     */
    public PlacasDTO generarPlaca(String tarifa);

    /**
     * Método que genera el número de una placa siguiendo el siguiente formato:
     * 3 letras + un guión + 3 dígitos (ejemplo: AAA-111).
     *
     * @return El número de la placa generado.
     */
    public String generarNumeroPlaca();

    /**
     * Método para buscar un automóvil dado un número de serie.
     *
     * @param numSerie Número de serie del automóvil buscado.
     * @return El automovil que se haya encontrado, null si no se encontró.
     */
    public AutomovilDTO buscarAutomovil(String numSerie);

    /**
     * Método para registrar unas placas en el caso de que el automóvil sea
     * nuevo.
     *
     * @param autoDTO Automóvil nuevo al cual se le están tramitando las placas.
     * @param curp CURP del solicitante.
     * @param placasDTO Placas a registrar.
     */
    public void agregarPlacaNuevo(AutomovilDTO autoDTO, String curp, PlacasDTO placasDTO);

    /**
     * Método para convertir de AutomovilDTO a AutomovilEntidad.
     *
     * @param autoDTO Automovil a convertir.
     * @return AutomovilEntidad ya convertido.
     */
    public AutomovilEntidad convertirAutomovilDTO_Entidad(AutomovilDTO autoDTO);

    /**
     * Método para convertir de PlacasDTO a PlacasEntidad.
     *
     * @param placasDTO Placas a convertir.
     * @return PlacasEntidad ya convertidas.
     */
    public PlacasEntidad convertirPlacasDTO_Entidad(PlacasDTO placasDTO);

    /**
     * Método para buscar una tarifa dada el tipo de trámite que se está
     * realizando.
     *
     * @param tipo Tipo de trámite que se está realizando.
     * @return Tarifa encontrada.
     */
    public TarifaPlacasDTO buscarTarifa(String tipo);

    /**
     * Método para buscar un automóvil dadas las últimas placas asociadas a
     * este.
     *
     * @param numPlacas Número de las últimas placas asociadas al auto.
     * @return El automóvil que se haya encontrado.
     * @throws NegocioException si no se encontró
     */
    public AutomovilDTO buscarAutoPlacas(String numPlacas) throws NegocioException;

    /**
     * Método para desactivar unas placas dado su número identificador.
     *
     * @param numPlacas Número de las placas a desactivar.
     */
    public void desactivarPlacas(String numPlacas);

    /**
     * Método para obtener las últimas placas asociadas a un automóvil.
     *
     * @param numSerie Número de serie del automóvil del cual se quieren obtener
     * las últimas placas.
     * @return Las placas que se hayan encontrado.
     */
    public PlacasDTO obtenerUltimasPlacas(String numSerie);

    /**
     * Método para registrar unas placas en el caso de que el automóvil sea
     * usado.
     *
     * @param numSerie Número de serie del automóvil usado al cual se le están
     * tramitando las placas.
     * @param curp CURP del solicitante.
     * @param placasDTO Placas a registrar.
     */
    public void agregarPlacaUsado(String numSerie, String curp, PlacasDTO placasDTO);
}
