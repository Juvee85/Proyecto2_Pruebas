/*
 * IRegistroPlacasBO.java
 */
package negocio;

import dtos.AutomovilDTO;
import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import dtos.PlacasDTO;
import dtos.TarifaPlacasDTO;
import entidades.LicenciaEntidad;
import excepciones.NegocioException;

/**
 * Interfaz que define métodos para aplicar las reglas de negocio y llevar a cabo
 * el registro de placas.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public interface IRegistroPlacasBO {
    
    /**
     * Método para buscar a una persona y su licencia dada una CURP.
     * @param curp CURP de la persona a buscar.
     * @return Persona encontrada.
     * @throws NegocioException si no se encuentra ninguna persona.
     */
    public Object[] buscarPersonaCurp(String curp) throws NegocioException;
    
    /**
     * Método para validar que la licencia del solicitante está vigente.
     * @param licencia Licencia a validar.
     * @throws NegocioException si la licencia no está vigente.
     */
    public void validarRequisitosLicencia(LicenciaDTO licencia) throws NegocioException;
    
    /**
     * Método para convertir una licencia de DTO a entidad.
     * @param licenciaDTO LicenciaDTO a convertir.
     * @return LicenciaEntidad ya convertida.
     */
    public LicenciaEntidad convertirLicenciaDTO_Entidad(LicenciaDTO licenciaDTO);
    
    public PlacasDTO generarPlaca(String estado);
    
    public String generarNumeroPlaca();
    
    public AutomovilDTO buscarAutomovil(String numSerie);
    
    public void agregarPlacaNuevo(AutomovilDTO auto, String curp, PlacasDTO placa) throws NegocioException;

    public TarifaPlacasDTO buscarTarifa(String tipo);

    public AutomovilDTO buscarAutoPlacas(String numPlacas) throws NegocioException;

    public void desactivarPlacas(String text);
    
    public PlacasDTO obtenerUltimasPlacas(String numSerie);
    
    public void agregarPlacaUsado(String numSerie, String curp, PlacasDTO placasDTO) throws NegocioException;
}
