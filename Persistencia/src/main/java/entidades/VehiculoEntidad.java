/*
 * VehiculoEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
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
@DiscriminatorColumn(name = "tipo")
public abstract class VehiculoEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_serie", nullable = false, unique = true)
    private String numeroSerie;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.PERSIST)
    private List<RelacionVehiculoPersona> detalle;

    @OneToMany(mappedBy = "vehiculo")
    private List<PlacasEntidad> placas;

    /**
     * Constructor vacío por defecto,
     */
    public VehiculoEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param numeroSerie
     */
    public VehiculoEntidad(String numeroSerie) {
        this.numeroSerie = numeroSerie;
        this.detalle = new ArrayList<>();
    }

    /**
     * Regresa el id de un vehículo.
     *
     * @return Id de un vehículo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a un vehículo.
     *
     * @param id Id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa el número de serie de un vehículo.
     *
     * @return Número de serie del vehículo.
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Asigna un número de serie a un vehículo.
     *
     * @param numeroSerie Número de serie a asignar.
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * Regresa la lista de relaciones que un vehículo tiene con personas.
     *
     * @return Lista de relaciones que un vehículo tiene con personas.
     */
    public List<RelacionVehiculoPersona> getDetalle() {
        return detalle;
    }

    /**
     * Añade una relación entre una persona y el vehículo.
     *
     * @param detalle Relación entre una persona y el vehículo.
     */
    public void setDetalle(RelacionVehiculoPersona detalle) {
        this.detalle.add(detalle);
    }

    /**
     * Regresa la lista de placas asociadas con el vehículo.
     *
     * @return Lista de placas asociadas con el vehículo.
     */
    public List<PlacasEntidad> getPlacas() {
        return placas;
    }

    /**
     * Añade unas placas a la lista de placas asociadas al vehículo.
     *
     * @param placas Placas a añadir.
     */
    public void setPlacas(PlacasEntidad placas) {
        this.placas.add(placas);
    }

}
