/*
 * TarifaVehiculoDTO.java
 */
package dtos;

/**
 * Clase DTO que que indica el tipo de vehículos.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class TipoVehiculoDTO {

    private String tipo;

    /**
     * Constructor que inicializa el único atributo.
     *
     * @param tipo Tipo de vehículo.
     */
    public TipoVehiculoDTO(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método que devuelve el tipo de vehículo.
     *
     * @return Tipo de vehículo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método que asigna el tipo de vehículo.
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Método para representar un objeto TipoVehiculoDTO como un String.
     *
     * @return El tipo de vehículo que eso.
     */
    @Override
    public String toString() {
        return tipo;
    }
}
