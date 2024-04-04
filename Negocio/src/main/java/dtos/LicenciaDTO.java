package dtos;

import java.util.Calendar;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class LicenciaDTO {
    private Calendar fechaEmision;
    private Float costo;
    private boolean activa;
    private TarifaLicenciaDTO tarifa;

    public LicenciaDTO(Calendar fechaEmision, Float costo, boolean activa, TarifaLicenciaDTO tarifa) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.activa = activa;
        this.tarifa = tarifa;
    }

    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public TarifaLicenciaDTO getTarifa() {
        return tarifa;
    }

    public void setTarifa(TarifaLicenciaDTO tarifa) {
        this.tarifa = tarifa;
    }
    
}
