/*
 * PlacasDTO.java
 */
package dtos;

import entidades.PlacasEntidad;
import java.util.Calendar;

/**
 * Clase que representa el DTO de las placas.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PlacasDTO {

    private String numero;
    private Calendar fechaRecepcion;
    private Calendar fechaEmision;
    private Float costo;
    private boolean activa;
    private TarifaPlacasDTO tarifa;

    /**
     * Constructor del DTO que inicializa todos los atributos.
     *
     * @param numero Número de las placas.
     * @param fechaRecepcion Fecha en que el vehículo cambia de placas.
     * @param fechaEmision Fecha en que se realiza el trámite.
     * @param costo Costo de las placas.
     * @param activa Indica si las placas están activas o no.
     * @param tarifa Tarifa de las placas.
     */
    public PlacasDTO(String numero, Calendar fechaRecepcion, Calendar fechaEmision, Float costo, boolean activa, TarifaPlacasDTO tarifa) {
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.activa = activa;
        this.tarifa = tarifa;
    }

    /**
     * Método que recibe PlacasEntidad para convertirlas a DTO.
     *
     * @param placasEnt PlacasEntidad a convertir.
     */
    public PlacasDTO(PlacasEntidad placasEnt) {
        this.numero = placasEnt.getNumero();
        this.fechaRecepcion = placasEnt.getFechaRecepcion();
        this.fechaEmision = placasEnt.getFechaEmision();
        this.costo = placasEnt.getCosto();
        this.activa = placasEnt.isActiva();
        this.tarifa = new TarifaPlacasDTO(placasEnt.getTarifa());
    }

    /**
     * Método para obtener el número de las placas.
     *
     * @return El número de las placas.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Método para asignar el número de placas.
     *
     * @param numero Número de placas a asignar.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Método para obtener la fecha de recepción de las placas.
     *
     * @return Fecha de recepción de las placas.
     */
    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Método para asignar la fecha de recepción de las placas.
     *
     * @param fechaRecepcion Fecha de recepción de las placas a asignar.
     */
    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Método para obtener la fecha de emisión del trámite.
     *
     * @return Fecha de emisión del trámite.
     */
    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Método para asignar la fecha de emisión del trámite.
     *
     * @param fechaEmision Fecha de emisión del trámite a asignar.
     */
    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Método para obtener el costo de las placas.
     *
     * @return Costo de las placas.
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Método para asignar el costo de las placas.
     *
     * @param costo Costo de las placas a asignar.
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Método para obtener el estado de las placas.
     *
     * @return Estado de las placas.
     */
    public boolean isActiva() {
        return activa;
    }

    /**
     * Método para asignar el estado de las placas.
     *
     * @param activa Estado de las placas a asignar.
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Método para obtener la tarifa de las placas.
     *
     * @return Tarifa de las placas.
     */
    public TarifaPlacasDTO getTarifa() {
        return tarifa;
    }

    /**
     * Método para asignar la tarifa de las placas.
     *
     * @param tarifa Tarifa de las placas a asignar.
     */
    public void setTarifa(TarifaPlacasDTO tarifa) {
        this.tarifa = tarifa;
    }

}
