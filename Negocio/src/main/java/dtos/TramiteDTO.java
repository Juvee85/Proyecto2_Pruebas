/*
 * TramiteDTO.java
 */
package dtos;

import entidades.LicenciaEntidad;
import entidades.PlacasEntidad;

/**
 * Clase DTO con los atributos de un trámite.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TramiteDTO {

    private String tipo;
    private String costo;
    private String fechaEmision;

    /**
     * Constructor que inicializa todos los atributos de la clase.
     *
     * @param tipo Tipo de trámite.
     * @param costo Costo del trámite.
     * @param fechaEmision Fecha de emisión del trámite.
     */
    public TramiteDTO(String tipo, String costo, String fechaEmision) {
        this.tipo = tipo;
        this.costo = costo;
        this.fechaEmision = fechaEmision;
    }

    /**
     * Constructor que inicializa todos los atributos de la clase.
     *
     * @param tipo Tipo de trámite.
     * @param costo Costo del trámite.
     * @param fechaEmision Fecha de emisión del trámite.
     */
    public TramiteDTO(Class tipo, String costo, String fechaEmision) {
        if (tipo.equals(LicenciaEntidad.class)) {
            this.tipo = "Licencia";
        } else if (tipo.equals(PlacasEntidad.class)) {
            this.tipo = "Placa";
        }
        this.costo = costo;
        this.fechaEmision = fechaEmision;
    }

    /**
     * Método quq devuelve el tipo de trámite.
     *
     * @return Tipo de trámite a devolver.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que asigna el tipo a un trámite.
     *
     * @param tipo Tipo de trámite a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devuelve el costo de un trámite.
     *
     * @return Costo a devolver.
     */
    public String getCosto() {
        return costo;
    }

    /**
     * Método para asignar el costo a un trámite.
     *
     * @param costo Costo a asignar.
     */
    public void setCosto(String costo) {
        this.costo = costo;
    }

    /**
     * Método que devuelve la fecha de emisión de un trámite.
     *
     * @return Fecha de emisión a devolver.
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Método que asigna la fecha de emisión a un trámite.
     *
     * @param fechaEmision Fecha de emisión a asignar.
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

}
