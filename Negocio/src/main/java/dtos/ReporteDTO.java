/**
 * ReporteDTO.java
 */
package dtos;

import entidades.LicenciaEntidad;
import entidades.PersonaEntidad;
import entidades.PlacasEntidad;
import entidades.TramiteEntidad;
import excepciones.PersistenciaException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.Encriptador;

/**
 * Clase DTO con las características que conforman un reporte.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class ReporteDTO {

    private String nombre;
    private String tipo;
    private String costo;
    private String fechaEmision;

    // Constructor que inicializa todos los atributos.
    /**
     *
     * @param nombre
     * @param tipo
     * @param costo
     * @param fechaRealizacion
     */
    public ReporteDTO(String nombre, Class tipo, String costo, String fechaRealizacion) {
        this.nombre = nombre;
        if (tipo.equals(LicenciaEntidad.class)) {
            this.tipo = "Licencia";
        } else if (tipo.equals(PlacasEntidad.class)) {
            this.tipo = "Placa";
        }
        this.costo = costo;
        this.fechaEmision = fechaRealizacion;
    }

    /**
     * Constructor que recibe un TramiteEntidad y lo convierte a ReporteDTO.
     *
     * @param tramite
     */
    public ReporteDTO(TramiteEntidad tramite) throws PersistenciaException {
        PersonaEntidad p = tramite.getPersona();
        Encriptador e = new Encriptador();
        try {
            /*
            this.nombre = e.desencriptar(p.getNombre()) + " "
                    + e.desencriptar(p.getApellidoPaterno()) + " "
                    + e.desencriptar(p.getApellidoMaterno());*/
            this.nombre = String.format("%s %s %s", p.getNombre(), p.getApellidoPaterno(), p.getApellidoMaterno());
        } catch (Exception ex) {
            throw new PersistenciaException("Ocurrio un error al intentar general el reporte, porfavor intente mas tarde...");
        }
        if (tramite.getClass() == LicenciaEntidad.class) {
            this.tipo = "Expedición de licencia";
        } else if (tramite.getClass() == PlacasEntidad.class) {
            this.tipo = "Expedición de placas";
        }
        this.costo = formatear(tramite.getCosto());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.fechaEmision = formatter.format(tramite.getFechaEmision().getTime());
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
     * Método que devuele el tipo de trámite del reporte.
     *
     * @return Tipo de trámite.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que asigna el tipo de trámite del reporte.
     *
     * @param tipo Tipo de trámite.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devuelve el costo de un trámite.
     *
     * @return Costo a devolver-
     */
    public String getCosto() {
        return costo;
    }

    /**
     * Método que asigna el costo a un trámite.
     *
     * @param costo Costo a asignar.
     */
    public void setCosto(String costo) {
        this.costo = costo;
    }

    /**
     * Método que devuelve la fecha de emisión de un trámite.
     *
     * @return Fecha de emisión de un trámite.
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Método que asigna la fecha de emisión a aun trámite.
     *
     * @param fechaEmision Fecha de emisión a asingar.
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Método para darle formato al dinero.
     *
     * @param cantidad Cantidad de dinero a formatera.
     * @return La cantidad de dinero ya formateada.
     */
    private String formatear(Float cantidad) {
        // Creamos un formato de dinero para México.
        Locale locale = new Locale("es", "MX");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        // Formateamos la cantidad como dinero.
        String cantFormateada = currencyFormatter.format(cantidad) + " MXN";

        // Retornamos la cantidad con el formato aplicado.
        return cantFormateada;
    }

}
