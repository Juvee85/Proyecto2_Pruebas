package dtos;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class LicenciaDTO {
    private String vigencia;
    private String costoNormal;
    private String costoDiscapacitado;

    public LicenciaDTO(String vigencia, String costoNormal, String costoDiscapacitado) {
        this.vigencia = vigencia;
        this.costoNormal = costoNormal;
        this.costoDiscapacitado = costoDiscapacitado;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getCostoNormal() {
        return costoNormal;
    }

    public void setCostoNormal(String costoNormal) {
        this.costoNormal = costoNormal;
    }

    public String getCostoDiscapacitado() {
        return costoDiscapacitado;
    }

    public void setCostoDiscapacitado(String costoDiscapacitado) {
        this.costoDiscapacitado = costoDiscapacitado;
    }

    @Override
    public String toString() {
        return vigencia;
    }
    
}
