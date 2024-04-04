package utilidades;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Diego Valenzuela Parra - 00000247700
 * @author Juventino López García - 00000248547
 */
public class FormatoDinero {
    public String formatear(Float cantidad) {
        // Crear un formato de dinero para el idioma y país deseado.
        Locale locale = new Locale("es", "MX");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        // Formatear el número como dinero.
        String cantFormateada = currencyFormatter.format(cantidad);
        
        return cantFormateada;
    }
}
