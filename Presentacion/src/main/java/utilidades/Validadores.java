/*
 * Validadores.java
 */
package utilidades;

import excepciones.PresentacionException;

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
     * @return Verdadero si la cadena no está vacía, falso si sí lo está.
     * @throws PresentacionException si la cadena del parámetro está vacía.
     */
    public boolean validarVacio(String cadena) throws PresentacionException {
        return (!cadena.isBlank());
    }
    
    /**
     * Método para validar que una curp cumple con el formato correcto.
     * @param curp CURP a validar.
     * @throws PresentacionException si la curp está vacía o excede los 13 caracteres.
     */
    public void validarCurp(String curp) throws PresentacionException {
        if (!validarVacio(curp)) {
            // Si la CURP está vacía, lanzamos una excepción.
            throw new PresentacionException("La CURP no puede estar vacía.");
        } else if (curp.length() != 18) {
            // Si la CURP excede los 13 caracteres, lanzamos una excepción.
            throw new PresentacionException("La CURP debe tener 18 caracteres.");
        }
    }
}
