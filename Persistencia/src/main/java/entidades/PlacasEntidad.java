/*
 * PlacasEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase de entidad con los atributos y relaciones de una placa
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "placas")
public class PlacasEntidad extends TramiteEntidad implements Serializable {

    @Column(name = "fecha_recepcion", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaRecepcion;

    @Column(name = "numero_placa", unique = true, nullable = false)
    private String numero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tarifa", nullable = false)
    private TarifaPlacasEntidad tarifa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoEntidad vehiculo;

    public PlacasEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de una placa, incluyendo los que
     * son comunes para los trámites.
     *
     * @param fechaRecepcion Fecha en que se reciben las placas.
     * @param numero Número identificador del la placa.
     * @param costo Costo del tramite de la placa.
     * @param fechaEmision Fecha y hora en que se emitió la placa.
     * @param activa Indica si la placa está activa o no.
     */
    public PlacasEntidad(Calendar fechaRecepcion, String numero, Boolean activa, Float costo, Calendar fechaEmision) {
        super(costo, fechaEmision, activa);
        this.fechaRecepcion = fechaRecepcion;
        this.numero = numero;
    }

    /**
     * Regresa la fecha de recepción de unas placas.
     *
     * @return Fecha de recepción de unas placas.
     */
    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Asigna la fecha de recepción a unas placas.
     *
     * @param fechaRecepcion Fecha de recepción a asignar.
     */
    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Regresa el número de las placas.
     *
     * @return Número de las placas.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Asigna el número de las placas.
     *
     * @param numero Número de las placas a asignar.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Regresa la tarifa asociada a unas placas.
     *
     * @return Tarifa asociada a unas placas.
     */
    public TarifaPlacasEntidad getTarifa() {
        return tarifa;
    }

    /**
     * Asigna la tarifa a unas placas.
     *
     * @param tarifa Tarifa a asignar.
     */
    public void setTarifa(TarifaPlacasEntidad tarifa) {
        this.tarifa = tarifa;
    }

    /**
     * Regresa el vehículo al cual pertenecen las placas.
     *
     * @return Vehículo al cual pertenecen las placas.
     */
    public VehiculoEntidad getVehiculo() {
        return vehiculo;
    }

    /**
     * Método que asigna un vehículo a las placas.
     *
     * @param vehiculo Vehículo a asignar.
     */
    public void setVehiculo(VehiculoEntidad vehiculo) {
        this.vehiculo = vehiculo;
    }

}
