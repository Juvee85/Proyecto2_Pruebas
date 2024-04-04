/*
 * TarifaPlacaEntidad.java
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
@Table(name = "tarifas_placa")
public class TarifaPlacaEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;
    
    @Column(name = "costo", nullable = false)
    private Float costo;
    
    @OneToMany(mappedBy = "tarifa")
    private List<PlacaEntidad> placas;

    public TarifaPlacaEntidad() {
    }

    public TarifaPlacaEntidad(String tipo, Float costo) {
        this.tipo = tipo;
        this.costo = costo;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public List<PlacaEntidad> getPlacas() {
        return placas;
    }

    public void setPlacas(List<PlacaEntidad> placas) {
        this.placas = placas;
    }

}
