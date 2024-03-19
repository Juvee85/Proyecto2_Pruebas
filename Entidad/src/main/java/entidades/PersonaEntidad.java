/*
 * PersonaEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "Personas")
public class PersonaEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombre;
    
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;
    
    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;
    
    @Column(name = "CURP", unique = true, nullable = false)
    private String curp;
    
    @Column(name = "RFC", unique = true, nullable = false)
    private String rfc;
    
    @Column(name = "telefono", nullable = false)
    private String telefono;
    
    @Column(name = "discapacitado", nullable = false)
    private boolean discapacitado;
    
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "persona")
    private List<Tramite> tramites;
    
    @OneToMany(mappedBy = "persona")
    private List<RelacionVehiculoPersona> detalle;
    
    public PersonaEntidad() {
    }

    public PersonaEntidad(String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String telefono, boolean discapacitado, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.discapacitado = discapacitado;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isDiscapacitado() {
        return discapacitado;
    }

    public void setDiscapacitado(boolean discapacitado) {
        this.discapacitado = discapacitado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    public List<RelacionVehiculoPersona> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<RelacionVehiculoPersona> detalle) {
        this.detalle = detalle;
    }

}
