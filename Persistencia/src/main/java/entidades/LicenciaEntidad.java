/*
 * LicenciaEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de entidad con los atributos y relaciones de una licencia
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "licencias")
public class LicenciaEntidad extends TramiteEntidad implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tarifa", nullable = false)
    private TarifaLicenciaEntidad tarifa;

    /**
     * Constructor vacio por defecto
     */
    public LicenciaEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de una licencia, incluyendo los
     * que son comunes para los trámites.
     *
     * @param costo Costo del tramite de la licencia.
     * @param fechaEmision Fecha y hora en que se emitió la licencia.
     * @param activa Indica si la licencia está activa o no.
     * @param tarifa Tipo de tarifa de la licencia.
     */
    public LicenciaEntidad(Float costo, Calendar fechaEmision, Boolean activa, TarifaLicenciaEntidad tarifa) {
        super(costo, fechaEmision, activa);
        this.tarifa = tarifa;
    }
    
    /**
     * Regresa la tarifa de la licencia
     *
     * @return Tarifa de la licencia
     */
    public TarifaLicenciaEntidad getTarifa() {
        return tarifa;
    }

    /**
     * Asigna la tarifa de la licencia
     *
     * @param tarifa Tarifa a asignar a la licencia
     */
    public void setTarifa(TarifaLicenciaEntidad tarifa) {
        this.tarifa = tarifa;
    }

}
