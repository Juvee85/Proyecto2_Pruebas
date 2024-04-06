package dtos;

import entidades.TarifaLicenciaEntidad;
import entidades.TarifaPlacasEntidad;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TarifaPlacasDTO {
    private String tipo;
    private Float costo;

    public TarifaPlacasDTO(String tipo, Float costo) {
        this.tipo = tipo;
        this.costo = costo;
    }
    
    public TarifaPlacasDTO(TarifaPlacasEntidad tarifaPlaca) {
        this.tipo = tarifaPlaca.getTipo();
        this.costo = tarifaPlaca.getCosto();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
    
}
