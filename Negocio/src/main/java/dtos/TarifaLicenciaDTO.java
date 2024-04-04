package dtos;

import entidades.TarifaLicenciaEntidad;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TarifaLicenciaDTO {
    private String vigencia;
    private Float costoNormal;
    private Float costoDiscapacitado;

    public TarifaLicenciaDTO(String vigencia, Float costoNormal, Float costoDiscapacitado) {
        this.vigencia = vigencia;
        this.costoNormal = costoNormal;
        this.costoDiscapacitado = costoDiscapacitado;
    }
    
    public TarifaLicenciaDTO(TarifaLicenciaEntidad tarifaLicencia) {
        this.vigencia = tarifaLicencia.getVigencia();
        this.costoNormal = tarifaLicencia.getCostoNormal();
        this.costoDiscapacitado = tarifaLicencia.getCostoDiscapacitado();
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

    @Override
    public String toString() {
        return vigencia;
    }
}
