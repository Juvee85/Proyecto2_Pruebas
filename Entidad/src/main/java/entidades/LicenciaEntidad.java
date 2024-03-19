/*
 * LicenciaEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_tarifa", nullable = false)
    private TarifaLicenciaEntidad tarifa;

    /**
     * Constructor vacio por defecto
     */
    public LicenciaEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de una licencia, incluyendo los
     * que son comunes para los tramites
     *
     * @param costo Costo del tramite de la licencia
     * @param fechaEmision Fecha y hora en que se emitió la licencia
     */
    public LicenciaEntidad(Float costo, Date fechaEmision) {
        super(costo, fechaEmision);
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
