/*
 * TramiteEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
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

    public TramiteEntidad() {
    }

    public TramiteEntidad(Float costo, Calendar fechaEmision, Boolean activa) {
        this.costo = costo;
        this.fechaEmision = fechaEmision;
        this.activa = activa;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
    
    public PersonaEntidad getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntidad persona) {
        this.persona = persona;
    }

}
