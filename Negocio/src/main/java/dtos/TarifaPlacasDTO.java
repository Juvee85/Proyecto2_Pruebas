/**
 * TarifaPlacasDTO.java
 */
package dtos;

import entidades.TarifaLicenciaEntidad;
import entidades.TarifaPlacasEntidad;

/**
 * Clase DTO con los atributos de una tarifa de placas.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TarifaPlacasDTO {

    private String tipo;
    private Float costo;

    /**
     * Constructor que inicializa todos los atributos.
     * @param tipo
     * @param costo 
     */
    public TarifaPlacasDTO(String tipo, Float costo) {
        this.tipo = tipo;
        this.costo = costo;
    }

    /**
     * Constructor que recibe una TarifaPlacasEntidad y la convierte a DTo.
     * @param tarifaPlaca 
     */
    public TarifaPlacasDTO(TarifaPlacasEntidad tarifaPlaca) {
        this.tipo = tarifaPlaca.getTipo();
        this.costo = tarifaPlaca.getCosto();
    }

    /**
     * Método que devuelve el tipo de trámite de las placas.
     * @return Tipo de trámite de las placas.
     */
    public String getTipo() {
        return tipo;
    }

    /** Método que asigna el tipo de trámite de unas placas.
     * 
     * @param tipo Tipo de trámite a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devuele el costo de unas placas.
     * @return Costo de unas placas.
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Método que asigna el costo a unas placas.
     * @param costo Costo a asignar.
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

}
