package dtos;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TramiteDTO {
    private String tipo;
    private String costo;
    private String fechaEmision;

    public TramiteDTO(String tipo, String costo, String fechaEmision) {
        this.tipo = tipo;
        this.costo = costo;
        this.fechaEmision = fechaEmision;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
    
}
