package dtos;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class PlacaDTO {
    private String tramite;
    private String costo;

    public PlacaDTO(String tramite, String costo) {
        this.tramite = tramite;
        this.costo = costo;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return tramite;
    }
    
}
