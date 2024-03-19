/*
 * TarifaLicenciaEntidad.java
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
 * Clase de entidad con los atributos y relaciones de una tarifa de licencia
 * 
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "tarifas_licencia")
public class TarifaLicenciaEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vigencia", nullable = false)
    private String vigencia;
    
    @Column(name = "costo_normal", nullable = false)
    private Float costoNormal;
    
    @Column(name = "costo_discapacitado", nullable = false)
    private Float costoDiscapacitado;
    
    @OneToMany(mappedBy = "tarifa")
    private List<LicenciaEntidad> licencias;

    public TarifaLicenciaEntidad() {
    }

    public TarifaLicenciaEntidad(String vigencia, Float costoNormal, Float costoDiscapacitado) {
        this.vigencia = vigencia;
        this.costoNormal = costoNormal;
        this.costoDiscapacitado = costoDiscapacitado;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Float getCostoNormal() {
        return costoNormal;
    }

    public void setCostoNormal(Float costoNormal) {
        this.costoNormal = costoNormal;
    }

    public Float getCostoDiscapacitado() {
        return costoDiscapacitado;
    }

    public void setCostoDiscapacitado(Float costoDiscapacitado) {
        this.costoDiscapacitado = costoDiscapacitado;
    }

    public List<LicenciaEntidad> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<LicenciaEntidad> licencias) {
        this.licencias = licencias;
    }

}
