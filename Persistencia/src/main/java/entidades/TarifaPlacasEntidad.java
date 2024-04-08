/*
 * TarifaPlacasEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase de entidad con los atributos y relaciones de una tarifa de placa
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "tarifas_placas")
public class TarifaPlacasEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "costo", nullable = false)
    private Float costo;

    @OneToMany(mappedBy = "tarifa")
    private List<PlacasEntidad> placas;

    /**
     * Constructor vacío por defecto.
     */
    public TarifaPlacasEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param tipo
     * @param costo
     */
    public TarifaPlacasEntidad(String tipo, Float costo) {
        this.tipo = tipo;
        this.costo = costo;
    }

    /**
     * Regresa el id de una tarifa placas.
     *
     * @return Id de una tarifa placas.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a una tarifa placas.
     *
     * @param id Id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa el tipo de vehículo al cual se le sacaron las placas.
     *
     * @return El tipo de vehículo al cual se le sacaron las placas.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Asigna el tipo de vehículo al cual se le sacaron las placas.
     *
     * @param tipo Tipo de vehículo a asignar.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Regresa el costo de una tarifa placas.
     *
     * @return El costo de una tarifa plcas.
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Asigna el costo de una tarifa plcas.
     *
     * @param costo Costo a asignar.
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Regresa la lista de placas asociadas a la tarifa.
     *
     * @return La lista de placas a asociadas a la tarifa.
     */
    public List<PlacasEntidad> getPlacas() {
        return placas;
    }

    /**
     * Asigna una lista de placas a una tarifa.
     *
     * @param placas Lista de placas a asignar.
     */
    public void setPlacas(List<PlacasEntidad> placas) {
        this.placas = placas;
    }

}
