/*
 * PersonaDTO.java
 */
package dtos;

import entidades.PersonaEntidad;
import excepciones.NegocioException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Encriptador;

/**
 * Clase DTO con los atributos de una persona.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PersonaDTO {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String rfc;
    private String telefono;
    private boolean discapacitado;
    private Calendar fechaNacimiento;
    private Encriptador e = new Encriptador();

    /**
     * Constructor que inicializa todos los atributos de la clase menos el
     * encriptador.
     *
     * @param nombre Nombre de la persona.
     * @param apellidoPaterno Apellido paterno de la persona.
     * @param apellidoMaterno Apellido materno de la persona.
     * @param curp CURP de la persona.
     * @param rfc RFC de la persona-
     * @param telefono Teléfono de la persona.
     * @param discapacitado Variable que indica si una persona está
     * discapacitada o no.
     * @param fechaNacimiento Fecha de nacimiento de la persona.
     */
    public PersonaDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String telefono, boolean discapacitado, Calendar fechaNacimiento) {
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
     * Constructor que recibe una PersonaEntidad y la conviere a DTO.
     *
     * @param personaEntidad PersonaEntidad a convertir.
     * @throws NegocioException Si ocurre algún error durante la encriptación.
     *
     * public PersonaDTO(PersonaEntidad personaEntidad) throws NegocioException
     * { try { this.nombre = e.desencriptar(personaEntidad.getNombre());
     * this.apellidoPaterno =
     * e.desencriptar(personaEntidad.getApellidoPaterno()); this.apellidoMaterno
     * = e.desencriptar(personaEntidad.getApellidoMaterno()); this.curp =
     * personaEntidad.getCurp(); this.rfc = personaEntidad.getRfc();
     * this.telefono = personaEntidad.getTelefono(); this.discapacitado =
     * personaEntidad.isDiscapacitado(); this.fechaNacimiento =
     * personaEntidad.getFechaNacimiento(); } catch (Exception ex) {
     * Logger.getLogger(PersonaDTO.class.getName()).log(Level.SEVERE, "Error al
     * desencriptar datos.", ex); throw new NegocioException("Hubo un error.");
     * }
    }
     */
    
    /**
     * Constructor que recibe una PersonaEntidad y la conviere a DTO.
     *
     * @param personaEntidad PersonaEntidad a convertir.
     * @throws NegocioException Si ocurre algún error durante la encriptación.
     */
    public PersonaDTO(PersonaEntidad personaEntidad) throws NegocioException {
        this.nombre = personaEntidad.getNombre();
        this.apellidoPaterno = personaEntidad.getApellidoPaterno();
        this.apellidoMaterno = personaEntidad.getApellidoMaterno();
        this.curp = personaEntidad.getCurp();
        this.rfc = personaEntidad.getRfc();
        this.telefono = personaEntidad.getTelefono();
        this.discapacitado = personaEntidad.isDiscapacitado();
        this.fechaNacimiento = personaEntidad.getFechaNacimiento();
    }

    /**
     * Constructor que inicializa la CURP.
     *
     * @param curp CURP de la persona.
     */
    public PersonaDTO(String curp) {
        this.curp = curp;
    }

    /**
     * Método que devuelve el nombre de una persona.
     *
     * @return Nombre a devolver.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que asigna el nombre a una persona.
     *
     * @param nombre Nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que devuelve el apellido paterno de una persona.
     *
     * @return Apellido paterno a devolver.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que asigna el apellido paterno a una persona.
     *
     * @param apellidoPaterno Apellido paterno a asignar.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Método que devuelve el apellido materno de una persona.
     *
     * @return Apellido materno a devolver.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que asigna el apellido materno a una persona.
     *
     * @param apellidoMaterno Apellido materno a asignar.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que devuelve la CURP de una persona.
     *
     * @return CURP a devolver.
     */
    public String getCurp() {
        return curp;
    }

    /**
     * Método que asigna la CURP a una persona.
     *
     * @param curp CURP a asignar.
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * Método que devuelve la RFC de una persona.
     *
     * @return RFC a devolver.
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Método que asigna el RFC a una persona.
     *
     * @param rfc RFC a asignar.
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Método que devuelve el teléfono de una persona.
     *
     * @return Teléfono a devolver.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que asigna el teléfono a una persona.
     *
     * @param telefono Teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que devuelve true si una persona está discapacitada y false en
     * caso contrario.
     *
     * @return True si una persona está discapacitada y false en caso contrario.
     */
    public boolean isDiscapacitado() {
        return discapacitado;
    }

    /**
     * Método que asigna un booleano para indicar si una persona está
     * discapacitada o no.
     *
     * @param discapacitado Variable que indica si una persona está
     * discapacitada o no.
     */
    public void setDiscapacitado(boolean discapacitado) {
        this.discapacitado = discapacitado;
    }

    /**
     * Método que devuelve la fecha de nacimiento de una persona.
     *
     * @return Fehca de nacimiento a devolver.
     */
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Método que asigna la fecha de nacimiento a una persona.
     *
     * @param fechaNacimiento Fecha de nacimiento a asignar.
     */
    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
