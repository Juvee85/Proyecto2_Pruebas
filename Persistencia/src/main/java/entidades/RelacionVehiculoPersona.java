/*
 * RelacionVehiculoPersona.java
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de entidad que representa la relación entre las entidades de vehiculo
 * y personas
 * 
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "vehiculo_persona")
public class RelacionVehiculoPersona implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntidad persona;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoEntidad vehiculo;

    public RelacionVehiculoPersona() {
    }

    public RelacionVehiculoPersona(PersonaEntidad persona, VehiculoEntidad vehiculo) {
        this.persona = persona;
        this.vehiculo = vehiculo;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonaEntidad getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntidad persona) {
        this.persona = persona;
    }

    public VehiculoEntidad getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoEntidad vehiculo) {
        this.vehiculo = vehiculo;
    }

}
