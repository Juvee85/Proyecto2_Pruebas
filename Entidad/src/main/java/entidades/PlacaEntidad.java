/*
 * PlacaEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
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
public class PlacaEntidad extends TramiteEntidad implements Serializable {

    @Column(name = "fecha_recepcion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    
    @Column(name = "numero_placa", unique = true, nullable = false)
    private String numero;
    
    @Column(name = "activa")
    private Boolean activa;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_tarifa", nullable = false)
    private TarifaPlacaEntidad tarifa;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoEntidad vehiculo;

    public PlacaEntidad() {
    }

    public PlacaEntidad(Date fechaRecepcion, String numero, Boolean activa, Float costo, Date fechaEmision) {
        super(costo, fechaEmision);
        this.fechaRecepcion = fechaRecepcion;
        this.numero = numero;
        this.activa = activa;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public TarifaPlacaEntidad getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaPlacaEntidad tarifa) {
        this.tarifa = tarifa;
    }

    public VehiculoEntidad getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoEntidad vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
