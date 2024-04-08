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
 * Clase de entidad con los atributos y relaciones de una tarifa de licencia.
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

    /**
     * Constructor vacío por defecto de una tarifa de licencia.
     */
    public TarifaLicenciaEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de una tarifaLicencia.
     *
     * @param vigencia
     * @param costoNormal
     * @param costoDiscapacitado
     */
    public TarifaLicenciaEntidad(String vigencia, Float costoNormal, Float costoDiscapacitado) {
        this.vigencia = vigencia;
        this.costoNormal = costoNormal;
        this.costoDiscapacitado = costoDiscapacitado;
    }

    /**
     * Regresa el id de una tarifaLicencia.
     *
     * @return El id de una tarifa Licencia.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a una tarifa licencia.
     *
     * @param id Id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa la vigencia de una tarifa licencia.
     *
     * @return Vigencia de una tarifa.
     */
    public String getVigencia() {
        return vigencia;
    }

    /**
     * Asigna la vigencia a una tarifa licencia.
     *
     * @param vigencia Vigencia a asignar.
     */
    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    /**
     * Regresa el costo normal de una tarifa licencia.
     *
     * @return Costo normal de una tarifa licencia.
     */
    public Float getCostoNormal() {
        return costoNormal;
    }

    /**
     * Asigna el costo normal de una tarifa licencia.
     *
     * @param costoNormal COsto normal de una tarifa licencia.
     */
    public void setCostoNormal(Float costoNormal) {
        this.costoNormal = costoNormal;
    }

    /**
     * Regresa el costo discapacitado de una tarifa licencia.
     *
     * @return Costo discapacitado de una licencia.
     */
    public Float getCostoDiscapacitado() {
        return costoDiscapacitado;
    }

    /**
     * Asigna el costo discapacitado a una tarifa licencia.
     *
     * @param costoDiscapacitado Costo discapacitado a asignar.
     */
    public void setCostoDiscapacitado(Float costoDiscapacitado) {
        this.costoDiscapacitado = costoDiscapacitado;
    }

    /**
     * Regresa la lista de licencias asociadas a la tarifa.
     *
     * @return Lista de licencias asociadas a la tarifa.
     */
    public List<LicenciaEntidad> getLicencias() {
        return licencias;
    }

    /**
     * Asigna una lista de licencias a la tarifa.
     *
     * @param licencias Lista a asignar.
     */
    public void setLicencias(List<LicenciaEntidad> licencias) {
        this.licencias = licencias;
    }

}
