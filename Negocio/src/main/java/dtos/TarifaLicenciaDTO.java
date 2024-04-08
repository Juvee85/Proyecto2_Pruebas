/**
 * TarifaLicenciaDTO.java
 */
package dtos;

import entidades.TarifaLicenciaEntidad;

/**
 * Clase DTO con los atributos de una tarifa de licencia.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TarifaLicenciaDTO {

    private String vigencia;
    private Float costoNormal;
    private Float costoDiscapacitado;

    /**
     * Constructorq ue inicializa todos los atributos.
     *
     * @param vigencia
     * @param costoNormal
     * @param costoDiscapacitado
     */
    public TarifaLicenciaDTO(String vigencia, Float costoNormal, Float costoDiscapacitado) {
        this.vigencia = vigencia;
        this.costoNormal = costoNormal;
        this.costoDiscapacitado = costoDiscapacitado;
    }

    /**
     * Constructor que recibe una TarifaLicenciaEntidad y la convierte a DTO.
     *
     * @param tarifaLicencia Tarifa a convertir.
     */
    public TarifaLicenciaDTO(TarifaLicenciaEntidad tarifaLicencia) {
        this.vigencia = tarifaLicencia.getVigencia();
        this.costoNormal = tarifaLicencia.getCostoNormal();
        this.costoDiscapacitado = tarifaLicencia.getCostoDiscapacitado();
    }

    /**
     * Método que devuelve la vigencia de una tarifa.
     *
     * @return Vigencia de una tarifa.
     */
    public String getVigencia() {
        return vigencia;
    }

    /**
     * Método que asigna la vigencia a una tarifa.
     *
     * @param vigencia Vigencia a asignar.
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    /**
     * Método que devuelve el costo normal de una tarifa.
     *
     * @return Costo normal a devolver.
     */
    public Float getCostoNormal() {
        return costoNormal;
    }

    /**
     * Método que asigna el costo normal a una tarifa.
     *
     * @param costoNormal Costo normal a asignar.
     */
    public void setCostoNormal(Float costoNormal) {
        this.costoNormal = costoNormal;
    }

    /**
     * Método que devuelve el costo de discapacitado de una tarifa.
     *
     * @return Costo de discapacitado de una tarifa.
     */
    public Float getCostoDiscapacitado() {
        return costoDiscapacitado;
    }

    /**
     * Método que asigna el costo de discapacitado a una tarifa.
     *
     * @param costoDiscapacitado
     */
    public void setCostoDiscapacitado(Float costoDiscapacitado) {
        this.costoDiscapacitado = costoDiscapacitado;
    }

    /**
     * Método para representar objetos TarifaLicenciaDTO como Strings.
     *
     * @return Vigencia a mostrar.
     */
    @Override
    public String toString() {
        return vigencia;
    }
}
