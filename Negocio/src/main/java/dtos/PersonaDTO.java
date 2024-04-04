package dtos;

import entidades.PersonaEntidad;
import excepciones.NegocioException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Encriptador;

/**
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

    public PersonaDTO() {
    }

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
    
    public PersonaDTO(PersonaEntidad personaEntidad) throws NegocioException {
        try {
            this.nombre = e.desencriptar(personaEntidad.getNombre());
            this.apellidoPaterno = e.desencriptar(personaEntidad.getApellidoPaterno());
            this.apellidoMaterno = e.desencriptar(personaEntidad.getApellidoMaterno());
            this.curp = personaEntidad.getCurp();
            this.rfc = personaEntidad.getRfc();
            this.telefono = personaEntidad.getTelefono();
            this.discapacitado = personaEntidad.isDiscapacitado();
            this.fechaNacimiento = personaEntidad.getFechaNacimiento();
        } catch (Exception ex) {
            Logger.getLogger(PersonaDTO.class.getName()).log(Level.SEVERE, "Error al desencriptar datos.", ex);
            throw new NegocioException("Hubo un error.");
        }
    }
    
    public PersonaDTO(String curp) {
        this.curp = curp;
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

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
}
