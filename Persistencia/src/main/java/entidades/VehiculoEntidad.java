/*
 * VehiculoEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase de entidad con los atributos y relaciones de un vehiculo
 * 
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "vehiculos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class VehiculoEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usado", nullable = false)
    private Boolean usado;
    
    @Column(name = "numero_serie", nullable = false)
    private String numeroSerie;
    
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private List<RelacionVehiculoPersona> detalle;
    
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private List<PlacasEntidad> placas;

    public VehiculoEntidad() {
    }

    public VehiculoEntidad(Boolean usado, String numeroSerie) {
        this.usado = usado;
        this.numeroSerie = numeroSerie;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isUsado() {
        return usado;
    }

    public void setUsado(Boolean usado) {
        this.usado = usado;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public List<RelacionVehiculoPersona> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<RelacionVehiculoPersona> detalle) {
        this.detalle = detalle;
    }

    public List<PlacasEntidad> getPlacas() {
        return placas;
    }

    public void setPlacas(List<PlacasEntidad> placas) {
        this.placas = placas;
    }

}
