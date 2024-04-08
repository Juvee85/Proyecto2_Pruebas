/*
 * TramiteEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase de entidad con los atributos y relaciones de un tramite
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "tramites")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public abstract class TramiteEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "costo", nullable = false)
    private Float costo;

    @Column(name = "fecha_emision", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fechaEmision;

    @Column(name = "activa")
    private Boolean activa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntidad persona;

    /**
     * Constructor vacío por defecto.
     */
    public TramiteEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de la clase.
     *
     * @param costo
     * @param fechaEmision
     * @param activa
     */
    public TramiteEntidad(Float costo, Calendar fechaEmision, Boolean activa) {
        this.costo = costo;
        this.fechaEmision = fechaEmision;
        this.activa = activa;
    }

    /**
     * Regresa el id del trámite.
     *
     * @return Id del trámite.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a un trámite.
     *
     * @param id Id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa el costo de un trámite.
     *
     * @return Costo de un trámite.
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Asigna el costo a un trámite.
     *
     * @param costo Costo a asignar.
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Regresa la fecha de emisión de un trámite.
     *
     * @return Fecha de emisión de un trámite.
     */
    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Asigna la fecha de emisión a un trámite.
     *
     * @param fechaEmision Fecha de emisión a asignar.
     */
    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Regresa true si el trámite está activo, false en caso contraro.
     *
     * @return True si el trámite está activo, false en caso contraro.
     */
    public Boolean isActiva() {
        return activa;
    }

    /**
     * Método que asigna el valor de activo o inactivo a un trámite.
     *
     * @param activa Valor a asignar.
     */
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    /**
     * Regresa la persona asociada a un trámite.
     *
     * @return Persona asociada a un trámite.
     */
    public PersonaEntidad getPersona() {
        return persona;
    }

    /**
     * Asigna la persona asociada a un trámite.
     *
     * @param persona Persona a asignar.
     */
    public void setPersona(PersonaEntidad persona) {
        this.persona = persona;
    }

}
