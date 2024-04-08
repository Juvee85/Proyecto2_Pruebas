/*
 * FormatoDinero.java
 */
package utilidades;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Clase para darle formato a las cantidades de dinero.
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class FormatoDinero {

    /**
     * Método para darle formato al dinero.
     *
     * @param cantidad Cantidad que se busca formatear.
     * @return La cantidad de dinero ya con el formato correcto.
     */
    public String formatear(Float cantidad) {
        // Creamos un formato de dinero para México.
        Locale locale = new Locale("es", "MX");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        // Formateamos la cantidad como dinero.
        String cantFormateada = currencyFormatter.format(cantidad) + " MXN";

        // Retornamos la cantidad con el formato aplicado.
        return cantFormateada;
    }
}
