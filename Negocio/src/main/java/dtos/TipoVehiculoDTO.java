package dtos;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TipoVehiculoDTO {
    private String tipo;

    public TipoVehiculoDTO(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
