/*
 * LicenciaDTO.java
 */
package dtos;

import entidades.LicenciaEntidad;
import java.util.Calendar;

/**
 * Clase DTO con los atributos de una licencia.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class LicenciaDTO {
    private Calendar fechaEmision;
    private Float costo;
    private boolean activa;
    private TarifaLicenciaDTO tarifa;

    /**
     * COnstructor que inicializa todos los parámetros de la clase.
     * @param fechaEmision
     * @param costo
     * @param activa
     * @param tarifa
     */
    public LicenciaDTO(Calendar fechaEmision, Float costo, boolean activa, TarifaLicenciaDTO tarifa) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.activa = activa;
        this.tarifa = tarifa;
    }
    
    /**
     * Constructor que recibe una LicenciaEntidad y la convierte a DTO.
     * @param licencia Licencia a convertir.
     */
    public LicenciaDTO(LicenciaEntidad licencia) {
        this.fechaEmision = licencia.getFechaEmision();
        this.costo = licencia.getCosto();
        this.activa = licencia.isActiva();
        this.tarifa = new TarifaLicenciaDTO(licencia.getTarifa());
    }

    /**
     * Método que devuelve la fecha de emisión.
     * @return Fecha de emisión a devolver.
     */
    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Método que asigna la fecha de emisión.
     * @param fechaEmision Fecha de emisisón a asignar.
     */
    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Método que devuelve el costo.
     * @return Costo a devolver.
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Método que asigna el costo.
     * @param costo Costo a asignar.
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Método que devuelve si la licencia está activa.
     * @return True si la licencia está activa, false en caso contrario.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Método que asigna el atributo de activa.
     * @param activa Variable que define si una licencia está activa o no.
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Método que devuelve la tarifa de la licencia.
     * @return Tarifa a devolver.
     */
    public TarifaLicenciaDTO getTarifa() {
        return tarifa;
    }

    /**
     * Método que asigna una tarifa.
     * @param tarifa Tarifa a asignar.
     */
    public void setTarifa(TarifaLicenciaDTO tarifa) {
        this.tarifa = tarifa;
    }
    
}
