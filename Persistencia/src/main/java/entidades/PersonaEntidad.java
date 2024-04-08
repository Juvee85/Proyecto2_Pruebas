/*
 * PersonaEntidad.java
 */
package entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
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
 * Clase de entidad con los atributos y relaciones de una persona
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "Personas")
public class PersonaEntidad implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "curp", unique = true, nullable = false, length = 18)
    private String curp;

    @Column(name = "rfc", unique = true, nullable = true, length = 13)
    private String rfc;

    @Column(name = "telefono", nullable = true, length = 10)
    private String telefono;

    @Column(name = "discapacitado", nullable = false)
    private Boolean discapacitado;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaNacimiento;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.PERSIST)
    private List<TramiteEntidad> tramites;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.PERSIST)
    private List<RelacionVehiculoPersona> detalle;

    /**
     * Constructor vacio por defecto
     */
    public PersonaEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de una persona
     *
     * @param nombre Nombre de la persona
     * @param apellidoPaterno Apellido paterno de la persona
     * @param apellidoMaterno Apellido materno de la persona
     * @param curp CURP de la persona
     * @param rfc RFC de la persona
     * @param telefono Número de telefono de la persona
     * @param discapacitado Estado de discapacitado de la persona
     * @param fechaNacimiento Fecha de nacimiento de la persona
     */
    public PersonaEntidad(String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String telefono, boolean discapacitado, Calendar fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.discapacitado = discapacitado;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Regresa el id de la persona
     *
     * @return Id de la persona
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el id a la persona
     *
     * @param id Id a asignar a la persona
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa el nombre de la persona
     *
     * @return Nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre a la persona
     *
     * @param nombre Nombre a asignar a la persona
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el apellido paterno de la persona
     *
     * @return Apellido paterno de la persona
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Asigna el apellido paterno a la persona
     *
     * @param apellidoPaterno Apellido a asignar a la persona
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Regresa el apellido materno de la persona
     *
     * @return Apellido paterno de la persona
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Asigna el apellido materno a la persona
     *
     * @param apellidoMaterno Apellido a asignar a la persona
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Regresa la CURP de la persona
     *
     * @return CURP de la persona
     */
    public String getCurp() {
        return curp;
    }

    /**
     * Asigna la CURP a la persona
     *
     * @param curp CURP a asignar a la persona
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * Regresa el RFC de la persona
     *
     * @return RFC de la persona
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Asigna el RFC a la persona
     *
     * @param rfc RFC a asignar a l apersona
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Regresa el número de teléfono de la persona
     *
     * @return Número de teléfono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el número de teléfono a la persona
     *
     * @param telefono Número de teléfono a asignar a la persona
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Determina si esta persona esta en condición de discapacitada, para efecto
     * de ofrecer tarifas más bajas
     *
     * @return true si la persona esta registrada como discapacitada, false en
     * caso contrario
     */
    public Boolean isDiscapacitado() {
        return discapacitado;
    }

    /**
     * Asigna el estado de discapacitada a una persona
     *
     * @param discapacitado Valor booleano para determinar si la persona se
     * encuentra en condición de dicapacitada
     */
    public void setDiscapacitado(Boolean discapacitado) {
        this.discapacitado = discapacitado;
    }

    /**
     * Regresa la fecha de nacimiento de la persona
     *
     * @return Fecha de nacimiento de la persona
     */
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Asigna la fecha de nacimiento a la persona
     *
     * @param fechaNacimiento Fecha de nacimiento a asignar a la persona
     */
    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Regresa la lista de tramites realizados por la persona
     *
     * @return Lista con los tramites realizados por la persona
     */
    public List<TramiteEntidad> getTramites() {
        return tramites;
    }

    /**
     * Asigna la lista de tramites de la persona
     *
     * @param tramites Lista con los tramites de la persona
     */
    public void setTramites(List<TramiteEntidad> tramites) {
        this.tramites = tramites;
    }

    /**
     * Regresa la lista con el detalle de los vehículos registrados de la
     * persona
     *
     * @return Lista con el detalle de los vehículos registrados de la persona
     */
    public List<RelacionVehiculoPersona> getDetalle() {
        return detalle;
    }

    /**
     * Asigna la lista con el detalle de los vehículos registrados a esta
     * persona
     *
     * @param detalle Lista con el detalle de los vehículos de la persona a
     * asignar a esta persona
     */
    public void setDetalle(List<RelacionVehiculoPersona> detalle) {
        this.detalle = detalle;
    }

}
