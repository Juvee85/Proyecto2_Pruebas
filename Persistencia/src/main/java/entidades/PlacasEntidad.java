/*
 * PlacasEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
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

    @Column(name = "fecha_recepcion", nullable = false)
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
     * Constructor que inicializa los atributos de una placa, incluyendo los
     * que son comunes para los trámites.
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

    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TarifaPlacasEntidad getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaPlacasEntidad tarifa) {
        this.tarifa = tarifa;
    }

    public VehiculoEntidad getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoEntidad vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
