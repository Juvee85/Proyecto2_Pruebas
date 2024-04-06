/*
 * Validadores.java
 */
package utilidades;

import excepciones.PresentacionException;
import javax.swing.ButtonModel;

/**
 * Clase con todos los métodos necesarios para validar que los datos introducidos
 * por el usuario sean en los formatos correctos.
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class Validadores {
    
    /**
     * Método para validar cadenas vacías o con puros espacios.
     * @param cadena Cadena a validar.
     * @throws PresentacionException si la cadena del parámetro está vacía.
     */
    public void validarVacio(String cadena) throws PresentacionException {
        if (cadena.isBlank()) {
            throw new PresentacionException("");
        }
    }
    
    /**
     * Método para validar que una curp cumple con el formato correcto.
     * @param curp CURP a validar.
     * @throws PresentacionException si la curp está vacía o no tiene 18
     * caracteres.
     */
    public void validarCurp(String curp) throws PresentacionException {
        try {
            validarVacio(curp);
        } catch (PresentacionException pe) {
            // Si la CURP está vacía, lanzamos una excepción.
            throw new PresentacionException("La CURP no puede estar vacía. Ejemplo: \"FEPI910601MTSLZB01\".");
        }
        if (curp.length() != 18) {
            // Si la CURP no tiene 18 caracteres, lanzamos una excepción.
            throw new PresentacionException("La CURP debe tener 18 caracteres. Ejemplo: \"FEPI910601MTSLZB01\".");
        }
    }
    
    /**
     * Método para validar se haya seleccionado algo en un grupo de RadioButtons.
     * @param seleccion Seleccion del grupo de botones.
     * @throws PresentacionException si no se seleccionó nada.
     */
    public void validarSeleccion(ButtonModel seleccion) throws PresentacionException {
        if (seleccion == null) {
            // Si no se seleccionó ninguna opción.
            throw new PresentacionException("Favor de especificar si el vehículo es nuevo o usado.");
        }
    }
    
    /**
     * Método para validar que el número de serie de un automóvil cumple con el
     * formato correcto.
     * @param numSerie Número de serie a validar.
     * @throws PresentacionException si el número de serie está vacío o no tiene
     * 17 caracteres.
     */
    public void validarNumSerie(String numSerie) throws PresentacionException {
        try {
            validarVacio(numSerie);
        } catch (PresentacionException pe) {
            // Si el número de serie está vacío, lanzamos una excepción.
            throw new PresentacionException("El número de serie no puede estar vacío. Ejemplo: \"1HGBA87NXBV165879\".");
        }
        if (numSerie.length() != 17) {
            // Si el número de serie no tiene 17 caracteres, lanzamos una excepción.
            throw new PresentacionException("El número de serie debe tener 17 caracteres. Ejemplo: \"1HGBA87NXBV165879\".");
        }
    }
    
    /**
     * Método para validar que la marca de un automóvil cumple con el formato
     * correcto.
     * @param marca Marca a validar.
     * @throws PresentacionException si la marca está vacía o contiene números.
     */
    public void validarMarca(String marca) throws PresentacionException {
        try {
            validarVacio(marca);
        } catch (PresentacionException pe) {
            // Si la marca está vacía, lanzamos una excepción.
            throw new PresentacionException("La marca no puede estar vacía. Ejemplo: \"Nissan\".");
        }
        if (marca.matches(".*\\d.*")) {
            // Si la marca contiene números, lanzamos una excepción.
            throw new PresentacionException("La marca no puede estar vacía. Ejemplo: \"Nissan\".");
        }
    }
    
    /**
     * Método para validar que la línea de un automóvil cumple con el formato
     * correcto.
     * @param linea Línea a validar.
     * @throws PresentacionException si la línea está vacía.
     */
    public void validarLinea(String linea) throws PresentacionException {
        try {
            validarVacio(linea);
        } catch (PresentacionException pe) {
            // Si la línea está vacía, lanzamos una excepción.
            throw new PresentacionException("La línea no puede estar vacía. Ejemplo: \"Sentra\".");
        }
    }
    
    /**
     * Método para validar que el color de un automóvil cumple con el formato
     * correcto.
     * @param color Color a validar.
     * @throws PresentacionException si el color está vacío o contiene números.
     */
    public void validarColor(String color) throws PresentacionException {
        try {
            validarVacio(color);
        } catch (PresentacionException pe) {
            // Si el color está vacío, lanzamos una excepción.
            throw new PresentacionException("El color no puede estar vacío. Ejemplo: \"Blanco\".");
        }
        if (color.matches(".*\\d.*")) {
            // Si el color contiene números, lanzamos una excepción.
            throw new PresentacionException("El color no puede tener números. Ejemplo: \"Blanco\".");
        }
    }
    
    /**
     * Método para validar que el modelo de un automóvil cumple con el formato
     * correcto.
     * @param modelo Modelo a validar.
     * @throws PresentacionException si el modelo está vacío o contiene letras.
     */
    public void validarModelo(String modelo) throws PresentacionException {
        try {
            validarVacio(modelo);
        } catch (PresentacionException pe) {
            // Si el modelo está vacío, lanzamos una excepción.
            throw new PresentacionException("El modelo no puede estar vacío. Ejemplo: \"2016\".");
        }
        if (!modelo.matches("\\d+") || modelo.length() != 4) {
            // Si el modelo contiene algo que no sean 4 números, lanzamos una excepción.
            throw new PresentacionException("El modelo sólo debe tener 4 números. Ejemplo: \"2016\".");
        }
    }

    public void validarPlacas(String numPlacas) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
