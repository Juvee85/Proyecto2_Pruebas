/*
 * AutomovilEntidad.java
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Clase de entidad con los atributos y relaciones de un automovil
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
@Entity
@Table(name = "Automoviles")
public class AutomovilEntidad extends VehiculoEntidad implements Serializable {

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "linea", nullable = false)
    private String linea;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    /**
     * Constructor vacio por defecto
     */
    public AutomovilEntidad() {
    }

    /**
     * Constructor que inicializa los atributos de un automovil, incluyendo los
     * que son comunes para los vehiculos
     *
     * @param marca Marca del auto
     * @param color Color del auto
     * @param linea Linea del auto
     * @param modelo Modelo del auto
     * @param usado Estado de usado del auto, si es verdadero se espera que el
     * auto haya sido registrado previamente
     * @param numeroSerie Numero de serie del auto
     */
    public AutomovilEntidad(String marca, String color, String linea, String modelo, Boolean usado, String numeroSerie) {
        super(usado, numeroSerie);
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
    }

    /**
     * Regresa la marca del auto
     *
     * @return Marca de este auto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Asigna la marca del auto
     *
     * @param marca Marca a asignar el auto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Regresa el color del auto
     *
     * @return Color del auto
     */
    public String getColor() {
        return color;
    }

    /**
     * Asigna el color del auto
     *
     * @param color Color a asignar al auto
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Regresa la linea del auto
     *
     * @return Linea del auto
     */
    public String getLinea() {
        return linea;
    }

    /**
     * Asigna la linea al auto
     *
     * @param linea Linea a asignar al auto
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * Regresa el modelo del auto
     *
     * @return Modelo del auto
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Asigna el modelo al auto
     *
     * @param modelo Modelo a asignar al auto
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
