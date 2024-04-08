/*
 * RelacionVehiculoPersona.java
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase de entidad que representa la relación entre las entidades de vehiculo y
 * personas
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntidad persona;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private VehiculoEntidad vehiculo;

    /**
     * Constructor vacío por defecto.
     */
    public RelacionVehiculoPersona() {
    }

    /**
     * Constructor que inicializa los atributos de una relación.
     *
     * @param persona Persona que participa en la relación.
     * @param vehiculo Vehículo que participa en la relación.
     */
    public RelacionVehiculoPersona(PersonaEntidad persona, VehiculoEntidad vehiculo) {
        this.persona = persona;
        this.vehiculo = vehiculo;
    }

    /**
     * Método que devuelve el id de una relación.
     *
     * @return El id de una relación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a una relación.
     *
     * @param id Id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa la persona de una relación.
     *
     * @return Persona de una relación.
     */
    public PersonaEntidad getPersona() {
        return persona;
    }

    /**
     * Asigna la persona de una relación
     *
     * @param persona Persona a asignar.
     */
    public void setPersona(PersonaEntidad persona) {
        this.persona = persona;
    }

    /**
     * Regresa el vehículo de una relación.
     *
     * @return Vehículo de una relación.
     */
    public VehiculoEntidad getVehiculo() {
        return vehiculo;
    }

    /**
     * Asigna un vehículo a una relación.
     *
     * @param vehiculo Vehículo a asignar.
     */
    public void setVehiculo(VehiculoEntidad vehiculo) {
        this.vehiculo = vehiculo;
    }

}
