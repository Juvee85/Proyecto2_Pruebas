/*
 * AutomovilDTO.java
 */
package dtos;

import entidades.AutomovilEntidad;

/**
 * Clase DTO con los atributos de un automóvil.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class AutomovilDTO {

    private String numSerie;
    private String marca;
    private String linea;
    private String color;
    private String modelo;
    private String numPlaca;

    /**
     * Constructor que inicializa todos los parámetros menos el número de placa.
     *
     * @param numSerie Número de serie automóvil.
     * @param marca Marca del automóvil.
     * @param linea Línea del automóvil.
     * @param color Color del automóvil.
     * @param modelo Modelo del automóvil.
     */
    public AutomovilDTO(String numSerie, String marca, String linea, String color, String modelo) {
        this.numSerie = numSerie;
        this.marca = marca;
        this.linea = linea;
        this.color = color;
        this.modelo = modelo;
    }

    /**
     * Constructor que recibe un AutomovilEntidad y lo convierte a DTO.
     *
     * @param autoEntidad AutomovilEntidad a convertir.
     */
    public AutomovilDTO(AutomovilEntidad autoEntidad) {
        this.numSerie = autoEntidad.getNumeroSerie();
        this.marca = autoEntidad.getMarca();
        this.linea = autoEntidad.getLinea();
        this.color = autoEntidad.getColor();
        this.modelo = autoEntidad.getModelo();
    }

    /**
     * Método que devuele el número de serie.
     *
     * @return Número de serie a devolver.
     */
    public String getNumSerie() {
        return numSerie;
    }

    /**
     * Método que asigna el número de serie.
     *
     * @param numSerie Número de serie a asignar.
     */
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    /**
     * Método que devuelve la marca.
     *
     * @return La marca a devolver.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Método que asigna la marca.
     *
     * @param marca La marca a asignar.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Método que que devuelve la línea.
     *
     * @return La línea a devolver.
     */
    public String getLinea() {
        return linea;
    }

    /**
     * Método que asigna la línea.
     *
     * @param linea La línea a asignar.
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * Método que devuelve el color.
     *
     * @return El color a devolver.
     */
    public String getColor() {
        return color;
    }

    /**
     * Método que asigna el color.
     *
     * @param color El color a asignar.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Método que devuelve el modelo.
     *
     * @return El modelo a devolver.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Método que asigna el modelo.
     *
     * @param modelo El modelo a asignar.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Método que devuelve el número de placas.
     *
     * @return Número de placas a devolver.
     */
    public String getNumPlaca() {
        return numPlaca;
    }

    /**
     * Método que asigna el número de placas.
     *
     * @param numPlaca Número de placas a asignar.
     */
    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }
}
